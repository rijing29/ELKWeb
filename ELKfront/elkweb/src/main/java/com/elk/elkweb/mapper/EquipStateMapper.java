package com.elk.elkweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface EquipStateMapper {
    //    获取cpu
    List< Map< String, Object > > getCPU();
    //    获取gpu
    List< Map< String, Object > > getGPU();

}
