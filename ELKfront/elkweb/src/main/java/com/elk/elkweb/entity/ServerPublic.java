package com.elk.elkweb.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ServerPublic {
    private Date time;

    private Object ip;

    private BigDecimal netfloatforworktime;

    private BigDecimal netfloatforsparetime;

    private BigDecimal cpurateforworktime;

    private BigDecimal cpurateforsparetime;

    private BigDecimal memrateforworktime;

    private BigDecimal memrateforsparetime;

    public BigDecimal getTotalpjvalue() {
        return totalpjvalue;
    }

    public void setTotalpjvalue(BigDecimal totalpjvalue) {
        this.totalpjvalue = totalpjvalue;
    }

    private BigDecimal totalpjvalue;

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

    public BigDecimal getNetfloatforworktime() {
        return netfloatforworktime;
    }

    public void setNetfloatforworktime(BigDecimal netfloatforworktime) {
        this.netfloatforworktime = netfloatforworktime;
    }

    public BigDecimal getNetfloatforsparetime() {
        return netfloatforsparetime;
    }

    public void setNetfloatforsparetime(BigDecimal netfloatforsparetime) {
        this.netfloatforsparetime = netfloatforsparetime;
    }

    public BigDecimal getCpurateforworktime() {
        return cpurateforworktime;
    }

    public void setCpurateforworktime(BigDecimal cpurateforworktime) {
        this.cpurateforworktime = cpurateforworktime;
    }

    public BigDecimal getCpurateforsparetime() {
        return cpurateforsparetime;
    }

    public void setCpurateforsparetime(BigDecimal cpurateforsparetime) {
        this.cpurateforsparetime = cpurateforsparetime;
    }

    public BigDecimal getMemrateforworktime() {
        return memrateforworktime;
    }

    public void setMemrateforworktime(BigDecimal memrateforworktime) {
        this.memrateforworktime = memrateforworktime;
    }

    public BigDecimal getMemrateforsparetime() {
        return memrateforsparetime;
    }

    public void setMemrateforsparetime(BigDecimal memrateforsparetime) {
        this.memrateforsparetime = memrateforsparetime;
    }

}