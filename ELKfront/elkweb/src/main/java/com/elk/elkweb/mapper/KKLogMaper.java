package com.elk.elkweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface KKLogMaper {
//    取出所有KK_Log_V日志
    List getKKLog();
}
