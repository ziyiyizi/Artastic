package com.javaee.artastic.Artastic.controller;

import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.javaee.artastic.Artastic.domain.ArtworksList;
import com.javaee.artastic.Artastic.domain.Follow;
import com.javaee.artastic.Artastic.domain.Params;
import com.javaee.artastic.Artastic.domain.UserDetails;
import com.javaee.artastic.Artastic.domain.Users;
import com.javaee.artastic.Artastic.service.UsersService;
import com.javaee.artastic.Artastic.service.impl.UploadPicService;
import com.javaee.artastic.Artastic.utils.ExceptionUtil;


@EnableAutoConfiguration
@RestController

public class UsersController {

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private UploadPicService uploadPicService;
	
	@RequestMapping(value="/user/login",method = RequestMethod.POST)
	@ResponseBody
	public Params login(@RequestBody Params param, HttpSession session) throws Exception {
		
		param.setError(false);
        String username = param.getUsername();
        String pwd = param.getPassword();
        UsernamePasswordToken token = new UsernamePasswordToken(username,pwd,param.isRemember());
        Subject subject = SecurityUtils.getSubject();
        try {
        	subject.login(token);
        	session.setAttribute("user", subject.getPrincipal());
        	Users users = usersService.findByUserName(username);
        	param.setUserId(users.getUserId());
        	param.setIconURL(users.getUserIcon());
        }catch (Exception e) {
			// TODO: handle exception
        	String msg = null;
        	if(e instanceof UnknownAccountException) {
                msg = "Unknown Account";
        	} else if(e instanceof IncorrectCredentialsException) {
                msg = "Incorrect Password";
        	} else {
                msg = "Other Exception";
			}
            param.setError(true);
            param.setErrorMsg(msg);
		}
        return param;
    }
	
	@RequestMapping(value={"/user/showAll"})
	@RequiresPermissions("user:showAll")
	@ResponseBody
	public List<Users> showAll(){
		List<Users> usersEntities = usersService.findAll();
		return usersEntities;
	}
	
	@RequestMapping(value="/followmember")
	@ResponseBody
	public ArtworksList followMember(@RequestHeader HttpHeaders headers) {
		ArtworksList artworksList = new ArtworksList();
		artworksList.setError(false);
		try {
			String artistName = headers.getFirst("present");
			int artistId = usersService.findUserIdByUserName(artistName);
			String userIdStr = headers.getFirst("userid");
			if(userIdStr.equals("null")) {
				artworksList.setError(true);
				artworksList.setErrorMsg("Please sign in first!");
				return artworksList;
			}
			int followerId = Integer.parseInt(userIdStr);
			if(artistId != followerId && usersService.isFollow(artistId, followerId) == false) {
				Follow follow = new Follow();
				follow.setArtistId(artistId);
				follow.setFollowerId(followerId);
				follow.setFollowtime(new Timestamp(System.currentTimeMillis()));
				usersService.saveFollow(follow);
				System.out.println("follow success");
				
				String senderName = headers.getFirst("username");
				usersService.pushNotification(senderName, artistName, "", "follow", "", 0);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			artworksList.setError(true);
		}
		
		return artworksList;
	}
	
	@RequestMapping(value="/getmemberdetail")
	@ResponseBody
	public ArtworksList getMemberDetails(@RequestHeader HttpHeaders headers) {
		ArtworksList artworksList = new ArtworksList();
		try {
			UserDetails userDetails = null;
			
			String present = URLDecoder.decode(headers.getFirst("present"), "UTF-8");
			String[] strings = present.split("/");
			String searchType = strings[1];
			String searchKey = strings[2];
			String userName = null;
			
			if(searchType.equals("member")) {
				userName = searchKey;
			} else {
				userName = headers.getFirst("username");	
			}
			
			if(!userName.equals("null")) {
				Pageable pageable = new PageRequest(0, 20);
				userDetails = usersService.findUserDetails(userName, pageable);
				
				String userIdStr = headers.getFirst("userId");
				boolean isFollow = false;
				if(!userIdStr.equals("null")) {
					int userId = Integer.parseInt(userIdStr);
					isFollow = usersService.isFollow(userDetails.getArtistId(), userId);
					
				}
				userDetails.setFollow(isFollow);
			}

			artworksList.setMember(userDetails);
			
		} catch (Exception e) {
			// TODO: handle exception
			ExceptionUtil.handleException(e);
		}
		return artworksList;
	}
	
	@RequestMapping(value="/getprofile")
	@ResponseBody
	public Users getUserProfile(@RequestHeader HttpHeaders headers) {
		Users users = null;
		try {
			int userId = Integer.parseInt(headers.getFirst("userid"));
			users = usersService.findByUserId(userId);
		} catch (Exception e) {
			// TODO: handle exception
			ExceptionUtil.handleException(e);
		}
		return users;
	}
	
	@RequestMapping(value="/uploadprofile", method=RequestMethod.POST)
	@ResponseBody
	public ArtworksList uploadProfile(HttpServletRequest request, @RequestHeader HttpHeaders headers){
		ArtworksList artworksList = new ArtworksList();
		artworksList.setError(false);
		MultipartHttpServletRequest mRequest = null;
        MultipartFile mFile = null;
        try {
        	mRequest = (MultipartHttpServletRequest)request;
        	
        	String userIdStr = headers.getFirst("userid");
        	if(userIdStr.equals("null")) {
        		artworksList.setError(true);
        		artworksList.setErrorMsg("Please sign in first!");
        		return artworksList;
        	}
        	int userId = Integer.valueOf(userIdStr);
        	String userSex = mRequest.getParameter("userSex");
        	String userDescription = mRequest.getParameter("userDescription");
        	String userMail = mRequest.getParameter("userMail");
        	String userPassword = mRequest.getParameter("userPassword");
        	
        	mFile = mRequest.getFile("file");
        	uploadPicService.uploadIcon(mFile, userId);
        	usersService.updateProfile(userSex, userMail, userPassword, userDescription, userId);
        	
        } catch (Exception e) {
			// TODO: handle exception
        	artworksList.setError(true);
        	
		}
        
		return artworksList;
	}
	
}
