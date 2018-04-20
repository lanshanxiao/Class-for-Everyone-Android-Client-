package com.wanli.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import android.util.Log;

public class ConnectionSocket implements Runnable {
	
	private String gateway;//网关的IP地址
	public static Socket connSocket;//创建Socket，用于和服务端建立连接
	
	//定义输出流，用来向服务端传输数据
	public static OutputStream os;
	
	//定义输入流，用来接收服务端的数据
	public static BufferedReader br;

	public ConnectionSocket(String gateway) {
		this.gateway = gateway;
	}

	@Override
	public void run() {
		try {	
			connSocket = new Socket(gateway, 30000);
			os = connSocket.getOutputStream();
			br = new BufferedReader(new InputStreamReader(connSocket.getInputStream()));
		} catch (Exception e) {
			e.printStackTrace();
			Log.i("ioexception", "ioexcepiton2");
		}
	}
}
