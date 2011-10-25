/**
 * Copyright 2011 jeeeyul@gmail.com
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 */
package kr.or.eclipse.swt.query;

import java.util.ArrayList;
import java.util.List;

import kr.or.eclipse.swt.query.internal.ChildrenSwitch;
import kr.or.eclipse.swt.query.internal.ParentSwitch;
import kr.or.eclipse.swt.query.internal.UniqueList;
import kr.or.eclipse.swt.query.internal.grammar.Selector;
import kr.or.eclipse.swt.query.internal.grammar.SelectorInterpreter;
import kr.or.eclipse.swt.query.util.WidgetPropertySwitch;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.custom.CTabFolderRenderer;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.dnd.DragSourceEffect;
import org.eclipse.swt.dnd.DropTargetEffect;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Caret;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.IME;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolTip;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.forms.HyperlinkSettings;
import org.eclipse.ui.forms.widgets.FormText;

public final class SWTQuery {

	public static SWTQuery $(Widget w) {
		return new SWTQuery(w);
	}

	public static SWTQuery $(Event event) {
		return new SWTQuery(event.widget);
	}

	public static SWTQuery $(Widget context, String selector) {
		return new SWTQuery(context).select(selector);
	}

	private List<Widget> items;

	public SWTQuery(List<Widget> itemList) {
		this.items = itemList;
	}

	public SWTQuery(Widget... items) {
		this.items = new ArrayList<Widget>();
		for (Widget each : items) {
			this.items.add(each);
		}
	}

	public SWTQuery setGridLayoutData(int style) {
		for (Widget each : items) {
			WidgetPropertySwitch.setLayoutData(each, new GridData(style));
		}
		return this;
	}

	public SWTQuery setGridLayoutData(int horizontalAlignment, int verticalAlignment, boolean grabExcessHorizontalSpace,
			boolean grabExcessVerticalSpace, int horizontalSpan, int verticalSpan) {
		for (Widget each : items) {
			WidgetPropertySwitch.setLayoutData(each, new GridData(horizontalAlignment, verticalAlignment,
					grabExcessHorizontalSpace, grabExcessVerticalSpace, horizontalSpan, verticalSpan));
		}
		return this;
	}

	public SWTQuery select(String selector) {
		List<Selector> selectors = SelectorInterpreter.build(selector);

		List<Widget> result = new UniqueList<Widget>();
		for (Selector each : selectors) {
			result.addAll(each.select(items));
		}
		return new SWTQuery(result);
	}

	public SWTQuery addListener(int eventType, Listener listener) {
		for (Widget each : this.items) {
			each.addListener(eventType, listener);
		}
		return this;
	}

	public SWTQuery each(IWidgetFunction f) {
		for (Widget each : this.items) {
			f.doFunction(each);
		}
		return this;
	}

	@SuppressWarnings("unchecked")
	public <T> T getData(String key) {
		if (items.size() > 0) {
			return (T) items.get(0).getData(key);
		} else {
			return null;
		}
	}

	public SWTQuery layout() {
		for (Widget each : this.items) {
			if (each instanceof Composite) {
				((Composite) each).layout();
			}
		}
		return this;
	}

	public SWTQuery layout(boolean changed) {
		for (Widget each : this.items) {
			if (each instanceof Composite) {
				((Composite) each).layout(changed);
			}
		}
		return this;
	}

	public SWTQuery layout(boolean changed, boolean all) {
		for (Widget each : this.items) {
			if (each instanceof Composite) {
				((Composite) each).layout(changed, all);
			}
		}
		return this;
	}

	public SWTQuery next() {
		return next(1);
	}

	public SWTQuery next(int delta) {
		return translate(delta);
	}

	public SWTQuery parent() {
		ArrayList<Widget> parents = new ArrayList<Widget>();
		for (Widget each : this.items) {
			Widget eachParent = ParentSwitch.INSTANCE.doSwitch(each);
			if (eachParent != null) {
				parents.add(eachParent);
			}
		}
		return parent();
	}

	public SWTQuery prev() {
		return prev(-1);
	}

	public SWTQuery prev(int delta) {
		return translate(delta);
	}

	public SWTQuery redraw() {
		for (Widget each : this.items) {
			if (each instanceof Control) {
				((Control) each).redraw();
			}
		}
		return this;
	}

	public SWTQuery setData(String key, Object data) {
		for (Widget each : items) {
			each.setData(key, data);
		}
		return this;
	}

	private SWTQuery translate(int delta) {
		ArrayList<Widget> result = new ArrayList<Widget>();

		for (Widget each : this.items) {
			Widget parent = ParentSwitch.INSTANCE.doSwitch(each);
			if (parent == null) {
				continue;
			}
			List<Widget> sibilings = ChildrenSwitch.INSTANCE.doSwitch(parent);
			int index = sibilings.indexOf(each);

			int newIndex = index + delta;
			if (newIndex >= 0 && newIndex < sibilings.size()) {
				result.add(sibilings.get(newIndex));
			}
		}

		return new SWTQuery(result);
	}

	public SWTQuery setMaximized(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMaximized(each, value);
		}
		return this;
	}

	public Boolean getMaximized() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMaximized(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setVisible(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setVisible(each, value);
		}
		return this;
	}

	public Boolean getVisible() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getVisible(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setHorizontalPixel(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setHorizontalPixel(each, value);
		}
		return this;
	}

	public Integer getHorizontalPixel() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getHorizontalPixel(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setMoveable(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMoveable(each, value);
		}
		return this;
	}

	public Boolean getMoveable() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMoveable(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setHorizontalIndex(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setHorizontalIndex(each, value);
		}
		return this;
	}

	public Integer getHorizontalIndex() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getHorizontalIndex(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setOrigin(Point value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setOrigin(each, value);
		}
		return this;
	}

	public Point getOrigin() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getOrigin(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setImages(Image[] value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setImages(each, value);
		}
		return this;
	}

	public Image[] getImages() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getImages(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setSingle(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setSingle(each, value);
		}
		return this;
	}

	public Boolean getSingle() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getSingle(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setData(Object value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setData(each, value);
		}
		return this;
	}

	public Object getData() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getData(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setEchoChar(Character value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setEchoChar(each, value);
		}
		return this;
	}

	public Character getEchoChar() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getEchoChar(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setHeadClient(Control value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setHeadClient(each, value);
		}
		return this;
	}

	public Control getHeadClient() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getHeadClient(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setToolTipText(String value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setToolTipText(each, value);
		}
		return this;
	}

	public String getToolTipText() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getToolTipText(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setMenuBar(Menu value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMenuBar(each, value);
		}
		return this;
	}

	public Menu getMenuBar() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMenuBar(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setUnselectedImageVisible(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setUnselectedImageVisible(each, value);
		}
		return this;
	}

	public Boolean getUnselectedImageVisible() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getUnselectedImageVisible(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setUrl(String value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setUrl(each, value);
		}
		return this;
	}

	public String getUrl() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getUrl(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setModified(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setModified(each, value);
		}
		return this;
	}

	public Boolean getModified() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getModified(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setParagraphsSeparated(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setParagraphsSeparated(each, value);
		}
		return this;
	}

	public Boolean getParagraphsSeparated() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getParagraphsSeparated(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setIncrement(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setIncrement(each, value);
		}
		return this;
	}

	public Integer getIncrement() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getIncrement(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setSortDirection(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setSortDirection(each, value);
		}
		return this;
	}

	public Integer getSortDirection() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getSortDirection(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setSeconds(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setSeconds(each, value);
		}
		return this;
	}

	public Integer getSeconds() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getSeconds(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setFullScreen(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setFullScreen(each, value);
		}
		return this;
	}

	public Boolean getFullScreen() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getFullScreen(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setLabelProvider(ILabelProvider value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setLabelProvider(each, value);
		}
		return this;
	}

	public ILabelProvider getLabelProvider() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getLabelProvider(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setVisibleItemCount(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setVisibleItemCount(each, value);
		}
		return this;
	}

	public Integer getVisibleItemCount() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getVisibleItemCount(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setParent(Composite value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setParent(each, value);
		}
		return this;
	}

	public Composite getParent() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getParent(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setThumb(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setThumb(each, value);
		}
		return this;
	}

	public Integer getThumb() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getThumb(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setWordWrap(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setWordWrap(each, value);
		}
		return this;
	}

	public Boolean getWordWrap() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getWordWrap(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setMinimized(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMinimized(each, value);
		}
		return this;
	}

	public Boolean getMinimized() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMinimized(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setCaretOffset(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setCaretOffset(each, value);
		}
		return this;
	}

	public Integer getCaretOffset() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getCaretOffset(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setDecorationColor(Color value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setDecorationColor(each, value);
		}
		return this;
	}

	public Color getDecorationColor() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getDecorationColor(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setBounds(Rectangle value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setBounds(each, value);
		}
		return this;
	}

	public Rectangle getBounds() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getBounds(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setDigits(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setDigits(each, value);
		}
		return this;
	}

	public Integer getDigits() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getDigits(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setIgnoreCase(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setIgnoreCase(each, value);
		}
		return this;
	}

	public Boolean getIgnoreCase() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getIgnoreCase(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setLeft(Control value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setLeft(each, value);
		}
		return this;
	}

	public Control getLeft() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getLeft(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setBottom(Control value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setBottom(each, value);
		}
		return this;
	}

	public Control getBottom() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getBottom(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setFilter(String value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setFilter(each, value);
		}
		return this;
	}

	public String getFilter() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getFilter(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setAllowDuplicates(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setAllowDuplicates(each, value);
		}
		return this;
	}

	public Boolean getAllowDuplicates() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getAllowDuplicates(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setLayout(Layout value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setLayout(each, value);
		}
		return this;
	}

	public Layout getLayout() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getLayout(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setBackgroundImage(Image value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setBackgroundImage(each, value);
		}
		return this;
	}

	public Image getBackgroundImage() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getBackgroundImage(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setTabPosition(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTabPosition(each, value);
		}
		return this;
	}

	public Integer getTabPosition() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTabPosition(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setTitleBarBackground(Color value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTitleBarBackground(each, value);
		}
		return this;
	}

	public Color getTitleBarBackground() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTitleBarBackground(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setTextChars(char[] value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTextChars(each, value);
		}
		return this;
	}

	public char[] getTextChars() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTextChars(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setIME(IME value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setIME(each, value);
		}
		return this;
	}

	public IME getIME() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getIME(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setTabHeight(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTabHeight(each, value);
		}
		return this;
	}

	public Integer getTabHeight() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTabHeight(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setMaximizeVisible(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMaximizeVisible(each, value);
		}
		return this;
	}

	public Boolean getMaximizeVisible() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMaximizeVisible(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setDragDetect(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setDragDetect(each, value);
		}
		return this;
	}

	public Boolean getDragDetect() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getDragDetect(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setAlpha(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setAlpha(each, value);
		}
		return this;
	}

	public Integer getAlpha() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getAlpha(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setJavascriptEnabled(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setJavascriptEnabled(each, value);
		}
		return this;
	}

	public Boolean getJavascriptEnabled() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getJavascriptEnabled(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setPreferredSize(Point value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setPreferredSize(each, value);
		}
		return this;
	}

	public Point getPreferredSize() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getPreferredSize(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setBackground(Color value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setBackground(each, value);
		}
		return this;
	}

	public Color getBackground() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getBackground(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setTopMargin(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTopMargin(each, value);
		}
		return this;
	}

	public Integer getTopMargin() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTopMargin(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setWeights(int[] value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setWeights(each, value);
		}
		return this;
	}

	public int[] getWeights() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getWeights(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setDoubleClickEnabled(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setDoubleClickEnabled(each, value);
		}
		return this;
	}

	public Boolean getDoubleClickEnabled() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getDoubleClickEnabled(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setLayoutData(Object value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setLayoutData(each, value);
		}
		return this;
	}

	public Object getLayoutData() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getLayoutData(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setStyleRanges(StyleRange[] value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setStyleRanges(each, value);
		}
		return this;
	}

	public StyleRange[] getStyleRanges() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getStyleRanges(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setControl(Control value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setControl(each, value);
		}
		return this;
	}

	public Control getControl() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getControl(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setSashWidth(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setSashWidth(each, value);
		}
		return this;
	}

	public Integer getSashWidth() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getSashWidth(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setRenderer(CTabFolderRenderer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setRenderer(each, value);
		}
		return this;
	}

	public CTabFolderRenderer getRenderer() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getRenderer(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setFont(Font value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setFont(each, value);
		}
		return this;
	}

	public Font getFont() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getFont(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setImage(Image value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setImage(each, value);
		}
		return this;
	}

	public Image getImage() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getImage(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setRightMinimumSize(Point value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setRightMinimumSize(each, value);
		}
		return this;
	}

	public Point getRightMinimumSize() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getRightMinimumSize(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setExpanded(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setExpanded(each, value);
		}
		return this;
	}

	public Boolean getExpanded() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getExpanded(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setClient(Control value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setClient(each, value);
		}
		return this;
	}

	public Control getClient() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getClient(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setTitleBarForeground(Color value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTitleBarForeground(each, value);
		}
		return this;
	}

	public Color getTitleBarForeground() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTitleBarForeground(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setRectangles(Rectangle[] value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setRectangles(each, value);
		}
		return this;
	}

	public Rectangle[] getRectangles() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getRectangles(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setRight(Control value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setRight(each, value);
		}
		return this;
	}

	public Control getRight() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getRight(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setItems(String[] value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setItems(each, value);
		}
		return this;
	}

	public String[] getItems() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getItems(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setDefaultItem(MenuItem value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setDefaultItem(each, value);
		}
		return this;
	}

	public MenuItem getDefaultItem() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getDefaultItem(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setOrientation(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setOrientation(each, value);
		}
		return this;
	}

	public Integer getOrientation() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getOrientation(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setMessage(String value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMessage(each, value);
		}
		return this;
	}

	public String getMessage() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMessage(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setUnselectedCloseVisible(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setUnselectedCloseVisible(each, value);
		}
		return this;
	}

	public Boolean getUnselectedCloseVisible() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getUnselectedCloseVisible(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setLeftMargin(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setLeftMargin(each, value);
		}
		return this;
	}

	public Integer getLeftMargin() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getLeftMargin(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setHours(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setHours(each, value);
		}
		return this;
	}

	public Integer getHours() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getHours(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setHoverImage(Image value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setHoverImage(each, value);
		}
		return this;
	}

	public Image getHoverImage() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getHoverImage(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setLinesVisible(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setLinesVisible(each, value);
		}
		return this;
	}

	public Boolean getLinesVisible() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getLinesVisible(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setDefaultButton(Button value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setDefaultButton(each, value);
		}
		return this;
	}

	public Button getDefaultButton() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getDefaultButton(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setBottomMargin(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setBottomMargin(each, value);
		}
		return this;
	}

	public Integer getBottomMargin() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getBottomMargin(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setMinimum(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMinimum(each, value);
		}
		return this;
	}

	public Integer getMinimum() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMinimum(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setAlignment(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setAlignment(each, value);
		}
		return this;
	}

	public Integer getAlignment() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getAlignment(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setAlwaysShowScrollBars(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setAlwaysShowScrollBars(each, value);
		}
		return this;
	}

	public Boolean getAlwaysShowScrollBars() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getAlwaysShowScrollBars(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setTopLeft(Control value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTopLeft(each, value);
		}
		return this;
	}

	public Control getTopLeft() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTopLeft(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setIndent(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setIndent(each, value);
		}
		return this;
	}

	public Integer getIndent() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getIndent(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setImeInputMode(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setImeInputMode(each, value);
		}
		return this;
	}

	public Integer getImeInputMode() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getImeInputMode(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setTopIndex(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTopIndex(each, value);
		}
		return this;
	}

	public Integer getTopIndex() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTopIndex(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setRightWidth(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setRightWidth(each, value);
		}
		return this;
	}

	public Integer getRightWidth() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getRightWidth(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setLayoutDeferred(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setLayoutDeferred(each, value);
		}
		return this;
	}

	public Boolean getLayoutDeferred() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getLayoutDeferred(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setSeparatorControl(Control value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setSeparatorControl(each, value);
		}
		return this;
	}

	public Control getSeparatorControl() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getSeparatorControl(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setForeground(Color value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setForeground(each, value);
		}
		return this;
	}

	public Color getForeground() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getForeground(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setMaximizedControl(Control value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMaximizedControl(each, value);
		}
		return this;
	}

	public Control getMaximizedControl() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMaximizedControl(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setListVisible(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setListVisible(each, value);
		}
		return this;
	}

	public Boolean getListVisible() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getListVisible(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setHeaderVisible(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setHeaderVisible(each, value);
		}
		return this;
	}

	public Boolean getHeaderVisible() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getHeaderVisible(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setTextClient(Control value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTextClient(each, value);
		}
		return this;
	}

	public Control getTextClient() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTextClient(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setMonth(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMonth(each, value);
		}
		return this;
	}

	public Integer getMonth() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMonth(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setBlockSelection(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setBlockSelection(each, value);
		}
		return this;
	}

	public Boolean getBlockSelection() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getBlockSelection(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setMenu(Menu value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMenu(each, value);
		}
		return this;
	}

	public Menu getMenu() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMenu(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setMinimizeVisible(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMinimizeVisible(each, value);
		}
		return this;
	}

	public Boolean getMinimizeVisible() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMinimizeVisible(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setLineSpacing(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setLineSpacing(each, value);
		}
		return this;
	}

	public Integer getLineSpacing() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getLineSpacing(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setID(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setID(each, value);
		}
		return this;
	}

	public Integer getID() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getID(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setHref(Object value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setHref(each, value);
		}
		return this;
	}

	public Object getHref() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getHref(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setResizable(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setResizable(each, value);
		}
		return this;
	}

	public Boolean getResizable() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getResizable(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setWidth(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setWidth(each, value);
		}
		return this;
	}

	public Integer getWidth() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getWidth(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setJustify(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setJustify(each, value);
		}
		return this;
	}

	public Boolean getJustify() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getJustify(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setToolTip(ToolTip value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setToolTip(each, value);
		}
		return this;
	}

	public ToolTip getToolTip() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getToolTip(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setHoverDecorationColor(Color value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setHoverDecorationColor(each, value);
		}
		return this;
	}

	public Color getHoverDecorationColor() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getHoverDecorationColor(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setSize(Point value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setSize(each, value);
		}
		return this;
	}

	public Point getSize() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getSize(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setStippled(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setStippled(each, value);
		}
		return this;
	}

	public Boolean getStippled() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getStippled(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setSelectionBackground(Color value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setSelectionBackground(each, value);
		}
		return this;
	}

	public Color getSelectionBackground() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getSelectionBackground(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setTextLimit(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTextLimit(each, value);
		}
		return this;
	}

	public Integer getTextLimit() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTextLimit(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setMaximum(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMaximum(each, value);
		}
		return this;
	}

	public Integer getMaximum() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMaximum(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setItemCount(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setItemCount(each, value);
		}
		return this;
	}

	public Integer getItemCount() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getItemCount(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setHeight(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setHeight(each, value);
		}
		return this;
	}

	public Integer getHeight() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getHeight(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setTopRight(Control value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTopRight(each, value);
		}
		return this;
	}

	public Control getTopRight() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTopRight(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setHyperlinkSettings(HyperlinkSettings value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setHyperlinkSettings(each, value);
		}
		return this;
	}

	public HyperlinkSettings getHyperlinkSettings() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getHyperlinkSettings(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setMinWidth(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMinWidth(each, value);
		}
		return this;
	}

	public Integer getMinWidth() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMinWidth(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setTransfer(Transfer[] value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTransfer(each, value);
		}
		return this;
	}

	public Transfer[] getTransfer() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTransfer(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setActiveImage(Image value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setActiveImage(each, value);
		}
		return this;
	}

	public Image getActiveImage() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getActiveImage(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setEditable(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setEditable(each, value);
		}
		return this;
	}

	public Boolean getEditable() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getEditable(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setFormText(FormText value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setFormText(each, value);
		}
		return this;
	}

	public FormText getFormText() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getFormText(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setTouchEnabled(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTouchEnabled(each, value);
		}
		return this;
	}

	public Boolean getTouchEnabled() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTouchEnabled(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setDropTargetEffect(DropTargetEffect value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setDropTargetEffect(each, value);
		}
		return this;
	}

	public DropTargetEffect getDropTargetEffect() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getDropTargetEffect(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setSimple(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setSimple(each, value);
		}
		return this;
	}

	public Boolean getSimple() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getSimple(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setMarginColor(Color value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMarginColor(each, value);
		}
		return this;
	}

	public Color getMarginColor() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMarginColor(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setTopPixel(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTopPixel(each, value);
		}
		return this;
	}

	public Integer getTopPixel() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTopPixel(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setChecked(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setChecked(each, value);
		}
		return this;
	}

	public Boolean getChecked() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getChecked(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setTitleBarBorderColor(Color value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTitleBarBorderColor(each, value);
		}
		return this;
	}

	public Color getTitleBarBorderColor() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTitleBarBorderColor(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setLocked(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setLocked(each, value);
		}
		return this;
	}

	public Boolean getLocked() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getLocked(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setCaret(Caret value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setCaret(each, value);
		}
		return this;
	}

	public Caret getCaret() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getCaret(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setWrapIndent(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setWrapIndent(each, value);
		}
		return this;
	}

	public Integer getWrapIndent() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getWrapIndent(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setAutoHide(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setAutoHide(each, value);
		}
		return this;
	}

	public Boolean getAutoHide() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getAutoHide(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setEnabled(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setEnabled(each, value);
		}
		return this;
	}

	public Boolean getEnabled() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getEnabled(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setDragSourceEffect(DragSourceEffect value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setDragSourceEffect(each, value);
		}
		return this;
	}

	public DragSourceEffect getDragSourceEffect() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getDragSourceEffect(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setShowFocusedControl(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setShowFocusedControl(each, value);
		}
		return this;
	}

	public Boolean getShowFocusedControl() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getShowFocusedControl(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setColumnOrder(int[] value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setColumnOrder(each, value);
		}
		return this;
	}

	public int[] getColumnOrder() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getColumnOrder(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setMRUVisible(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMRUVisible(each, value);
		}
		return this;
	}

	public Boolean getMRUVisible() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMRUVisible(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setGrayed(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setGrayed(each, value);
		}
		return this;
	}

	public Boolean getGrayed() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getGrayed(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setDay(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setDay(each, value);
		}
		return this;
	}

	public Integer getDay() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getDay(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setCursor(Cursor value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setCursor(each, value);
		}
		return this;
	}

	public Cursor getCursor() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getCursor(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setYear(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setYear(each, value);
		}
		return this;
	}

	public Integer getYear() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getYear(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setToolBarVerticalAlignment(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setToolBarVerticalAlignment(each, value);
		}
		return this;
	}

	public Integer getToolBarVerticalAlignment() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getToolBarVerticalAlignment(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setRegion(Region value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setRegion(each, value);
		}
		return this;
	}

	public Region getRegion() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getRegion(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setAccelerator(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setAccelerator(each, value);
		}
		return this;
	}

	public Integer getAccelerator() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getAccelerator(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setWrapIndices(int[] value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setWrapIndices(each, value);
		}
		return this;
	}

	public int[] getWrapIndices() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getWrapIndices(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setPageIncrement(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setPageIncrement(each, value);
		}
		return this;
	}

	public Integer getPageIncrement() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getPageIncrement(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setMatchEmptyString(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMatchEmptyString(each, value);
		}
		return this;
	}

	public Boolean getMatchEmptyString() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMatchEmptyString(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setCompositionOffset(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setCompositionOffset(each, value);
		}
		return this;
	}

	public Integer getCompositionOffset() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getCompositionOffset(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setDescription(String value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setDescription(each, value);
		}
		return this;
	}

	public String getDescription() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getDescription(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setShowClose(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setShowClose(each, value);
		}
		return this;
	}

	public Boolean getShowClose() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getShowClose(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setSelectionForeground(Color value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setSelectionForeground(each, value);
		}
		return this;
	}

	public Color getSelectionForeground() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getSelectionForeground(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setBackgroundMode(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setBackgroundMode(each, value);
		}
		return this;
	}

	public Integer getBackgroundMode() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getBackgroundMode(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setTitleBarGradientBackground(Color value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTitleBarGradientBackground(each, value);
		}
		return this;
	}

	public Color getTitleBarGradientBackground() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTitleBarGradientBackground(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setBlockSelectionBounds(Rectangle value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setBlockSelectionBounds(each, value);
		}
		return this;
	}

	public Rectangle getBlockSelectionBounds() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getBlockSelectionBounds(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setMinHeight(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMinHeight(each, value);
		}
		return this;
	}

	public Integer getMinHeight() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMinHeight(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setRightMargin(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setRightMargin(each, value);
		}
		return this;
	}

	public Integer getRightMargin() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getRightMargin(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setLocation(Point value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setLocation(each, value);
		}
		return this;
	}

	public Point getLocation() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getLocation(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setSpacing(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setSpacing(each, value);
		}
		return this;
	}

	public Integer getSpacing() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getSpacing(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setMinimumSize(Point value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMinimumSize(each, value);
		}
		return this;
	}

	public Point getMinimumSize() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMinimumSize(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setDescriptionControl(Control value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setDescriptionControl(each, value);
		}
		return this;
	}

	public Control getDescriptionControl() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getDescriptionControl(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setText(String value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setText(each, value);
		}
		return this;
	}

	public String getText() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getText(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setTabs(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTabs(each, value);
		}
		return this;
	}

	public Integer getTabs() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTabs(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setBorderVisible(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setBorderVisible(each, value);
		}
		return this;
	}

	public Boolean getBorderVisible() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getBorderVisible(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setState(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setState(each, value);
		}
		return this;
	}

	public Integer getState() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getState(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setTopItem(TreeItem value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTopItem(each, value);
		}
		return this;
	}

	public TreeItem getTopItem() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTopItem(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setTabList(Control[] value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTabList(each, value);
		}
		return this;
	}

	public Control[] getTabList() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTabList(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setTopCenter(Control value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTopCenter(each, value);
		}
		return this;
	}

	public Control getTopCenter() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTopCenter(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setExpandHorizontal(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setExpandHorizontal(each, value);
		}
		return this;
	}

	public Boolean getExpandHorizontal() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getExpandHorizontal(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setTabStops(int[] value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTabStops(each, value);
		}
		return this;
	}

	public int[] getTabStops() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTabStops(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setMinutes(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMinutes(each, value);
		}
		return this;
	}

	public Integer getMinutes() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMinutes(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setExpandVertical(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setExpandVertical(each, value);
		}
		return this;
	}

	public Boolean getExpandVertical() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getExpandVertical(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setHotImage(Image value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setHotImage(each, value);
		}
		return this;
	}

	public Image getHotImage() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getHotImage(items.get(0));
		} else {
			return null;
		}
	}

	public SWTQuery setMinimumCharacters(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMinimumCharacters(each, value);
		}
		return this;
	}

	public Integer getMinimumCharacters() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMinimumCharacters(items.get(0));
		} else {
			return null;
		}
	}

}
