package com.elk.elkweb.service;

import com.elk.elkweb.mapper.FrontMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class FrontServiceImpl implements FrontService{
    @Autowired
    private FrontMapper frontMapper;
    @Override
    public List<Map<String, Object>> getCardDPMInfo() {
        List<Map<String, Object>> cardDPMInfo = frontMapper.getCardDPMInfo();
        return cardDPMInfo;
    }

    @Override
    public List<Map<String, Object>> getCardDPMAvg() {
        List<Map<String, Object>> cardDPMAvg = frontMapper.getCardDPMAvg();
        return cardDPMAvg;
    }

    @Override
    public List<Map<String, Object>> getServerStateAvg() {
        List<Map<String, Object>> serverStateAvg = frontMapper.getServerStateAvg();
        return serverStateAvg;
    }
}
