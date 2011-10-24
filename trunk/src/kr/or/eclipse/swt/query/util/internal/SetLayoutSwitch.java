package kr.or.eclipse.swt.query.util.internal;

import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Widget;
import kr.or.eclipse.swt.query.util.WidgetSwitchWithArgument;
import org.eclipse.swt.widgets.Composite;
/*
 * generated by SWT Query SDK at Mon Oct 24 19:21:20 KST 2011
 */ 
public class SetLayoutSwitch extends WidgetSwitchWithArgument<Object, Layout> {
	public void setProperty(Widget widget, Layout value){
		doSwitch(widget, value);
	}

	public Object caseComposite(Composite widget, Layout value){
		widget.setLayout(value);
		return null;
	}
}
    