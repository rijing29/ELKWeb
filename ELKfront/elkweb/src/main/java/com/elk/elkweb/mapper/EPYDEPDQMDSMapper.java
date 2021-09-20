package com.elk.elkweb.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface EPYDEPDQMDSMapper {
    //    查询单位DQMDS日志统计
    List getDEPDQMDS();
}
