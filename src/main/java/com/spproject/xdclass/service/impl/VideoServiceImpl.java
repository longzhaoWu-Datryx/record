package com.spproject.xdclass.service.impl;


import com.spproject.xdclass.config.CacheKeyManager;
import com.spproject.xdclass.model.entity.Video;
import com.spproject.xdclass.model.entity.VideoBanner;
import com.spproject.xdclass.mapper.VideoMapper;
import com.spproject.xdclass.model.entity.VideoCart;
import com.spproject.xdclass.service.VideoService;
import com.spproject.xdclass.utils.BaseCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaolongzhao
 */
@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private  VideoMapper VideoMapper;
    
    @Autowired
    private BaseCache baseCache;


    @Override
    public List<Video> listVideo() {
        try{

            Object cacheObj =  baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_VIDEL_LIST,()->{

                List<Video> videoList = VideoMapper.listVideo();

                return videoList;

            });

            if(cacheObj instanceof List){
                List<Video> videoList = (List<Video>)cacheObj;
                return videoList;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        //可以返回兜底数据，业务系统降级-》SpringCloud专题课程
        return null;
  }

    @Override
    public List<VideoBanner> listBanner() {

        try{

            Object cacheObj =  baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_BANNER_KEY, ()->{

                List<VideoBanner> bannerList =  VideoMapper.listVideoBanner();

                System.out.println("从数据库里面找轮播图列表");

                return bannerList;

            });

            if(cacheObj instanceof List){
                List<VideoBanner> bannerList = (List<VideoBanner>)cacheObj;
                return bannerList;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Video findDetailById(int videoId){

        String videoCacheKey = String.format(CacheKeyManager.VIDEO_DETAIL,videoId);

        try{

            Object cacheObject = baseCache.getOneHourCache().get( videoCacheKey, ()->{

                // 需要使用mybaits关联复杂查询
                Video video = VideoMapper.findDetailById(videoId);

                return video;

            });

            if(cacheObject instanceof Video){

                Video video = (Video)cacheObject;
                return video;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public VideoCart findVideoCart(int videoId){
        VideoCart videoCart = VideoMapper.findVideoCart(videoId);
        return  videoCart;
    }
}
