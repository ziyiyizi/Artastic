package com.javaee.artastic.Artastic.service.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

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
import com.javaee.artastic.Artastic.domain.ArtworksList;
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
    public ArtworksList uploadIcon(MultipartFile mFile, int userId){
    	ArtworksList artworksList = new ArtworksList();
        artworksList.setError(false);

        try {

        	if (mFile != null && mFile.getSize() > 0) {
        		ossUtil.setFileDir(ossUtil.getBaseFileDir() + String.valueOf(userId) + "/");
            	String name = ossUtil.uploadImg2Oss(mFile);
                String imgUrl = ossUtil.getImgUrl(name);
            	usersDao.updateUserIconByUserId(userId, imgUrl);
            }

        } catch (Exception e) {
			// TODO: handle exception
        	artworksList.setError(true);
        	
		}
        return artworksList;
    }
    
    @Transactional
    public ArtworksList uploadFile(HttpServletRequest request, HttpHeaders headers){
    	ArtworksList artworksList = new ArtworksList();
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

            Artworks artworks = new Artworks();
            artworks.setArtistId(artistId);
            artworks.setArtworkDescription(description);
            artworks.setArtworkDir(folders);
            artworks.setArtworkName(title);
            artworks.setUploadtime(new Timestamp(System.currentTimeMillis()));

            int artworkId = artworksDao.save(artworks).getArtworkId();
            
            if(imgUrl != null) {
            	Artdata artdata = new Artdata();
            	artdata.setArtworkId(artworkId);
            	artdata.setArtdata(imgUrl);
            	artdataDao.save(artdata);
            }   
            
            if(tags != null && !tags.equals("")) {
            	String[] tagslist = tags.split(",");
            	Set<String> tagset = new HashSet<>();
            	for(String tagName : tagslist) {
                	tagset.add(tagName);         	
                }
                for(String tagName2 : tagset) {
                	Tags tag = new Tags();
                	tag.setArtworkId(artworkId);
                	tag.setTagName(tagName2);
                	tagsDao.save(tag);                	
                }
            }          

        } catch (IOException e) {
            artworksList.setError(true);
        } catch (Exception e) {
        	artworksList.setError(true);
        }
        
        return artworksList; 
    }
}
