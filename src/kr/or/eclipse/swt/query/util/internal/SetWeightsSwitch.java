package kr.or.eclipse.swt.query.util.internal;


import org.eclipse.swt.widgets.Widget;
import kr.or.eclipse.swt.query.util.WidgetSwitchWithArgument;
import org.eclipse.swt.custom.SashForm;
/*
 * generated by SWT Query SDK at Mon Oct 24 19:21:20 KST 2011
 */ 
public class SetWeightsSwitch extends WidgetSwitchWithArgument<Object, int[]> {
	public void setProperty(Widget widget, int[] value){
		doSwitch(widget, value);
	}

	public Object caseSashForm(SashForm widget, int[] value){
		widget.setWeights(value);
		return null;
	}
}
    