package com.elk.elkweb.entity;

import java.util.Date;

public class MDSLog {
    private Object userid;

    private Object username;

    private Object operation;

    private Object operationcode;

    private Object description;

    private Date operationtime;

    private Object host;

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

    public Object getOperation() {
        return operation;
    }

    public void setOperation(Object operation) {
        this.operation = operation;
    }

    public Object getOperationcode() {
        return operationcode;
    }

    public void setOperationcode(Object operationcode) {
        this.operationcode = operationcode;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Date getOperationtime() {
        return operationtime;
    }

    public void setOperationtime(Date operationtime) {
        this.operationtime = operationtime;
    }

    public Object getHost() {
        return host;
    }

    public void setHost(Object host) {
        this.host = host;
    }
}