package com.elk.elkweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface EPYMapper {
    //    查询员工刷卡信息
    List<Map< String, Object >> getEpyCardInfo(@Param("user_lname")String user_lname);

    //    查询员工统计数据
    List<Map< String, Object >> getEpyCardData(@Param("personname")String user_lname);

    //    查询员工DQMDA信息
    List<Map< String, Object >> getEpyDQMSInfo(@Param("username")String username);

    //    查询员工DQMDS数据
    List<Map< String, Object >> getEpyDQMDSData(@Param("personname")String personname);

    //查询员工外网浏览信息
    List<Map< String, Object >> getEpyNetInfo(@Param("username") String username);

    //默认查询
    List<Map< String, Object >> getDefaultCardInfo();
    List<Map< String, Object >> getDefaultNetInfo();
    List<Map< String, Object >> getDefaultDQMDS();
    List<Map< String, Object >> getDefaultSoftInfo();
    List<Map< String, Object >> getDefaultBehavior();
}
