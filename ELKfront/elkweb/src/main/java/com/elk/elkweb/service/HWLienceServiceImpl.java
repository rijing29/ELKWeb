package com.elk.elkweb.service;

import com.elk.elkweb.mapper.HWLicenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HWLienceServiceImpl implements HWLienceService{
    @Autowired
    private HWLicenceMapper hwLicenceMapper;
    @Override
    public List showHWLience() {
        List hwlist = hwLicenceMapper.showHWLience();
        return hwlist;
    }
}
