package com.elk.elkweb.controller;

import com.elk.elkweb.service.FrontService;
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

    /**
     * DaPingKuoLuo
     * Time:2021/09/26  16:47
     * 获取IPMI告警数
     * */
    @ResponseBody
    @RequestMapping(value = "/getIPMIAlertNum",produces = "application/json;charset=utf-8")
    public int getIPMIAlertNum(@Param("Time") String Time)throws Exception{
        String time =SplicingTime(Time);
        System.out.printf(time);
        int ipmiAlertNum = frontService.getIPMIAlertNum(time);
        System.out.printf(ipmiAlertNum+"IPMI告警数");
        return ipmiAlertNum;
    }
    /**
     * DaPingKuoLuo
     * Time:2021/09/26  16:47
     * 获取平均任务数量
     * */
    @ResponseBody
    @RequestMapping(value = "/getTaskAvg",produces = "application/json;charset=utf-8")
    public float getTaskAvg()throws Exception{
        float taskAvg = frontService.getTaskAvg();
        System.out.printf(taskAvg+"均任务数量");
        return taskAvg;
    }
    /**
     * DaPingKuoLuo
     * Time:2021/09/26  16:47
     * 获取agent故障数量
     * */
    @ResponseBody
    @RequestMapping(value = "/getAgentNum",produces = "application/json;charset=utf-8")
    public List<Map<String, Object>> getAgentNum()throws Exception{
        List<Map<String, Object>> agentNum = frontService.getAgentNum();
        agentNum.forEach(System.out::println);
        return agentNum;
    }

    /**
     * DaPingKuoLuo
     * Time:2021/09/26  19:16
     * 轮播图信息汇总
     * */
    @ResponseBody
    @RequestMapping(value = "/getGetherInfo",produces = "application/json;charset=utf-8")
    public List<Map<String, Object>> getGetherInfo()throws Exception{
        List<Map<String, Object>> getherInfo = frontService.getGetherInfo();
        getherInfo.forEach(System.out::println);
        return getherInfo;
    }

    /**
     *
     * 添加时分秒
     *
     * */
    public String SplicingTime(String s){
        StringBuilder builder = new StringBuilder(s);
        builder.replace(10,s.length()," 00:00:00");
        String time = builder.toString();
        return time;
    }
}
