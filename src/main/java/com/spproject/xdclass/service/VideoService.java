package com.spproject.xdclass.service;



import com.spproject.xdclass.model.entity.Video;
import com.spproject.xdclass.model.entity.VideoBanner;
import com.spproject.xdclass.model.entity.VideoCart;

import java.util.List;

/**
 * @author xiaolongzhao
 */
public interface VideoService {

    List<Video> listVideo();

    List<VideoBanner> listBanner();

    Video findDetailById(int videoId);

    VideoCart findVideoCart(int videoId);
}
