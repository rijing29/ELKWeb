package com.elk.elkweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface FrontMapper {

//    全院刷卡信息查询
    List<Map<String,Object>> getCardDPMInfo();

//    全院平均刷卡信息查询
    List<Map<String,Object>> getCardDPMAvg();

//    服务器平均负载查询
    List<Map<String,Object>> getServerStateAvg();

}
