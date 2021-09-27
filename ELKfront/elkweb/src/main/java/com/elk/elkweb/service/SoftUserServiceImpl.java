package com.elk.elkweb.service;

import com.elk.elkweb.mapper.SoftUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SoftUserServiceImpl implements SoftUserService {
    @Autowired
    private SoftUserMapper softUserMapper;
    @Override
    public List<Map<String, Object>> getSoftName() {
        List<Map<String, Object>> softName = softUserMapper.getSoftName();
        return softName;
    }

    @Override
    public List<Map<String, Object>> getSoft_userInfo(String softName) {
        List<Map<String, Object>> soft_userInfo = softUserMapper.getSoft_userInfo(softName);
        return soft_userInfo;
    }
}
