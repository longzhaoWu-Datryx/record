package com.spproject.xdclass.mapper;

import com.spproject.xdclass.model.entity.Video;
import com.spproject.xdclass.model.entity.VideoOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoOrderMapper {

    VideoOrder findByUserIdAndVideoIdAndState(@Param("user_id") int userId, @Param("video_id") int videoId, @Param("state") int state);

    int saveOrder(VideoOrder videoOrder);

}
