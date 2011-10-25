package kr.or.eclipse.swt.query.internal.grammar;

import java.util.List;
import java.util.Stack;

import kr.or.eclipse.swt.query.internal.UniqueList;

import org.eclipse.swt.widgets.Widget;

public class WidgetContext {
	private Stack<UniqueList<Widget>> stack = new Stack<UniqueList<Widget>>();
	private UniqueList<Widget> workspace = new UniqueList<Widget>();
	private List<Widget> rootContext;

	public WidgetContext(List<Widget> rootContext) {
	}

}
