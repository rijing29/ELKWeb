package com.elk.elkweb.controller;

import com.elk.elkweb.entity.NodeSoftMap;
import com.elk.elkweb.service.ZAHostsErrorService;
import com.elk.elkweb.service.ZAServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/elk")
public class ELKZAErrorController {
    @Autowired
    private ZAServiceImpl zaService;
    @RequestMapping(value = "/searchZAError",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public PageInfo<Map<String, Object>> serachZAError(@Param("pageNum")String pageNum, @Param("pageSize")String pageSize){
        Integer pnum = Integer.valueOf(pageNum);
        Integer psize = Integer.valueOf(pageSize);
        PageHelper.startPage(pnum, psize);//1,20
        List<Map<String, Object>> serachZAError = zaService.serachZAError();
        PageInfo<Map<String, Object>> pageInfoUser = new PageInfo<Map<String, Object>>(serachZAError);
        pageInfoUser.setList(serachZAError);
        System.out.println(serachZAError);
        return pageInfoUser;
    }

    /**
     * DaPingKuoLuo
     * 2121/10/10 21:27
     *
     * */

    @RequestMapping(value = "/searchWinlogbeats",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public PageInfo<Map<String, Object>> searchWinlogbeats(@Param("pageNum")String pageNum, @Param("pageSize")String pageSize){
        Integer pnum = Integer.valueOf(pageNum);
        Integer psize = Integer.valueOf(pageSize);
        PageHelper.startPage(pnum, psize);//1,20
        List<Map<String, Object>> Winlogbeats = zaService.searchWinlogbeats();
        PageInfo<Map<String, Object>> pageInfoUser = new PageInfo<Map<String, Object>>(Winlogbeats);
        pageInfoUser.setList(Winlogbeats);
        System.out.println(Winlogbeats);
        return pageInfoUser;
    }
}