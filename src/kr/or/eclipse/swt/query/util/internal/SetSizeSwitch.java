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

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;
import kr.or.eclipse.swt.query.util.WidgetSwitchWithArgument;
import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Caret;
import org.eclipse.swt.graphics.Point;


/*
 * This file was generated by SWT Query SDK.
 * So you should not modify it manually.
 * If you want to customize this code, contact below:
 *
 * http://code.google.com/p/swtquery/
 */
public class SetSizeSwitch extends WidgetSwitchWithArgument<Object, Point> {
	public void setProperty(Widget widget, Point value){
		doSwitch(widget, value);
	}

	public Object caseCaret(Caret widget, Point value){
		widget.setSize(value);
		return null;
	}
	public Object caseShell(Shell widget, Point value){
		widget.setSize(value);
		return null;
	}
	public Object caseDecorations(Decorations widget, Point value){
		widget.setSize(value);
		return null;
	}
	public Object caseControl(Control widget, Point value){
		widget.setSize(value);
		return null;
	}
	public Object caseCoolItem(CoolItem widget, Point value){
		widget.setSize(value);
		return null;
	}
}
    