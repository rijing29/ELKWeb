package com.elk.elkweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PersonActionLogMapper {
//    员工行为画像
    List searchEpyBehavProfile( @Param("startTime")String startTime,
                                @Param("stopTime")String stopTime,
                                @Param("userName")String userName);
}
