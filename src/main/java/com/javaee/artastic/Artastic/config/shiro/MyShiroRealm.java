package com.javaee.artastic.Artastic.config.shiro;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import com.javaee.artastic.Artastic.domain.Permissions;
import com.javaee.artastic.Artastic.domain.Roles;
import com.javaee.artastic.Artastic.domain.Users;
import com.javaee.artastic.Artastic.service.RolesService;
import com.javaee.artastic.Artastic.service.UsersService;

@Component
public class MyShiroRealm extends AuthorizingRealm{
	@Resource
	private UsersService usersService;
	
	@Resource
	private RolesService rolesService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String username = (String) principals.getPrimaryPrincipal();
        Users users = usersService.findByUserName(username);
        if(users == null) {
        	return null;
        }
        for (Roles role : usersService.findRoleList(users)) {
            authorizationInfo.addRole(role.getRoleName());
            for (Permissions p: rolesService.findPermissionList(role)) {
                authorizationInfo.addStringPermission(p.getAuthority());
            }
        }
        return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
        String username = (String)token.getPrincipal(); //获取用户名，loginUser-username。
        Users users = usersService.findByUserName(username);

        if (!usersService.isUserActivate(users)) {
            //没有返回登录用户名对应的SimpleAuthenticationInfo对象时,就会在LoginController中抛出UnknownAccountException异常
            return null;
        }
  //验证通过返回一个封装了用户信息的AuthenticationInfo实例即可。
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                username, //用户信息
                users.getUserPassword(),
                getName() //realm name
        );

        return authenticationInfo;

	}
	
}
