package com.elk.elkweb.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ViewCountAPPWell {
    private BigDecimal id;

    private String modulename;

    private String department;

    private String username;

    private String vyear;

    private String vmonth;

    private String phasename;

    private String wellnum;

    private Date vdatetime;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getModulename() {
        return modulename;
    }

    public void setModulename(String modulename) {
        this.modulename = modulename == null ? null : modulename.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getVyear() {
        return vyear;
    }

    public void setVyear(String vyear) {
        this.vyear = vyear == null ? null : vyear.trim();
    }

    public String getVmonth() {
        return vmonth;
    }

    public void setVmonth(String vmonth) {
        this.vmonth = vmonth == null ? null : vmonth.trim();
    }

    public String getPhasename() {
        return phasename;
    }

    public void setPhasename(String phasename) {
        this.phasename = phasename == null ? null : phasename.trim();
    }

    public String getWellnum() {
        return wellnum;
    }

    public void setWellnum(String wellnum) {
        this.wellnum = wellnum == null ? null : wellnum.trim();
    }

    public Date getVdatetime() {
        return vdatetime;
    }

    public void setVdatetime(Date vdatetime) {
        this.vdatetime = vdatetime;
    }
}