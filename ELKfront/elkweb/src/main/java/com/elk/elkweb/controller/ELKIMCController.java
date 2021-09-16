package com.elk.elkweb.controller;

import com.elk.elkweb.entity.IMCAlarm;
import com.elk.elkweb.service.IMCService;
import com.elk.elkweb.service.IPMIService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/elk")
public class ELKIMCController {
    @Autowired
    private IMCService imcService;

    /**
     *
     * 查询IPMI的IP
     *
     * */
    @RequestMapping(value = "/getIMCIP",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public List<Map<String, Object>> getIMCIP() throws ParseException {
        List<Map<String, Object>> imcip = imcService.getIMCIP();
        System.out.println(imcip);
        return imcip;
    }

    /**
     *
     * 查询IPMI的信息
     *
     * */
    @RequestMapping(value = "/getIMCInfo",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public List<IMCAlarm> getIMCInfo(@Param("ip")String ip,@Param("filtration")String filtration) throws ParseException {
        List<IMCAlarm> imcInfo = imcService.getIMCInfo(ip, filtration);
        System.out.println(imcInfo);
        return imcInfo;
    }
}
