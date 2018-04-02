package com.wanli.classforereryone.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

import com.wanli.swing.entities.OnlineUser;
import com.wanli.swing.frame.MainFrame;

public class ServerThread implements Runnable {

	//定义当前线程所处理的Socket
	Socket s = null;
	//该线程所处理的Socket所对应的输入流
	BufferedReader br = null;
	//接收连接上服务端的客户端imei号
	private static String ipAddress = "";
	public static String quitSocket = "";
	
	public ServerThread(Socket s) throws IOException {
		this.s = s;
		//初始化该Socket对应的输入流
		br = new BufferedReader(new InputStreamReader(s.getInputStream(), "utf-8"));
	}
	
	@Override
	public void run() {
		
		String content = null;
		//采用循环不断从Socket中读取客户端发送过来的数据
		while ((content = readFromClient()) != null) {
			String[] info = content.split(",");
			MyServer.users.get(s.getInetAddress().toString().substring(1)).setInetAddress(s.getInetAddress().toString().substring(1));
			MyServer.users.get(s.getInetAddress().toString().substring(1)).setUsername(info[0]);
			MyServer.users.get(s.getInetAddress().toString().substring(1)).setImei(info[1]);
			ipAddress = s.getInetAddress().toString().substring(1);
			System.out.println(ipAddress);
			if (info.length == 3) {
				MyServer.users.get(s.getInetAddress().toString().substring(1)).setContent(info[2]);
				System.out.println(info[0] + "," + info[1] + "," + info[2]);				
			}
		}
		//跳出循环，表明该Socket对应的客户端已经关闭
		//删除该Socket
		quitSocket = s.getInetAddress().toString().substring(1);
		MyServer.users.remove(s.getInetAddress().toString().substring(1));
	}

	//定义读取客户端数据的方法
	private String readFromClient() {
		try {
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//向客户端发送消息
	public static void sendToClient(String msg) {
		for (Map.Entry<String, OnlineUser> user: MyServer.users.entrySet()) {
			try {
				System.out.println(msg);
				OnlineUser val = user.getValue(); 
                PrintWriter pw =val.getPw();  
                pw.println(msg);  
                pw.flush();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }
		}
	}
	
	public static String getIpAddress() {
		return ipAddress;
	}
	
}
