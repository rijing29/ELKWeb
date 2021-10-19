package com.elk.elkweb.controller;

import com.elk.elkweb.entity.IPSAnalysis;
import com.elk.elkweb.entity.info;
import com.elk.elkweb.service.IPSService;
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
public class ELKIPSController {
    /**
     *
     * 查询IPS告警分析信息
     *
     * */
    @Qualifier("IPSServiceImpl")
    @Autowired
    private IPSService ipsService;
    @RequestMapping(value = "/getIPSAnalysis",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public IPSAnalysis getIPSAnalyize(@Param("time") String time) throws ParseException {
        IPSAnalysis ipsAnalysis = new IPSAnalysis();
        String Time = dayStopTime(time);//根据传入的时间得到当天最后时间 --23:59:59
        List<Map< String, Object >> IPSAnalyse = ipsService.getIPSAnalysis(Time);//查询
        IPSAnalyse.forEach(System.out::println);
        //定义三个数组
        String IP[] = new String[IPSAnalyse.size()];
        String TIME[] = new String[IPSAnalyse.size()];
        String DANGERVALUE[] = new String[IPSAnalyse.size()];
        //定义三个变量
        int low=0,middle=0,high=0;//计算三个变量,用以饼状图数据展示

        for (int i=0;i<IPSAnalyse.size();i++){
            IP[i]=IPSAnalyse.get(i).get("IP").toString();
            TIME[i]=cutTime(IPSAnalyse.get(i).get("TIME").toString());
            int dangervalue = Integer.valueOf(IPSAnalyse.get(i).get("DANGERVALUE").toString());
            if(dangervalue<=10){
                DANGERVALUE[i]="低";
                low++;
            }
            else if(dangervalue>10&&dangervalue<=50){
                DANGERVALUE[i]="中";
                middle++;
            }
            else if(dangervalue>50){
                DANGERVALUE[i]="高";
                high++;
            }
        }
        ipsAnalysis.setDANGERVALUE(DANGERVALUE);
        ipsAnalysis.setDSTIPADDR(IP);
        ipsAnalysis.setTIME(TIME);
        ipsAnalysis.setLOW(low);
        ipsAnalysis.setMIDDLE(middle);
        ipsAnalysis.setHIGH(high);
        System.out.println(ipsAnalysis+"--------");
        return ipsAnalysis;
    }

    /**
     *
     * 查询IPS详细信息
     *
     * */
    @RequestMapping(value = "/getIPSInfo",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public IPSAnalysis getIPSInfo(@Param("time") String time,
                                  @Param("dstipaddr") String dstipaddr,
                                  @Param("pageNum")String pageNum,
                                  @Param("pageSize")String pageSize) throws ParseException {
        Integer pnum = Integer.valueOf(pageNum);
        Integer psize = Integer.valueOf(pageSize);
        System.out.println(time+"--------"+dstipaddr);
        String startTime = SplicingTime(time);
        String stopTime = dayStopTime(startTime);//根据传入的时间得到当天最后时间 --23:59:59
        System.out.println(startTime+"--------"+stopTime+"--------"+dstipaddr);
        IPSAnalysis ipsAnalysis = new IPSAnalysis();
        PageHelper.startPage(pnum, psize);//1,20
        List<Map< String, Object >> IPSInfo = ipsService.getIPSInfo(startTime, stopTime, dstipaddr);
        PageInfo<Map< String, Object >> pageInfoUser = new PageInfo<Map< String, Object >>(IPSInfo);
        pageInfoUser.setList(IPSInfo);

        System.out.println("数据："+pageInfoUser.getTotal());
        //定义三个数组
        String TIME[] = new String[IPSInfo.size()];
        String ATTACKNAME[] = new String[IPSInfo.size()];
        String DANGERVALUE[] = new String[IPSInfo.size()];
        String SRCIPADDR[] = new String[IPSInfo.size()];
        for (int i=0;i<IPSInfo.size();i++){
            TIME[i]=pageInfoUser.getList().get(i).get("TIME").toString();
            ATTACKNAME[i]=pageInfoUser.getList().get(i).get("ATTACKNAME").toString();
            SRCIPADDR[i]=pageInfoUser.getList().get(i).get("SRCIPADDR").toString();
            String level=pageInfoUser.getList().get(i).get("SEVERITY").toString();
            if(level.equals("LOW")){
                DANGERVALUE[i]="低";
            }
            else if(level.equals("MEDIUM")){
                DANGERVALUE[i]="中";
            }
            else if(level.equals("HIGH")){
                DANGERVALUE[i]="高";
            }
        }
        ipsAnalysis.setTIME(TIME);
        ipsAnalysis.setATTACKNAME(ATTACKNAME);
        ipsAnalysis.setDANGERVALUE(DANGERVALUE);
        ipsAnalysis.setSRCIPADDR(SRCIPADDR);
        ipsAnalysis.setTOTAL(Integer.valueOf(Math.toIntExact(pageInfoUser.getTotal())));
        System.out.println(ipsAnalysis);
        return ipsAnalysis;
    }

    /**
     *
     * 根据时间，计算截止时间
     *
     * */
    public String dayStopTime(String s){
        StringBuilder builder = new StringBuilder(s);
        builder.replace(11,s.length(),"23:59:59");
        String dayStopTime = builder.toString();
        return dayStopTime;
    }
    /**
     *
     * 剪切时分秒
     *
     * */
    public String cutTime(String s){
        StringBuilder builder = new StringBuilder(s);
        builder.replace(10,s.length(),"");
        String time = builder.toString();
        return time;
    }

    /**
     *
     * 添加时分秒
     *
     * */
    public String SplicingTime(String s){
        StringBuilder builder = new StringBuilder(s);
        builder.replace(10,s.length()," 00:00:00");
        String time = builder.toString();
        return time;
    }

    /**
    *@Author:whj
    *@date:2021-10-19 9:45
    *@Method:根据源ip找到所有的受攻击的ip
    */
    @RequestMapping(value = "/getAttackedIP",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public List getAttackedIP(@Param("startTime") String startTime,
                              @Param("srcipaddr") String srcipaddr){
        String stopTime=startTime+" 23:59:59";
        List<Map<String, Object>> attackedIP = ipsService.getAttackedIP(startTime, stopTime, srcipaddr);
        return attackedIP;
    }
}
