package kr.or.eclipse.swt.query;

import java.util.ArrayList;
import java.util.List;

import kr.or.eclipse.swt.query.filter.IWidgetFilter;
import kr.or.eclipse.swt.query.parse.FilterBuilder;
import kr.or.eclipse.swt.query.part.ChildrenSiwtch;
import kr.or.eclipse.swt.query.part.ParentSwitch;
import kr.or.eclipse.swt.query.part.TextPropertySwitch;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Widget;

public final class SWTQuery {
	private static ChildrenSiwtch childrenSiwtch = new ChildrenSiwtch();
	private static ParentSwitch parentSwitch = new ParentSwitch();
	private static TextPropertySwitch textPart = new TextPropertySwitch();

	public static SWTQuery $(Widget w) {
		return new SWTQuery(w);
	}

	public static SWTQuery $(Widget context, IWidgetFilter filter) {
		ArrayList<Widget> list = new ArrayList<Widget>();
		gatherChildren(context, list, filter);
		return new SWTQuery(list);
	}

	public static SWTQuery $(Widget context, String selector) {
		IWidgetFilter filter = FilterBuilder.build(selector);
		ArrayList<Widget> list = new ArrayList<Widget>();
		gatherChildren(context, list, filter);
		return new SWTQuery(list);
	}

	private static void gatherChildren(Widget w, List<Widget> target,
			IWidgetFilter filter) {
		if (!target.contains(w) && filter.matches(w)) {
			target.add(w);
		}
		for (Widget eachChild : childrenSiwtch.doSwitch(w)) {
			gatherChildren(eachChild, target, filter);
		}
	}

	private List<Widget> items;

	public SWTQuery(List<Widget> itemList) {
		this.items = itemList;
	}

	public SWTQuery(Widget... items) {
		this.items = new ArrayList<Widget>();
		for (Widget each : items) {
			this.items.add(each);
		}
	}

	public SWTQuery each(IWidgetFunction f) {
		for (Widget each : this.items) {
			f.doFunction(each);
		}
		return this;
	}

	@SuppressWarnings("unchecked")
	public <T> T getData() {
		if (items.size() > 0) {
			return (T) items.get(0).getData();
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T getData(String key) {
		if (items.size() > 0) {
			return (T) items.get(0).getData(key);
		} else {
			return null;
		}
	}

	public SWTQuery layout() {
		for (Widget each : this.items) {
			if (each instanceof Composite) {
				((Composite) each).layout();
			}
		}
		return this;
	}

	public SWTQuery layout(boolean changed) {
		for (Widget each : this.items) {
			if (each instanceof Composite) {
				((Composite) each).layout(changed);
			}
		}
		return this;
	}

	public SWTQuery layout(boolean changed, boolean all) {
		for (Widget each : this.items) {
			if (each instanceof Composite) {
				((Composite) each).layout(changed, all);
			}
		}
		return this;
	}

	public SWTQuery next() {
		return next(1);
	}

	public SWTQuery next(int delta) {
		return translate(delta);
	}

	public SWTQuery parent() {
		ArrayList<Widget> parents = new ArrayList<Widget>();
		for (Widget each : this.items) {
			Widget eachParent = ParentSwitch.INSTANCE.doSwitch(each);
			if (eachParent != null) {
				parents.add(eachParent);
			}
		}
		return parent();
	}

	public SWTQuery prev() {
		return prev(-1);
	}

	public SWTQuery prev(int delta) {
		return translate(delta);
	}

	public SWTQuery redraw() {
		for (Widget each : this.items) {
			if (each instanceof Control) {
				((Control) each).redraw();
			}
		}
		return this;
	}

	public SWTQuery addListener(int eventType, Listener listener) {
		for (Widget each : this.items) {
			each.addListener(eventType, listener);
		}
		return this;
	}

	public SWTQuery setData(Object data) {
		for (Widget each : items) {
			each.setData(data);
		}
		return this;
	}

	public SWTQuery setData(String key, Object data) {
		for (Widget each : items) {
			each.setData(key, data);
		}
		return this;
	}

	private SWTQuery translate(int delta) {
		ArrayList<Widget> result = new ArrayList<Widget>();

		for (Widget each : this.items) {
			Widget parent = parentSwitch.doSwitch(each);
			if (parent == null) {
				continue;
			}
			List<Widget> sibilings = childrenSiwtch.doSwitch(parent);
			int index = sibilings.indexOf(each);

			int newIndex = index + delta;
			if (newIndex >= 0 && newIndex < sibilings.size()) {
				result.add(sibilings.get(newIndex));
			}
		}

		return new SWTQuery(result);
	}

	public String text() {
		if (this.items.size() > 0) {
			return textPart.getText(this.items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery text(String text) {
		for (Widget each : this.items) {
			textPart.setText(each, text);
		}
		return this;
	}

}
