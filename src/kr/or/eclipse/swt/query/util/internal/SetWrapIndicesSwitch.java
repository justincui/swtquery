package kr.or.eclipse.swt.query.util.internal;


import org.eclipse.swt.widgets.Widget;
import kr.or.eclipse.swt.query.util.WidgetSwitchWithArgument;
import org.eclipse.swt.widgets.CoolBar;
/*
 * generated by SWT Query SDK at Mon Oct 24 19:21:20 KST 2011
 */ 
public class SetWrapIndicesSwitch extends WidgetSwitchWithArgument<Object, int[]> {
	public void setProperty(Widget widget, int[] value){
		doSwitch(widget, value);
	}

	public Object caseCoolBar(CoolBar widget, int[] value){
		widget.setWrapIndices(value);
		return null;
	}
}
    