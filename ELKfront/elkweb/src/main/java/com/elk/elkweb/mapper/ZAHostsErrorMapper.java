package com.elk.elkweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ZAHostsErrorMapper {
    //    查询
    List<Map< String, Object >> serachZAError();
    List<Map< String, Object >> searchWinlogbeats();
}
