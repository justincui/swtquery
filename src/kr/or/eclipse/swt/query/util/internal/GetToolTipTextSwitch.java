package kr.or.eclipse.swt.query.util.internal;

import java.lang.String;
import org.eclipse.swt.widgets.Widget;
import kr.or.eclipse.swt.query.util.WidgetSwitch;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.swt.widgets.TreeColumn;
/*
 * generated by SWT Query SDK at Mon Oct 24 19:21:20 KST 2011
 */ 
public class GetToolTipTextSwitch extends WidgetSwitch<String> {
	public String getProperty(Widget widget){
		return doSwitch(widget);
	}

	public String caseHyperlink(Hyperlink widget){
		return widget.getToolTipText();
	}
	public String caseCLabel(CLabel widget){
		return widget.getToolTipText();
	}
	public String caseControl(Control widget){
		return widget.getToolTipText();
	}
	public String caseCTabItem(CTabItem widget){
		return widget.getToolTipText();
	}
	public String caseTabItem(TabItem widget){
		return widget.getToolTipText();
	}
	public String caseTableColumn(TableColumn widget){
		return widget.getToolTipText();
	}
	public String caseToolItem(ToolItem widget){
		return widget.getToolTipText();
	}
	public String caseTrayItem(TrayItem widget){
		return widget.getToolTipText();
	}
	public String caseTreeColumn(TreeColumn widget){
		return widget.getToolTipText();
	}
}
    