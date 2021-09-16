package com.elk.elkweb.service;
import java.util.List;
import java.util.Map;

import com.elk.elkweb.entity.FireWallInfo;
import com.elk.elkweb.entity.dataInfo;
import com.elk.elkweb.entity.info;
import com.elk.elkweb.entity.selection;
import com.elk.elkweb.mapper.FireWallMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FireWallServiceImpl implements FireWallService{
    @Autowired
    private FireWallMapper fireWallMapper;
    @Override
    public List<FireWallInfo> getInfo(String startTime,String stopTime,String SRCZONENAME,String DSTZONENAME,
                                      String PROTOCOL,String EVENT,String DSTIPADDR, String DSTPORT,
                                      String SRCIPADDR, String SRCPORT) {
        List<FireWallInfo> fireWallInfo = fireWallMapper.getInfo(startTime,stopTime,SRCZONENAME,DSTZONENAME,PROTOCOL,
                EVENT,DSTIPADDR,DSTPORT,SRCIPADDR,SRCPORT);
        return fireWallInfo;
    }

    @Override
    public List<info> getSelection(String startTime, String stopTime, String sourceDomain,
                                   String destinationDomain, String protocol, String event,
                                   String sourceIP, String sourcePort, String destinationIP,
                                   String destinationPort,String srcIp,String dstIp) {
        List<info> selection = fireWallMapper.getSelection(startTime, stopTime,sourceDomain,
                destinationDomain, protocol, event, sourceIP, sourcePort, destinationIP, destinationPort,srcIp,dstIp);
        return selection;
    }
//    @Override
//    public List<Map< String, Object >> getSelection(String startTime, String stopTime,String sourceDomain,
//                                        String destinationDomain, String protocol, String event) {
//        List<Map< String, Object >> srcipaddr = fireWallMapper.getSelection(startTime, stopTime, sourceDomain,
//                 destinationDomain, protocol, event);
//        return srcipaddr;
//    }

    @Override
    public List<Map< String, Object >> getSrczonename(String startTime, String stopTime) {
        List<Map< String, Object >> srczonename = fireWallMapper.getSrczonename(startTime, stopTime);
        return srczonename;
    }

    @Override
    public List<Map< String, Object >> getDstzonename(String startTime, String stopTime) {
        List<Map< String, Object >> dstzonename = fireWallMapper.getDstzonename(startTime, stopTime);
        return dstzonename;
    }

    @Override
    public List<Map< String, Object >> getProtocol(String startTime, String stopTime) {
        List<Map< String, Object >> protocol = fireWallMapper.getProtocol(startTime, stopTime);
        return protocol;
    }

    @Override
    public List<Map< String, Object >> getEvent(String startTime, String stopTime) {
        List<Map< String, Object >> event = fireWallMapper.getEvent(startTime, stopTime);
        return event;
    }
}
