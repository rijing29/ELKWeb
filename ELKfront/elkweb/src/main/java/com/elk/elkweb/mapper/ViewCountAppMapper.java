package com.elk.elkweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ViewCountAppMapper {
//    获取软件系统日志
    List getViewCountApp();

}
