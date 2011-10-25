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

import kr.or.eclipse.swt.query.util.WidgetSwitchWithArgument;
import java.lang.Integer;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.custom.SashForm;


/*
 * This file was generated by SWT Query SDK.
 * So you should not modify it manually.
 * If you want to customize this code, contact below:
 *
 * http://code.google.com/p/swtquery/
 */
public class SetSashWidthSwitch extends WidgetSwitchWithArgument<Object, Integer> {
	public void setProperty(Widget widget, Integer value){
		doSwitch(widget, value);
	}

	public Object caseSashForm(SashForm widget, Integer value){
		widget.setSashWidth(value);
		return null;
	}
}
    