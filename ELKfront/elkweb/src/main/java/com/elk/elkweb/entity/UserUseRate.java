package com.elk.elkweb.entity;

import java.util.Arrays;

public class UserUseRate {
    private String time[];
    private String softname[];
    private Double useRate[];

    public String[] getTime() {
        return time;
    }

    public void setTime(String[] time) {
        this.time = time;
    }

    public String[] getSoftname() {
        return softname;
    }

    public void setSoftname(String[] softname) {
        this.softname = softname;
    }

    public Double[] getUseRate() {
        return useRate;
    }

    public void setUseRate(Double[] useRate) {
        this.useRate = useRate;
    }

    @Override
    public String toString() {
        return "UserUseRate{" +
                "time=" + Arrays.toString(time) +
                ", softname=" + Arrays.toString(softname) +
                ", useRate=" + Arrays.toString(useRate) +
                '}';
    }
}
