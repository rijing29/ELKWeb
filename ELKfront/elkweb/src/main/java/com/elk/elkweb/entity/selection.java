package com.elk.elkweb.entity;

import java.util.Date;

public class selection {
    private String srcipaddr;
    private String srcport;
    private String dstipaddr;
    private String dstport;
    private int count;

    public String getSrcipaddr() {
        return srcipaddr;
    }

    public void setSrcipaddr(String srcipaddr) {
        this.srcipaddr = srcipaddr == null ? null : srcipaddr.trim();
    }

    public String getSrcport() {
        return srcport;
    }

    public void setSrcport(String srcport) {
        this.srcport = srcport == null ? null : srcport.trim();
    }
    public String getDstipaddr() {
        return dstipaddr;
    }

    public void setDstipaddr(String dstipaddr) {
        this.dstipaddr = dstipaddr == null ? null : dstipaddr.trim();
    }

    public String getDstport() {
        return dstport;
    }

    public void setDstport(String dstport) {
        this.dstport = dstport == null ? null : dstport.trim();
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "selection{" +
                "srcipaddr='" + srcipaddr + '\'' +
                ", srcport='" + srcport + '\'' +
                ", dstipaddr='" + dstipaddr + '\'' +
                ", dstport='" + dstport + '\'' +
                ", count=" + count +
                '}';
    }
}
