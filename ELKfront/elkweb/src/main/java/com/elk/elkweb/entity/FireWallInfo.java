package com.elk.elkweb.entity;

import java.util.Date;

public class FireWallInfo {
    private Date time;

    private String srczonename;

    private String dstzonename;

    private String protocol;

    private String srcipaddr;

    private String srcport;

    private String dstipaddr;

    private String dstport;

    private String event;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getSrczonename() {
        return srczonename;
    }

    public void setSrczonename(String srczonename) {
        this.srczonename = srczonename == null ? null : srczonename.trim();
    }

    public String getDstzonename() {
        return dstzonename;
    }

    public void setDstzonename(String dstzonename) {
        this.dstzonename = dstzonename == null ? null : dstzonename.trim();
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol == null ? null : protocol.trim();
    }

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

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event == null ? null : event.trim();
    }

    @Override
    public String toString() {
        return "FireWallInfo{" +
                "time=" + time +
                ", srczonename='" + srczonename + '\'' +
                ", dstzonename='" + dstzonename + '\'' +
                ", protocol='" + protocol + '\'' +
                ", srcipaddr='" + srcipaddr + '\'' +
                ", srcport='" + srcport + '\'' +
                ", dstipaddr='" + dstipaddr + '\'' +
                ", dstport='" + dstport + '\'' +
                ", event='" + event + '\'' +
                '}';
    }
}