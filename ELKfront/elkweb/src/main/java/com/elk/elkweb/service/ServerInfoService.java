package com.elk.elkweb.service;

import com.elk.elkweb.entity.ServerPublic;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ServerInfoService {
    List<ServerPublic> showServerPublic();

    //    查询最近日期目录及存储使用信息
    List<Map< String, Object >> getStorage();

    //    查询选中路径存储历史数据
    List<Map< String, Object >> getStorageInfo(String DIRNAME);
}
