package com.wanli.thread;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TreeItem;

import com.wanli.classforereryone.server.MyServer;
import com.wanli.classforereryone.server.ServerThread;
import com.wanli.swing.frame.MainFrame;

public class ListeningSocket implements Runnable  {

	private int socketNum = 0;
	private Map<String, TreeItem> onlineUsers = new HashMap<>();
	
	public ListeningSocket() {
		socketNum = MyServer.users.size();
	}
	
	@Override
	public void run() {
		while (true) {
			if (MainFrame.rooms.size() > 0 && MyServer.users.size() > socketNum) {
				if (ServerThread.getIpAddress() != "") {
					socketNum = MyServer.users.size();
					Display.getDefault().syncExec(new Runnable(){
						public void run() {
							TreeItem treeItem = new TreeItem(MainFrame.rooms.get(0), SWT.NONE);
							treeItem.setText(ServerThread.getIpAddress());
							onlineUsers.put(ServerThread.getIpAddress(), treeItem);
						}
						
					});
				}
			} else {
				Display.getDefault().syncExec(new Runnable(){
					public void run() {
						if (onlineUsers != null && onlineUsers.size() > 0) {
							if (ServerThread.quitSocket != "") {
								socketNum = MyServer.users.size();
								onlineUsers.get(ServerThread.quitSocket).dispose();
								ServerThread.quitSocket = "";
							}
						}
					}
				});
			}
			
//			System.out.println(MainFrame.rooms.size());
//			System.out.println(MyServer.users.size());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
