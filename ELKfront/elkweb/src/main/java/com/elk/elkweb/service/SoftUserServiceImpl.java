package com.elk.elkweb.service;

import com.elk.elkweb.mapper.SoftUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SoftUserServiceImpl implements SoftUserService {
    @Autowired
    private SoftUserMapper softUserMapper;
    @Override
    public List<Map<String, Object>> getSoftName() {
        List<Map<String, Object>> softName = softUserMapper.getSoftName();
        return softName;
    }

    @Override
    public List<Map<String, Object>> getSoft_userInfo(String softName) {
        List<Map<String, Object>> soft_userInfo = softUserMapper.getSoft_userInfo(softName);
        return soft_userInfo;
    }

    @Override
    public String getMaxTime() {
        String maxTime = softUserMapper.getMaxTime();
        return maxTime;
    }

    @Override
    public Double getWork_load(String soft_name) {
        Double workLoad = softUserMapper.getWork_load(soft_name);
        return workLoad;
    }

    @Override
    public List<Map< String, Object >> getsoftname(String users) {
        List<Map< String, Object >> softname = softUserMapper.getsoftname(users);
        return softname;
    }

    @Override
    public Double getSumJob(String soft_name,String users, String startTime, String stopTime) {
        Double sumJob = softUserMapper.getSumJob(soft_name,users, startTime, stopTime);
        return sumJob;
    }
}
