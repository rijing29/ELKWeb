package com.elk.elkweb.entity;

import java.math.BigDecimal;
import java.util.Date;

public class siteCDWDSD {
    private Object placename;

    private BigDecimal wd;

    private BigDecimal sd;

    private Date time;

    public Object getPlacename() {
        return placename;
    }

    public void setPlacename(Object placename) {
        this.placename = placename;
    }

    public BigDecimal getWd() {
        return wd;
    }

    public void setWd(BigDecimal wd) {
        this.wd = wd;
    }

    public BigDecimal getSd() {
        return sd;
    }

    public void setSd(BigDecimal sd) {
        this.sd = sd;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}