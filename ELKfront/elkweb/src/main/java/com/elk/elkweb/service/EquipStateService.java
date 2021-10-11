package com.elk.elkweb.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public interface EquipStateService {
    //    获取cpu
    List< Map< String, Object > > getCPU();
    //    获取gpu
    List< Map< String, Object > > getGPU();
    //    软件使用率
    String getMaxTime();
    List<Map<String,Object>> getSoftName();
    Double getSumJob(String soft_name,String startTime, String stoptime);
    Double getSumWork(String soft_name);
    //    获取存储系统使用
    List< Map< String, Object > > getCCUsage();
}
