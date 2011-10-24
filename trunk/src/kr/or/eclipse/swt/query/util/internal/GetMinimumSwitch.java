package kr.or.eclipse.swt.query.util.internal;

import java.lang.Integer;
import org.eclipse.swt.widgets.Widget;
import kr.or.eclipse.swt.query.util.WidgetSwitch;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.ScrollBar;
/*
 * generated by SWT Query SDK at Mon Oct 24 19:21:20 KST 2011
 */ 
public class GetMinimumSwitch extends WidgetSwitch<Integer> {
	public Integer getProperty(Widget widget){
		return doSwitch(widget);
	}

	public Integer caseSpinner(Spinner widget){
		return widget.getMinimum();
	}
	public Integer caseProgressBar(ProgressBar widget){
		return widget.getMinimum();
	}
	public Integer caseScale(Scale widget){
		return widget.getMinimum();
	}
	public Integer caseSlider(Slider widget){
		return widget.getMinimum();
	}
	public Integer caseScrollBar(ScrollBar widget){
		return widget.getMinimum();
	}
}
    