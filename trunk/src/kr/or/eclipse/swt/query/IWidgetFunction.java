package kr.or.eclipse.swt.query;

import org.eclipse.swt.widgets.Widget;

public interface IWidgetFunction<T extends Widget> {
    public void doFunction(T w);
}
