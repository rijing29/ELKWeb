package com.elk.elkweb.controller;

import com.elk.elkweb.entity.IPMILogInfo;
import com.elk.elkweb.entity.IPMILogWarn;
import com.elk.elkweb.entity.IPSAnalysis;
import com.elk.elkweb.service.IPMIService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/elk")
public class ELKIPMIController {
    /**
     *
     * 查询IPMI告警日志信息
     *
     * */
    @Autowired
    private IPMIService ipmiService;
    @RequestMapping(value = "/getIPMIAlarm",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public List<IPMILogWarn> getIPMIAlarm(@Param("time") String time) throws ParseException {
        System.out.println(time);
        String stopTime = time;//根据传入的时间得到当天最后时间 --23:59:59
        String startTime=transTime(transferString(decDay(transferDate(stopTime))));
        System.out.println(startTime);
        List<IPMILogWarn> IPMIAlarm = ipmiService.getIPMIAlarm(startTime,stopTime);
        return IPMIAlarm;
    }
    /**
     *
     * 查询IPMI详细信息
     *
     * */
    @RequestMapping(value = "/getIPMIInfo",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public List<IPMILogInfo> getIPMIInfo(@Param("ip") String ip) throws ParseException {
        List<IPMILogInfo> IPMIInfo = ipmiService.getIPMIInfo(ip);
        return IPMIInfo;
    }
    /**
     *
     * 查询IPMI的IP
     *
     * */
    @RequestMapping(value = "/getIPSelection",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public List<Map<String, Object>> getIPSelection() throws ParseException {
        List<Map<String, Object>> IPSelection = ipmiService.getIPSelection();
        System.out.println(IPSelection);
        return IPSelection;
    }

    /**
     *
     * 根据ip查询IPMI详细信息
     *
     * */
    @RequestMapping(value = "/getIPMIInfo1",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public List<IPMILogInfo> getIPMIInfo1(@Param("ip") String ip) throws ParseException {
        List<IPMILogInfo> IPMIInfo1 = ipmiService.getIPMIInfo1(ip);
        return IPMIInfo1;
    }

    /**
     *
     * 根据ip查询IPMI告警信息
     *
     * */
    @RequestMapping(value = "/getIPMIAlarm1",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public List<IPMILogWarn> getIPMIAlarm1(@Param("ip") String ip) throws ParseException {
        List<IPMILogWarn> IPMIAlarm1 = ipmiService.getIPMIAlarm1(ip);
        return IPMIAlarm1;
    }

    /**
     *
     * 根据时间，计算截止时间
     *
     * */
    public String transTime(String s){
        StringBuilder builder = new StringBuilder(s);
        builder.replace(11,s.length(),"00:00:00");
        String dayStopTime = builder.toString();
        return dayStopTime;
    }

    /**
     *
     *
     * 日期减7天
     *
     */
    public Date decDay(Date date){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,-6); //把日期往后增加一天,整数  往后推,负数往前移动
        date=calendar.getTime(); //这个时间就是日期往后推一天的结果
        return date;
    }

    /**
     *
     * string转date
     *
     */
    public static Date transferDate(String Time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = sdf.parse(Time);
        return date;
    }
    /**
     *
     * date转string
     *
     */
    public static String transferString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String res = formatter.format(date);
        return res;
    }
}
