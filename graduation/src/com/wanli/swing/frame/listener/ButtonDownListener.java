package com.wanli.swing.frame.listener;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.wanli.swing.frame.MainFrame;
import com.wanli.swing.service.DBService;

public class ButtonDownListener extends SelectionAdapter {

	private String btnName;
	private DBService dbService;
	private List<String[]> records;
	private int columnNum = 0;
	
	public ButtonDownListener(String btnName) {
		this.btnName = btnName;
		dbService = new DBService();
	}
	
	@Override
    public void widgetSelected(final SelectionEvent e) { //匿名类
        selected(e);
    }

	protected void selected(SelectionEvent e) {
		switch (btnName) {
		case "first":
			MainFrame.index = 1;
			MainFrame.text.setText(MainFrame.questions[MainFrame.index]);
			break;
		case "previous":
			if (MainFrame.index > 1) {
				MainFrame.index -= 1;				
			}
			MainFrame.text.setText(MainFrame.questions[MainFrame.index]);
			break;
		case "next":
			if (MainFrame.index < MainFrame.questions.length - 1) {
				MainFrame.index += 1;				
			}
			MainFrame.text.setText(MainFrame.questions[MainFrame.index]);
			break;
		case "last":
			MainFrame.index = MainFrame.questions.length - 1;
			MainFrame.text.setText(MainFrame.questions[MainFrame.index]);
			break;
		case "refresh":
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
			break;
		default:
			break;
		}
	}
	
}
