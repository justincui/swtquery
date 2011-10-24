package kr.or.eclipse.swt.query.util.internal;

import java.lang.Integer;
import org.eclipse.swt.widgets.Widget;
import kr.or.eclipse.swt.query.util.WidgetSwitch;
import org.eclipse.swt.custom.SashForm;
/*
 * generated by SWT Query SDK at Mon Oct 24 19:21:20 KST 2011
 */ 
public class GetSashWidthSwitch extends WidgetSwitch<Integer> {
	public Integer getProperty(Widget widget){
		return doSwitch(widget);
	}

	public Integer caseSashForm(SashForm widget){
		return widget.getSashWidth();
	}
}
    