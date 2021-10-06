package com.elk.elkweb.controller;

import com.elk.elkweb.service.ZAHostsErrorService;
import com.elk.elkweb.service.ZAServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/elk")
public class ELKZAErrorController {
    @Autowired
    private ZAServiceImpl zaService;
    @RequestMapping(value = "/searchZAError",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public List serachZAError(){
        List<Map<String, Object>> serachZAError = zaService.serachZAError();
        return serachZAError;
    }
}