package com.elk.elkweb.entity;

import java.util.Date;

public class LogWinJobInc {
    private String ip;

    private Date time;

    private String type;

    private Integer incValue;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getIncValue() {
        return incValue;
    }

    public void setIncValue(Integer incValue) {
        this.incValue = incValue;
    }
}