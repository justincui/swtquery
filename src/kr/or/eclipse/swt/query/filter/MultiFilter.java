package kr.or.eclipse.swt.query.filter;

import java.util.ArrayList;
import java.util.List;

import kr.or.eclipse.swt.query.part.ChildrenSiwtch;

import org.eclipse.swt.widgets.Widget;

public class MultiFilter implements IWidgetFilter {
    private List<IWidgetFilter> filters = new ArrayList<IWidgetFilter>();

    private boolean isDirectChildrenOnly = false;

    public boolean isDirectChildrenOnly() {
        return this.isDirectChildrenOnly;
    }

    public void setDirectChildrenOnly(boolean isDirectChildrenOnly) {
        this.isDirectChildrenOnly = isDirectChildrenOnly;
    }

    public void addFilter(IWidgetFilter filter) {
        this.filters.add(filter);
    }

    @Override
    public boolean matches(Widget widget) {
        for (IWidgetFilter each : this.filters) {
            if (!each.matches(widget)) {
                return false;
            }
        }
        return true;
    }

    public boolean matches(Widget context, Widget widget) {
        if (this.isDirectChildrenOnly) {
            List<Widget> children = ChildrenSiwtch.INSTANCE.doSwitch(widget);
            return children.contains(widget) && matches(widget);
        } else {
            return matches(widget);
        }
    }

}
