package kr.or.eclipse.swt.query.util;

import kr.or.eclipse.swt.query.internal.ParentSwitch;
import kr.or.eclipse.swt.query.util.internal.GetBoundsSwitch;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;

public class AbsoluteBounds extends WidgetSwitch<Rectangle> {
	private GetBoundsSwitch boundsSwitch;

	@Override
	public Rectangle caseControl(Control control) {
		Point size = control.getSize();
		Point location = control.toDisplay(0, 0);
		return new Rectangle(location.x, location.y, size.x, size.y);
	}

	@Override
	public Rectangle caseShell(Shell shell) {
		return shell.getBounds();
	}

	@Override
	public Rectangle caseDecorations(Decorations decorations) {
		return decorations.getBounds();
	}

	public AbsoluteBounds() {
		this.boundsSwitch = new GetBoundsSwitch();
	}

	@Override
	public Rectangle caseItem(Item item) {
		ParentSwitch parentFinder = ParentSwitch.INSTANCE;
		Widget finger = item;

		while (finger instanceof Item) {
			finger = parentFinder.doSwitch(finger);
		}

		if (finger == null) {
			return null;
		}

		if (finger instanceof Control) {
			Control control = (Control) finger;
			Rectangle bounds = this.boundsSwitch.doSwitch(item);
			if (bounds == null) {
				return null;
			}
			Point location = control.toDisplay(bounds.x, bounds.y);
			return new Rectangle(location.x, location.y, bounds.width, bounds.height);
		}

		return super.caseItem(item);
	}
}
