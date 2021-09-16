package com.elk.elkweb.entity;

import java.util.Date;

public class IPS_ANALYIZE {
    private Date time;

    private Object ip;

    private Short dangervalue;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Object getIp() {
        return ip;
    }

    public void setIp(Object ip) {
        this.ip = ip;
    }

    public Short getDangervalue() {
        return dangervalue;
    }

    public void setDangervalue(Short dangervalue) {
        this.dangervalue = dangervalue;
    }

    @Override
    public String toString() {
        return "IPS_ANALYIZE{" +
                "time=" + time +
                ", ip=" + ip +
                ", dangervalue=" + dangervalue +
                '}';
    }
}