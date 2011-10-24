package kr.or.eclipse.swt.query.util.internal;

import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Widget;
import kr.or.eclipse.swt.query.util.WidgetSwitchWithArgument;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.MenuItem;
/*
 * generated by SWT Query SDK at Mon Oct 24 19:21:20 KST 2011
 */ 
public class SetMenuSwitch extends WidgetSwitchWithArgument<Object, Menu> {
	public void setProperty(Widget widget, Menu value){
		doSwitch(widget, value);
	}

	public Object caseCCombo(CCombo widget, Menu value){
		widget.setMenu(value);
		return null;
	}
	public Object caseControl(Control widget, Menu value){
		widget.setMenu(value);
		return null;
	}
	public Object caseMenuItem(MenuItem widget, Menu value){
		widget.setMenu(value);
		return null;
	}
}
    