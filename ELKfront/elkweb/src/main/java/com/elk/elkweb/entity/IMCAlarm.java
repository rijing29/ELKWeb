package com.elk.elkweb.entity;

import java.util.Date;

public class IMCAlarm {
    private Object id;

    private Object deviceid;

    private Object deviceip;

    private Object alarmlevel;

    private Object alarmcategorydesc;

    private Object recstatus;

    private Object alarmdesc;

    private Object rectimedesc;

    private Date faulttimedesc;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(Object deviceid) {
        this.deviceid = deviceid;
    }

    public Object getDeviceip() {
        return deviceip;
    }

    public void setDeviceip(Object deviceip) {
        this.deviceip = deviceip;
    }

    public Object getAlarmlevel() {
        return alarmlevel;
    }

    public void setAlarmlevel(Object alarmlevel) {
        this.alarmlevel = alarmlevel;
    }

    public Object getAlarmcategorydesc() {
        return alarmcategorydesc;
    }

    public void setAlarmcategorydesc(Object alarmcategorydesc) {
        this.alarmcategorydesc = alarmcategorydesc;
    }

    public Object getRecstatus() {
        return recstatus;
    }

    public void setRecstatus(Object recstatus) {
        this.recstatus = recstatus;
    }

    public Object getAlarmdesc() {
        return alarmdesc;
    }

    public void setAlarmdesc(Object alarmdesc) {
        this.alarmdesc = alarmdesc;
    }

    public Object getRectimedesc() {
        return rectimedesc;
    }

    public void setRectimedesc(Object rectimedesc) {
        this.rectimedesc = rectimedesc;
    }

    public Date getFaulttimedesc() {
        return faulttimedesc;
    }

    public void setFaulttimedesc(Date faulttimedesc) {
        this.faulttimedesc = faulttimedesc;
    }

    @Override
    public String toString() {
        return "IMCAlarm{" +
                "id=" + id +
                ", deviceid=" + deviceid +
                ", deviceip=" + deviceip +
                ", alarmlevel=" + alarmlevel +
                ", alarmcategorydesc=" + alarmcategorydesc +
                ", recstatus=" + recstatus +
                ", alarmdesc=" + alarmdesc +
                ", rectimedesc=" + rectimedesc +
                ", faulttimedesc=" + faulttimedesc +
                '}';
    }
}