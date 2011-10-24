/*
 * Copyright 2011 jeeeyul@gmail.com
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 */

package kr.or.eclipse.swt.query.util.internal;

import org.eclipse.swt.widgets.ToolItem;
import kr.or.eclipse.swt.query.util.WidgetSwitchWithArgument;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TableColumn;
import java.lang.String;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.custom.CLabel;


/*
 * This file was generated by SWT Query SDK.
 * So you should not modify it manually.
 * If you want to customize this code, contact below:
 *
 * http://code.google.com/p/swtquery/
 */
public class SetToolTipTextSwitch extends WidgetSwitchWithArgument<Object, String> {
	public void setProperty(Widget widget, String value){
		doSwitch(widget, value);
	}

	public Object caseHyperlink(Hyperlink widget, String value){
		widget.setToolTipText(value);
		return null;
	}
	public Object caseCLabel(CLabel widget, String value){
		widget.setToolTipText(value);
		return null;
	}
	public Object caseControl(Control widget, String value){
		widget.setToolTipText(value);
		return null;
	}
	public Object caseCTabItem(CTabItem widget, String value){
		widget.setToolTipText(value);
		return null;
	}
	public Object caseTabItem(TabItem widget, String value){
		widget.setToolTipText(value);
		return null;
	}
	public Object caseTableColumn(TableColumn widget, String value){
		widget.setToolTipText(value);
		return null;
	}
	public Object caseToolItem(ToolItem widget, String value){
		widget.setToolTipText(value);
		return null;
	}
	public Object caseTrayItem(TrayItem widget, String value){
		widget.setToolTipText(value);
		return null;
	}
	public Object caseTreeColumn(TreeColumn widget, String value){
		widget.setToolTipText(value);
		return null;
	}
}
    