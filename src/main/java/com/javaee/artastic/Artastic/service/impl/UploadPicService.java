package com.javaee.artastic.Artastic.service.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.javaee.artastic.Artastic.dao.ArtdataDao;
import com.javaee.artastic.Artastic.dao.ArtworksDao;
import com.javaee.artastic.Artastic.dao.TagsDao;
import com.javaee.artastic.Artastic.dao.UsersDao;
import com.javaee.artastic.Artastic.domain.Artdata;
import com.javaee.artastic.Artastic.domain.Artworks;
import com.javaee.artastic.Artastic.domain.Tags;
import com.javaee.artastic.Artastic.utils.AliyunOSSUtil;

@Service
public class UploadPicService {
	
	@Autowired
	private TagsDao tagsDao;
	
	@Autowired
	private ArtworksDao artworksDao;
	
	@Autowired
	private ArtdataDao artdataDao;
	
	@Autowired
	private UsersDao usersDao;
	
    private AliyunOSSUtil ossUtil = new AliyunOSSUtil();
    
    @Transactional
    public Map<String, Object> uploadIcon(MultipartFile mFile, int userId){
    	Map<String, Object> value = new HashMap<String, Object>();
        value.put("error", true);

        try {

        	if (mFile != null && mFile.getSize() > 0) {
        		ossUtil.setFileDir(ossUtil.getBaseFileDir() + String.valueOf(userId) + "/");
            	String name = ossUtil.uploadImg2Oss(mFile);
                String imgUrl = ossUtil.getImgUrl(name);
                value.put("name", name);
                value.put("imgUrl", imgUrl);
            	usersDao.updateUserIconByUserId(userId, imgUrl);
            }

        } catch (Exception e) {
			// TODO: handle exception
        	value.put("error", false);
        	
		}
        return value;
    }
    
    @Transactional
    public Map<String, Object> uploadFile(HttpServletRequest request, HttpHeaders headers){
    	Map<String, Object> value = new HashMap<String, Object>();
        value.put("error", true);
        MultipartHttpServletRequest mRequest = null;
        MultipartFile mFile = null;
        try {
        	mRequest = (MultipartHttpServletRequest)request;
        	mFile = mRequest.getFile("file");
        	if (mFile == null || mFile.getSize() <= 0) {
                throw new Exception("the pic is not allowed to be empty...");
            }
        	
            int artistId = Integer.valueOf(headers.getFirst("userId"));
            String description = mRequest.getParameter("description");
            String title = mRequest.getParameter("title");
            String tags = mRequest.getParameter("tags");
            String folders = mRequest.getParameter("folders");
            if(title == null || title.equals("")) {
            	title = "unkonwn";
            }
            if(folders != null && !folders.equals("")) {
            	ossUtil.setFileDir(ossUtil.getBaseFileDir() + String.valueOf(artistId) + "/" + folders + "/");
            }else {
            	ossUtil.setFileDir(ossUtil.getBaseFileDir() + String.valueOf(artistId) + "/");
            }
            
        	String name = ossUtil.uploadImg2Oss(mFile);
            String imgUrl = ossUtil.getImgUrl(name);
            value.put("name", name);
            value.put("imgUrl", imgUrl);
            
            Artworks artworks = new Artworks();
            artworks.setArtistId(artistId);
            artworks.setArtworkDescription(description);
            artworks.setArtworkDir(folders);
            artworks.setArtworkName(title);
            artworks.setUploadtime(new Timestamp(System.currentTimeMillis()));

            int artworkId = artworksDao.save(artworks).getArtworkId();
            
            if(tags != null && !tags.equals("")) {
            	String[] tagslist = tags.split(",");
                for(String tagName : tagslist) {
                	Tags tag = new Tags();
                	tag.setArtworkId(artworkId);
                	tag.setTagName(tagName);
                	tagsDao.save(tag);                	
                }
            }
            
            if(imgUrl != null) {
            	Artdata artdata = new Artdata();
            	artdata.setArtworkId(artworkId);
            	artdata.setArtdata(imgUrl);
            	artdataDao.save(artdata);
            }        

        } catch (IOException e) {
            e.printStackTrace();
            value.put("error", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return value; 
    }
}
