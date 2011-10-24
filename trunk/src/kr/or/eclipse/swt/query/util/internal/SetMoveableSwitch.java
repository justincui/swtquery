package kr.or.eclipse.swt.query.util.internal;

import java.lang.Boolean;
import org.eclipse.swt.widgets.Widget;
import kr.or.eclipse.swt.query.util.WidgetSwitchWithArgument;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TreeColumn;
/*
 * generated by SWT Query SDK at Mon Oct 24 19:21:20 KST 2011
 */ 
public class SetMoveableSwitch extends WidgetSwitchWithArgument<Object, Boolean> {
	public void setProperty(Widget widget, Boolean value){
		doSwitch(widget, value);
	}

	public Object caseTableColumn(TableColumn widget, Boolean value){
		widget.setMoveable(value);
		return null;
	}
	public Object caseTreeColumn(TreeColumn widget, Boolean value){
		widget.setMoveable(value);
		return null;
	}
}
    