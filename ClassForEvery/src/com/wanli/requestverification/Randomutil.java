package com.wanli.requestverification;

/**
 * 生成6位随机验证码
 * @author wanli
 *
 */

public class Randomutil {

	// 获取6位随机验证码
	public static String getRandom() {
		String num = "";
		for (int i = 0; i < 6; i++) {
			num = num + String.valueOf((int) Math.floor(Math.random() * 9 + 1));
		}
		return num;
	}

}
