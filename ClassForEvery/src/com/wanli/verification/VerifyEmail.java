package com.wanli.verification;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyEmail {
	
	/**
	 * 判断邮箱是否合法
	 * @param email
	 * @return
	 */
	//验证邮箱，注意true表示是邮箱，false表示不是邮箱
	public static boolean isEmail(String email){ 
		
		if (null==email || "".equals(email)) return false;	
			
			//Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配  
			Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配  
			Matcher m = p.matcher(email);  
		    
			return m.matches();  
		}
}
