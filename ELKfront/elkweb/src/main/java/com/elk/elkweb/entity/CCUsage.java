package com.elk.elkweb.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CCUsage {
    private BigDecimal ggUsed;

    private BigDecimal ggFree;

    private BigDecimal lcUsed;

    private BigDecimal lcFree;

    private BigDecimal hwyzxUsed;

    private BigDecimal hwyzxFree;

    private BigDecimal bdhwUsed;

    private BigDecimal bdhwFree;

    private Date time;

    public BigDecimal getGgUsed() {
        return ggUsed;
    }

    public void setGgUsed(BigDecimal ggUsed) {
        this.ggUsed = ggUsed;
    }

    public BigDecimal getGgFree() {
        return ggFree;
    }

    public void setGgFree(BigDecimal ggFree) {
        this.ggFree = ggFree;
    }

    public BigDecimal getLcUsed() {
        return lcUsed;
    }

    public void setLcUsed(BigDecimal lcUsed) {
        this.lcUsed = lcUsed;
    }

    public BigDecimal getLcFree() {
        return lcFree;
    }

    public void setLcFree(BigDecimal lcFree) {
        this.lcFree = lcFree;
    }

    public BigDecimal getHwyzxUsed() {
        return hwyzxUsed;
    }

    public void setHwyzxUsed(BigDecimal hwyzxUsed) {
        this.hwyzxUsed = hwyzxUsed;
    }

    public BigDecimal getHwyzxFree() {
        return hwyzxFree;
    }

    public void setHwyzxFree(BigDecimal hwyzxFree) {
        this.hwyzxFree = hwyzxFree;
    }

    public BigDecimal getBdhwUsed() {
        return bdhwUsed;
    }

    public void setBdhwUsed(BigDecimal bdhwUsed) {
        this.bdhwUsed = bdhwUsed;
    }

    public BigDecimal getBdhwFree() {
        return bdhwFree;
    }

    public void setBdhwFree(BigDecimal bdhwFree) {
        this.bdhwFree = bdhwFree;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}