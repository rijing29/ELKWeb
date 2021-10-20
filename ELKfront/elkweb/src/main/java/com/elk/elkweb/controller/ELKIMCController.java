package com.elk.elkweb.controller;

import com.elk.elkweb.entity.IMCAlarm;
import com.elk.elkweb.entity.IMCAlarm;
import com.elk.elkweb.service.IMCService;
import com.elk.elkweb.service.IPMIService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/elk")
public class ELKIMCController {
    @Qualifier("IMCServiceImpl")
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
    public PageInfo<IMCAlarm> getIMCInfo(@Param("ip")String ip,
                                     @Param("filtration")String filtration,
                                     @Param("pageNum")String pageNum,
                                     @Param("pageSize")String pageSize) throws ParseException {
        Integer pnum = Integer.valueOf(pageNum);
        Integer psize = Integer.valueOf(pageSize);
        PageHelper.startPage(pnum, psize);//1,20
        List<IMCAlarm> imcInfo = imcService.getIMCInfo(ip, filtration);
        PageInfo<IMCAlarm> pageInfoUser = new PageInfo<IMCAlarm>(imcInfo);
        pageInfoUser.setList(imcInfo);
        System.out.println(imcInfo);
        return pageInfoUser;
    }


    /**
     *
     * 默认查询IPMI
     *
     * */
    @RequestMapping(value = "/getDefaultIMC",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public List<IMCAlarm> getDefaultIMC() throws ParseException {
        List<IMCAlarm> defaultIMC = imcService.getDefaultIMC();
        System.out.println(defaultIMC);
        return defaultIMC;
    }
}
