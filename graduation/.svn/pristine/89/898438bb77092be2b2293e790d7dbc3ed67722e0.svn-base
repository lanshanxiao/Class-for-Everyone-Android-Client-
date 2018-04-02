<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>重置密码</title>
<link rel="stylesheet" type="text/css" href="css/H-ui.css">
<script type="text/javascript" src="javascript/jquery.js"></script>
<script type="text/javascript">
	$(function(){
		$.Huitab("#tab_demo .tabBar span","#tab_demo .tabCon","current","click","0");
		$("#password").focus(function() {
			$("#passwordError").html("6~16个字符，区分大小写").removeClass();
		});
		$("#password").blur(function() {
			checkPassword();
		});
		$("#confirmPassword").focus(function() {
			$("#confirmPasswordError").html("再次输入密码，与前面保持一致").removeClass();
		});
	});
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
	
	//校验输入的密码格式
	function checkPassword() {
		var password = $("#password").val();
		if (password == null || password == '') {
			$("#passwordError").html("密码不能为空").addClass("c-error");
			return false;
		} else {
			if (password.length < 6 || password.length > 16) {
				$("#passwordError").html("密码必须由6~16个字符组成，区分大小写").addClass("c-error");
				return false;
			}
		}
		return true;
	}
	//判断两次密码是否一致
	function checkConfirmPassword() {
		var password = $("#password").val();
		var confirmPassword = $("#confirmPassword").val();
		if (password != confirmPassword) {
			$("#confirmPasswordError").html("两次密码不一致,请重新输入").addClass("c-error");
			return false;
		}
		var parameter = urlSearch();
		var action = $("form").attr('action');
		$("form").attr("action", action + "?parameter=" + parameter + "&password=" + password);
		return true;
	}
	//获取地址栏的参数
	function urlSearch() {
	    var name,value;
	    var str=location.href; //取得整个地址栏
	    var num=str.indexOf("?")
	    str=str.substr(num+1); //取得所有参数   stringvar.substr(start [, length ]
	
	    var arr=str.split("&"); //各个参数放到数组里
	    console.log(arr)
	    for(var i=0;i < arr.length;i++){
	        num=arr[i].indexOf("=");
	        if(num>0){
	             name=arr[i].substring(0,num);
	             value=arr[i].substr(num+1);
	        }
	    }
	    return value;
	}
	
	function check() {
		//验证密码
		if(checkPassword()) {
			//验证确认密码
			return checkConfirmPassword();
		} else {
			return false;
		}
		
		return true;
	}
	
	
</script>
</head>
<body>

	<center>
		<div id="tab_demo" class="HuiTab">
			<div class="tabBar clearfix">
				<span class="f-18">重置密码</span>
			</div>
			<div class="tabCon">
				<form action="${pageContext.request.contextPath}/registUser_resetPasswordSucc.action" method="post" onsubmit="return check();">
					<table style="border-collapse:separate; border-spacing:0px 10px;">
						<thead>
							<tr>
							<td  width="15%"></td>
							<td></td>
						</tr>
						</thead>
						<tr>
							<td class="text-r f-18">输入密码：</td>
							<td><input id="password" type="text" name="password" placeholder="密码" class="input-text radius size-L"/></td>
						</tr>
						<tr>
							<td></td>
							<td id="passwordError">6~16个字符，区分大小写</td>
						</tr>
						<tr>
							<td class="text-r f-18">确认密码：</td>
							<td><input id="confirmPassword" type="text" name="confirmPassword" placeholder="确认密码" class="input-text radius size-L"/></td>
						</tr>
						<tr>
							<td></td>
							<td id="confirmPasswordError">再次输入密码，与前面保持一致</td>
						</tr>
						<tr class="text-l">
							<td></td>
							<td colspan="2"><button type="submit" class="btn btn-primary-outline radius size-L">提交</button></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</center>

</body>
</html>