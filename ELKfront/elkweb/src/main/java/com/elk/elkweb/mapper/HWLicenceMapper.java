package com.elk.elkweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HWLicenceMapper {
//    华为云软件许可日志
    List showHWLience();
}
