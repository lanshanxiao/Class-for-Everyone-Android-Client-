package com.wanli.swing.frame;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.Shell;

public class FontWindow implements SelectionListener {
	private Shell shell;
	private Display display;
	public FontWindow(Shell shell, Display display) {
		this.shell = shell;
		this.display = display;
	}
	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void widgetSelected(SelectionEvent arg0) {
		FontDialog fontdialog = new FontDialog(shell, SWT.NONE);
//		fontdialog.setFontData (new FontData());
		// fontdialog.setRGB(new RGB(255, 0, 0));
		fontdialog.setFontList(null);
		FontData fontdata = fontdialog.open();
		RGB rgb = fontdialog.getRGB();
		if (fontdata != null){
//			MainWindow.textFile.setFont(new Font(display, fontdata));
//			MainWindow.textFile.setForeground(new Color(display, rgb));
		}
		
	}


	
	
}
