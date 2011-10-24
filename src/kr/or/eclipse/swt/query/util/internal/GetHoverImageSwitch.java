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

import org.eclipse.swt.widgets.Widget;
import kr.or.eclipse.swt.query.util.WidgetSwitch;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.swt.graphics.Image;


/*
 * This file was generated by SWT Query SDK.
 * So you should not modify it manually.
 * If you want to customize this code, contact below:
 *
 * http://code.google.com/p/swtquery/
 */
public class GetHoverImageSwitch extends WidgetSwitch<Image> {
	public Image getProperty(Widget widget){
		return doSwitch(widget);
	}

	public Image caseImageHyperlink(ImageHyperlink widget){
		return widget.getHoverImage();
	}
}
    