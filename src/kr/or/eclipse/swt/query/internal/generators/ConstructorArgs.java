package kr.or.eclipse.swt.query.internal.generators;

import org.eclipse.swt.widgets.Widget;

public class ConstructorArgs {
	public Widget parent;
	public int style;

	public ConstructorArgs(Widget parent, int style) {
		super();
		this.parent = parent;
		this.style = style;
	}
}