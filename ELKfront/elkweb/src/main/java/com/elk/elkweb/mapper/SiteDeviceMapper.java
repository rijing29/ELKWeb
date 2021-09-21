package com.elk.elkweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface SiteDeviceMapper {
//    查询UPS监控信息
    List<Map<String,Object>> getUPSInfo();
//    查询温湿度监控信息
    List<Map<String,Object>> getEnvironmentInfo();

}
