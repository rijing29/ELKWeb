package com.elk.elkweb.service;

import com.elk.elkweb.entity.ServerPublic;
import com.elk.elkweb.mapper.EPYMapper;
import com.elk.elkweb.mapper.EpySoftWareUsageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EPYServiceImpl implements EPYService{
    @Autowired
    private EPYMapper epyMapper;
    @Autowired
    private EpySoftWareUsageMapper epySoftWareUsageMapper;
    //    查询员工刷卡信息
    public List<Map<String, Object>> getEpyCardInfo(String user_lname) {
        List<Map<String, Object>> epyCardInfo =epyMapper.getEpyCardInfo(user_lname);
        return epyCardInfo;
    }

    public List<Map<String, Object>> getEpyCardData(String personname) {
        List<Map<String, Object>> epyCardData =epyMapper.getEpyCardData(personname);
        return epyCardData;
    }


    //    查询软件使用行为
    public List<EpySoftWareUsageMapper> showEpySoftWareUsage(String username) {
        List<EpySoftWareUsageMapper> epySoftWareUsageMapperList = epySoftWareUsageMapper.showEpySoftWareUsage(username);
        return epySoftWareUsageMapperList;
    }

//    员工近期加班使用软件统计
    public List<Map<String, Object>> showEpySoftWareUsageCount(String username) {
        List<Map<String, Object>> softWareUsageCount = epySoftWareUsageMapper.showEpySoftWareUsageCount(username);
        return softWareUsageCount;
    }
}
