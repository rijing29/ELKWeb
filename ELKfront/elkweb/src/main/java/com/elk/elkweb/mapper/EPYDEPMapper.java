package com.elk.elkweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface EPYDEPMapper {
    //    查询单位刷卡统计
    List getCardDEP();

}
