package com.wanli.page;

import com.wanli.classforevery.R;
import com.wanli.socket.ConnectionSocket;
import com.wanli.socket.GetWiFiGateway;
import com.wanli.verification.VerifyEmail;
import com.wanli.verification.VerifyPhone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Register extends Activity {
	
	//注册界面的控件
	private EditText rAccount;//1.账号
	private EditText rPasswordOnce;//2.密码
	private ToggleButton rTogglePassword;//3.密码显示
	private EditText rPasswordSecond;//4.确认密码
	private ToggleButton rConfirmTogglePassword;//5.确认密码显示
	private EditText rEmail;//6.邮箱
	private EditText rPhone;//7.手机号
	private Button rBtnRegister;//8.注册
	private Button rBtnBack;//9.返回

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register);
		
		//获取WiFi网关IP地址
		GetWiFiGateway getWiFiGateway = new GetWiFiGateway(this);
		String gateway = getWiFiGateway.getGateway();
				
//输出获取的Gateway的IP地址
Toast.makeText(this, gateway, Toast.LENGTH_SHORT).show();

		//创建连接服务端的线程
		new Thread(new ConnectionSocket(gateway)).start();
		
		//通过id找到相应的控件
		rAccount = (EditText) findViewById(R.id.register_username); 
		rPasswordOnce = (EditText) findViewById(R.id.register_password_once);
		rTogglePassword = (ToggleButton) findViewById(R.id.registerTogglePwd); 
		rPasswordSecond = (EditText) findViewById(R.id.register_password_second);
		rConfirmTogglePassword = (ToggleButton) findViewById(R.id.registerConfirmTogglePwd);
		rEmail = (EditText) findViewById(R.id.register_email);
		rPhone = (EditText) findViewById(R.id.register_phone);
		rBtnRegister = (Button) findViewById(R.id.buttonRegister);
		rBtnBack = (Button) findViewById(R.id.buttonBack);
		
		//为按钮注册监听器
		rBtnRegister.setOnClickListener(rListener);
		rBtnBack.setOnClickListener(rListener);
		rTogglePassword.setOnClickListener(rListener);
		rConfirmTogglePassword.setOnClickListener(rListener);
	}
	
	//不同按钮按下的点击监听事件选择
	private OnClickListener rListener = new OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.buttonRegister:
					register();
					break;

				case R.id.buttonBack:
					back();
					break;
					
				case R.id.registerTogglePwd:
					if(rTogglePassword.isChecked()){
						/* 显示密码 */
						rPasswordOnce.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
					} else{
			    	   /* 隐藏密码 */
			    	   rPasswordOnce.setTransformationMethod(PasswordTransformationMethod.getInstance()); 
			        }
					break;
					
				case R.id.registerConfirmTogglePwd:
					if(rConfirmTogglePassword.isChecked()){
						/* 显示密码 */
						rPasswordSecond.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
					} else{
			    	   /* 隐藏密码 */
						rPasswordSecond.setTransformationMethod(PasswordTransformationMethod.getInstance()); 
			        }
					break;
					
			}
		}
	};
		
	//注册按钮函数
	private void register() {
		//校验账号和密码
		if (checkTheInput()) {
			String userName = getAccount();
			String userPassword = getPassword();
			String userEmail = getEmail();
			String userPhone = getPhone();
					
			try {
				//使用“严苛模式”的线程策略，监控线程中的操作
				StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy);

				String sendRegisterData = "1," + userName + "," + userPassword + "," + userEmail + "," + userPhone + "," + "\r\n";
				//将账号密码传到服务端
				ConnectionSocket.os.write((sendRegisterData).getBytes());
				ConnectionSocket.os.flush(); 
//输出发送到服务端的账号密码
Toast.makeText(this, sendRegisterData, Toast.LENGTH_SHORT).show();
			} catch (Exception e) {
				e.printStackTrace();
				Log.i("ioexception2", "ioexception1");
			}
		}
	}
	
	//校验注册界面的输入项是否正确
	private boolean checkTheInput() {
		
		String userName = getAccount();
		String userPassword = getPassword();
		String userConfirmPassword = getConfirmPassword();
		String userEmail = getEmail();
		String userPhone = getPhone();
		
		if (userName.equals("")) { //账号为空
			Toast.makeText(this, getString(R.string.account_empty), Toast.LENGTH_SHORT).show();
					
			return false;
		}  else if (userPassword.equals("")) { //密码为空
			Toast.makeText(this, getString(R.string.password_empty), Toast.LENGTH_SHORT).show();
					
			return false;
		} else if (userPassword.length() < 6 || userPassword.length() > 16) { //密码长度错误
			rPasswordOnce.setText("");
			rPasswordSecond.setText("");
			Toast.makeText(this, getString(R.string.password_length_wrong), Toast.LENGTH_SHORT).show();
			
			return false;
		} else if (!userConfirmPassword.equals(userPassword)) {
			rPasswordOnce.setText("");
			rPasswordSecond.setText("");
			Toast.makeText(this, getString(R.string.password_equal_wrong), Toast.LENGTH_SHORT).show();
			
			return false;
		} else if (userEmail.equals("")) {
			Toast.makeText(this, getString(R.string.email_empty), Toast.LENGTH_SHORT).show();
			
			return false;
		} else if (!VerifyEmail.isEmail(userEmail)) {
			rEmail.setText("");
			Toast.makeText(this, getString(R.string.email_wrong), Toast.LENGTH_SHORT).show();
			
			return false;
		} else if (userPhone.equals("")) {
			Toast.makeText(this, getString(R.string.phone_empty), Toast.LENGTH_SHORT).show();
			
			return false;
		} else if (!VerifyPhone.isMobileNO(userPhone)) {
			rPhone.setText("");
			Toast.makeText(this, getString(R.string.phone_length_wrong), Toast.LENGTH_SHORT).show();
			
			return false;
		}
		
		return true;
	}
	
	//获取当前注册界面的账号
	private String getAccount() {
		return rAccount.getText().toString().trim();
	}
	//获取当前注册界面的密码
	private String getPassword() {
		return rPasswordOnce.getText().toString().trim();
	}
	//获取当前注册界面的确认密码
	private String getConfirmPassword() {
		return rPasswordSecond.getText().toString().trim();
	}
	//获取当前注册界面的邮箱
	private String getEmail() {
		return rEmail.getText().toString().trim();
	}
	//获取当前注册界面的手机号
	private String getPhone() {
		return rPhone.getText().toString().trim();
	}
	
	//返回按钮函数
	private void back() {
		Intent intent = new Intent(Register.this, Login.class);
		startActivity(intent);
		finish();
	}
	
}
