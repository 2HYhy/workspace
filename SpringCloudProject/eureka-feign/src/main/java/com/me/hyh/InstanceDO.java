package com.me.hyh;

import java.io.Serializable;

/**
 * @author CH-yfy
 * @date 2018/8/10
 */
public class InstanceDO implements Serializable{
    private static final long serialVersionUID = -409647832760000530L;

    private String instanceId;
    private String hostName;
    private String ipAddr;
    private String status;
    private String app;
    private Integer port;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "InstanceDO{" +
                "instanceId='" + instanceId + '\'' +
                ", hostName='" + hostName + '\'' +
                ", ipAddr='" + ipAddr + '\'' +
                ", status='" + status + '\'' +
                ", app='" + app + '\'' +
                ", port=" + port +
                '}';
    }
}
