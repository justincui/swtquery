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

import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.swt.custom.StyledText;
import java.lang.String;
import org.eclipse.swt.widgets.Widget;
import kr.or.eclipse.swt.query.util.WidgetSwitch;
import org.eclipse.swt.widgets.Text;


/*
 * This file was generated by SWT Query SDK.
 * So you should not modify it manually.
 * If you want to customize this code, contact below:
 *
 * http://code.google.com/p/swtquery/
 */
public class GetSelectionTextSwitch extends WidgetSwitch<String> {
	public String getProperty(Widget widget){
		return doSwitch(widget);
	}

	public String caseStyledText(StyledText widget){
		return widget.getSelectionText();
	}
	public String caseFormText(FormText widget){
		return widget.getSelectionText();
	}
	public String caseText(Text widget){
		return widget.getSelectionText();
	}
}
    