package com.elk.elkweb.service;

import java.util.List;
import java.util.Map;

import com.elk.elkweb.mapper.SLAMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SLAServiceImpl implements SLAService{

    @Autowired
    private SLAMapper sLAMapper;

    //    查询软件使用频率
    @Override
    public List<Map< String, Object >> getAllFrequency(String startTime, String stopTime) {
        List<Map< String, Object >> getAllFrequency = sLAMapper.getAllFrequency(startTime,stopTime);
        return getAllFrequency;
    }

    //    查询软件使用时间
    @Override
    public List<Map<String, Object>> getSumTime(String startTime, String stopTime) {
        List<Map< String, Object >> getSumTime = sLAMapper.getSumTime(startTime,stopTime);
        return getSumTime;
    }

    //    查询软件选择下拉框信息
    @Override
    public List<Map<String, Object>> getSoftwareName(String startTime, String stopTime) {
        List<Map< String, Object >> getSoftwareName = sLAMapper.getSoftwareName(startTime,stopTime);
        return getSoftwareName;
    }

    //    查询软件模块使用次数
    @Override
    public List<Map<String, Object>> getModuleCount(String startTime, String stopTime, String softwareName) {
        List<Map< String, Object >> getModuleCount = sLAMapper.getModuleCount(startTime,stopTime,softwareName);
        return getModuleCount;
    }
    //    查询软件模块使用时间
    @Override
    public List<Map<String, Object>> getModuleTime(String startTime, String stopTime, String softwareName) {
        List<Map< String, Object >> getModuleTime = sLAMapper.getModuleTime(startTime,stopTime,softwareName);
        return getModuleTime;
    }


}
