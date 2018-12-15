package com.javaee.artastic.Artastic.controller;

import java.sql.Timestamp;
import java.util.List;


import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.javaee.artastic.Artastic.domain.ArtworksList;
import com.javaee.artastic.Artastic.domain.Error;
import com.javaee.artastic.Artastic.domain.Follow;
import com.javaee.artastic.Artastic.domain.Params;
import com.javaee.artastic.Artastic.domain.Roles;
import com.javaee.artastic.Artastic.domain.UserDetails;
import com.javaee.artastic.Artastic.domain.Users;
import com.javaee.artastic.Artastic.service.UsersService;
import com.javaee.artastic.Artastic.utils.ExceptionUtil;


@EnableAutoConfiguration
@RestController
//@RequestMapping(value="/user")

public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	@RequestMapping(value="/user/login",method = RequestMethod.GET)
	public ModelAndView login() {
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="/user/login",method = RequestMethod.POST)
	@ResponseBody
	public Params login(@RequestBody Params param, HttpResponse response, HttpSession session) throws Exception {

        //ModelAndView mView = new ModelAndView("success");
        String username = param.getUsername();
        String pwd = param.getPassword();
        UsernamePasswordToken token = new UsernamePasswordToken(username,pwd);
        token.setRememberMe(true);
        Subject subject = SecurityUtils.getSubject();
        try {
        	subject.login(token);
        	session.setAttribute("user", subject.getPrincipal());
        	Users users = usersService.findByUserName(username);
        	param.setUserId(users.getUserId());
        	System.out.println("登录成功");
        	return param;
        }catch (Exception e) {
			// TODO: handle exception
        	String msg = null;
        	if(e instanceof UnknownAccountException) {
        		System.out.println("账户不存在");
                msg = "账户不存在或密码不正确";
        	} else if(e instanceof IncorrectCredentialsException) {
        		System.out.println("密码不正确");
                msg = "账户不存在或密码不正确";
        	} else {
        		System.out.println("其他异常");
                msg = "其他异常";
			}
        	System.out.println(msg);
//            mView.addObject("msg", msg);	
//            mView.addObject("error", true);
            return null;
		}
        
    }
	
	@RequestMapping(value={"/user/showAll"})
	@RequiresPermissions("user:showAll")
	@ResponseBody
	public List<Users> showAll(){
		List<Users> usersEntities = usersService.findAll();
		return usersEntities;
	}
	
	@RequestMapping(value="followmember")
	@ResponseBody
	public Error followMember(@RequestHeader HttpHeaders headers) {
		Error error = new Error();
		error.setError(false);
		try {
			String artistName = headers.getFirst("present");
			int artistId = usersService.findUserIdByUserName(artistName);
			int followerId = Integer.parseInt(headers.getFirst("userid"));
			if(usersService.isFollow(artistId, followerId) == false) {
				Follow follow = new Follow();
				follow.setArtistId(artistId);
				follow.setFollowerId(followerId);
				follow.setFollowtime(new Timestamp(System.currentTimeMillis()));
				usersService.saveFollow(follow);
				System.out.println("成功关注该作者");
				
				String senderName = headers.getFirst("username");
				usersService.pushNotification(senderName, artistName, "follow", "");
				
			} else {
				System.out.println("已经关注过");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			error.setError(true);
		}
		
		return error;
	}
	
	@RequestMapping(value="getmemberdetail")
	@ResponseBody
	public ArtworksList getMember(@RequestHeader HttpHeaders headers) {
		ArtworksList artworksList = new ArtworksList();
		try {
			String userName = headers.getFirst("username");
			int userId = Integer.parseInt(headers.getFirst("userId"));
			
			Pageable pageable = new PageRequest(0, 10);
			UserDetails userDetails = usersService.findUserDetails(userName, pageable);
			
			boolean isFollow = usersService.isFollow(userDetails.getArtistId(), userId);
			userDetails.setFollow(isFollow);
			
			artworksList.setMember(userDetails);
		} catch (Exception e) {
			// TODO: handle exception
			ExceptionUtil.handleException(e);
		}
		return artworksList;
	}
}
