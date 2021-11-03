package com.elk.elkweb.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ViewCountService {
    List getViewCount();
}
