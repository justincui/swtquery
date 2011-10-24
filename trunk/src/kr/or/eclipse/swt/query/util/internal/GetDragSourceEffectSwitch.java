package kr.or.eclipse.swt.query.util.internal;

import org.eclipse.swt.dnd.DragSourceEffect;
import org.eclipse.swt.widgets.Widget;
import kr.or.eclipse.swt.query.util.WidgetSwitch;
import org.eclipse.swt.dnd.DragSource;
/*
 * generated by SWT Query SDK at Mon Oct 24 19:21:20 KST 2011
 */ 
public class GetDragSourceEffectSwitch extends WidgetSwitch<DragSourceEffect> {
	public DragSourceEffect getProperty(Widget widget){
		return doSwitch(widget);
	}

	public DragSourceEffect caseDragSource(DragSource widget){
		return widget.getDragSourceEffect();
	}
}
    