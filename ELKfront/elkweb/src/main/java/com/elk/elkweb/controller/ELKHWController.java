package com.elk.elkweb.controller;

import com.elk.elkweb.entity.CpuGpuResults;
import com.elk.elkweb.service.EquipStateService;
import com.elk.elkweb.service.HWLienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/elk")
public class ELKHWController {
    @Autowired
    private EquipStateService equipStateService;
    @Qualifier("HWLienceServiceImpl")
    @Autowired
    private HWLienceService hwLienceService;
    //    获取cpu
    @RequestMapping(value = "/getCPU",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public CpuGpuResults getCPU(){
        List< Map< String, Object > > cpulist = equipStateService.getCPU();
        String cpu[]=new String[cpulist.size()];
        String time[]=new String[cpulist.size()];
        int i=0;
        int k=0;
        for( Map<String, Object> mapList : cpulist ) {
            for( String key : mapList.keySet() ) {
                if(key.equals("TIME")) {
                    time[i] = String.valueOf(mapList.get(key));
                    i++;
                }else {
                    cpu[k] = String.valueOf(mapList.get(key));
                    k++;
                }
            }
        }
        CpuGpuResults results = new CpuGpuResults();
        results.setTime(time);
        results.setName(cpu);
        return results;
    }
    //    获取gpu
    @RequestMapping(value = "/getGPU",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public CpuGpuResults getGPU(){
        List< Map< String, Object > > gpulist = equipStateService.getGPU();
        String gpu[]=new String[gpulist.size()];
        String time[]=new String[gpulist.size()];
        int i=0;
        int k=0;
        for( Map<String, Object> mapList : gpulist ) {
            for( String key : mapList.keySet() ) {
                if(key.equals("TIME")) {
                    time[i] = String.valueOf(mapList.get(key));
                    i++;
                }else {
                    gpu[k] = String.valueOf(mapList.get(key));
                    k++;
                }
            }
        }
        CpuGpuResults results = new CpuGpuResults();
        results.setTime(time);
        results.setName(gpu);
        return results;
    }
    //    获取软件使用率
    @RequestMapping(value = "/getSoftWareEfficiency",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public String[] getSoftWareEfficiency(){
        List<Map<String, Object>> softWareEfficiency = equipStateService.getSoftWareEfficiency();
//        放三个值
        String[] arr = new String[3];
        int i=0;
        for( Map<String, Object> mapList : softWareEfficiency ) {
            for( String key : mapList.keySet() ) {
                System.out.println( key + "-->" + mapList.get(key) );
                arr[i]= String.valueOf(mapList.get(key));
                i++;
            }
        }
        return arr;
    }
    /**
     *@Author:whj
     *@date:2021-09-2110:40
     *@Method:获取存储系统使用
     */
    @RequestMapping(value = "/getCCUsage",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public List getCCUsage(){
        List<Map<String, Object>> ccUsage = equipStateService.getCCUsage();
        return ccUsage;
    }
    /**
    *@Author:whj
    *@date:2021-09-2313:23
    *@Method:软件许可日志
    */
    @RequestMapping(value = "/getLience",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public List getLience(){
        List showHWLience = hwLienceService.showHWLience();
        return showHWLience;
    }
}
