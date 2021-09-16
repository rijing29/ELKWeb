package com.elk.elkweb.entity;

import java.util.Arrays;

public class IPSAnalysis {
    private String TIME[];
    private String DSTIPADDR[];
    private String SRCIPADDR[];
    private String ATTACKNAME[];
    private String DANGERVALUE[];
    private int LOW;
    private int MIDDLE;
    private int HIGH;
    private int TOTAL;

    public int getTOTAL() {
        return TOTAL;
    }

    public void setTOTAL(int TOTAL) {
        this.TOTAL = TOTAL;
    }

    public String[] getDSTIPADDR() {
        return DSTIPADDR;
    }

    public void setDSTIPADDR(String[] DSTIPADDR) {
        this.DSTIPADDR = DSTIPADDR;
    }

    public String[] getSRCIPADDR() {
        return SRCIPADDR;
    }

    public void setSRCIPADDR(String[] SRCIPADDR) {
        this.SRCIPADDR = SRCIPADDR;
    }

    public String[] getATTACKNAME() {
        return ATTACKNAME;
    }

    public void setATTACKNAME(String[] ATTACKNAME) {
        this.ATTACKNAME = ATTACKNAME;
    }

    public int getLOW() {
        return LOW;
    }

    public void setLOW(int LOW) {
        this.LOW = LOW;
    }

    public int getMIDDLE() {
        return MIDDLE;
    }

    public void setMIDDLE(int MIDDLE) {
        this.MIDDLE = MIDDLE;
    }

    public int getHIGH() {
        return HIGH;
    }

    public void setHIGH(int HIGH) {
        this.HIGH = HIGH;
    }


    public String[] getTIME() {
        return TIME;
    }

    public void setTIME(String[] TIME) {
        this.TIME = TIME;
    }

    public String[] getDANGERVALUE() {
        return DANGERVALUE;
    }

    public void setDANGERVALUE(String[] DANGERVALUE) {
        this.DANGERVALUE = DANGERVALUE;
    }

    @Override
    public String toString() {
        return "IPSAnalysis{" +
                "TIME=" + Arrays.toString(TIME) +
                ", DSTIPADDR=" + Arrays.toString(DSTIPADDR) +
                ", SRCIPADDR=" + Arrays.toString(SRCIPADDR) +
                ", ATTACKNAME=" + Arrays.toString(ATTACKNAME) +
                ", DANGERVALUE=" + Arrays.toString(DANGERVALUE) +
                ", LOW=" + LOW +
                ", MIDDLE=" + MIDDLE +
                ", HIGH=" + HIGH +
                ", TOTAL=" + TOTAL +
                '}';
    }
}
