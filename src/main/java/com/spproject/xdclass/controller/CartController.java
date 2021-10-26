package com.spproject.xdclass.controller;

import com.spproject.xdclass.model.entity.Cart;
import com.spproject.xdclass.model.entity.VideoCart;
import com.spproject.xdclass.service.VideoService;
import com.spproject.xdclass.utils.JsonData;
import com.spproject.xdclass.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaolongzhao
 */
@RestController
@RequestMapping("api/v1/pri/cart")
public class CartController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private VideoService videoService;

    @RequestMapping("add")
    public JsonData addCart(@RequestParam(value = "video_id") int videoId, @RequestParam(value = "num"
    ) int buyNum, HttpServletRequest request){

        //获取购物车
        BoundHashOperations<String,Object,Object> myCart = getMyCartOps(request);

        Object cacheObj = myCart.get(videoId+"");

        String result = "";
        if(cacheObj!=null){
            result = (String)cacheObj;
        }


        //购物车没这个商品
        if(cacheObj == null){

            VideoCart videoCart = videoService.findVideoCart(videoId);


            myCart.put(videoId+"", JsonUtil.objectToJson(videoCart));

        }else {
            //增加商品购买数量
            VideoCart videoCart = JsonUtil.jsonToPojo(result,VideoCart.class);
            videoCart.setBuyNum(videoCart.getBuyNum()+buyNum);

            myCart.put(videoId+"",JsonUtil.objectToJson(videoCart));
        }


        return JsonData.buildSuccess();


    }

    @RequestMapping("mycart")
    public JsonData getMycart(HttpServletRequest request){
        //获取购物车
        BoundHashOperations<String,Object,Object> myCart = getMyCartOps(request);

        List<Object> itemList =  myCart.values();

        List<VideoCart> cartItemVOList = new ArrayList<>();

        for(Object item : itemList){
            VideoCart cartItemVO = JsonUtil.jsonToPojo((String)item,VideoCart.class);
            cartItemVOList.add(cartItemVO);
        }

        Cart cartVO = new Cart();
        cartVO.setCartItems(cartItemVOList);

        return JsonData.buildSuccess(cartVO);

    }

    private BoundHashOperations<String,Object,Object> getMyCartOps(HttpServletRequest request){

        String key = getCartKey(request);

        return redisTemplate.boundHashOps(key);
    }


    private String getCartKey(HttpServletRequest request){

        //用户的id,从拦截器获取
        Integer userId = (Integer) request.getAttribute("user_id");
        String cartKey = String.format("video:cart:%s",userId);
        return cartKey;

    }
}
