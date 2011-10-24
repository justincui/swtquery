package kr.or.eclipse.swt.query.util.internal;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Widget;
import kr.or.eclipse.swt.query.util.WidgetSwitchWithArgument;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
/*
 * generated by SWT Query SDK at Mon Oct 24 19:21:20 KST 2011
 */ 
public class SetTitleBarForegroundSwitch extends WidgetSwitchWithArgument<Object, Color> {
	public void setProperty(Widget widget, Color value){
		doSwitch(widget, value);
	}

	public Object caseExpandableComposite(ExpandableComposite widget, Color value){
		widget.setTitleBarForeground(value);
		return null;
	}
}
    