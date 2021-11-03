package com.elk.elkweb.service;

import com.elk.elkweb.mapper.LogQueryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LogQueryServiceImpl implements LogQueryService{
    @Autowired
    private LogQueryMapper logQueryMapper;

    @Override
    public List<Map<String, Object>> getZabbixShenji() {
        List<Map<String, Object>> zabbixShenji = logQueryMapper.getZabbixShenji();
        return zabbixShenji;
    }

    @Override
    public List<Map<String, Object>> getCdnDataLog() {
        List<Map<String, Object>> cdnDataLog = logQueryMapper.getCdnDataLog();
        return cdnDataLog;
    }

    @Override
    public List<Map<String, Object>> getSysSrmLog() {
        List<Map<String, Object>> sysSrmLog = logQueryMapper.getSysSrmLog();
        return sysSrmLog;
    }
}
