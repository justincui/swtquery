package kr.or.eclipse.swt.query.test;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import static kr.or.eclipse.swt.query.SWTQuery.$;

public class Test1 {
	public static void main(String[] args) {
		final Display display = Display.getDefault();
		final Shell shell = new Shell();

		final Label l1 = new Label(shell, SWT.NORMAL);
		l1.setLocation(50, 50);

		final Label l2 = new Label(shell, SWT.NORMAL);
		l2.setLocation(500, 50);
		$(l1, l2).setSize(new Point(100, 20)).setText("Click").setCursor(display.getSystemCursor(SWT.CURSOR_HAND));

		final Map<String, Object> left = new HashMap<String, Object>();
		left.put("location", new Point(50, 250));
		left.put("background", display.getSystemColor(SWT.COLOR_DARK_GREEN));
		left.put("foreground", display.getSystemColor(SWT.COLOR_WHITE));
		left.put("size", new Point(100, 100));

		final Map<String, Object> right = new HashMap<String, Object>();
		right.put("location", new Point(200, 250));
		right.put("background", display.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		right.put("foreground", display.getSystemColor(SWT.COLOR_WIDGET_FOREGROUND));
		right.put("size", new Point(100, 20));

		final Listener swap = new Listener() {
			boolean toggle = false;

			@Override
			public void handleEvent(Event event) {
				$(l1, l2).markAnimationStart();
				if (toggle) {
					$(l1).addProperties(left);
					$(l2).addProperties(right);
				} else {
					$(l1).addProperties(right);
					$(l2).addProperties(left);
				}
				toggle = !toggle;
			}
		};
		$(l1, l2).addListener(SWT.MouseDown, swap);
		
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.readAndDispatch();
			}
		}
		display.dispose();
	}
}
