package com.javaee.artastic.Artastic.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Arrays;

@Entity
public class Users {
    private int userId;
    private String userName;
    private String userPassword;
    private String userSex;
    private String userAge;
    private String userDescription;
    private String userSoftware;
    private String userJob;
    private String userAddress;
    private String userMail;
    private String userPhone;
    private Timestamp registertime;
    private String userIcon;
    private String userState;
    private String userToken;
    private Timestamp tokenTime;
    private String userSalt;

    @Id@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "User_ID")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "User_name", unique = true)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "User_password")
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Basic
    @Column(name = "User_sex")
    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    @Basic
    @Column(name = "User_age")
    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    @Basic
    @Column(name = "User_description")
    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    @Basic
    @Column(name = "User_software")
    public String getUserSoftware() {
        return userSoftware;
    }

    public void setUserSoftware(String userSoftware) {
        this.userSoftware = userSoftware;
    }

    @Basic
    @Column(name = "User_job")
    public String getUserJob() {
        return userJob;
    }

    public void setUserJob(String userJob) {
        this.userJob = userJob;
    }

    @Basic
    @Column(name = "User_address")
    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Basic
    @Column(name = "User_mail")
    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    @Basic
    @Column(name = "User_phone")
    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Basic
    @Column(name = "registertime")
    public Timestamp getRegistertime() {
        return registertime;
    }

    public void setRegistertime(Timestamp registertime) {
        this.registertime = registertime;
    }

    @Basic
    @Column(name = "User_icon")
    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    @Basic
    @Column(name = "User_state")
    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    @Basic
    @Column(name = "User_token")
    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    @Basic
    @Column(name = "token_time")
    public Timestamp getTokenTime() {
        return tokenTime;
    }

    public void setTokenTime(Timestamp tokenTime) {
        this.tokenTime = tokenTime;
    }

    @Basic
    @Column(name = "User_salt")
    public String getUserSalt() {
        return userSalt;
    }

    public void setUserSalt(String userSalt) {
        this.userSalt = userSalt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users users = (Users) o;

        if (userId != users.userId) return false;
        if (userName != null ? !userName.equals(users.userName) : users.userName != null) return false;
        if (userPassword != null ? !userPassword.equals(users.userPassword) : users.userPassword != null) return false;
        if (userSex != null ? !userSex.equals(users.userSex) : users.userSex != null) return false;
        if (userAge != null ? !userAge.equals(users.userAge) : users.userAge != null) return false;
        if (userDescription != null ? !userDescription.equals(users.userDescription) : users.userDescription != null)
            return false;
        if (userSoftware != null ? !userSoftware.equals(users.userSoftware) : users.userSoftware != null) return false;
        if (userJob != null ? !userJob.equals(users.userJob) : users.userJob != null) return false;
        if (userAddress != null ? !userAddress.equals(users.userAddress) : users.userAddress != null) return false;
        if (userMail != null ? !userMail.equals(users.userMail) : users.userMail != null) return false;
        if (userPhone != null ? !userPhone.equals(users.userPhone) : users.userPhone != null) return false;
        if (registertime != null ? !registertime.equals(users.registertime) : users.registertime != null) return false;
        if (userIcon != null ? !userIcon.equals(users.userIcon) : users.userIcon != null) return false;
        if (userState != null ? !userState.equals(users.userState) : users.userState != null) return false;
        if (userToken != null ? !userToken.equals(users.userToken) : users.userToken != null) return false;
        if (tokenTime != null ? !tokenTime.equals(users.tokenTime) : users.tokenTime != null) return false;
        if (userSalt != null ? !userSalt.equals(users.userSalt) : users.userSalt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userPassword != null ? userPassword.hashCode() : 0);
        result = 31 * result + (userSex != null ? userSex.hashCode() : 0);
        result = 31 * result + (userAge != null ? userAge.hashCode() : 0);
        result = 31 * result + (userDescription != null ? userDescription.hashCode() : 0);
        result = 31 * result + (userSoftware != null ? userSoftware.hashCode() : 0);
        result = 31 * result + (userJob != null ? userJob.hashCode() : 0);
        result = 31 * result + (userAddress != null ? userAddress.hashCode() : 0);
        result = 31 * result + (userMail != null ? userMail.hashCode() : 0);
        result = 31 * result + (userPhone != null ? userPhone.hashCode() : 0);
        result = 31 * result + (registertime != null ? registertime.hashCode() : 0);
        result = 31 * result + (userIcon != null ? userIcon.hashCode() : 0);
        result = 31 * result + (userState != null ? userState.hashCode() : 0);
        result = 31 * result + (userToken != null ? userToken.hashCode() : 0);
        result = 31 * result + (tokenTime != null ? tokenTime.hashCode() : 0);
        result = 31 * result + (userSalt != null ? userSalt.hashCode() : 0);
        return result;
    }
}
