package com.elk.elkweb.mapper;

import com.elk.elkweb.entity.IMCAlarm;
import com.elk.elkweb.entity.IPMILogInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface IMCMapper {
    //    查询IMC的ip
    List<Map< String, Object >> getIMCIP();

    //    查询IMC信息
    List<IMCAlarm> getIMCInfo(@Param("ip")String ip, @Param("filtration")String filtration);


    //    默认查询
    List<IMCAlarm> getDefaultIMC();
}
