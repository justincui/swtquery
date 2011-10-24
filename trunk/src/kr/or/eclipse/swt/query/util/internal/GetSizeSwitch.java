package kr.or.eclipse.swt.query.util.internal;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Widget;
import kr.or.eclipse.swt.query.util.WidgetSwitch;
import org.eclipse.swt.widgets.Caret;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.CoolItem;
/*
 * generated by SWT Query SDK at Mon Oct 24 19:21:20 KST 2011
 */ 
public class GetSizeSwitch extends WidgetSwitch<Point> {
	public Point getProperty(Widget widget){
		return doSwitch(widget);
	}

	public Point caseCaret(Caret widget){
		return widget.getSize();
	}
	public Point caseShell(Shell widget){
		return widget.getSize();
	}
	public Point caseDecorations(Decorations widget){
		return widget.getSize();
	}
	public Point caseControl(Control widget){
		return widget.getSize();
	}
	public Point caseCoolItem(CoolItem widget){
		return widget.getSize();
	}
}
    