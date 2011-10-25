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

import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.List;
import java.lang.Integer;
import org.eclipse.swt.widgets.Widget;
import kr.or.eclipse.swt.query.util.WidgetSwitch;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;


/*
 * This file was generated by SWT Query SDK.
 * So you should not modify it manually.
 * If you want to customize this code, contact below:
 *
 * http://code.google.com/p/swtquery/
 */
public class GetSelectionCountSwitch extends WidgetSwitch<Integer> {
	public Integer getProperty(Widget widget){
		return doSwitch(widget);
	}

	public Integer caseStyledText(StyledText widget){
		return widget.getSelectionCount();
	}
	public Integer caseTable(Table widget){
		return widget.getSelectionCount();
	}
	public Integer caseTree(Tree widget){
		return widget.getSelectionCount();
	}
	public Integer caseList(List widget){
		return widget.getSelectionCount();
	}
	public Integer caseText(Text widget){
		return widget.getSelectionCount();
	}
}
    