package kr.or.eclipse.swt.query.internal.grammar;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.Tree;

public class SelectorInterpreter {

	public static void main(String[] args) throws RecognitionException {
		String selector = "label:(border,readonly)[a=twest, c=-24.392] bbb, cba > system";

		SWTQuerySelectorLexer lexer = new SWTQuerySelectorLexer(new ANTLRStringStream(selector));
		SWTQuerySelectorParser parser = new SWTQuerySelectorParser(new CommonTokenStream(lexer));

		SWTQuerySelectorParser.selectors_return r = parser.selectors();
		Tree root = (Tree) r.getTree();

		System.out.println(root.getType());
		visit(root, 0);

		List<Selector> build = build(selector);
		System.out.println(build);
	}

	public static List<Selector> build(String selectorExp) {
		try {
			ArrayList<Selector> result = new ArrayList<Selector>();

			SWTQuerySelectorLexer lexer = new SWTQuerySelectorLexer(new ANTLRStringStream(selectorExp));
			SWTQuerySelectorParser parser = new SWTQuerySelectorParser(new CommonTokenStream(lexer));
			SWTQuerySelectorParser.selectors_return r = parser.selectors();
			Tree root = (Tree) r.getTree();

			if (root.getType() != 0) {
				Selector selector = new Selector();
				selector.getSegments().add(new SelectorSegment(root));
				result.add(selector);
			}

			else {
				Selector currentSelector = new Selector();
				for (int i = 0; i < root.getChildCount(); i++) {
					Tree eachNode = root.getChild(i);
					if (eachNode.getType() == SWTQuerySelectorLexer.COMMA) {
						result.add(currentSelector);
						currentSelector = new Selector();
						continue;
					}
					SelectorSegment segment = new SelectorSegment(eachNode);
					currentSelector.getSegments().add(segment);
				}
				result.add(currentSelector);
			}
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
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
