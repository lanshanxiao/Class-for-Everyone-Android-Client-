package com.wanli.swing;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.TitleEvent;
import org.eclipse.swt.browser.TitleListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Test {

    static{
     System.setProperty("org.eclipse.swt.browser.XULRunnerPath", "e:\\xulrunner"); 
    } 
    public static void main(String[] args) {

        Display display = new Display (); 
        final Shell shell = new Shell (display); 
        FillLayout layout = new FillLayout(); 
        shell.setLayout(layout); 

        Browser browser = new Browser(shell, SWT.MOZILLA);  //1
        browser.addTitleListener(new TitleListener(){  //2
            public void changed(TitleEvent event) { 
                shell.setText(event.title); 
            } 
        }); 
        browser.setUrl("www.baidu.com"); //3
        shell.open (); 
        while (!shell.isDisposed ()) { 
            if (!display.readAndDispatch ()) display.sleep (); 
        } 
        display.dispose ();
    }
}
