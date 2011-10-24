package kr.or.eclipse.swt.query.util.internal;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;
import kr.or.eclipse.swt.query.util.WidgetSwitch;
import org.eclipse.swt.custom.SashForm;
/*
 * generated by SWT Query SDK at Mon Oct 24 19:21:20 KST 2011
 */ 
public class GetMaximizedControlSwitch extends WidgetSwitch<Control> {
	public Control getProperty(Widget widget){
		return doSwitch(widget);
	}

	public Control caseSashForm(SashForm widget){
		return widget.getMaximizedControl();
	}
}
    