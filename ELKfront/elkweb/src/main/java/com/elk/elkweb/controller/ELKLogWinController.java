package com.elk.elkweb.controller;

import com.elk.elkweb.service.LogWinService;
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
public class ELKLogWinController {
    @Autowired
    private LogWinService logWinService;

    /**
     *
     * Windows服务器告警
     *
     * */
    @RequestMapping(value = "/getWinWaring",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public List<Map< String, Object >> getWinWaring() throws ParseException {
        List<Map< String, Object >> winWaring = logWinService.getWinWaring();
        return winWaring;
    }

    /**
     *
     * Windows服务信息
     *
     * */
    @RequestMapping(value = "/getWinServiceInfo",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public List<Map< String, Object >> getWinServiceInfo() throws ParseException {
        List<Map< String, Object >> winServiceInfo = logWinService.getWinServiceInfo();
        return winServiceInfo;
    }

    /**
     *
     * Windows服务器任务增长信息
     *
     * */
    @RequestMapping(value = "/getWinServiceJobInc",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public List<Map< String, Object >> getWinServiceJobInc() throws ParseException {
        List<Map< String, Object >> winServiceJobInc = logWinService.getWinServiceJobInc();
        return winServiceJobInc;
    }

    /**
     *
     * Windows服务器负载统计饼状图
     *
     * */
    @RequestMapping(value = "/getWinLoad",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public List<Map< String, Object >> getWinLoad() throws ParseException {
        List<Map< String, Object >> winLoad = logWinService.getWinLoad();
        return winLoad;
    }
    
    /**
    *@Author:whj
    *@date:2022-02-1615:38
    *@Method:根据ip查询所有数据
    */
    @RequestMapping(value = "/getWinLogInfo",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public List getWinLogInfo(@Param("ip")String ip) throws ParseException {
        List WinLoglist = logWinService.selectAllInfo(ip);
        return WinLoglist;
    }

}
