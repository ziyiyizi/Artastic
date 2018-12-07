package com.javaee.artastic.Artastic.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class UserRolePK implements Serializable {
    private int userid;
    private int roleid;

    @Column(name = "userid")
    @Id
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Column(name = "roleid")
    @Id
    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRolePK that = (UserRolePK) o;

        if (userid != that.userid) return false;
        if (roleid != that.roleid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userid;
        result = 31 * result + roleid;
        return result;
    }
}
