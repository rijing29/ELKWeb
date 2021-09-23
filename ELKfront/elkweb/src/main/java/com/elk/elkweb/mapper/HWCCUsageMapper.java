package com.elk.elkweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface HWCCUsageMapper {
    //    获取存储使用情况
    List<Map<String,Object>> getHWCCUseage();

    //    获取某个用户存储使用记录
    List<Map<String,Object>> getMemUseInfo(@Param("CCNAME")String CCNAME);
}
