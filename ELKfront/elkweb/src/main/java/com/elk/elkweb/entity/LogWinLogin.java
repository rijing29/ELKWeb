package com.elk.elkweb.entity;

import java.util.Date;

public class LogWinLogin {
    private String ip;

    private Long type2;

    private Long type3;

    private Long type5;

    private Long type10;

    private Date time;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Long getType2() {
        return type2;
    }

    public void setType2(Long type2) {
        this.type2 = type2;
    }

    public Long getType3() {
        return type3;
    }

    public void setType3(Long type3) {
        this.type3 = type3;
    }

    public Long getType5() {
        return type5;
    }

    public void setType5(Long type5) {
        this.type5 = type5;
    }

    public Long getType10() {
        return type10;
    }

    public void setType10(Long type10) {
        this.type10 = type10;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}