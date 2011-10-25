package kr.or.eclipse.swt.query;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Widget;

public class DebugPainter implements Listener {
	private Widget taret;
	private int color;

	@Override
	public void handleEvent(Event event) {
		System.out.println(event);
	}

}
