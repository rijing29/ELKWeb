package com.elk.elkweb.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public interface SLAService {
    //    查询软件使用频率
    List<Map< String, Object >> getAllFrequency(String startTime,String stopTime);

    //    查询软件使用时间
    List<Map< String, Object >>getSumTime(String startTime,String stopTime);

    //    查询软件选择下拉框信息
    List<Map< String, Object >>getSoftwareName(String startTime,String stopTime);

    //    查询软件模块使用次数
    List<Map< String, Object >>getModuleCount(String startTime,String stopTime,String softwareName);

    //    查询软件模块使用时间
    List<Map< String, Object >>getModuleTime(String startTime,String stopTime,String softwareName);
}
