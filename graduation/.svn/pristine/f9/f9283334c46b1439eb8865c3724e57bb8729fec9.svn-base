<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>找回密码</title>
<link rel="stylesheet" type="text/css" href="css/H-ui.css">
<script type="text/javascript" src="javascript/jquery.js"></script>
<script type="text/javascript">
	var smsVeriCode;//短信验证码
	var tipInfo;//短信找回提示信息
	var emailTipInfo;//邮箱验证码
	var emailVeriCode;//邮箱找回提示信息
	var InterValObj; //timer变量，控制时间 
	var count = 10; //间隔函数，1秒执行 
	var curCount;//当前剩余秒数 
	var $btnType;
	$(function(){
		$.Huitab("#tab_demo .tabBar span","#tab_demo .tabCon","current","click","0");
		$("#phonenum").focus(function() {
			$("#errorTip").html("");
		});
		$("#email").focus(function() {
			$("#errorTip").html("");
		});
		$("#getVeriCode").click(function() {
			checkUsername();
		});
		$("#getEmailVeriCode").click(function() {
			checkEmailExist();
		});
	});
	
	//倒计时程序，发送验证码
	function sendMessage(type) { 
		$btnType = type;
		curCount = count; 
		//设置button效果，开始计时 
		
		$($btnType).attr("disabled", "true"); 
		$($btnType).val(curCount + "秒后可重新发送"); 
		InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次 　  
		//请求后台发送验证码 TODO
		//getVeriCode();
		
	} 
		  
	//timer处理函数 
	function SetRemainTime() { 
		if (curCount == 0) {         
			window.clearInterval(InterValObj);//停止计时器 
			$($btnType).removeAttr("disabled");//启用按钮 
			$($btnType).html("重新发送验证码"); 
		} else { 
			curCount--; 
			$($btnType).html(curCount + "秒后可重新发送"); 
		} 
	}
	
	//选项卡
	jQuery.Huitab = function(tabBar, tabCon, class_name, tabEvent, i) {
		var $tab_menu = $(tabBar);
		// 初始化操作
		$tab_menu.removeClass(class_name);
		$(tabBar).eq(i).addClass(class_name);
		$(tabCon).hide();
		$(tabCon).eq(i).show();

		$tab_menu.bind(tabEvent, function() {
			$tab_menu.removeClass(class_name);
			$(this).addClass(class_name);
			var index = $tab_menu.index(this);
			$(tabCon).hide();
			$(tabCon).eq(index).show()
		})
	}
	
	//发送异步请求获取短信验证码
	function getVeriCode() {
		if (tipInfo == "该手机号已经注册可直接登录") {
			//1.创建异步交互对象
			var xhr = createXmlHttp();
			var phonenum = $("#phonenum").val();
			//2.设置监听
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {
						//$("#nicknameError").html(xhr.responseText);
						smsVeriCode = xhr.responseText;
						smsVeriCode = smsVeriCode.trim();
						sendMessage($("#getVeriCode"));
						alert(smsVeriCode);
					}
				}
			}
			//3.打开链接, 传三个参数，第一个参数指定请求方式，第二个参数指定路径，第三个true表示异步方式
			//添加new Date().getTime()是为了避免浏览器的缓存
			xhr.open("GET","${pageContext.request.contextPath}/registUser_getSmsVeriCode.action?time="+new Date().getTime()+"&username="+phonenum,true);
			//4.发送
			xhr.send(null);
		} else {
			$("#errorTip").html("该手机还未注册，请先注册").addClass("c-error f-14");
			return false;
		}
	}
	
	//校验输入的手机号是否为正确的号码
	function checkPhonenum() {
		//获得电话号码文本框的值
		var phonenum = $("#phonenum").val();
		var reg = /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;
		if (phonenum == null || phonenum == '') {
			$("#errorTip").html("手机号码不能为空").addClass("c-error f-14");
			return false;
		} else {
			var reg = /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;
			if (!reg.test(phonenum)) {
				$("#errorTip").html("请输入正确的11位手机号码").addClass("c-error f-14");
				return false;
			}
		}
		return true;
	}
	
	//发送异步请求校验手机号是否存在
	function checkUsername() {
		//获得电话号码文本框的值
		var phonenum = $("#phonenum").val();
		if (checkPhonenum()) {
			//1.创建异步交互对象
			var xhr = createXmlHttp();
			//2.设置监听
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {
						tipInfo = xhr.responseText;
						tipInfo = tipInfo.replace(/[^\u4e00-\u9fa5]/g,"");
						return getVeriCode();
					}
				}
			}
			//3.打开链接, 传三个参数，第一个参数指定请求方式，第二个参数指定路径，第三个true表示异步方式
			//添加new Date().getTime()是为了避免浏览器的缓存
			xhr.open("GET","${pageContext.request.contextPath}/registUser_findByName.action?time="+new Date().getTime()+"&username="+phonenum,true);
			//4.发送
			xhr.send(null);
		}
	}
	
	//校验输入的邮箱格式是否正确
	function checkEmail() {
		var email = $("#email").val();
		if (email == null || email == '') {
			$("#errorTip").html("邮箱不能为空").addClass("c-error f-14");
			return false;
		} else {
			var emailreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
			if (!emailreg.test(email)) {
				$("#errorTip").html("请输入正确的邮箱").addClass("c-error f-14");
				return false;
			}
		}
		return true;
	}
	
	//校验该邮箱是否已经注册
	function checkEmailExist() {
		var email = $("#email").val();
		if (checkEmail()) {
			//1.创建异步交互对象
			var xhr = createXmlHttp();
			//2.设置监听
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {
						emailTipInfo = xhr.responseText;
						emailTipInfo = emailTipInfo.replace(/[^\u4e00-\u9fa5]/g,"");
						getEmailVeriCode();
					}
				}
			}
			//3.打开链接, 传三个参数，第一个参数指定请求方式，第二个参数指定路径，第三个true表示异步方式
			//添加new Date().getTime()是为了避免浏览器的缓存
			xhr.open("GET","${pageContext.request.contextPath}/registUser_findByEmail.action?time="+new Date().getTime()+"&email="+email,true);
			//4.发送
			xhr.send(null);
		}
	}
	
	//获取邮箱验证码
	function getEmailVeriCode() {
		if (emailTipInfo == "邮箱已经存在") {
			//1.创建异步交互对象
			var xhr = createXmlHttp();
			//2.设置监听
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {
						emailVeriCode = xhr.responseText;
						emailVeriCode = emailVeriCode.trim();
						sendMessage($("#getEmailVeriCode"));
						alert(emailVeriCode);
					}
				}
			}
			//3.打开链接, 传三个参数，第一个参数指定请求方式，第二个参数指定路径，第三个true表示异步方式
			//添加new Date().getTime()是为了避免浏览器的缓存
			xhr.open("GET","${pageContext.request.contextPath}/registUser_getVeriCode.action?time="+new Date().getTime()+"&email="+email,true);
			//4.发送
			xhr.send(null);
		} else {
			$("#errorTip").html("该邮箱未注册，不可用").addClass("c-error f-14");
		}
	}

	
	//异步校验表单，创建XMLHttpRequest类的对象
	function createXmlHttp() {
		var xmlHttp;
		try { //Firefox, Opera8.0+, Safari
			xmlHttp = new XMLHttpRequest();
		} catch(e) {
			try { //Internet Explorer
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch(e) {
				try {
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				} catch(e) {}
			}
		}
		return xmlHttp;
	}
	
	//短信验证表单提交校验
	function smsCheck() {
		var veriCode = $("#veriCode").val();
		//验证手机号码
		if (checkPhonenum()) {
			//验证短信验证码
			if (veriCode == null || veriCode == '') {
				$("#errorTip").html("验证码不能为空").addClass("c-error f-14");
				return false;
			} else {
				if (smsVeriCode != veriCode) {
					$("#errorTip").html("验证码不正确").addClass("c-error f-14");
					return false;
				}
			}
		} else {
			return false;
		}
		var action = $("#smsform").attr('action');
		var phonenum = $("#phonenum").val();
		$("#smsform").attr("action", action + "?phonenum=" + phonenum);
		return true;
	}
	
	//邮箱验证表单提交校验
	function emailCheck() {
		var veriCode = $("#emailVeriCode").val();
		//验证手机号码
		if (checkEmail()) {
			//验证短信验证码
			if (veriCode == null || emailVeriCode == '') {
				$("#errorTip").html("验证码不能为空").addClass("c-error f-14");
				return false;
			} else {
				if (veriCode != emailVeriCode) {
					$("#errorTip").html("验证码不正确").addClass("c-error f-14");
					return false;
				}
			}
		} else {
			return false;
		}
		var action = $("#emailform").attr('action');
		var email = $("#email").val();
		$("#emailform").attr("action", action + "?email=" + email);
		alert($("#emailform").attr('action'));
		return true;
	}
	
</script>
</head>
<body>

	<center>
		<div id="tab_demo" class="HuiTab">
			<div class="tabBar clearfix">
				<span class="f-18">通过手机号找回密码</span><span class="f-18">通过邮箱找回密码</span>
			</div>
			<div class="tabCon">
				<form id="smsform" action="${pageContext.request.contextPath}/registUser_getBackPassword.action" method="post" onsubmit="return smsCheck();">
					<table style="border-collapse:separate; border-spacing:0px 30px;">
						<thead>
							<tr>
								<td  width="20%"></td>
								<td></td>
								<td width="10%"></td>
							</tr>
						</thead>
						<tr>
							<td class="text-r f-18">手机号：</td>
							<td><input id="phonenum" type="text" name="phonenum" placeholder="请输入手机号" class="input-text radius size-L"></input></td>
							<td><button id="getVeriCode" type="button" class="btn btn-primary-outline radius size-L">免费获取短信验证码</button></td>
						</tr>
						<tr>
							<td class="text-r f-18">短信验证码：</td>
							<td><input id="veriCode" type="text" name="veriCode" placeholder="请输入短信验证码" class="input-text radius size-L"></input></td>
						</tr>
						<tr>
							<td></td>
							<td><button type="submit" class="btn btn-primary-outline radius size-L">提交</button></td>
						</tr>
						
					</table>
				</form>
			</div>
			<div class="tabCon">
				<form id="emailform" action="${pageContext.request.contextPath}/registUser_getBackPassword.action" method="post" onsubmit="return emailCheck();">
					<table style="border-collapse:separate; border-spacing:0px 30px;">
						<thead>
							<tr>
								<td  width="20%"></td>
								<td></td>
								<td width="10%"></td>
							</tr>
						</thead>
						<tr>
							<td class="text-r f-18">邮箱：</td>
							<td><input id="email" type="text" name="email" placeholder="请输入注册时的邮箱" class="input-text radius size-L"></input></td>
							<td><button id="getEmailVeriCode" type="button" class="btn btn-primary-outline radius size-L">获取验证码</button></td>
						</tr>
						<tr>
							<td class="text-r f-18">验证码：</td>
							<td><input id="emailVeriCode" type="text" name="veriCode" placeholder="请输入验证码" class="input-text radius size-L"></input></td>
						</tr>
						<tr>
							<td></td>
							<td><button type="submit" class="btn btn-primary-outline radius size-L">提交</button></td>
						</tr>
						
					</table>
				</form>
			</div>
			<p id="errorTip"></p>
		</div>
	</center>
</body>
</html>