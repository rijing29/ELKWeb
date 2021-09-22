package com.elk.elkweb.service;

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

}
