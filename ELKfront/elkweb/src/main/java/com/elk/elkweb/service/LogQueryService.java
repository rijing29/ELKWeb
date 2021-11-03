package com.elk.elkweb.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface LogQueryService {
    //  获取zabbix审计日志
    List<Map< String, Object >> getZabbixShenji();

    //  获取套管损坏预警系统日志
    List<Map< String, Object >> getCdnDataLog();

    //  获取A2生产管理软件日志
    List<Map< String, Object >> getSysSrmLog();
}
