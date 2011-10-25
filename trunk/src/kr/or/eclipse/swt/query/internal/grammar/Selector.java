package kr.or.eclipse.swt.query.internal.grammar;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Widget;

public class Selector {
	private ArrayList<SelectorSegment> segments = new ArrayList<SelectorSegment>();

	public ArrayList<SelectorSegment> getSegments() {
		return segments;
	}

	public List<Widget> select(List<Widget> origianlContext) {
		List<Widget> context = new ArrayList<Widget>(origianlContext);
		List<Widget> phase = new ArrayList<Widget>();

		for (SelectorSegment eachSeg : segments) {
			for (Widget each : context) {
				List<Widget> eachResult = eachSeg.select(each);
				phase.addAll(eachResult);
			}

			context.clear();
			context.addAll(phase);
			phase.clear();
		}

		return context;
	}

	public List<Widget> select(Widget context) {
		List<Widget> phase = new ArrayList<Widget>();

		return phase;
	}
}
