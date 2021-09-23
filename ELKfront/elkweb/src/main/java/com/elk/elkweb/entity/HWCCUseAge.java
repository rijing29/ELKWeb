package com.elk.elkweb.entity;

import java.util.Date;

public class HWCCUseAge {
    private String ccname;

    private Date time;

    private Short total;

    private Short use;

    public String getCcname() {
        return ccname;
    }

    public void setCcname(String ccname) {
        this.ccname = ccname == null ? null : ccname.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Short getTotal() {
        return total;
    }

    public void setTotal(Short total) {
        this.total = total;
    }

    public Short getUse() {
        return use;
    }

    public void setUse(Short use) {
        this.use = use;
    }
}