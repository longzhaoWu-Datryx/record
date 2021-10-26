package com.spproject.xdclass.service.impl;

import com.spproject.xdclass.Exception.XDException;
import com.spproject.xdclass.mapper.*;
import com.spproject.xdclass.model.entity.Episode;
import com.spproject.xdclass.model.entity.PlayRecord;
import com.spproject.xdclass.model.entity.Video;
import com.spproject.xdclass.model.entity.VideoOrder;
import com.spproject.xdclass.service.VideoOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class VideoOrderServiceimpl implements VideoOrderService {

    @Autowired
    private VideoOrderMapper videoOrderMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private episodeMapper EpisodeMapper;

    @Autowired
    private PlayRecordMapper playRecordMapper;

    @Autowired
    private VideoOrderListMapper videoOrderListMapper;


    @Override
    @Transactional
    public int save(int userId, int videoId) {

        VideoOrder videoOrder = videoOrderMapper.findByUserIdAndVideoIdAndState(userId,videoId,1);

        if (videoOrder != null){return 0;}

        Video video = videoMapper.findById(videoId);

        if (video == null){
            return  0;
        }

        VideoOrder newVideoOrder = new VideoOrder();
        newVideoOrder.setCreateTime(new Date());
        newVideoOrder.setOutTradeNo(UUID.randomUUID().toString());
        newVideoOrder.setState(1);
        newVideoOrder.setTotalFee(video.getPrice());
        newVideoOrder.setUserId(userId);

        newVideoOrder.setVideoId(videoId);
        newVideoOrder.setVideoImg(video.getCoverImg());
        newVideoOrder.setVideoTitle(video.getTitle());

        int rows = videoOrderMapper.saveOrder(newVideoOrder);

        if(rows == 1){
            Episode episode = EpisodeMapper.findFirstEpisodeById(videoId);
            if(episode == null){
                throw  new XDException(-1,"视频没有集信息，请运营人员检查");
            }
            PlayRecord playRecord = new PlayRecord();
            playRecord.setCreateTime(new Date());
            playRecord.setEpisodeId(episode.getId());
            playRecord.setCurrentNum(episode.getNum());
            playRecord.setUserId(userId);
            playRecord.setVideoId(videoId);
            playRecordMapper.saveRecord(playRecord);
        }
        return rows;
    }

    @Override
    public List<VideoOrder> listOrderByUserId(int userId) {

        List<VideoOrder> videoOrderList = videoOrderListMapper.listOrderByUserId(userId);

        return videoOrderList;
    }

}
