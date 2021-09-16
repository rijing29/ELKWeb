package com.elk.elkweb.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DoorInfoOneMan {
    private String personname;

    private Long classno;

    private BigDecimal placecount;

    private BigDecimal cq;

    private BigDecimal jb;

    private BigDecimal zs;

    private Date time;

    public String getPersonname() {
        return personname;
    }

    public void setPersonname(String personname) {
        this.personname = personname == null ? null : personname.trim();
    }

    public Long getClassno() {
        return classno;
    }

    public void setClassno(Long classno) {
        this.classno = classno;
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