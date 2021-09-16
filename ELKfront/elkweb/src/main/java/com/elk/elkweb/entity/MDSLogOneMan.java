package com.elk.elkweb.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MDSLogOneMan {
    private String personname;

    private Long classno;

    private BigDecimal avgdays;

    private BigDecimal countoneday;

    private BigDecimal jb;

    private BigDecimal onetype;

    private BigDecimal twotype;

    private BigDecimal thrtype;

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

    public BigDecimal getAvgdays() {
        return avgdays;
    }

    public void setAvgdays(BigDecimal avgdays) {
        this.avgdays = avgdays;
    }

    public BigDecimal getCountoneday() {
        return countoneday;
    }

    public void setCountoneday(BigDecimal countoneday) {
        this.countoneday = countoneday;
    }

    public BigDecimal getJb() {
        return jb;
    }

    public void setJb(BigDecimal jb) {
        this.jb = jb;
    }

    public BigDecimal getOnetype() {
        return onetype;
    }

    public void setOnetype(BigDecimal onetype) {
        this.onetype = onetype;
    }

    public BigDecimal getTwotype() {
        return twotype;
    }

    public void setTwotype(BigDecimal twotype) {
        this.twotype = twotype;
    }

    public BigDecimal getThrtype() {
        return thrtype;
    }

    public void setThrtype(BigDecimal thrtype) {
        this.thrtype = thrtype;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}