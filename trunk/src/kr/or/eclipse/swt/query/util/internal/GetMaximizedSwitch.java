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

import kr.or.eclipse.swt.query.util.WidgetSwitch;

import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;


/*
 * This file was generated by SWT Query SDK.
 * So you should not modify it manually.
 * If you want to customize this code, contact below:
 *
 * http://code.google.com/p/swtquery/
 */
public class GetMaximizedSwitch extends WidgetSwitch<Boolean> {
	public Boolean getProperty(Widget widget){
		return doSwitch(widget);
	}

	public Boolean caseShell(Shell widget){
		return widget.getMaximized();
	}
	public Boolean caseDecorations(Decorations widget){
		return widget.getMaximized();
	}
	public Boolean caseCTabFolder(CTabFolder widget){
		return widget.getMaximized();
	}
}
    