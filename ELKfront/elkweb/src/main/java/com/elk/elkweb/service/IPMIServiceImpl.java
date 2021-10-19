package com.elk.elkweb.service;

import com.elk.elkweb.entity.IPMILogInfo;
import com.elk.elkweb.entity.IPMILogWarn;
import com.elk.elkweb.mapper.IPMIMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class IPMIServiceImpl implements IPMIService{

    @Autowired
    private IPMIMapper ipmiMapper;
    @Override
    public List<IPMILogWarn> getIPMIAlarm() {
        List<IPMILogWarn> IPMIAlarm = ipmiMapper.getIPMIAlarm();
        return IPMIAlarm;
    }

    @Override
    public List<IPMILogInfo> getIPMIInfo(String ip) {
        List<IPMILogInfo> IPMIInfo = ipmiMapper.getIPMIInfo(ip);
        return IPMIInfo;
    }

    @Override
    public List<Map<String, Object>> getIPSelection() {
        List<Map<String, Object>> IPSelection = ipmiMapper.getIPSelection();
        return IPSelection;
    }

    @Override
    public List<IPMILogInfo> getIPMIInfo1(String ip) {
        List<IPMILogInfo> IPMIInfo1 = ipmiMapper.getIPMIInfo1(ip);
        return IPMIInfo1;
    }

    @Override
    public List<IPMILogWarn> getIPMIAlarm1(String ip) {
        List<IPMILogWarn> IPMIAlarm1 = ipmiMapper.getIPMIAlarm1(ip);
        return IPMIAlarm1;
    }

    @Override
    public List<IPMILogInfo> getDefaultIPMI() {
        List<IPMILogInfo> defaultIPMI = ipmiMapper.getDefaultIPMI();
        return defaultIPMI;
    }
}
