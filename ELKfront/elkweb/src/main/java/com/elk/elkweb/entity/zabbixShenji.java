package com.elk.elkweb.entity;

import java.util.Date;

public class zabbixShenji {
    private String osUsername;

    private String username;

    private String userhost;

    private String terminal;

    private Date timestamp;

    private String owner;

    private String objName;

    private Short action;

    private String actionName;

    private String newOwner;

    private String newName;

    private String objPrivilege;

    private String sysPrivilege;

    private String adminOption;

    private String grantee;

    private String auditOption;

    private String sesActions;

    private Date logoffTime;

    private Short logoffLread;

    private Short logoffPread;

    private Short logoffLwrite;

    private String logoffDlock;

    private String commentText;

    private Short sessionid;

    private Short entryid;

    private Short statementid;

    private Short returncode;

    private String privUsed;

    private String clientId;

    private String econtextId;

    private Short sessionCpu;

    private Object extendedTimestamp;

    private Short proxySessionid;

    private String globalUid;

    private Short instanceNumber;

    private String osProcess;

    private Short scn;

    private Object sqlBind;

    private Object sqlText;

    private String objEditionName;

    private Short dbid;

    private byte[] transactionid;

    public String getOsUsername() {
        return osUsername;
    }

    public void setOsUsername(String osUsername) {
        this.osUsername = osUsername == null ? null : osUsername.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUserhost() {
        return userhost;
    }

    public void setUserhost(String userhost) {
        this.userhost = userhost == null ? null : userhost.trim();
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal == null ? null : terminal.trim();
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName == null ? null : objName.trim();
    }

    public Short getAction() {
        return action;
    }

    public void setAction(Short action) {
        this.action = action;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName == null ? null : actionName.trim();
    }

    public String getNewOwner() {
        return newOwner;
    }

    public void setNewOwner(String newOwner) {
        this.newOwner = newOwner == null ? null : newOwner.trim();
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName == null ? null : newName.trim();
    }

    public String getObjPrivilege() {
        return objPrivilege;
    }

    public void setObjPrivilege(String objPrivilege) {
        this.objPrivilege = objPrivilege == null ? null : objPrivilege.trim();
    }

    public String getSysPrivilege() {
        return sysPrivilege;
    }

    public void setSysPrivilege(String sysPrivilege) {
        this.sysPrivilege = sysPrivilege == null ? null : sysPrivilege.trim();
    }

    public String getAdminOption() {
        return adminOption;
    }

    public void setAdminOption(String adminOption) {
        this.adminOption = adminOption == null ? null : adminOption.trim();
    }

    public String getGrantee() {
        return grantee;
    }

    public void setGrantee(String grantee) {
        this.grantee = grantee == null ? null : grantee.trim();
    }

    public String getAuditOption() {
        return auditOption;
    }

    public void setAuditOption(String auditOption) {
        this.auditOption = auditOption == null ? null : auditOption.trim();
    }

    public String getSesActions() {
        return sesActions;
    }

    public void setSesActions(String sesActions) {
        this.sesActions = sesActions == null ? null : sesActions.trim();
    }

    public Date getLogoffTime() {
        return logoffTime;
    }

    public void setLogoffTime(Date logoffTime) {
        this.logoffTime = logoffTime;
    }

    public Short getLogoffLread() {
        return logoffLread;
    }

    public void setLogoffLread(Short logoffLread) {
        this.logoffLread = logoffLread;
    }

    public Short getLogoffPread() {
        return logoffPread;
    }

    public void setLogoffPread(Short logoffPread) {
        this.logoffPread = logoffPread;
    }

    public Short getLogoffLwrite() {
        return logoffLwrite;
    }

    public void setLogoffLwrite(Short logoffLwrite) {
        this.logoffLwrite = logoffLwrite;
    }

    public String getLogoffDlock() {
        return logoffDlock;
    }

    public void setLogoffDlock(String logoffDlock) {
        this.logoffDlock = logoffDlock == null ? null : logoffDlock.trim();
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText == null ? null : commentText.trim();
    }

    public Short getSessionid() {
        return sessionid;
    }

    public void setSessionid(Short sessionid) {
        this.sessionid = sessionid;
    }

    public Short getEntryid() {
        return entryid;
    }

    public void setEntryid(Short entryid) {
        this.entryid = entryid;
    }

    public Short getStatementid() {
        return statementid;
    }

    public void setStatementid(Short statementid) {
        this.statementid = statementid;
    }

    public Short getReturncode() {
        return returncode;
    }

    public void setReturncode(Short returncode) {
        this.returncode = returncode;
    }

    public String getPrivUsed() {
        return privUsed;
    }

    public void setPrivUsed(String privUsed) {
        this.privUsed = privUsed == null ? null : privUsed.trim();
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    public String getEcontextId() {
        return econtextId;
    }

    public void setEcontextId(String econtextId) {
        this.econtextId = econtextId == null ? null : econtextId.trim();
    }

    public Short getSessionCpu() {
        return sessionCpu;
    }

    public void setSessionCpu(Short sessionCpu) {
        this.sessionCpu = sessionCpu;
    }

    public Object getExtendedTimestamp() {
        return extendedTimestamp;
    }

    public void setExtendedTimestamp(Object extendedTimestamp) {
        this.extendedTimestamp = extendedTimestamp;
    }

    public Short getProxySessionid() {
        return proxySessionid;
    }

    public void setProxySessionid(Short proxySessionid) {
        this.proxySessionid = proxySessionid;
    }

    public String getGlobalUid() {
        return globalUid;
    }

    public void setGlobalUid(String globalUid) {
        this.globalUid = globalUid == null ? null : globalUid.trim();
    }

    public Short getInstanceNumber() {
        return instanceNumber;
    }

    public void setInstanceNumber(Short instanceNumber) {
        this.instanceNumber = instanceNumber;
    }

    public String getOsProcess() {
        return osProcess;
    }

    public void setOsProcess(String osProcess) {
        this.osProcess = osProcess == null ? null : osProcess.trim();
    }

    public Short getScn() {
        return scn;
    }

    public void setScn(Short scn) {
        this.scn = scn;
    }

    public Object getSqlBind() {
        return sqlBind;
    }

    public void setSqlBind(Object sqlBind) {
        this.sqlBind = sqlBind;
    }

    public Object getSqlText() {
        return sqlText;
    }

    public void setSqlText(Object sqlText) {
        this.sqlText = sqlText;
    }

    public String getObjEditionName() {
        return objEditionName;
    }

    public void setObjEditionName(String objEditionName) {
        this.objEditionName = objEditionName == null ? null : objEditionName.trim();
    }

    public Short getDbid() {
        return dbid;
    }

    public void setDbid(Short dbid) {
        this.dbid = dbid;
    }

    public byte[] getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(byte[] transactionid) {
        this.transactionid = transactionid;
    }
}