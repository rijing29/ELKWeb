package com.elk.elkweb.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface FrontService {
    //    全院刷卡信息查询
    List<Map<String,Object>> getCardDPMInfo();

    //    全院平均刷卡信息查询
    List<Map<String,Object>> getCardDPMAvg();

    //    服务器平均负载查询
    List<Map<String,Object>> getServerStateAvg();

    //    IPMI告警数查询
    int getIPMIAlertNum(String time);

    //    平均任务数量查询
    float getTaskAvg();

    //    agent故障数量查询
    List<Map<String,Object>>getAgentNum();

    //    轮播图信息汇总
    List<Map<String,Object>>getGetherInfo();
}
