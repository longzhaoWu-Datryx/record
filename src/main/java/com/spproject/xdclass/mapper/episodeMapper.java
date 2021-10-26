package com.spproject.xdclass.mapper;

import com.spproject.xdclass.model.entity.Episode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface episodeMapper {

    Episode findFirstEpisodeById(@Param("video_id") int videoId);
}
