package com.javaee.artastic.Artastic.utils;

import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.UUID;
  
/** 
 * 采用MD5加密解密 
 * @datetime 2018-10-13 
 */  
public class Md5Util {  
  
    /*** 
     * MD5加码 生成32位md5码 
     */  
    public static String string2MD5(String inStr){  
        MessageDigest md5 = null;  
        try{  
            md5 = MessageDigest.getInstance("MD5");  
        }catch (Exception e){  
            System.out.println(e.toString());  
            e.printStackTrace();  
            return "";  
        }  
        char[] charArray = inStr.toCharArray();  
        byte[] byteArray = new byte[charArray.length];  
  
        for (int i = 0; i < charArray.length; i++)  
            byteArray[i] = (byte) charArray[i];  
        byte[] md5Bytes = md5.digest(byteArray);  
        StringBuffer hexValue = new StringBuffer();  
        for (int i = 0; i < md5Bytes.length; i++){  
            int val = ((int) md5Bytes[i]) & 0xff;  
            if (val < 16)  
                hexValue.append("0");  
            hexValue.append(Integer.toHexString(val));  
        }  
        return hexValue.toString();  
  
    }  
  
    /** 
     * 加密解密算法 执行一次加密，两次解密 
     */   
    public static String convertMD5(String inStr){  
  
        char[] a = inStr.toCharArray();  
        for (int i = 0; i < a.length; i++){  
            a[i] = (char) (a[i] ^ 't');  
        }  
        String s = new String(a);  
        return s;  
  
    }  
  
    // 测试主函数  
    public static void main(String[] args) {  
        String token = "9e3ba187e65b02aa01afbe01bc6d7a8a";
        String tokenTime = "088fd6e05a521ecbd1b5c476ef1a7a14";
        String userId = "1234567895";
        int userId2 = 1234567895;
//        System.out.println("原始:" + token);  
//        System.out.println("MD5后:" + string2MD5(token));  
//        System.out.println("原始:" + tokenTime);  
//        System.out.println("MD5后:" + string2MD5(tokenTime));
//        System.out.println("原始:" + userId);  
//        System.out.println("MD5后:" + string2MD5(userId));
        
        System.out.println("加密的:" + convertMD5(userId));  
        System.out.println("加密的:" + convertMD5(token));  
        System.out.println("加密的:" + convertMD5(tokenTime));  
        System.out.println("解密的:" + convertMD5(convertMD5(userId))); 
        
        System.out.println("原始:" + userId2);  
        System.out.println("MD5后:" + string2MD5(String.valueOf(userId2)));
        
        System.out.println("加密的:" + convertMD5(String.valueOf(userId2)));  
        System.out.println("解密的:" + convertMD5(convertMD5(String.valueOf(userId2)))); 
 
    }  
}  
