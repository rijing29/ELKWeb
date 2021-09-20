package com.elk.elkweb.service;

import com.elk.elkweb.mapper.EPYDEPMapper;
import com.elk.elkweb.mapper.EPYMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EPYDEPServiceImpl implements EPYDEPService{
    @Autowired
    private EPYDEPMapper epydepMapper;
    //单位刷卡信息
    public List getCardDEP() {
        List cardDEP = epydepMapper.getCardDEP();
        return cardDEP;
    }


}
