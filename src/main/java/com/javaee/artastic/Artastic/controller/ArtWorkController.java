package com.javaee.artastic.Artastic.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.javaee.artastic.Artastic.domain.ArtWorkDetails;
import com.javaee.artastic.Artastic.domain.ArtWorkLikes;
import com.javaee.artastic.Artastic.domain.ArtworksList;
import com.javaee.artastic.Artastic.domain.ChartData;
import com.javaee.artastic.Artastic.domain.Clicks;
import com.javaee.artastic.Artastic.domain.Comments;
import com.javaee.artastic.Artastic.domain.Likes;
import com.javaee.artastic.Artastic.domain.UserDetails;
import com.javaee.artastic.Artastic.service.ArtworksService;
import com.javaee.artastic.Artastic.service.UsersService;
import com.javaee.artastic.Artastic.utils.ExceptionUtil;

import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class ArtWorkController {
	@Autowired
	private ArtworksService artworkService;
	
	@Autowired
	private UsersService usersService;
	
	@RequestMapping(value="/community/{postType}")
	@ResponseBody
	public ModelAndView showArtwork(@PathVariable("postType")String postType) {
		return new ModelAndView("community");
	}
	
	@RequestMapping(value="/lab/{postType}")
	@ResponseBody
	public ModelAndView showLab(@PathVariable("postType")String postType) {
		return new ModelAndView("community");
	}
	
	@RequestMapping(value="search/{searchType}/{searchKey}")
	@ResponseBody
	public ModelAndView search(@PathVariable("searchType")String searchType, @PathVariable("searchKey")String searchKey) {
		return new ModelAndView("community");
	}
	
	@RequestMapping(value="/getPosts")
	@ResponseBody
	public ArtworksList getArtWorksAll(@RequestHeader HttpHeaders headers) {
		ArtworksList artworksList = new ArtworksList();
		
		try {
			
			int pageNo = 0;
			String pageStr = headers.getFirst("page");
			if(pageStr != null && !pageStr.equals("")) {
				pageNo = Integer.parseInt(pageStr);
			}
			
			Pageable pageable = new PageRequest(pageNo, 10);
			Page<Integer> page = null;
			String typeSort = headers.getFirst("present");
			String userIdStr = headers.getFirst("userId");
			
			if(typeSort.equals("popular")) {
				page = artworkService.findAllLikeSort(pageable);
			} else if (typeSort.equals("latest")) {
				page = artworkService.findAllTimeSort(pageable);
			} else if (typeSort.equals("feed")) {
				int clientId = Integer.parseInt(userIdStr);
				page = artworkService.findFollowArtworks(clientId, pageable);
			} else if (typeSort.equals("mylikes")) {
				//page = artworkService.findAllRandSort(pageable);
				int userId = Integer.parseInt(userIdStr);
				page = artworkService.findUserLikes(userId, pageable);
			} else if (typeSort.equals("tweet")) {
				page = artworkService.findAllCommentSort(pageable);
			} else {
				page = artworkService.findAllRandSort(pageable);
			}
			
			List<ArtWorkDetails> artWorkDetails = new ArrayList<>();
			List<Integer> artworkIds = page.getContent();
			
			if(userIdStr.equals("null")) {
				for(Integer integer : artworkIds) {
					artWorkDetails.add(artworkService.getArtworkDetails(integer));
				}
			} else {
				int clientId = Integer.parseInt(userIdStr);
				for(Integer integer : artworkIds) {
					artWorkDetails.add(artworkService.getArtworkDetails(integer, clientId));
				}
			}

			artworksList.setPosts(artWorkDetails);
			
		} catch (Exception e) {
			// TODO: handle exception
			ExceptionUtil.handleException(e);
		}
		return artworksList;
	}
	
	@RequestMapping(value="/post/{postId}")
	@ResponseBody
	public ModelAndView Post(@PathVariable("postId")String postId) {
		return new ModelAndView("community");
	}
	
	@RequestMapping(value="/getpost")
	@ResponseBody
	public ArtworksList getArtworkOne(@RequestHeader HttpHeaders headers) {
		ArtworksList artworksList = new ArtworksList();
		try {
			System.out.println(headers.getFirst("present"));
			int artworkId = Integer.parseInt(headers.getFirst("present"));
			String userIdStr = headers.getFirst("userId");
			ArtWorkDetails artWorkDetails = null;
			if(userIdStr.equals("null")) {
				artWorkDetails = artworkService.getArtworkDetails(artworkId);
			} else {
				int clientId = Integer.parseInt(userIdStr);				
				artWorkDetails = artworkService.getArtworkDetails(artworkId, clientId);
			}
			artworksList.setPost(artWorkDetails);
		} catch (Exception e) {
			// TODO: handle exception
			ExceptionUtil.handleException(e);
		}
		return artworksList;
	}
	
	@RequestMapping(value="/getlikelistandcomments")
	@ResponseBody
	public ArtWorkLikes getlikelistandcomments(@RequestHeader HttpHeaders headers) {
		ArtWorkLikes artWorkLikes = new ArtWorkLikes();
		try {
			int artworkId = Integer.valueOf(headers.getFirst("artworkid"));
			String userIdStr = headers.getFirst("userId");
			int userId = 114514;
			if(!userIdStr.equals("null")) {
				userId = Integer.valueOf(userIdStr);
			}
			Clicks clicks = new Clicks();
			clicks.setArtworkId(artworkId);
			clicks.setUserId(userId);
			clicks.setClicktime(new Timestamp(System.currentTimeMillis()));
			artworkService.saveClick(clicks);
			artWorkLikes = artworkService.getArtworkLikes(artworkId);
		} catch (Exception e) {
			// TODO: handle exception
			ExceptionUtil.handleException(e);
		}
		
		return artWorkLikes;
	}
	
	@RequestMapping(value="/likerequest")
	@ResponseBody
	public ArtworksList addLikes(@RequestHeader HttpHeaders headers) {
		ArtworksList artworksList = new ArtworksList();
		artworksList.setError(false);
		try {
			int userId = Integer.valueOf(headers.getFirst("userid"));
			int artworkId = Integer.valueOf(headers.getFirst("present"));
			
			if(artworkService.isLike(userId, artworkId) == false) {
				Likes likes = new Likes();
				likes.setArtworkId(artworkId);
				likes.setUserId(userId);
				likes.setLiketime(new Timestamp(System.currentTimeMillis()));
				artworkService.saveLike(likes);
				String senderName = headers.getFirst("username");
				String receiverName = usersService.findNameByWorkId(artworkId);
				String workName = artworkService.findNameByworkId(artworkId);
				usersService.pushNotification(senderName, receiverName, workName, "like", "", artworkId);
				System.out.println("已添加喜欢");
			} else {
				System.out.println("已喜欢过该作品");
			}
			
		}catch (Exception e) {
			artworksList.setError(true);
		}
		
		return artworksList;
	}
	
	@RequestMapping(value="makecomment")
	public ArtworksList makeComment(HttpServletRequest request, @RequestHeader HttpHeaders headers) {
		ArtworksList artworksList = new ArtworksList();
		artworksList.setError(false);
		try {
			String commentorName = headers.getFirst("username");
			
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest)request;
			String responseTo = mRequest.getParameter("responseTo");
			String comment = mRequest.getParameter("comment");
			if(comment == null || comment.equals("")) {
				artworksList.setError(true);
				return artworksList;
			}
			int artworkId = Integer.parseInt(mRequest.getParameter("artworkId"));
			
			Comments comments = new Comments();
			comments.setArtworkId(artworkId);
			comments.setComment(comment);
			comments.setCommentorName(commentorName);
			comments.setUserName(responseTo);
			comments.setCommentTime(new Timestamp(System.currentTimeMillis()));
			artworkService.saveComment(comments);
			
			String workName = artworkService.findNameByworkId(artworkId);
			if(responseTo == null || responseTo.equals("")) {
				String receiverName = usersService.findNameByWorkId(artworkId);
				usersService.pushNotification(commentorName, receiverName, workName, "comment", comment, artworkId);
			} else {
				usersService.pushNotification(commentorName, responseTo, workName, "comment2", comment, artworkId);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			artworksList.setError(true);
		}
		return artworksList;
	}
	
	@RequestMapping(value="getsearch")
	@ResponseBody
	public ArtworksList searchArtworks(@RequestHeader HttpHeaders headers) {
		
		ArtworksList artworksList = new ArtworksList();
		try {
			
			String present = URLDecoder.decode(headers.getFirst("present"), "UTF-8");
			String userIdStr = headers.getFirst("userId");
			int pageNo = 0;
			String pageStr = headers.getFirst("page");
			if(pageStr != null && !pageStr.equals("")) {
				pageNo = Integer.parseInt(pageStr);
			}
			String[] strings = present.split("/");
			String searchType = strings[1];
			String searchKey = strings[2];
			Pageable pageable = new PageRequest(pageNo, 12);
			Page<Integer> page = null;
			
			if(searchType.equals("member")) {
				
				List<UserDetails> members = usersService.findUsers(searchKey, pageable);
				artworksList.setMembers(members);
				return artworksList;
				
			} else if(searchType.equals("thismember")){
				page = artworkService.findByArtistNameEX(searchKey, pageable);
			} else if(searchType.equals("thistag")){
				page = artworkService.findByTagNameEX(searchKey, pageable);
			} else if(searchType.equals("tag")){
				page = artworkService.findByTagName(searchKey, pageable);	
			} else {
				page = artworkService.findBySearchKey(searchKey, pageable);
//				Page<Integer> page = artworkService.findBySearchAll(searchKey, pageable);	
			}
			
			List<ArtWorkDetails> artWorkDetails = new ArrayList<>();
			List<Integer> artworkIds = page.getContent();
			if(userIdStr.equals("null")) {
				for(Integer integer : artworkIds) {
					artWorkDetails.add(artworkService.getArtworkDetails(integer));
				}
			} else {
				int clientId = Integer.parseInt(userIdStr);
				for(Integer integer : artworkIds) {
					artWorkDetails.add(artworkService.getArtworkDetails(integer, clientId));
				}
			}
			artworksList.setPosts(artWorkDetails);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExceptionUtil.handleException(e);
		}
		
		return artworksList;
		
	}
	
	@RequestMapping(value="getpostchart")
	@ResponseBody
	public ArtworksList getPostChart(@RequestHeader HttpHeaders headers) {
		
		ArtworksList artworksList = new ArtworksList();
		try {
			int artworkId = Integer.parseInt(headers.getFirst("present").split("/")[3]);
			List<Object[]> datalist = artworkService.countClicksPerMonth(artworkId);
			List<Object[]> datalist2 = artworkService.countClicksBySex(artworkId);
			ChartData chartData = new ChartData();
			chartData.setData1(datalist);
			chartData.setData2(datalist2);
			artworksList.setChartdata(chartData);
			
		} catch (Exception e) {
			// TODO: handle exception
			ExceptionUtil.handleException(e);
		}
		
		return artworksList;
	}
	
	@RequestMapping(value="getnotification")
	@ResponseBody
	public ArtworksList pullNotification(@RequestHeader HttpHeaders headers){
		ArtworksList artworksList = new ArtworksList();
		try {
			String receiverName = headers.getFirst("username");
			if(!receiverName.equals("null")) {
				artworksList.setNotification(usersService.findByReceiverName(receiverName));
				usersService.updateNotification(receiverName);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			ExceptionUtil.handleException(e);
		}
		
		return artworksList;
	}
	
	@RequestMapping(value="fetchnotification")
	@ResponseBody
	public ArtworksList fetchNotification(@RequestHeader HttpHeaders headers) {
		ArtworksList artworksList = new ArtworksList();
		try {
			String receiverName = headers.getFirst("username");
			if(!receiverName.equals("null")) {
				artworksList.setNotifyNum(usersService.countNotifyNum(receiverName));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			ExceptionUtil.handleException(e);
		}
		
		return artworksList;
	}
	
}
