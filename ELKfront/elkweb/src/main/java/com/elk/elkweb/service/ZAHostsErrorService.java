package com.elk.elkweb.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ZAHostsErrorService {
    List<Map< String, Object >> serachZAError();
    List<Map< String, Object >> searchWinlogbeats();
}