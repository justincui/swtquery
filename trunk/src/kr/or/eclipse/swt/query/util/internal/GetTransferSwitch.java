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

import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.widgets.Widget;
import kr.or.eclipse.swt.query.util.WidgetSwitch;
import org.eclipse.swt.dnd.Transfer;


/*
 * This file was generated by SWT Query SDK.
 * So you should not modify it manually.
 * If you want to customize this code, contact below:
 *
 * http://code.google.com/p/swtquery/
 */
public class GetTransferSwitch extends WidgetSwitch<Transfer[]> {
	public Transfer[] getProperty(Widget widget){
		return doSwitch(widget);
	}

	public Transfer[] caseDragSource(DragSource widget){
		return widget.getTransfer();
	}
	public Transfer[] caseDropTarget(DropTarget widget){
		return widget.getTransfer();
	}
}
    