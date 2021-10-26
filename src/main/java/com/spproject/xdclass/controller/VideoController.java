package com.spproject.xdclass.controller;


import com.spproject.xdclass.model.entity.Video;
import com.spproject.xdclass.model.entity.VideoBanner;
import com.spproject.xdclass.model.entity.VideoCart;
import com.spproject.xdclass.service.VideoService;
import com.spproject.xdclass.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaolongzhao
 */
@RestController
@RequestMapping("api/v1/pub/video")
public class VideoController {
    @Autowired
    private VideoService videoService;
    @Autowired
    private RedisTemplate redisTemplate;

    private static final String VIDEO_CARD_CACHE_KEY = "video:card:key";

    @GetMapping("list_banner")
    public JsonData indexBanner(){
        List<VideoBanner> bannerList = videoService.listBanner();
        return JsonData.buildSuccess(bannerList);
    }

    @GetMapping("list_banner_cache")
    public JsonData listCardCache(){


        Object cacheObj = redisTemplate.opsForValue().get(VIDEO_CARD_CACHE_KEY);

        if(cacheObj != null){

            List<VideoBanner> list = (List<VideoBanner>)  cacheObj;
            return JsonData.buildSuccess(list);

        } else {

            List<VideoBanner> list = videoService.listBanner();

            redisTemplate.opsForValue().set(VIDEO_CARD_CACHE_KEY,list,10, TimeUnit.MINUTES);

            return JsonData.buildSuccess(list);
        }

    }

    @RequestMapping("list")
    public Object listVideo(){
        List<Video> videoList = videoService.listVideo();
        return JsonData.buildSuccess(videoList);
    }

    @GetMapping("find_detail_by_id")
    public JsonData findDetailById(@RequestParam(value = "video_id", required = true) int videoId){
        Video video = videoService.findDetailById(videoId);
        System.out.println("888");
        return JsonData.buildSuccess(video);
    }

    @GetMapping("find_Video_Cart")
    public JsonData findVideoCart(@RequestParam(value ="video_id",required = true) int videoId){
        VideoCart videoCart = videoService.findVideoCart(videoId);
        return  JsonData.buildSuccess(videoCart);
    }
}
