package com.elk.elkweb.entity;

import java.math.BigDecimal;
import java.util.Date;

public class FrontCardDPMInfo {
    private String name;

    private Long counts;

    private BigDecimal placecount;

    private BigDecimal cq;

    private BigDecimal jb;

    private BigDecimal zs;

    private Date time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getCounts() {
        return counts;
    }

    public void setCounts(Long counts) {
        this.counts = counts;
    }

    public BigDecimal getPlacecount() {
        return placecount;
    }

    public void setPlacecount(BigDecimal placecount) {
        this.placecount = placecount;
    }

    public BigDecimal getCq() {
        return cq;
    }

    public void setCq(BigDecimal cq) {
        this.cq = cq;
    }

    public BigDecimal getJb() {
        return jb;
    }

    public void setJb(BigDecimal jb) {
        this.jb = jb;
    }

    public BigDecimal getZs() {
        return zs;
    }

    public void setZs(BigDecimal zs) {
        this.zs = zs;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}