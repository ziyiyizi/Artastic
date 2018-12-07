package com.javaee.artastic.Artastic.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.javaee.artastic.Artastic.domain.Permissions;

@Component
public interface PermissionsDao extends JpaRepository<Permissions, Long>{

	@Query("select u from Permissions u, RolePermission ru where u.pid = ru.permissionid and ru.roleid = :roleid")
	List<Permissions> findPermissionByRole(@Param("roleid")int roleid);
}
