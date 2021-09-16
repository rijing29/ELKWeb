package com.elk.elkweb.entity;

import java.util.Date;

public class ModuleUsage {
    private String softwarename;

    private String modulename;

    private Short usercount;

    private Short usemintue;

    private Date time;

    public String getSoftwarename() {
        return softwarename;
    }

    public void setSoftwarename(String softwarename) {
        this.softwarename = softwarename == null ? null : softwarename.trim();
    }

    public String getModulename() {
        return modulename;
    }

    public void setModulename(String modulename) {
        this.modulename = modulename == null ? null : modulename.trim();
    }

    public Short getUsercount() {
        return usercount;
    }

    public void setUsercount(Short usercount) {
        this.usercount = usercount;
    }

    public Short getUsemintue() {
        return usemintue;
    }

    public void setUsemintue(Short usemintue) {
        this.usemintue = usemintue;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}