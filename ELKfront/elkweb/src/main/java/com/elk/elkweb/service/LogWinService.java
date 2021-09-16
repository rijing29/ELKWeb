package com.elk.elkweb.service;

import com.elk.elkweb.entity.LogWinLogin;
import com.elk.elkweb.entity.LogWinWarning;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface LogWinService {
    //    查询Windows服务器日志告警信息
    List<Map< String, Object >> getWinWaring();

    //    Windows服务信息
    List<Map< String, Object >> getWinServiceInfo();

    //    Windows服务器任务增长信息
    List<Map< String, Object >> getWinServiceJobInc();

    //    Windows服务器负载统计饼状图
    List<Map< String, Object >> getWinLoad();

}
