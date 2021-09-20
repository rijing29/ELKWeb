package com.elk.elkweb.service;

import com.elk.elkweb.mapper.EPYDEPDQMDSMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EPYDEPDQMDSImpl implements EPYDEPDQMDS{
    @Autowired
    private EPYDEPDQMDSMapper epydepdqmdsMapper;
    @Override
    public List getDEPDQMDS() {
        List depdqmds = epydepdqmdsMapper.getDEPDQMDS();
        return depdqmds;
    }
}
