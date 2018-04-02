package com.wanli.swing.frame.listener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;

public class ComboListener implements SelectionListener {

	private Properties userProp;//读取保存的登录信息，用于下拉列表框显示
	private Properties saveProp;//读取保存的登录信息，用于显示上一次退出时的帐号
	private FileInputStream inStream;//输入流，读取信息
	private File userFile;//读取保存用户信息的文件
	private File saveFile;//读取保存上一次退出时帐号的信息
	private Combo comboUser;//下拉框
	private Button bRememberMe;//记住密码复选框

	public ComboListener(Combo comboUser, Button bRememberMe) {
		this.comboUser = comboUser;
		this.bRememberMe = bRememberMe;
		userProp = new Properties();
		saveProp = new Properties();
		userFile = new File("F:\\毕业设计\\graduation\\users.properties");
		saveFile = new File("F:\\毕业设计\\graduation\\savecount.properties");
		try {
			inStream = new FileInputStream(userFile);
			userProp.load(inStream);
			inStream = new FileInputStream(saveFile);
			saveProp.load(inStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
	}

	@Override
	public void widgetSelected(SelectionEvent arg0) {
		String key = comboUser.getText();
		if (userProp.getProperty(key) != null) {
			bRememberMe.setSelection(true);
		} else {
			bRememberMe.setSelection(false);
		}
	}

}
