package com.javaee.artastic.Artastic.domain;

import javax.persistence.*;

@Entity
@Table(name = "user_role", schema = "artastic", catalog = "")
@IdClass(UserRolePK.class)
public class UserRole {
    private int userid;
    private int roleid;

    @Id
    @Column(name = "userid")
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Id
    @Column(name = "roleid")
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

        UserRole userRole = (UserRole) o;

        if (userid != userRole.userid) return false;
        if (roleid != userRole.roleid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userid;
        result = 31 * result + roleid;
        return result;
    }
}
