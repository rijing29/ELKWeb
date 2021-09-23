package com.elk.elkweb.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public interface EquipStateService {
    //    获取cpu
    List< Map< String, Object > > getCPU();
    //    获取gpu
    List< Map< String, Object > > getGPU();
    //    软件使用率
    List< Map< String, Object > > getSoftWareEfficiency();
    //    获取存储系统使用
    List< Map< String, Object > > getCCUsage();
}
