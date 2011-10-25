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
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;

public class Test1 {
	public static void main(String[] args) {
		final Display display = Display.getDefault();
		final Shell shell = new Shell();
		shell.setLayout(new GridLayout(2, false));

		Group group = new Group(shell, SWT.NORMAL);
		group.setText("������ �����ϼ���.");
		group.setLayout(new GridLayout(3, false));
		group.setData("role", "file");

		for (int i = 0; i < 10; i++) {
			new Link(group, SWT.NORMAL).setText("<a href=\"#\">����</a>");
			new Text(group, SWT.BORDER);
			new Button(group, SWT.PUSH).setText("ã��..");
		}

		Group group2 = new Group(shell, SWT.NORMAL);
		group2.setText("�׸� ����.");
		group2.setLayout(new GridLayout(3, false));

		Tree tree = new Tree(shell, SWT.NORMAL);
		for (int i = 0; i < 5; i++) {
			new TreeItem(tree, SWT.NORMAL).setText("xx");
		}
		$(tree).setGridLayoutData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		$(tree, "*").debug("a");

		for (int i = 0; i < 10; i++) {
			new Link(group2, SWT.NORMAL).setText("<a href=\"#\">����</a>");
			new Text(group2, SWT.BORDER);
			new Button(group2, SWT.PUSH).setText("ã��..");
		}

		$(shell, "group").setGridLayoutData(GridData.FILL_BOTH);
		$(shell, "group > text").setGridLayoutData(GridData.FILL_HORIZONTAL);
		$(shell, "group > button:push[text='ã��..']").addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				FileDialog dialog = new FileDialog(shell, SWT.OPEN);
				String result = dialog.open();
				if (result != null) {
					$(event).prev().setText(result).setData("url", result);
				}
			}
		});
		$(shell, "link").setToolTipText("��ũ�� ���� ������ �� �� �ֽ��ϴ�.");
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

		new Button(shell, SWT.PUSH).setText("left");

		$(shell, "button[text='left']").addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				$(shell, "group[data-role=file] > *").toggleEnabled();
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
