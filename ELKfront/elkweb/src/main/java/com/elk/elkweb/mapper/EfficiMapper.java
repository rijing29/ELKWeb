package com.elk.elkweb.mapper;

import com.elk.elkweb.entity.NodeSoftMap;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface EfficiMapper {
    // 软件名效率
    int softNameEfficiency(@Param("softName")String softName,
                            @Param("startTime")String startTime,
                            @Param("stopTime")String stopTime);
    // 软件名各节点工作量之和
    int sumWorkLoad(NodeSoftMap nodeSoftMap);
//    根据nodeType和nodeId
    int nodeTypeEfficiency(@Param("nodeType")String nodeType,
                           @Param("nodeId")String nodeId,
                           @Param("startTime")String startTime,
                           @Param("stopTime")String stopTime);
//    nodeType各节点工作之和
    int sumNodeTypeWorkLoad(NodeSoftMap nodeSoftMap);
//    取出所有的softName
    List getSoftName();
//    取出softName的所有nodeType和nodeId
    List<Map< String, Object >> getNodeInfo(String softName);
}
