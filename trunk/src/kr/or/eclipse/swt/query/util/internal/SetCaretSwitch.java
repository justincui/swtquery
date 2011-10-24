package kr.or.eclipse.swt.query.util.internal;

import org.eclipse.swt.widgets.Caret;
import org.eclipse.swt.widgets.Widget;
import kr.or.eclipse.swt.query.util.WidgetSwitchWithArgument;
import org.eclipse.swt.widgets.Canvas;
/*
 * generated by SWT Query SDK at Mon Oct 24 19:21:20 KST 2011
 */ 
public class SetCaretSwitch extends WidgetSwitchWithArgument<Object, Caret> {
	public void setProperty(Widget widget, Caret value){
		doSwitch(widget, value);
	}

	public Object caseCanvas(Canvas widget, Caret value){
		widget.setCaret(value);
		return null;
	}
}
    