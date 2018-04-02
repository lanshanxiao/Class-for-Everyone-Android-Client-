package com.wanli.swing.frame.listener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

//该类没有被使用

public class CreateClassListener extends SelectionAdapter {

	private Tree tree;
	private String userName;
	private int number = 1;
	
	public CreateClassListener(Tree tree, String userName) {
		this.tree = tree;
		this.userName = userName;
	}
	
	public void createClass() {
		TreeItem classroom = new TreeItem(tree, SWT.NONE);
		classroom.setText(userName + number);
		number++;
	}
	
	@Override
	public void widgetSelected(SelectionEvent arg0) {
		
		TreeItem classroom = new TreeItem(tree, SWT.NONE);
		classroom.setText(userName + number);
		number++;
	}
	
}
