package kr.or.eclipse.swt.query.util.internal;

import java.lang.String;
import org.eclipse.swt.widgets.Widget;
import kr.or.eclipse.swt.query.util.WidgetSwitchWithArgument;
import org.eclipse.ui.dialogs.FilteredList;
/*
 * generated by SWT Query SDK at Mon Oct 24 19:21:20 KST 2011
 */ 
public class SetFilterSwitch extends WidgetSwitchWithArgument<Object, String> {
	public void setProperty(Widget widget, String value){
		doSwitch(widget, value);
	}

	public Object caseFilteredList(FilteredList widget, String value){
		widget.setFilter(value);
		return null;
	}
}
    