package com.elk.elkweb.entity;

import java.math.BigDecimal;
import java.util.Date;

public class EquipState {
    private BigDecimal cpu;

    private BigDecimal gpu;

    private Date time;

    public BigDecimal getCpu() {
        return cpu;
    }

    public void setCpu(BigDecimal cpu) {
        this.cpu = cpu;
    }

    public BigDecimal getGpu() {
        return gpu;
    }

    public void setGpu(BigDecimal gpu) {
        this.gpu = gpu;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}