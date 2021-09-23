package com.elk.elkweb.entity;

import java.util.Arrays;

public class CpuGpuResults {
    private String name[];
    private String time[];

    @Override
    public String toString() {
        return "CpuGpuResults{" +
                "name=" + Arrays.toString(name) +
                ", time=" + Arrays.toString(time) +
                '}';
    }

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public String[] getTime() {
        return time;
    }

    public void setTime(String[] time) {
        this.time = time;
    }

    public CpuGpuResults() {
    }

    public CpuGpuResults(String[] name, String[] time) {
        this.name = name;
        this.time = time;
    }
}
