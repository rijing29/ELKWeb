package com.elk.elkweb.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface SoftUserService {
    //    查询软件
    List<Map< String, Object >> getSoftName();

    //    根据软件名查询一周内用户数据
    List<Map< String, Object >> getSoft_userInfo(String softName);

    //查询一周内用户使用软件效率

    String getMaxTime();
    Double getWork_load(String users);
    List<Map< String, Object >> getsoftname(String soft_name);
    Double getSumJob(String soft_name,String users,String startTime,String stopTime);
}
