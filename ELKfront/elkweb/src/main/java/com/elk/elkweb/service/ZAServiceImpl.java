package com.elk.elkweb.service;

import com.elk.elkweb.mapper.ZAHostsErrorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class ZAServiceImpl implements ZAHostsErrorService{
    @Autowired
    private ZAHostsErrorMapper zaHostsErrorMapper;
    //    查询zabbix error
    public List<Map<String, Object>> serachZAError() {
        List<Map<String, Object>> serachZAError = zaHostsErrorMapper.serachZAError();
        return serachZAError;
    }

    @Override
    public List<Map<String, Object>> searchWinlogbeats() {
        List<Map<String, Object>> Winlogbeats = zaHostsErrorMapper.searchWinlogbeats();
        return Winlogbeats;
    }
}