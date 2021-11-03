package com.elk.elkweb.controller;

import com.elk.elkweb.service.KKLogService;
import com.elk.elkweb.service.KKLogServiceImpl;
import com.elk.elkweb.service.ViewCountAppServiceImpl;
import com.elk.elkweb.service.ViewCountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/elk")
public class ServerLogController {
    @Autowired
    private KKLogServiceImpl kkLogService;
    @Autowired
    private ViewCountServiceImpl viewCountService;
    @Autowired
    private ViewCountAppServiceImpl viewCountAppService;
//    查询成果归档日志
    @RequestMapping(value = "/getKKLog",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public List getKKLog(){
        List kkLog = kkLogService.getKKLog();
        return kkLog;
    }
//    查询系统日志
    @RequestMapping(value = "/getViewCount",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public List getViewCount(){
        List viewCount = viewCountService.getViewCount();
        return viewCount;
    }
//    查询系统日志2
    @RequestMapping(value = "/getViewCountApp",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public List getViewCountApp(){
        List viewCountApp = viewCountAppService.getViewCountApp();
        return viewCountApp;
    }
}
