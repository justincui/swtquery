package kr.or.eclipse.swt.query.parse;

import java.util.List;

import kr.or.eclipse.swt.query.filter.IWidgetFilter;
import kr.or.eclipse.swt.query.filter.MultiFilter;
import kr.or.eclipse.swt.query.filter.TextAttrFilter;
import kr.or.eclipse.swt.query.filter.TypeFilter;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.eclipse.swt.widgets.Widget;

public class FilterBuilder {
    public static IWidgetFilter build(String query) {
        try {
            SWTQuerySelectorLexer lexer = new SWTQuerySelectorLexer(new ANTLRStringStream(query));
            SWTQuerySelectorParser parser = new SWTQuerySelectorParser(new CommonTokenStream(lexer));
            SWTQuerySelectorParser.selectors_return r = parser.selectors();
            CommonTree tree = (CommonTree) r.getTree();

            List<CommonTree> selectorNodes = tree.getChildren();

            boolean direct = false;
            for (CommonTree eachSelectorNode : selectorNodes) {

                if (eachSelectorNode.getText().equals(">")) {
                    direct = true;
                    continue;
                }

                MultiFilter eachFilter = createEachFilter(tree);
                direct = false;
            }

            return eachFilter;

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

    private static MultiFilter createEachFilter(CommonTree tree) {
        MultiFilter eachFilter = new MultiFilter();
        String type = tree.getText();
        eachFilter.addFilter(new TypeFilter(type));

        for (int i = 0; i < tree.getChildCount(); i++) {
            String attribute = tree.getChild(i).getText();
            String attributeValue = tree.getChild(i).getChild(0).getText();
            attributeValue = attributeValue.substring(1, attributeValue.length() - 1);
            if ("text".equals(attribute)) {
                eachFilter.addFilter(new TextAttrFilter(attributeValue, false));
            }
        }
        return eachFilter;
    }
}
