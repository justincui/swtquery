package kr.or.eclipse.swt.query.examples;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import kr.or.eclipse.swt.query.IWidgetFunction;
import kr.or.eclipse.swt.query.SWTQuery;
import kr.or.eclipse.swt.query.SWTQueryExtension;

import static kr.or.eclipse.swt.query.SWTQuery.*;

public class PopBar extends SWTQueryExtension {

	private List<String> contents = new ArrayList<String>();

	public PopBar(SWTQuery $context) {
		super($context);
	}

	public PopBar addContent(String... content) {
		for (String each : content) {
			contents.add(each);
		}
		return this;
	}

	public PopBar install() {
		$context.addListener(SWT.MouseDown, new Listener() {
			@Override
			public void handleEvent(Event event) {
				onSelect($(event));
			}
		});
		return this;
	}

	protected void onSelect(final SWTQuery $selectedWidget) {
		final SWTQuery $menu = $selectedWidget.create(Menu.class, SWT.POP_UP);
		for (String each : contents) {
			$menu.create(MenuItem.class, SWT.NORMAL).setText(each);
		}
	}

}
