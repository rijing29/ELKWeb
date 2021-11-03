package com.elk.elkweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface ViewCountMapper {
//    取出ViewCount信息
    List getViewCount();
}
