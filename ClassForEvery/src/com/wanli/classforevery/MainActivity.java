package com.wanli.classforevery;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	//定义界面上的两个文本框
	EditText input, show;
	//定义界面上的一个按钮
	Button send;
	//输出流，用于向服务器端写数据
	static OutputStream os;
	//客户端用户昵称
	private String nickName;
	//登录设备的imei号
	private String imei;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Context context = this.getBaseContext();
		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
		imei = telephonyManager.getDeviceId();
		
		input = (EditText) findViewById(R.id.input);
		send = (Button) findViewById(R.id.send);
		show = (EditText) findViewById(R.id.show);
		
//		show.setText(imei);
		new Thread(new StartSocket(show, imei)).start();
		send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				try {
					//使用“严苛模式”的线程策略，监控线程中的操作
					StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
			        StrictMode.setThreadPolicy(policy);
			        //将用户在文本框内输入的内容写入网络
					os.write(("1111," + imei + "," + input.getText().toString() + "\r\n").getBytes());
					//清空input文本框
					input.setText("");
				} catch (Exception e) {
					e.printStackTrace();
					Log.i("ioexception2", "ioexception2");
				}
			}
		});
	}

}

//当客户端用户登录后向服务器发送设备的相关信息
class SendImeiToServer implements Runnable {

	private String imei;
	
	public SendImeiToServer(String imei) {
		this.imei = imei;
	}
	
	@Override
	public void run() {
		if (MainActivity.os != null) {
			try {
				//将用户在文本框内输入的内容写入网络
				StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
				//使用“严苛模式”的线程策略，监控线程中的操作
				StrictMode.setThreadPolicy(policy);
				MainActivity.os.write(("1111," + imei + "\r\n").getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

class StartSocket implements Runnable {

	//创建Socket，用于与服务端进行通信
	Socket s;
	EditText text;
	String imei;
	
	public StartSocket(EditText text, String imei) {
		// TODO Auto-generated constructor stub
		this.text = text;
		this.imei = imei;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			s = new Socket("192.168.191.1", 30000);
			//客户端启动ClientThread线程不断读取来自服务器的数据
			new Thread(new ClientThread(s, text)).start();
			MainActivity.os = s.getOutputStream();
			new Thread(new SendImeiToServer(imei)).start();
		} catch (Exception e1) {
			e1.printStackTrace();
			Log.i("ioexception1", "ioexception1");
		}
	}
	
}
