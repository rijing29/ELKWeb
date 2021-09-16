package com.elk.elkweb.service;

import com.elk.elkweb.mapper.EpySoftWareUsageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class showEpySoftWareUsageImpl implements showEpySoftWareUsage {
    @Autowired
    private EpySoftWareUsageMapper epySoftWareUsageMapper;
    public List<EpySoftWareUsageMapper> showEpySoftWareUsage(String username) {
        List<EpySoftWareUsageMapper> epySoftWareUsageMapperList = epySoftWareUsageMapper.showEpySoftWareUsage(username);
        return epySoftWareUsageMapperList;
    }
}
