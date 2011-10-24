package kr.or.eclipse.swt.query.internal;

import kr.or.eclipse.swt.query.util.WidgetSwitch;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;

public class ParentSwitch extends WidgetSwitch<Widget> {
    public static final ParentSwitch INSTANCE = new ParentSwitch();

    @Override
    public Widget caseControl(Control widget) {
        return widget.getParent();
    }

    @Override
    public Widget caseTreeItem(TreeItem widget) {
        TreeItem parentNode = widget.getParentItem();

        if (parentNode == null) {
            return widget.getParent();
        } else {
            return parentNode;
        }
    }

}
