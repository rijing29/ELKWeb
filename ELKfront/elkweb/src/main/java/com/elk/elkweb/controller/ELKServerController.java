package com.elk.elkweb.controller;

import com.elk.elkweb.entity.ServerPublic;
import com.elk.elkweb.service.ServerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/elk")
public class ELKServerController {
    @Autowired
    private ServerInfoService serverInfoService;
//    展示公共服务器性能数据
    @ResponseBody
    @RequestMapping("/getPublicServer")
    public List<ServerPublic> showServerPubilc(){
        List<ServerPublic> serverPublics = serverInfoService.showServerPublic();
        return serverPublics;
    }
}
