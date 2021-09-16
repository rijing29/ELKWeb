package com.elk.elkweb.service;

import java.util.List;
import java.util.Map;

import com.elk.elkweb.entity.FireWallInfo;
import com.elk.elkweb.entity.dataInfo;
import com.elk.elkweb.entity.info;
import com.elk.elkweb.entity.selection;
import org.springframework.stereotype.Service;
@Service
public interface FireWallService {

    List<FireWallInfo> getInfo(String startTime,String stopTime,String SRCZONENAME,
                               String DSTZONENAME,String PROTOCOL,String EVENT,String DSTIPADDR,
                               String DSTPORT,String SRCIPADDR, String SRCPORT);

    List<info> getSelection(String startTime, String stopTime, String sourceDomain,
                            String destinationDomain, String protocol, String event,
                            String sourceIP, String sourcePort, String destinationIP,
                            String destinationPort,String srcIp,String dstIp);

//    List<Map< String, Object >> getSelection(String startTime, String stopTime,String sourceDomain,
//                                 String destinationDomain, String protocol, String event);

    List<Map< String, Object >> getSrczonename(String startTime, String stopTime);
    List<Map< String, Object >> getDstzonename(String startTime, String stopTime);
    List<Map< String, Object >> getProtocol(String startTime, String stopTime);
    List<Map< String, Object >> getEvent(String startTime, String stopTime);
}