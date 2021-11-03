package com.elk.elkweb.controller;

import com.elk.elkweb.service.LogQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/elk")
public class ELKLogQueryController {
    /**
     * DaPingKuoLuo
     * 2021/11/03
     * 查询zabbix审计日志
     * */
    @Autowired
    private LogQueryService logQueryService;
    @RequestMapping(value = "/getZabbixShenji",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public List<Map<String,Object>> getZabbixShenji()throws Exception{
        List<Map<String, Object>> zabbixShenji = logQueryService.getZabbixShenji();
        return zabbixShenji;
    }

    /**
     * DaPingKuoLuo
     * 2021/11/03
     * 查询zabbix审计日志
     * */
    @RequestMapping(value = "/getCdnDataLog",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public List<Map<String,Object>> getCdnDataLog()throws Exception{
        List<Map<String, Object>> cdnDataLog = logQueryService.getCdnDataLog();
        return cdnDataLog;
    }

    /**
     * DaPingKuoLuo
     * 2021/11/03
     * 查询zabbix审计日志
     * */
    @RequestMapping(value = "/getSysSrmLog",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public List<Map<String,Object>> getSysSrmLog()throws Exception{
        List<Map<String, Object>> sysSrmLog = logQueryService.getSysSrmLog();
        return sysSrmLog;
    }

}
