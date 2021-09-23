package com.elk.elkweb.entity;

import java.util.Date;

public class HWLicence {
    private String licenceServer;

    private String logTime;

    private Date inputTime;

    private String moduleName;

    private String userAction;

    private String userName;

    private String userCopmuter;

    public String getLicenceServer() {
        return licenceServer;
    }

    public void setLicenceServer(String licenceServer) {
        this.licenceServer = licenceServer == null ? null : licenceServer.trim();
    }

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime == null ? null : logTime.trim();
    }

    public Date getInputTime() {
        return inputTime;
    }

    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    public String getUserAction() {
        return userAction;
    }

    public void setUserAction(String userAction) {
        this.userAction = userAction == null ? null : userAction.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserCopmuter() {
        return userCopmuter;
    }

    public void setUserCopmuter(String userCopmuter) {
        this.userCopmuter = userCopmuter == null ? null : userCopmuter.trim();
    }
}