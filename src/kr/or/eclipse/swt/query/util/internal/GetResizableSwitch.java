package kr.or.eclipse.swt.query.util.internal;

import java.lang.Boolean;
import org.eclipse.swt.widgets.Widget;
import kr.or.eclipse.swt.query.util.WidgetSwitch;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TreeColumn;
/*
 * generated by SWT Query SDK at Mon Oct 24 19:21:20 KST 2011
 */ 
public class GetResizableSwitch extends WidgetSwitch<Boolean> {
	public Boolean getProperty(Widget widget){
		return doSwitch(widget);
	}

	public Boolean caseTableColumn(TableColumn widget){
		return widget.getResizable();
	}
	public Boolean caseTreeColumn(TreeColumn widget){
		return widget.getResizable();
	}
}
    