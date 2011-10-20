package kr.or.eclipse.swt.query.part;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kr.or.eclipse.swt.query.WidgetSwitch;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;

public class ChildrenSiwtch extends WidgetSwitch<List<Widget>> {

    public static final ChildrenSiwtch INSTANCE = new ChildrenSiwtch();

    @SuppressWarnings("unchecked")
    @Override
    public List<Widget> caseWidget(Widget widget, Object... arguments) {
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<Widget> caseComposite(Composite widget, Object... arguments) {
        ArrayList<Widget> result = new ArrayList<Widget>();
        for (Control each : widget.getChildren()) {
            result.add(each);
        }
        return result;
    }
}
