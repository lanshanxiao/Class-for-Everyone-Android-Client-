package com.wanli.verification;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyPhone {
	
	/**
	 * 国家号码段分配如下：
	 * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
	 * 联通：130、131、132、152、155、156、185、186
	 * 电信：133、153、180、189、（1349卫通）
	 * @param mobiles
	 * @return
	 */
	//验证手机号，注意true表示是手机号，false表示不是手机号
	public static boolean isMobileNO(String mobiles) {  

		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  
		Matcher m = p.matcher(mobiles);  

		return m.matches();  
	}
}
