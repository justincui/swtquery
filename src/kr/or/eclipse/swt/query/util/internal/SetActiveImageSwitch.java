package kr.or.eclipse.swt.query.util.internal;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Widget;
import kr.or.eclipse.swt.query.util.WidgetSwitchWithArgument;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
/*
 * generated by SWT Query SDK at Mon Oct 24 19:21:20 KST 2011
 */ 
public class SetActiveImageSwitch extends WidgetSwitchWithArgument<Object, Image> {
	public void setProperty(Widget widget, Image value){
		doSwitch(widget, value);
	}

	public Object caseImageHyperlink(ImageHyperlink widget, Image value){
		widget.setActiveImage(value);
		return null;
	}
}
    