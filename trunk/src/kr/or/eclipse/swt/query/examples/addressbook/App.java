package kr.or.eclipse.swt.query.examples.addressbook;

import static kr.or.eclipse.swt.query.SWTQuery.$;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;

import kr.or.eclipse.swt.query.IWidgetFunction;
import kr.or.eclipse.swt.query.SWTQuery;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Widget;

public class App {
	private class UpdateContactIcon extends Thread {
		private String email;

		private UpdateContactIcon(String email) {
			super();
			this.email = email;
		}

		@Override
		public void run() {
			// GRAVATAR 서비스로 부터, 이메일 소유자의 아바타 이미지를 얻음.
			String gravatarURL = "http://www.gravatar.com/avatar/" + MD5HashUtil.md5Hex(email) + "?s=32";
			try {
				URL url = new URL(gravatarURL);
				final Image image = new Image(Display.getDefault(), url.openStream());
				$shell.schedule(new IWidgetFunction() {
					@Override
					public void doFunction(Widget widget) {
						SWTQuery $tableItem = $shell.select(MessageFormat.format("tableitem[text=\"{0}\"]", email));
						$tableItem.setImage(image).chainDispose(image);
					}
				});
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		App app = new App();
		app.contacts.add("jeeeyul@gmail.com");
		app.contacts.add("ameluna@gmail.com");
		app.run();
	}

	private ArrayList<String> contacts = new ArrayList<String>();

	private SWTQuery $shell;

	private Image emptyImage;

	protected void addNewContact() {
		InputDialog dialog = new InputDialog((Shell) $shell.as(), "새 메일 추가", "Email:", "", null);
		if (dialog.open() == Window.OK) {
			contacts.add(dialog.getValue());
			refresh();
		}
	}

	public void refresh() {
		SWTQuery $table = $shell.select("table[data-role=viewer]");

		for (String each : contacts) {
			if ($table.select(MessageFormat.format("tableitem[text=\"{0}\"]", each)).size() > 0) {
				continue;
			}
			$table.create(TableItem.class).setText(each).setImage(emptyImage);
			new UpdateContactIcon(each).start();
		}
	}

	public void run() {
		Display display = Display.getDefault();
		$shell = $(new Shell(display));
		this.emptyImage = new Image(display, 32, 32);

		new UI().create($shell);

		$shell.select("toolitem[text='추가']").addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				addNewContact();
			}
		});

		refresh();

		$shell.pack().open().runEventLoopUntilDispose();
	}
}
