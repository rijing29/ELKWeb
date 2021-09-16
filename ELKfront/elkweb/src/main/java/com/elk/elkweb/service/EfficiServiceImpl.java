package com.elk.elkweb.service;

import com.elk.elkweb.entity.NodeSoftMap;
import com.elk.elkweb.mapper.EfficiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class EfficiServiceImpl implements EfficiService {
    @Autowired
    private EfficiMapper efficiMapper;
    //    软件名效率
    public int softNameEfficiency(String softName, String startTime, String stopTime) {
        int res = efficiMapper.softNameEfficiency(softName, startTime, stopTime);
        return res;
    }
    //各节点工作量之和
    public int sumWorkLoad(NodeSoftMap nodeSoftMap) {
        int res = efficiMapper.sumWorkLoad(nodeSoftMap);
        return res;
    }
//    nodeType效率
    public int nodeTypeEfficiency(String nodeType, String nodeId, String startTime, String stopTime) {
        int res = efficiMapper.nodeTypeEfficiency(nodeType, nodeId, startTime, stopTime);
        return res;
    }
    //    nodeType各节点工作量之和
    public int sumNodeTypeWorkLoad(NodeSoftMap nodeSoftMap) {
        int res = efficiMapper.sumNodeTypeWorkLoad(nodeSoftMap);
        return res;
    }

//    取出所有的softName
    public List getSoftName() {
        List softName = efficiMapper.getSoftName();
        return softName;
    }

//    根据softName取出nodeType和nodeId
    public List<Map< String, Object >> getNodeInfo(String softName) {
        List nodeInfo = efficiMapper.getNodeInfo(softName);
        return nodeInfo;
    }
}
