package com.elk.elkweb.entity;

import java.util.Date;

public class ZAHostsError {
    private String host;

    private Date errorsFrom;

    private String error;

    private Date time;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host == null ? null : host.trim();
    }

    public Date getErrorsFrom() {
        return errorsFrom;
    }

    public void setErrorsFrom(Date errorsFrom) {
        this.errorsFrom = errorsFrom;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error == null ? null : error.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}