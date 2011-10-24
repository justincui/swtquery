package kr.or.eclipse.swt.query.internal;

import kr.or.eclipse.swt.query.util.WidgetSwitch;
import kr.or.eclipse.swt.query.util.WidgetSwitchWithArgument;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.forms.widgets.ExpandableComposite;

public class TextSwitch {
    public static final TextSwitch INSTANCE = new TextSwitch();

    private class GetSwitch extends WidgetSwitch<String> {
        @Override
        public String caseButton(Button widget) {
            return widget.getText();
        }

        @Override
        public String caseExpandableComposite(ExpandableComposite widget) {
            return widget.getText();
        }
    }

    private class SetSwitch extends WidgetSwitchWithArgument<Object, String> {
        @Override
        public Object caseButton(Button widget, String text) {
            widget.setText(text);
            return null;
        }

        @Override
        public Object caseText(Text widget, String text) {
            widget.setText(text);
            return null;
        }

        @Override
        public Object caseExpandableComposite(ExpandableComposite widget, String text) {
            widget.setText(text);
            return null;
        }
    }

    private GetSwitch getSwitch = new GetSwitch();
    private SetSwitch setSwitch = new SetSwitch();

    /**
     * 
     * @param w
     * @return
     */
    public String getText(Widget w) {
        return this.getSwitch.doSwitch(w);
    }

    public void setText(Widget w, String text) {
        this.setSwitch.doSwitch(w, text);
    }

}
