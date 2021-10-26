package com.spproject.xdclass.service;

import com.spproject.xdclass.model.entity.VideoOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoOrderService {

    int save(int userId, int videoId);

    List<VideoOrder> listOrderByUserId(int userId);

}
