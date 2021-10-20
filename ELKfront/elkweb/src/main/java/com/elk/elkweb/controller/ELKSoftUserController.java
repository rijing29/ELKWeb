package com.elk.elkweb.controller;

import com.elk.elkweb.entity.UserUseRate;
import com.elk.elkweb.service.SoftUserService;
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

    /**
     * DaPingKuoLuo
     * Time:2021/10/11  14:56
     * 根据用户名查询一周内用户使用软件效率
     * */
    @ResponseBody
    @RequestMapping(value = "/getUserUseRate",produces = "application/json;charset=utf-8")
    public UserUseRate getUserUseRate(@Param("users")String users)throws Exception{
        UserUseRate userUseRate1 = new UserUseRate();
        String maxTime = softUserService.getMaxTime();
        List<Map< String, Object >> softname = softUserService.getsoftname(users);
        System.out.println(softname+"---------------+++++");
        String Time[] = new String[7*softname.size()];
        String Softname[] = new String[7*softname.size()];
        Double UserUseRate[] = new Double[7*softname.size()];
        Date cutOneDay=null;
        cutOneDay=transferDate(maxTime);
        int k=0;
        for(int i=0;i<7;i++){
            String startTime= dayStartTime(transferString(cutOneDay));
            String stopTime= dayStopTime(transferString(cutOneDay));
            String time=tarnsDay(transferString(cutOneDay));
            System.out.println(startTime+"starttime");
            for(int j=0;j<softname.size();j++){
                String soft_name = softname.get(j).get("SOFT_NAME").toString();
                System.out.println(softname.get(j).get("SOFT_NAME").toString()+"8888888");
                Double workLoad = softUserService.getWork_load(soft_name);
                Double sumJob = softUserService.getSumJob(soft_name,users, startTime, stopTime);
                System.out.println(sumJob+"----------"+workLoad);
                Double userUseRate = calculate(sumJob,workLoad);
                System.out.println(userUseRate);
                Time[k]=time;
                Softname[k]=soft_name;
                UserUseRate[k]=Double.valueOf(new Formatter().format("%.1f", sumJob).toString());
//                if(userUseRate*100>100){ //注销了此代码，使用率变成使用时间，向UserUseRate[k]传值不同
//                    UserUseRate[k]=95.0;
//                }
//                else {
//                    UserUseRate[k]=Double.valueOf(new Formatter().format("%.1f", userUseRate*100).toString());
//                }
                k++;
            }
            cutOneDay  = cutOneDay(cutOneDay);
        }
        userUseRate1.setTime(Time);
        userUseRate1.setUseRate(UserUseRate);
        userUseRate1.setSoftname(Softname);
        System.out.println(userUseRate1);
        return userUseRate1;
    }

    public static Double calculate(Double sumJob,Double workLoad){
        Double calculate=0.0;
        if(sumJob==0){
            calculate=0.0;
        }
        else {
            calculate = Double.valueOf(sumJob/(48*workLoad));
        }

        System.out.println(sumJob+"总时间");
        return calculate;
    }

    public Date cutOneDay(Date date){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,-1); //把日期往后增加一天,整数  往后推,负数往前移动
        date=calendar.getTime(); //这个时间就是日期往后推一天的结果
        return date;
    }

    public String tarnsDay(String s){
        StringBuilder builder = new StringBuilder(s);
        builder.replace(10,s.length(),"");
        String tarnsDay = builder.toString();
        return tarnsDay;
    }

    public String dayStopTime(String s){
        StringBuilder builder = new StringBuilder(s);
        builder.replace(11,s.length(),"23:59:59");
        String dayStopTime = builder.toString();
        return dayStopTime;
    }
    public String dayStartTime(String s){
        StringBuilder builder = new StringBuilder(s);
        builder.replace(11,s.length(),"00:00:00");
        String dayStartTime = builder.toString();
        return dayStartTime;
    }

    /**
     * Description:
     * date: 2021/7/15 9:57
     * @author: whj
     * @method:string转date
     */
    public static Date transferDate(String Time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(Time);
        return date;
    }
    /**
     * Description:
     * date: 2021/7/15 15:20
     * @author: whj
     * @method:date转string
     */
    public static String transferString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String res = formatter.format(date);
        return res;
    }
}
