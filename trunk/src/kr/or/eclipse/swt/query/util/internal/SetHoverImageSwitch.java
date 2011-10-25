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

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.forms.widgets.ImageHyperlink;


/*
 * This file was generated by SWT Query SDK.
 * So you should not modify it manually.
 * If you want to customize this code, contact below:
 *
 * http://code.google.com/p/swtquery/
 */
public class SetHoverImageSwitch extends WidgetSwitchWithArgument<Object, Image> {
	public void setProperty(Widget widget, Image value){
		doSwitch(widget, value);
	}

	public Object caseImageHyperlink(ImageHyperlink widget, Image value){
		widget.setHoverImage(value);
		return null;
	}
}
    