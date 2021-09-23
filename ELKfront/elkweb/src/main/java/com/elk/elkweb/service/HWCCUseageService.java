package com.elk.elkweb.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface HWCCUseageService {
    //    获取存储使用情况
    List<Map<String,Object>> getHWCCUseage();

    //    获取某个用户存储使用记录
    List<Map<String,Object>> getMemUseInfo(String CCNAME);
}

