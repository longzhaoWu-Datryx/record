package com.spproject.xdclass.mapper;
import com.spproject.xdclass.model.entity.Video;
import com.spproject.xdclass.model.entity.VideoBanner;
import com.spproject.xdclass.model.entity.VideoCart;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface VideoMapper
{
    /**
     * 查询视频列表
     * @return
     */
    List<Video> listVideo();

    List<VideoBanner> listVideoBanner();

    Video findDetailById(@Param("video_id") int videoId);

    Video findById(@Param("video_id") int videoId);

    VideoCart findVideoCart(@Param("video_id") int videoId);
}
