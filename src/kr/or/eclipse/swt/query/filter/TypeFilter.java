package kr.or.eclipse.swt.query.filter;

import org.eclipse.swt.widgets.Widget;

public class TypeFilter implements IWidgetFilter {
    private String type;

    public TypeFilter(String type) {
        super();
        this.type = type;
    }

    @Override
    public boolean matches(Widget widget) {
        if (widget == null) {
            return false;
        }
        return widget.getClass().getSimpleName().toLowerCase().equals(this.type.toLowerCase());
    }

    @Override
    public String toString() {
        return "TypeFilter [type=" + this.type + "]";
    }
}
