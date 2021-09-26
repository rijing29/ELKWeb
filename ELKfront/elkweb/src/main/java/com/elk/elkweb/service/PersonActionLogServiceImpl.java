package com.elk.elkweb.service;

import com.elk.elkweb.mapper.PersonActionLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonActionLogServiceImpl implements PersonActionLogService{
    @Autowired
    private PersonActionLogMapper personActionLogMapper;
    @Override
    public List searchEpyProfile(String startTime,String stopTime,String userName) {
        List behavProfile = personActionLogMapper.searchEpyBehavProfile(startTime,stopTime,userName);
        return behavProfile;
    }
}
