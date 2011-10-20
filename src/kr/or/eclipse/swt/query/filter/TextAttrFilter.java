package kr.or.eclipse.swt.query.filter;

import kr.or.eclipse.swt.query.part.TextPropertySwitch;

import org.eclipse.swt.widgets.Widget;

public class TextAttrFilter implements IWidgetFilter {
    TextPropertySwitch textPropertySwitch = new TextPropertySwitch();
    private final String suspected;
    private final boolean isRegExp;

    public TextAttrFilter(String suspected, boolean isRegExp) {
        this.suspected = suspected;
        this.isRegExp = isRegExp;
    }

    @Override
    public boolean matches(Widget widget) {
        String text = this.textPropertySwitch.getText(widget);
        if (text == null) {
            return false;
        }
        if (this.isRegExp) {
            return text.matches(this.suspected);
        } else {
            return text.equalsIgnoreCase(this.suspected);
        }
    }
}
