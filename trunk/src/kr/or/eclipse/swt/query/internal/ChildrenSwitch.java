package kr.or.eclipse.swt.query.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kr.or.eclipse.swt.query.util.WidgetSwitch;

import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;

public class ChildrenSwitch extends WidgetSwitch<List<Widget>> {
	public static final ChildrenSwitch INSTANCE = new ChildrenSwitch();

	@Override
	public List<Widget> caseComposite(Composite widget) {
		ArrayList<Widget> result = new ArrayList<Widget>();
		for (Control each : widget.getChildren()) {
			result.add(each);
		}
		return result;
	}

	@Override
	public List<Widget> caseCTabFolder(CTabFolder widget) {
		ArrayList<Widget> result = new ArrayList<Widget>();
		for (CTabItem each : widget.getItems()) {
			result.add(each);
		}
		return result;
	}

	@Override
	public List<Widget> caseCTabItem(CTabItem widget) {
		ArrayList<Widget> result = new ArrayList<Widget>();
		if (widget.getControl() != null) {
			result.add(widget.getControl());
		}
		return result;
	}

	@Override
	public List<Widget> caseTabFolder(TabFolder widget) {
		ArrayList<Widget> result = new ArrayList<Widget>();
		for (TabItem each : widget.getItems()) {
			result.add(each);
		}
		return result;
	}

	@Override
	public List<Widget> caseTabItem(TabItem widget) {
		ArrayList<Widget> result = new ArrayList<Widget>();
		if (widget.getControl() != null) {
			result.add(widget.getControl());
		}
		return result;
	}

	@Override
	public List<Widget> caseTable(Table widget) {
		ArrayList<Widget> result = new ArrayList<Widget>();

		for (TableItem each : widget.getItems()) {
			result.add(each);
		}
		return result;
	}

	@Override
	public List<Widget> caseTree(Tree widget) {
		ArrayList<Widget> result = new ArrayList<Widget>();
		for (TreeItem each : widget.getItems()) {
			result.add(each);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Widget> caseWidget(Widget widget) {
		return Collections.EMPTY_LIST;
	}

	private void gatherChildren(Widget w, List<Widget> target) {
		if (!target.contains(w)) {
			target.add(w);
		}
		for (Widget eachChild : ChildrenSwitch.INSTANCE.doSwitch(w)) {
			gatherChildren(eachChild, target);
		}
	}

	public List<Widget> getChildren(Widget parent, boolean includeItSelf, boolean recursive) {
		List<Widget> result = new UniqueList<Widget>();
		if (includeItSelf) {
			result.add(0, parent);
		}

		if (recursive == false) {
			result.addAll(doSwitch(parent));
		} else {
			gatherChildren(parent, result);
		}

		return result;

	}
}
