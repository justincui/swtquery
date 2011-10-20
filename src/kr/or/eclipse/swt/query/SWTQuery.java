package kr.or.eclipse.swt.query;

import java.util.ArrayList;
import java.util.List;

import kr.or.eclipse.swt.query.filter.IWidgetFilter;
import kr.or.eclipse.swt.query.parse.FilterBuilder;
import kr.or.eclipse.swt.query.part.ChildrenSiwtch;
import kr.or.eclipse.swt.query.part.ParentSwitch;
import kr.or.eclipse.swt.query.part.TextPropertySwitch;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Widget;

public class SWTQuery {
    private static ChildrenSiwtch childrenSiwtch = new ChildrenSiwtch();
    private static ParentSwitch parentSwitch = new ParentSwitch();
    private static TextPropertySwitch textPart = new TextPropertySwitch();

    private List<Widget> items;

    public SWTQuery(List<Widget> items) {
        this.items = items;
    }

    public SWTQuery(Widget... items) {
        this.items = new ArrayList<Widget>();
        for (Widget each : items) {
            this.items.add(each);
        }
    }

    public SWTQuery next() {
        ArrayList<Widget> result = new ArrayList<Widget>();

        for (Widget each : this.items) {
            Widget parent = parentSwitch.doSwitch(each);
            if (parent == null) {
                continue;
            }
            List<Widget> sibilings = childrenSiwtch.doSwitch(parent);
            int index = sibilings.indexOf(each);
            if (sibilings.size() > index + 1) {
                result.add(sibilings.get(index + 1));
            }
        }

        return new SWTQuery(result);
    }

    public SWTQuery previous() {
        ArrayList<Widget> result = new ArrayList<Widget>();

        for (Widget each : this.items) {
            Widget parent = parentSwitch.doSwitch(each);
            if (parent == null) {
                continue;
            }
            List<Widget> sibilings = childrenSiwtch.doSwitch(parent);
            int index = sibilings.indexOf(each);
            if (index > 0) {
                result.add(sibilings.get(index - 1));
            }
        }

        return new SWTQuery(result);
    }

    public String text() {
        if (this.items.size() > 0) {
            return textPart.getText(this.items.get(0));
        } else {
            return null;
        }
    }

    public SWTQuery each(IWidgetFunction f){
        for (Widget each : this.items) {
            f.doFunction(each);
        }
        return this;
    }

    public SWTQuery text(String text) {
        for (Widget each : this.items) {
            textPart.setText(each, text);
        }
        return this;
    }

    public SWTQuery select(Listener listener) {
        for (Widget each : this.items) {
            each.addListener(SWT.Selection, listener);
        }
        return this;
    }

    public static SWTQuery $(Widget context, String selector) {
        IWidgetFilter filter = FilterBuilder.build(selector);

        ArrayList<Widget> list = new ArrayList<Widget>();
        gatherChildren(context, list, filter);

        return new SWTQuery(list);
    }

    public static SWTQuery $(Widget context, IWidgetFilter filter) {

        ArrayList<Widget> list = new ArrayList<Widget>();
        gatherChildren(context, list, filter);

        return new SWTQuery(list);
    }

    public static SWTQuery $(Widget w) {
        return new SWTQuery(w);
    }

    private static void gatherChildren(Widget w, List<Widget> target, IWidgetFilter filter) {
        if (!target.contains(w) && filter.matches(w)) {
            target.add(w);
        }
        for (Widget eachChild : childrenSiwtch.doSwitch(w)) {
            gatherChildren(eachChild, target, filter);
        }
    }

    public SWTQuery redraw() {
        for (Widget each : this.items) {
            if(each instanceof Control){
                ((Control) each).redraw();
            }
        }
        return this;
    }

}
