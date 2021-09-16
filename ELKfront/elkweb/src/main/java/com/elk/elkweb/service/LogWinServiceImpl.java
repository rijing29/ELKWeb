package com.elk.elkweb.service;

import com.elk.elkweb.entity.LogWinLogin;
import com.elk.elkweb.entity.LogWinWarning;
import com.elk.elkweb.mapper.LogWinMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LogWinServiceImpl implements LogWinService{
    @Autowired
    private LogWinMapper logWinMapper;
    @Override
    public List<Map< String, Object >> getWinWaring() {
        List<Map< String, Object >> winWaring = logWinMapper.getWinWaring();
        return winWaring;
    }

    @Override
    public List<Map< String, Object >> getWinServiceInfo() {
        List<Map< String, Object >> winServiceInfo = logWinMapper.getWinServiceInfo();
        return winServiceInfo;
    }

    @Override
    public List<Map<String, Object>> getWinServiceJobInc() {
        List<Map<String, Object>> winServiceJobInc = logWinMapper.getWinServiceJobInc();
        return winServiceJobInc;
    }

    @Override
    public List<Map<String, Object>> getWinLoad() {
        List<Map<String, Object>> winLoad = logWinMapper.getWinLoad();
        return winLoad;
    }
}
