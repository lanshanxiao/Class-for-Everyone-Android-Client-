package com.wanli.page;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.provider.ContactsContract.FullNameStyle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class ClientThread implements Runnable {

	//该线程负责处理的Socket
	private Socket s;
	private EditText text;
	//该线程所处理的Socket所对应的输入流
	BufferedReader br = null;
	
	public ClientThread(Socket s, EditText text) throws IOException {
		this.s = s;
		this.text = text;
	}
	
	@Override
	public void run() {
		
		try {
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String content = null;
			//不断读取服务器发送过来的信息
			while ((content = br.readLine()) != null) {
				new SetText().execute("");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("ioexception3", "ioexception3");
		}
		
	}
	//在子线程中修改组件的状态需要使用AsyncTask类
	private class SetText extends AsyncTask<String, Object, String> {

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			text.setText("开启霸屏");
		}
		
	}

}
