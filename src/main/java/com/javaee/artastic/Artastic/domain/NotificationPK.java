package com.javaee.artastic.Artastic.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

public class NotificationPK implements Serializable {
    private int notiId;
    private String senderName;
    private String receiverName;
    private Timestamp notiTime;

    @Column(name = "Noti_ID")
    @Id
    public int getNotiId() {
        return notiId;
    }

    public void setNotiId(int notiId) {
        this.notiId = notiId;
    }

    @Column(name = "Sender_Name")
    @Id
    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    @Column(name = "Receiver_Name")
    @Id
    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    @Column(name = "Noti_Time")
    @Id
    public Timestamp getNotiTime() {
        return notiTime;
    }

    public void setNotiTime(Timestamp notiTime) {
        this.notiTime = notiTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotificationPK that = (NotificationPK) o;

        if (notiId != that.notiId) return false;
        if (senderName != null ? !senderName.equals(that.senderName) : that.senderName != null) return false;
        if (receiverName != null ? !receiverName.equals(that.receiverName) : that.receiverName != null) return false;
        if (notiTime != null ? !notiTime.equals(that.notiTime) : that.notiTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = notiId;
        result = 31 * result + (senderName != null ? senderName.hashCode() : 0);
        result = 31 * result + (receiverName != null ? receiverName.hashCode() : 0);
        result = 31 * result + (notiTime != null ? notiTime.hashCode() : 0);
        return result;
    }
}
