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

import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.List;
import java.lang.String;
import org.eclipse.swt.widgets.Widget;
import kr.or.eclipse.swt.query.util.WidgetSwitch;
import org.eclipse.swt.widgets.Combo;


/*
 * This file was generated by SWT Query SDK.
 * So you should not modify it manually.
 * If you want to customize this code, contact below:
 *
 * http://code.google.com/p/swtquery/
 */
public class GetItemsSwitch extends WidgetSwitch<String[]> {
	public String[] getProperty(Widget widget){
		return doSwitch(widget);
	}

	public String[] caseCCombo(CCombo widget){
		return widget.getItems();
	}
	public String[] caseCombo(Combo widget){
		return widget.getItems();
	}
	public String[] caseList(List widget){
		return widget.getItems();
	}
}
    