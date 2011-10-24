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
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ExpandItem;


/*
 * This file was generated by SWT Query SDK.
 * So you should not modify it manually.
 * If you want to customize this code, contact below:
 *
 * http://code.google.com/p/swtquery/
 */
public class SetControlSwitch extends WidgetSwitchWithArgument<Object, Control> {
	public void setProperty(Widget widget, Control value){
		doSwitch(widget, value);
	}

	public Object caseCTabItem(CTabItem widget, Control value){
		widget.setControl(value);
		return null;
	}
	public Object caseCoolItem(CoolItem widget, Control value){
		widget.setControl(value);
		return null;
	}
	public Object caseExpandItem(ExpandItem widget, Control value){
		widget.setControl(value);
		return null;
	}
	public Object caseTabItem(TabItem widget, Control value){
		widget.setControl(value);
		return null;
	}
	public Object caseToolItem(ToolItem widget, Control value){
		widget.setControl(value);
		return null;
	}
}
    