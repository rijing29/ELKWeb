package com.elk.elkweb.controller;

import com.elk.elkweb.entity.*;
import com.elk.elkweb.mapper.SLAMapper;
import com.elk.elkweb.service.SLAService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Formatter;


@Controller
@RequestMapping("/elk")
public class ELKSLAController {
    @Qualifier("SLAServiceImpl")
    @Autowired
    private SLAService sLAService;
    /**
     *
     * time：2021/08/20 11:32
     * 全部软件使用频率
     *
     * */
    @RequestMapping(value = "/getAllFrequency",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public List<Map< String, Object >> getAllFrequency(@Param("startTime") String startTime,
                                                       @Param("stopTime") String stopTime) throws ParseException {
        List<Map< String, Object >> frequency = sLAService.getAllFrequency(startTime,stopTime);
        frequency.forEach(System.out::println);
        return frequency;
    }

    /**
     *
     * time：2021/08/20 11:32
     * 全部软件使用时间、使用率
     *
     * */
    @RequestMapping(value = "/getSumTime",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public SLAData getSumTime(@Param("startTime") String startTime,
                                                  @Param("stopTime") String stopTime) throws ParseException {
        List<Map< String, Object >> sumTime = sLAService.getSumTime(startTime,stopTime);
        SLAData slaData = new SLAData();
        Double frequency[] = new Double[sumTime.size()];
        String softwareName[] = new String[sumTime.size()];
        String sumtime[] = new String[sumTime.size()];
        for(int i=0;i<sumTime.size();i++){
            frequency[i] = Double.valueOf(new Formatter().format("%.2f",useFrequency(startTime,stopTime,Double.valueOf(sumTime.get(i).get("SUMTIME").toString()))*100).toString());
            //计算全部软件使用率并存入frequency数组
            softwareName[i] = sumTime.get(i).get("SOFTWARENAME").toString();
            //获取软件名并存入frequency数组
            sumtime[i] = sumTime.get(i).get("SUMTIME").toString();
            //获取软件使用时间并存入frequency数组
        }
        slaData.setSOFTWARENAME(softwareName);
        slaData.setSUMTIME(sumtime);
        slaData.setFREQUENCY(frequency);
        System.out.println(slaData);
        return slaData;
    }

    /**
     *
     * time：2021/08/21 09:00
     * 软件选择下拉框信息
     *
     * */
    @RequestMapping(value = "/getSoftwareName",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public SLAData getSelectInfo(@Param("startTime") String startTime,
                                  @Param("stopTime") String stopTime) throws ParseException {
        SLAData SLAData = new SLAData();//引入SLAData对象
//        查询四个下拉框值
        List<Map< String, Object >> softwarename = sLAService.getSoftwareName(startTime,stopTime);
//        创建四个数组空间
        String keySoftwarename[] = new String[softwarename.size()];
//        创建"选项k"数组空间,用以向前端传递"选项k"
        String key_option[] = new String[softwarename.size()];
        int k=0;

//        查询SRCZONENAME并存储到SLAData
        for(int i=0;i<softwarename.size();i++){
            keySoftwarename[i] =softwarename.get(i).get("SOFTWARENAME").toString();
            key_option[k]="'选项"+k+"'";
            k++;
        }
        SLAData.setSOFTWARENAME(keySoftwarename);
//        将key_option存入SLAData
        SLAData.setOPTION(key_option);
        return SLAData;
    }
    /**
     *
     * time：2021/08/21 11:03
     * 查询某种软件各个模块使用次数
     *
     * */
    @RequestMapping(value = "/getModuleCount",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public List<Map< String, Object >> getModuleCount(@Param("startTime") String startTime,
                                                      @Param("stopTime") String stopTime,
                                                      @Param("softwareName")String softwareName) throws ParseException {
        List<Map< String, Object >> moduleCount = sLAService.getModuleCount(startTime,stopTime,softwareName);
        moduleCount.forEach(System.out::println);
        return moduleCount;
    }

    /**
     *
     * time：2021/08/21 13:32
     * 某个软件各个模块使用时间、使用率
     *
     * */
    @RequestMapping(value = "/getModuleTime",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public SLAData getModuleTime(@Param("startTime") String startTime,
                                 @Param("stopTime") String stopTime,
                                 @Param("softwareName")String softwareName) throws ParseException {
        List<Map< String, Object >> sumTime = sLAService.getModuleTime(startTime,stopTime,softwareName);
        SLAData slaData = new SLAData();
        Double Frequency[] = new Double[sumTime.size()];
        String SoftwareName[] = new String[sumTime.size()];
        String ModuleName[]=new String[sumTime.size()];
        String Sumtime[] = new String[sumTime.size()];
        for(int i=0;i<sumTime.size();i++){
            //计算全部软件使用率并存入Frequency数组
            Frequency[i] =Double.valueOf(new Formatter().format("%.2f",useFrequency(startTime,stopTime,Double.valueOf(sumTime.get(i).get("SUMTIME").toString()))*100).toString());

            //获取软件名并存入Frequency数组
            SoftwareName[i] = sumTime.get(i).get("SOFTWARENAME").toString();

            //获取软件使用时间并存入Frequency数组
            ModuleName[i] = sumTime.get(i).get("MODULENAME").toString();

            //获取模块使用时间并存入Frequency数组
            Sumtime[i] = sumTime.get(i).get("SUMTIME").toString();
        }
        slaData.setSOFTWARENAME(SoftwareName);
        slaData.setMODULENAME(ModuleName);
        slaData.setSUMTIME(Sumtime);
        slaData.setFREQUENCY(Frequency);
        System.out.println(slaData);
        return slaData;
    }

    /**
     *
     * time：2021/08/20 11:50
     * 计算软件使用率
     *
     * */
    public Double useFrequency(String startTime,String stopTime,Double sumTime){

        double day = calculateTime(startTime,stopTime);
        Double time = day*24*60;
        Double useFrequency = sumTime*60/time;
//        Integer useFrequency = Integer.valueOf(new Formatter().format("%.0f",useFrequency1).toString()) ;
        return  useFrequency;
    }
    /**
     *
     * time：2021/08/20 11:51
     * 计算开始时间与结束时间的时间差
     *
     * */
    public static float calculateTime(String starTime,String stopTime){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");
        float day = 0;
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = simpleDateFormat.parse(starTime);
            date2 = simpleDateFormat.parse(stopTime);
            long millisecond = date2.getTime() - date1.getTime();
            day = millisecond / (24 * 60 * 60 * 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day+1;
    }
}
