package com.spproject.xdclass.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiaolongzhao
 */
public class VideoBanner implements Serializable {

    private int id;

    private  String url;

    private  String img;

    private Date createTime;

    private int weight;

    @Override
    public String toString() {
        return "VideoBanner{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", img='" + img + '\'' +
                ", createTime=" + createTime +
                ", weight=" + weight +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public int getWeight() {
        return weight;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
