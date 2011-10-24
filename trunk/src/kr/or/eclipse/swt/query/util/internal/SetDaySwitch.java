package kr.or.eclipse.swt.query.util.internal;

import java.lang.Integer;
import org.eclipse.swt.widgets.Widget;
import kr.or.eclipse.swt.query.util.WidgetSwitchWithArgument;
import org.eclipse.swt.widgets.DateTime;
/*
 * generated by SWT Query SDK at Mon Oct 24 19:21:20 KST 2011
 */ 
public class SetDaySwitch extends WidgetSwitchWithArgument<Object, Integer> {
	public void setProperty(Widget widget, Integer value){
		doSwitch(widget, value);
	}

	public Object caseDateTime(DateTime widget, Integer value){
		widget.setDay(value);
		return null;
	}
}
    