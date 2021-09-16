package com.elk.elkweb.entity;

public class info {
//    根据下拉框查询所有信息实体类
    private String sourceDomain;
    private String destinationDomain;
    private String protocol;
    private String event;
    private String option;
    private String srcipaddr;
    private String srcport;
    private String dstipaddr;
    private String dstport;
    private Integer count;

    public String getSourceDomain() {
        return sourceDomain;
    }

    public void setSourceDomain(String sourceDomain) {
        this.sourceDomain = sourceDomain;
    }

    public String getDestinationDomain() {
        return destinationDomain;
    }

    public void setDestinationDomain(String destinationDomain) {
        this.destinationDomain = destinationDomain;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getSrcipaddr() {
        return srcipaddr;
    }

    public void setSrcipaddr(String srcipaddr) {
        this.srcipaddr = srcipaddr;
    }

    public String getSrcport() {
        return srcport;
    }

    public void setSrcport(String srcport) {
        this.srcport = srcport;
    }

    public String getDstipaddr() {
        return dstipaddr;
    }

    public void setDstipaddr(String dstipaddr) {
        this.dstipaddr = dstipaddr;
    }

    public String getDstport() {
        return dstport;
    }

    public void setDstport(String dstport) {
        this.dstport = dstport;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }



    public info() {
    }

    @Override
    public String toString() {
        return "info{" +
                "sourceDomain='" + sourceDomain + '\'' +
                ", destinationDomain='" + destinationDomain + '\'' +
                ", protocol='" + protocol + '\'' +
                ", event='" + event + '\'' +
                ", option='" + option + '\'' +
                ", srcipaddr='" + srcipaddr + '\'' +
                ", srcport='" + srcport + '\'' +
                ", dstipaddr='" + dstipaddr + '\'' +
                ", dstport='" + dstport + '\'' +
                ", count=" + count +
                '}';
    }

    public info(String sourceDomain, String destinationDomain, String protocol, String event, String option, String srcipaddr, String srcport, String dstipaddr, String dstport, Integer count) {
        this.sourceDomain = sourceDomain;
        this.destinationDomain = destinationDomain;
        this.protocol = protocol;
        this.event = event;
        this.option = option;
        this.srcipaddr = srcipaddr;
        this.srcport = srcport;
        this.dstipaddr = dstipaddr;
        this.dstport = dstport;
        this.count = count;
    }
}
