package com.spproject.xdclass.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiaolongzhao
 */
public class VideoCart implements Serializable {

    private int id;

    private  String title;

    private  String coverImg;

    private  int price;

    private int buyNum;

    private int totalPrice;

    public int getTotalPrice() {
        return this.price * this.buyNum;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
