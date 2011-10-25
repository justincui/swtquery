/**
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
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ExpandItem;
import kr.or.eclipse.swt.query.util.WidgetSwitch;


/*
 * This file was generated by SWT Query SDK.
 * So you should not modify it manually.
 * If you want to customize this code, contact below:
 *
 * http://code.google.com/p/swtquery/
 */
public class GetControlSwitch extends WidgetSwitch<Control> {
	public Control getProperty(Widget widget){
		return doSwitch(widget);
	}

	public Control caseCTabItem(CTabItem widget){
		return widget.getControl();
	}
	public Control caseCoolItem(CoolItem widget){
		return widget.getControl();
	}
	public Control caseExpandItem(ExpandItem widget){
		return widget.getControl();
	}
	public Control caseTabItem(TabItem widget){
		return widget.getControl();
	}
	public Control caseToolItem(ToolItem widget){
		return widget.getControl();
	}
}
    