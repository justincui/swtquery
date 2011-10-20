package kr.or.eclipse.swt.query.part;

import kr.or.eclipse.swt.query.WidgetSwitch;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.forms.widgets.ExpandableComposite;

public class TextPropertySwitch {
    private class GetSwitch extends WidgetSwitch<String> {
        @Override
        public String caseButton(Button widget, Object... args) {
            return widget.getText();
        }

        @Override
        public String caseExpandableComposite(ExpandableComposite widget, Object... args) {
            return widget.getText();
        }
    }

    private class SetSwitch extends WidgetSwitch<Object> {
        @Override
        public Object caseButton(Button widget, Object... args) {
            widget.setText((String) args[0]);
            return null;
        }

        @Override
        public Object caseText(Text widget, Object... arguments) {
            widget.setText((String) arguments[0]);
            return null;
        }

        @Override
        public Object caseExpandableComposite(ExpandableComposite widget, Object... args) {
            widget.setText((String) args[0]);
            return null;
        }
    }

    private GetSwitch getSwitch = new GetSwitch();
    private SetSwitch setSwitch = new SetSwitch();

    public String getText(Widget w) {
        return this.getSwitch.doSwitch(w);
    }

    public void setText(Widget w, String text) {
        this.setSwitch.doSwitch(w, text);
    }

}
