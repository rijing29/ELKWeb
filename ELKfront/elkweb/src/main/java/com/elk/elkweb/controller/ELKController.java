package com.elk.elkweb.controller;
import com.elk.elkweb.entity.IPMILogInfo;
import com.elk.elkweb.entity.TableExport;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.elk.elkweb.entity.NodeSoftMap;
import com.elk.elkweb.entity.dataResults;
import com.elk.elkweb.service.ELKService;
import com.elk.elkweb.service.EfficiService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/elk")
public class ELKController {
    @Qualifier("ELKServiceImpl")
    @Autowired
    private ELKService elkService;
    @Autowired
    private EfficiService efficiService;
    //    查询所有table数据
    @RequestMapping(value = "/queryTable",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public PageInfo<NodeSoftMap> show(@Param("pageNum")String pageNum, @Param("pageSize")String pageSize){
        Integer pnum = Integer.valueOf(pageNum);
        Integer psize = Integer.valueOf(pageSize);
        PageHelper.startPage(pnum, psize);//1,20
        List<NodeSoftMap> nodeSoftMaps = elkService.queryTableData();
        PageInfo<NodeSoftMap> pageInfoUser = new PageInfo<NodeSoftMap>(nodeSoftMaps);
        pageInfoUser.setList(nodeSoftMaps);
        System.out.println(nodeSoftMaps);
        return pageInfoUser;
    }
    //    删除
    @ResponseBody
    @RequestMapping(value = "/del",produces = "application/json;charset=utf-8" )
    public void del(@RequestParam("nodeType") String nodeType) {
        System.out.println("从前端传回来的："+nodeType);
        elkService.del(nodeType);
    }
    //    新增
    @RequestMapping(value = "/add",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public void add(
            @RequestParam("nodeType") String nodeType,
            @RequestParam("softName") String softName,
            @RequestParam("startValue") int startValue,
            @RequestParam("stopValue") int stopValue,
            @RequestParam("workLoad") Long workLoad){
        int count=stopValue-startValue+1;
        for(int i=startValue;i<=stopValue;i++){
            int res = elkService.isExits(nodeType,i,softName);
            System.out.println(res);
            if(res<0){
                System.out.println("未插入成功的id："+i);
            }else{
                System.out.println("可以插入的值:"+i);
                elkService.add(nodeType,i, softName,workLoad);
            }
        }
    }

    /**
     * Description:
     * date: 2021/7/15 14:40
     * @author: whj
     * @method: 按软件名查询 传入年和月 最终计算得到每天的日效率
     */
    @RequestMapping(value = "/calSoftName",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public dataResults softNameEfficiency(@RequestParam("softName") String softName,
                                          @RequestParam("Time") String Time) throws ParseException {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!时间!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+Time);
        List dateList = calDate(Time);
        String startTime =(String) dateList.get(0)+" 00:00:00";
        String stopTime =(String) dateList.get(1)+" 00:00:00";
        System.out.println(startTime+"-----"+stopTime);
        LinkedHashMap<String, Double> EfficiencyMap = new LinkedHashMap<String, Double>();
        double aveEffici = calSoftNameAveEffici(startTime, stopTime,30, softName, EfficiencyMap);
        dataResults dataResults = new dataResults();
        System.out.println("----------------------------平均效率-----------------------"+aveEffici);
        return res(dataResults, aveEffici, EfficiencyMap);
    }
    /**
     *@Author:whj
     *@date:2021-09-28 10:14
     *@Method:按节点名查询 传入年和月 最终计算得到每天的日效率
     */
    @RequestMapping(value = "/calNodeType",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public dataResults nodeTypeEfficiency(@RequestParam("nodeType")String nodeType,
                                          @RequestParam("nodeId")String nodeId,
                                          @RequestParam("Time") String Time) throws ParseException {
        List dateList = calDate(Time);
        String startTime =(String) dateList.get(0)+" 00:00:00";
        String stopTime =(String) dateList.get(1)+" 00:00:00";
        System.out.println(startTime+"-----按节点名查询，传入年月----"+stopTime);
        LinkedHashMap<String, Double> EfficiencyMap = new LinkedHashMap<String, Double>();
        double aveEffici = calNodeTypeAveEffici(startTime, stopTime, 30, nodeType, nodeId, EfficiencyMap);
        dataResults dataResults = new dataResults();
        System.out.println("----------------------------平均效率-----------------------"+aveEffici);
        return res(dataResults, aveEffici, EfficiencyMap);
    }

    /**
     *@Author:whj
     *@date:2021-09-28 10:19
     *@Method:按软件名查询 传入年 计算最终得到的月平均
     */
    @RequestMapping(value = "/calSoftNameYear",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public dataResults nodeTypeEfficiency(@RequestParam("softName") String softName,
                                          @RequestParam("Time") String Time) throws ParseException {
        List dateList = calDate(Time);
        String startTime =(String) dateList.get(0)+" 00:00:00";
        String stopTime =(String) dateList.get(1)+" 00:00:00";
        System.out.println(startTime+"-----"+stopTime);
        LinkedHashMap<String, Double> EfficiencyMap = new LinkedHashMap<String, Double>();
        double aveEffici = 0;
        String[] months = new String[12];
        double[] efficicy = new double[12];
        int j=1;
        Date addThreeDay=null;
        addThreeDay=transferDate(startTime);
        for(int i=0;i<12;i++) {
            String startTimeTwo = transferString(addThreeDay);
            aveEffici = calSoftNameAveEfficiMonth(startTimeTwo, stopTime, 30, softName, EfficiencyMap);
            months[i]=Time+"-"+j+"月";
            j++;
            efficicy[i]=aveEffici;
            addThreeDay= addThirstyDay(addThreeDay);
        }
        dataResults results = new dataResults();
        results.setKey(months);
        results.setValue(efficicy);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!结果为！！！！！！！！！！！!!!!!!!!!!!!!!!"+results+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return results;
    }
    /**
     *@Author:whj
     *@date:2021-09-2811:18
     *@Method:按节点查询 传入年 计算最终得到的月平均
     */
    @RequestMapping(value = "/calNodeTypeYear",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public dataResults nodeTypeEfficiencyYear(@RequestParam("nodeType")String nodeType,
                                              @RequestParam("nodeId")String nodeId,
                                              @RequestParam("Time") String Time) throws ParseException {
        List dateList = calDate(Time);
        String startTime =(String) dateList.get(0)+" 00:00:00";
        String stopTime =(String) dateList.get(1)+" 00:00:00";
        System.out.println(startTime+"askdhkadka"+stopTime);
        LinkedHashMap<String, Double> EfficiencyMap = new LinkedHashMap<String, Double>();
        double aveEffici = 0;
        String[] months = new String[12];
        double[] efficicy = new double[12];
        int j=1;
        Date addThreeDay=null;
        addThreeDay=transferDate(startTime);
        for(int i=0;i<12;i++) {
            String startTimeTwo = transferString(addThreeDay);
            aveEffici =  calNodeTypeAveEffici(startTimeTwo, stopTime, 30, nodeType, nodeId, EfficiencyMap);
            months[i]=Time+"-"+j+"月";
            j++;
            efficicy[i]=aveEffici;
            addThreeDay= addThirstyDay(addThreeDay);
        }
        dataResults results = new dataResults();
        results.setKey(months);
        results.setValue(efficicy);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!结果为！！！！！！！！！！！!!!!!!!!!!!!!!!"+results+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return results;
    }



    /**
     * Description:
     * date: 2021/7/21 11:05
     * @author: whj
     * @method:ShowTable里的方法softName
     */
    @RequestMapping(value = "/tableSoftName",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public JSONArray nodeType(@RequestParam("softName") String softName,
                              @RequestParam("startTime") String startTime,
                              @RequestParam("stopTime") String stopTime) throws ParseException {
        float day = calculateTimeGapDay(startTime, stopTime);
        LinkedHashMap<String, Double> EfficiencyMap = new LinkedHashMap<String, Double>();
        JSONArray jsonArray = jsonArraySoftNameAveEffici(startTime, stopTime, day, softName, EfficiencyMap);
        return jsonArray;
    }
    /**
     * Description:
     * date: 2021/7/21 11:16
     * @author: whj
     * @method:ShowTable里的方法nodeType
     */
    @RequestMapping(value = "/tableNodeType",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public JSONArray nodeType(@Param("nodeType")String nodeType,
                              @Param("nodeId")String nodeId,
                              @Param("startTime")String startTime,
                              @Param("stopTime")String stopTime) throws ParseException {
        float day = calculateTimeGapDay(startTime, stopTime);
        LinkedHashMap<String, Double> EfficiencyMap = new LinkedHashMap<String, Double>();
        JSONArray jsonArray = jsonArrayNodeTypeAveEffici(startTime, stopTime, day, nodeType, nodeId, EfficiencyMap);
        return jsonArray;
    }

    /**
     * Description:
     * date: 2021/7/20 10:09
     * @author: whj
     * @method:nodeType计算平均效率总和
     */
    public double calNodeTypeAveEffici(String startTime,String stopTime,float day,String nodeType,String nodeId,LinkedHashMap<String, Double> EfficiencyMap) throws ParseException {
        Date addOneDay=null;
        addOneDay=transferDate(startTime);
        double total=0;
//        定义一个json数组对象
        JSONArray array1 = null;
        JSONArray array2 = null;
        for(int i=0;i<day;i++){
            //        startTime
            String dayStopTime = dayStopTime(transferString(addOneDay));//2020/7/14 23:59:59
            String dayStart=transferString(addOneDay);//2020/7/14 7:45:26
            String dayStop=dayStopTime;//2020/7/14 23:59:59
            NodeSoftMap nodeSoftMap = new NodeSoftMap(nodeType,null,nodeId);
            //        各节点工作量之和(分母)
            int sumNodeWorkLoad = efficiService.sumNodeTypeWorkLoad(nodeSoftMap);
            //        工作总量（分子）
            int sumTotalWorkLoad = efficiService.nodeTypeEfficiency(nodeType,nodeId,dayStart,dayStop);
            //        日效率
            double oneDayEfficiency = calOneDayEfficiency(sumTotalWorkLoad, sumNodeWorkLoad);
            total=total+oneDayEfficiency;
            String yAxis = splitStartTime(dayStart);
            EfficiencyMap.put(yAxis,oneDayEfficiency);

            //        日期加1
            addOneDay  = addOneDay(addOneDay);//+1
        }
        JSONArray jsonArray = joinJSON(EfficiencyMap);
        System.out.println("json字符串："+jsonArray);
        double aveEffici=calAveEfficiency(startTime,stopTime,total);
        return aveEffici;
    }
    /**
     * Description:
     * date: 2021/7/20 10:38
     * @author: whj
     * @method:softName计算平均效率总和
     */
    public double calSoftNameAveEffici(String startTime,String stopTime,float day,String softName,LinkedHashMap<String, Double> EfficicencyMap) throws ParseException {
        //        加一天的变量
        Date addOneDay=null;
        addOneDay=transferDate(startTime);
        //        总workload个数
        double total=0;
        for(int i=0;i<day;i++){
            //        startTime
            String dayStopTime = dayStopTime(transferString(addOneDay));//2020/7/14 23:59:59
            String dayStart=transferString(addOneDay);//2020/7/14 7:45:26
            String dayStop=dayStopTime;//2020/7/14 23:59:59
            NodeSoftMap nodeSoftMap = new NodeSoftMap("",null,softName);
            //        各节点工作量之和(分母)
            int sumNodeWorkLoad = efficiService.sumWorkLoad(nodeSoftMap);
            //        工作总量（分子）
            int sumTotalWorkLoad = efficiService.softNameEfficiency(softName, dayStart, dayStop);
            //        日效率
            double oneDayEfficiency = calOneDayEfficiency(sumTotalWorkLoad, sumNodeWorkLoad);
            total=total+oneDayEfficiency;
            String yAxis = splitStartTime(dayStart);
            EfficicencyMap.put(yAxis,oneDayEfficiency);
            //        日期加1
            addOneDay  = addOneDay(addOneDay);//+1
            System.out.println("加了一天之后的天数：---------------------"+addOneDay);
        }
        JSONArray jsonArray = joinJSON(EfficicencyMap);
        System.out.println("json字符串："+jsonArray);
        double aveEffici=calAveEfficiency(startTime,stopTime,total);
        return aveEffici;
    }
    /**
     *@Author:whj
     *@date:2021-09-2810:26
     *@Method:传入一年计算每个月的月平均
     */
    public double calSoftNameAveEfficiMonth(String startTime,String stopTime,float day,String softName,LinkedHashMap<String, Double> EfficicencyMap) throws ParseException {
        //        加一天的变量
        Date addOneDay=null;
        addOneDay=transferDate(startTime);
        //        总workload个数
        double total=0;
        for(int i=0;i<day;i++){
            //        startTime
            String dayStopTime = dayStopTime(transferString(addOneDay));//2020/7/14 23:59:59
            String dayStart=transferString(addOneDay);//2020/7/14 7:45:26
            String dayStop=dayStopTime;//2020/7/14 23:59:59
            NodeSoftMap nodeSoftMap = new NodeSoftMap("",null,softName);
            //        各节点工作量之和(分母)
            int sumNodeWorkLoad = efficiService.sumWorkLoad(nodeSoftMap);
            //        工作总量（分子）
            int sumTotalWorkLoad = efficiService.softNameEfficiency(softName, dayStart, dayStop);
            //        日效率
            double oneDayEfficiency = calOneDayEfficiency(sumTotalWorkLoad, sumNodeWorkLoad);
            if(oneDayEfficiency>=0.95){
                oneDayEfficiency=0.95;
            }
            System.out.println("这次对了吧!!!!"+oneDayEfficiency);
            total=total+oneDayEfficiency;
            String yAxis = splitStartTime(dayStart);
//            EfficicencyMap.put(yAxis,oneDayEfficiency);
            //        日期加1
            addOneDay  = addOneDay(addOneDay);//+1
            System.out.println("加了一天之后的天数：---------------------"+addOneDay);
        }
        JSONArray jsonArray = joinJSON(EfficicencyMap);
        System.out.println("json字符串："+jsonArray);
        double aveEffici=calAveEfficiency(startTime,stopTime,total);
        System.out.println("10月11日生成的月效率:"+aveEffici);
        return aveEffici;
    }
    /**
     * Description:
     * date: 2021/7/20 15:00
     * @author: whj
     * @method:切割字符串切掉时分秒
     */
    public String splitStartTime(String startTime){
        int i = startTime.indexOf(" ");
        System.out.println(i);
        startTime=startTime.substring(0,i);
        return startTime;
    }

    /**
     * Description:
     * date: 2021/7/21 10:34
     * @author: whj
     * @method:softName返回JSONArray
     */
    public JSONArray jsonArraySoftNameAveEffici(String startTime,String stopTime,float day,String softName,LinkedHashMap<String, Double> EfficicencyMap) throws ParseException {
        //        加一天的变量
        Date addOneDay=null;
        addOneDay=transferDate(startTime);
        //        总个数
        double total=0;
        for(int i=0;i<day;i++){
            //        startTime
            String dayStopTime = dayStopTime(transferString(addOneDay));//2020/7/14 23:59:59
            String dayStart=transferString(addOneDay);//2020/7/14 7:45:26
            String dayStop=dayStopTime;//2020/7/14 23:59:59
            NodeSoftMap nodeSoftMap = new NodeSoftMap("",null,softName);
            //        各节点工作量之和(分母)
            int sumNodeWorkLoad = efficiService.sumWorkLoad(nodeSoftMap);
            //        工作总量（分子）
            int sumTotalWorkLoad = efficiService.softNameEfficiency(softName, dayStart, dayStop);
            //        日效率
            double oneDayEfficiency = calOneDayEfficiency(sumTotalWorkLoad, sumNodeWorkLoad);
            total=total+oneDayEfficiency;
            String yAxis = splitStartTime(dayStart);
            EfficicencyMap.put(yAxis,oneDayEfficiency);
            //        日期加1
            addOneDay  = addOneDay(addOneDay);//+1
        }
        return joinJSON(EfficicencyMap);
    }
    /**
     * Description:
     * date: 2021/7/21 11:18
     * @author: whj
     * @method:nodeType返回jsonArray
     */
    public JSONArray jsonArrayNodeTypeAveEffici(String startTime,String stopTime,float day,String nodeType,String nodeId,LinkedHashMap<String, Double> EfficiencyMap) throws ParseException {
        Date addOneDay=null;
        addOneDay=transferDate(startTime);
        double total=0;
//        定义一个json数组对象
        JSONArray array1 = null;
        JSONArray array2 = null;
        for(int i=0;i<day;i++){
            //        startTime
            String dayStopTime = dayStopTime(transferString(addOneDay));//2020/7/14 23:59:59
            String dayStart=transferString(addOneDay);//2020/7/14 7:45:26
            String dayStop=dayStopTime;//2020/7/14 23:59:59
            NodeSoftMap nodeSoftMap = new NodeSoftMap(nodeType,null,nodeId);
            //        各节点工作量之和(分母)
            int sumNodeWorkLoad = efficiService.sumNodeTypeWorkLoad(nodeSoftMap);
            //        工作总量（分子）
            int sumTotalWorkLoad = efficiService.nodeTypeEfficiency(nodeType,nodeId,dayStart,dayStop);
            //        日效率
            double oneDayEfficiency = calOneDayEfficiency(sumTotalWorkLoad, sumNodeWorkLoad);
            total=total+oneDayEfficiency;
            String yAxis = splitStartTime(dayStart);
            EfficiencyMap.put(yAxis,oneDayEfficiency);
            //        日期加1
            addOneDay  = addOneDay(addOneDay);//+1
        }
        return joinJSON(EfficiencyMap);
    }

    /**
     * Description:
     * date: 2021/7/21 10:17
     * @author: whj
     * @method:向jsonArray中添加jsonObject
     */
    public JSONArray joinJSON(LinkedHashMap<String, Double> EfficicencyMap){
        JSONArray array = new JSONArray();
        Iterator iter = EfficicencyMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = String.valueOf(entry.getKey());
            String val = String.valueOf(entry.getValue());
            if(val.equals("Infinity")){
                val="0";
            }
            System.out.println("存进去的value值："+val);
            JSONObject object = new JSONObject();
            object.put("time",key);
            object.put("efficiency",val);
            array.add(object);
        }
        return array;
    }


    /**
     * Description:
     * date: 2021/7/20 10:17
     * @author: whj
     * @method:把key value ave三个数组放入dataRestults
     */
    public dataResults res(dataResults dataResults,double aveEffici,LinkedHashMap<String, Double> EfficiencyMap){
        double totalArr[]=new double[1];
        totalArr[0]=aveEffici;
        dataResults.setAve(totalArr);
        Iterator it=EfficiencyMap.values().iterator();
        while(it.hasNext()) {
            System.out.print("今天的日效率为："+it.next());
        }
        int i=0;
        for (Map.Entry<String, Double> entry : EfficiencyMap.entrySet()) {
            i++;
        }
        String key[] = new String[i];
        double value[] = new double[i];
        int j=0;
        for (Map.Entry<String, Double> entry : EfficiencyMap.entrySet()) {
            String num = entry.getKey();
            double count = entry.getValue();
            System.out.println("这次应该是对的了的效率值："+count);
            System.out.println(num+"---"+count);
            String s=String.valueOf(count);
            if(s.equals("Infinity")){
                count=0;
            }
            key[j]=num;
            value[j]=count;
            j++;
        }
        dataResults.setKey(key);
        dataResults.setValue(value);
        System.out.println(dataResults);
        return dataResults;
    }
    /**
     *@Author:whj
     *@date:2021-09-2810:40
     *@Method:放月平均的值
     */
    public dataResults resMonth(dataResults dataResults,double aveEffici,LinkedHashMap<String, Double> EfficiencyMap){
        double totalArr[]=new double[1];
        totalArr[0]=aveEffici;
        dataResults.setAve(totalArr);
        Iterator it=EfficiencyMap.values().iterator();
        int i=0;
        for (Map.Entry<String, Double> entry : EfficiencyMap.entrySet()) {
            i++;
        }
        String key[] = new String[i];
        double value[] = new double[i];
        int j=0;
        for (Map.Entry<String, Double> entry : EfficiencyMap.entrySet()) {
            String num = entry.getKey();
            double count = entry.getValue();
            System.out.println(num+"---"+count);
            key[j]=num;
            value[j]=count;
            j++;
        }
        dataResults.setKey(key);
        dataResults.setValue(value);
        System.out.println(dataResults);
        return dataResults;
    }

    /**
     * Description:
     * date: 2021/7/16 14:46
     * @author: whj
     * @method:计算平均效率
     */
    public static double calAveEfficiency(String time1,String time2,double total){
        float day = calculateTimeGapDay(time1, time2);
        return total/day;
    }

    /**
     * Description:
     * date: 2021/7/15 15:06
     * @author: whj
     * @method:日期+1天
     */
    public Date addOneDay(Date date){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,1); //把日期往后增加一天,整数  往后推,负数往前移动
        date=calendar.getTime(); //这个时间就是日期往后推一天的结果
        return date;
    }
    /**
     *@Author:whj
     *@date:2021-09-2817:15
     *@Method:日期+30天
     */
    public Date addThirstyDay(Date date){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,30); //把日期往后增加一天,整数  往后推,负数往前移动
        date=calendar.getTime(); //这个时间就是日期往后推一天的结果
        return date;
    }

    /**
     * Description:
     * date: 2021/7/15 14:50
     * @author: whj
     * @method:stopTime转dayStopTime
     */
    public String dayStopTime(String s){
        StringBuilder builder = new StringBuilder(s);
        builder.replace(10,s.length(),"23:59:59");
        String dayStopTime = builder.toString();
        return dayStopTime;
    }
    /**
     * Description:
     * date: 2021/7/15 14:30
     * @author: whj
     * @method:日效率计算
     */
    public double calOneDayEfficiency( int sumTotalWorkLoad, int sumNodeWorkLoad){
//        int wordLoad = efficiService.getSoftNameWordLoad(softName);
        double fenMu=24*2*sumTotalWorkLoad;        //✖ 4或1
        double res=sumNodeWorkLoad/fenMu;
        System.out.println("Total是："+sumTotalWorkLoad);
        System.out.println("node是："+sumNodeWorkLoad);
        System.out.println("分子和分母分别是："+fenMu+"-----------"+res);
        return res;
    }
    /**
     * Description:
     * date: 2021/7/15 9:46
     * @author: whj
     * @method:比较两个时间相差天数
     */
    public static float calculateTimeGapDay(String time1, String time2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyy-MM");
        float day = 0;
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = simpleDateFormat.parse(time1);
            date2 = simpleDateFormat.parse(time2);
            long millisecond = date2.getTime() - date1.getTime();
            day = millisecond / (24 * 60 * 60 * 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (day);
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
    /**
     * Description:
     * date: 2021/7/22 16:08
     * @author: whj
     * @method:把效率换成百分比
     */
    public String aveTransferPercen(Double aveEffici){
        System.out.println(aveEffici);
        NumberFormat nFromat = NumberFormat.getPercentInstance();
        String avePercent = nFromat.format(aveEffici);
        return avePercent;
    }

/********************************************************************************************************************************************************************************/


    /**
     *@Author:whj
     *@date:2021-09-2716:53
     *@Method:按软件名查询月的日效率
     */
    @RequestMapping(value = "/softNameEffiYear",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public void searchSoftNameYearEffi(@Param("softName") String softName,
                                       @Param("Time") String Time) throws ParseException {
        List dateList = calDate(Time);
        String startTime =(String) dateList.get(0);
        String stopTime =(String) dateList.get(1);
        NodeSoftMap nodeSoftMap = new NodeSoftMap("",null,softName);
        int sumWorkLoad = efficiService.sumWorkLoad(nodeSoftMap);
//        int sumTotalWorkLoad = efficiService.softNameEfficiency(softName, startTime, stopTime);
//        double efficiency = calEfficiency(sumTotalWorkLoad, sumWorkLoad);
        calMonthEffi(softName,Time,sumWorkLoad);
    }
    public void calMonthEffi(String softName,String Time,int sumWorkLoad) throws ParseException {
        List dateList = calDate(Time);
        String startTime =(String) dateList.get(0)+" 00:00:00";
        for(int i=0;i<31;i++){
            String stopTime = String.valueOf(addOneDay(transferDate(startTime)));
            int sumTotalWorkLoad = efficiService.softNameEfficiency(softName, startTime, stopTime);
            double efficiency = calEfficiency(sumTotalWorkLoad, sumWorkLoad);
            System.out.println(efficiency);
        }
    }
    /**
     *@Author:whj
     *@date:2021-09-2719:16
     *@Method:计算效率
     */
    public double calEfficiency(int fenZi,int fenMu){
        double i = fenZi / (fenMu * 48);
        return i;
    }
    /**
     *@Author:whj
     *@date:2021-09-2717:00
     *@Method:算日期的
     */
    public List calDate(String date) throws ParseException {
        String startTime = null;
        String stopTime = null;
        //        判断是否是年月查询
        boolean res = date.contains("-");
//        如果是按年查询
        if(!res){
            startTime=date+"-01-01";
            stopTime=date+"-12-31";
        }else{
            startTime=date+"-01";
            String substring = date.substring(5,7);
            String year=date.substring(0,3);
            if(substring.equals("01")){
                stopTime=date+"-31";
            }else if(substring.equals("02")){
                //判断是不是闰年
                boolean flag = ifBissExtile(year);
                if(flag)
                    stopTime=date+"-29";
                else
                    stopTime=date+"-28";
            }else if(substring.equals("03")){
                stopTime=date+"-31";
            }else if(substring.equals("04")){
                stopTime=date+"-30";
            }else if(substring.equals("05")){
                stopTime=date+"-31";
            }else if(substring.equals("06")){
                stopTime=date+"-30";
            }else if(substring.equals("07")){
                stopTime=date+"-31";
            }else if(substring.equals("08")){
                stopTime=date+"-31";
            }else if(substring.equals("09")){
                stopTime=date+"-30";
            }else if(substring.equals("10")){
                stopTime=date+"-31";
            }else if(substring.equals("11")){
                stopTime=date+"-30";
            }else if(substring.equals("12")){
                stopTime=date+"-31";
            }
        }
        ArrayList<Object> dateList = new ArrayList<>();
        dateList.add(startTime);
        dateList.add(stopTime);
        return dateList;
    }
    /**
     *@Author:whj
     *@date:2021-09-2717:18
     *@Method:判断闰年
     */
    public boolean ifBissExtile(String year) throws ParseException{
        Calendar calendar=Calendar.getInstance();
        boolean flag;
        //设置年份 月份
        calendar.set(Integer.parseInt(year), 2,1);
        calendar.add(calendar.DATE, -1);
        int day=calendar.get(calendar.DATE);
        if(day==29){flag=true; }
        else{flag=false; }
        return flag;
    }



}
