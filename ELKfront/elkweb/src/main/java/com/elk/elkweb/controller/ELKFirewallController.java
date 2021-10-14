package com.elk.elkweb.controller;

import com.elk.elkweb.entity.*;
import com.elk.elkweb.service.FireWallService;
import com.elk.elkweb.service.FireWallServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
import java.text.ParseException;
import java.util.*;
import java.util.List;

@Controller
@RequestMapping("/elk")
public class ELKFirewallController {
    /**
     *
     * 查询下拉框信息
     *
     * */
    @Autowired
    private FireWallService fireWallService;
    @RequestMapping(value = "/getSelectionInfo",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public dataInfo getSelectInfo(@Param("startTime") String startTime,
                                  @Param("stopTime") String stopTime) throws ParseException {
        dataInfo dataInfo = new dataInfo();//引入dataInfo对象
//        查询四个下拉框值
        List<Map< String, Object >> srczonename = fireWallService.getSrczonename(dayStart(startTime), dayStopTime(stopTime));
        List<Map< String, Object >> dstzonename = fireWallService.getDstzonename(dayStart(startTime), dayStopTime(stopTime));
        List<Map< String, Object >> protocol = fireWallService.getProtocol(dayStart(startTime), dayStopTime(stopTime));
        List<Map< String, Object >> event = fireWallService.getEvent(dayStart(startTime), dayStopTime(stopTime));
//        创建四个数组空间
        String keySrczonename[] = new String[srczonename.size()];
        String keyDstzonename[] = new String[dstzonename.size()];
        String keyProtocol[] = new String[protocol.size()];
        String keyEvent[] = new String[event.size()];
//        创建"选项k"数组空间,用以向前端传递"选项k"
        String key_option[] = new String[srczonename.size()+dstzonename.size()+protocol.size()+event.size()];
        int k=0;

//        查询SRCZONENAME并存储到dataInfo
        for(int i=0;i<srczonename.size();i++){
            keySrczonename[i] =srczonename.get(i).get("SRCZONENAME").toString();
            key_option[k]="'选项"+k+"'";
            k++;
        }
        dataInfo.setSourceDomain(keySrczonename);

//        查询DSTZONENAME并存储到dataInfo
        for(int i=0;i<dstzonename.size();i++){
            keyDstzonename[i] =dstzonename.get(i).get("DSTZONENAME").toString();
            key_option[k]="'选项"+k+"'";
            k++;
        }
        dataInfo.setDestinationDomain(keyDstzonename);

//        查询PROTOCOL并存储到dataInfo
        for(int i=0;i<protocol.size();i++){
            keyProtocol[i] =protocol.get(i).get("PROTOCOL").toString();
            key_option[k]="'选项"+k+"'";
            k++;
        }
        dataInfo.setProtocol(keyProtocol);

//        查询EVENT并存储到dataInfo
        for(int i=0;i<event.size();i++){
            keyEvent[i] =event.get(i).get("EVENT").toString();
            key_option[k]="'选项"+k+"'";
            k++;
        }
        dataInfo.setEvent(keyEvent);

//      将key_option存入dataInfo
        dataInfo.setOption(key_option);
        return dataInfo;
    }

    /**
     *
     *根据选项筛选外表信息
     *
     * */
    @RequestMapping(value = "/getSelection",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public PageInfo<info> getSelection(@Param("startTime") String startTime,
                                       @Param("stopTime") String stopTime,
                                       @Param("sourceDomain")String sourceDomain,
                                       @Param("destinationDomain")String destinationDomain,
                                       @Param("protocol") String protocol,
                                       @Param("event") String event,
                                       @Param("sourceIP") String sourceIP,
                                       @Param("sourcePort")String sourcePort,
                                       @Param("destinationIP") String destinationIP,
                                       @Param("destinationPort")String destinationPort,
                                       @Param("srcIp")String srcIp,
                                       @Param("dstIp")String dstIp,
                                       @Param("pageNum")String pageNum,
                                       @Param("pageSize")String pageSize) throws ParseException {
        System.out.println(dayStart(startTime)+ dayStopTime(stopTime));
        Integer pnum = Integer.valueOf(pageNum);
        Integer psize = Integer.valueOf(pageSize);
        PageHelper.startPage(pnum, psize);//1,20
        List<info> selection = fireWallService.getSelection(dayStart(startTime), dayStopTime(stopTime), sourceDomain,
                destinationDomain, protocol, event, sourceIP, sourcePort, destinationIP, destinationPort,srcIp,dstIp);
        PageInfo<info> pageInfoUser = new PageInfo<info>(selection);
        pageInfoUser.setList(selection);
        return pageInfoUser;
    }

    /**
     *
     *根据所选择的信息查询所有信息
     *
     * */
    @RequestMapping(value = "/getInfo",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public List<FireWallInfo> getInfo(@Param("startTime") String startTime,
                                      @Param("stopTime") String stopTime,
                                      @Param("SRCZONENAME")String SRCZONENAME,
                                      @Param("DSTZONENAME")String DSTZONENAME,
                                      @Param("PROTOCOL") String PROTOCOL,
                                      @Param("EVENT") String EVENT,
                                      @Param("DSTIPADDR") String DSTIPADDR,
                                      @Param("DSTPORT") String DSTPORT,
                                      @Param("SRCIPADDR")String SRCIPADDR,
                                      @Param("SRCPORT")String SRCPORT) throws ParseException {
        System.out.println("数据传递："+startTime+stopTime+SRCZONENAME+DSTZONENAME+PROTOCOL+EVENT+DSTIPADDR+DSTPORT+SRCIPADDR+SRCPORT);
        List<FireWallInfo> selection = fireWallService.getInfo(dayStart(startTime), dayStopTime(stopTime),SRCZONENAME,DSTZONENAME,PROTOCOL, EVENT,DSTIPADDR, DSTPORT, SRCIPADDR, SRCPORT);
        selection.forEach(System.out::println);
        return selection;
    }

    public String dayStopTime(String s){
        StringBuilder builder = new StringBuilder(s);
        builder.replace(11,s.length(),"23:59:59");
        String dayStopTime = builder.toString();
        return dayStopTime;
    }

    public String dayStart(String s){
        StringBuilder builder = new StringBuilder(s);
        builder.replace(11,s.length(),"00:00:00");
        String dayStart = builder.toString();
        return dayStart;
    }
}
