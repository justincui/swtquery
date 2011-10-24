package kr.or.eclipse.swt.query.util.internal;

import java.lang.Integer;
import org.eclipse.swt.widgets.Widget;
import kr.or.eclipse.swt.query.util.WidgetSwitchWithArgument;
import org.eclipse.swt.widgets.Shell;
/*
 * generated by SWT Query SDK at Mon Oct 24 19:21:20 KST 2011
 */ 
public class SetImeInputModeSwitch extends WidgetSwitchWithArgument<Object, Integer> {
	public void setProperty(Widget widget, Integer value){
		doSwitch(widget, value);
	}

	public Object caseShell(Shell widget, Integer value){
		widget.setImeInputMode(value);
		return null;
	}
}
    