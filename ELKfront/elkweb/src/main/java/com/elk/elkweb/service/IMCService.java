package com.elk.elkweb.service;

import com.elk.elkweb.entity.IMCAlarm;
import com.elk.elkweb.entity.IPMILogInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface IMCService {

    //    查询IMC的ip
    List<Map< String, Object >> getIMCIP();

    //    查询IMC信息
    List<IMCAlarm> getIMCInfo(String ip, String filtration);

    //    默认查询
    List<IMCAlarm> getDefaultIMC();
}
