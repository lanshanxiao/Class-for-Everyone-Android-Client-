//package com.wanli.swing.frame.listener;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileReader;
//
//import org.apache.poi.hwpf.HWPFDocument;
//import org.apache.poi.hwpf.usermodel.Range;
//import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.events.SelectionEvent;
//import org.eclipse.swt.events.SelectionListener;
//import org.eclipse.swt.widgets.FileDialog;
//
//import com.wanli.swing.frame.MainWindow;
//
//public class OpenListener implements SelectionListener {
//
//	@Override
//	public void widgetDefaultSelected(SelectionEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void widgetSelected(SelectionEvent arg0) {
//		FileDialog openFileDialog = new FileDialog(MainWindow.shell, SWT.OPEN);
//		openFileDialog.setText(" 打开文件");//设置对话框标题
//		openFileDialog.setFilterExtensions( new String[] { "*. *", "*.txt", "*.doc", "*.docx"});//设置文件扩展名
//		openFileDialog.setFilterNames( new String[] { " 所有类型(*. *)","word格式(*.doc, *.docx)", " 文本格式(*.txt)" });//设置默认文件名
//		openFileDialog.setFilterPath("C:\\");//设置打开文件的默认路径
//		// 打开对话框, 返回一个所选文件的路径
//		String selectedOpenFile = openFileDialog.open();
//		int lastPoint = selectedOpenFile.lastIndexOf(".");
//		String fileFormat = selectedOpenFile.substring(lastPoint);
//		String text = null;
//		if (selectedOpenFile != null) { 
//			File file = new File(selectedOpenFile);
//			try {
//				FileInputStream in = new FileInputStream(file);
//				switch (fileFormat) {
//					case ".doc":
//						HWPFDocument extractor = new HWPFDocument(in);
//						text = extractor.getDocumentText();
//						System.out.println(text);
//						MainWindow.textFile.setText(text);
//						in.close();
//						break;
//					
//					case ".docx":
//						XWPFDocument docx = new XWPFDocument(in);
//						XWPFWordExtractor extractor2 = new XWPFWordExtractor(docx);
//						text = extractor2.getText();
//						System.out.println(text);
//						MainWindow.textFile.setText(text);
//						in.close();
//						break;
//						
//					case ".txt":
//						BufferedReader br = new BufferedReader(new FileReader(file));
//						String line = null;
//						while ((line = br.readLine()) != null) {
//							if (text == null) {
//								text = "";
//							}
//							text = text + line + "\n";
//						}
//						System.out.println(text);
//						MainWindow.textFile.setText(text);
//						break;
//					default:
//						break;
//					}
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
//}
