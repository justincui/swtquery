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

import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.widgets.Menu;
import kr.or.eclipse.swt.query.util.WidgetSwitchWithArgument;
import org.eclipse.swt.widgets.MenuItem;


/*
 * This file was generated by SWT Query SDK.
 * So you should not modify it manually.
 * If you want to customize this code, contact below:
 *
 * http://code.google.com/p/swtquery/
 */
public class SetMenuSwitch extends WidgetSwitchWithArgument<Object, Menu> {
	public void setProperty(Widget widget, Menu value){
		doSwitch(widget, value);
	}

	public Object caseCCombo(CCombo widget, Menu value){
		widget.setMenu(value);
		return null;
	}
	public Object caseControl(Control widget, Menu value){
		widget.setMenu(value);
		return null;
	}
	public Object caseMenuItem(MenuItem widget, Menu value){
		widget.setMenu(value);
		return null;
	}
}
    