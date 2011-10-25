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

import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.widgets.Control;
import kr.or.eclipse.swt.query.util.WidgetSwitchWithArgument;
import org.eclipse.ui.forms.widgets.Form;


/*
 * This file was generated by SWT Query SDK.
 * So you should not modify it manually.
 * If you want to customize this code, contact below:
 *
 * http://code.google.com/p/swtquery/
 */
public class SetHeadClientSwitch extends WidgetSwitchWithArgument<Object, Control> {
	public void setProperty(Widget widget, Control value){
		doSwitch(widget, value);
	}

	public Object caseForm(Form widget, Control value){
		widget.setHeadClient(value);
		return null;
	}
}
    