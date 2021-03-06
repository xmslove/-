package com.bus.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.UUID;

import com.bus.vo.Constant;
import com.bus.weixin.api.APPTokenAPI;
import com.bus.weixin.api.SnsAPI;
import com.bus.weixin.vo.APPToken;
import com.bus.weixin.vo.jsapiVO;
/**
 * 获取微信jssdk的工具类
 * @author xms
 *
 */
public class WeinXinJSSDKUtil {
	
	public static String accessToken = null;
	
	
	public static JsSDKVO getJsSDK(String url){
		APPToken tokens = APPTokenAPI.token(Constant.WEIXIN_APPID,Constant.WEIXIN_SECRET);
		accessToken = tokens.getAccess_token();
		jsapiVO jsapi =  SnsAPI.getjsapi(accessToken);
		String jsapi_ticket = jsapi.getTicket(); 
	   String nonce_str = create_nonce_str();  
	   String timestamp = create_timestamp();  
	   String string1;  
	   String signature = ""; 
	   //注意这里参数名必须全部小写，且必须有序  
	   string1 ="jsapi_ticket="+jsapi_ticket+
	   		 "&noncestr="+nonce_str +
	            "&timestamp=" +timestamp+
	            "&url=" + url; 
	   System.out.println("string1="+string1);  
	   try  
	   {  
	       MessageDigest crypt = MessageDigest.getInstance("SHA-1");  
	       crypt.reset();  
	       crypt.update(string1.getBytes("UTF-8"));  
	       signature = byteToHex(crypt.digest());  
	   }  
	   catch (NoSuchAlgorithmException e)  
	   {  
	       e.printStackTrace();  
	   }  
	   catch (UnsupportedEncodingException e)  
	   {  
	       e.printStackTrace();  
	   }  
	   
	   JsSDKVO vo = new JsSDKVO();
	   vo.setUrl(url);
	   vo.setJsapi_ticket(jsapi_ticket);
	   vo.setNonceStr(nonce_str);
	   vo.setTimestamp(timestamp);
	   vo.setSignature(signature);
	   return vo;
	}
	
	/** 
	 * 随机加密 
	 * @param hash 
	 * @return 
	 */  
	private static String byteToHex(final byte[] hash) {  
	    Formatter formatter = new Formatter();  
	    for (byte b : hash)  
	    {  
	        formatter.format("%02x", b);  
	    }  
	    String result = formatter.toString();  
	    formatter.close();  
	    return result;  
	}  


	/** 
	 * 产生随机串--由程序自己随机产生 
	 * @return 
	 */  
	private static String create_nonce_str() {  
	    return UUID.randomUUID().toString();  
	}  

	/** 
	 * 由程序自己获取当前时间 
	 * @return 
	 */  
	private static String create_timestamp() {  
	    return Long.toString(System.currentTimeMillis() / 1000);  
	}

}
