package com.elk.elkweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface SoftWareEfficiencyMapper {
//    获取软件使用率
    List<Map< String, Object >> getSoftWareEfficiency();

}
