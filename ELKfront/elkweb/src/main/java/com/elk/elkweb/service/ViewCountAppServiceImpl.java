package com.elk.elkweb.service;

import com.elk.elkweb.mapper.ViewCountAppMapper;
import com.elk.elkweb.mapper.ViewCountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewCountAppServiceImpl implements ViewCountAppService{
    @Autowired
    private ViewCountAppMapper viewCountAppMapper;
    @Override
    public List getViewCountApp() {
        List viewCountApp = viewCountAppMapper.getViewCountApp();
        return viewCountApp;
    }
}
