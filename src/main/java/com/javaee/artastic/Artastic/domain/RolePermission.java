package com.javaee.artastic.Artastic.domain;

import javax.persistence.*;

@Entity
@Table(name = "role_permission", schema = "artastic", catalog = "")
@IdClass(RolePermissionPK.class)
public class RolePermission {
    private int roleid;
    private int permissionid;

    @Id
    @Column(name = "roleid")
    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    @Id
    @Column(name = "permissionid")
    public int getPermissionid() {
        return permissionid;
    }

    public void setPermissionid(int permissionid) {
        this.permissionid = permissionid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolePermission that = (RolePermission) o;

        if (roleid != that.roleid) return false;
        if (permissionid != that.permissionid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleid;
        result = 31 * result + permissionid;
        return result;
    }
}
