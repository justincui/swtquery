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
import org.eclipse.swt.widgets.Display;
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

	public static SWTQuery $(Event event) {
		return new SWTQuery(event.widget);
	}

	public static SWTQuery $(Widget w) {
		return new SWTQuery(w);
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

	public Integer getAccelerator() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getAccelerator(items.get(0));
		} else {
			return null;
		}
	}

	public Image getActiveImage() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getActiveImage(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getAlignment() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getAlignment(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getAllowDuplicates() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getAllowDuplicates(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getAlpha() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getAlpha(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getAlwaysShowScrollBars() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getAlwaysShowScrollBars(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getAutoHide() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getAutoHide(items.get(0));
		} else {
			return null;
		}
	}

	public Color getBackground() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getBackground(items.get(0));
		} else {
			return null;
		}
	}

	public Image getBackgroundImage() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getBackgroundImage(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getBackgroundMode() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getBackgroundMode(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getBlockSelection() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getBlockSelection(items.get(0));
		} else {
			return null;
		}
	}

	public Rectangle getBlockSelectionBounds() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getBlockSelectionBounds(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getBorderVisible() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getBorderVisible(items.get(0));
		} else {
			return null;
		}
	}

	public Control getBottom() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getBottom(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getBottomMargin() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getBottomMargin(items.get(0));
		} else {
			return null;
		}
	}

	public Rectangle getBounds() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getBounds(items.get(0));
		} else {
			return null;
		}
	}

	public Caret getCaret() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getCaret(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getCaretOffset() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getCaretOffset(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getChecked() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getChecked(items.get(0));
		} else {
			return null;
		}
	}

	public Control getClient() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getClient(items.get(0));
		} else {
			return null;
		}
	}

	public int[] getColumnOrder() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getColumnOrder(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getCompositionOffset() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getCompositionOffset(items.get(0));
		} else {
			return null;
		}
	}

	public Control getControl() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getControl(items.get(0));
		} else {
			return null;
		}
	}

	public Cursor getCursor() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getCursor(items.get(0));
		} else {
			return null;
		}
	}

	public Object getData() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getData(items.get(0));
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T getData(String key) {
		if (items.size() > 0) {
			return (T) items.get(0).getData(key);
		} else {
			return null;
		}
	}

	public Integer getDay() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getDay(items.get(0));
		} else {
			return null;
		}
	}

	public Color getDecorationColor() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getDecorationColor(items.get(0));
		} else {
			return null;
		}
	}

	public Button getDefaultButton() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getDefaultButton(items.get(0));
		} else {
			return null;
		}
	}

	public MenuItem getDefaultItem() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getDefaultItem(items.get(0));
		} else {
			return null;
		}
	}

	public String getDescription() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getDescription(items.get(0));
		} else {
			return null;
		}
	}

	public Control getDescriptionControl() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getDescriptionControl(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getDigits() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getDigits(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getDoubleClickEnabled() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getDoubleClickEnabled(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getDragDetect() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getDragDetect(items.get(0));
		} else {
			return null;
		}
	}

	public DragSourceEffect getDragSourceEffect() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getDragSourceEffect(items.get(0));
		} else {
			return null;
		}
	}

	public DropTargetEffect getDropTargetEffect() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getDropTargetEffect(items.get(0));
		} else {
			return null;
		}
	}

	public Character getEchoChar() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getEchoChar(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getEditable() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getEditable(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getEnabled() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getEnabled(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getExpanded() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getExpanded(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getExpandHorizontal() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getExpandHorizontal(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getExpandVertical() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getExpandVertical(items.get(0));
		} else {
			return null;
		}
	}

	public String getFilter() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getFilter(items.get(0));
		} else {
			return null;
		}
	}

	public Font getFont() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getFont(items.get(0));
		} else {
			return null;
		}
	}

	public Color getForeground() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getForeground(items.get(0));
		} else {
			return null;
		}
	}

	public FormText getFormText() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getFormText(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getFullScreen() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getFullScreen(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getGrayed() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getGrayed(items.get(0));
		} else {
			return null;
		}
	}

	public Control getHeadClient() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getHeadClient(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getHeaderVisible() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getHeaderVisible(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getHeight() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getHeight(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getHorizontalIndex() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getHorizontalIndex(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getHorizontalPixel() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getHorizontalPixel(items.get(0));
		} else {
			return null;
		}
	}

	public Image getHotImage() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getHotImage(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getHours() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getHours(items.get(0));
		} else {
			return null;
		}
	}

	public Color getHoverDecorationColor() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getHoverDecorationColor(items.get(0));
		} else {
			return null;
		}
	}

	public Image getHoverImage() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getHoverImage(items.get(0));
		} else {
			return null;
		}
	}

	public Object getHref() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getHref(items.get(0));
		} else {
			return null;
		}
	}

	public HyperlinkSettings getHyperlinkSettings() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getHyperlinkSettings(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getID() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getID(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getIgnoreCase() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getIgnoreCase(items.get(0));
		} else {
			return null;
		}
	}

	public Image getImage() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getImage(items.get(0));
		} else {
			return null;
		}
	}

	public Image[] getImages() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getImages(items.get(0));
		} else {
			return null;
		}
	}

	public IME getIME() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getIME(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getImeInputMode() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getImeInputMode(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getIncrement() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getIncrement(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getIndent() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getIndent(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getItemCount() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getItemCount(items.get(0));
		} else {
			return null;
		}
	}

	public String[] getItems() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getItems(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getJavascriptEnabled() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getJavascriptEnabled(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getJustify() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getJustify(items.get(0));
		} else {
			return null;
		}
	}

	public ILabelProvider getLabelProvider() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getLabelProvider(items.get(0));
		} else {
			return null;
		}
	}

	public Layout getLayout() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getLayout(items.get(0));
		} else {
			return null;
		}
	}

	public Object getLayoutData() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getLayoutData(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getLayoutDeferred() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getLayoutDeferred(items.get(0));
		} else {
			return null;
		}
	}

	public Control getLeft() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getLeft(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getLeftMargin() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getLeftMargin(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getLineSpacing() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getLineSpacing(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getLinesVisible() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getLinesVisible(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getListVisible() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getListVisible(items.get(0));
		} else {
			return null;
		}
	}

	public Point getLocation() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getLocation(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getLocked() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getLocked(items.get(0));
		} else {
			return null;
		}
	}

	public Color getMarginColor() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMarginColor(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getMatchEmptyString() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMatchEmptyString(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getMaximized() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMaximized(items.get(0));
		} else {
			return null;
		}
	}

	public Control getMaximizedControl() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMaximizedControl(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getMaximizeVisible() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMaximizeVisible(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getMaximum() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMaximum(items.get(0));
		} else {
			return null;
		}
	}

	public Menu getMenu() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMenu(items.get(0));
		} else {
			return null;
		}
	}

	public Menu getMenuBar() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMenuBar(items.get(0));
		} else {
			return null;
		}
	}

	public String getMessage() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMessage(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getMinHeight() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMinHeight(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getMinimized() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMinimized(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getMinimizeVisible() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMinimizeVisible(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getMinimum() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMinimum(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getMinimumCharacters() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMinimumCharacters(items.get(0));
		} else {
			return null;
		}
	}

	public Point getMinimumSize() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMinimumSize(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getMinutes() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMinutes(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getMinWidth() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMinWidth(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getModified() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getModified(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getMonth() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMonth(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getMoveable() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMoveable(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getMRUVisible() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getMRUVisible(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getOrientation() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getOrientation(items.get(0));
		} else {
			return null;
		}
	}

	public Point getOrigin() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getOrigin(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getPageIncrement() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getPageIncrement(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getParagraphsSeparated() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getParagraphsSeparated(items.get(0));
		} else {
			return null;
		}
	}

	public Composite getParent() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getParent(items.get(0));
		} else {
			return null;
		}
	}

	public Point getPreferredSize() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getPreferredSize(items.get(0));
		} else {
			return null;
		}
	}

	public Rectangle[] getRectangles() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getRectangles(items.get(0));
		} else {
			return null;
		}
	}

	public Region getRegion() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getRegion(items.get(0));
		} else {
			return null;
		}
	}

	public CTabFolderRenderer getRenderer() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getRenderer(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getResizable() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getResizable(items.get(0));
		} else {
			return null;
		}
	}

	public Control getRight() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getRight(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getRightMargin() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getRightMargin(items.get(0));
		} else {
			return null;
		}
	}

	public Point getRightMinimumSize() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getRightMinimumSize(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getRightWidth() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getRightWidth(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getSashWidth() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getSashWidth(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getSeconds() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getSeconds(items.get(0));
		} else {
			return null;
		}
	}

	public Color getSelectionBackground() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getSelectionBackground(items.get(0));
		} else {
			return null;
		}
	}

	public Color getSelectionForeground() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getSelectionForeground(items.get(0));
		} else {
			return null;
		}
	}

	public Control getSeparatorControl() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getSeparatorControl(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getShowClose() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getShowClose(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getShowFocusedControl() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getShowFocusedControl(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getSimple() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getSimple(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getSingle() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getSingle(items.get(0));
		} else {
			return null;
		}
	}

	public Point getSize() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getSize(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getSortDirection() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getSortDirection(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getSpacing() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getSpacing(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getState() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getState(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getStippled() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getStippled(items.get(0));
		} else {
			return null;
		}
	}

	public StyleRange[] getStyleRanges() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getStyleRanges(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getTabHeight() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTabHeight(items.get(0));
		} else {
			return null;
		}
	}

	public Control[] getTabList() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTabList(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getTabPosition() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTabPosition(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getTabs() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTabs(items.get(0));
		} else {
			return null;
		}
	}

	public int[] getTabStops() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTabStops(items.get(0));
		} else {
			return null;
		}
	}

	public String getText() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getText(items.get(0));
		} else {
			return null;
		}
	}

	public char[] getTextChars() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTextChars(items.get(0));
		} else {
			return null;
		}
	}

	public Control getTextClient() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTextClient(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getTextLimit() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTextLimit(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getThumb() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getThumb(items.get(0));
		} else {
			return null;
		}
	}

	public Color getTitleBarBackground() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTitleBarBackground(items.get(0));
		} else {
			return null;
		}
	}

	public Color getTitleBarBorderColor() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTitleBarBorderColor(items.get(0));
		} else {
			return null;
		}
	}

	public Color getTitleBarForeground() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTitleBarForeground(items.get(0));
		} else {
			return null;
		}
	}

	public Color getTitleBarGradientBackground() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTitleBarGradientBackground(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getToolBarVerticalAlignment() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getToolBarVerticalAlignment(items.get(0));
		} else {
			return null;
		}
	}

	public ToolTip getToolTip() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getToolTip(items.get(0));
		} else {
			return null;
		}
	}

	public String getToolTipText() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getToolTipText(items.get(0));
		} else {
			return null;
		}
	}

	public Control getTopCenter() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTopCenter(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getTopIndex() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTopIndex(items.get(0));
		} else {
			return null;
		}
	}

	public TreeItem getTopItem() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTopItem(items.get(0));
		} else {
			return null;
		}
	}

	public Control getTopLeft() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTopLeft(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getTopMargin() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTopMargin(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getTopPixel() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTopPixel(items.get(0));
		} else {
			return null;
		}
	}

	public Control getTopRight() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTopRight(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getTouchEnabled() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTouchEnabled(items.get(0));
		} else {
			return null;
		}
	}

	public Transfer[] getTransfer() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getTransfer(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getUnselectedCloseVisible() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getUnselectedCloseVisible(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getUnselectedImageVisible() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getUnselectedImageVisible(items.get(0));
		} else {
			return null;
		}
	}

	public String getUrl() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getUrl(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getVisible() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getVisible(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getVisibleItemCount() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getVisibleItemCount(items.get(0));
		} else {
			return null;
		}
	}

	public int[] getWeights() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getWeights(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getWidth() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getWidth(items.get(0));
		} else {
			return null;
		}
	}

	public Boolean getWordWrap() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getWordWrap(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getWrapIndent() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getWrapIndent(items.get(0));
		} else {
			return null;
		}
	}

	public int[] getWrapIndices() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getWrapIndices(items.get(0));
		} else {
			return null;
		}
	}

	public Integer getYear() {
		if (items.size() > 0) {
			return WidgetPropertySwitch.getYear(items.get(0));
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

	public SWTQuery select(String selector) {
		List<Selector> selectors = SelectorInterpreter.build(selector);

		List<Widget> result = new UniqueList<Widget>();
		for (Selector each : selectors) {
			result.addAll(each.select(items));
		}
		return new SWTQuery(result);
	}

	public SWTQuery setAccelerator(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setAccelerator(each, value);
		}
		return this;
	}

	public SWTQuery setActiveImage(Image value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setActiveImage(each, value);
		}
		return this;
	}

	public SWTQuery setAlignment(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setAlignment(each, value);
		}
		return this;
	}

	public SWTQuery setAllowDuplicates(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setAllowDuplicates(each, value);
		}
		return this;
	}

	public SWTQuery setAlpha(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setAlpha(each, value);
		}
		return this;
	}

	public SWTQuery setAlwaysShowScrollBars(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setAlwaysShowScrollBars(each, value);
		}
		return this;
	}

	public SWTQuery setAutoHide(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setAutoHide(each, value);
		}
		return this;
	}

	public SWTQuery setBackground(Color value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setBackground(each, value);
		}
		return this;
	}

	public SWTQuery setBackgroundImage(Image value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setBackgroundImage(each, value);
		}
		return this;
	}

	public SWTQuery setBackgroundMode(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setBackgroundMode(each, value);
		}
		return this;
	}

	public SWTQuery setBlockSelection(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setBlockSelection(each, value);
		}
		return this;
	}

	public SWTQuery setBlockSelectionBounds(Rectangle value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setBlockSelectionBounds(each, value);
		}
		return this;
	}

	public SWTQuery setBorderVisible(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setBorderVisible(each, value);
		}
		return this;
	}

	public SWTQuery setBottom(Control value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setBottom(each, value);
		}
		return this;
	}

	public SWTQuery setBottomMargin(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setBottomMargin(each, value);
		}
		return this;
	}

	public SWTQuery setBounds(Rectangle value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setBounds(each, value);
		}
		return this;
	}

	public SWTQuery setCaret(Caret value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setCaret(each, value);
		}
		return this;
	}

	public SWTQuery setCaretOffset(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setCaretOffset(each, value);
		}
		return this;
	}

	public SWTQuery setChecked(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setChecked(each, value);
		}
		return this;
	}

	public SWTQuery setClient(Control value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setClient(each, value);
		}
		return this;
	}

	public SWTQuery setColumnOrder(int[] value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setColumnOrder(each, value);
		}
		return this;
	}

	public SWTQuery setCompositionOffset(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setCompositionOffset(each, value);
		}
		return this;
	}

	public SWTQuery setControl(Control value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setControl(each, value);
		}
		return this;
	}

	public SWTQuery setCursor(Cursor value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setCursor(each, value);
		}
		return this;
	}

	public SWTQuery setData(Object value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setData(each, value);
		}
		return this;
	}

	public SWTQuery setData(String key, Object data) {
		for (Widget each : items) {
			each.setData(key, data);
		}
		return this;
	}

	public SWTQuery setDay(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setDay(each, value);
		}
		return this;
	}

	public SWTQuery setDecorationColor(Color value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setDecorationColor(each, value);
		}
		return this;
	}

	public SWTQuery setDefaultButton(Button value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setDefaultButton(each, value);
		}
		return this;
	}

	public SWTQuery setDefaultItem(MenuItem value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setDefaultItem(each, value);
		}
		return this;
	}

	public SWTQuery setDescription(String value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setDescription(each, value);
		}
		return this;
	}

	public SWTQuery setDescriptionControl(Control value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setDescriptionControl(each, value);
		}
		return this;
	}

	public SWTQuery setDigits(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setDigits(each, value);
		}
		return this;
	}

	public SWTQuery setDoubleClickEnabled(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setDoubleClickEnabled(each, value);
		}
		return this;
	}

	public SWTQuery setDragDetect(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setDragDetect(each, value);
		}
		return this;
	}

	public SWTQuery setDragSourceEffect(DragSourceEffect value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setDragSourceEffect(each, value);
		}
		return this;
	}

	public SWTQuery setDropTargetEffect(DropTargetEffect value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setDropTargetEffect(each, value);
		}
		return this;
	}

	public SWTQuery setEchoChar(Character value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setEchoChar(each, value);
		}
		return this;
	}

	public SWTQuery setEditable(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setEditable(each, value);
		}
		return this;
	}

	public SWTQuery setEnabled(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setEnabled(each, value);
		}
		return this;
	}

	public SWTQuery setExpanded(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setExpanded(each, value);
		}
		return this;
	}

	public SWTQuery setExpandHorizontal(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setExpandHorizontal(each, value);
		}
		return this;
	}

	public SWTQuery setExpandVertical(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setExpandVertical(each, value);
		}
		return this;
	}

	public SWTQuery setFilter(String value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setFilter(each, value);
		}
		return this;
	}

	public SWTQuery setFont(Font value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setFont(each, value);
		}
		return this;
	}

	public SWTQuery setForeground(Color value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setForeground(each, value);
		}
		return this;
	}

	public SWTQuery setFormText(FormText value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setFormText(each, value);
		}
		return this;
	}

	public SWTQuery setFullScreen(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setFullScreen(each, value);
		}
		return this;
	}

	public SWTQuery setGrayed(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setGrayed(each, value);
		}
		return this;
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

	public SWTQuery setHeadClient(Control value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setHeadClient(each, value);
		}
		return this;
	}

	public SWTQuery setHeaderVisible(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setHeaderVisible(each, value);
		}
		return this;
	}

	public SWTQuery setHeight(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setHeight(each, value);
		}
		return this;
	}

	public SWTQuery setHorizontalIndex(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setHorizontalIndex(each, value);
		}
		return this;
	}

	public SWTQuery setHorizontalPixel(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setHorizontalPixel(each, value);
		}
		return this;
	}

	public SWTQuery setHotImage(Image value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setHotImage(each, value);
		}
		return this;
	}

	public SWTQuery setHours(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setHours(each, value);
		}
		return this;
	}

	public SWTQuery setHoverDecorationColor(Color value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setHoverDecorationColor(each, value);
		}
		return this;
	}

	public SWTQuery setHoverImage(Image value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setHoverImage(each, value);
		}
		return this;
	}

	public SWTQuery setHref(Object value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setHref(each, value);
		}
		return this;
	}

	public SWTQuery setHyperlinkSettings(HyperlinkSettings value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setHyperlinkSettings(each, value);
		}
		return this;
	}

	public SWTQuery setID(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setID(each, value);
		}
		return this;
	}

	public SWTQuery setIgnoreCase(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setIgnoreCase(each, value);
		}
		return this;
	}

	public SWTQuery setImage(Image value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setImage(each, value);
		}
		return this;
	}

	public SWTQuery setImages(Image[] value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setImages(each, value);
		}
		return this;
	}

	public SWTQuery setIME(IME value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setIME(each, value);
		}
		return this;
	}

	public SWTQuery setImeInputMode(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setImeInputMode(each, value);
		}
		return this;
	}

	public SWTQuery setIncrement(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setIncrement(each, value);
		}
		return this;
	}

	public SWTQuery setIndent(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setIndent(each, value);
		}
		return this;
	}

	public SWTQuery setItemCount(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setItemCount(each, value);
		}
		return this;
	}

	public SWTQuery setItems(String[] value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setItems(each, value);
		}
		return this;
	}

	public SWTQuery setJavascriptEnabled(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setJavascriptEnabled(each, value);
		}
		return this;
	}

	public SWTQuery setJustify(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setJustify(each, value);
		}
		return this;
	}

	public SWTQuery setLabelProvider(ILabelProvider value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setLabelProvider(each, value);
		}
		return this;
	}

	public SWTQuery setLayout(Layout value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setLayout(each, value);
		}
		return this;
	}

	public SWTQuery setLayoutData(Object value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setLayoutData(each, value);
		}
		return this;
	}

	public SWTQuery setLayoutDeferred(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setLayoutDeferred(each, value);
		}
		return this;
	}

	public SWTQuery setLeft(Control value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setLeft(each, value);
		}
		return this;
	}

	public SWTQuery setLeftMargin(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setLeftMargin(each, value);
		}
		return this;
	}

	public SWTQuery setLineSpacing(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setLineSpacing(each, value);
		}
		return this;
	}

	public SWTQuery setLinesVisible(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setLinesVisible(each, value);
		}
		return this;
	}

	public SWTQuery setListVisible(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setListVisible(each, value);
		}
		return this;
	}

	public SWTQuery setLocation(Point value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setLocation(each, value);
		}
		return this;
	}

	public SWTQuery setLocked(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setLocked(each, value);
		}
		return this;
	}

	public SWTQuery setMarginColor(Color value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMarginColor(each, value);
		}
		return this;
	}

	public SWTQuery setMatchEmptyString(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMatchEmptyString(each, value);
		}
		return this;
	}

	public SWTQuery setMaximized(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMaximized(each, value);
		}
		return this;
	}

	public SWTQuery setMaximizedControl(Control value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMaximizedControl(each, value);
		}
		return this;
	}

	public SWTQuery setMaximizeVisible(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMaximizeVisible(each, value);
		}
		return this;
	}

	public SWTQuery setMaximum(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMaximum(each, value);
		}
		return this;
	}

	public SWTQuery setMenu(Menu value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMenu(each, value);
		}
		return this;
	}

	public SWTQuery setMenuBar(Menu value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMenuBar(each, value);
		}
		return this;
	}

	public SWTQuery setMessage(String value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMessage(each, value);
		}
		return this;
	}

	public SWTQuery setMinHeight(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMinHeight(each, value);
		}
		return this;
	}

	public SWTQuery setMinimized(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMinimized(each, value);
		}
		return this;
	}

	public SWTQuery setMinimizeVisible(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMinimizeVisible(each, value);
		}
		return this;
	}

	public SWTQuery setMinimum(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMinimum(each, value);
		}
		return this;
	}

	public SWTQuery setMinimumCharacters(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMinimumCharacters(each, value);
		}
		return this;
	}

	public SWTQuery setMinimumSize(Point value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMinimumSize(each, value);
		}
		return this;
	}

	public SWTQuery setMinutes(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMinutes(each, value);
		}
		return this;
	}

	public SWTQuery setMinWidth(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMinWidth(each, value);
		}
		return this;
	}

	public SWTQuery setModified(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setModified(each, value);
		}
		return this;
	}

	public SWTQuery setMonth(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMonth(each, value);
		}
		return this;
	}

	public SWTQuery setMoveable(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMoveable(each, value);
		}
		return this;
	}

	public SWTQuery setMRUVisible(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setMRUVisible(each, value);
		}
		return this;
	}

	public SWTQuery setOrientation(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setOrientation(each, value);
		}
		return this;
	}

	public SWTQuery setOrigin(Point value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setOrigin(each, value);
		}
		return this;
	}

	public SWTQuery setPageIncrement(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setPageIncrement(each, value);
		}
		return this;
	}

	public SWTQuery setParagraphsSeparated(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setParagraphsSeparated(each, value);
		}
		return this;
	}

	public SWTQuery setParent(Composite value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setParent(each, value);
		}
		return this;
	}

	public SWTQuery setPreferredSize(Point value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setPreferredSize(each, value);
		}
		return this;
	}

	public SWTQuery setRectangles(Rectangle[] value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setRectangles(each, value);
		}
		return this;
	}

	public SWTQuery setRegion(Region value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setRegion(each, value);
		}
		return this;
	}

	public SWTQuery setRenderer(CTabFolderRenderer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setRenderer(each, value);
		}
		return this;
	}

	public SWTQuery setResizable(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setResizable(each, value);
		}
		return this;
	}

	public SWTQuery setRight(Control value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setRight(each, value);
		}
		return this;
	}

	public SWTQuery setRightMargin(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setRightMargin(each, value);
		}
		return this;
	}

	public SWTQuery setRightMinimumSize(Point value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setRightMinimumSize(each, value);
		}
		return this;
	}

	public SWTQuery setRightWidth(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setRightWidth(each, value);
		}
		return this;
	}

	public SWTQuery setSashWidth(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setSashWidth(each, value);
		}
		return this;
	}

	public SWTQuery setSeconds(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setSeconds(each, value);
		}
		return this;
	}

	public SWTQuery setSelectionBackground(Color value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setSelectionBackground(each, value);
		}
		return this;
	}

	public SWTQuery setSelectionForeground(Color value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setSelectionForeground(each, value);
		}
		return this;
	}

	public SWTQuery setSeparatorControl(Control value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setSeparatorControl(each, value);
		}
		return this;
	}

	public SWTQuery setShowClose(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setShowClose(each, value);
		}
		return this;
	}

	public SWTQuery setShowFocusedControl(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setShowFocusedControl(each, value);
		}
		return this;
	}

	public SWTQuery setSimple(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setSimple(each, value);
		}
		return this;
	}

	public SWTQuery setSingle(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setSingle(each, value);
		}
		return this;
	}

	public SWTQuery setSize(Point value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setSize(each, value);
		}
		return this;
	}

	public SWTQuery setSortDirection(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setSortDirection(each, value);
		}
		return this;
	}

	public SWTQuery setSpacing(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setSpacing(each, value);
		}
		return this;
	}

	public SWTQuery setState(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setState(each, value);
		}
		return this;
	}

	public SWTQuery setStippled(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setStippled(each, value);
		}
		return this;
	}

	public SWTQuery setStyleRanges(StyleRange[] value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setStyleRanges(each, value);
		}
		return this;
	}

	public SWTQuery setTabHeight(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTabHeight(each, value);
		}
		return this;
	}

	public SWTQuery setTabList(Control[] value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTabList(each, value);
		}
		return this;
	}

	public SWTQuery setTabPosition(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTabPosition(each, value);
		}
		return this;
	}

	public SWTQuery setTabs(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTabs(each, value);
		}
		return this;
	}

	public SWTQuery setTabStops(int[] value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTabStops(each, value);
		}
		return this;
	}

	public SWTQuery setText(String value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setText(each, value);
		}
		return this;
	}

	public SWTQuery setTextChars(char[] value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTextChars(each, value);
		}
		return this;
	}

	public SWTQuery setTextClient(Control value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTextClient(each, value);
		}
		return this;
	}

	public SWTQuery setTextLimit(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTextLimit(each, value);
		}
		return this;
	}

	public SWTQuery setThumb(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setThumb(each, value);
		}
		return this;
	}

	public SWTQuery setTitleBarBackground(Color value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTitleBarBackground(each, value);
		}
		return this;
	}

	public SWTQuery setTitleBarBorderColor(Color value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTitleBarBorderColor(each, value);
		}
		return this;
	}

	public SWTQuery setTitleBarForeground(Color value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTitleBarForeground(each, value);
		}
		return this;
	}

	public SWTQuery setTitleBarGradientBackground(Color value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTitleBarGradientBackground(each, value);
		}
		return this;
	}

	public SWTQuery setToolBarVerticalAlignment(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setToolBarVerticalAlignment(each, value);
		}
		return this;
	}

	public SWTQuery setToolTip(ToolTip value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setToolTip(each, value);
		}
		return this;
	}

	public SWTQuery setToolTipText(String value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setToolTipText(each, value);
		}
		return this;
	}

	public SWTQuery setTopCenter(Control value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTopCenter(each, value);
		}
		return this;
	}

	public SWTQuery setTopIndex(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTopIndex(each, value);
		}
		return this;
	}

	public SWTQuery setTopItem(TreeItem value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTopItem(each, value);
		}
		return this;
	}

	public SWTQuery setTopLeft(Control value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTopLeft(each, value);
		}
		return this;
	}

	public SWTQuery setTopMargin(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTopMargin(each, value);
		}
		return this;
	}

	public SWTQuery setTopPixel(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTopPixel(each, value);
		}
		return this;
	}

	public SWTQuery setTopRight(Control value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTopRight(each, value);
		}
		return this;
	}

	public SWTQuery setTouchEnabled(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTouchEnabled(each, value);
		}
		return this;
	}

	public SWTQuery setTransfer(Transfer[] value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setTransfer(each, value);
		}
		return this;
	}

	public SWTQuery setUnselectedCloseVisible(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setUnselectedCloseVisible(each, value);
		}
		return this;
	}

	public SWTQuery setUnselectedImageVisible(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setUnselectedImageVisible(each, value);
		}
		return this;
	}

	public SWTQuery setUrl(String value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setUrl(each, value);
		}
		return this;
	}

	public SWTQuery setVisible(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setVisible(each, value);
		}
		return this;
	}

	public SWTQuery setVisibleItemCount(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setVisibleItemCount(each, value);
		}
		return this;
	}

	public SWTQuery setWeights(int[] value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setWeights(each, value);
		}
		return this;
	}

	public SWTQuery setWidth(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setWidth(each, value);
		}
		return this;
	}

	public SWTQuery setWordWrap(Boolean value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setWordWrap(each, value);
		}
		return this;
	}

	public SWTQuery setWrapIndent(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setWrapIndent(each, value);
		}
		return this;
	}

	public SWTQuery setWrapIndices(int[] value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setWrapIndices(each, value);
		}
		return this;
	}

	public SWTQuery setYear(Integer value) {
		for (Widget each : items) {
			WidgetPropertySwitch.setYear(each, value);
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

	public SWTQuery schedule(final IWidgetFunction function) {
		Display.getCurrent().asyncExec(new Runnable() {
			@Override
			public void run() {
				SWTQuery.this.each(function);
			}
		});
		return this;
	}

}
