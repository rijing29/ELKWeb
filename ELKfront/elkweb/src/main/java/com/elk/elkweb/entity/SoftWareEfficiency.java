package com.elk.elkweb.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SoftWareEfficiency {
    private BigDecimal geoeast;

    private BigDecimal es360;

    private BigDecimal qpstm;

    private Date time;

    public BigDecimal getGeoeast() {
        return geoeast;
    }

    public void setGeoeast(BigDecimal geoeast) {
        this.geoeast = geoeast;
    }

    public BigDecimal getEs360() {
        return es360;
    }

    public void setEs360(BigDecimal es360) {
        this.es360 = es360;
    }

    public BigDecimal getQpstm() {
        return qpstm;
    }

    public void setQpstm(BigDecimal qpstm) {
        this.qpstm = qpstm;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}