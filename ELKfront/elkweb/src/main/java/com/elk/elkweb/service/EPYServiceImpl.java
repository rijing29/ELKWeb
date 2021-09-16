package com.elk.elkweb.service;

import com.elk.elkweb.mapper.EPYMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EPYServiceImpl implements EPYService{
    @Autowired
    private EPYMapper epyMapper;

    //    查询员工刷卡信息
    @Override
    public List<Map<String, Object>> getEpyCardInfo(String user_lname) {
        List<Map<String, Object>> epyCardInfo =epyMapper.getEpyCardInfo(user_lname);
        return epyCardInfo;
    }

    @Override
    public List<Map<String, Object>> getEpyCardData(String personname) {
        List<Map<String, Object>> epyCardData =epyMapper.getEpyCardData(personname);
        return epyCardData;
    }

    @Override
    public List<Map<String, Object>> getEpyDQMSInfo(String username) {
        List<Map<String, Object>> epyDQMSInfo = epyMapper.getEpyDQMSInfo(username);
        return epyDQMSInfo;
    }

    @Override
    public List<Map<String, Object>> getEpyDQMDSData(String personname) {
        List<Map<String, Object>> epyDQMDSData =epyMapper.getEpyDQMDSData(personname);
        return epyDQMDSData;
    }
}
