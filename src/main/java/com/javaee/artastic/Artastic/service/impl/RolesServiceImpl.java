package com.javaee.artastic.Artastic.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.javaee.artastic.Artastic.dao.PermissionsDao;
import com.javaee.artastic.Artastic.domain.Permissions;
import com.javaee.artastic.Artastic.domain.Roles;
import com.javaee.artastic.Artastic.service.RolesService;

@Service
public class RolesServiceImpl implements RolesService{

	@Resource
	private PermissionsDao permissionsDao;
	
	@Override
	public List<Permissions> findPermissionList(int roleid){
		return permissionsDao.findPermissionByRole(roleid);
	}

	@Override
	public List<Permissions> findPermissionList(Roles roles) {
		// TODO Auto-generated method stub
		int roleid = roles.getRoleId();
		return permissionsDao.findPermissionByRole(roleid);
	}
	
}
