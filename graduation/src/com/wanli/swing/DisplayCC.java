package com.wanli.swing;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.*;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;

import org.eclipse.swt.widgets.Display;

import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
 
public class DisplayCC {
	public static void main(String args[])
    {
        final Display display = new Display();
        final Shell shell = new Shell();
        shell.setSize(838, 665);
        shell.setText("·Ö×é¿ò");
        shell.setToolTipText("ShellÈÝÆ÷");
        shell.setLayout(null);
        
        Composite composite = new Composite(shell, SWT.NONE);
        composite.setBounds(0, 0, 822, 626);
        
        Tree tree = new Tree(composite, SWT.BORDER);
        tree.setBounds(95, 47, 268, 167);
        
        TreeItem treeItem = new TreeItem(tree, SWT.NONE);
        treeItem.setText("303");
        
        TreeItem trtmHuang = new TreeItem(treeItem, SWT.NONE);
        trtmHuang.setText("huang");
        treeItem.setExpanded(true);
         

        shell.open();
        while(!shell.isDisposed())
        {
            if(!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
         
         
    }
}
