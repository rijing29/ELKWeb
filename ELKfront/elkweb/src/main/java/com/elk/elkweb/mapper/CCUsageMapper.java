package com.elk.elkweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CCUsageMapper {
    //    获取存储系统使用
    List<Map< String, Object >> getCCUsage();
}
