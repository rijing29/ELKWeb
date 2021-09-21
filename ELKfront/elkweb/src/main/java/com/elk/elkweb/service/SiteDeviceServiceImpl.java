package com.elk.elkweb.service;

import com.elk.elkweb.mapper.SiteDeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class SiteDeviceServiceImpl implements SitDeviceService{
    @Autowired
    private SiteDeviceMapper siteDeviceMapper;
    @Override
    public List<Map<String, Object>> getUPSInfo() {
        List<Map<String, Object>> upsInfo = siteDeviceMapper.getUPSInfo();
        return upsInfo;
    }

    @Override
    public List<Map<String, Object>> getEnvironmentInfo() {
        List<Map<String, Object>> environmentInfo = siteDeviceMapper.getEnvironmentInfo();
        return environmentInfo;
    }
}
