package com.elk.elkweb.service;

import com.elk.elkweb.mapper.CCUsageMapper;
import com.elk.elkweb.mapper.EquipStateMapper;
import com.elk.elkweb.mapper.SoftWareEfficiencyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
@Service
public class EquipStateServiceImpl implements EquipStateService{
    @Autowired
    private EquipStateMapper equipStateMapper;
    @Autowired
    private SoftWareEfficiencyMapper softWareEfficiencyMapper;
    @Autowired
    private CCUsageMapper ccUsageMapper;
    //    获取cpu
    @Override
    public List< Map< String, Object > > getCPU() {
        List< Map< String, Object > > equipStateMapperCPU = equipStateMapper.getCPU();
        return equipStateMapperCPU;
    }
    //     获取gpu
    @Override
    public List<Map<String, Object>> getGPU() {
        List<Map<String, Object>> equipStateMapperGPU = equipStateMapper.getGPU();
        return equipStateMapperGPU;
    }

    @Override
    public String getMaxTime() {
        String maxTime = softWareEfficiencyMapper.getMaxTime();
        return maxTime;
    }

    @Override
    public List<Map<String,Object>> getSoftName() {
        List<Map<String,Object>> softName = softWareEfficiencyMapper.getSoftName();
        return softName;
    }

    @Override
    public Double getSumJob(String soft_name, String startTime, String stoptime) {
        Double sumJob = softWareEfficiencyMapper.getSumJob(soft_name, startTime, stoptime);
        return sumJob;
    }

    @Override
    public Double getSumWork(String soft_name) {
        Double sumWork = softWareEfficiencyMapper.getSumWork(soft_name);
        return sumWork;
    }

    //      软件使用率

    //      获取存储系统使用
    public List<Map<String, Object>> getCCUsage() {
        List<Map<String, Object>> ccUsage = ccUsageMapper.getCCUsage();
        return ccUsage;
    }
}
