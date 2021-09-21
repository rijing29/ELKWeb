package com.elk.elkweb.entity;

import java.util.Date;

public class siteUPSLoad {
    private Object upsname;

    private Object itemname;

    private Long value;

    private Date time;

    public Object getUpsname() {
        return upsname;
    }

    public void setUpsname(Object upsname) {
        this.upsname = upsname;
    }

    public Object getItemname() {
        return itemname;
    }

    public void setItemname(Object itemname) {
        this.itemname = itemname;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}