package com.elk.elkweb.service;

import com.elk.elkweb.entity.ServerPublic;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ServerInfoService {
    List<ServerPublic> showServerPublic();
}
