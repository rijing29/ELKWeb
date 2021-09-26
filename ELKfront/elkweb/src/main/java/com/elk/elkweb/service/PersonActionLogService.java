package com.elk.elkweb.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonActionLogService {
//    员工行为画像
    List searchEpyProfile(String startTime,String stopTime,String userName);
}
