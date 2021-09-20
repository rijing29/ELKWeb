package com.elk.elkweb.controller;

import com.elk.elkweb.mapper.EpySoftWareUsageMapper;
import com.elk.elkweb.service.EPYDEPDQMDS;
import com.elk.elkweb.service.EPYDEPService;
import com.elk.elkweb.service.EPYService;
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
public class ELKEPYController {
    @Qualifier("EPYServiceImpl")
    @Autowired
    private EPYService epyService;
    @Autowired
    private EPYDEPService epydepService;
    @Autowired
    private EPYDEPDQMDS epydepdqmds;
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
    /**
     * Description:
     * date: 2021/9/11 17:10
     * @author: whj
     * @method:员工使用软件行为
     */
    @RequestMapping(value = "/getEpySoftWareUsage",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public List showEpySoftWareUsage(@Param("username")String username){
        List<EpySoftWareUsageMapper> epySoftWareUsageMapperList = epyService.showEpySoftWareUsage(username);
        return epySoftWareUsageMapperList;
    }
    /**
     * Description:
     * date: 2021/9/13 19:58
     * @author: whj
     * @method:员工近期使用软件统计
     */
    @RequestMapping(value = "/getEpySoftWareUsageCount",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public List showEpySoftWareUsageCount(@Param("username")String username){
        List<Map<String, Object>> showEpySoftWareUsageCount = epyService.showEpySoftWareUsageCount(username);
        return showEpySoftWareUsageCount;
    }
   /**
   *@Author:whj
   *@date:2021-09-209:28
   *@Method:员工外网浏览
   */
   public List showEpyNet(@Param("username")String username){
       List epyServiceEpyNet = epyService.getEpyNet(username);
       return epyServiceEpyNet;
   }

   /**
   *@Author:whj
   *@date:2021-09-2010:59
   *@Method:单位员工刷卡统计
   */
   @RequestMapping(value = "/getCardDEP",produces = "application/json;charset=utf-8" )
   @ResponseBody
   public List getCardDEP(){
       List cardDEP = epydepService.getCardDEP();
       return cardDEP;
   }
   /**
   *@Author:whj
   *@date:2021-09-2011:50
   *@Method:单位DQMDS日志统计
   */
   @RequestMapping(value = "/getDQMDSDEP",produces = "application/json;charset=utf-8" )
   @ResponseBody
   public List getDQMDSDEP(){
       List depdqmds = epydepdqmds.getDEPDQMDS();
       return depdqmds;
   }

}
