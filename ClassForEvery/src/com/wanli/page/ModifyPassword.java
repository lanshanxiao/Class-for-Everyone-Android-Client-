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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ModifyPassword extends Activity {
	
	//注册界面的控件
		private TextView mAccount;//1.账号
		private EditText mPasswordOnce;//2.密码
		private ToggleButton mTogglePassword;//3.密码显示
		private EditText mPasswordSecond;//4.确认密码
		private ToggleButton mConfirmTogglePassword;//5.确认密码显示
		private Button mBtnModify;//6.提交
		private Button mBtnBack;//7.返回
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.modify_password);
		
		//获取忘记密码界面传来的账号信息
		Intent intent = getIntent();
		String str = intent.getStringExtra("Account");
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
		
		
		//获取WiFi网关IP地址
		GetWiFiGateway getWiFiGateway = new GetWiFiGateway(this);
		String gateway = getWiFiGateway.getGateway();
						
//输出获取的Gateway的IP地址
Toast.makeText(this, gateway, Toast.LENGTH_SHORT).show();

		//创建连接服务端的线程
		new Thread(new ConnectionSocket(gateway)).start();
				
		//通过id找到相应的控件
		mAccount = (TextView) findViewById(R.id.userAccount); //1
		mPasswordOnce = (EditText) findViewById(R.id.modify_password_once); //2
		mTogglePassword = (ToggleButton) findViewById(R.id.modifyTogglePwd); //3 
		mPasswordSecond = (EditText) findViewById(R.id.modify_password_second); //4
		mConfirmTogglePassword = (ToggleButton) findViewById(R.id.modifyConfirmTogglePwd); //5
		mBtnModify = (Button) findViewById(R.id.modifyConfirmButton); //6
		mBtnBack = (Button) findViewById(R.id.modifyBackButton);//7.返回
		
		//将修改密码的账号显示出来
		mAccount.setText(str);
		
		//为按钮注册监听器
		mTogglePassword.setOnClickListener(mListener);
		mConfirmTogglePassword.setOnClickListener(mListener);
		mBtnModify.setOnClickListener(mListener);
		mBtnBack.setOnClickListener(mListener);
	}
	
	
	//不同按钮按下的点击监听事件选择
	private OnClickListener mListener = new OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.modifyTogglePwd:
					if(mTogglePassword.isChecked()){
						/* 显示密码 */
						mPasswordOnce.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
					} else{
			    	   /* 隐藏密码 */
			    	   mPasswordOnce.setTransformationMethod(PasswordTransformationMethod.getInstance()); 
			        }
					break;

				case R.id.modifyConfirmTogglePwd:
					if(mConfirmTogglePassword.isChecked()){
						/* 显示密码 */
						mPasswordSecond.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
					} else{
			    	   /* 隐藏密码 */
						mPasswordSecond.setTransformationMethod(PasswordTransformationMethod.getInstance()); 
			        }
					break;
						
				case R.id.modifyConfirmButton:
					submit();
					break;
					
				case R.id.modifyBackButton:
					back();
					break;
			
			}
		}
	};
	
	//提交按钮事件
	private void submit() {
		//校验账号和密码
		if (checkTheInput()) {
			String userName = getAccount();
			String userPassword = getPassword();
					
			try {
				//使用“严苛模式”的线程策略，监控线程中的操作
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy);

				String sendRegisterData = "5," + userName + "," + userPassword + "\r\n";
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

		
		if (userPassword.equals("")) { //密码为空
			Toast.makeText(this, getString(R.string.password_empty), Toast.LENGTH_SHORT).show();
					
			return false;
		} else if (userPassword.length() < 6 || userPassword.length() > 16) { //密码长度错误
			mPasswordOnce.setText("");
			mPasswordSecond.setText("");
			Toast.makeText(this, getString(R.string.password_length_wrong), Toast.LENGTH_SHORT).show();
			
			return false;
		} else if (!userConfirmPassword.equals(userPassword)) {
			mPasswordOnce.setText("");
			mPasswordSecond.setText("");
			Toast.makeText(this, getString(R.string.password_equal_wrong), Toast.LENGTH_SHORT).show();
			
			return false;
		}
		
		return true;
	}
	
	
	//获取当前注册界面的账号
	private String getAccount() {
		return mAccount.getText().toString().trim();
	}
	//获取当前注册界面的密码
	private String getPassword() {
		return mPasswordOnce.getText().toString().trim();
	}
	//获取当前注册界面的确认密码
	private String getConfirmPassword() {
		return mPasswordSecond.getText().toString().trim();
	}
	
	//返回按钮函数
	private void back() {
		Intent intent = new Intent(ModifyPassword.this, Login.class);
		startActivity(intent);
		finish();
	}
}
