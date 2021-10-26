package com.spproject.xdclass.mapper;

import com.spproject.xdclass.model.entity.PlayRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayRecordMapper {

    int saveRecord(PlayRecord playRecord);
}
