package kr.or.eclipse.swt.query.internal.grammar;

import java.util.List;

import kr.or.eclipse.swt.query.filter.IWidgetFilter;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;
import org.eclipse.swt.widgets.Widget;

public class FilterBuilder {
	public static IWidgetFilter build(String query) {
		try {
			SWTQuerySelectorLexer lexer = new SWTQuerySelectorLexer(
					new ANTLRStringStream(query));
			SWTQuerySelectorParser parser = new SWTQuerySelectorParser(
					new CommonTokenStream(lexer));
			SWTQuerySelectorParser.selectors_return r = parser.selectors();
			CommonTree tree = (CommonTree) r.getTree();

			List<CommonTree> selectorNodes = tree.getChildren();

			boolean direct = false;
			for (CommonTree eachSelectorNode : selectorNodes) {

				if (eachSelectorNode.getText().equals(">")) {
					direct = true;
					continue;
				}

				direct = false;
			}

			return null;

		} catch (Exception e) {
			e.printStackTrace();
			return new IWidgetFilter() {
				@Override
				public boolean matches(Widget widget) {
					return false;
				}
			};
		}

	}

	public static void main(String[] args) throws RecognitionException {
		String selector = "label[a=test], cba";

		SWTQuerySelectorLexer lexer = new SWTQuerySelectorLexer(
				new ANTLRStringStream(selector));
		SWTQuerySelectorParser parser = new SWTQuerySelectorParser(
				new CommonTokenStream(lexer));

		SWTQuerySelectorParser.selectors_return r = parser.selectors();
		Tree root = (Tree) r.getTree();

		System.out.println(root.getType());
		visit(root, 0);
	}

	public static void visit(Tree t, int depth) {
		System.out.println(indent(depth) + t.getText());
		for (int i = 0; i < t.getChildCount(); i++) {
			visit(t.getChild(i), depth + 1);
		}
	}

	public static String indent(int depth) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < depth; i++) {
			buffer.append("   ");
		}
		return buffer.toString();
	}
}
