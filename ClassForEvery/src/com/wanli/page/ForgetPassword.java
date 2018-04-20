package com.wanli.page;

import com.wanli.classforevery.R;
import com.wanli.socket.ConnectionSocket;
import com.wanli.socket.GetWiFiGateway;
import com.wanli.verification.VerifyEmail;
import com.wanli.verification.VerifyPhone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ForgetPassword extends Activity {
	
	//忘记密码界面上控件
	private TextView fPrevious;//1.上一步
	private TextView fNext;//2.下一步
	private EditText fAccount;//3.账号
	private EditText fInputCode;//4.输入验证码
	private Button fSendCode;//5.获取验证码
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.forget_password);
		
		//获取WiFi网关IP地址
		GetWiFiGateway getWiFiGateway = new GetWiFiGateway(this);
		String gateway = getWiFiGateway.getGateway();
						
//输出获取的Gateway的IP地址
Toast.makeText(this, gateway, Toast.LENGTH_SHORT).show();

		//创建连接服务端的线程
		new Thread(new ConnectionSocket(gateway)).start();
		
		//获取控件的id
		fPrevious = (TextView) findViewById(R.id.forget_password_previous);//1
		fNext = (TextView) findViewById(R.id.forget_password_next);//2
		fAccount = (EditText) findViewById(R.id.forget_password_account);//3
		fInputCode = (EditText) findViewById(R.id.input_verify_code);//4
		fSendCode = (Button) findViewById(R.id.send_verify_code);//5
		
		//注册监听事件
		fPrevious.setOnClickListener(fListener);
		fNext.setOnClickListener(fListener);
		fSendCode.setOnClickListener(fListener);
	}
	
	//不同按钮按下的监听事件选择
	private OnClickListener fListener = new OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.forget_password_previous:
				previous();
				break;
					
			case R.id.forget_password_next:
				next();
				break;
					
			case R.id.send_verify_code:
				
				if ( accountIsExist() ) {
					sendCode();
				} else {
					outputInformation();
				}
				break;

			}
		}
	};
	
	//取消事件
	private void previous() {
		Intent intent = new Intent(ForgetPassword.this, Login.class);
		startActivity(intent);
		finish();
	}
	
	//下一步事件
	private void next() {
		
		if ( codeIsTrue( getCode() ) ) {
			Intent intent = new Intent(ForgetPassword.this, ModifyPassword.class);
			intent.putExtra("Account",getAccount());
			startActivity(intent);
			finish();
		} else {
			Toast.makeText(this, getString(R.string.code_wrong), Toast.LENGTH_SHORT).show();
		}
	}
	
	//判断输入的账号，服务端是否存在
	private boolean accountIsExist() {
		String userName = getAccount();
		
		try {
			//使用“严苛模式”的线程策略，监控线程中的操作
			StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);

			String sendForgetPasswordData = "5," + userName + "," + "null" + "\r\n";
			//将账号密码传到服务端
			ConnectionSocket.os.write((sendForgetPasswordData).getBytes());
			ConnectionSocket.os.flush(); 
//输出发送到服务端的账号密码
Toast.makeText(this, sendForgetPasswordData, Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			e.printStackTrace();
			Log.i("ioexception2", "ioexception1");
		}
		
		return true;
	}
	
	//获取验证码事件
	private void sendCode() {
		String userName = getAccount();
		
		if ( VerifyEmail.isEmail(userName) ) {
			
			Toast.makeText(this, getString(R.string.email_reveice), Toast.LENGTH_SHORT).show();
			timer.start();
			
			//邮箱获取验证码函数requestEmailCode();
		} else if ( VerifyPhone.isMobileNO(userName) ) {
			
			Toast.makeText(this, getString(R.string.message_reveice), Toast.LENGTH_SHORT).show();
			timer.start();
			
			//短信获取验证码函数requestPhoneCode();
		} else {
			Toast.makeText(this, getString(R.string.account_wrong), Toast.LENGTH_SHORT).show();
		}
	}
	
	//
	private void outputInformation() {
		Toast.makeText(this, getString(R.string.account_not_exist), Toast.LENGTH_SHORT).show();
	}
	
	//判断验证码是否正确
	private boolean codeIsTrue(String code) {
		
		return true;
	}
	
	/**
	 * 获取验证码的计时器变量，倒计时
	 * 倒计时为60秒，每1秒刷新一次
	 */
	private CountDownTimer timer = new CountDownTimer(60 * 1000, 1000) {  
		  
	    @Override  
	    public void onTick(long millisUntilFinished) {  
	    	fSendCode.setEnabled(false);  
	    	fSendCode.setText((millisUntilFinished / 1000) + "秒后可重发");  
	    }  
	  
	    @Override  
	    public void onFinish() {  
	    	fSendCode.setEnabled(true);  
	    	fSendCode.setText(R.string.send_code);  
	    }  
	};  
	
	//获取账号
	private String getAccount() {
		return fAccount.getText().toString().trim();
	}
	
	//获取验证码
	private String getCode() {
		return fInputCode.getText().toString().trim();
	}
	
}
