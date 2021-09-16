package com.elk.elkweb.mapper;

import com.elk.elkweb.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface FireWallMapper {
    //    查询所有信息
    List<FireWallInfo> getInfo(@Param("startTime")String startTime,
                               @Param("stopTime")String stopTime,
                               @Param("SRCZONENAME")String SRCZONENAME,
                               @Param("DSTZONENAME")String DSTZONENAME,
                               @Param("PROTOCOL")String PROTOCOL,
                               @Param("EVENT")String EVENT,
                               @Param("DSTIPADDR") String DSTIPADDR,
                               @Param("DSTPORT") String DSTPORT,
                               @Param("SRCIPADDR")String SRCIPADDR,
                               @Param("SRCPORT")String SRCPORT);

    //    查询所选择的信息
    List<info> getSelection(@Param("startTime") String startTime,
                            @Param("stopTime") String stopTime,
                            @Param("sourceDomain")String sourceDomain,
                            @Param("destinationDomain")String destinationDomain,
                            @Param("protocol") String protocol,
                            @Param("event") String event,
                            @Param("sourceIP") String sourceIP,
                            @Param("sourcePort")String sourcePort,
                            @Param("destinationIP") String destinationIP,
                            @Param("destinationPort")String destinationPort,
                            @Param("srcIp")String srcIp,
                            @Param("dstIp")String dstIp);

//    List<Map< String, Object >> getSelection(@Param("startTime") String startTime,
//                                 @Param("stopTime") String stopTime,
//                                 @Param("sourceDomain")String sourceDomain,
//                                 @Param("destinationDomain")String destinationDomain,
//                                 @Param("protocol") String protocol,
//                                 @Param("event") String event);

    //    查询下拉框
    List<Map< String, Object >> getSrczonename(@Param("startTime") String startTime,
                                               @Param("stopTime") String stopTime

    );
    List<Map< String, Object >> getDstzonename(@Param("startTime") String startTime,
                                               @Param("stopTime") String stopTime

    );
    List<Map< String, Object >> getProtocol(@Param("startTime") String startTime,
                                            @Param("stopTime") String stopTime

    );
    List<Map< String, Object >> getEvent(@Param("startTime") String startTime,
                                         @Param("stopTime") String stopTime

    );
}