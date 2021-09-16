package com.elk.elkweb.entity;

import java.util.Date;

public class IPMILogInfo {
    private Object ip;

    private Object info;

    private Date time;

    public Object getIp() {
        return ip;
    }

    public void setIp(Object ip) {
        this.ip = ip;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}