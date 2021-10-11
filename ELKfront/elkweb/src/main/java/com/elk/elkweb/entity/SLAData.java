package com.elk.elkweb.entity;

import java.util.Arrays;

public class SLAData {
    private String SOFTWARENAME[];
    private String SUMTIME[];

    public Double[] getFREQUENCY() {
        return FREQUENCY;
    }

    public void setFREQUENCY(Double[] FREQUENCY) {
        this.FREQUENCY = FREQUENCY;
    }

    private Double FREQUENCY[];


    private String OPTION[];
    private String MODULENAME[];

    public String[] getMODULENAME() { return MODULENAME; }

    public void setMODULENAME(String[] MODULENAME) { this.MODULENAME = MODULENAME; }

    public void setOPTION(String[] OPTION) { this.OPTION = OPTION; }

    public String[] getOPTION() { return OPTION; }

    public String[] getSOFTWARENAME() {
        return SOFTWARENAME;
    }

    public String[] getSUMTIME() {
        return SUMTIME;
    }

    public void setSOFTWARENAME(String[] SOFTWARENAME) {
        this.SOFTWARENAME = SOFTWARENAME;
    }

    public void setSUMTIME(String[] SUMTIME) {
        this.SUMTIME = SUMTIME;
    }

    @Override
    public String toString() {
        return "SLAData{" +
                "SOFTWARENAME=" + Arrays.toString(SOFTWARENAME) +
                ", SUMTIME=" + Arrays.toString(SUMTIME) +
                ", FREQUENCY=" + Arrays.toString(FREQUENCY) +
                ", OPTION=" + Arrays.toString(OPTION) +
                ", MODULENAME=" + Arrays.toString(MODULENAME) +
                '}';
    }
}
