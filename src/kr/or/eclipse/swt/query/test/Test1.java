package kr.or.eclipse.swt.query.test;

import static kr.or.eclipse.swt.query.SWTQuery.$;

import java.io.File;

import kr.or.eclipse.swt.query.IWidgetFunction;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

public class Test1 {
	public static void main(String[] args) {
		final Display display = Display.getDefault();
		final Shell shell = new Shell();
		shell.setLayout(new GridLayout());

		Group group = new Group(shell, SWT.NORMAL);
		group.setText("파일을 선택하세요.");
		group.setLayout(new GridLayout(3, false));

		for (int i = 0; i < 10; i++) {
			new Link(group, SWT.NORMAL).setText("<a href=\"#\">열기</a>");
			new Text(group, SWT.BORDER);
			new Button(group, SWT.PUSH).setText("찾기..");
		}

		$(shell, "group").setGridLayoutData(GridData.FILL_BOTH);
		$(shell, "group > text").setGridLayoutData(GridData.FILL_HORIZONTAL);
		$(shell, "group > button:push[text='찾기..']").addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				FileDialog dialog = new FileDialog(shell, SWT.OPEN);
				String result = dialog.open();
				if (result != null) {
					$(event).prev().setText(result).setData("url", result);
				}
			}
		});
		$(shell, "link").setToolTipText("링크를 눌러 파일을 열 수 있습니다.");
		$(shell, "text").addListener(SWT.FocusIn, new Listener() {
			@Override
			public void handleEvent(Event event) {
				$(event).setBackground(display.getSystemColor(SWT.COLOR_INFO_BACKGROUND));
				$(event).schedule(new IWidgetFunction() {
					@Override
					public void doFunction(Widget w) {
						((Text) w).selectAll();
					}
				});
			}
		});

		$(shell, "text").addListener(SWT.FocusOut, new Listener() {
			@Override
			public void handleEvent(Event event) {
				$(event).setBackground(null);
			}
		});

		$(shell, "link").addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				File file = new File($(event).next().getText().trim());
				if (file.exists()) {
					Program.launch(file.getAbsolutePath());
				}
			}
		});

		$(shell, "*").addListener(SWT.SetData, new Listener() {
			@Override
			public void handleEvent(Event event) {
				System.out.println(event);
			}
		});

		shell.pack();
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.readAndDispatch();
			}
		}
		display.dispose();
	}
}
