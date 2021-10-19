package com.elk.elkweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface IPSMapper {

    //    查询IPS告警分析
    List<Map< String, Object >> getIPSAnalysis(@Param("Time")String Time);
    //    查询IPS详细信息
    List<Map< String, Object >> getIPSInfo(@Param("startTime")String startTime,
                                           @Param("stopTime")String stopTime,
                                           @Param("dstipaddr")String dstipaddr);
    //根据源ip查询所有被攻击的ip
    List<Map< String, Object >> getAttackedIP(@Param("startTime")String startTime,
                       @Param("stopTime")String stopTime,
                       @Param("srcipaddr")String srcipaddr);
}
