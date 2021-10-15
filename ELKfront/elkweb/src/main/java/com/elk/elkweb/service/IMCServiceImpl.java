package com.elk.elkweb.service;

import com.elk.elkweb.entity.IMCAlarm;
import com.elk.elkweb.entity.IPMILogInfo;
import com.elk.elkweb.mapper.IMCMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class IMCServiceImpl implements IMCService{
    @Autowired
    private IMCMapper imcMapper;
    @Override
    public List<Map<String, Object>> getIMCIP() {
        List<Map<String, Object>> imcip = imcMapper.getIMCIP();
        return imcip;
    }

    @Override
    public List<IMCAlarm> getIMCInfo(String ip, String filtration) {
        List<IMCAlarm> imcInfo = imcMapper.getIMCInfo(ip, filtration);
        return imcInfo;
    }

    @Override
    public List<IMCAlarm> getDefaultIMC() {
        List<IMCAlarm> defaultIMC = imcMapper.getDefaultIMC();
        return defaultIMC;
    }
}
