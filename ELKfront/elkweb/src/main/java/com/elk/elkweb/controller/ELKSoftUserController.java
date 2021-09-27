package com.elk.elkweb.controller;

import com.elk.elkweb.service.SoftUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/elk")
public class ELKSoftUserController {
    @Autowired
    private SoftUserService softUserService;

    /**
     * DaPingKuoLuo
     * Time:2021/09/27  14:56
     * 获取软件名
     * */
    @ResponseBody
    @RequestMapping(value = "/getSoftName",produces = "application/json;charset=utf-8")
    public List<Map<String,Object>> getSoftName()throws Exception{
        List<Map<String, Object>> softName = softUserService.getSoftName();
        softName.forEach(System.out::println);
        return softName;
    }

    /**
     * DaPingKuoLuo
     * Time:2021/09/27  14:56
     * 根据软件名获取一周内用户数据
     * */
    @ResponseBody
    @RequestMapping(value = "/getSoft_userInfo",produces = "application/json;charset=utf-8")
    public List<Map<String,Object>> getSoft_userInfo(@Param("softName")String softName)throws Exception{
        List<Map<String, Object>> soft_userInfo = softUserService.getSoft_userInfo(softName);
        soft_userInfo.forEach(System.out::println);
        return soft_userInfo;
    }
}
