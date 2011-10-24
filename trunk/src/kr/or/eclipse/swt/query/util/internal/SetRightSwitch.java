package kr.or.eclipse.swt.query.util.internal;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;
import kr.or.eclipse.swt.query.util.WidgetSwitchWithArgument;
import org.eclipse.swt.custom.CBanner;
/*
 * generated by SWT Query SDK at Mon Oct 24 19:21:20 KST 2011
 */ 
public class SetRightSwitch extends WidgetSwitchWithArgument<Object, Control> {
	public void setProperty(Widget widget, Control value){
		doSwitch(widget, value);
	}

	public Object caseCBanner(CBanner widget, Control value){
		widget.setRight(value);
		return null;
	}
}
    