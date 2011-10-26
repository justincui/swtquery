package kr.or.eclipse.swt.query.test;

import static kr.or.eclipse.swt.query.SWTQuery.$;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class Test1 {
	public static void main(String[] args) {
		final Display display = Display.getDefault();
		final Shell shell = new Shell();
		shell.setLayout(new GridLayout());

		ToolBar toolBar = new ToolBar(shell, SWT.FLAT);
		new ToolItem(toolBar, 0).setText("Grid");
		new ToolItem(toolBar, 0).setText("Fill");
		new ToolItem(toolBar, 0).setText("Row");

		new ToolItem(toolBar, SWT.SEPARATOR);

		new ToolItem(toolBar, 0).setText("Default");
		new ToolItem(toolBar, 0).setText("Red");
		new ToolItem(toolBar, 0).setText("Yellow");

		final Composite composite = new Composite(shell, 0);
		$(composite).setGridLayoutData(GridData.FILL_BOTH);

		for (int i = 0; i < 10; i++) {
			Label l = new Label(composite, SWT.BORDER);
			l.setBackground(display.getSystemColor(SWT.COLOR_INFO_BACKGROUND));
			l.setLocation(i * 100, 50);
			l.setSize(80, 20);
			l.setText("http://eclipse.or.kr");
		}

		$(shell, "toolitem[text='Grid']").addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				$(composite, "*").setGridLayoutData(GridData.FILL_HORIZONTAL).markAnimationStart();
				$(composite).setLayout(new GridLayout()).layout();
			}
		});

		$(shell, "toolitem[text='Fill']").addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				$(composite, "*").setLayoutData(null).markAnimationStart();
				$(composite).setLayout(new FillLayout()).layout();
			}
		});

		$(shell, "toolitem[text='Row']").addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				$(composite, "*").setLayoutData(null).markAnimationStart();
				$(composite).setLayout(new RowLayout()).layout();
			}
		});

		$(shell, "toolitem[text='Red']").addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				$(shell, "composite>label").markAnimationStart().setBackground(display.getSystemColor(SWT.COLOR_RED));
			}
		});

		$(shell, "toolitem[text='Default']").addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				$(shell, "composite>label").markAnimationStart().setBackground(
						display.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
			}
		});

		$(shell, "toolitem[text='Yellow']").addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				$(shell, "composite>label").markAnimationStart().setBackground(display.getSystemColor(SWT.COLOR_YELLOW));
			}
		});

		$(shell).setSize(new Point(800, 300));

		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.readAndDispatch();
			}
		}
		display.dispose();
	}
}
