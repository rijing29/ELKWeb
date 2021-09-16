package com.elk.elkweb.entity;

import java.util.Date;

public class EpySoftWareUsage {
    private String softwarename;

    private String username;

    private String modulename;

    private Short usemintue;

    private Short workusemintue;

    private Short spareusemintue;

    private Date time;

    public String getSoftwarename() {
        return softwarename;
    }

    public void setSoftwarename(String softwarename) {
        this.softwarename = softwarename == null ? null : softwarename.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getModulename() {
        return modulename;
    }

    public void setModulename(String modulename) {
        this.modulename = modulename == null ? null : modulename.trim();
    }

    public Short getUsemintue() {
        return usemintue;
    }

    public void setUsemintue(Short usemintue) {
        this.usemintue = usemintue;
    }

    public Short getWorkusemintue() {
        return workusemintue;
    }

    public void setWorkusemintue(Short workusemintue) {
        this.workusemintue = workusemintue;
    }

    public Short getSpareusemintue() {
        return spareusemintue;
    }

    public void setSpareusemintue(Short spareusemintue) {
        this.spareusemintue = spareusemintue;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}