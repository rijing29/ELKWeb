package com.elk.elkweb.entity;

import java.util.Date;

public class SysSrmLog {
    private Object oid;

    private Object otype;

    private Date occurtime;

    private Object msgtype;

    private String msgdesc;

    private Object funname;

    private Object userid;

    private Object username;

    private Object userip;

    private Object userphone;

    private Object useremail;

    private Object systemid;

    private Object systemname;

    public Object getOid() {
        return oid;
    }

    public void setOid(Object oid) {
        this.oid = oid;
    }

    public Object getOtype() {
        return otype;
    }

    public void setOtype(Object otype) {
        this.otype = otype;
    }

    public Date getOccurtime() {
        return occurtime;
    }

    public void setOccurtime(Date occurtime) {
        this.occurtime = occurtime;
    }

    public Object getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(Object msgtype) {
        this.msgtype = msgtype;
    }

    public String getMsgdesc() {
        return msgdesc;
    }

    public void setMsgdesc(String msgdesc) {
        this.msgdesc = msgdesc == null ? null : msgdesc.trim();
    }

    public Object getFunname() {
        return funname;
    }

    public void setFunname(Object funname) {
        this.funname = funname;
    }

    public Object getUserid() {
        return userid;
    }

    public void setUserid(Object userid) {
        this.userid = userid;
    }

    public Object getUsername() {
        return username;
    }

    public void setUsername(Object username) {
        this.username = username;
    }

    public Object getUserip() {
        return userip;
    }

    public void setUserip(Object userip) {
        this.userip = userip;
    }

    public Object getUserphone() {
        return userphone;
    }

    public void setUserphone(Object userphone) {
        this.userphone = userphone;
    }

    public Object getUseremail() {
        return useremail;
    }

    public void setUseremail(Object useremail) {
        this.useremail = useremail;
    }

    public Object getSystemid() {
        return systemid;
    }

    public void setSystemid(Object systemid) {
        this.systemid = systemid;
    }

    public Object getSystemname() {
        return systemname;
    }

    public void setSystemname(Object systemname) {
        this.systemname = systemname;
    }
}