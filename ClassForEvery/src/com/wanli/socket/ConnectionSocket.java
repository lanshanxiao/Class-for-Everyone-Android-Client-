package com.wanli.socket;

import java.io.OutputStream;
import java.net.Socket;

import com.wanli.page.Login;

import android.util.Log;

public class ConnectionSocket implements Runnable {
	
	String gateway;//网关的IP地址
	Socket connSocket;//创建Socket，用于和服务端建立连接
	
	//定义输出流，用来向服务端传输数据
	public static OutputStream os;
	
	public ConnectionSocket(String gateway) {
		this.gateway = gateway;
	}

	@Override
	public void run() {
		try {	
			connSocket = new Socket(gateway, 30000);
			os = connSocket.getOutputStream();
		} catch (Exception e) {
			e.printStackTrace();
			Log.i("ioexception", "ioexcepiton2");
		}
	}
}
