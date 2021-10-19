package com.elk.elkweb.service;

import com.elk.elkweb.entity.IPS_ANALYIZE;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public interface IPSService {
    //    查询IPS告警分析
    List<Map< String, Object >> getIPSAnalysis(String Time);
    //    查询IPS详细信息
    List<Map< String, Object >> getIPSInfo(String startTime,String stopTime,String dstipaddr);
    //    根据源ip查询所有被攻击的ip
    List<Map< String, Object >> getAttackedIP(String startTime,String stopTime,String srcipaddr);
}
