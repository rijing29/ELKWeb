package com.elk.elkweb.mapper;

import com.elk.elkweb.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface SLAMapper {
    //    查询软件使用频率
    List<Map< String, Object >> getAllFrequency(@Param("startTime")String startTime,
                                                @Param("stopTime")String stopTime);
    //    查询软件使用时间
    List<Map< String, Object >>getSumTime(@Param("startTime")String startTime,
                                          @Param("stopTime")String stopTime);
    //    查询软件选择下拉框信息
    List<Map< String, Object >>getSoftwareName(@Param("startTime")String startTime,
                                               @Param("stopTime")String stopTime);
    //    查询软件模块使用次数
    List<Map< String, Object >>getModuleCount(@Param("startTime")String startTime,
                                              @Param("stopTime")String stopTime,
                                              @Param("softwareName")String softwareName);
    //    查询软件模块使用时间
    List<Map< String, Object >>getModuleTime(@Param("startTime")String startTime,
                                              @Param("stopTime")String stopTime,
                                              @Param("softwareName")String softwareName);
}
