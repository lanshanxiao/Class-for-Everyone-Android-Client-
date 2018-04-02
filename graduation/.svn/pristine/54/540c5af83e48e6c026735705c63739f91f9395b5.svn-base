package com.wanli.swing.frame;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.wanli.swing.dao.RegistDao;
import com.wanli.swing.entities.UserBean;

public class LoginWindow {
	private static Text textPassword;
	private static LoginWindow window;
	private UserBean user;
	private Image coverImg;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public LoginWindow() {
		this.user = new UserBean();
	}

	public static void main(String[] args) {
		window = new LoginWindow();
		Display display = Display.getDefault();
		Shell shell = new Shell(display, SWT.DIALOG_TRIM);
		shell.setImage(SWTResourceManager.getImage("E:\\迅雷下载\\壁纸\\1.jpg"));
		shell.setSize(474, 365);
		center(display, shell);
		shell.setText("Class For Everyone");

		Composite globalLayout = new Composite(shell, SWT.NONE);
		globalLayout.setLocation(0, 0);
		globalLayout.setSize(527, 338);
		globalLayout.setLayout(null);
		// window.set(new Image(display, "E:\\迅雷下载\\壁纸\\【10.21】每周精选壁纸\\6.jpg"));
		// composite.setBackgroundImage(window.getHeadImg());

		Composite classforeveryone = new Composite(globalLayout, SWT.NONE);
		classforeveryone.setBounds(0, 0, 469, 154);
		classforeveryone.setBackgroundImage(SWTResourceManager.getImage("E:\\迅雷下载\\壁纸\\1.jpg"));

		Composite loginComposite = new Composite(globalLayout, SWT.NONE);
		loginComposite.setBounds(0, 152, 469, 186);

		Combo comboUser = new Combo(loginComposite, SWT.NONE);
		comboUser.setBounds(162, 24, 202, 32);

		textPassword = new Text(loginComposite, SWT.BORDER | SWT.PASSWORD);
		textPassword.setBounds(162, 55, 202, 25);

		Button bRememberMe = new Button(loginComposite, SWT.CHECK);
		bRememberMe.setBounds(162, 93, 92, 28);
		bRememberMe.setForeground(SWTResourceManager.getColor(240, 248, 255));
		bRememberMe.setText("\u8BB0\u4F4F\u5BC6\u7801");

		Button bAutoLogin = new Button(loginComposite, SWT.CHECK);
		bAutoLogin.setBounds(280, 93, 84, 28);
		bAutoLogin.setForeground(SWTResourceManager.getColor(240, 248, 255));
		bAutoLogin.setText("\u81EA\u52A8\u767B\u5F55");

		Link linkRegist = new Link(loginComposite, SWT.NONE);
		linkRegist.setBounds(370, 24, 72, 25);
		linkRegist.setText("<a>\u6CE8\u518C\u8D26\u53F7</a>");
		linkRegist.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent arg0) {

			}

			@Override
			public void mouseDown(MouseEvent arg0) {
				URI uri = null;
				try {
					uri = new URI("http://localhost:8080/graduation/regist.jsp");
					Desktop dtp = Desktop.getDesktop();
					dtp.browse(uri);
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			@Override
			public void mouseDoubleClick(MouseEvent arg0) {

			}

		});

		Link linkForget = new Link(loginComposite, SWT.NONE);
		linkForget.setBounds(370, 55, 72, 25);
		linkForget.setText("<a>\u627E\u56DE\u5BC6\u7801</a>");

		Button bLogin = new Button(loginComposite, SWT.NONE);
		bLogin.setBounds(162, 137, 202, 27);
		bLogin.setText("\u767B\u5F55");

		Composite composite = new Composite(loginComposite, SWT.NONE);
		composite.setBounds(32, 24, 98, 97);

		bLogin.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				window.getUser().setName(comboUser.getText());
				window.getUser().setPassword(textPassword.getText());
				RegistDao dao = new RegistDao();
				if (dao.getUserByNameAndPassword(window.getUser().getName(), window.getUser().getPassword())) {
					shell.dispose();
					new MainWindow(window.getUser().getName()).run();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {

			}
		});
		window.setCoverImg(new Image(display, "E:\\迅雷下载\\壁纸\\1.jpg"));

		shell.open();
		shell.layout();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

	}

	/**
	 * 设置窗口位于屏幕中间
	 * 
	 * @param display
	 *            设备
	 * @param shell
	 *            要调整位置的窗口对象
	 */
	public static void center(Display display, Shell shell) {
		Rectangle bounds = display.getPrimaryMonitor().getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation(x, y);
	}

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public Image getCoverImg() {
		return coverImg;
	}

	public void setCoverImg(Image coverImg) {
		this.coverImg = coverImg;
	}
}
