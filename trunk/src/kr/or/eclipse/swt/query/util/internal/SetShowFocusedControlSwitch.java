package kr.or.eclipse.swt.query.util.internal;

import java.lang.Boolean;
import org.eclipse.swt.widgets.Widget;
import kr.or.eclipse.swt.query.util.WidgetSwitchWithArgument;
import org.eclipse.swt.custom.ScrolledComposite;
/*
 * generated by SWT Query SDK at Mon Oct 24 19:21:20 KST 2011
 */ 
public class SetShowFocusedControlSwitch extends WidgetSwitchWithArgument<Object, Boolean> {
	public void setProperty(Widget widget, Boolean value){
		doSwitch(widget, value);
	}

	public Object caseScrolledComposite(ScrolledComposite widget, Boolean value){
		widget.setShowFocusedControl(value);
		return null;
	}
}
    