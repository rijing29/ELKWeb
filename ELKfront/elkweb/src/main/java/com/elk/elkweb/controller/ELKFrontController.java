package com.elk.elkweb.controller;

import com.elk.elkweb.service.FrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/elk")
public class ELKFrontController {
    @Autowired
    private FrontService frontService;
    /**
     * DaPingKuoLuo
     * Time:2021/09/22  19:06
     * 获取全院刷卡信息
     * */
    @ResponseBody
    @RequestMapping(value = "/getCardDPMInfo",produces = "application/json;charset=utf-8")
    public List<Map<String,Object>> getCardDPMInfo()throws Exception{
        List<Map<String, Object>> cardDPMInfo = frontService.getCardDPMInfo();
        cardDPMInfo.forEach(System.out::println);
        return cardDPMInfo;
    }
    /**
     * DaPingKuoLuo
     * Time:2021/09/22  19:06
     * 获取全院刷卡平均信息
     * */
    @ResponseBody
    @RequestMapping(value = "/getCardDPMAvg",produces = "application/json;charset=utf-8")
    public List<Map<String,Object>> getCardDPMAvg()throws Exception{
        List<Map<String, Object>> cardDPMAvg = frontService.getCardDPMAvg();
        cardDPMAvg.forEach(System.out::println);
        return cardDPMAvg;
    }
    /**
     * DaPingKuoLuo
     * Time:2021/09/22  19:06
     * 获取服务器平均负载
     * */
    @ResponseBody
    @RequestMapping(value = "/getServerStateAvg",produces = "application/json;charset=utf-8")
    public List<Map<String,Object>> getServerStateAvg()throws Exception{
        List<Map<String, Object>> serverStateAvg = frontService.getServerStateAvg();
        serverStateAvg.forEach(System.out::println);
        return serverStateAvg;
    }
}
