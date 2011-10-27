package kr.or.eclipse.swt.query.test;

import static kr.or.eclipse.swt.query.SWTQuery.$;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class Test1 {
	public static void main(String[] args) throws IOException {
		Display display = Display.getDefault();

		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout());
		for (int i = 0; i < 5; i++) {
			Group g = new Group(shell, SWT.NORMAL);
			g.setText("group " + i);
		}

		$(shell, "group").setGridLayoutData(GridData.FILL_BOTH).setRowLayout().create(Button.class, SWT.PUSH).setText("Áö¶ö")
		.parent().create(Label.class, SWT.NORMAL).setText("Áö¶ö¸¶");

		shell.pack();
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
