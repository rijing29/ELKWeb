package com.elk.elkweb.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface SitDeviceService {
    //    查询UPS监控信息
    List<Map<String,Object>> getUPSInfo();
    //    查询温湿度监控信息
    List<Map<String,Object>> getEnvironmentInfo();
}
