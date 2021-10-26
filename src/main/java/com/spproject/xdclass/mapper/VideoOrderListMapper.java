package com.spproject.xdclass.mapper;

import com.spproject.xdclass.model.entity.Video;
import com.spproject.xdclass.model.entity.VideoOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoOrderListMapper {
    List<VideoOrder> listOrderByUserId(@Param("user_id") int userId);
}
