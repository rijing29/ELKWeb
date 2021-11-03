package com.elk.elkweb.entity;

import java.util.Date;

public class CdnDataLog {
    private String userId;

    private String moudleName;

    private String dataContent;

    private Date operateDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getMoudleName() {
        return moudleName;
    }

    public void setMoudleName(String moudleName) {
        this.moudleName = moudleName == null ? null : moudleName.trim();
    }

    public String getDataContent() {
        return dataContent;
    }

    public void setDataContent(String dataContent) {
        this.dataContent = dataContent == null ? null : dataContent.trim();
    }

    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }
}