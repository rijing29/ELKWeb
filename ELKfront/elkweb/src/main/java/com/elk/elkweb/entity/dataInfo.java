package com.elk.elkweb.entity;

import java.util.Arrays;

public class dataInfo {
//    下拉框信息实体类
    private String sourceDomain[];
    private String destinationDomain[];
    private String protocol[];
    private String event[];
    private String option[];
    private String srcipaddr[];
    private String srcport[];
    private String dstipaddr[];
    private String dstport[];
    private Integer count[];

    public dataInfo() {
    }
    public void setSourceDomain(String[] sourceDomain) {
        this.sourceDomain = sourceDomain;
    }
    public String[] getSourceDomain() {
        return sourceDomain;
    }
    public void setDestinationDomain(String[] destinationDomain) {
        this.destinationDomain = destinationDomain;
    }
    public String[] getDestinationDomain() {
        return destinationDomain;
    }
    public void setProtocol(String[] protocol) {
        this.protocol = protocol;
    }
    public String[] getProtocol() {
        return protocol;
    }
    public void setEvent(String[] event) {
        this.event = event;
    }
    public String[] getEvent() {
        return event;
    }
    public void setOption(String[] option) {
        this.option = option;
    }
    public String[] getOption() {
        return option;
    }
    public void setSrcipaddr(String[] srcipaddr) {
        this.srcipaddr = srcipaddr;
    }
    public String[] getSrcipaddr() {
        return srcipaddr;
    }
    public void setSrcport(String[] srcport) {
        this.srcport = srcport;
    }
    public String[] getSrcport() {
        return srcport;
    }
    public void setDstipaddr(String[] dstipaddr) {
        this.dstipaddr = dstipaddr;
    }
    public String[] getDstipaddr() {
        return dstipaddr;
    }
    public void setDstport(String[] dstport) {
        this.dstport = dstport;
    }
    public String[] getDstport() {
        return dstport;
    }
    public void setCount(Integer[] count) {
        this.count = count;
    }
    public Integer[] getCount() {
        return count;
    }


    public dataInfo(String[] sourceDomain,
                    String[] destinationDomain,
                    String[] protocol,
                    String[] event,
                    String[] option,
                    String[] srcipaddr,
                    String[] srcport,
                    String[] dstipaddr,
                    String[] dstport,
                    Integer[] count) {
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

    @Override
    public String toString() {
        return "dataInfo{" +
                "sourceDomain=" + Arrays.toString(sourceDomain) +
                ", destinationDomain=" + Arrays.toString(destinationDomain) +
                ", protocol=" + Arrays.toString(protocol) +
                ", event=" + Arrays.toString(event) +
                ", option=" + Arrays.toString(option) +
                ", srcipaddr=" + Arrays.toString(srcipaddr) +
                ", srcport=" + Arrays.toString(srcport) +
                ", dstipaddr=" + Arrays.toString(dstipaddr) +
                ", dstport=" + Arrays.toString(dstport) +
                ", count=" + Arrays.toString(count) +
                '}';
    }
}
