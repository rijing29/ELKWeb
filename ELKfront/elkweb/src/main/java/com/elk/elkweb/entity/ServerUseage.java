package com.elk.elkweb.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ServerUseage {
    private Date time;

    private String dirname;

    private String info;

    private Short dw;

    private BigDecimal use;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDirname() {
        return dirname;
    }

    public void setDirname(String dirname) {
        this.dirname = dirname == null ? null : dirname.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public Short getDw() {
        return dw;
    }

    public void setDw(Short dw) {
        this.dw = dw;
    }

    public BigDecimal getUse() {
        return use;
    }

    public void setUse(BigDecimal use) {
        this.use = use;
    }
}