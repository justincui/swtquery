package kr.or.eclipse.swt.query.util.internal;


import org.eclipse.swt.widgets.Widget;
import kr.or.eclipse.swt.query.util.WidgetSwitch;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Tree;
/*
 * generated by SWT Query SDK at Mon Oct 24 19:21:20 KST 2011
 */ 
public class GetColumnOrderSwitch extends WidgetSwitch<int[]> {
	public int[] getProperty(Widget widget){
		return doSwitch(widget);
	}

	public int[] caseTable(Table widget){
		return widget.getColumnOrder();
	}
	public int[] caseTree(Tree widget){
		return widget.getColumnOrder();
	}
}
    