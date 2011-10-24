package kr.or.eclipse.swt.query.util.internal;

import java.lang.Boolean;
import org.eclipse.swt.widgets.Widget;
import kr.or.eclipse.swt.query.util.WidgetSwitch;
import org.eclipse.swt.custom.CBanner;
import org.eclipse.swt.custom.CTabFolder;
/*
 * generated by SWT Query SDK at Mon Oct 24 19:21:20 KST 2011
 */ 
public class GetSimpleSwitch extends WidgetSwitch<Boolean> {
	public Boolean getProperty(Widget widget){
		return doSwitch(widget);
	}

	public Boolean caseCBanner(CBanner widget){
		return widget.getSimple();
	}
	public Boolean caseCTabFolder(CTabFolder widget){
		return widget.getSimple();
	}
}
    