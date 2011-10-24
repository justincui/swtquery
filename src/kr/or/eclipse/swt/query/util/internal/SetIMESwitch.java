package kr.or.eclipse.swt.query.util.internal;

import org.eclipse.swt.widgets.IME;
import org.eclipse.swt.widgets.Widget;
import kr.or.eclipse.swt.query.util.WidgetSwitchWithArgument;
import org.eclipse.swt.widgets.Canvas;
/*
 * generated by SWT Query SDK at Mon Oct 24 19:21:20 KST 2011
 */ 
public class SetIMESwitch extends WidgetSwitchWithArgument<Object, IME> {
	public void setProperty(Widget widget, IME value){
		doSwitch(widget, value);
	}

	public Object caseCanvas(Canvas widget, IME value){
		widget.setIME(value);
		return null;
	}
}
    