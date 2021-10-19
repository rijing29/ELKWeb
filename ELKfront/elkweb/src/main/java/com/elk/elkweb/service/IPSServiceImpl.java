package com.elk.elkweb.service;

import com.elk.elkweb.entity.IPS_ANALYIZE;
import com.elk.elkweb.mapper.IPSMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class IPSServiceImpl implements IPSService{
    @Autowired
    private IPSMapper ipsMapper;
    @Override
    public List<Map< String, Object >> getIPSAnalysis(String Time) {
        List<Map< String, Object >> IPSAnalyse = ipsMapper.getIPSAnalysis(Time);
        return IPSAnalyse;
    }

    @Override
    public List<Map<String, Object>> getIPSInfo(String startTime, String stopTime, String dstipaddr) {
        List<Map< String, Object >> IPSInfo = ipsMapper.getIPSInfo(startTime, stopTime, dstipaddr);
        return IPSInfo;
    }
    //根据源ip查询所有被攻击ip
    public List<Map< String, Object >> getAttackedIP(String startTime, String stopTime, String srcipaddr) {
        List<Map<String, Object>> attackedIP = ipsMapper.getAttackedIP(startTime, stopTime, srcipaddr);
        return attackedIP;
    }


}
