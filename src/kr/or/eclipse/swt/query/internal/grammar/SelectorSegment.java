package kr.or.eclipse.swt.query.internal.grammar;

import java.util.ArrayList;
import java.util.List;

import kr.or.eclipse.swt.query.internal.ChildrenSwitch;
import kr.or.eclipse.swt.query.util.SWTConstants;

import org.antlr.runtime.tree.Tree;
import org.eclipse.swt.widgets.Widget;

public class SelectorSegment {
	private List<AttributeFilter> attributeFilters = new ArrayList<AttributeFilter>();
	private boolean directChildOnly = false;
	private int styleMask = 0;

	private String typeName;

	public SelectorSegment(Tree rootNode) {
		if (rootNode.getType() != SWTQuerySelectorLexer.ASTERIK) {
			typeName = rootNode.getText();
		}

		for (int i = 0; i < rootNode.getChildCount(); i++) {
			Tree child = rootNode.getChild(i);

			switch (child.getType()) {
			case SWTQuerySelectorLexer.LT:
				directChildOnly = true;
				break;

			case SWTQuerySelectorLexer.COLON:
				addStyle(child);
				break;

			case SWTQuerySelectorLexer.ATTR_OP:
				addAttribute(child);
			}
		}
	}

	public SelectorSegment() {

	}

	private void addAttribute(Tree child) {
		String operator = child.getText();
		String key = child.getChild(0).getText();
		Tree valueNode = child.getChild(1);
		String value = valueNode.getText();
		if (valueNode.getType() == SWTQuerySelectorLexer.LITERAL) {
			value = value.substring(1, value.length() - 1);
		}

		AttributeFilter attributeFilter = new AttributeFilter(AttributeOperator.get(operator), key, value);
		attributeFilters.add(attributeFilter);
	}

	private void addStyle(Tree child) {
		for (int i = 0; i < child.getChildCount(); i++) {
			Tree eachStyleNode = child.getChild(i);
			styleMask |= SWTConstants.toInt(eachStyleNode.getText());
		}
	}

	public int getStyleMask() {
		return styleMask;
	}

	public String getTypeName() {
		return typeName;
	}

	public boolean isDirectChildOnly() {
		return directChildOnly;
	}

	public void setDirectChildOnly(boolean directChildOnly) {
		this.directChildOnly = directChildOnly;
	}

	public void setStyleMask(int styleMask) {
		this.styleMask = styleMask;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public List<Widget> select(Widget context) {
		List<Widget> children;

		if (isDirectChildOnly()) {
			children = ChildrenSwitch.INSTANCE.getChildren(context, false, false);
		} else {
			children = ChildrenSwitch.INSTANCE.getChildren(context, false, true);
		}

		if (typeName != null) {
			for (Widget each : children.toArray(new Widget[children.size()])) {
				if (!each.getClass().getSimpleName().equalsIgnoreCase(typeName)) {
					children.remove(each);
				}
			}
		}

		if (styleMask != 0) {
			for (Widget each : children.toArray(new Widget[children.size()])) {
				if ((each.getStyle() & styleMask) != styleMask) {
					children.remove(each);
				}
			}
		}

		// FIXME 어트리뷰터 필터 걸어야 해요

		return children;
	}

}
