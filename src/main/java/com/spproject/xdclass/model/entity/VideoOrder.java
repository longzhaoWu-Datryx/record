package com.spproject.xdclass.model.entity;

import java.io.Serializable;
import java.util.Date;

public class VideoOrder implements Serializable {

    private Integer id;

    private String outTradeNo;

    private Integer state;

    private Date createTime;

    private  Integer totalFee;

    private Integer videoId;

    private  String videoTitle;

    private  String videoImg;

    private  Integer userId;

    @Override
    public String toString() {
        return "VideoOrder{" +
                "id=" + id +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", state=" + state +
                ", creatTime=" + createTime +
                ", totalFee=" + totalFee +
                ", videoId=" + videoId +
                ", videoTitle='" + videoTitle + '\'' +
                ", videoImg='" + videoImg + '\'' +
                ", userId=" + userId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date creatTime) {
        this.createTime = createTime;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoImg() {
        return videoImg;
    }

    public void setVideoImg(String videoImg) {
        this.videoImg = videoImg;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
