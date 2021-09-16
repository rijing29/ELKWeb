package com.elk.elkweb.mapper;

import com.elk.elkweb.entity.ServerPublic;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface ServerInfoMapper {
    List<ServerPublic> showServerPublic();
}
