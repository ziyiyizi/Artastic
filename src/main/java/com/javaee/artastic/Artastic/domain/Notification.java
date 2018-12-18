package com.javaee.artastic.Artastic.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Notification {
    private int notiId;
    private String senderName;
    private String receiverName;
    private Timestamp notiTime;
    private String notiState;
    private String notiContent;
    private String type;
    private String workName;
    private int workId;

    @Id
    @Column(name = "Noti_ID")
    public int getNotiId() {
        return notiId;
    }

    public void setNotiId(int notiId) {
        this.notiId = notiId;
    }

    @Basic
    @Column(name = "Sender_Name")
    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    @Basic
    @Column(name = "Receiver_Name")
    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    @Basic
    @Column(name = "Noti_Time")
    public Timestamp getNotiTime() {
        return notiTime;
    }

    public void setNotiTime(Timestamp notiTime) {
        this.notiTime = notiTime;
    }

    @Basic
    @Column(name = "Noti_State")
    public String getNotiState() {
        return notiState;
    }

    public void setNotiState(String notiState) {
        this.notiState = notiState;
    }

    @Basic
    @Column(name = "Noti_Content")
    public String getNotiContent() {
        return notiContent;
    }

    public void setNotiContent(String notiContent) {
        this.notiContent = notiContent;
    }
    
    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    @Basic
    @Column(name = "Work_Name")
    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }
    
    @Basic
    @Column(name = "Work_ID")
    public int getWorkId() {
        return workId;
    }

    public void setWorkId(int workId) {
        this.workId = workId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Notification that = (Notification) o;

        if (notiId != that.notiId) return false;
        if (senderName != null ? !senderName.equals(that.senderName) : that.senderName != null) return false;
        if (receiverName != null ? !receiverName.equals(that.receiverName) : that.receiverName != null) return false;
        if (notiTime != null ? !notiTime.equals(that.notiTime) : that.notiTime != null) return false;
        if (notiState != null ? !notiState.equals(that.notiState) : that.notiState != null) return false;
        if (notiContent != null ? !notiContent.equals(that.notiContent) : that.notiContent != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (workName != null ? !workName.equals(that.workName) : that.workName != null) return false;
        if (workId != that.workId) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = notiId;
        result = 31 * result + (senderName != null ? senderName.hashCode() : 0);
        result = 31 * result + (receiverName != null ? receiverName.hashCode() : 0);
        result = 31 * result + (notiTime != null ? notiTime.hashCode() : 0);
        result = 31 * result + (notiState != null ? notiState.hashCode() : 0);
        result = 31 * result + (notiContent != null ? notiContent.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (workName != null ? workName.hashCode() : 0);
        result = 31 * result + workId;
        return result;
    }
}
