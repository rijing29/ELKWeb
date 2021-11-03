package com.elk.elkweb.service;

import com.elk.elkweb.mapper.KKLogMaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KKLogServiceImpl implements KKLogService{
    @Autowired
    private KKLogMaper kkLogMaper;

//    获取kklog日志
    public List getKKLog() {
        List kkLog = kkLogMaper.getKKLog();
        return kkLog;
    }
}
