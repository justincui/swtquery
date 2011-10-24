package kr.or.eclipse.swt.query.util.internal;

import org.eclipse.ui.forms.HyperlinkSettings;
import org.eclipse.swt.widgets.Widget;
import kr.or.eclipse.swt.query.util.WidgetSwitchWithArgument;
import org.eclipse.ui.forms.widgets.FormText;
/*
 * generated by SWT Query SDK at Mon Oct 24 19:21:20 KST 2011
 */ 
public class SetHyperlinkSettingsSwitch extends WidgetSwitchWithArgument<Object, HyperlinkSettings> {
	public void setProperty(Widget widget, HyperlinkSettings value){
		doSwitch(widget, value);
	}

	public Object caseFormText(FormText widget, HyperlinkSettings value){
		widget.setHyperlinkSettings(value);
		return null;
	}
}
    