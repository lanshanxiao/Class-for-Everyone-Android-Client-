package com.wanli.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 邮件发送的工具类
 * 
 * @author wanli
 *
 */
public class MailUtils {

	/**
	 * 发送邮件的方法
	 * 
	 * @param to
	 *            :收件人
	 * @param code
	 *            :验证码
	 */
	public static void sendMail(String to, String code) {
		/**
		 * 1.获得一个Session对象. 2.创建一个代表邮件的对象Message. 3.发送邮件Transport
		 */
		// 1.获得连接对象
		// 登录官方帐号，用来给注册的邮箱发送验证码
		String userName = "hwl123me@163.com";
		String userPwd = "hlq0830wb";
		//配置发送邮件的属性
		Properties props = new Properties();
		//配置发送服务器，使用网易的163服务器
		props.setProperty("mail.smtp.host", "smtp.163.com");
		//验证官方帐号的用户名和密码
		props.put("mail.smtp.auth", "true"); 
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, userPwd);
			}

		});
		// 2.创建邮件对象:
		Message message = new MimeMessage(session);
		try {
			// 设置发件人:
			message.setFrom(new InternetAddress("hwl123me@163.com"));
			// 设置收件人:RecipientType.TO,抄送R:ecipientType.CC 密送:RecipientType.BCC
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			// 设置标题
			message.setSubject("来自ClassForEveryone官方注册邮件");
			// 设置邮件正文:
			message.setContent(
					"<span><font size='16' face='verdana' color='#00FFFF'>Hello.</font></span>"
							+ "<span><font face='verdana' color='#0000FF'>"+ to +"</font></span>"
							+ "<p>您注册ClassForEveryone的验证码是：</p>"
							+ "<h1>"+ code +"</h1>",
					"text/html;charset=UTF-8");
			// 3.发送邮件:
			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

}

