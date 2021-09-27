package com.elk.elkweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface SoftUserMapper {
    //    查询软件
    List<Map< String, Object >> getSoftName();

    //    根据软件名查询一周内用户数据
    List<Map< String, Object >> getSoft_userInfo(@Param("softName")String softName);
}
