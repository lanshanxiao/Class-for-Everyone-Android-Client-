package com.wanli.page;

import java.io.OutputStream;

import com.wanli.classforevery.R;
import com.wanli.socket.ConnectionSocket;
import com.wanli.socket.GetWiFiGateway;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
	private Button lBtnCancel;//6.取消
	private Button lBtnRegister;//7.注册
	
	//定义输出流，用来向服务端传输数据
	public static OutputStream os;
	
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
		lBtnCancel = (Button) findViewById(R.id.bnCancel);//6
		lBtnRegister = (Button) findViewById(R.id.bnRegister);//7
		
		//获取WiFi网关IP地址
		GetWiFiGateway getWiFiGateway = new GetWiFiGateway(this);
		String gateway = getWiFiGateway.getGateway();
		
//输出获取的Gateway的IP地址
Toast.makeText(this, gateway, Toast.LENGTH_SHORT).show();

		//创建连接服务端的线程
		new Thread(new ConnectionSocket(gateway)).start();
		
		lBtnLogin.setOnClickListener(lListener);
		lBtnCancel.setOnClickListener(lListener);
		lBtnRegister.setOnClickListener(lListener);
	}
	
	//不同按钮按下的监听事件选择
	OnClickListener lListener = new OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.bnLogin:
				login();
				break;
				
			case R.id.bnCancel:
				break;
				
			case R.id.bnRegister:
				break;
				
			case R.id.login_text_change_pwd:
				break;
				
			}
		}
	};
	
	//登录
	public void login() {
		//校验账号和密码
		if (checkUsernameAndPwd()) {
			String userName = getAccount();
			String userPassword = getPassword();
			
			try {
				//使用“严苛模式”的线程策略，监控线程中的操作
				StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
		        StrictMode.setThreadPolicy(policy);

		        String sendData = "2," + userName + "," + userPassword + "\r\n";
		      //将账号密码传到服务端
				os.write((sendData).getBytes());
				os.flush(); 
//输出发送到服务端的账号密码
Toast.makeText(this, sendData, Toast.LENGTH_SHORT).show();
			} catch (Exception e) {
				e.printStackTrace();
				Log.i("ioexception2", "ioexception1");
			}
		}
	}
	
	//校验账号和密码
	public boolean checkUsernameAndPwd() {
		String userName = lAccount.getText().toString().trim();
		String userPassword = lPassword.getText().toString().trim();
		
		if (userName.equals("")) { //账号为空
			Toast.makeText(this, getString(R.string.account_empty), Toast.LENGTH_SHORT).show();
					
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
	public String getAccount() {
		String account = lAccount.getText().toString().trim();
		
		return account;
	}
	public String getPassword() {
		String password = lPassword.getText().toString().trim();
		
		return password;
	}
}
