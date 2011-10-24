package kr.or.eclipse.swt.query.util.internal;

import java.lang.Boolean;
import org.eclipse.swt.widgets.Widget;
import kr.or.eclipse.swt.query.util.WidgetSwitchWithArgument;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Text;
/*
 * generated by SWT Query SDK at Mon Oct 24 19:21:20 KST 2011
 */ 
public class SetDoubleClickEnabledSwitch extends WidgetSwitchWithArgument<Object, Boolean> {
	public void setProperty(Widget widget, Boolean value){
		doSwitch(widget, value);
	}

	public Object caseStyledText(StyledText widget, Boolean value){
		widget.setDoubleClickEnabled(value);
		return null;
	}
	public Object caseText(Text widget, Boolean value){
		widget.setDoubleClickEnabled(value);
		return null;
	}
}
    