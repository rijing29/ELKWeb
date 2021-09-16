package com.elk.elkweb.entity;

import java.util.Date;

public class IPSALERT {
    private Date time;

    private Object srczonename;

    private Object dstzonename;

    private Object protocol;

    private Object srcipaddr;

    private Object srcport;

    private Object dstipaddr;

    private Object dstport;

    private Object attackname;

    private Object category;

    private Object protection;

    private Object severity;

    private Object subcategory;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Object getSrczonename() {
        return srczonename;
    }

    public void setSrczonename(Object srczonename) {
        this.srczonename = srczonename;
    }

    public Object getDstzonename() {
        return dstzonename;
    }

    public void setDstzonename(Object dstzonename) {
        this.dstzonename = dstzonename;
    }

    public Object getProtocol() {
        return protocol;
    }

    public void setProtocol(Object protocol) {
        this.protocol = protocol;
    }

    public Object getSrcipaddr() {
        return srcipaddr;
    }

    public void setSrcipaddr(Object srcipaddr) {
        this.srcipaddr = srcipaddr;
    }

    public Object getSrcport() {
        return srcport;
    }

    public void setSrcport(Object srcport) {
        this.srcport = srcport;
    }

    public Object getDstipaddr() {
        return dstipaddr;
    }

    public void setDstipaddr(Object dstipaddr) {
        this.dstipaddr = dstipaddr;
    }

    public Object getDstport() {
        return dstport;
    }

    public void setDstport(Object dstport) {
        this.dstport = dstport;
    }

    public Object getAttackname() {
        return attackname;
    }

    public void setAttackname(Object attackname) {
        this.attackname = attackname;
    }

    public Object getCategory() {
        return category;
    }

    public void setCategory(Object category) {
        this.category = category;
    }

    public Object getProtection() {
        return protection;
    }

    public void setProtection(Object protection) {
        this.protection = protection;
    }

    public Object getSeverity() {
        return severity;
    }

    public void setSeverity(Object severity) {
        this.severity = severity;
    }

    public Object getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Object subcategory) {
        this.subcategory = subcategory;
    }

    @Override
    public String toString() {
        return "IPSALERT{" +
                "time=" + time +
                ", srczonename=" + srczonename +
                ", dstzonename=" + dstzonename +
                ", protocol=" + protocol +
                ", srcipaddr=" + srcipaddr +
                ", srcport=" + srcport +
                ", dstipaddr=" + dstipaddr +
                ", dstport=" + dstport +
                ", attackname=" + attackname +
                ", category=" + category +
                ", protection=" + protection +
                ", severity=" + severity +
                ", subcategory=" + subcategory +
                '}';
    }
}