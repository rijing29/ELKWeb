package com.elk.elkweb.entity;

import java.util.Date;

public class LogWinWarning {
    private String ip;

    private Date time;

    private String warntype;

    private String warninfo;

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

    public String getWarntype() {
        return warntype;
    }

    public void setWarntype(String warntype) {
        this.warntype = warntype == null ? null : warntype.trim();
    }

    public String getWarninfo() {
        return warninfo;
    }

    public void setWarninfo(String warninfo) {
        this.warninfo = warninfo == null ? null : warninfo.trim();
    }
}