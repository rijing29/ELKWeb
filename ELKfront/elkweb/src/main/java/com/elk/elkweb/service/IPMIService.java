package com.elk.elkweb.service;

import com.elk.elkweb.entity.IPMILogInfo;
import com.elk.elkweb.entity.IPMILogWarn;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface IPMIService {
    //    查询IPMI告警信息
    List<IPMILogWarn> getIPMIAlarm();

    //    查询IPMI告警详细信息
    List<IPMILogInfo> getIPMIInfo(String ip);

    //    查询IPMI的ip
    List<Map< String, Object >>getIPSelection();

    //    根据ip查询IPMI告警详细信息
    List<IPMILogInfo> getIPMIInfo1(String ip);

    //    根据ip查询IPMI告警信息
    List<IPMILogWarn> getIPMIAlarm1(String ip);


    //    IPMI信息默认查询
    List<IPMILogInfo> getDefaultIPMI();
}
