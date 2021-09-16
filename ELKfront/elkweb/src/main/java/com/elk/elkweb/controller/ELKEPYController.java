package com.elk.elkweb.controller;

import com.elk.elkweb.service.EPYService;
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
public class ELKEPYController {
    @Autowired
    private EPYService epyService;

    /**
     *
     * 查询员工刷卡信息
     *
     * */
    @RequestMapping(value = "/getEpyCardInfo",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public List<Map<String, Object>> getEpyCardInfo(@Param("user_lname")String user_lname) throws ParseException {
        List<Map<String, Object>> epyCardInfo = epyService.getEpyCardInfo(user_lname);
        epyCardInfo.forEach(System.out::println);
        return epyCardInfo;
    }

    /**
     *
     * 查询员工数据统计
     *
     * */
    @RequestMapping(value = "/getEpyCardData",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public List<Map<String, Object>> getEpyCardData(@Param("user_lname")String user_lname) throws ParseException {
        String personname= user_lname;
        System.out.println(personname+"---------------");
        List<Map<String, Object>> epyCardData = epyService.getEpyCardData(personname);
        epyCardData.forEach(System.out::println);
        return epyCardData;
    }

    /**
     *
     * 查询员DQMDS信息
     *
     * */
    @RequestMapping(value = "/getEpyDQMSInfo",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public List<Map<String, Object>> getEpyDQMSInfo(@Param("username")String username) throws ParseException {
        List<Map<String, Object>> epyDQMSInfo = epyService.getEpyDQMSInfo(username);
        epyDQMSInfo.forEach(System.out::println);
        return epyDQMSInfo;
    }

    /**
     *
     * 查询员工DQMDS统计
     *
     * */
    @RequestMapping(value = "/getEpyDQMDSData",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public List<Map<String, Object>> getEpyDQMDSData(@Param("username")String username) throws ParseException {
        String personname= username;
        System.out.println(personname+"---------------");
        List<Map<String, Object>> epyDQMDSData = epyService.getEpyDQMDSData(personname);
        epyDQMDSData.forEach(System.out::println);
        return epyDQMDSData;
    }
}
