package com.elk.elkweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface EpySoftWareUsageMapper {
//    员工近期使用软件信息
    List<EpySoftWareUsageMapper> showEpySoftWareUsage(@Param("username")String username);
//员工近期加班使用软件统计
    List<Map< String, Object >> showEpySoftWareUsageCount(@Param("username")String username);


}
