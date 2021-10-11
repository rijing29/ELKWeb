package com.elk.elkweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface SoftWareEfficiencyMapper {
    //    获取软件使用率
    String getMaxTime();
    List<Map<String,Object>> getSoftName();
    Double getSumJob(@Param("soft_name")String soft_name,@Param("startTime")String startTime,@Param("stopTime")String stoptime);
    Double getSumWork(@Param("soft_name")String soft_name);

}
