package com.elk.elkweb.service;

import com.elk.elkweb.mapper.EPYMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.elk.elkweb.entity.ServerPublic;
import com.elk.elkweb.mapper.EPYMapper;
import com.elk.elkweb.mapper.EpySoftWareUsageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class EPYServiceImpl implements EPYService{
    @Autowired
    private EPYMapper epyMapper;
    @Autowired
    private EpySoftWareUsageMapper epySoftWareUsageMapper;
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

    //    查询软件使用行为
    @Override
    public List<EpySoftWareUsageMapper> showEpySoftWareUsage(String username) {
        List<EpySoftWareUsageMapper> epySoftWareUsageMapperList = epySoftWareUsageMapper.showEpySoftWareUsage(username);
        return epySoftWareUsageMapperList;
    }

    //    员工近期加班使用软件统计
    @Override
    public List<Map<String, Object>> showEpySoftWareUsageCount(String username) {
        List<Map<String, Object>> softWareUsageCount = epySoftWareUsageMapper.showEpySoftWareUsageCount(username);
        return softWareUsageCount;
    }
    //员工外网浏览信息
    @Override
    public List<Map<String, Object>> getEpyNetInfo(String username) {
        List<Map<String, Object>> epyNetInfo = epyMapper.getEpyNetInfo(username);
        return epyNetInfo;
    }

    //默认查询
    @Override
    public List<Map<String, Object>> getDefaultCardInfo() {
        List<Map<String, Object>> defaultCardInfo = epyMapper.getDefaultCardInfo();
        return defaultCardInfo;
    }

    @Override
    public List<Map<String, Object>> getDefaultNetInfo() {
        List<Map<String, Object>> defaultNetInfo = epyMapper.getDefaultNetInfo();
        return defaultNetInfo;
    }

    @Override
    public List<Map<String, Object>> getDefaultDQMDS() {
        List<Map<String, Object>> defaultDQMDS = epyMapper.getDefaultDQMDS();
        return defaultDQMDS;
    }

    @Override
    public List<Map<String, Object>> getDefaultSoftInfo() {
        List<Map<String, Object>> defaultSoftInfo = epyMapper.getDefaultSoftInfo();
        return defaultSoftInfo;
    }

    @Override
    public List<Map<String, Object>> getDefaultBehavior() {
        List<Map<String, Object>> defaultBehavior = epyMapper.getDefaultBehavior();
        return defaultBehavior;
    }


}
