package kr.or.eclipse.swt.query.examples;

import static kr.or.eclipse.swt.query.SWTQuery.$;
import static kr.or.eclipse.swt.query.graphics.GCQuery.$gc;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import kr.or.eclipse.swt.query.IWidgetFunction;
import kr.or.eclipse.swt.query.SWTQuery;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Widget;

public class AddressBook {
	public static void main(String[] args) {
		final Display display = Display.getDefault();
		final SWTQuery $shell = $(new Shell(display));

		// UI 구성
		$shell.setGridLayout();

		SWTQuery $toolbar = $shell.create(ToolBar.class);
		$toolbar.create(ToolItem.class).setText("추가").setData("role", "add");
		$shell.create(Table.class, SWT.FULL_SELECTION).setGridLayoutData(GridData.FILL_BOTH).setData("role", "viewer");

		// 공용 리소스
		final Image emptyImage = new Image(display, 32, 32);
		$shell.chainDispose(emptyImage);

		// 컨트롤러

		$shell.select("toolitem[data-role=add]").addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				Shell s = $shell.as();
				InputDialog dialog = new InputDialog(s, "Address Book", "EMail:", "", null);
				if (dialog.open() == Window.OK) {
					String mailAddress = dialog.getValue();
					final SWTQuery $newItem = $shell.select("table[data-role=viewer]").create(TableItem.class);
					$newItem.setText(mailAddress).setImage(emptyImage);
					String hex = md5Hex(mailAddress);
					try {
						final URL url = new URL("http://www.gravatar.com/avatar/" + hex + "?s=32");
						new Thread() {
							public void run() {
								try {
									final Image gravatarIcon = new Image(display, url.openStream());
									$newItem.schedule(new IWidgetFunction() {
										@Override
										public void doFunction(Widget widget) {
											$(widget).setImage(gravatarIcon).chainDispose(gravatarIcon);
										}
									});

								} catch (IOException e) {
									e.printStackTrace();
								}
							};
						}.start();
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}

				}
			}
		});

		$shell.open().runEventLoopUntilDispose();
	}

	public static String hex(byte[] array) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; ++i) {
			sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString();
	}

	public static String md5Hex(String message) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return hex(md.digest(message.getBytes("CP1252")));
		} catch (NoSuchAlgorithmException e) {
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}

}
