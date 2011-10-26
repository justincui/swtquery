package kr.or.eclipse.swt.query;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import static kr.or.eclipse.swt.query.SWTQuery.$;

public class ColorDropTargetEffect extends DropTargetAdapter {
	@Override
	public void dragEnter(DropTargetEvent event) {
		Color oldBackground = $(event).getControl().getBackground();
		$($(event).getControl()).setData("before-dnd-color", oldBackground);
		$(event).getControl().setBackground(Display.getDefault().getSystemColor(SWT.COLOR_INFO_BACKGROUND));
	}

	@Override
	public void dragLeave(DropTargetEvent event) {
		Color color = $($(event).getControl()).getData("before-dnd-color");
		$($(event).getControl()).setBackground(color);
	}

}
