package com.elk.elkweb.mapper;

import com.elk.elkweb.entity.ServerPublic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ServerInfoMapper {
    List<ServerPublic> showServerPublic();

    //    查询最近日期目录及存储使用信息
    List<Map< String, Object >> getStorage();

    //    查询选中路径存储历史数据
    List<Map< String, Object >> getStorageInfo(@Param("DIRNAME")String DIRNAME);
}
