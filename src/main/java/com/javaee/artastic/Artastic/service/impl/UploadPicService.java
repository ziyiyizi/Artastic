package com.javaee.artastic.Artastic.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.aliyun.oss.model.ObjectPermission;
import com.javaee.artastic.Artastic.dao.ArtdataDao;
import com.javaee.artastic.Artastic.dao.ArtworksDao;
import com.javaee.artastic.Artastic.dao.TagsDao;
import com.javaee.artastic.Artastic.domain.Artworks;
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
//        	String name = ossUtil.uploadImg2Oss(mFile);
//            String imgUrl = ossUtil.getImgUrl(name);
//            value.put("name", name);
//            value.put("imgUrl", imgUrl);

            int artistId = Integer.valueOf(headers.getFirst("userId"));
            String description = mRequest.getParameter("description");
            String title = mRequest.getParameter("title");
            String tags = mRequest.getParameter("tags");
            String folders = mRequest.getParameter("folders");
            
            Artworks artworks = new Artworks();
            artworks.setArtistId(artistId);
            artworks.setArtworkDescription(description);
            artworks.setArtworkDir(folders);
            artworks.setArtworkName(title);
            artworks.setUploadtime(new Timestamp(Calendar.getInstance().getTimeInMillis()));

            Artworks artworks2 = artworksDao.saveAndFlush(artworks);
            System.out.println("id:"+artworks2.getArtworkId());
            System.out.println("title:"+title);
            System.out.println("description:"+description);
            System.out.println("tags:"+tags);
            System.out.println("folders:"+folders);
            
            String[] tagslist = tags.split(",");
            for(String each:tagslist) {
            	System.out.println(each);
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
