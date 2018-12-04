package com.javaee.artastic.Artastic.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.javaee.artastic.Artastic.utils.AliyunOSSUtil;

@Service
public class UploadPicService {

    private AliyunOSSUtil ossUtil = new AliyunOSSUtil();
	
    public Map<String, Object> uploadFile(HttpServletRequest request){
    	Map<String, Object> value = new HashMap<String, Object>();
        value.put("success", true);
        value.put("errorCode", 0);
        value.put("errorMsg", "");
        MultipartHttpServletRequest mRequest = null;
        MultipartFile mFile = null;
        try {
        	mRequest = (MultipartHttpServletRequest)request;
        	mFile = mRequest.getFile("file");
        	if (mFile == null || mFile.getSize() <= 0) {
                throw new Exception("图片不能为空");
            }
        	String name = ossUtil.uploadImg2Oss(mFile);
            String imgUrl = ossUtil.getImgUrl(name);
            value.put("name", name);
            value.put("imgUrl", imgUrl);
        } catch (IOException e) {
            e.printStackTrace();
            value.put("success", false);
            value.put("errorCode", 200);
            value.put("errorMsg", "图片上传失败");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value; 
    }
}
