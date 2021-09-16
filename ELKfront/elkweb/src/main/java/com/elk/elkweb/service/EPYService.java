package com.elk.elkweb.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface EPYService {
    //    查询员工刷卡信息
    List<Map< String, Object >> getEpyCardInfo(String user_lname);

    //    查询员工统计数据
    List<Map< String, Object >> getEpyCardData(String personname);

    //    查询员工DQMDA信息
    List<Map< String, Object >> getEpyDQMSInfo(String username);

    //    查询员工DQMDS数据
    List<Map< String, Object >> getEpyDQMDSData(String personname);
}
