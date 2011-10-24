package kr.or.eclipse.swt.query.util.internal;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Widget;
import kr.or.eclipse.swt.query.util.WidgetSwitch;
import org.eclipse.ui.forms.widgets.ToggleHyperlink;
/*
 * generated by SWT Query SDK at Mon Oct 24 19:21:20 KST 2011
 */ 
public class GetDecorationColorSwitch extends WidgetSwitch<Color> {
	public Color getProperty(Widget widget){
		return doSwitch(widget);
	}

	public Color caseToggleHyperlink(ToggleHyperlink widget){
		return widget.getDecorationColor();
	}
}
    