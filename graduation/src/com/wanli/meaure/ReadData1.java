package com.wanli.meaure;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;

import java.awt.Toolkit;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

public class ReadData1 {
	private static Shell shell;
	private static Display display;
	private static Text TemText;
	private static Text HumiText;
	private static Composite mainframe;
	private static Composite readDataView;
	private static Label temperature;
	private static Label humidity;
	private List<Data> datas;
	private static Text textWarnTemTopLimit;
	private static Text textWarnHumiTopLimit;
	private static Text textWarnTemLowLimit;
	private static Text textWarnHumiLowLimit;
	private static Table table;
	private static Label comfortLabel;
	private static int roomNum = 0;
	private static Label uncomfortLab;
	private static Label comfortLab;
	private static Label uncomfortL2Lab;
	private static int page = 1;
	private static double temToplimit;
	private static double temLowlimit;
	private static double humiToplimit;
	private static double humiLowlimit;
	
	
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		ReadData1 read = new ReadData1();
		ReadDatas readDatas = read.new ReadDatas(read);
		display = Display.getDefault();
		shell = new Shell();
		shell.setSize(800, 700);
		shell.setText("\u6559\u5BA4\u6E29\u5EA6\u76D1\u6D4B\u7CFB\u7EDF");
		center(display, shell);
		
		mainframe = new Composite(shell, SWT.NONE);
		mainframe.setToolTipText("\u8BF7\u9009\u62E9\r\n");
		mainframe.setBounds(0, 0, 784, 661);
		
		readDataView = new Composite(mainframe, SWT.BORDER);
		readDataView.setToolTipText("\u8BF7\u9009\u62E9");
		readDataView.setBounds(0, 0, 784, 320);
		
		Group temAndHumi = new Group(readDataView, SWT.NONE);
		temAndHumi.setBounds(10, 41, 152, 116);
		
		temperature = new Label(temAndHumi, SWT.NONE);
		temperature.setBounds(10, 25, 43, 17);
		temperature.setText("\u6E29\u5EA6\uFF1A");
		
		humidity = new Label(temAndHumi, SWT.NONE);
		humidity.setBounds(10, 73, 43, 17);
		humidity.setText("\u6E7F\u5EA6\uFF1A");
		
		TemText = new Text(temAndHumi, SWT.BORDER | SWT.READ_ONLY);
		TemText.setBounds(59, 22, 73, 23);
		
		HumiText = new Text(temAndHumi, SWT.BORDER | SWT.READ_ONLY);
		HumiText.setBounds(59, 70, 73, 23);
		
		Combo classRoom = new Combo(readDataView, SWT.READ_ONLY);
		classRoom.setBounds(10, 10, 88, 25);
		String[] rooms = { "请选择", "教室1", "教室2", "教室3", "教室4", "教室5"};
		classRoom.setItems(rooms);
		classRoom.select(0);
		classRoom.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (readDatas.isAlive()) {
					
				} else {
					readDatas.start();
				}
				String room = classRoom.getText();
				int num = classRoom.indexOf(room);
				System.out.println(num);
				switch (num) {
				case 1:
					roomNum = 1;
					break;
				case 2:
					roomNum = 2;
					break;
				case 3:
					roomNum = 3;
					break;
				case 4:
					roomNum = 4;
				case 5:
					roomNum = 5;
					break;
				default:
					break;
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				
			}
		});
		
		Group warn = new Group(readDataView, SWT.NONE);
		warn.setText("\u8BBE\u7F6E\u8B66\u544A\u4E0A\u4E0B\u9650");
		warn.setBounds(295, 10, 475, 147);
		
		Label warnTem = new Label(warn, SWT.NONE);
		warnTem.setBounds(10, 43, 42, 17);
		warnTem.setText("\u6E29\u5EA6");
		
		Label warnHumi = new Label(warn, SWT.NONE);
		warnHumi.setBounds(10, 103, 42, 17);
		warnHumi.setText("\u6E7F\u5EA6");
		
		Label warnTemTopLimit = new Label(warn, SWT.NONE);
		warnTemTopLimit.setBounds(58, 43, 42, 17);
		warnTemTopLimit.setText("\u4E0A\u9650");
		
		Label warnHumiTopLimit = new Label(warn, SWT.NONE);
		warnHumiTopLimit.setBounds(58, 103, 42, 17);
		warnHumiTopLimit.setText("\u4E0A\u9650");
		
		textWarnTemTopLimit = new Text(warn, SWT.BORDER);
		textWarnTemTopLimit.setBounds(109, 37, 73, 23);
		
		textWarnHumiTopLimit = new Text(warn, SWT.BORDER);
		textWarnHumiTopLimit.setBounds(109, 97, 73, 23);
		
		Label warnTemLowLimit = new Label(warn, SWT.NONE);
		warnTemLowLimit.setBounds(217, 43, 42, 17);
		warnTemLowLimit.setText("\u4E0B\u9650");
		
		Label warnHumiLowLimit = new Label(warn, SWT.NONE);
		warnHumiLowLimit.setBounds(217, 103, 42, 17);
		warnHumiLowLimit.setText("\u4E0B\u9650");
		
		textWarnTemLowLimit = new Text(warn, SWT.BORDER);
		textWarnTemLowLimit.setBounds(270, 37, 73, 23);
		
		textWarnHumiLowLimit = new Text(warn, SWT.BORDER);
		textWarnHumiLowLimit.setBounds(270, 97, 73, 23);
		
		Button temButton = new Button(warn, SWT.NONE);
		temButton.setBounds(379, 33, 80, 27);
		temButton.setText("\u786E\u5B9A");
		temButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				temToplimit = Double.valueOf(textWarnHumiTopLimit.getText());
				temLowlimit = Double.valueOf(textWarnHumiLowLimit.getText());
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button humiButton = new Button(warn, SWT.NONE);
		humiButton.setBounds(379, 93, 80, 27);
		humiButton.setText("\u786E\u5B9A");
		humiButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				humiToplimit = Double.valueOf(textWarnHumiTopLimit.getText());
				humiLowlimit = Double.valueOf(textWarnHumiLowLimit.getText());
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Group comfortDegreeTab = new Group(readDataView, SWT.NONE);
		comfortDegreeTab.setText("\u8212\u9002\u5EA6\u6837\u8868");
		comfortDegreeTab.setBounds(10, 163, 760, 143);
		
		uncomfortLab = new Label(comfortDegreeTab, SWT.NONE);
		uncomfortLab.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
		uncomfortLab.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
		uncomfortLab.setBounds(63, 49, 35, 30);
		
		Label uncomfortL = new Label(comfortDegreeTab, SWT.NONE);
		uncomfortL.setBounds(15, 26, 47, 17);
		uncomfortL.setText("\u4E0D\u8212\u9002");
		
		Label temRange1 = new Label(comfortDegreeTab, SWT.NONE);
		temRange1.setBounds(52, 94, 61, 17);
		temRange1.setText("\u6E29\u5EA6<20\u2103");
		
		Label humiRange1 = new Label(comfortDegreeTab, SWT.NONE);
		humiRange1.setBounds(52, 117, 61, 17);
		humiRange1.setText("\u6E7F\u5EA6>50%");
		
		Label comfortL = new Label(comfortDegreeTab, SWT.NONE);
		comfortL.setBounds(170, 26, 61, 17);
		comfortL.setText("\u8212\u9002");
		
		comfortLab = new Label(comfortDegreeTab, SWT.NONE);
		comfortLab.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		comfortLab.setBounds(208, 49, 35, 30);
		
		Label temRange2 = new Label(comfortDegreeTab, SWT.NONE);
		temRange2.setBounds(187, 94, 100, 17);
		temRange2.setText("24\u2103\u2264\u6E29\u5EA6\u226427\u2103");
		
		Label humiRange2 = new Label(comfortDegreeTab, SWT.NONE);
		humiRange2.setBounds(187, 117, 100, 17);
		humiRange2.setText("10%\u2264\u6E7F\u5EA6\u226440%");
		
		Label uncomfortL2 = new Label(comfortDegreeTab, SWT.NONE);
		uncomfortL2.setBounds(324, 26, 61, 17);
		uncomfortL2.setText("\u4E0D\u8212\u9002");
		
		uncomfortL2Lab = new Label(comfortDegreeTab, SWT.NONE);
		uncomfortL2Lab.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		uncomfortL2Lab.setBounds(380, 49, 35, 30);
		
		Label temRange3 = new Label(comfortDegreeTab, SWT.NONE);
		temRange3.setBounds(368, 94, 61, 17);
		temRange3.setText("\u6E29\u5EA6\u226528\u2103");
		
		Label humiRange3 = new Label(comfortDegreeTab, SWT.NONE);
		humiRange3.setBounds(368, 117, 61, 17);
		humiRange3.setText("\u6E7F\u5EA6>50%");
		
		Group comfortDegree = new Group(readDataView, SWT.NONE);
		comfortDegree.setText("\u8212\u9002\u5EA6");
		comfortDegree.setBounds(168, 49, 104, 95);
		
		comfortLabel = new Label(comfortDegree, SWT.NONE);
		comfortLabel.setBounds(29, 27, 50, 47);
		
		Composite histroyData = new Composite(mainframe, SWT.BORDER);
		histroyData.setBounds(0, 320, 784, 303);
		
		table = new Table(histroyData, SWT.BORDER);
		table.setBounds(-2, -2, 782, 339);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn num = new TableColumn(table, SWT.NONE);
		num.setWidth(100);
		num.setText("\u5E8F\u53F7");
		
		TableColumn deviceNum = new TableColumn(table, SWT.NONE);
		deviceNum.setWidth(100);
		deviceNum.setText("\u4F20\u611F\u8BBE\u5907\u6807\u53F7");
		
		TableColumn temColumn = new TableColumn(table, SWT.NONE);
		temColumn.setWidth(100);
		temColumn.setText("\u6E29\u5EA6");
		
		TableColumn humiColumn = new TableColumn(table, SWT.NONE);
		humiColumn.setWidth(100);
		humiColumn.setText("\u6E7F\u5EA6");
		
		TableColumn time = new TableColumn(table, SWT.NONE);
		time.setWidth(100);
		time.setText("\u65F6\u95F4");
		
		TableItem tableItem = new TableItem(table, SWT.LEFT);
		for (int i = 0; i < read.getDatas().size(); i++) {
			tableItem.setText(new String[] {"0", "1", "20", "30", "2017-11-1"});			
		}
		
		
		Button refresh = new Button(mainframe, SWT.NONE);
		refresh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			}
		});
		refresh.setBounds(515, 629, 80, 27);
		refresh.setText("\u5237\u65B0");
		
		Button prePage = new Button(mainframe, SWT.NONE);
		prePage.setBounds(257, 629, 80, 27);
		prePage.setText("\u4E0A\u4E00\u9875");
		prePage.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (page == 1) 
					prePage.setEnabled(false);
				else
					page--;
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				
			}
		});
		
		Button nextPage = new Button(mainframe, SWT.NONE);
		nextPage.setBounds(343, 629, 80, 27);
		nextPage.setText("\u4E0B\u4E00\u9875");
		nextPage.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				page++;
			}
		});
		
		Button firstPage = new Button(mainframe, SWT.NONE);
		firstPage.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				page = 1;
			}
		});
		firstPage.setBounds(171, 629, 80, 27);
		firstPage.setText("\u9996\u9875");
		
		Button lastPage = new Button(mainframe, SWT.NONE);
		lastPage.setBounds(429, 629, 80, 27);
		lastPage.setText("\u672B\u9875");
		lastPage.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				page = 1;
			}
		});
		
		shell.open();
		shell.layout();
		if (!display.isDisposed()) {
			Runnable runnable = new Runnable() {
				public void run() {
//					readDatas.start();
				}
			};
			display.syncExec(runnable); // 关键在这一句上
		}
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public static void center(Display display, Shell shell) {
		Rectangle bounds = display.getPrimaryMonitor().getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation(x, y);
	}
	
	class ReadDatas extends Thread {
		ReadData1 readData = null;
		Convert convert = new Convert();
		List<Data> data0 = new ArrayList<Data>();
		List<Data> data1 = new ArrayList<Data>();
		double temperature = 0;
		double humidity = 0;
		double temperature2 = 0;
		double humidity2 = 0;
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		
		public ReadDatas(ReadData1 readData) {
			this.readData = readData;
		}
		
		public void run() {
			while (true) {
				readData.setDatas(convert.convertData(null));
				for (int i = 0; i < readData.getDatas().size(); i++) {
					if (i % 2 == 0) {
						data0.add(readData.getDatas().get(i));
					} else {
						data1.add(readData.getDatas().get(i));
					}
				}
				if (roomNum == 1) {
					for (int i = 0; i < data0.size(); i++) {
						temperature = Double.valueOf(data0.get(i).getTemperature()) * 0.1;
						humidity = Double.valueOf(data0.get(i).getHumidity()) * 0.1;
						shell.getDisplay().asyncExec(new Runnable() {
							public void run() {
								if (temperature < 20 && humidity > 50) {
									comfortLabel.setBackground(uncomfortLab.getBackground());
								} else if (24 <= temperature && temperature <= 27 && 10 <= humidity && humidity <= 40) {
									comfortLabel.setBackground(comfortLab.getBackground());
								} else if (temperature >= 28 && humidity > 50) {
									comfortLabel.setBackground(uncomfortL2Lab.getBackground());
								}
								
								if (temperature > temToplimit || temperature < temLowlimit || humidity > humiToplimit || humidity < humiLowlimit) {
									toolkit.beep();
								}
								BigDecimal bg = new BigDecimal(temperature);
								TemText.setText(Double.toString(bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()));
								bg = new BigDecimal(humidity);
								HumiText.setText(Double.toString(bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()));
							}
						});
					}					
				} else if (roomNum == 2) {
					for (int i = 0; i < data1.size(); i++) {
						temperature2 = Double.valueOf(data1.get(i).getTemperature()) * 0.1;
						humidity2 = Double.valueOf(data1.get(i).getHumidity()) * 0.1;
						shell.getDisplay().asyncExec(new Runnable() {
							public void run() {
								if (temperature2 < 20 && humidity2 > 50) {
									comfortLabel.setBackground(uncomfortLab.getBackground());
								} else if (24 <= temperature2 && temperature2 <= 27 && 10 <= humidity2 && humidity2 <= 40) {
									comfortLabel.setBackground(comfortLab.getBackground());
								} else if (temperature2 >= 28 && humidity2 > 50) {
									comfortLabel.setBackground(uncomfortL2Lab.getBackground());
								}
								
								if (temperature2 > temToplimit || temperature2 < temLowlimit || humidity2 > humiToplimit || humidity2 < humiLowlimit) {
									toolkit.beep();
								}
								BigDecimal bg = new BigDecimal(temperature2);
								TemText.setText(Double.toString(bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()));
								bg = new BigDecimal(humidity2);
								HumiText.setText(Double.toString(bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()));
							}
						});
					}					
				}
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public List<Data> getDatas() {
		return datas;
	}

	public void setDatas(List<Data> datas) {
		this.datas = datas;
	}
	
	
}

