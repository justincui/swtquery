package kr.or.eclipse.swt.query.util.internal;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Widget;
import kr.or.eclipse.swt.query.util.WidgetSwitchWithArgument;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.swt.widgets.Control;
/*
 * generated by SWT Query SDK at Mon Oct 24 19:21:20 KST 2011
 */ 
public class SetBackgroundImageSwitch extends WidgetSwitchWithArgument<Object, Image> {
	public void setProperty(Widget widget, Image value){
		doSwitch(widget, value);
	}

	public Object caseScrolledForm(ScrolledForm widget, Image value){
		widget.setBackgroundImage(value);
		return null;
	}
	public Object caseForm(Form widget, Image value){
		widget.setBackgroundImage(value);
		return null;
	}
	public Object caseControl(Control widget, Image value){
		widget.setBackgroundImage(value);
		return null;
	}
}
    