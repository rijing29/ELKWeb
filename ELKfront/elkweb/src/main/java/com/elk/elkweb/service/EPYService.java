package com.elk.elkweb.service;

import com.elk.elkweb.mapper.EpySoftWareUsageMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface EPYService {
    //    查询员工刷卡信息
    List<Map< String, Object >> getEpyCardInfo(String user_lname);

    //    查询员工统计数据
    List<Map< String, Object >> getEpyCardData(String personname);

//     查询员工使用软件行为
    List<EpySoftWareUsageMapper> showEpySoftWareUsage(String username);
//    员工近期加班使用软件统计
    List<Map< String, Object >> showEpySoftWareUsageCount(String username);
}
