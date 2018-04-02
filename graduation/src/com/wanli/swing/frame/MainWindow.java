package com.wanli.swing.frame;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class MainWindow extends ApplicationWindow {
		private Action newCreate;
		private Action openFile;
		private Action saveFile;
		private Action saveAsFile;
		private Action exit;
		private Action copyFile;
		private Action pasteFile;
		private Action cutFile;
		private Action setFont;
		private Action setColor;
		private Action selectAll;
		private Action formate;
		private Action about;
		private StyledText text;
		private Font font;
		private File file;
		private Color color;
		private StyleRange style, range;
		private String userName;
		boolean changes;;

		public MainWindow(String userName) {
			// 部署窗口
			super(null);
			this.userName = userName;
			newCreate = new NewCreateAction();
			openFile = new OpenFileAction();
			saveFile = new SaveFileAction();
			saveAsFile = new SaveAsFileAction();
			exit = new ExitAction();
			copyFile = new CopyFileAction();
			pasteFile = new PasteFileAction();
			cutFile = new CutFileAction();
			setFont = new SetFontAction();
			setColor = new SetColorAction();
			selectAll = new SelectAllAction();
			formate = new FormateAction();
			about = new AboutAction();
			addMenuBar();
			addToolBar(SWT.FLAT);
		}

		public void run() {
			setBlockOnOpen(true);
			open();
			Display.getCurrent().dispose();
		}

		@Override
		public Control createContents(Composite parent) {
			// 设置窗体大小
			Rectangle bounds = parent.getShell().getDisplay().getPrimaryMonitor().getBounds();
			int windowWidth = bounds.width;
			int windowHeight = (int) (bounds.height * 0.98);
			parent.getShell().setSize(windowWidth, (int) (bounds.height * 0.98));
			// 设置窗体标题
			parent.getShell().setText("Class For Everyone");
			Composite mainFrame = new Composite(parent, SWT.NONE);
			mainFrame.setLayout(null);
			Composite onlineView = new Composite(mainFrame, SWT.BORDER);
			onlineView.setBounds(0, 0, (int)(windowWidth * 0.3), (int)(windowHeight * 0.87));
			
			Composite createRoom = new Composite(onlineView, SWT.BORDER);
			createRoom.setBounds(0, 0, onlineView.getSize().x, (int)(onlineView.getSize().y * 0.04));
			
			Label createClass = new Label(createRoom, SWT.NONE);
			createClass.setBounds(0, 0, 76, 32);
			createClass.setText("New Label");
			
			Composite onlineNum = new Composite(onlineView, SWT.BORDER);
			onlineNum.setBounds(0, (int)(onlineView.getSize().y * 0.04), onlineView.getSize().x, (int)(onlineView.getSize().y * 0.04));
			
			Label welcome = new Label(onlineNum, SWT.NONE);
			welcome.setBounds(0, 0, 231, 32);
			welcome.setText("\u6B22\u8FCE\u60A8\uFF01" + this.userName);
			
			Label onlining = new Label(onlineNum, SWT.NONE);
			onlining.setBounds(407, 0, 165, 32);
			onlining.setText("\u5728\u7EBF\u4EBA\u6570\uFF1A");
			
			Composite onlineUser = new Composite(onlineView, SWT.BORDER);
			onlineUser.setBounds(0, onlineNum.getSize().y + createRoom.getSize().y, onlineView.getSize().x, (int)(onlineView.getSize().y * 0.92));
			onlineUser.setLayout(new FillLayout(SWT.HORIZONTAL));
			
			Tree tree = new Tree(onlineUser, SWT.BORDER);

			Composite textView = new Composite(mainFrame, SWT.BORDER);
			textView.setBounds((int)(windowWidth * 0.3), 0, (int)(windowWidth * 0.69), onlineView.getSize().y);
			textView.setLayout(new FillLayout());
			
			text = new StyledText(textView, SWT.BORDER | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
			text.setRightMargin(40);
			text.setLeftMargin(40);
			text.setBounds(0, 0, textView.getSize().x, textView.getSize().y);
			
			
			Composite statusBar = new Composite(mainFrame, SWT.BORDER);
			statusBar.setBounds(0, (int)(windowHeight * 0.87), windowWidth, 33);

			parent.getShell().addControlListener(new ControlListener() {
				
				@Override
				public void controlResized(ControlEvent arg0) {
//					int windowWidth = parent.getShell().getSize().x;
//					int windowHeight = parent.getShell().getSize().y;
//					onlineView.setBounds(0, 0, (int)(windowWidth * 0.3), (int)(windowHeight * 0.87));
//					createRoom.setBounds(0, 0, onlineView.getSize().x, (int)(onlineView.getSize().y * 0.04));
//					onlineNum.setBounds(0, (int)(onlineView.getSize().y * 0.04), onlineView.getSize().x, (int)(onlineView.getSize().y * 0.04));
//					onlineUser.setBounds(0, onlineNum.getSize().y + createRoom.getSize().y, onlineView.getSize().x, (int)(onlineView.getSize().y * 0.92));
//					textView.setBounds((int)(windowWidth * 0.3), 0, (int)(windowWidth * 0.69), onlineView.getSize().y);
////					System.out.println(parent.getShell().getSize().x);
//					statusBar.setBounds(0, (int)(windowHeight * 0.87), windowWidth, 33);
					int windowWidth = parent.getShell().getSize().x;
					int windowHeight = parent.getShell().getSize().y;
					onlineView.setBounds(0, 0, (int)(windowWidth * 0.3), (int)(windowHeight - 130));
//					System.out.println(windowHeight + ", " +(int)(windowHeight * 0.87));
					createRoom.setBounds(0, 0, onlineView.getSize().x, 33);
					onlineNum.setBounds(0, 33, onlineView.getSize().x, 33);
					onlineUser.setBounds(0, 66, onlineView.getSize().x, onlineView.getSize().y - 70);
					textView.setBounds((int)(windowWidth * 0.3), 0, (int)(windowWidth * 0.69), onlineView.getSize().y);
//					System.out.println(parent.getShell().getSize().x);
					statusBar.setBounds(0, windowHeight - 33, windowWidth, 33);
				}
				
				@Override
				public void controlMoved(ControlEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			return mainFrame;
		}

		protected MenuManager createMenuManager() {
			MenuManager menuBar = new MenuManager();
			MenuManager fileMenu = new MenuManager(" 文件(&F)");
			MenuManager editorMenu = new MenuManager(" 编辑(&E)");
			MenuManager helpMenu = new MenuManager(" 帮助(&H)");
			// 在文件菜单项添加下拉菜单
			fileMenu.add(newCreate);
			fileMenu.add(openFile);
			fileMenu.add(new Separator());
			fileMenu.add(saveFile);
			fileMenu.add(saveAsFile);
			fileMenu.add(new Separator());
			fileMenu.add(exit);
			// 在编辑菜单下添加下拉菜单
			editorMenu.add(copyFile);
			editorMenu.add(pasteFile);
			editorMenu.add(cutFile);
			editorMenu.add(new Separator());
			editorMenu.add(setFont);
			editorMenu.add(setColor);
			editorMenu.add(new Separator());
			editorMenu.add(selectAll);
			editorMenu.add(formate);
			helpMenu.add(about);
			// 在 menuBar 上添加文件菜单、编辑菜单和帮助菜单
			menuBar.add(fileMenu);
			menuBar.add(editorMenu);
			menuBar.add(helpMenu);
			return menuBar;
		}

		// 在工具栏上添加工具栏按钮
		protected ToolBarManager createToolBarManager(int style) {
			ToolBarManager toolBarManager = new ToolBarManager(style);
			toolBarManager.add(new NewCreateAction());
			toolBarManager.add(new OpenFileAction());
			toolBarManager.add(new SaveFileAction());
			toolBarManager.add(new Separator());
			toolBarManager.add(new CopyFileAction());
			toolBarManager.add(new PasteFileAction());
			toolBarManager.add(new CutFileAction());
			toolBarManager.add(new Separator());
			toolBarManager.add(new BlodAction());
			toolBarManager.add(new ItalicAction());
			toolBarManager.add(new UnderlineAction());
			return toolBarManager;
		}

		class NewCreateAction extends Action {
			public NewCreateAction() {
				super("NewCreateAction@Ctrl+N", Action.AS_PUSH_BUTTON);
				setText(" 新建");
				try {
					// 载入图像
					ImageDescriptor icon = ImageDescriptor.createFromURL(new URL("file:image/new.png"));
					setImageDescriptor(icon);
				} catch (MalformedURLException e) {
					System.err.println(e.getMessage());
				}
			}

			public void run() {
				// 在新建文本之前，判断当前文本是否需要保存
				if (judgeTextSave()) {
					text.setText("");
				}
			}
		}

		class OpenFileAction extends Action {
			public OpenFileAction() {
				super("OpenFileAction@Ctrl+O", Action.AS_PUSH_BUTTON);
				setText(" 打开");
				try {
					// 载入图像
					ImageDescriptor icon = ImageDescriptor.createFromURL(new URL("file:image/open.png"));
					setImageDescriptor(icon);
				} catch (MalformedURLException e) {
					System.err.println(e.getMessage());
				}
			}

			public void run() {
				// 在打开新的文件之前，判断是否保存当前文件
				if (judgeTextSave())
					OpenTextFile();
			}
		}

		class SaveFileAction extends Action {
			public SaveFileAction() {
				super("SaveFileAction@Ctrl+S", Action.AS_PUSH_BUTTON);
				setText(" 保存");
				try {
					// 载入图像
					ImageDescriptor icon = ImageDescriptor.createFromURL(new URL("file:image/save.png"));
					setImageDescriptor(icon);
				} catch (MalformedURLException e) {
					System.err.println(e.getMessage());
				}
			}

			public void run() {
				saveTextFile();
			}
		}

		class SaveAsFileAction extends Action {
			public SaveAsFileAction() {
				super(" 另存为@Ctrl+A", Action.AS_PUSH_BUTTON);
			}

			public void run() {
				saveFileAs();
			}
		}

		class ExitAction extends Action {
			public ExitAction() {
				super(" 退出@Ctrl+E", Action.AS_PUSH_BUTTON);
			}

			public void run() {
				getShell().dispose();
			}
		}

		class CopyFileAction extends Action {
			public CopyFileAction() {
				super("CopyFileAction@Ctrl+C", Action.AS_PUSH_BUTTON);
				setText(" 复制");
				try {
					// 载入图像
					ImageDescriptor icon = ImageDescriptor.createFromURL(new URL("file:image/copy.png"));
					setImageDescriptor(icon);
				} catch (MalformedURLException e) {
					System.err.println(e.getMessage());
				}
			}

			public void run() {
				text.copy();
			}
		}

		class PasteFileAction extends Action {
			public PasteFileAction() {
				super("PasteFileAction@Ctrl+V", Action.AS_PUSH_BUTTON);
				setText(" 粘贴");
				try {
					// 载入图像
					ImageDescriptor icon = ImageDescriptor.createFromURL(new URL("file:image/paste.png"));
					setImageDescriptor(icon);
				} catch (MalformedURLException e) {
					System.err.println(e.getMessage());
				}
			}

			public void run() {
				text.paste();
			}
		}

		class CutFileAction extends Action {
			public CutFileAction() {
				super("CutFileAction @Ctrl+X", Action.AS_PUSH_BUTTON);
				setText(" 剪切");
				try {
					// 载入图像
					ImageDescriptor icon = ImageDescriptor.createFromURL(new URL("file:image/cut.png"));
					setImageDescriptor(icon);
				} catch (MalformedURLException e) {
					System.err.println(e.getMessage());
				}
			}

			public void run() {
				text.cut();
			}
		}

		class SetFontAction extends Action {
			public SetFontAction() {
				super(" 设置字体@Alt+F", Action.AS_PUSH_BUTTON);
			}

			public void run() {
				FontDialog fontDialog = new FontDialog(getShell());
				fontDialog.setFontList((text.getFont()).getFontData());
				FontData fontData = fontDialog.open();
				if (fontData != null) {
					if (font != null) {
						font.dispose();
					}
					font = new Font(getShell().getDisplay(), fontData);
					text.setFont(font);
				}
			}
		}

		class SetColorAction extends Action {
			public SetColorAction() {
				super(" 设置颜色@Alt+C", Action.AS_PUSH_BUTTON);
			}

			public void run() {
				// 定义颜色选择对话框
				ColorDialog dlg = new ColorDialog(getShell());
				// 打开对话框
				RGB rgb = dlg.open();
				if (rgb != null) {
					// 定义 color 对象
					color = new Color(getShell().getDisplay(), rgb);
					// 定义 point 对象，获取选择范围。
					Point point = text.getSelectionRange();
					for (int i = point.x; i < point.x + point.y; i++) {
						// 获得选中的字体样式和范围
						range = text.getStyleRangeAtOffset(i);
						// 如果字体设置了其他样式( 如加粗、斜体、加下划线)
						if (range != null) {
							/**
							 * 设置一个与原来 StyleRange 的值相同的 StyleRange
							 */
							style = (StyleRange) range.clone();
							style.start = i;
							style.length = 1;
							// 设置前景颜色
							style.foreground = color;
						} else {

							style = new StyleRange(i, 1, color, null, SWT.NORMAL);
						}
						text.setStyleRange(style);
					}
				}
			}
		}

		class SelectAllAction extends Action {
			public SelectAllAction() {
				super(" 全选@Alt+A", Action.AS_PUSH_BUTTON);
			}

			public void run() {
				text.selectAll();
			}
		}

		class FormateAction extends Action {
			public FormateAction() {
				super(" 格式化@Ctrl+W", Action.AS_CHECK_BOX);
			}

			public void run() {
				text.setWordWrap(isChecked());
			}
		}

		class AboutAction extends Action {
			public AboutAction() {
				super(" 关于@Ctrl+H", Action.AS_PUSH_BUTTON);
			}

			public void run() {
				MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_INFORMATION | SWT.OK);
				messageBox.setMessage(" 文本编辑器 2.0 版本！ ");
				messageBox.open();
			}
		}

		class BlodAction extends Action {
			public BlodAction() {
				setText(" 加粗");
				try {
					// 载入图像
					ImageDescriptor icon = ImageDescriptor.createFromURL(new URL("file:image/bold.png"));
					setImageDescriptor(icon);
				} catch (MalformedURLException e) {
					System.err.println(e.getMessage());
				}
			}

			public void run() {
				Point point = text.getSelectionRange();
				for (int i = point.x; i < point.x + point.y; i++) {
					StyleRange range = text.getStyleRangeAtOffset(i);
					if (range != null) {
						style = (StyleRange) range.clone();
						style.start = i;
						style.length = 1;
					} else {
						style = new StyleRange(i, 1, null, null, SWT.NORMAL);
					}
					// 加粗字体
					style.fontStyle ^= SWT.BOLD;
					text.setStyleRange(style);
				}
			}
		}

		class ItalicAction extends Action {
			public ItalicAction() {
				setText(" 斜体");
				try {
					// 载入图像
					ImageDescriptor icon = ImageDescriptor.createFromURL(new URL("file:image/italic.png"));
					setImageDescriptor(icon);
				} catch (MalformedURLException e) {
					System.err.println(e.getMessage());
				}
			}

			public void run() {
				Point point = text.getSelectionRange();
				for (int i = point.x; i < point.x + point.y; i++) {
					range = text.getStyleRangeAtOffset(i);
					if (range != null) {
						style = (StyleRange) range.clone();
						style.start = i;
						style.length = 1;
					} else {
						style = new StyleRange(i, 1, null, null, SWT.NORMAL);
					}
					// 设置为斜体
					style.fontStyle ^= SWT.ITALIC;
					text.setStyleRange(style);
				}
			}
		}

		class UnderlineAction extends Action {
			public UnderlineAction() {
				setText(" 下划线");
				try {
					// 载入图像
					ImageDescriptor icon = ImageDescriptor.createFromURL(new URL("file:image/underline.png"));
					setImageDescriptor(icon);
				} catch (MalformedURLException e) {
					System.err.println(e.getMessage());
				}
			}

			public void run() {
				Point point = text.getSelectionRange();
				for (int i = point.x; i < point.x + point.y; i++) {
					range = text.getStyleRangeAtOffset(i);
					if (range != null) {
						style = (StyleRange) range.clone();
						style.start = i;
						style.length = 1;
					} else {
						style = new StyleRange(i, 1, null, null, SWT.NORMAL);
					}
					// 设置下划线
					style.underline = !style.underline;
					text.setStyleRange(style);
				}
			}
		}

		boolean judgeTextSave() {
			if (!changes)
				return true;
			MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_WARNING | SWT.YES | SWT.NO | SWT.CANCEL);
			messageBox.setMessage(" 是否保存对文件的更改？ ");
			messageBox.setText(" 文本编辑器 V2.0");
			int message = messageBox.open();
			if (message == SWT.YES) {
				return saveTextFile();
			} else if (message == SWT.NO) {
				return true;
			} else {
				return false;
			}
		}

		boolean OpenTextFile() {
			// 定义对话框，类型为打开型
			FileDialog dialog = new FileDialog(getShell(), SWT.OPEN);
			// 设置对话框打开的限定类型
			dialog.setFilterExtensions(new String[] { " *.txt", " *. *" });
			// 打开对话框，并返回打开文件的路径
			String openFile = dialog.open();
			if (openFile == null) {
				return false;
			}
			/**
			 * java.io 包的 File 类专门处理文件，并获取文件相关信息。 File 类的构造方法： public File(String
			 * pathnames) 例如 ： File file=new(D:\my.java) public File(File
			 * parent,String child) 例如： File file=new(parent,"my.java") parent
			 * 指文件所在目录的文件对象 public File(String parent,String child) 例如:File
			 * file=new(dir,"my.java") dir 指文件所在目录的字符串
			 */
			file = new File(openFile);
			try {
				/**
				 * FileReader 文件字符流 构造方法： public FileReader(File file) 例如：
				 * FileReader fileReade=new FileReader(file) public
				 * FileReader(String filenames) filenames 为包含文件名的字符串
				 */
				FileReader fileReader = new FileReader(file);
				/**
				 * BufferedReader 类用来把字符流的字符读入缓冲区
				 */
				BufferedReader reader = new BufferedReader(fileReader);
				/**
				 * 对 Stringbuffer 字符串缓冲实例化
				 */
				StringBuffer sb = new StringBuffer();
				String line = null;
				while ((line = reader.readLine()) != null) {
					/**
					 * 通过 append() 方法实现将字符串添加到字符缓冲区。 也可以通过 insert() 方法将字符串插入缓冲区中
					 */
					sb.append(line);
					sb.append("\r\n");
				}
				text.setText(sb.toString());
				return true;
			} catch (IOException e) {
			}
			return false;
		}

		boolean saveTextFile() {
			if (file == null) {
				// 定义文件选择对话框，类型为保存型
				FileDialog dialog = new FileDialog(getShell(), SWT.SAVE);
				dialog.setText(" 保存");
				// 设置对话框保存的限定类型
				dialog.setFilterExtensions(new String[] { " *.txt", "*.doc", "*.xls", "*. *" });
				// 打开对话框，并返回保存文件的路径
				String saveFile = dialog.open();
				if (saveFile == null) {
					return false;
				}
				file = new File(saveFile);
			}
			try {
				FileWriter writer = new FileWriter(file);
				writer.write(text.getText());
				writer.close();
				changes = false;
				return true;
			} catch (IOException e) {
			}
			return false;
		}

		boolean saveFileAs() {
			SafeSaveDialog dlg = new SafeSaveDialog(getShell());
			String temp = dlg.open();
			if (temp == null) {
				return false;
			}
			file = new File(temp);
			try {
				FileWriter writer = new FileWriter(file);
				writer.write(text.getText());
				writer.close();
			} catch (IOException e) {
			}
			return false;
		}

		class SafeSaveDialog {
			private FileDialog dlg;

			public SafeSaveDialog(Shell shell) {
				dlg = new FileDialog(shell, SWT.SAVE);
				dlg.setFilterExtensions(new String[] { "*.txt", "*.doc", "*.xls", "*. *" });
			}

			public String open() {
				String fileName = null;
				boolean done = false;
				while (!done) {
					// 打开另存为对话框，并返回保存路径
					fileName = dlg.open();
					if (fileName == null) {
						done = true;
					} else {
						// 判断保存的文件是否已经存在
						File file = new File(fileName);
						if (file.exists()) {
							// 若文件存在，则弹出提示性的对话框
							MessageBox mb = new MessageBox(dlg.getParent(), SWT.ICON_WARNING | SWT.YES | SWT.NO);
							// 提示性的信息
							mb.setMessage(fileName + " 已经存在，是否将该文件替换?");
							/**
							 * 单击"yes" 按钮将磁盘上的文件替换 否则重新填写文件名
							 */
							done = mb.open() == SWT.YES;
						} else {
							done = true;
						}
					}
				}
				return fileName;
			}
		}
}
