package com.spproject.xdclass.model.entity;

import java.util.List;

/**
 * @author xiaolongzhao
 */
public class Cart {

    /**
     * 购物项
     */
    private List<VideoCart> cartItems;


    /**
     * 购物车总价格
     */
    private Integer totalAmount;



    /**
     * 总价格
     * @return
     */
    public int getTotalAmount() {
        return cartItems.stream().mapToInt(VideoCart::getTotalPrice).sum();
    }



    public List<VideoCart> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<VideoCart> cartItems) {
        this.cartItems = cartItems;
    }
}

