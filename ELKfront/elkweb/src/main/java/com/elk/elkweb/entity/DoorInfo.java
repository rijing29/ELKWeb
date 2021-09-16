package com.elk.elkweb.entity;

import java.util.Date;

public class DoorInfo {
    private Long xh;

    private Long fx;

    private Date jlSj;

    private Object userNo;

    private Object userLname;

    private Object userDepname;

    private Object depName;

    public Long getXh() {
        return xh;
    }

    public void setXh(Long xh) {
        this.xh = xh;
    }

    public Long getFx() {
        return fx;
    }

    public void setFx(Long fx) {
        this.fx = fx;
    }

    public Date getJlSj() {
        return jlSj;
    }

    public void setJlSj(Date jlSj) {
        this.jlSj = jlSj;
    }

    public Object getUserNo() {
        return userNo;
    }

    public void setUserNo(Object userNo) {
        this.userNo = userNo;
    }

    public Object getUserLname() {
        return userLname;
    }

    public void setUserLname(Object userLname) {
        this.userLname = userLname;
    }

    public Object getUserDepname() {
        return userDepname;
    }

    public void setUserDepname(Object userDepname) {
        this.userDepname = userDepname;
    }

    public Object getDepName() {
        return depName;
    }

    public void setDepName(Object depName) {
        this.depName = depName;
    }
}