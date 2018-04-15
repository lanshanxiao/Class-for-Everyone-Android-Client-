package com.wanli.page;

import java.io.OutputStream;

import com.wanli.classforevery.R;
import com.wanli.socket.ConnectionSocket;
import com.wanli.socket.GetWiFiGateway;
import com.wanli.verification.VerifyEmail;
import com.wanli.verification.VerifyPhone;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

//登录界面活动
public class Login extends Activity {
	
	//选中记住密码后存储账号和密码的String
	private String userNameValue;
	private String passwordValue;
	
	//登录界面上控件
	private EditText lAccount;//1.账号
	private EditText lPassword;//2.密码
	private CheckBox lRememberPwd;//3.记住密码
	private TextView lChangePwd;//4.修改密码
	private Button lBtnLogin;//5.登录
	private Button lBtnRegister;//6.注册
	private ToggleButton lTogglePwd;//7.显示密码
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		
		//通过id找到相应的控件
		lAccount = (EditText) findViewById(R.id.userEditText);//1
		lPassword = (EditText) findViewById(R.id.pwdEditText);//2
		lRememberPwd = (CheckBox) findViewById(R.id.Login_Remember);//3
		lChangePwd = (TextView) findViewById(R.id.login_text_change_pwd);//4
		lBtnLogin = (Button) findViewById(R.id.bnLogin);//5
		lBtnRegister = (Button) findViewById(R.id.bnRegister);//6
		lTogglePwd = (ToggleButton) findViewById(R.id.loginTogglePwd);//7
		
		//获取WiFi网关IP地址
		GetWiFiGateway getWiFiGateway = new GetWiFiGateway(this);
		String gateway = getWiFiGateway.getGateway();
		
//输出获取的Gateway的IP地址
Toast.makeText(this, gateway, Toast.LENGTH_SHORT).show();

		//创建连接服务端的线程
		new Thread(new ConnectionSocket(gateway)).start();
		
		//第一次运行该软件，设置CheckBox
		firstRunAPP();
		
		//为按钮注册监听器
		lBtnLogin.setOnClickListener(lListener);
		lBtnRegister.setOnClickListener(lListener);
		lTogglePwd.setOnClickListener(lListener);
		lRememberPwd.setOnClickListener(lListener);
	}
	
	//不同按钮按下的监听事件选择
	private OnClickListener lListener = new OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.bnLogin:
				login();
				break;
				
			case R.id.bnRegister:
				register();
				break;
				
			case R.id.Login_Remember:
				remember_password();
				break;
				
			case R.id.login_text_change_pwd:
				break;
			
			case R.id.loginTogglePwd:
				if(lTogglePwd.isChecked()){
					/* 显示密码 */
					lPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
				} else{
		    	   /* 隐藏密码 */
					lPassword.setTransformationMethod(PasswordTransformationMethod.getInstance()); 
		        }
				break;
				
			}
		}
	};
	
	//登录
	private void login() {
		//校验账号和密码
		if (checkUsernameAndPwd()) {
			String userName = getAccount();
			String userPassword = getPassword();
			
			try {
				//使用“严苛模式”的线程策略，监控线程中的操作
				StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
		        StrictMode.setThreadPolicy(policy);

		        String sendLoginData = "2," + userName + "," + userPassword + "\r\n";
		      //将账号密码传到服务端
				ConnectionSocket.os.write((sendLoginData).getBytes());
				ConnectionSocket.os.flush(); 
//输出发送到服务端的账号密码
Toast.makeText(this, sendLoginData, Toast.LENGTH_SHORT).show();
			} catch (Exception e) {
				e.printStackTrace();
				Log.i("ioexception2", "ioexception1");
			}
		}
	}
	
	//校验账号和密码
	private boolean checkUsernameAndPwd() {
		
		String userName = getAccount();
		String userPassword = getPassword();
		
		if (userName.equals("")) { //账号为空
			Toast.makeText(this, getString(R.string.account_empty), Toast.LENGTH_SHORT).show();
					
			return false;
		}  else if ( !verifyAccount(userName) ) { //账号格式错误
			lAccount.setText("");
			Toast.makeText(this, getString(R.string.account_wrong), Toast.LENGTH_SHORT).show();
					
			return false;
		}  else if (userPassword.equals("")) { //密码为空
			Toast.makeText(this, getString(R.string.password_empty), Toast.LENGTH_SHORT).show();
					
			return false;
		} else if (userPassword.length() < 6 || userPassword.length() > 16) { //密码长度错误
			lPassword.setText("");
			Toast.makeText(this, getString(R.string.password_length_wrong), Toast.LENGTH_SHORT).show();
			
			return false;
		}
				
		return true;
	}
	
	//获取当前登录界面的账号和密码
	private String getAccount() {
		return lAccount.getText().toString().trim();
	}
	private String getPassword() {
		return lPassword.getText().toString().trim();
	}
	
	//点击注册按钮触发的事件
	private void register() {
		Intent intent = new Intent(Login.this, Register.class);
		startActivity(intent);
		finish();
	}
	
	//记住登录账号和密码
	private void remember_password() {
		SharedPreferences userInformation = getSharedPreferences("remember_password", 0);
		
		if (lRememberPwd.isChecked()) {
			userInformation.edit().putString("judgeCheckBox", "yes")
						   .putString("userName", getAccount())
						   .putString("password", getPassword())
						   .commit();
			Toast.makeText(Login.this, "记住账号和密码", Toast.LENGTH_SHORT).show();
		} else {
			userInformation.edit().putString("judgeCheckBox", "no")
						   .putString("userName", "")
						   .putString("password", "")
						   .commit();
			Toast.makeText(Login.this, "不记住账号和密码", Toast.LENGTH_SHORT).show();
		}
	}
	
	/**
	 * 第一次运行该软件
	 * 设置CheckBox
	 */
	private void firstRunAPP() {  
        //从配置文件中取用户名密码的键值对  
        //若第一运行，则取出的键值对为所设置的默认值  
        SharedPreferences userInformation = getSharedPreferences("remember_password", 0);  
        String strJudge = userInformation.getString("judgeCheckBox", "no");// 选中状态  
        String strUserName = userInformation.getString("userName", "");// 用户名  
        String strPassword = userInformation.getString("password", "");// 密码  
        if (strJudge.equals("yes")) {  
        	lRememberPwd.setChecked(true);  
        	lAccount.setText(strUserName);  
            lPassword.setText(strPassword);  
        } else {  
        	lRememberPwd.setChecked(false);  
            lAccount.setText("");  
            lPassword.setText("");  
        }  
    }  
	
	/**
	 * 验证账号是手机号或者邮箱
	 */
	private boolean verifyAccount(String userName) {
		
		//首先判断账号是手机号还是邮箱
		if (userName.contains("@")) {
			//若是邮箱，进行邮箱判断
			if (!VerifyEmail.isEmail(userName)) {
				//邮箱格式不正确
				return false;
			}
		} else {
			//若是手机号，进行手机号判断
			if (!VerifyPhone.isMobileNO(userName)) {
				//手机号格式不正确
				return false;
			}
		}
		
		return true;
	}

}
