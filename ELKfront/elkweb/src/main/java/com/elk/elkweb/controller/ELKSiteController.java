package com.elk.elkweb.controller;

import com.elk.elkweb.service.SitDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/elk")
public class ELKSiteController {
    @Autowired
    private SitDeviceService sitDeviceService;
    /**
     *
     * DaPingKuoLuo
     * Time:2021/09/20  20:09
     * 获取UPS监控信息
     *
     * */
    @ResponseBody
    @RequestMapping("/getUPSInfo")
    public List<Map<String,Object>> getUPSInfo(){
        List<Map<String, Object>> upsInfo = sitDeviceService.getUPSInfo();
        upsInfo.forEach(System.out::println);
        return upsInfo;
    }

    /**
     *
     * DaPingKuoLuo
     * Time:2021/09/20  20:09
     * 获取温湿度监控信息
     *
     * */
    @ResponseBody
    @RequestMapping("/getEnvironmentInfo")
    public List<Map<String,Object>> getEnvironmentInfo(){
        List<Map<String, Object>> environmentInfo = sitDeviceService.getEnvironmentInfo();
        environmentInfo.forEach(System.out::println);
        return environmentInfo;
    }
}
