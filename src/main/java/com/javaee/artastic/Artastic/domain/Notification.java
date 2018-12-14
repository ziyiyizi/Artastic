package com.javaee.artastic.Artastic.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@IdClass(NotificationPK.class)
public class Notification {
    private int notiId;
    private String senderName;
    private String receiverName;
    private Timestamp notiTime;
    private String notiState;
    private String notiContent;

    @Id
    @Column(name = "Noti_ID")
    public int getNotiId() {
        return notiId;
    }

    public void setNotiId(int notiId) {
        this.notiId = notiId;
    }

    @Id
    @Column(name = "Sender_Name")
    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    @Id
    @Column(name = "Receiver_Name")
    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    @Id
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
        return result;
    }
}
