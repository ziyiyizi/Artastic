package com.javaee.artastic.Artastic.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaee.artastic.Artastic.domain.ArtWorkDetails;
import com.javaee.artastic.Artastic.domain.ArtworksList;
import com.javaee.artastic.Artastic.domain.UserDetails;
import com.javaee.artastic.Artastic.service.ArtworksService;
import com.javaee.artastic.Artastic.service.UsersService;
import com.javaee.artastic.Artastic.utils.ExceptionUtil;

@Controller
public class SearchController {
	@Autowired
	private ArtworksService artworkService;
	
	@Autowired
	private UsersService usersService;
	
	@RequestMapping(value="/getsearch")
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
}
