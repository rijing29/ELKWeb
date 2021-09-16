package com.elk.elkweb.service;

import com.elk.elkweb.mapper.EpySoftWareUsageMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface showEpySoftWareUsage {
    List<EpySoftWareUsageMapper> showEpySoftWareUsage(String username);
}
