package com.wanli.swing.frame.listener;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TreeItem;

import com.wanli.swing.frame.MainFrame;
import com.wanli.swing.service.DBService;

public class TabFordlerListener extends SelectionAdapter {

	private TabFolder tabFolder;//选项卡
	private Button first;//跳转到第一题或第一页
	private Button previous;//上一题或上一页
	private Button next;//下一题或下一页
	private Button last;//最后一题或最后一页
	private DBService dbService;
	private List<String[]> records;
	private int columnNum = 0;
	
	public TabFordlerListener(TabFolder tabFolder, Button first, Button previous, Button next, Button last) {
		this.tabFolder = tabFolder;
		this.first = first;
		this.previous = previous;
		this.next = next;
		this.last = last;
		dbService = new DBService();
	}
	
	@Override
    public void widgetSelected(final SelectionEvent e) { //匿名类
        handle_tabFolder_widgetSelected(e);
    }
	
	protected void handle_tabFolder_widgetSelected(final SelectionEvent e) {
		
		if (tabFolder.getSelectionIndex() == 0) {
			first.setText("首题");
			previous.setText("上一题");
			next.setText("下一题");
			last.setText("末题");
			MainFrame.historyCombo.setEnabled(false);
			MainFrame.refresh.setEnabled(false);
		}
		
		if (tabFolder.getSelectionIndex() == 1) {
			first.setText("首页");
			previous.setText("上一页");
			next.setText("下一页");
			last.setText("末页");
			MainFrame.historyCombo.setEnabled(false);
			MainFrame.refresh.setEnabled(true);
			MainFrame.refresh.setEnabled(false);
			if (MainFrame.scoreTab.getColumnCount() != 0) {
				int deleteColumn = MainFrame.scoreTab.getColumnCount();
				int deleteRow = MainFrame.scoreTab.getItemCount();
				for (int i = 0; i < deleteRow; i++) {
					MainFrame.scoreTab.remove(0);
				}
				for (int i = 0; i < deleteColumn; i++) {
					MainFrame.scoreTab.getColumn(0).dispose();
				}
			}
			if (MainFrame.tableName != null) {
				MainFrame.refresh.setEnabled(true);
				columnNum = dbService.getTableColumn(MainFrame.tableName);
				records = dbService.getScoreData(MainFrame.tableName);
				for (int i = 0; i < columnNum; i++) {
					if (i == 0) {
						TableColumn Column_name = new TableColumn(MainFrame.scoreTab, SWT.NONE);
						Column_name.setText("用户名");
						Column_name.setWidth(100);						
					} else {
						TableColumn Column_name = new TableColumn(MainFrame.scoreTab, SWT.NONE);
						Column_name.setText("题" + i);
						Column_name.setWidth(100);
					}
				}
				for (String[] record: records) {
					new TableItem(MainFrame.scoreTab, SWT.NONE).setText(record);
				}
			}
			
		}
		
		if (tabFolder.getSelectionIndex() == 2) {
			MainFrame.historyCombo.setEnabled(true);
			MainFrame.refresh.setEnabled(false);
			first.setText("首页");
			previous.setText("上一页");
			next.setText("下一页");
			last.setText("末页");
		}
		
	}

}
