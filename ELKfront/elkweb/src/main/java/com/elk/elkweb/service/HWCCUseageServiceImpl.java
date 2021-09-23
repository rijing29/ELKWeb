package com.elk.elkweb.service;

import com.elk.elkweb.mapper.HWCCUsageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class HWCCUseageServiceImpl implements HWCCUseageService{
    @Autowired
    private HWCCUsageMapper hwccUsageMapper;
    @Override
    public List<Map<String, Object>> getHWCCUseage() {
        List<Map<String, Object>> hwccUseage = hwccUsageMapper.getHWCCUseage();
        return hwccUseage;
    }

    @Override
    public List<Map<String, Object>> getMemUseInfo(String CCNAME) {
        List<Map<String, Object>> hwccUseage = hwccUsageMapper.getMemUseInfo(CCNAME);
        return hwccUseage;
    }
}
