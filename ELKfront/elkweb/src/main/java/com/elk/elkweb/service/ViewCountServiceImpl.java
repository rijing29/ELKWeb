package com.elk.elkweb.service;

import com.elk.elkweb.mapper.ViewCountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ViewCountServiceImpl implements ViewCountService{
    @Autowired
    private ViewCountMapper viewCountMapper;
    @Override
    public List getViewCount() {
        List viewCount = viewCountMapper.getViewCount();
        return viewCount;
    }
}
