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

import java.lang.Boolean;
import org.eclipse.swt.widgets.Widget;
import kr.or.eclipse.swt.query.util.WidgetSwitchWithArgument;
import org.eclipse.swt.custom.ScrolledComposite;


/*
 * This file was generated by SWT Query SDK.
 * So you should not modify it manually.
 * If you want to customize this code, contact below:
 *
 * http://code.google.com/p/swtquery/
 */
public class SetAlwaysShowScrollBarsSwitch extends WidgetSwitchWithArgument<Object, Boolean> {
	public void setProperty(Widget widget, Boolean value){
		doSwitch(widget, value);
	}

	public Object caseScrolledComposite(ScrolledComposite widget, Boolean value){
		widget.setAlwaysShowScrollBars(value);
		return null;
	}
}
    