package com.elk.elkweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface EPYMapper {
    //    查询员工刷卡信息
    List<Map< String, Object >> getEpyCardInfo(@Param("user_lname")String user_lname);

    //    查询员工统计数据
    List<Map< String, Object >> getEpyCardData(@Param("personname")String user_lname);
}
