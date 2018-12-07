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
	
    private AliyunOSSUtil ossUtil = new AliyunOSSUtil();
    
    @Transactional
    public Map<String, Object> uploadFile(HttpServletRequest request, HttpHeaders headers){
    	Map<String, Object> value = new HashMap<String, Object>();
        value.put("success", true);
        MultipartHttpServletRequest mRequest = null;
        MultipartFile mFile = null;
        try {
        	mRequest = (MultipartHttpServletRequest)request;
        	mFile = mRequest.getFile("file");
        	if (mFile == null || mFile.getSize() <= 0) {
                throw new Exception("图片不能为空");
            }
        	
            int artistId = Integer.valueOf(headers.getFirst("userId"));
            String description = mRequest.getParameter("description");
            String title = mRequest.getParameter("title");
            String tags = mRequest.getParameter("tags");
            String folders = mRequest.getParameter("folders");
            if(title == null) {
            	title = "unkonwn";
            }
            
            ossUtil.setFileDir(String.valueOf(artistId) + "/" + folders + "/");
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
            System.out.println(String.valueOf(artworkId));
            
//            if(true) {
//            	throw new RuntimeException("test exception");
//            }
            
            if(tags != null) {
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
            value.put("success", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return value; 
    }
}
