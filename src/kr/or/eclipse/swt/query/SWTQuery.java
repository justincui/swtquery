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

import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.*;
import org.eclipse.swt.graphics.GC;

import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.swt.widgets.TableItem;
import java.lang.Boolean;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.graphics.Font;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.custom.CTabFolderRenderer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import java.lang.Object;
import java.util.List;
import java.lang.Integer;
import org.eclipse.swt.dnd.DND;
import kr.or.eclipse.swt.query.internal.grammar.Selector;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;
import kr.or.eclipse.swt.query.util.WidgetPropertySwitch;
import java.util.Map;
import kr.or.eclipse.swt.query.filter.IWidgetFilter;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.graphics.Color;
import java.lang.Character;
import org.eclipse.swt.widgets.ToolTip;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.widgets.Caret;
import org.eclipse.swt.dnd.DropTargetEffect;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.swt.accessibility.Accessible;
import org.eclipse.ui.forms.widgets.Form;
import java.util.ArrayList;
import org.eclipse.ui.forms.HyperlinkSettings;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.IMessage;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.dnd.DropTarget;
import java.lang.String;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.ui.forms.IMessageManager;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.opengl.GLData;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.dnd.DragSourceEffect;
import org.eclipse.swt.widgets.IME;
import kr.or.eclipse.swt.query.internal.grammar.SelectorInterpreter;
import kr.or.eclipse.swt.query.internal.ChildrenSwitch;
import kr.or.eclipse.swt.query.internal.UniqueList;
import kr.or.eclipse.swt.query.internal.ParentSwitch;
import org.eclipse.swt.widgets.MenuItem;


public final class SWTQuery {
	private static int CURRENT_DEBUG_COLOR = 0;
	
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
		ArrayList<Widget> parents = new UniqueList<Widget>();
		for (Widget each : this.items) {
			Widget eachParent = ParentSwitch.INSTANCE.doSwitch(each);
			if (eachParent != null) {
				parents.add(eachParent);
			}
		}
		return new SWTQuery(parents);
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
	
	public SWTQuery schedule(final IWidgetFunction function) {
		Display.getCurrent().asyncExec(new Runnable() {
			@Override
			public void run() {
				SWTQuery.this.each(function);
			}
		});
		return this;
	}
	
	public SWTQuery addProperties(Map<String, Object> wClass) {
		for (String each : wClass.keySet()) {
			for (Widget item : items) {
				WidgetPropertySwitch.setProperty(item, each, wClass.get(each));
			}
		}
		return this;
	}

	/**
	 * Sets a Visible property.
	 * 
	 * @param value a Visible value to set.
	 *
	 * @see Caret
	 * @see Shell
	 * @see Control
	 * @see Menu
	 * @see ScrollBar
	 * @see ToolTip	 
	 */
	public SWTQuery setVisible(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setVisible(each, value);
		}
		return this;
	}

	/**
	 *Inverts Visible property.
	 *
	 * @see Caret
	 * @see Shell
	 * @see Control
	 * @see Menu
	 * @see ScrollBar
	 * @see ToolTip	 
	 */
	public SWTQuery toggleVisible(){
		for(Widget each : items){
			Boolean old = WidgetPropertySwitch.getVisible(each);
			WidgetPropertySwitch.setVisible(each, !old);
		}
		return this;
	}	

	/**
	 * Sets a HorizontalPixel property.
	 * 
	 * @param value a HorizontalPixel value to set.
	 *
	 * @see StyledText	 
	 */
	public SWTQuery setHorizontalPixel(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setHorizontalPixel(each, value);
		}
		return this;
	}	

	/**
	 * Sets a MenuBar property.
	 * 
	 * @param value a MenuBar value to set.
	 *
	 * @see Decorations	 
	 */
	public SWTQuery setMenuBar(Menu value){
		for(Widget each : items){
			WidgetPropertySwitch.setMenuBar(each, value);
		}
		return this;
	}	

	/**
	 * Sets a ToolTipText property.
	 * 
	 * @param value a ToolTipText value to set.
	 *
	 * @see Hyperlink
	 * @see CLabel
	 * @see Control
	 * @see CTabItem
	 * @see TabItem
	 * @see TableColumn
	 * @see ToolItem
	 * @see TrayItem
	 * @see TreeColumn	 
	 */
	public SWTQuery setToolTipText(String value){
		for(Widget each : items){
			WidgetPropertySwitch.setToolTipText(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Busy property.
	 * 
	 * @param value a Busy value to set.
	 *
	 * @see Form	 
	 */
	public SWTQuery setBusy(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setBusy(each, value);
		}
		return this;
	}

	/**
	 *Inverts Busy property.
	 *
	 * @see Form	 
	 */
	public SWTQuery toggleBusy(){
		for(Widget each : items){
			Boolean old = WidgetPropertySwitch.getBusy(each);
			WidgetPropertySwitch.setBusy(each, !old);
		}
		return this;
	}	

	/**
	 * Sets a Increment property.
	 * 
	 * @param value a Increment value to set.
	 *
	 * @see Spinner
	 * @see Scale
	 * @see Slider
	 * @see ScrollBar	 
	 */
	public SWTQuery setIncrement(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setIncrement(each, value);
		}
		return this;
	}	

	/**
	 * Sets a LabelProvider property.
	 * 
	 * @param value a LabelProvider value to set.
	 *
	 * @see FilteredList	 
	 */
	public SWTQuery setLabelProvider(ILabelProvider value){
		for(Widget each : items){
			WidgetPropertySwitch.setLabelProvider(each, value);
		}
		return this;
	}	

	/**
	 * Sets a CaretOffset property.
	 * 
	 * @param value a CaretOffset value to set.
	 *
	 * @see StyledText	 
	 */
	public SWTQuery setCaretOffset(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setCaretOffset(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Underlined property.
	 * 
	 * @param value a Underlined value to set.
	 *
	 * @see Hyperlink	 
	 */
	public SWTQuery setUnderlined(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setUnderlined(each, value);
		}
		return this;
	}

	/**
	 *Inverts Underlined property.
	 *
	 * @see Hyperlink	 
	 */
	public SWTQuery toggleUnderlined(){
		for(Widget each : items){
			Boolean old = WidgetPropertySwitch.getUnderlined(each);
			WidgetPropertySwitch.setUnderlined(each, !old);
		}
		return this;
	}	

	/**
	 * Sets a Filter property.
	 * 
	 * @param value a Filter value to set.
	 *
	 * @see FilteredList	 
	 */
	public SWTQuery setFilter(String value){
		for(Widget each : items){
			WidgetPropertySwitch.setFilter(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Layout property.
	 * 
	 * @param value a Layout value to set.
	 *
	 * @see Composite	 
	 */
	public SWTQuery setLayout(Layout value){
		for(Widget each : items){
			WidgetPropertySwitch.setLayout(each, value);
		}
		return this;
	}	

	/**
	 * Sets a BackgroundImageTiled property.
	 * 
	 * @param value a BackgroundImageTiled value to set.
	 *
	 * @see Form	 
	 */
	public SWTQuery setBackgroundImageTiled(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setBackgroundImageTiled(each, value);
		}
		return this;
	}

	/**
	 *Inverts BackgroundImageTiled property.
	 *
	 * @see Form	 
	 */
	public SWTQuery toggleBackgroundImageTiled(){
		for(Widget each : items){
			Boolean old = WidgetPropertySwitch.getBackgroundImageTiled(each);
			WidgetPropertySwitch.setBackgroundImageTiled(each, !old);
		}
		return this;
	}	

	/**
	 * Sets a IME property.
	 * 
	 * @param value a IME value to set.
	 *
	 * @see Canvas	 
	 */
	public SWTQuery setIME(IME value){
		for(Widget each : items){
			WidgetPropertySwitch.setIME(each, value);
		}
		return this;
	}	

	/**
	 * Sets a TextChars property.
	 * 
	 * @param value a TextChars value to set.
	 *
	 * @see Text	 
	 */
	public SWTQuery setTextChars(char[] value){
		for(Widget each : items){
			WidgetPropertySwitch.setTextChars(each, value);
		}
		return this;
	}	

	/**
	 * Sets a TopMargin property.
	 * 
	 * @param value a TopMargin value to set.
	 *
	 * @see CLabel
	 * @see StyledText	 
	 */
	public SWTQuery setTopMargin(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setTopMargin(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Weights property.
	 * 
	 * @param value a Weights value to set.
	 *
	 * @see SashForm	 
	 */
	public SWTQuery setWeights(int[] value){
		for(Widget each : items){
			WidgetPropertySwitch.setWeights(each, value);
		}
		return this;
	}	

	/**
	 * Sets a LayoutData property.
	 * 
	 * @param value a LayoutData value to set.
	 *
	 * @see Control	 
	 */
	public SWTQuery setLayoutData(Object value){
		for(Widget each : items){
			WidgetPropertySwitch.setLayoutData(each, value);
		}
		return this;
	}	

	/**
	 * Sets a StyleRanges property.
	 * 
	 * @param value a StyleRanges value to set.
	 *
	 * @see StyledText	 
	 */
	public SWTQuery setStyleRanges(StyleRange[] value){
		for(Widget each : items){
			WidgetPropertySwitch.setStyleRanges(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Control property.
	 * 
	 * @param value a Control value to set.
	 *
	 * @see CTabItem
	 * @see CoolItem
	 * @see ExpandItem
	 * @see TabItem
	 * @see ToolItem	 
	 */
	public SWTQuery setControl(Control value){
		for(Widget each : items){
			WidgetPropertySwitch.setControl(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Renderer property.
	 * 
	 * @param value a Renderer value to set.
	 *
	 * @see CTabFolder	 
	 */
	public SWTQuery setRenderer(CTabFolderRenderer value){
		for(Widget each : items){
			WidgetPropertySwitch.setRenderer(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Image property.
	 * 
	 * @param value a Image value to set.
	 *
	 * @see Caret
	 * @see ImageHyperlink
	 * @see ScrolledForm
	 * @see CLabel
	 * @see Decorations
	 * @see Form
	 * @see Button
	 * @see Label
	 * @see TableItem
	 * @see TreeItem
	 * @see Item	 
	 */
	public SWTQuery setImage(Image value){
		for(Widget each : items){
			WidgetPropertySwitch.setImage(each, value);
		}
		return this;
	}	

	/**
	 * Sets a RightMinimumSize property.
	 * 
	 * @param value a RightMinimumSize value to set.
	 *
	 * @see CBanner	 
	 */
	public SWTQuery setRightMinimumSize(Point value){
		for(Widget each : items){
			WidgetPropertySwitch.setRightMinimumSize(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Expanded property.
	 * 
	 * @param value a Expanded value to set.
	 *
	 * @see ToggleHyperlink
	 * @see ExpandableComposite	 
	 */
	public SWTQuery setExpanded(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setExpanded(each, value);
		}
		return this;
	}

	/**
	 *Inverts Expanded property.
	 *
	 * @see ToggleHyperlink
	 * @see ExpandableComposite	 
	 */
	public SWTQuery toggleExpanded(){
		for(Widget each : items){
			Boolean old = WidgetPropertySwitch.getExpanded(each);
			WidgetPropertySwitch.setExpanded(each, !old);
		}
		return this;
	}	

	/**
	 * Sets a TitleBarForeground property.
	 * 
	 * @param value a TitleBarForeground value to set.
	 *
	 * @see ExpandableComposite	 
	 */
	public SWTQuery setTitleBarForeground(Color value){
		for(Widget each : items){
			WidgetPropertySwitch.setTitleBarForeground(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Client property.
	 * 
	 * @param value a Client value to set.
	 *
	 * @see ExpandableComposite	 
	 */
	public SWTQuery setClient(Control value){
		for(Widget each : items){
			WidgetPropertySwitch.setClient(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Right property.
	 * 
	 * @param value a Right value to set.
	 *
	 * @see CBanner	 
	 */
	public SWTQuery setRight(Control value){
		for(Widget each : items){
			WidgetPropertySwitch.setRight(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Orientation property.
	 * 
	 * @param value a Orientation value to set.
	 *
	 * @see StyledText
	 * @see SashForm
	 * @see Combo
	 * @see Text
	 * @see Control
	 * @see Menu	 
	 */
	public SWTQuery setOrientation(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setOrientation(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Hours property.
	 * 
	 * @param value a Hours value to set.
	 *
	 * @see DateTime	 
	 */
	public SWTQuery setHours(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setHours(each, value);
		}
		return this;
	}	

	/**
	 * Sets a HoverImage property.
	 * 
	 * @param value a HoverImage value to set.
	 *
	 * @see ImageHyperlink	 
	 */
	public SWTQuery setHoverImage(Image value){
		for(Widget each : items){
			WidgetPropertySwitch.setHoverImage(each, value);
		}
		return this;
	}	

	/**
	 * Sets a BottomMargin property.
	 * 
	 * @param value a BottomMargin value to set.
	 *
	 * @see CLabel
	 * @see StyledText	 
	 */
	public SWTQuery setBottomMargin(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setBottomMargin(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Indent property.
	 * 
	 * @param value a Indent value to set.
	 *
	 * @see StyledText	 
	 */
	public SWTQuery setIndent(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setIndent(each, value);
		}
		return this;
	}	

	/**
	 * Sets a TopIndex property.
	 * 
	 * @param value a TopIndex value to set.
	 *
	 * @see StyledText
	 * @see Table
	 * @see List
	 * @see Text	 
	 */
	public SWTQuery setTopIndex(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setTopIndex(each, value);
		}
		return this;
	}	

	/**
	 * Sets a ImeInputMode property.
	 * 
	 * @param value a ImeInputMode value to set.
	 *
	 * @see Shell	 
	 */
	public SWTQuery setImeInputMode(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setImeInputMode(each, value);
		}
		return this;
	}	

	/**
	 * Sets a RightWidth property.
	 * 
	 * @param value a RightWidth value to set.
	 *
	 * @see CBanner	 
	 */
	public SWTQuery setRightWidth(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setRightWidth(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Foreground property.
	 * 
	 * @param value a Foreground value to set.
	 *
	 * @see StyledText
	 * @see TableCursor
	 * @see Control
	 * @see TableItem
	 * @see TreeItem	 
	 */
	public SWTQuery setForeground(Color value){
		for(Widget each : items){
			WidgetPropertySwitch.setForeground(each, value);
		}
		return this;
	}	

	/**
	 * Sets a MaximizedControl property.
	 * 
	 * @param value a MaximizedControl value to set.
	 *
	 * @see SashForm	 
	 */
	public SWTQuery setMaximizedControl(Control value){
		for(Widget each : items){
			WidgetPropertySwitch.setMaximizedControl(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Month property.
	 * 
	 * @param value a Month value to set.
	 *
	 * @see DateTime	 
	 */
	public SWTQuery setMonth(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setMonth(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Menu property.
	 * 
	 * @param value a Menu value to set.
	 *
	 * @see CCombo
	 * @see Control
	 * @see MenuItem	 
	 */
	public SWTQuery setMenu(Menu value){
		for(Widget each : items){
			WidgetPropertySwitch.setMenu(each, value);
		}
		return this;
	}	

	/**
	 * Sets a LineSpacing property.
	 * 
	 * @param value a LineSpacing value to set.
	 *
	 * @see StyledText	 
	 */
	public SWTQuery setLineSpacing(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setLineSpacing(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Href property.
	 * 
	 * @param value a Href value to set.
	 *
	 * @see AbstractHyperlink	 
	 */
	public SWTQuery setHref(Object value){
		for(Widget each : items){
			WidgetPropertySwitch.setHref(each, value);
		}
		return this;
	}	

	/**
	 * Sets a HoverDecorationColor property.
	 * 
	 * @param value a HoverDecorationColor value to set.
	 *
	 * @see ToggleHyperlink	 
	 */
	public SWTQuery setHoverDecorationColor(Color value){
		for(Widget each : items){
			WidgetPropertySwitch.setHoverDecorationColor(each, value);
		}
		return this;
	}	

	/**
	 * Sets a TextLimit property.
	 * 
	 * @param value a TextLimit value to set.
	 *
	 * @see StyledText
	 * @see CCombo
	 * @see Combo
	 * @see Spinner
	 * @see Text	 
	 */
	public SWTQuery setTextLimit(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setTextLimit(each, value);
		}
		return this;
	}	

	/**
	 * Sets a ItemCount property.
	 * 
	 * @param value a ItemCount value to set.
	 *
	 * @see Table
	 * @see Tree
	 * @see TreeItem	 
	 */
	public SWTQuery setItemCount(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setItemCount(each, value);
		}
		return this;
	}	

	/**
	 * Sets a MinWidth property.
	 * 
	 * @param value a MinWidth value to set.
	 *
	 * @see ScrolledComposite	 
	 */
	public SWTQuery setMinWidth(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setMinWidth(each, value);
		}
		return this;
	}	

	/**
	 * Sets a TitleBarBorderColor property.
	 * 
	 * @param value a TitleBarBorderColor value to set.
	 *
	 * @see Section	 
	 */
	public SWTQuery setTitleBarBorderColor(Color value){
		for(Widget each : items){
			WidgetPropertySwitch.setTitleBarBorderColor(each, value);
		}
		return this;
	}	

	/**
	 * Sets a WrapIndent property.
	 * 
	 * @param value a WrapIndent value to set.
	 *
	 * @see StyledText	 
	 */
	public SWTQuery setWrapIndent(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setWrapIndent(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Enabled property.
	 * 
	 * @param value a Enabled value to set.
	 *
	 * @see Shell
	 * @see Control
	 * @see MenuItem
	 * @see ToolItem
	 * @see Menu
	 * @see ScrollBar	 
	 */
	public SWTQuery setEnabled(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setEnabled(each, value);
		}
		return this;
	}

	/**
	 *Inverts Enabled property.
	 *
	 * @see Shell
	 * @see Control
	 * @see MenuItem
	 * @see ToolItem
	 * @see Menu
	 * @see ScrollBar	 
	 */
	public SWTQuery toggleEnabled(){
		for(Widget each : items){
			Boolean old = WidgetPropertySwitch.getEnabled(each);
			WidgetPropertySwitch.setEnabled(each, !old);
		}
		return this;
	}	

	/**
	 * Sets a Cursor property.
	 * 
	 * @param value a Cursor value to set.
	 *
	 * @see Control	 
	 */
	public SWTQuery setCursor(Cursor value){
		for(Widget each : items){
			WidgetPropertySwitch.setCursor(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Year property.
	 * 
	 * @param value a Year value to set.
	 *
	 * @see DateTime	 
	 */
	public SWTQuery setYear(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setYear(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Region property.
	 * 
	 * @param value a Region value to set.
	 *
	 * @see Shell
	 * @see Control	 
	 */
	public SWTQuery setRegion(Region value){
		for(Widget each : items){
			WidgetPropertySwitch.setRegion(each, value);
		}
		return this;
	}	

	/**
	 * Sets a ToolBarVerticalAlignment property.
	 * 
	 * @param value a ToolBarVerticalAlignment value to set.
	 *
	 * @see Form	 
	 */
	public SWTQuery setToolBarVerticalAlignment(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setToolBarVerticalAlignment(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Accelerator property.
	 * 
	 * @param value a Accelerator value to set.
	 *
	 * @see MenuItem	 
	 */
	public SWTQuery setAccelerator(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setAccelerator(each, value);
		}
		return this;
	}	

	/**
	 * Sets a PageIncrement property.
	 * 
	 * @param value a PageIncrement value to set.
	 *
	 * @see Spinner
	 * @see Scale
	 * @see Slider
	 * @see ScrollBar	 
	 */
	public SWTQuery setPageIncrement(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setPageIncrement(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Description property.
	 * 
	 * @param value a Description value to set.
	 *
	 * @see Section	 
	 */
	public SWTQuery setDescription(String value){
		for(Widget each : items){
			WidgetPropertySwitch.setDescription(each, value);
		}
		return this;
	}	

	/**
	 * Sets a SelectionForeground property.
	 * 
	 * @param value a SelectionForeground value to set.
	 *
	 * @see StyledText
	 * @see CTabFolder	 
	 */
	public SWTQuery setSelectionForeground(Color value){
		for(Widget each : items){
			WidgetPropertySwitch.setSelectionForeground(each, value);
		}
		return this;
	}	

	/**
	 * Sets a BlockSelectionBounds property.
	 * 
	 * @param value a BlockSelectionBounds value to set.
	 *
	 * @see StyledText	 
	 */
	public SWTQuery setBlockSelectionBounds(Rectangle value){
		for(Widget each : items){
			WidgetPropertySwitch.setBlockSelectionBounds(each, value);
		}
		return this;
	}	

	/**
	 * Sets a MinHeight property.
	 * 
	 * @param value a MinHeight value to set.
	 *
	 * @see ScrolledComposite	 
	 */
	public SWTQuery setMinHeight(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setMinHeight(each, value);
		}
		return this;
	}	

	/**
	 * Sets a MinimumSize property.
	 * 
	 * @param value a MinimumSize value to set.
	 *
	 * @see Shell
	 * @see CoolItem	 
	 */
	public SWTQuery setMinimumSize(Point value){
		for(Widget each : items){
			WidgetPropertySwitch.setMinimumSize(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Tabs property.
	 * 
	 * @param value a Tabs value to set.
	 *
	 * @see StyledText
	 * @see Text	 
	 */
	public SWTQuery setTabs(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setTabs(each, value);
		}
		return this;
	}	

	/**
	 * Sets a State property.
	 * 
	 * @param value a State value to set.
	 *
	 * @see ProgressBar	 
	 */
	public SWTQuery setState(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setState(each, value);
		}
		return this;
	}	

	/**
	 * Sets a TopItem property.
	 * 
	 * @param value a TopItem value to set.
	 *
	 * @see Tree	 
	 */
	public SWTQuery setTopItem(TreeItem value){
		for(Widget each : items){
			WidgetPropertySwitch.setTopItem(each, value);
		}
		return this;
	}	

	/**
	 * Sets a TopCenter property.
	 * 
	 * @param value a TopCenter value to set.
	 *
	 * @see ViewForm	 
	 */
	public SWTQuery setTopCenter(Control value){
		for(Widget each : items){
			WidgetPropertySwitch.setTopCenter(each, value);
		}
		return this;
	}	

	/**
	 * Sets a TabStops property.
	 * 
	 * @param value a TabStops value to set.
	 *
	 * @see StyledText	 
	 */
	public SWTQuery setTabStops(int[] value){
		for(Widget each : items){
			WidgetPropertySwitch.setTabStops(each, value);
		}
		return this;
	}	

	/**
	 * Sets a MinimumCharacters property.
	 * 
	 * @param value a MinimumCharacters value to set.
	 *
	 * @see CTabFolder	 
	 */
	public SWTQuery setMinimumCharacters(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setMinimumCharacters(each, value);
		}
		return this;
	}	

	/**
	 * Sets a HorizontalIndex property.
	 * 
	 * @param value a HorizontalIndex value to set.
	 *
	 * @see StyledText	 
	 */
	public SWTQuery setHorizontalIndex(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setHorizontalIndex(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Images property.
	 * 
	 * @param value a Images value to set.
	 *
	 * @see Decorations	 
	 */
	public SWTQuery setImages(Image[] value){
		for(Widget each : items){
			WidgetPropertySwitch.setImages(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Origin property.
	 * 
	 * @param value a Origin value to set.
	 *
	 * @see ScrolledComposite	 
	 */
	public SWTQuery setOrigin(Point value){
		for(Widget each : items){
			WidgetPropertySwitch.setOrigin(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Data property.
	 * 
	 * @param value a Data value to set.
	 *
	 * @see Widget	 
	 */
	public SWTQuery setData(Object value){
		for(Widget each : items){
			WidgetPropertySwitch.setData(each, value);
		}
		return this;
	}	

	/**
	 * Sets a EchoChar property.
	 * 
	 * @param value a EchoChar value to set.
	 *
	 * @see Text	 
	 */
	public SWTQuery setEchoChar(Character value){
		for(Widget each : items){
			WidgetPropertySwitch.setEchoChar(each, value);
		}
		return this;
	}	

	/**
	 * Sets a HeadClient property.
	 * 
	 * @param value a HeadClient value to set.
	 *
	 * @see Form	 
	 */
	public SWTQuery setHeadClient(Control value){
		for(Widget each : items){
			WidgetPropertySwitch.setHeadClient(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Url property.
	 * 
	 * @param value a Url value to set.
	 *
	 * @see Browser	 
	 */
	public SWTQuery setUrl(String value){
		for(Widget each : items){
			WidgetPropertySwitch.setUrl(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Seconds property.
	 * 
	 * @param value a Seconds value to set.
	 *
	 * @see DateTime	 
	 */
	public SWTQuery setSeconds(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setSeconds(each, value);
		}
		return this;
	}	

	/**
	 * Sets a SortDirection property.
	 * 
	 * @param value a SortDirection value to set.
	 *
	 * @see Table
	 * @see Tree	 
	 */
	public SWTQuery setSortDirection(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setSortDirection(each, value);
		}
		return this;
	}	

	/**
	 * Sets a VisibleItemCount property.
	 * 
	 * @param value a VisibleItemCount value to set.
	 *
	 * @see CCombo
	 * @see Combo	 
	 */
	public SWTQuery setVisibleItemCount(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setVisibleItemCount(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Thumb property.
	 * 
	 * @param value a Thumb value to set.
	 *
	 * @see Slider
	 * @see ScrollBar	 
	 */
	public SWTQuery setThumb(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setThumb(each, value);
		}
		return this;
	}	

	/**
	 * Sets a DecorationColor property.
	 * 
	 * @param value a DecorationColor value to set.
	 *
	 * @see ToggleHyperlink	 
	 */
	public SWTQuery setDecorationColor(Color value){
		for(Widget each : items){
			WidgetPropertySwitch.setDecorationColor(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Bounds property.
	 * 
	 * @param value a Bounds value to set.
	 *
	 * @see Caret
	 * @see Shell
	 * @see Decorations
	 * @see Control	 
	 */
	public SWTQuery setBounds(Rectangle value){
		for(Widget each : items){
			WidgetPropertySwitch.setBounds(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Digits property.
	 * 
	 * @param value a Digits value to set.
	 *
	 * @see Spinner	 
	 */
	public SWTQuery setDigits(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setDigits(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Left property.
	 * 
	 * @param value a Left value to set.
	 *
	 * @see CBanner	 
	 */
	public SWTQuery setLeft(Control value){
		for(Widget each : items){
			WidgetPropertySwitch.setLeft(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Bottom property.
	 * 
	 * @param value a Bottom value to set.
	 *
	 * @see CBanner	 
	 */
	public SWTQuery setBottom(Control value){
		for(Widget each : items){
			WidgetPropertySwitch.setBottom(each, value);
		}
		return this;
	}	

	/**
	 * Sets a BackgroundImage property.
	 * 
	 * @param value a BackgroundImage value to set.
	 *
	 * @see ScrolledForm
	 * @see Form
	 * @see Control	 
	 */
	public SWTQuery setBackgroundImage(Image value){
		for(Widget each : items){
			WidgetPropertySwitch.setBackgroundImage(each, value);
		}
		return this;
	}	

	/**
	 * Sets a TabPosition property.
	 * 
	 * @param value a TabPosition value to set.
	 *
	 * @see CTabFolder	 
	 */
	public SWTQuery setTabPosition(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setTabPosition(each, value);
		}
		return this;
	}	

	/**
	 * Sets a TitleBarBackground property.
	 * 
	 * @param value a TitleBarBackground value to set.
	 *
	 * @see Section	 
	 */
	public SWTQuery setTitleBarBackground(Color value){
		for(Widget each : items){
			WidgetPropertySwitch.setTitleBarBackground(each, value);
		}
		return this;
	}	

	/**
	 * Sets a TabHeight property.
	 * 
	 * @param value a TabHeight value to set.
	 *
	 * @see CTabFolder	 
	 */
	public SWTQuery setTabHeight(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setTabHeight(each, value);
		}
		return this;
	}	

	/**
	 * Sets a DelayedReflow property.
	 * 
	 * @param value a DelayedReflow value to set.
	 *
	 * @see SharedScrolledComposite	 
	 */
	public SWTQuery setDelayedReflow(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setDelayedReflow(each, value);
		}
		return this;
	}

	/**
	 *Inverts DelayedReflow property.
	 *
	 * @see SharedScrolledComposite	 
	 */
	public SWTQuery toggleDelayedReflow(){
		for(Widget each : items){
			Boolean old = WidgetPropertySwitch.getDelayedReflow(each);
			WidgetPropertySwitch.setDelayedReflow(each, !old);
		}
		return this;
	}	

	/**
	 * Sets a Alpha property.
	 * 
	 * @param value a Alpha value to set.
	 *
	 * @see Shell	 
	 */
	public SWTQuery setAlpha(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setAlpha(each, value);
		}
		return this;
	}	

	/**
	 * Sets a PreferredSize property.
	 * 
	 * @param value a PreferredSize value to set.
	 *
	 * @see CoolItem	 
	 */
	public SWTQuery setPreferredSize(Point value){
		for(Widget each : items){
			WidgetPropertySwitch.setPreferredSize(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Background property.
	 * 
	 * @param value a Background value to set.
	 *
	 * @see StyledText
	 * @see TableCursor
	 * @see Control
	 * @see TableItem
	 * @see TreeItem	 
	 */
	public SWTQuery setBackground(Color value){
		for(Widget each : items){
			WidgetPropertySwitch.setBackground(each, value);
		}
		return this;
	}	

	/**
	 * Sets a SashWidth property.
	 * 
	 * @param value a SashWidth value to set.
	 *
	 * @see SashForm	 
	 */
	public SWTQuery setSashWidth(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setSashWidth(each, value);
		}
		return this;
	}	

	/**
	 * Sets a WhitespaceNormalized property.
	 * 
	 * @param value a WhitespaceNormalized value to set.
	 *
	 * @see FormText	 
	 */
	public SWTQuery setWhitespaceNormalized(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setWhitespaceNormalized(each, value);
		}
		return this;
	}

	/**
	 *Inverts WhitespaceNormalized property.
	 *
	 * @see FormText	 
	 */
	public SWTQuery toggleWhitespaceNormalized(){
		for(Widget each : items){
			Boolean old = WidgetPropertySwitch.getWhitespaceNormalized(each);
			WidgetPropertySwitch.setWhitespaceNormalized(each, !old);
		}
		return this;
	}	

	/**
	 * Sets a Font property.
	 * 
	 * @param value a Font value to set.
	 *
	 * @see Caret
	 * @see Control
	 * @see CTabItem
	 * @see TableItem
	 * @see TreeItem	 
	 */
	public SWTQuery setFont(Font value){
		for(Widget each : items){
			WidgetPropertySwitch.setFont(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Rectangles property.
	 * 
	 * @param value a Rectangles value to set.
	 *
	 * @see Tracker	 
	 */
	public SWTQuery setRectangles(Rectangle[] value){
		for(Widget each : items){
			WidgetPropertySwitch.setRectangles(each, value);
		}
		return this;
	}	

	/**
	 * Sets a DefaultItem property.
	 * 
	 * @param value a DefaultItem value to set.
	 *
	 * @see Menu	 
	 */
	public SWTQuery setDefaultItem(MenuItem value){
		for(Widget each : items){
			WidgetPropertySwitch.setDefaultItem(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Message property.
	 * 
	 * @param value a Message value to set.
	 *
	 * @see Form
	 * @see Text
	 * @see ToolTip	 
	 */
	public SWTQuery setMessage(String value){
		for(Widget each : items){
			WidgetPropertySwitch.setMessage(each, value);
		}
		return this;
	}	

	/**
	 * Sets a LeftMargin property.
	 * 
	 * @param value a LeftMargin value to set.
	 *
	 * @see CLabel
	 * @see StyledText	 
	 */
	public SWTQuery setLeftMargin(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setLeftMargin(each, value);
		}
		return this;
	}	

	/**
	 * Sets a DefaultButton property.
	 * 
	 * @param value a DefaultButton value to set.
	 *
	 * @see Decorations	 
	 */
	public SWTQuery setDefaultButton(Button value){
		for(Widget each : items){
			WidgetPropertySwitch.setDefaultButton(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Minimum property.
	 * 
	 * @param value a Minimum value to set.
	 *
	 * @see Spinner
	 * @see ProgressBar
	 * @see Scale
	 * @see Slider
	 * @see ScrollBar	 
	 */
	public SWTQuery setMinimum(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setMinimum(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Alignment property.
	 * 
	 * @param value a Alignment value to set.
	 *
	 * @see CLabel
	 * @see StyledText
	 * @see Button
	 * @see Label
	 * @see TableColumn
	 * @see TreeColumn	 
	 */
	public SWTQuery setAlignment(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setAlignment(each, value);
		}
		return this;
	}	

	/**
	 * Sets a TopLeft property.
	 * 
	 * @param value a TopLeft value to set.
	 *
	 * @see ViewForm	 
	 */
	public SWTQuery setTopLeft(Control value){
		for(Widget each : items){
			WidgetPropertySwitch.setTopLeft(each, value);
		}
		return this;
	}	

	/**
	 * Sets a SeparatorControl property.
	 * 
	 * @param value a SeparatorControl value to set.
	 *
	 * @see Section	 
	 */
	public SWTQuery setSeparatorControl(Control value){
		for(Widget each : items){
			WidgetPropertySwitch.setSeparatorControl(each, value);
		}
		return this;
	}	

	/**
	 * Sets a LayoutDeferred property.
	 * 
	 * @param value a LayoutDeferred value to set.
	 *
	 * @see Composite	 
	 */
	public SWTQuery setLayoutDeferred(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setLayoutDeferred(each, value);
		}
		return this;
	}

	/**
	 *Inverts LayoutDeferred property.
	 *
	 * @see Composite	 
	 */
	public SWTQuery toggleLayoutDeferred(){
		for(Widget each : items){
			Boolean old = WidgetPropertySwitch.getLayoutDeferred(each);
			WidgetPropertySwitch.setLayoutDeferred(each, !old);
		}
		return this;
	}	

	/**
	 * Sets a TextClient property.
	 * 
	 * @param value a TextClient value to set.
	 *
	 * @see ExpandableComposite	 
	 */
	public SWTQuery setTextClient(Control value){
		for(Widget each : items){
			WidgetPropertySwitch.setTextClient(each, value);
		}
		return this;
	}	

	/**
	 * Sets a ID property.
	 * 
	 * @param value a ID value to set.
	 *
	 * @see MenuItem	 
	 */
	public SWTQuery setID(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setID(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Width property.
	 * 
	 * @param value a Width value to set.
	 *
	 * @see TableColumn
	 * @see ToolItem
	 * @see TreeColumn	 
	 */
	public SWTQuery setWidth(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setWidth(each, value);
		}
		return this;
	}	

	/**
	 * Sets a ToolTip property.
	 * 
	 * @param value a ToolTip value to set.
	 *
	 * @see TrayItem	 
	 */
	public SWTQuery setToolTip(ToolTip value){
		for(Widget each : items){
			WidgetPropertySwitch.setToolTip(each, value);
		}
		return this;
	}	

	/**
	 * Sets a SeparatorVisible property.
	 * 
	 * @param value a SeparatorVisible value to set.
	 *
	 * @see Form	 
	 */
	public SWTQuery setSeparatorVisible(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setSeparatorVisible(each, value);
		}
		return this;
	}

	/**
	 *Inverts SeparatorVisible property.
	 *
	 * @see Form	 
	 */
	public SWTQuery toggleSeparatorVisible(){
		for(Widget each : items){
			Boolean old = WidgetPropertySwitch.getSeparatorVisible(each);
			WidgetPropertySwitch.setSeparatorVisible(each, !old);
		}
		return this;
	}	

	/**
	 * Sets a Size property.
	 * 
	 * @param value a Size value to set.
	 *
	 * @see Caret
	 * @see Shell
	 * @see Decorations
	 * @see Control
	 * @see CoolItem	 
	 */
	public SWTQuery setSize(Point value){
		for(Widget each : items){
			WidgetPropertySwitch.setSize(each, value);
		}
		return this;
	}	

	/**
	 * Sets a SelectionBackground property.
	 * 
	 * @param value a SelectionBackground value to set.
	 *
	 * @see StyledText
	 * @see CTabFolder	 
	 */
	public SWTQuery setSelectionBackground(Color value){
		for(Widget each : items){
			WidgetPropertySwitch.setSelectionBackground(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Maximum property.
	 * 
	 * @param value a Maximum value to set.
	 *
	 * @see Spinner
	 * @see ProgressBar
	 * @see Scale
	 * @see Slider
	 * @see ScrollBar	 
	 */
	public SWTQuery setMaximum(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setMaximum(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Height property.
	 * 
	 * @param value a Height value to set.
	 *
	 * @see ExpandItem	 
	 */
	public SWTQuery setHeight(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setHeight(each, value);
		}
		return this;
	}	

	/**
	 * Sets a TopRight property.
	 * 
	 * @param value a TopRight value to set.
	 *
	 * @see CTabFolder
	 * @see ViewForm	 
	 */
	public SWTQuery setTopRight(Control value){
		for(Widget each : items){
			WidgetPropertySwitch.setTopRight(each, value);
		}
		return this;
	}	

	/**
	 * Sets a HyperlinkSettings property.
	 * 
	 * @param value a HyperlinkSettings value to set.
	 *
	 * @see FormText	 
	 */
	public SWTQuery setHyperlinkSettings(HyperlinkSettings value){
		for(Widget each : items){
			WidgetPropertySwitch.setHyperlinkSettings(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Transfer property.
	 * 
	 * @param value a Transfer value to set.
	 *
	 * @see DragSource
	 * @see DropTarget	 
	 */
	public SWTQuery setTransfer(Transfer[] value){
		for(Widget each : items){
			WidgetPropertySwitch.setTransfer(each, value);
		}
		return this;
	}	

	/**
	 * Sets a ActiveImage property.
	 * 
	 * @param value a ActiveImage value to set.
	 *
	 * @see ImageHyperlink	 
	 */
	public SWTQuery setActiveImage(Image value){
		for(Widget each : items){
			WidgetPropertySwitch.setActiveImage(each, value);
		}
		return this;
	}	

	/**
	 * Sets a FormText property.
	 * 
	 * @param value a FormText value to set.
	 *
	 * @see ScrolledFormText	 
	 */
	public SWTQuery setFormText(FormText value){
		for(Widget each : items){
			WidgetPropertySwitch.setFormText(each, value);
		}
		return this;
	}	

	/**
	 * Sets a DropTargetEffect property.
	 * 
	 * @param value a DropTargetEffect value to set.
	 *
	 * @see DropTarget	 
	 */
	public SWTQuery setDropTargetEffect(DropTargetEffect value){
		for(Widget each : items){
			WidgetPropertySwitch.setDropTargetEffect(each, value);
		}
		return this;
	}	

	/**
	 * Sets a TopPixel property.
	 * 
	 * @param value a TopPixel value to set.
	 *
	 * @see StyledText	 
	 */
	public SWTQuery setTopPixel(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setTopPixel(each, value);
		}
		return this;
	}	

	/**
	 * Sets a MarginColor property.
	 * 
	 * @param value a MarginColor value to set.
	 *
	 * @see StyledText	 
	 */
	public SWTQuery setMarginColor(Color value){
		for(Widget each : items){
			WidgetPropertySwitch.setMarginColor(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Caret property.
	 * 
	 * @param value a Caret value to set.
	 *
	 * @see Canvas	 
	 */
	public SWTQuery setCaret(Caret value){
		for(Widget each : items){
			WidgetPropertySwitch.setCaret(each, value);
		}
		return this;
	}	

	/**
	 * Sets a DragSourceEffect property.
	 * 
	 * @param value a DragSourceEffect value to set.
	 *
	 * @see DragSource	 
	 */
	public SWTQuery setDragSourceEffect(DragSourceEffect value){
		for(Widget each : items){
			WidgetPropertySwitch.setDragSourceEffect(each, value);
		}
		return this;
	}	

	/**
	 * Sets a ColumnOrder property.
	 * 
	 * @param value a ColumnOrder value to set.
	 *
	 * @see Table
	 * @see Tree	 
	 */
	public SWTQuery setColumnOrder(int[] value){
		for(Widget each : items){
			WidgetPropertySwitch.setColumnOrder(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Day property.
	 * 
	 * @param value a Day value to set.
	 *
	 * @see DateTime	 
	 */
	public SWTQuery setDay(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setDay(each, value);
		}
		return this;
	}	

	/**
	 * Sets a WrapIndices property.
	 * 
	 * @param value a WrapIndices value to set.
	 *
	 * @see CoolBar	 
	 */
	public SWTQuery setWrapIndices(int[] value){
		for(Widget each : items){
			WidgetPropertySwitch.setWrapIndices(each, value);
		}
		return this;
	}	

	/**
	 * Sets a CompositionOffset property.
	 * 
	 * @param value a CompositionOffset value to set.
	 *
	 * @see IME	 
	 */
	public SWTQuery setCompositionOffset(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setCompositionOffset(each, value);
		}
		return this;
	}	

	/**
	 * Sets a TitleBarGradientBackground property.
	 * 
	 * @param value a TitleBarGradientBackground value to set.
	 *
	 * @see Section	 
	 */
	public SWTQuery setTitleBarGradientBackground(Color value){
		for(Widget each : items){
			WidgetPropertySwitch.setTitleBarGradientBackground(each, value);
		}
		return this;
	}	

	/**
	 * Sets a BackgroundMode property.
	 * 
	 * @param value a BackgroundMode value to set.
	 *
	 * @see Composite	 
	 */
	public SWTQuery setBackgroundMode(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setBackgroundMode(each, value);
		}
		return this;
	}	

	/**
	 * Sets a RightMargin property.
	 * 
	 * @param value a RightMargin value to set.
	 *
	 * @see CLabel
	 * @see StyledText	 
	 */
	public SWTQuery setRightMargin(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setRightMargin(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Location property.
	 * 
	 * @param value a Location value to set.
	 *
	 * @see Caret
	 * @see Shell
	 * @see Decorations
	 * @see Control	 
	 */
	public SWTQuery setLocation(Point value){
		for(Widget each : items){
			WidgetPropertySwitch.setLocation(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Spacing property.
	 * 
	 * @param value a Spacing value to set.
	 *
	 * @see ExpandBar	 
	 */
	public SWTQuery setSpacing(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setSpacing(each, value);
		}
		return this;
	}	

	/**
	 * Sets a DescriptionControl property.
	 * 
	 * @param value a DescriptionControl value to set.
	 *
	 * @see Section	 
	 */
	public SWTQuery setDescriptionControl(Control value){
		for(Widget each : items){
			WidgetPropertySwitch.setDescriptionControl(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Text property.
	 * 
	 * @param value a Text value to set.
	 *
	 * @see Hyperlink
	 * @see ScrolledForm
	 * @see CLabel
	 * @see StyledText
	 * @see Decorations
	 * @see ExpandableComposite
	 * @see Browser
	 * @see CCombo
	 * @see Combo
	 * @see Group
	 * @see Form
	 * @see Text
	 * @see Button
	 * @see Label
	 * @see Link
	 * @see TableItem
	 * @see TreeItem
	 * @see Item
	 * @see ToolTip	 
	 */
	public SWTQuery setText(String value){
		for(Widget each : items){
			WidgetPropertySwitch.setText(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Canceled property.
	 * 
	 * @param value a Canceled value to set.
	 *
	 * @see ProgressMonitorPart	 
	 */
	public SWTQuery setCanceled(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setCanceled(each, value);
		}
		return this;
	}

	/**
	 *Inverts Canceled property.
	 *
	 * @see ProgressMonitorPart	 
	 */
	public SWTQuery toggleCanceled(){
		for(Widget each : items){
			Boolean old = WidgetPropertySwitch.getCanceled(each);
			WidgetPropertySwitch.setCanceled(each, !old);
		}
		return this;
	}	

	/**
	 * Sets a TabList property.
	 * 
	 * @param value a TabList value to set.
	 *
	 * @see Composite	 
	 */
	public SWTQuery setTabList(Control[] value){
		for(Widget each : items){
			WidgetPropertySwitch.setTabList(each, value);
		}
		return this;
	}	

	/**
	 * Sets a Minutes property.
	 * 
	 * @param value a Minutes value to set.
	 *
	 * @see DateTime	 
	 */
	public SWTQuery setMinutes(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setMinutes(each, value);
		}
		return this;
	}	

	/**
	 * Sets a HotImage property.
	 * 
	 * @param value a HotImage value to set.
	 *
	 * @see ToolItem	 
	 */
	public SWTQuery setHotImage(Image value){
		for(Widget each : items){
			WidgetPropertySwitch.setHotImage(each, value);
		}
		return this;
	}	


	/**
	 * Gets a Visible property value.
	 * 
	 * @return value a Visible.
	 *
	 * @see Caret
	 * @see Shell
	 * @see Control
	 * @see Menu
	 * @see ScrollBar
	 * @see ToolTip	 
	 */	
	public Boolean isVisible(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getVisible(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a HorizontalPixel property value.
	 * 
	 * @return value a HorizontalPixel.
	 *
	 * @see StyledText	 
	 */	
	public Integer getHorizontalPixel(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getHorizontalPixel(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a ItemSizes property value.
	 * 
	 * @return value a ItemSizes.
	 *
	 * @see CoolBar	 
	 */	
	public Point[] getItemSizes(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getItemSizes(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a MenuBar property value.
	 * 
	 * @return value a MenuBar.
	 *
	 * @see Decorations	 
	 */	
	public Menu getMenuBar(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getMenuBar(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a ToolTipText property value.
	 * 
	 * @return value a ToolTipText.
	 *
	 * @see Hyperlink
	 * @see CLabel
	 * @see Control
	 * @see CTabItem
	 * @see TabItem
	 * @see TableColumn
	 * @see ToolItem
	 * @see TrayItem
	 * @see TreeColumn	 
	 */	
	public String getToolTipText(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getToolTipText(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Busy property value.
	 * 
	 * @return value a Busy.
	 *
	 * @see Form	 
	 */	
	public Boolean isBusy(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getBusy(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Increment property value.
	 * 
	 * @return value a Increment.
	 *
	 * @see Spinner
	 * @see Scale
	 * @see Slider
	 * @see ScrollBar	 
	 */	
	public Integer getIncrement(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getIncrement(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Shell property value.
	 * 
	 * @return value a Shell.
	 *
	 * @see Shell
	 * @see CCombo
	 * @see Control
	 * @see Menu	 
	 */	
	public Shell getShell(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getShell(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a LabelProvider property value.
	 * 
	 * @return value a LabelProvider.
	 *
	 * @see FilteredList	 
	 */	
	public ILabelProvider getLabelProvider(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getLabelProvider(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a FilterControl property value.
	 * 
	 * @return value a FilterControl.
	 *
	 * @see FilteredTree	 
	 */	
	public Text getFilterControl(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getFilterControl(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a CaretOffset property value.
	 * 
	 * @return value a CaretOffset.
	 *
	 * @see StyledText
	 * @see IME	 
	 */	
	public Integer getCaretOffset(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getCaretOffset(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Underlined property value.
	 * 
	 * @return value a Underlined.
	 *
	 * @see Hyperlink	 
	 */	
	public Boolean isUnderlined(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getUnderlined(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a ChildrenMessages property value.
	 * 
	 * @return value a ChildrenMessages.
	 *
	 * @see Form	 
	 */	
	public IMessage[] getChildrenMessages(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getChildrenMessages(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Filter property value.
	 * 
	 * @return value a Filter.
	 *
	 * @see FilteredList	 
	 */	
	public String getFilter(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getFilter(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Layout property value.
	 * 
	 * @return value a Layout.
	 *
	 * @see Composite	 
	 */	
	public Layout getLayout(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getLayout(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a BackgroundImageTiled property value.
	 * 
	 * @return value a BackgroundImageTiled.
	 *
	 * @see Form	 
	 */	
	public Boolean isBackgroundImageTiled(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getBackgroundImageTiled(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a LineDelimiter property value.
	 * 
	 * @return value a LineDelimiter.
	 *
	 * @see StyledText
	 * @see Text	 
	 */	
	public String getLineDelimiter(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getLineDelimiter(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a IME property value.
	 * 
	 * @return value a IME.
	 *
	 * @see Canvas	 
	 */	
	public IME getIME(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getIME(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a TextChars property value.
	 * 
	 * @return value a TextChars.
	 *
	 * @see Text	 
	 */	
	public char[] getTextChars(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getTextChars(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a MessageType property value.
	 * 
	 * @return value a MessageType.
	 *
	 * @see ScrolledForm
	 * @see Form	 
	 */	
	public Integer getMessageType(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getMessageType(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a TopMargin property value.
	 * 
	 * @return value a TopMargin.
	 *
	 * @see CLabel
	 * @see StyledText	 
	 */	
	public Integer getTopMargin(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getTopMargin(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a GridLineWidth property value.
	 * 
	 * @return value a GridLineWidth.
	 *
	 * @see Table
	 * @see Tree	 
	 */	
	public Integer getGridLineWidth(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getGridLineWidth(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Weights property value.
	 * 
	 * @return value a Weights.
	 *
	 * @see SashForm	 
	 */	
	public int[] getWeights(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getWeights(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a LayoutData property value.
	 * 
	 * @return value a LayoutData.
	 *
	 * @see Control	 
	 */	
	public Object getLayoutData(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getLayoutData(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a StyleRanges property value.
	 * 
	 * @return value a StyleRanges.
	 *
	 * @see StyledText	 
	 */	
	public StyleRange[] getStyleRanges(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getStyleRanges(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Control property value.
	 * 
	 * @return value a Control.
	 *
	 * @see DragSource
	 * @see DropTarget
	 * @see CTabItem
	 * @see CoolItem
	 * @see ExpandItem
	 * @see TabItem
	 * @see ToolItem	 
	 */	
	public Control getControl(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getControl(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Renderer property value.
	 * 
	 * @return value a Renderer.
	 *
	 * @see CTabFolder	 
	 */	
	public CTabFolderRenderer getRenderer(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getRenderer(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Image property value.
	 * 
	 * @return value a Image.
	 *
	 * @see Caret
	 * @see ImageHyperlink
	 * @see ScrolledForm
	 * @see CLabel
	 * @see Decorations
	 * @see Form
	 * @see Button
	 * @see Label
	 * @see TableItem
	 * @see TreeItem
	 * @see Item	 
	 */	
	public Image getImage(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getImage(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a RightMinimumSize property value.
	 * 
	 * @return value a RightMinimumSize.
	 *
	 * @see CBanner	 
	 */	
	public Point getRightMinimumSize(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getRightMinimumSize(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a SelectedLinkText property value.
	 * 
	 * @return value a SelectedLinkText.
	 *
	 * @see FormText	 
	 */	
	public String getSelectedLinkText(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getSelectedLinkText(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Expanded property value.
	 * 
	 * @return value a Expanded.
	 *
	 * @see ToggleHyperlink
	 * @see ExpandableComposite	 
	 */	
	public Boolean isExpanded(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getExpanded(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a TitleBarForeground property value.
	 * 
	 * @return value a TitleBarForeground.
	 *
	 * @see ExpandableComposite	 
	 */	
	public Color getTitleBarForeground(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getTitleBarForeground(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Client property value.
	 * 
	 * @return value a Client.
	 *
	 * @see ExpandableComposite	 
	 */	
	public Control getClient(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getClient(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Right property value.
	 * 
	 * @return value a Right.
	 *
	 * @see CBanner	 
	 */	
	public Control getRight(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getRight(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Orientation property value.
	 * 
	 * @return value a Orientation.
	 *
	 * @see StyledText
	 * @see SashForm
	 * @see Combo
	 * @see Text
	 * @see Control
	 * @see Menu	 
	 */	
	public Integer getOrientation(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getOrientation(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Hours property value.
	 * 
	 * @return value a Hours.
	 *
	 * @see DateTime	 
	 */	
	public Integer getHours(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getHours(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a HoverImage property value.
	 * 
	 * @return value a HoverImage.
	 *
	 * @see ImageHyperlink	 
	 */	
	public Image getHoverImage(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getHoverImage(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a BottomMargin property value.
	 * 
	 * @return value a BottomMargin.
	 *
	 * @see CLabel
	 * @see StyledText	 
	 */	
	public Integer getBottomMargin(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getBottomMargin(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a ThumbBounds property value.
	 * 
	 * @return value a ThumbBounds.
	 *
	 * @see ScrollBar	 
	 */	
	public Rectangle getThumbBounds(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getThumbBounds(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Indent property value.
	 * 
	 * @return value a Indent.
	 *
	 * @see StyledText	 
	 */	
	public Integer getIndent(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getIndent(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a TopIndex property value.
	 * 
	 * @return value a TopIndex.
	 *
	 * @see StyledText
	 * @see Table
	 * @see List
	 * @see Text	 
	 */	
	public Integer getTopIndex(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getTopIndex(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a ImeInputMode property value.
	 * 
	 * @return value a ImeInputMode.
	 *
	 * @see Shell	 
	 */	
	public Integer getImeInputMode(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getImeInputMode(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a RightWidth property value.
	 * 
	 * @return value a RightWidth.
	 *
	 * @see CBanner	 
	 */	
	public Integer getRightWidth(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getRightWidth(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Foreground property value.
	 * 
	 * @return value a Foreground.
	 *
	 * @see StyledText
	 * @see TableCursor
	 * @see Control
	 * @see TableItem
	 * @see TreeItem	 
	 */	
	public Color getForeground(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getForeground(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a MaximizedControl property value.
	 * 
	 * @return value a MaximizedControl.
	 *
	 * @see SashForm	 
	 */	
	public Control getMaximizedControl(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getMaximizedControl(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Month property value.
	 * 
	 * @return value a Month.
	 *
	 * @see DateTime	 
	 */	
	public Integer getMonth(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getMonth(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Menu property value.
	 * 
	 * @return value a Menu.
	 *
	 * @see CCombo
	 * @see Control
	 * @see MenuItem	 
	 */	
	public Menu getMenu(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getMenu(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Reparentable property value.
	 * 
	 * @return value a Reparentable.
	 *
	 * @see Decorations
	 * @see Control	 
	 */	
	public Boolean isReparentable(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getReparentable(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a CaretLocation property value.
	 * 
	 * @return value a CaretLocation.
	 *
	 * @see Text	 
	 */	
	public Point getCaretLocation(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getCaretLocation(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a LineSpacing property value.
	 * 
	 * @return value a LineSpacing.
	 *
	 * @see StyledText	 
	 */	
	public Integer getLineSpacing(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getLineSpacing(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Href property value.
	 * 
	 * @return value a Href.
	 *
	 * @see AbstractHyperlink	 
	 */	
	public Object getHref(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getHref(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a HoverDecorationColor property value.
	 * 
	 * @return value a HoverDecorationColor.
	 *
	 * @see ToggleHyperlink	 
	 */	
	public Color getHoverDecorationColor(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getHoverDecorationColor(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Current property value.
	 * 
	 * @return value a Current.
	 *
	 * @see GLCanvas	 
	 */	
	public Boolean isCurrent(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getCurrent(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a MessageManager property value.
	 * 
	 * @return value a MessageManager.
	 *
	 * @see ScrolledForm
	 * @see Form	 
	 */	
	public IMessageManager getMessageManager(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getMessageManager(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Head property value.
	 * 
	 * @return value a Head.
	 *
	 * @see Form	 
	 */	
	public Composite getHead(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getHead(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a TextLimit property value.
	 * 
	 * @return value a TextLimit.
	 *
	 * @see StyledText
	 * @see CCombo
	 * @see Combo
	 * @see Spinner
	 * @see Text	 
	 */	
	public Integer getTextLimit(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getTextLimit(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a ItemCount property value.
	 * 
	 * @return value a ItemCount.
	 *
	 * @see CCombo
	 * @see CTabFolder
	 * @see Combo
	 * @see CoolBar
	 * @see ExpandBar
	 * @see TabFolder
	 * @see Table
	 * @see ToolBar
	 * @see Tree
	 * @see List
	 * @see TreeItem
	 * @see Menu
	 * @see Tray	 
	 */	
	public Integer getItemCount(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getItemCount(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a MinWidth property value.
	 * 
	 * @return value a MinWidth.
	 *
	 * @see ScrolledComposite	 
	 */	
	public Integer getMinWidth(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getMinWidth(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a PatternFilter property value.
	 * 
	 * @return value a PatternFilter.
	 *
	 * @see FilteredTree	 
	 */	
	public PatternFilter getPatternFilter(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getPatternFilter(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a TitleBarBorderColor property value.
	 * 
	 * @return value a TitleBarBorderColor.
	 *
	 * @see Section	 
	 */	
	public Color getTitleBarBorderColor(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getTitleBarBorderColor(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a WrapIndent property value.
	 * 
	 * @return value a WrapIndent.
	 *
	 * @see StyledText	 
	 */	
	public Integer getWrapIndent(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getWrapIndent(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Enabled property value.
	 * 
	 * @return value a Enabled.
	 *
	 * @see Shell
	 * @see Control
	 * @see MenuItem
	 * @see ToolItem
	 * @see Menu
	 * @see ScrollBar	 
	 */	
	public Boolean isEnabled(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getEnabled(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Viewer property value.
	 * 
	 * @return value a Viewer.
	 *
	 * @see FilteredTree	 
	 */	
	public TreeViewer getViewer(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getViewer(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a ItemHeight property value.
	 * 
	 * @return value a ItemHeight.
	 *
	 * @see CCombo
	 * @see Combo
	 * @see Table
	 * @see Tree
	 * @see List	 
	 */	
	public Integer getItemHeight(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getItemHeight(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Cursor property value.
	 * 
	 * @return value a Cursor.
	 *
	 * @see Control	 
	 */	
	public Cursor getCursor(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getCursor(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a LineCount property value.
	 * 
	 * @return value a LineCount.
	 *
	 * @see StyledText
	 * @see Text	 
	 */	
	public Integer getLineCount(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getLineCount(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a WebBrowser property value.
	 * 
	 * @return value a WebBrowser.
	 *
	 * @see Browser	 
	 */	
	public Object getWebBrowser(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getWebBrowser(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Year property value.
	 * 
	 * @return value a Year.
	 *
	 * @see DateTime	 
	 */	
	public Integer getYear(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getYear(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Region property value.
	 * 
	 * @return value a Region.
	 *
	 * @see Shell
	 * @see Control	 
	 */	
	public Region getRegion(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getRegion(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a ToolBarVerticalAlignment property value.
	 * 
	 * @return value a ToolBarVerticalAlignment.
	 *
	 * @see Form	 
	 */	
	public Integer getToolBarVerticalAlignment(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getToolBarVerticalAlignment(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Accelerator property value.
	 * 
	 * @return value a Accelerator.
	 *
	 * @see MenuItem	 
	 */	
	public Integer getAccelerator(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getAccelerator(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a PageIncrement property value.
	 * 
	 * @return value a PageIncrement.
	 *
	 * @see Spinner
	 * @see Scale
	 * @see Slider
	 * @see ScrollBar	 
	 */	
	public Integer getPageIncrement(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getPageIncrement(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a FocusControl property value.
	 * 
	 * @return value a FocusControl.
	 *
	 * @see Browser
	 * @see CCombo
	 * @see Control	 
	 */	
	public Boolean isFocusControl(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getFocusControl(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Disposed property value.
	 * 
	 * @return value a Disposed.
	 *
	 * @see Widget	 
	 */	
	public Boolean isDisposed(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getDisposed(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Description property value.
	 * 
	 * @return value a Description.
	 *
	 * @see Section	 
	 */	
	public String getDescription(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getDescription(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a SelectionForeground property value.
	 * 
	 * @return value a SelectionForeground.
	 *
	 * @see StyledText
	 * @see CTabFolder	 
	 */	
	public Color getSelectionForeground(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getSelectionForeground(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a FocusIndex property value.
	 * 
	 * @return value a FocusIndex.
	 *
	 * @see List	 
	 */	
	public Integer getFocusIndex(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getFocusIndex(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a BlockSelectionBounds property value.
	 * 
	 * @return value a BlockSelectionBounds.
	 *
	 * @see StyledText	 
	 */	
	public Rectangle getBlockSelectionBounds(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getBlockSelectionBounds(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a MinHeight property value.
	 * 
	 * @return value a MinHeight.
	 *
	 * @see ScrolledComposite	 
	 */	
	public Integer getMinHeight(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getMinHeight(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a MinimumSize property value.
	 * 
	 * @return value a MinimumSize.
	 *
	 * @see Shell
	 * @see CoolItem	 
	 */	
	public Point getMinimumSize(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getMinimumSize(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Tabs property value.
	 * 
	 * @return value a Tabs.
	 *
	 * @see StyledText
	 * @see Text	 
	 */	
	public Integer getTabs(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getTabs(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a ThumbTrackBounds property value.
	 * 
	 * @return value a ThumbTrackBounds.
	 *
	 * @see ScrollBar	 
	 */	
	public Rectangle getThumbTrackBounds(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getThumbTrackBounds(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a CaretLineNumber property value.
	 * 
	 * @return value a CaretLineNumber.
	 *
	 * @see Text	 
	 */	
	public Integer getCaretLineNumber(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getCaretLineNumber(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a State property value.
	 * 
	 * @return value a State.
	 *
	 * @see ProgressBar	 
	 */	
	public Integer getState(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getState(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a TopItem property value.
	 * 
	 * @return value a TopItem.
	 *
	 * @see Tree	 
	 */	
	public TreeItem getTopItem(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getTopItem(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a TopCenter property value.
	 * 
	 * @return value a TopCenter.
	 *
	 * @see ViewForm	 
	 */	
	public Control getTopCenter(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getTopCenter(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a TabStops property value.
	 * 
	 * @return value a TabStops.
	 *
	 * @see StyledText	 
	 */	
	public int[] getTabStops(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getTabStops(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Styles property value.
	 * 
	 * @return value a Styles.
	 *
	 * @see IME	 
	 */	
	public TextStyle[] getStyles(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getStyles(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a MinimumCharacters property value.
	 * 
	 * @return value a MinimumCharacters.
	 *
	 * @see CTabFolder	 
	 */	
	public Integer getMinimumCharacters(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getMinimumCharacters(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a HorizontalIndex property value.
	 * 
	 * @return value a HorizontalIndex.
	 *
	 * @see StyledText	 
	 */	
	public Integer getHorizontalIndex(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getHorizontalIndex(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Images property value.
	 * 
	 * @return value a Images.
	 *
	 * @see Decorations	 
	 */	
	public Image[] getImages(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getImages(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Origin property value.
	 * 
	 * @return value a Origin.
	 *
	 * @see ScrolledComposite	 
	 */	
	public Point getOrigin(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getOrigin(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a LineHeight property value.
	 * 
	 * @return value a LineHeight.
	 *
	 * @see StyledText
	 * @see Text	 
	 */	
	public Integer getLineHeight(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getLineHeight(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Empty property value.
	 * 
	 * @return value a Empty.
	 *
	 * @see FilteredList	 
	 */	
	public Boolean isEmpty(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getEmpty(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Data property value.
	 * 
	 * @return value a Data.
	 *
	 * @see Widget	 
	 */	
	public Object getData(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getData(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a EchoChar property value.
	 * 
	 * @return value a EchoChar.
	 *
	 * @see Text	 
	 */	
	public Character getEchoChar(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getEchoChar(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a HeadClient property value.
	 * 
	 * @return value a HeadClient.
	 *
	 * @see Form	 
	 */	
	public Control getHeadClient(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getHeadClient(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Url property value.
	 * 
	 * @return value a Url.
	 *
	 * @see Browser	 
	 */	
	public String getUrl(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getUrl(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a BackEnabled property value.
	 * 
	 * @return value a BackEnabled.
	 *
	 * @see Browser	 
	 */	
	public Boolean isBackEnabled(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getBackEnabled(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a SelectionCount property value.
	 * 
	 * @return value a SelectionCount.
	 *
	 * @see StyledText
	 * @see Table
	 * @see Tree
	 * @see List
	 * @see Text	 
	 */	
	public Integer getSelectionCount(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getSelectionCount(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Seconds property value.
	 * 
	 * @return value a Seconds.
	 *
	 * @see DateTime	 
	 */	
	public Integer getSeconds(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getSeconds(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a SortDirection property value.
	 * 
	 * @return value a SortDirection.
	 *
	 * @see Table
	 * @see Tree	 
	 */	
	public Integer getSortDirection(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getSortDirection(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a HorizontalBar property value.
	 * 
	 * @return value a HorizontalBar.
	 *
	 * @see Scrollable	 
	 */	
	public ScrollBar getHorizontalBar(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getHorizontalBar(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a MenuManager property value.
	 * 
	 * @return value a MenuManager.
	 *
	 * @see Form	 
	 */	
	public IMenuManager getMenuManager(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getMenuManager(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a VisibleItemCount property value.
	 * 
	 * @return value a VisibleItemCount.
	 *
	 * @see CCombo
	 * @see Combo	 
	 */	
	public Integer getVisibleItemCount(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getVisibleItemCount(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Thumb property value.
	 * 
	 * @return value a Thumb.
	 *
	 * @see Slider
	 * @see ScrollBar	 
	 */	
	public Integer getThumb(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getThumb(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a DecorationColor property value.
	 * 
	 * @return value a DecorationColor.
	 *
	 * @see ToggleHyperlink	 
	 */	
	public Color getDecorationColor(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getDecorationColor(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Bounds property value.
	 * 
	 * @return value a Bounds.
	 *
	 * @see Caret
	 * @see Shell
	 * @see Decorations
	 * @see Control
	 * @see CTabItem
	 * @see CoolItem
	 * @see TabItem
	 * @see TableItem
	 * @see ToolItem
	 * @see TreeItem	 
	 */	
	public Rectangle getBounds(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getBounds(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Digits property value.
	 * 
	 * @return value a Digits.
	 *
	 * @see Spinner	 
	 */	
	public Integer getDigits(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getDigits(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Left property value.
	 * 
	 * @return value a Left.
	 *
	 * @see CBanner	 
	 */	
	public Control getLeft(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getLeft(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a BorderWidth property value.
	 * 
	 * @return value a BorderWidth.
	 *
	 * @see Text
	 * @see Control	 
	 */	
	public Integer getBorderWidth(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getBorderWidth(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Bottom property value.
	 * 
	 * @return value a Bottom.
	 *
	 * @see CBanner	 
	 */	
	public Control getBottom(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getBottom(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a BackgroundImage property value.
	 * 
	 * @return value a BackgroundImage.
	 *
	 * @see ScrolledForm
	 * @see Form
	 * @see Control	 
	 */	
	public Image getBackgroundImage(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getBackgroundImage(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a SelectedLinkHref property value.
	 * 
	 * @return value a SelectedLinkHref.
	 *
	 * @see FormText	 
	 */	
	public Object getSelectedLinkHref(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getSelectedLinkHref(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a TabPosition property value.
	 * 
	 * @return value a TabPosition.
	 *
	 * @see CTabFolder	 
	 */	
	public Integer getTabPosition(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getTabPosition(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Row property value.
	 * 
	 * @return value a Row.
	 *
	 * @see TableCursor	 
	 */	
	public TableItem getRow(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getRow(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a SelectionText property value.
	 * 
	 * @return value a SelectionText.
	 *
	 * @see StyledText
	 * @see FormText
	 * @see Text	 
	 */	
	public String getSelectionText(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getSelectionText(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a TitleBarBackground property value.
	 * 
	 * @return value a TitleBarBackground.
	 *
	 * @see Section	 
	 */	
	public Color getTitleBarBackground(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getTitleBarBackground(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a TabHeight property value.
	 * 
	 * @return value a TabHeight.
	 *
	 * @see CTabFolder	 
	 */	
	public Integer getTabHeight(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getTabHeight(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a DelayedReflow property value.
	 * 
	 * @return value a DelayedReflow.
	 *
	 * @see SharedScrolledComposite	 
	 */	
	public Boolean isDelayedReflow(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getDelayedReflow(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Alpha property value.
	 * 
	 * @return value a Alpha.
	 *
	 * @see Shell	 
	 */	
	public Integer getAlpha(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getAlpha(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a ToolBarManager property value.
	 * 
	 * @return value a ToolBarManager.
	 *
	 * @see ScrolledForm
	 * @see Form	 
	 */	
	public IToolBarManager getToolBarManager(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getToolBarManager(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a PreferredSize property value.
	 * 
	 * @return value a PreferredSize.
	 *
	 * @see CoolItem	 
	 */	
	public Point getPreferredSize(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getPreferredSize(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Background property value.
	 * 
	 * @return value a Background.
	 *
	 * @see StyledText
	 * @see TableCursor
	 * @see Control
	 * @see TableItem
	 * @see TreeItem	 
	 */	
	public Color getBackground(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getBackground(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a SelectionIndex property value.
	 * 
	 * @return value a SelectionIndex.
	 *
	 * @see CCombo
	 * @see CTabFolder
	 * @see Combo
	 * @see TabFolder
	 * @see Table
	 * @see FilteredList
	 * @see List	 
	 */	
	public Integer getSelectionIndex(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getSelectionIndex(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a SashWidth property value.
	 * 
	 * @return value a SashWidth.
	 *
	 * @see SashForm	 
	 */	
	public Integer getSashWidth(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getSashWidth(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a WhitespaceNormalized property value.
	 * 
	 * @return value a WhitespaceNormalized.
	 *
	 * @see FormText	 
	 */	
	public Boolean isWhitespaceNormalized(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getWhitespaceNormalized(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a CommitCount property value.
	 * 
	 * @return value a CommitCount.
	 *
	 * @see IME	 
	 */	
	public Integer getCommitCount(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getCommitCount(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Monitor property value.
	 * 
	 * @return value a Monitor.
	 *
	 * @see Control	 
	 */	
	public Monitor getMonitor(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getMonitor(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Font property value.
	 * 
	 * @return value a Font.
	 *
	 * @see Caret
	 * @see Control
	 * @see CTabItem
	 * @see TableItem
	 * @see TreeItem	 
	 */	
	public Font getFont(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getFont(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Accessible property value.
	 * 
	 * @return value a Accessible.
	 *
	 * @see FilteredList
	 * @see Control	 
	 */	
	public Accessible getAccessible(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getAccessible(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Ranges property value.
	 * 
	 * @return value a Ranges.
	 *
	 * @see StyledText
	 * @see IME	 
	 */	
	public int[] getRanges(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getRanges(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Rectangles property value.
	 * 
	 * @return value a Rectangles.
	 *
	 * @see Tracker	 
	 */	
	public Rectangle[] getRectangles(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getRectangles(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a DefaultItem property value.
	 * 
	 * @return value a DefaultItem.
	 *
	 * @see Menu	 
	 */	
	public MenuItem getDefaultItem(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getDefaultItem(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a SelectionRanges property value.
	 * 
	 * @return value a SelectionRanges.
	 *
	 * @see StyledText	 
	 */	
	public int[] getSelectionRanges(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getSelectionRanges(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Message property value.
	 * 
	 * @return value a Message.
	 *
	 * @see ScrolledForm
	 * @see Form
	 * @see Text
	 * @see ToolTip	 
	 */	
	public String getMessage(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getMessage(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a LeftMargin property value.
	 * 
	 * @return value a LeftMargin.
	 *
	 * @see CLabel
	 * @see StyledText	 
	 */	
	public Integer getLeftMargin(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getLeftMargin(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a SelectionIndices property value.
	 * 
	 * @return value a SelectionIndices.
	 *
	 * @see Table
	 * @see FilteredList
	 * @see List	 
	 */	
	public int[] getSelectionIndices(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getSelectionIndices(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a TextClientHeightDifference property value.
	 * 
	 * @return value a TextClientHeightDifference.
	 *
	 * @see ExpandableComposite	 
	 */	
	public Integer getTextClientHeightDifference(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getTextClientHeightDifference(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a CharCount property value.
	 * 
	 * @return value a CharCount.
	 *
	 * @see StyledText
	 * @see Text	 
	 */	
	public Integer getCharCount(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getCharCount(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a DefaultButton property value.
	 * 
	 * @return value a DefaultButton.
	 *
	 * @see Decorations	 
	 */	
	public Button getDefaultButton(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getDefaultButton(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Minimum property value.
	 * 
	 * @return value a Minimum.
	 *
	 * @see Spinner
	 * @see ProgressBar
	 * @see Scale
	 * @see Slider
	 * @see ScrollBar	 
	 */	
	public Integer getMinimum(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getMinimum(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Baseline property value.
	 * 
	 * @return value a Baseline.
	 *
	 * @see StyledText	 
	 */	
	public Integer getBaseline(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getBaseline(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Alignment property value.
	 * 
	 * @return value a Alignment.
	 *
	 * @see CLabel
	 * @see StyledText
	 * @see Button
	 * @see Label
	 * @see TableColumn
	 * @see TreeColumn	 
	 */	
	public Integer getAlignment(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getAlignment(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Shells property value.
	 * 
	 * @return value a Shells.
	 *
	 * @see Shell	 
	 */	
	public Shell[] getShells(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getShells(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Showing property value.
	 * 
	 * @return value a Showing.
	 *
	 * @see CTabItem	 
	 */	
	public Boolean isShowing(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getShowing(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a DropListeners property value.
	 * 
	 * @return value a DropListeners.
	 *
	 * @see DropTarget	 
	 */	
	public DropTargetListener[] getDropListeners(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getDropListeners(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a TopLeft property value.
	 * 
	 * @return value a TopLeft.
	 *
	 * @see ViewForm	 
	 */	
	public Control getTopLeft(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getTopLeft(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a SeparatorControl property value.
	 * 
	 * @return value a SeparatorControl.
	 *
	 * @see Section	 
	 */	
	public Control getSeparatorControl(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getSeparatorControl(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a LayoutDeferred property value.
	 * 
	 * @return value a LayoutDeferred.
	 *
	 * @see Composite	 
	 */	
	public Boolean isLayoutDeferred(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getLayoutDeferred(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a ParentMenu property value.
	 * 
	 * @return value a ParentMenu.
	 *
	 * @see Menu	 
	 */	
	public Menu getParentMenu(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getParentMenu(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Style property value.
	 * 
	 * @return value a Style.
	 *
	 * @see CLabel
	 * @see Browser
	 * @see CCombo
	 * @see CTabFolder
	 * @see SashForm
	 * @see Widget	 
	 */	
	public Integer getStyle(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getStyle(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a TextClient property value.
	 * 
	 * @return value a TextClient.
	 *
	 * @see ExpandableComposite	 
	 */	
	public Control getTextClient(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getTextClient(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a VerticalBar property value.
	 * 
	 * @return value a VerticalBar.
	 *
	 * @see Scrollable	 
	 */	
	public ScrollBar getVerticalBar(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getVerticalBar(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Column property value.
	 * 
	 * @return value a Column.
	 *
	 * @see TableCursor	 
	 */	
	public Integer getColumn(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getColumn(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a TopRightAlignment property value.
	 * 
	 * @return value a TopRightAlignment.
	 *
	 * @see CTabFolder	 
	 */	
	public Integer getTopRightAlignment(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getTopRightAlignment(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a ID property value.
	 * 
	 * @return value a ID.
	 *
	 * @see MenuItem	 
	 */	
	public Integer getID(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getID(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Width property value.
	 * 
	 * @return value a Width.
	 *
	 * @see TableColumn
	 * @see ToolItem
	 * @see TreeColumn	 
	 */	
	public Integer getWidth(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getWidth(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a ToolTip property value.
	 * 
	 * @return value a ToolTip.
	 *
	 * @see TrayItem	 
	 */	
	public ToolTip getToolTip(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getToolTip(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a SeparatorVisible property value.
	 * 
	 * @return value a SeparatorVisible.
	 *
	 * @see Form	 
	 */	
	public Boolean isSeparatorVisible(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getSeparatorVisible(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Size property value.
	 * 
	 * @return value a Size.
	 *
	 * @see Caret
	 * @see Shell
	 * @see Decorations
	 * @see Control
	 * @see CoolItem
	 * @see ScrollBar	 
	 */	
	public Point getSize(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getSize(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a SelectionBackground property value.
	 * 
	 * @return value a SelectionBackground.
	 *
	 * @see StyledText
	 * @see CTabFolder	 
	 */	
	public Color getSelectionBackground(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getSelectionBackground(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Body property value.
	 * 
	 * @return value a Body.
	 *
	 * @see ScrolledForm
	 * @see Form	 
	 */	
	public Composite getBody(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getBody(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Maximum property value.
	 * 
	 * @return value a Maximum.
	 *
	 * @see Spinner
	 * @see ProgressBar
	 * @see Scale
	 * @see Slider
	 * @see ScrollBar	 
	 */	
	public Integer getMaximum(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getMaximum(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Display property value.
	 * 
	 * @return value a Display.
	 *
	 * @see Widget	 
	 */	
	public Display getDisplay(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getDisplay(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a ToolBar property value.
	 * 
	 * @return value a ToolBar.
	 *
	 * @see Shell	 
	 */	
	public ToolBar getToolBar(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getToolBar(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a RowCount property value.
	 * 
	 * @return value a RowCount.
	 *
	 * @see ToolBar	 
	 */	
	public Integer getRowCount(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getRowCount(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Height property value.
	 * 
	 * @return value a Height.
	 *
	 * @see ExpandItem	 
	 */	
	public Integer getHeight(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getHeight(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a TopRight property value.
	 * 
	 * @return value a TopRight.
	 *
	 * @see CTabFolder
	 * @see ViewForm	 
	 */	
	public Control getTopRight(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getTopRight(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a HyperlinkSettings property value.
	 * 
	 * @return value a HyperlinkSettings.
	 *
	 * @see FormText	 
	 */	
	public HyperlinkSettings getHyperlinkSettings(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getHyperlinkSettings(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a ClientArea property value.
	 * 
	 * @return value a ClientArea.
	 *
	 * @see Decorations
	 * @see CBanner
	 * @see CTabFolder
	 * @see ViewForm
	 * @see Group
	 * @see TabFolder
	 * @see Scrollable	 
	 */	
	public Rectangle getClientArea(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getClientArea(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Transfer property value.
	 * 
	 * @return value a Transfer.
	 *
	 * @see DragSource
	 * @see DropTarget	 
	 */	
	public Transfer[] getTransfer(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getTransfer(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a ColumnCount property value.
	 * 
	 * @return value a ColumnCount.
	 *
	 * @see Table
	 * @see Tree	 
	 */	
	public Integer getColumnCount(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getColumnCount(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a ActiveImage property value.
	 * 
	 * @return value a ActiveImage.
	 *
	 * @see ImageHyperlink	 
	 */	
	public Image getActiveImage(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getActiveImage(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a FormText property value.
	 * 
	 * @return value a FormText.
	 *
	 * @see ScrolledFormText	 
	 */	
	public FormText getFormText(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getFormText(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a DropTargetEffect property value.
	 * 
	 * @return value a DropTargetEffect.
	 *
	 * @see DropTarget	 
	 */	
	public DropTargetEffect getDropTargetEffect(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getDropTargetEffect(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a TopPixel property value.
	 * 
	 * @return value a TopPixel.
	 *
	 * @see StyledText
	 * @see Text	 
	 */	
	public Integer getTopPixel(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getTopPixel(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a MarginColor property value.
	 * 
	 * @return value a MarginColor.
	 *
	 * @see StyledText	 
	 */	
	public Color getMarginColor(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getMarginColor(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a ExpansionStyle property value.
	 * 
	 * @return value a ExpansionStyle.
	 *
	 * @see ExpandableComposite	 
	 */	
	public Integer getExpansionStyle(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getExpansionStyle(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a CurrentPage property value.
	 * 
	 * @return value a CurrentPage.
	 *
	 * @see ScrolledPageBook	 
	 */	
	public Control getCurrentPage(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getCurrentPage(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Caret property value.
	 * 
	 * @return value a Caret.
	 *
	 * @see Canvas	 
	 */	
	public Caret getCaret(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getCaret(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a DragListeners property value.
	 * 
	 * @return value a DragListeners.
	 *
	 * @see DragSource	 
	 */	
	public DragSourceListener[] getDragListeners(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getDragListeners(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a CaretPosition property value.
	 * 
	 * @return value a CaretPosition.
	 *
	 * @see Text	 
	 */	
	public Integer getCaretPosition(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getCaretPosition(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a BrowserType property value.
	 * 
	 * @return value a BrowserType.
	 *
	 * @see Browser	 
	 */	
	public String getBrowserType(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getBrowserType(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a DragSourceEffect property value.
	 * 
	 * @return value a DragSourceEffect.
	 *
	 * @see DragSource	 
	 */	
	public DragSourceEffect getDragSourceEffect(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getDragSourceEffect(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a ColumnOrder property value.
	 * 
	 * @return value a ColumnOrder.
	 *
	 * @see Table
	 * @see Tree	 
	 */	
	public int[] getColumnOrder(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getColumnOrder(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Day property value.
	 * 
	 * @return value a Day.
	 *
	 * @see DateTime	 
	 */	
	public Integer getDay(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getDay(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a ForwardEnabled property value.
	 * 
	 * @return value a ForwardEnabled.
	 *
	 * @see Browser	 
	 */	
	public Boolean isForwardEnabled(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getForwardEnabled(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a ItemOrder property value.
	 * 
	 * @return value a ItemOrder.
	 *
	 * @see CoolBar	 
	 */	
	public int[] getItemOrder(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getItemOrder(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Form property value.
	 * 
	 * @return value a Form.
	 *
	 * @see ScrolledForm	 
	 */	
	public Form getForm(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getForm(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a WrapIndices property value.
	 * 
	 * @return value a WrapIndices.
	 *
	 * @see CoolBar	 
	 */	
	public int[] getWrapIndices(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getWrapIndices(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a TextHeight property value.
	 * 
	 * @return value a TextHeight.
	 *
	 * @see CCombo
	 * @see Combo	 
	 */	
	public Integer getTextHeight(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getTextHeight(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Container property value.
	 * 
	 * @return value a Container.
	 *
	 * @see ScrolledPageBook	 
	 */	
	public Composite getContainer(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getContainer(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a HeaderHeight property value.
	 * 
	 * @return value a HeaderHeight.
	 *
	 * @see Table
	 * @see Tree
	 * @see ExpandItem	 
	 */	
	public Integer getHeaderHeight(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getHeaderHeight(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a CompositionOffset property value.
	 * 
	 * @return value a CompositionOffset.
	 *
	 * @see IME	 
	 */	
	public Integer getCompositionOffset(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getCompositionOffset(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a GLData property value.
	 * 
	 * @return value a GLData.
	 *
	 * @see GLCanvas	 
	 */	
	public GLData getGLData(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getGLData(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a SelectionRange property value.
	 * 
	 * @return value a SelectionRange.
	 *
	 * @see StyledText	 
	 */	
	public Point getSelectionRange(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getSelectionRange(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a TitleBarGradientBackground property value.
	 * 
	 * @return value a TitleBarGradientBackground.
	 *
	 * @see Section	 
	 */	
	public Color getTitleBarGradientBackground(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getTitleBarGradientBackground(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a BackgroundMode property value.
	 * 
	 * @return value a BackgroundMode.
	 *
	 * @see Composite	 
	 */	
	public Integer getBackgroundMode(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getBackgroundMode(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a RightMargin property value.
	 * 
	 * @return value a RightMargin.
	 *
	 * @see CLabel
	 * @see StyledText	 
	 */	
	public Integer getRightMargin(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getRightMargin(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Location property value.
	 * 
	 * @return value a Location.
	 *
	 * @see Caret
	 * @see Shell
	 * @see Decorations
	 * @see Control	 
	 */	
	public Point getLocation(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getLocation(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Children property value.
	 * 
	 * @return value a Children.
	 *
	 * @see CCombo
	 * @see Composite	 
	 */	
	public Control[] getChildren(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getChildren(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Spacing property value.
	 * 
	 * @return value a Spacing.
	 *
	 * @see ExpandBar	 
	 */	
	public Integer getSpacing(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getSpacing(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a DescriptionControl property value.
	 * 
	 * @return value a DescriptionControl.
	 *
	 * @see Section	 
	 */	
	public Control getDescriptionControl(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getDescriptionControl(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Text property value.
	 * 
	 * @return value a Text.
	 *
	 * @see Hyperlink
	 * @see ScrolledForm
	 * @see CLabel
	 * @see StyledText
	 * @see Decorations
	 * @see AbstractHyperlink
	 * @see ExpandableComposite
	 * @see Browser
	 * @see CCombo
	 * @see Combo
	 * @see Group
	 * @see Spinner
	 * @see Form
	 * @see Text
	 * @see Button
	 * @see Label
	 * @see Link
	 * @see IME
	 * @see TableItem
	 * @see TreeItem
	 * @see Item
	 * @see ToolTip	 
	 */	
	public String getText(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getText(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Canceled property value.
	 * 
	 * @return value a Canceled.
	 *
	 * @see ProgressMonitorPart	 
	 */	
	public Boolean isCanceled(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getCanceled(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a TabList property value.
	 * 
	 * @return value a TabList.
	 *
	 * @see Composite	 
	 */	
	public Control[] getTabList(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getTabList(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a Minutes property value.
	 * 
	 * @return value a Minutes.
	 *
	 * @see DateTime	 
	 */	
	public Integer getMinutes(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getMinutes(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Gets a HotImage property value.
	 * 
	 * @return value a HotImage.
	 *
	 * @see ToolItem	 
	 */	
	public Image getHotImage(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getHotImage(items.get(0));
		}
		else{
			return null;
		}
	}
	public SWTQuery debug(final String text) {
		final int color = (CURRENT_DEBUG_COLOR + 2);
		CURRENT_DEBUG_COLOR = (++CURRENT_DEBUG_COLOR % 15);
		
		this.addListener(SWT.Paint, new Listener() {
			@Override
			public void handleEvent(Event event) {
				Rectangle bounds = $(event).getBounds();
				GC gc = event.gc;
				gc.setAlpha(150);
				gc.setBackground(Display.getDefault().getSystemColor(color));
				gc.fillRectangle(0, 0, bounds.width - 1, bounds.height - 1);
				gc.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
				gc.setAlpha(255);
				gc.drawText(text, 1, 1, true);
			}
		});
		
		this.redraw();
		return this;
	}
	
	public SWTQuery debug() {
		final int color = (CURRENT_DEBUG_COLOR + 2);
		CURRENT_DEBUG_COLOR = (++CURRENT_DEBUG_COLOR % 15);
		
		this.addListener(SWT.Paint, new Listener() {
			@Override
			public void handleEvent(Event event) {
				Rectangle bounds = $(event).getBounds();
				GC gc = event.gc;
				gc.setAlpha(150);
				gc.setBackground(Display.getDefault().getSystemColor(color));
				gc.fillRectangle(0, 0, bounds.width - 1, bounds.height - 1);
				gc.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
			}
		});
		this.redraw();
		return this;
	}
	
	public SWTQuery createDropTarget(int style) {
		ArrayList<Widget> dropTargets = new ArrayList<Widget>();
		for (Widget each : items) {
			if (each instanceof Control) {
				dropTargets.add(new DropTarget((Control) each, style));
			}
		}
		return new SWTQuery(dropTargets);
	}

	public SWTQuery getDropTarget(int style) {
		ArrayList<Widget> dropTargets = new ArrayList<Widget>();
		for (Widget each : items) {
			if (each instanceof Control) {
				Object target = ((Control) each).getData(DND.DROP_TARGET_KEY);
				if (target instanceof DropTarget) {
					dropTargets.add((Widget) target);
				}
			}
		}
		return new SWTQuery(dropTargets);
	}

	public int size() {
		return items.size();
	}
	
	public SWTQuery first(){
		if(items.size() > 0){
			return new SWTQuery(items.get(0));
		}
		else {
			return new SWTQuery(new UniqueList<Widget>());
		}
	}
	
	public SWTQuery last(){
		if(items.size() > 0){
			return new SWTQuery(items.get(items.size()-1));
		}
		else {
			return new SWTQuery(new UniqueList<Widget>());
		}
	}
	
	public SWTQuery get(int index){
		if(items.size() > index){
			return new SWTQuery(items.get(index));
		}
		else {
			return new SWTQuery(new UniqueList<Widget>());
		}
	}
}
