package com.elk.elkweb.controller;

import com.elk.elkweb.service.HWCCUseageService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/elk")
public class ELKHWCCUAGColtroller {
    @Qualifier("HWCCUseageServiceImpl")
    @Autowired
    private HWCCUseageService hwccUseageService;
    /**
     * DaPingKuoLuo
     * Time:2021/09/23  18:40
     * 获取用户存储使用信息
     * */
    @ResponseBody
    @RequestMapping(value = "/getHWCCUseage",produces = "application/json;charset=utf-8")
    public List<Map<String,Object>> getHWCCUseage()throws Exception{
        List<Map<String, Object>> hwccUseage = hwccUseageService.getHWCCUseage();
        hwccUseage.forEach(System.out::println);
        return hwccUseage;
    }

    /**
     * DaPingKuoLuo
     * Time:2021/09/23  18:40
     * 获取某个用户存储使用记录
     * */
    @ResponseBody
    @RequestMapping(value = "/getMemUseInfo",produces = "application/json;charset=utf-8")
    public List<Map<String,Object>> getMemUseInfo(@Param("CCNAME")String CCNAME)throws Exception{
        List<Map<String, Object>> memUseInfo = hwccUseageService.getMemUseInfo(CCNAME);
        memUseInfo.forEach(System.out::println);
        return memUseInfo;
    }
}
