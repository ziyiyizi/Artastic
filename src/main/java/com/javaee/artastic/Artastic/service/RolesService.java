package com.javaee.artastic.Artastic.service;

import java.util.List;

import com.javaee.artastic.Artastic.domain.Permissions;
import com.javaee.artastic.Artastic.domain.Roles;

public interface RolesService {
	public List<Permissions> findPermissionList(int roleid);
	public List<Permissions> findPermissionList(Roles roles);
}
