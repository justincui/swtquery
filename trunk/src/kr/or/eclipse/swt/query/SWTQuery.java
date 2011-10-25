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

import org.eclipse.ui.forms.widgets.FormText;
import kr.or.eclipse.swt.query.util.WidgetPropertySwitch;
import java.util.Map;
import java.lang.Boolean;
import kr.or.eclipse.swt.query.filter.IWidgetFilter;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Font;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.custom.CTabFolderRenderer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.graphics.Color;
import java.lang.Character;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.ToolTip;
import org.eclipse.swt.widgets.Caret;
import org.eclipse.swt.dnd.DropTargetEffect;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import java.util.ArrayList;
import org.eclipse.ui.forms.HyperlinkSettings;
import java.lang.Object;
import java.util.List;
import org.eclipse.swt.widgets.Layout;
import java.lang.String;
import java.lang.Integer;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.dnd.DragSourceEffect;
import kr.or.eclipse.swt.query.internal.grammar.Selector;
import org.eclipse.swt.widgets.IME;
import kr.or.eclipse.swt.query.internal.grammar.SelectorInterpreter;
import kr.or.eclipse.swt.query.internal.ChildrenSwitch;
import org.eclipse.swt.graphics.Cursor;
import kr.or.eclipse.swt.query.internal.UniqueList;
import kr.or.eclipse.swt.query.internal.ParentSwitch;
import org.eclipse.swt.widgets.MenuItem;


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
	 * Sets a Maximized.
	 * supported by:
	 * <ul>
	 * <li>Shell</li>
	 * <li>Decorations</li>
	 * <li>CTabFolder</li>	 
	 * </ul>
	 * 
	 * @param value	 a Maximized.
	 */
	public SWTQuery setMaximized(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setMaximized(each, value);
		}
		return this;
	}

	/**
	 * Gets a Maximized.
	 * supported by:
	 * <ul>
	 * <li>Shell</li>
	 * <li>Decorations</li>
	 * <li>CTabFolder</li>	 
	 * </ul>
	 * 
	 * @return value	 a Maximized.
	 */	
	public Boolean getMaximized(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getMaximized(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a Visible.
	 * supported by:
	 * <ul>
	 * <li>Caret</li>
	 * <li>Control</li>
	 * <li>TrayItem</li>
	 * <li>Menu</li>
	 * <li>ScrollBar</li>
	 * <li>ToolTip</li>	 
	 * </ul>
	 * 
	 * @param value	 a Visible.
	 */
	public SWTQuery setVisible(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setVisible(each, value);
		}
		return this;
	}

	/**
	 * Gets a Visible.
	 * supported by:
	 * <ul>
	 * <li>Caret</li>
	 * <li>Control</li>
	 * <li>TrayItem</li>
	 * <li>Menu</li>
	 * <li>ScrollBar</li>
	 * <li>ToolTip</li>	 
	 * </ul>
	 * 
	 * @return value	 a Visible.
	 */	
	public Boolean getVisible(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getVisible(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a HorizontalPixel.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>	 
	 * </ul>
	 * 
	 * @param value	 a HorizontalPixel.
	 */
	public SWTQuery setHorizontalPixel(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setHorizontalPixel(each, value);
		}
		return this;
	}

	/**
	 * Gets a HorizontalPixel.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>	 
	 * </ul>
	 * 
	 * @return value	 a HorizontalPixel.
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
	 * Sets a Moveable.
	 * supported by:
	 * <ul>
	 * <li>TableColumn</li>
	 * <li>TreeColumn</li>	 
	 * </ul>
	 * 
	 * @param value	 a Moveable.
	 */
	public SWTQuery setMoveable(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setMoveable(each, value);
		}
		return this;
	}

	/**
	 * Gets a Moveable.
	 * supported by:
	 * <ul>
	 * <li>TableColumn</li>
	 * <li>TreeColumn</li>	 
	 * </ul>
	 * 
	 * @return value	 a Moveable.
	 */	
	public Boolean getMoveable(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getMoveable(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a HorizontalIndex.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>	 
	 * </ul>
	 * 
	 * @param value	 a HorizontalIndex.
	 */
	public SWTQuery setHorizontalIndex(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setHorizontalIndex(each, value);
		}
		return this;
	}

	/**
	 * Gets a HorizontalIndex.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>	 
	 * </ul>
	 * 
	 * @return value	 a HorizontalIndex.
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
	 * Sets a Origin.
	 * supported by:
	 * <ul>
	 * <li>ScrolledComposite</li>	 
	 * </ul>
	 * 
	 * @param value	 a Origin.
	 */
	public SWTQuery setOrigin(Point value){
		for(Widget each : items){
			WidgetPropertySwitch.setOrigin(each, value);
		}
		return this;
	}

	/**
	 * Gets a Origin.
	 * supported by:
	 * <ul>
	 * <li>ScrolledComposite</li>	 
	 * </ul>
	 * 
	 * @return value	 a Origin.
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
	 * Sets a Images.
	 * supported by:
	 * <ul>
	 * <li>Decorations</li>	 
	 * </ul>
	 * 
	 * @param value	 a Images.
	 */
	public SWTQuery setImages(Image[] value){
		for(Widget each : items){
			WidgetPropertySwitch.setImages(each, value);
		}
		return this;
	}

	/**
	 * Gets a Images.
	 * supported by:
	 * <ul>
	 * <li>Decorations</li>	 
	 * </ul>
	 * 
	 * @return value	 a Images.
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
	 * Sets a Single.
	 * supported by:
	 * <ul>
	 * <li>CTabFolder</li>	 
	 * </ul>
	 * 
	 * @param value	 a Single.
	 */
	public SWTQuery setSingle(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setSingle(each, value);
		}
		return this;
	}

	/**
	 * Gets a Single.
	 * supported by:
	 * <ul>
	 * <li>CTabFolder</li>	 
	 * </ul>
	 * 
	 * @return value	 a Single.
	 */	
	public Boolean getSingle(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getSingle(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a Data.
	 * supported by:
	 * <ul>
	 * <li>Widget</li>	 
	 * </ul>
	 * 
	 * @param value	 a Data.
	 */
	public SWTQuery setData(Object value){
		for(Widget each : items){
			WidgetPropertySwitch.setData(each, value);
		}
		return this;
	}

	/**
	 * Gets a Data.
	 * supported by:
	 * <ul>
	 * <li>Widget</li>	 
	 * </ul>
	 * 
	 * @return value	 a Data.
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
	 * Sets a EchoChar.
	 * supported by:
	 * <ul>
	 * <li>Text</li>	 
	 * </ul>
	 * 
	 * @param value	 a EchoChar.
	 */
	public SWTQuery setEchoChar(Character value){
		for(Widget each : items){
			WidgetPropertySwitch.setEchoChar(each, value);
		}
		return this;
	}

	/**
	 * Gets a EchoChar.
	 * supported by:
	 * <ul>
	 * <li>Text</li>	 
	 * </ul>
	 * 
	 * @return value	 a EchoChar.
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
	 * Sets a HeadClient.
	 * supported by:
	 * <ul>
	 * <li>Form</li>	 
	 * </ul>
	 * 
	 * @param value	 a HeadClient.
	 */
	public SWTQuery setHeadClient(Control value){
		for(Widget each : items){
			WidgetPropertySwitch.setHeadClient(each, value);
		}
		return this;
	}

	/**
	 * Gets a HeadClient.
	 * supported by:
	 * <ul>
	 * <li>Form</li>	 
	 * </ul>
	 * 
	 * @return value	 a HeadClient.
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
	 * Sets a ToolTipText.
	 * supported by:
	 * <ul>
	 * <li>Hyperlink</li>
	 * <li>CLabel</li>
	 * <li>Control</li>
	 * <li>CTabItem</li>
	 * <li>TabItem</li>
	 * <li>TableColumn</li>
	 * <li>ToolItem</li>
	 * <li>TrayItem</li>
	 * <li>TreeColumn</li>	 
	 * </ul>
	 * 
	 * @param value	 a ToolTipText.
	 */
	public SWTQuery setToolTipText(String value){
		for(Widget each : items){
			WidgetPropertySwitch.setToolTipText(each, value);
		}
		return this;
	}

	/**
	 * Gets a ToolTipText.
	 * supported by:
	 * <ul>
	 * <li>Hyperlink</li>
	 * <li>CLabel</li>
	 * <li>Control</li>
	 * <li>CTabItem</li>
	 * <li>TabItem</li>
	 * <li>TableColumn</li>
	 * <li>ToolItem</li>
	 * <li>TrayItem</li>
	 * <li>TreeColumn</li>	 
	 * </ul>
	 * 
	 * @return value	 a ToolTipText.
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
	 * Sets a MenuBar.
	 * supported by:
	 * <ul>
	 * <li>Decorations</li>	 
	 * </ul>
	 * 
	 * @param value	 a MenuBar.
	 */
	public SWTQuery setMenuBar(Menu value){
		for(Widget each : items){
			WidgetPropertySwitch.setMenuBar(each, value);
		}
		return this;
	}

	/**
	 * Gets a MenuBar.
	 * supported by:
	 * <ul>
	 * <li>Decorations</li>	 
	 * </ul>
	 * 
	 * @return value	 a MenuBar.
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
	 * Sets a UnselectedImageVisible.
	 * supported by:
	 * <ul>
	 * <li>CTabFolder</li>	 
	 * </ul>
	 * 
	 * @param value	 a UnselectedImageVisible.
	 */
	public SWTQuery setUnselectedImageVisible(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setUnselectedImageVisible(each, value);
		}
		return this;
	}

	/**
	 * Gets a UnselectedImageVisible.
	 * supported by:
	 * <ul>
	 * <li>CTabFolder</li>	 
	 * </ul>
	 * 
	 * @return value	 a UnselectedImageVisible.
	 */	
	public Boolean getUnselectedImageVisible(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getUnselectedImageVisible(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a Url.
	 * supported by:
	 * <ul>
	 * <li>Browser</li>	 
	 * </ul>
	 * 
	 * @param value	 a Url.
	 */
	public SWTQuery setUrl(String value){
		for(Widget each : items){
			WidgetPropertySwitch.setUrl(each, value);
		}
		return this;
	}

	/**
	 * Gets a Url.
	 * supported by:
	 * <ul>
	 * <li>Browser</li>	 
	 * </ul>
	 * 
	 * @return value	 a Url.
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
	 * Sets a Modified.
	 * supported by:
	 * <ul>
	 * <li>Shell</li>	 
	 * </ul>
	 * 
	 * @param value	 a Modified.
	 */
	public SWTQuery setModified(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setModified(each, value);
		}
		return this;
	}

	/**
	 * Gets a Modified.
	 * supported by:
	 * <ul>
	 * <li>Shell</li>	 
	 * </ul>
	 * 
	 * @return value	 a Modified.
	 */	
	public Boolean getModified(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getModified(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a ParagraphsSeparated.
	 * supported by:
	 * <ul>
	 * <li>FormText</li>	 
	 * </ul>
	 * 
	 * @param value	 a ParagraphsSeparated.
	 */
	public SWTQuery setParagraphsSeparated(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setParagraphsSeparated(each, value);
		}
		return this;
	}

	/**
	 * Gets a ParagraphsSeparated.
	 * supported by:
	 * <ul>
	 * <li>FormText</li>	 
	 * </ul>
	 * 
	 * @return value	 a ParagraphsSeparated.
	 */	
	public Boolean getParagraphsSeparated(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getParagraphsSeparated(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a Increment.
	 * supported by:
	 * <ul>
	 * <li>Spinner</li>
	 * <li>Scale</li>
	 * <li>Slider</li>
	 * <li>ScrollBar</li>	 
	 * </ul>
	 * 
	 * @param value	 a Increment.
	 */
	public SWTQuery setIncrement(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setIncrement(each, value);
		}
		return this;
	}

	/**
	 * Gets a Increment.
	 * supported by:
	 * <ul>
	 * <li>Spinner</li>
	 * <li>Scale</li>
	 * <li>Slider</li>
	 * <li>ScrollBar</li>	 
	 * </ul>
	 * 
	 * @return value	 a Increment.
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
	 * Sets a SortDirection.
	 * supported by:
	 * <ul>
	 * <li>Table</li>
	 * <li>Tree</li>	 
	 * </ul>
	 * 
	 * @param value	 a SortDirection.
	 */
	public SWTQuery setSortDirection(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setSortDirection(each, value);
		}
		return this;
	}

	/**
	 * Gets a SortDirection.
	 * supported by:
	 * <ul>
	 * <li>Table</li>
	 * <li>Tree</li>	 
	 * </ul>
	 * 
	 * @return value	 a SortDirection.
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
	 * Sets a Seconds.
	 * supported by:
	 * <ul>
	 * <li>DateTime</li>	 
	 * </ul>
	 * 
	 * @param value	 a Seconds.
	 */
	public SWTQuery setSeconds(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setSeconds(each, value);
		}
		return this;
	}

	/**
	 * Gets a Seconds.
	 * supported by:
	 * <ul>
	 * <li>DateTime</li>	 
	 * </ul>
	 * 
	 * @return value	 a Seconds.
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
	 * Sets a FullScreen.
	 * supported by:
	 * <ul>
	 * <li>Shell</li>	 
	 * </ul>
	 * 
	 * @param value	 a FullScreen.
	 */
	public SWTQuery setFullScreen(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setFullScreen(each, value);
		}
		return this;
	}

	/**
	 * Gets a FullScreen.
	 * supported by:
	 * <ul>
	 * <li>Shell</li>	 
	 * </ul>
	 * 
	 * @return value	 a FullScreen.
	 */	
	public Boolean getFullScreen(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getFullScreen(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a LabelProvider.
	 * supported by:
	 * <ul>
	 * <li>FilteredList</li>	 
	 * </ul>
	 * 
	 * @param value	 a LabelProvider.
	 */
	public SWTQuery setLabelProvider(ILabelProvider value){
		for(Widget each : items){
			WidgetPropertySwitch.setLabelProvider(each, value);
		}
		return this;
	}

	/**
	 * Gets a LabelProvider.
	 * supported by:
	 * <ul>
	 * <li>FilteredList</li>	 
	 * </ul>
	 * 
	 * @return value	 a LabelProvider.
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
	 * Sets a VisibleItemCount.
	 * supported by:
	 * <ul>
	 * <li>CCombo</li>
	 * <li>Combo</li>	 
	 * </ul>
	 * 
	 * @param value	 a VisibleItemCount.
	 */
	public SWTQuery setVisibleItemCount(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setVisibleItemCount(each, value);
		}
		return this;
	}

	/**
	 * Gets a VisibleItemCount.
	 * supported by:
	 * <ul>
	 * <li>CCombo</li>
	 * <li>Combo</li>	 
	 * </ul>
	 * 
	 * @return value	 a VisibleItemCount.
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
	 * Sets a Parent.
	 * supported by:
	 * <ul>
	 * <li>Control</li>	 
	 * </ul>
	 * 
	 * @param value	 a Parent.
	 */
	public SWTQuery setParent(Composite value){
		for(Widget each : items){
			WidgetPropertySwitch.setParent(each, value);
		}
		return this;
	}

	/**
	 * Gets a Parent.
	 * supported by:
	 * <ul>
	 * <li>Control</li>	 
	 * </ul>
	 * 
	 * @return value	 a Parent.
	 */	
	public Composite getParent(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getParent(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a Thumb.
	 * supported by:
	 * <ul>
	 * <li>Slider</li>
	 * <li>ScrollBar</li>	 
	 * </ul>
	 * 
	 * @param value	 a Thumb.
	 */
	public SWTQuery setThumb(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setThumb(each, value);
		}
		return this;
	}

	/**
	 * Gets a Thumb.
	 * supported by:
	 * <ul>
	 * <li>Slider</li>
	 * <li>ScrollBar</li>	 
	 * </ul>
	 * 
	 * @return value	 a Thumb.
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
	 * Sets a WordWrap.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>	 
	 * </ul>
	 * 
	 * @param value	 a WordWrap.
	 */
	public SWTQuery setWordWrap(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setWordWrap(each, value);
		}
		return this;
	}

	/**
	 * Gets a WordWrap.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>	 
	 * </ul>
	 * 
	 * @return value	 a WordWrap.
	 */	
	public Boolean getWordWrap(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getWordWrap(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a Minimized.
	 * supported by:
	 * <ul>
	 * <li>Decorations</li>
	 * <li>CTabFolder</li>	 
	 * </ul>
	 * 
	 * @param value	 a Minimized.
	 */
	public SWTQuery setMinimized(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setMinimized(each, value);
		}
		return this;
	}

	/**
	 * Gets a Minimized.
	 * supported by:
	 * <ul>
	 * <li>Decorations</li>
	 * <li>CTabFolder</li>	 
	 * </ul>
	 * 
	 * @return value	 a Minimized.
	 */	
	public Boolean getMinimized(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getMinimized(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a CaretOffset.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>	 
	 * </ul>
	 * 
	 * @param value	 a CaretOffset.
	 */
	public SWTQuery setCaretOffset(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setCaretOffset(each, value);
		}
		return this;
	}

	/**
	 * Gets a CaretOffset.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>	 
	 * </ul>
	 * 
	 * @return value	 a CaretOffset.
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
	 * Sets a DecorationColor.
	 * supported by:
	 * <ul>
	 * <li>ToggleHyperlink</li>	 
	 * </ul>
	 * 
	 * @param value	 a DecorationColor.
	 */
	public SWTQuery setDecorationColor(Color value){
		for(Widget each : items){
			WidgetPropertySwitch.setDecorationColor(each, value);
		}
		return this;
	}

	/**
	 * Gets a DecorationColor.
	 * supported by:
	 * <ul>
	 * <li>ToggleHyperlink</li>	 
	 * </ul>
	 * 
	 * @return value	 a DecorationColor.
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
	 * Sets a Bounds.
	 * supported by:
	 * <ul>
	 * <li>Caret</li>
	 * <li>Shell</li>
	 * <li>Decorations</li>
	 * <li>Control</li>	 
	 * </ul>
	 * 
	 * @param value	 a Bounds.
	 */
	public SWTQuery setBounds(Rectangle value){
		for(Widget each : items){
			WidgetPropertySwitch.setBounds(each, value);
		}
		return this;
	}

	/**
	 * Gets a Bounds.
	 * supported by:
	 * <ul>
	 * <li>Caret</li>
	 * <li>Shell</li>
	 * <li>Decorations</li>
	 * <li>Control</li>	 
	 * </ul>
	 * 
	 * @return value	 a Bounds.
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
	 * Sets a Digits.
	 * supported by:
	 * <ul>
	 * <li>Spinner</li>	 
	 * </ul>
	 * 
	 * @param value	 a Digits.
	 */
	public SWTQuery setDigits(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setDigits(each, value);
		}
		return this;
	}

	/**
	 * Gets a Digits.
	 * supported by:
	 * <ul>
	 * <li>Spinner</li>	 
	 * </ul>
	 * 
	 * @return value	 a Digits.
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
	 * Sets a IgnoreCase.
	 * supported by:
	 * <ul>
	 * <li>FilteredList</li>	 
	 * </ul>
	 * 
	 * @param value	 a IgnoreCase.
	 */
	public SWTQuery setIgnoreCase(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setIgnoreCase(each, value);
		}
		return this;
	}

	/**
	 * Gets a IgnoreCase.
	 * supported by:
	 * <ul>
	 * <li>FilteredList</li>	 
	 * </ul>
	 * 
	 * @return value	 a IgnoreCase.
	 */	
	public Boolean getIgnoreCase(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getIgnoreCase(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a Left.
	 * supported by:
	 * <ul>
	 * <li>CBanner</li>	 
	 * </ul>
	 * 
	 * @param value	 a Left.
	 */
	public SWTQuery setLeft(Control value){
		for(Widget each : items){
			WidgetPropertySwitch.setLeft(each, value);
		}
		return this;
	}

	/**
	 * Gets a Left.
	 * supported by:
	 * <ul>
	 * <li>CBanner</li>	 
	 * </ul>
	 * 
	 * @return value	 a Left.
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
	 * Sets a Bottom.
	 * supported by:
	 * <ul>
	 * <li>CBanner</li>	 
	 * </ul>
	 * 
	 * @param value	 a Bottom.
	 */
	public SWTQuery setBottom(Control value){
		for(Widget each : items){
			WidgetPropertySwitch.setBottom(each, value);
		}
		return this;
	}

	/**
	 * Gets a Bottom.
	 * supported by:
	 * <ul>
	 * <li>CBanner</li>	 
	 * </ul>
	 * 
	 * @return value	 a Bottom.
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
	 * Sets a Filter.
	 * supported by:
	 * <ul>
	 * <li>FilteredList</li>	 
	 * </ul>
	 * 
	 * @param value	 a Filter.
	 */
	public SWTQuery setFilter(String value){
		for(Widget each : items){
			WidgetPropertySwitch.setFilter(each, value);
		}
		return this;
	}

	/**
	 * Gets a Filter.
	 * supported by:
	 * <ul>
	 * <li>FilteredList</li>	 
	 * </ul>
	 * 
	 * @return value	 a Filter.
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
	 * Sets a AllowDuplicates.
	 * supported by:
	 * <ul>
	 * <li>FilteredList</li>	 
	 * </ul>
	 * 
	 * @param value	 a AllowDuplicates.
	 */
	public SWTQuery setAllowDuplicates(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setAllowDuplicates(each, value);
		}
		return this;
	}

	/**
	 * Gets a AllowDuplicates.
	 * supported by:
	 * <ul>
	 * <li>FilteredList</li>	 
	 * </ul>
	 * 
	 * @return value	 a AllowDuplicates.
	 */	
	public Boolean getAllowDuplicates(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getAllowDuplicates(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a Layout.
	 * supported by:
	 * <ul>
	 * <li>Composite</li>	 
	 * </ul>
	 * 
	 * @param value	 a Layout.
	 */
	public SWTQuery setLayout(Layout value){
		for(Widget each : items){
			WidgetPropertySwitch.setLayout(each, value);
		}
		return this;
	}

	/**
	 * Gets a Layout.
	 * supported by:
	 * <ul>
	 * <li>Composite</li>	 
	 * </ul>
	 * 
	 * @return value	 a Layout.
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
	 * Sets a BackgroundImage.
	 * supported by:
	 * <ul>
	 * <li>ScrolledForm</li>
	 * <li>Form</li>
	 * <li>Control</li>	 
	 * </ul>
	 * 
	 * @param value	 a BackgroundImage.
	 */
	public SWTQuery setBackgroundImage(Image value){
		for(Widget each : items){
			WidgetPropertySwitch.setBackgroundImage(each, value);
		}
		return this;
	}

	/**
	 * Gets a BackgroundImage.
	 * supported by:
	 * <ul>
	 * <li>ScrolledForm</li>
	 * <li>Form</li>
	 * <li>Control</li>	 
	 * </ul>
	 * 
	 * @return value	 a BackgroundImage.
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
	 * Sets a TabPosition.
	 * supported by:
	 * <ul>
	 * <li>CTabFolder</li>	 
	 * </ul>
	 * 
	 * @param value	 a TabPosition.
	 */
	public SWTQuery setTabPosition(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setTabPosition(each, value);
		}
		return this;
	}

	/**
	 * Gets a TabPosition.
	 * supported by:
	 * <ul>
	 * <li>CTabFolder</li>	 
	 * </ul>
	 * 
	 * @return value	 a TabPosition.
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
	 * Sets a TitleBarBackground.
	 * supported by:
	 * <ul>
	 * <li>Section</li>	 
	 * </ul>
	 * 
	 * @param value	 a TitleBarBackground.
	 */
	public SWTQuery setTitleBarBackground(Color value){
		for(Widget each : items){
			WidgetPropertySwitch.setTitleBarBackground(each, value);
		}
		return this;
	}

	/**
	 * Gets a TitleBarBackground.
	 * supported by:
	 * <ul>
	 * <li>Section</li>	 
	 * </ul>
	 * 
	 * @return value	 a TitleBarBackground.
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
	 * Sets a TextChars.
	 * supported by:
	 * <ul>
	 * <li>Text</li>	 
	 * </ul>
	 * 
	 * @param value	 a TextChars.
	 */
	public SWTQuery setTextChars(char[] value){
		for(Widget each : items){
			WidgetPropertySwitch.setTextChars(each, value);
		}
		return this;
	}

	/**
	 * Gets a TextChars.
	 * supported by:
	 * <ul>
	 * <li>Text</li>	 
	 * </ul>
	 * 
	 * @return value	 a TextChars.
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
	 * Sets a IME.
	 * supported by:
	 * <ul>
	 * <li>Canvas</li>	 
	 * </ul>
	 * 
	 * @param value	 a IME.
	 */
	public SWTQuery setIME(IME value){
		for(Widget each : items){
			WidgetPropertySwitch.setIME(each, value);
		}
		return this;
	}

	/**
	 * Gets a IME.
	 * supported by:
	 * <ul>
	 * <li>Canvas</li>	 
	 * </ul>
	 * 
	 * @return value	 a IME.
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
	 * Sets a TabHeight.
	 * supported by:
	 * <ul>
	 * <li>CTabFolder</li>	 
	 * </ul>
	 * 
	 * @param value	 a TabHeight.
	 */
	public SWTQuery setTabHeight(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setTabHeight(each, value);
		}
		return this;
	}

	/**
	 * Gets a TabHeight.
	 * supported by:
	 * <ul>
	 * <li>CTabFolder</li>	 
	 * </ul>
	 * 
	 * @return value	 a TabHeight.
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
	 * Sets a MaximizeVisible.
	 * supported by:
	 * <ul>
	 * <li>CTabFolder</li>	 
	 * </ul>
	 * 
	 * @param value	 a MaximizeVisible.
	 */
	public SWTQuery setMaximizeVisible(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setMaximizeVisible(each, value);
		}
		return this;
	}

	/**
	 * Gets a MaximizeVisible.
	 * supported by:
	 * <ul>
	 * <li>CTabFolder</li>	 
	 * </ul>
	 * 
	 * @return value	 a MaximizeVisible.
	 */	
	public Boolean getMaximizeVisible(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getMaximizeVisible(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a DragDetect.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>
	 * <li>Control</li>	 
	 * </ul>
	 * 
	 * @param value	 a DragDetect.
	 */
	public SWTQuery setDragDetect(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setDragDetect(each, value);
		}
		return this;
	}

	/**
	 * Gets a DragDetect.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>
	 * <li>Control</li>	 
	 * </ul>
	 * 
	 * @return value	 a DragDetect.
	 */	
	public Boolean getDragDetect(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getDragDetect(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a Alpha.
	 * supported by:
	 * <ul>
	 * <li>Shell</li>	 
	 * </ul>
	 * 
	 * @param value	 a Alpha.
	 */
	public SWTQuery setAlpha(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setAlpha(each, value);
		}
		return this;
	}

	/**
	 * Gets a Alpha.
	 * supported by:
	 * <ul>
	 * <li>Shell</li>	 
	 * </ul>
	 * 
	 * @return value	 a Alpha.
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
	 * Sets a JavascriptEnabled.
	 * supported by:
	 * <ul>
	 * <li>Browser</li>	 
	 * </ul>
	 * 
	 * @param value	 a JavascriptEnabled.
	 */
	public SWTQuery setJavascriptEnabled(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setJavascriptEnabled(each, value);
		}
		return this;
	}

	/**
	 * Gets a JavascriptEnabled.
	 * supported by:
	 * <ul>
	 * <li>Browser</li>	 
	 * </ul>
	 * 
	 * @return value	 a JavascriptEnabled.
	 */	
	public Boolean getJavascriptEnabled(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getJavascriptEnabled(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a PreferredSize.
	 * supported by:
	 * <ul>
	 * <li>CoolItem</li>	 
	 * </ul>
	 * 
	 * @param value	 a PreferredSize.
	 */
	public SWTQuery setPreferredSize(Point value){
		for(Widget each : items){
			WidgetPropertySwitch.setPreferredSize(each, value);
		}
		return this;
	}

	/**
	 * Gets a PreferredSize.
	 * supported by:
	 * <ul>
	 * <li>CoolItem</li>	 
	 * </ul>
	 * 
	 * @return value	 a PreferredSize.
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
	 * Sets a Background.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>
	 * <li>TableCursor</li>
	 * <li>Control</li>
	 * <li>TableItem</li>
	 * <li>TreeItem</li>	 
	 * </ul>
	 * 
	 * @param value	 a Background.
	 */
	public SWTQuery setBackground(Color value){
		for(Widget each : items){
			WidgetPropertySwitch.setBackground(each, value);
		}
		return this;
	}

	/**
	 * Gets a Background.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>
	 * <li>TableCursor</li>
	 * <li>Control</li>
	 * <li>TableItem</li>
	 * <li>TreeItem</li>	 
	 * </ul>
	 * 
	 * @return value	 a Background.
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
	 * Sets a TopMargin.
	 * supported by:
	 * <ul>
	 * <li>CLabel</li>
	 * <li>StyledText</li>	 
	 * </ul>
	 * 
	 * @param value	 a TopMargin.
	 */
	public SWTQuery setTopMargin(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setTopMargin(each, value);
		}
		return this;
	}

	/**
	 * Gets a TopMargin.
	 * supported by:
	 * <ul>
	 * <li>CLabel</li>
	 * <li>StyledText</li>	 
	 * </ul>
	 * 
	 * @return value	 a TopMargin.
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
	 * Sets a Weights.
	 * supported by:
	 * <ul>
	 * <li>SashForm</li>	 
	 * </ul>
	 * 
	 * @param value	 a Weights.
	 */
	public SWTQuery setWeights(int[] value){
		for(Widget each : items){
			WidgetPropertySwitch.setWeights(each, value);
		}
		return this;
	}

	/**
	 * Gets a Weights.
	 * supported by:
	 * <ul>
	 * <li>SashForm</li>	 
	 * </ul>
	 * 
	 * @return value	 a Weights.
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
	 * Sets a DoubleClickEnabled.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>
	 * <li>Text</li>	 
	 * </ul>
	 * 
	 * @param value	 a DoubleClickEnabled.
	 */
	public SWTQuery setDoubleClickEnabled(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setDoubleClickEnabled(each, value);
		}
		return this;
	}

	/**
	 * Gets a DoubleClickEnabled.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>
	 * <li>Text</li>	 
	 * </ul>
	 * 
	 * @return value	 a DoubleClickEnabled.
	 */	
	public Boolean getDoubleClickEnabled(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getDoubleClickEnabled(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a LayoutData.
	 * supported by:
	 * <ul>
	 * <li>Control</li>	 
	 * </ul>
	 * 
	 * @param value	 a LayoutData.
	 */
	public SWTQuery setLayoutData(Object value){
		for(Widget each : items){
			WidgetPropertySwitch.setLayoutData(each, value);
		}
		return this;
	}

	/**
	 * Gets a LayoutData.
	 * supported by:
	 * <ul>
	 * <li>Control</li>	 
	 * </ul>
	 * 
	 * @return value	 a LayoutData.
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
	 * Sets a StyleRanges.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>	 
	 * </ul>
	 * 
	 * @param value	 a StyleRanges.
	 */
	public SWTQuery setStyleRanges(StyleRange[] value){
		for(Widget each : items){
			WidgetPropertySwitch.setStyleRanges(each, value);
		}
		return this;
	}

	/**
	 * Gets a StyleRanges.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>	 
	 * </ul>
	 * 
	 * @return value	 a StyleRanges.
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
	 * Sets a Control.
	 * supported by:
	 * <ul>
	 * <li>CTabItem</li>
	 * <li>CoolItem</li>
	 * <li>ExpandItem</li>
	 * <li>TabItem</li>
	 * <li>ToolItem</li>	 
	 * </ul>
	 * 
	 * @param value	 a Control.
	 */
	public SWTQuery setControl(Control value){
		for(Widget each : items){
			WidgetPropertySwitch.setControl(each, value);
		}
		return this;
	}

	/**
	 * Gets a Control.
	 * supported by:
	 * <ul>
	 * <li>CTabItem</li>
	 * <li>CoolItem</li>
	 * <li>ExpandItem</li>
	 * <li>TabItem</li>
	 * <li>ToolItem</li>	 
	 * </ul>
	 * 
	 * @return value	 a Control.
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
	 * Sets a SashWidth.
	 * supported by:
	 * <ul>
	 * <li>SashForm</li>	 
	 * </ul>
	 * 
	 * @param value	 a SashWidth.
	 */
	public SWTQuery setSashWidth(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setSashWidth(each, value);
		}
		return this;
	}

	/**
	 * Gets a SashWidth.
	 * supported by:
	 * <ul>
	 * <li>SashForm</li>	 
	 * </ul>
	 * 
	 * @return value	 a SashWidth.
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
	 * Sets a Renderer.
	 * supported by:
	 * <ul>
	 * <li>CTabFolder</li>	 
	 * </ul>
	 * 
	 * @param value	 a Renderer.
	 */
	public SWTQuery setRenderer(CTabFolderRenderer value){
		for(Widget each : items){
			WidgetPropertySwitch.setRenderer(each, value);
		}
		return this;
	}

	/**
	 * Gets a Renderer.
	 * supported by:
	 * <ul>
	 * <li>CTabFolder</li>	 
	 * </ul>
	 * 
	 * @return value	 a Renderer.
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
	 * Sets a Font.
	 * supported by:
	 * <ul>
	 * <li>Caret</li>
	 * <li>Control</li>
	 * <li>CTabItem</li>
	 * <li>TableItem</li>
	 * <li>TreeItem</li>	 
	 * </ul>
	 * 
	 * @param value	 a Font.
	 */
	public SWTQuery setFont(Font value){
		for(Widget each : items){
			WidgetPropertySwitch.setFont(each, value);
		}
		return this;
	}

	/**
	 * Gets a Font.
	 * supported by:
	 * <ul>
	 * <li>Caret</li>
	 * <li>Control</li>
	 * <li>CTabItem</li>
	 * <li>TableItem</li>
	 * <li>TreeItem</li>	 
	 * </ul>
	 * 
	 * @return value	 a Font.
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
	 * Sets a Image.
	 * supported by:
	 * <ul>
	 * <li>Caret</li>
	 * <li>ImageHyperlink</li>
	 * <li>ScrolledForm</li>
	 * <li>CLabel</li>
	 * <li>Decorations</li>
	 * <li>Form</li>
	 * <li>Button</li>
	 * <li>Label</li>
	 * <li>TableItem</li>
	 * <li>TreeItem</li>
	 * <li>Item</li>	 
	 * </ul>
	 * 
	 * @param value	 a Image.
	 */
	public SWTQuery setImage(Image value){
		for(Widget each : items){
			WidgetPropertySwitch.setImage(each, value);
		}
		return this;
	}

	/**
	 * Gets a Image.
	 * supported by:
	 * <ul>
	 * <li>Caret</li>
	 * <li>ImageHyperlink</li>
	 * <li>ScrolledForm</li>
	 * <li>CLabel</li>
	 * <li>Decorations</li>
	 * <li>Form</li>
	 * <li>Button</li>
	 * <li>Label</li>
	 * <li>TableItem</li>
	 * <li>TreeItem</li>
	 * <li>Item</li>	 
	 * </ul>
	 * 
	 * @return value	 a Image.
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
	 * Sets a RightMinimumSize.
	 * supported by:
	 * <ul>
	 * <li>CBanner</li>	 
	 * </ul>
	 * 
	 * @param value	 a RightMinimumSize.
	 */
	public SWTQuery setRightMinimumSize(Point value){
		for(Widget each : items){
			WidgetPropertySwitch.setRightMinimumSize(each, value);
		}
		return this;
	}

	/**
	 * Gets a RightMinimumSize.
	 * supported by:
	 * <ul>
	 * <li>CBanner</li>	 
	 * </ul>
	 * 
	 * @return value	 a RightMinimumSize.
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
	 * Sets a Expanded.
	 * supported by:
	 * <ul>
	 * <li>ExpandItem</li>
	 * <li>TreeItem</li>	 
	 * </ul>
	 * 
	 * @param value	 a Expanded.
	 */
	public SWTQuery setExpanded(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setExpanded(each, value);
		}
		return this;
	}

	/**
	 * Gets a Expanded.
	 * supported by:
	 * <ul>
	 * <li>ExpandItem</li>
	 * <li>TreeItem</li>	 
	 * </ul>
	 * 
	 * @return value	 a Expanded.
	 */	
	public Boolean getExpanded(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getExpanded(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a Client.
	 * supported by:
	 * <ul>
	 * <li>ExpandableComposite</li>	 
	 * </ul>
	 * 
	 * @param value	 a Client.
	 */
	public SWTQuery setClient(Control value){
		for(Widget each : items){
			WidgetPropertySwitch.setClient(each, value);
		}
		return this;
	}

	/**
	 * Gets a Client.
	 * supported by:
	 * <ul>
	 * <li>ExpandableComposite</li>	 
	 * </ul>
	 * 
	 * @return value	 a Client.
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
	 * Sets a TitleBarForeground.
	 * supported by:
	 * <ul>
	 * <li>ExpandableComposite</li>	 
	 * </ul>
	 * 
	 * @param value	 a TitleBarForeground.
	 */
	public SWTQuery setTitleBarForeground(Color value){
		for(Widget each : items){
			WidgetPropertySwitch.setTitleBarForeground(each, value);
		}
		return this;
	}

	/**
	 * Gets a TitleBarForeground.
	 * supported by:
	 * <ul>
	 * <li>ExpandableComposite</li>	 
	 * </ul>
	 * 
	 * @return value	 a TitleBarForeground.
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
	 * Sets a Rectangles.
	 * supported by:
	 * <ul>
	 * <li>Tracker</li>	 
	 * </ul>
	 * 
	 * @param value	 a Rectangles.
	 */
	public SWTQuery setRectangles(Rectangle[] value){
		for(Widget each : items){
			WidgetPropertySwitch.setRectangles(each, value);
		}
		return this;
	}

	/**
	 * Gets a Rectangles.
	 * supported by:
	 * <ul>
	 * <li>Tracker</li>	 
	 * </ul>
	 * 
	 * @return value	 a Rectangles.
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
	 * Sets a Right.
	 * supported by:
	 * <ul>
	 * <li>CBanner</li>	 
	 * </ul>
	 * 
	 * @param value	 a Right.
	 */
	public SWTQuery setRight(Control value){
		for(Widget each : items){
			WidgetPropertySwitch.setRight(each, value);
		}
		return this;
	}

	/**
	 * Gets a Right.
	 * supported by:
	 * <ul>
	 * <li>CBanner</li>	 
	 * </ul>
	 * 
	 * @return value	 a Right.
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
	 * Sets a Items.
	 * supported by:
	 * <ul>
	 * <li>CCombo</li>
	 * <li>Combo</li>
	 * <li>List</li>	 
	 * </ul>
	 * 
	 * @param value	 a Items.
	 */
	public SWTQuery setItems(String[] value){
		for(Widget each : items){
			WidgetPropertySwitch.setItems(each, value);
		}
		return this;
	}

	/**
	 * Gets a Items.
	 * supported by:
	 * <ul>
	 * <li>CCombo</li>
	 * <li>Combo</li>
	 * <li>List</li>	 
	 * </ul>
	 * 
	 * @return value	 a Items.
	 */	
	public String[] getItems(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getItems(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a DefaultItem.
	 * supported by:
	 * <ul>
	 * <li>Menu</li>	 
	 * </ul>
	 * 
	 * @param value	 a DefaultItem.
	 */
	public SWTQuery setDefaultItem(MenuItem value){
		for(Widget each : items){
			WidgetPropertySwitch.setDefaultItem(each, value);
		}
		return this;
	}

	/**
	 * Gets a DefaultItem.
	 * supported by:
	 * <ul>
	 * <li>Menu</li>	 
	 * </ul>
	 * 
	 * @return value	 a DefaultItem.
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
	 * Sets a Orientation.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>
	 * <li>SashForm</li>
	 * <li>Combo</li>
	 * <li>Text</li>
	 * <li>Control</li>
	 * <li>Menu</li>	 
	 * </ul>
	 * 
	 * @param value	 a Orientation.
	 */
	public SWTQuery setOrientation(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setOrientation(each, value);
		}
		return this;
	}

	/**
	 * Gets a Orientation.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>
	 * <li>SashForm</li>
	 * <li>Combo</li>
	 * <li>Text</li>
	 * <li>Control</li>
	 * <li>Menu</li>	 
	 * </ul>
	 * 
	 * @return value	 a Orientation.
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
	 * Sets a Message.
	 * supported by:
	 * <ul>
	 * <li>Form</li>
	 * <li>Text</li>
	 * <li>ToolTip</li>	 
	 * </ul>
	 * 
	 * @param value	 a Message.
	 */
	public SWTQuery setMessage(String value){
		for(Widget each : items){
			WidgetPropertySwitch.setMessage(each, value);
		}
		return this;
	}

	/**
	 * Gets a Message.
	 * supported by:
	 * <ul>
	 * <li>Form</li>
	 * <li>Text</li>
	 * <li>ToolTip</li>	 
	 * </ul>
	 * 
	 * @return value	 a Message.
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
	 * Sets a UnselectedCloseVisible.
	 * supported by:
	 * <ul>
	 * <li>CTabFolder</li>	 
	 * </ul>
	 * 
	 * @param value	 a UnselectedCloseVisible.
	 */
	public SWTQuery setUnselectedCloseVisible(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setUnselectedCloseVisible(each, value);
		}
		return this;
	}

	/**
	 * Gets a UnselectedCloseVisible.
	 * supported by:
	 * <ul>
	 * <li>CTabFolder</li>	 
	 * </ul>
	 * 
	 * @return value	 a UnselectedCloseVisible.
	 */	
	public Boolean getUnselectedCloseVisible(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getUnselectedCloseVisible(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a LeftMargin.
	 * supported by:
	 * <ul>
	 * <li>CLabel</li>
	 * <li>StyledText</li>	 
	 * </ul>
	 * 
	 * @param value	 a LeftMargin.
	 */
	public SWTQuery setLeftMargin(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setLeftMargin(each, value);
		}
		return this;
	}

	/**
	 * Gets a LeftMargin.
	 * supported by:
	 * <ul>
	 * <li>CLabel</li>
	 * <li>StyledText</li>	 
	 * </ul>
	 * 
	 * @return value	 a LeftMargin.
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
	 * Sets a Hours.
	 * supported by:
	 * <ul>
	 * <li>DateTime</li>	 
	 * </ul>
	 * 
	 * @param value	 a Hours.
	 */
	public SWTQuery setHours(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setHours(each, value);
		}
		return this;
	}

	/**
	 * Gets a Hours.
	 * supported by:
	 * <ul>
	 * <li>DateTime</li>	 
	 * </ul>
	 * 
	 * @return value	 a Hours.
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
	 * Sets a HoverImage.
	 * supported by:
	 * <ul>
	 * <li>ImageHyperlink</li>	 
	 * </ul>
	 * 
	 * @param value	 a HoverImage.
	 */
	public SWTQuery setHoverImage(Image value){
		for(Widget each : items){
			WidgetPropertySwitch.setHoverImage(each, value);
		}
		return this;
	}

	/**
	 * Gets a HoverImage.
	 * supported by:
	 * <ul>
	 * <li>ImageHyperlink</li>	 
	 * </ul>
	 * 
	 * @return value	 a HoverImage.
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
	 * Sets a LinesVisible.
	 * supported by:
	 * <ul>
	 * <li>Table</li>
	 * <li>Tree</li>	 
	 * </ul>
	 * 
	 * @param value	 a LinesVisible.
	 */
	public SWTQuery setLinesVisible(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setLinesVisible(each, value);
		}
		return this;
	}

	/**
	 * Gets a LinesVisible.
	 * supported by:
	 * <ul>
	 * <li>Table</li>
	 * <li>Tree</li>	 
	 * </ul>
	 * 
	 * @return value	 a LinesVisible.
	 */	
	public Boolean getLinesVisible(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getLinesVisible(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a DefaultButton.
	 * supported by:
	 * <ul>
	 * <li>Decorations</li>	 
	 * </ul>
	 * 
	 * @param value	 a DefaultButton.
	 */
	public SWTQuery setDefaultButton(Button value){
		for(Widget each : items){
			WidgetPropertySwitch.setDefaultButton(each, value);
		}
		return this;
	}

	/**
	 * Gets a DefaultButton.
	 * supported by:
	 * <ul>
	 * <li>Decorations</li>	 
	 * </ul>
	 * 
	 * @return value	 a DefaultButton.
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
	 * Sets a BottomMargin.
	 * supported by:
	 * <ul>
	 * <li>CLabel</li>
	 * <li>StyledText</li>	 
	 * </ul>
	 * 
	 * @param value	 a BottomMargin.
	 */
	public SWTQuery setBottomMargin(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setBottomMargin(each, value);
		}
		return this;
	}

	/**
	 * Gets a BottomMargin.
	 * supported by:
	 * <ul>
	 * <li>CLabel</li>
	 * <li>StyledText</li>	 
	 * </ul>
	 * 
	 * @return value	 a BottomMargin.
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
	 * Sets a Minimum.
	 * supported by:
	 * <ul>
	 * <li>Spinner</li>
	 * <li>ProgressBar</li>
	 * <li>Scale</li>
	 * <li>Slider</li>
	 * <li>ScrollBar</li>	 
	 * </ul>
	 * 
	 * @param value	 a Minimum.
	 */
	public SWTQuery setMinimum(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setMinimum(each, value);
		}
		return this;
	}

	/**
	 * Gets a Minimum.
	 * supported by:
	 * <ul>
	 * <li>Spinner</li>
	 * <li>ProgressBar</li>
	 * <li>Scale</li>
	 * <li>Slider</li>
	 * <li>ScrollBar</li>	 
	 * </ul>
	 * 
	 * @return value	 a Minimum.
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
	 * Sets a Alignment.
	 * supported by:
	 * <ul>
	 * <li>CLabel</li>
	 * <li>StyledText</li>
	 * <li>Button</li>
	 * <li>Label</li>
	 * <li>TableColumn</li>
	 * <li>TreeColumn</li>	 
	 * </ul>
	 * 
	 * @param value	 a Alignment.
	 */
	public SWTQuery setAlignment(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setAlignment(each, value);
		}
		return this;
	}

	/**
	 * Gets a Alignment.
	 * supported by:
	 * <ul>
	 * <li>CLabel</li>
	 * <li>StyledText</li>
	 * <li>Button</li>
	 * <li>Label</li>
	 * <li>TableColumn</li>
	 * <li>TreeColumn</li>	 
	 * </ul>
	 * 
	 * @return value	 a Alignment.
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
	 * Sets a AlwaysShowScrollBars.
	 * supported by:
	 * <ul>
	 * <li>ScrolledComposite</li>	 
	 * </ul>
	 * 
	 * @param value	 a AlwaysShowScrollBars.
	 */
	public SWTQuery setAlwaysShowScrollBars(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setAlwaysShowScrollBars(each, value);
		}
		return this;
	}

	/**
	 * Gets a AlwaysShowScrollBars.
	 * supported by:
	 * <ul>
	 * <li>ScrolledComposite</li>	 
	 * </ul>
	 * 
	 * @return value	 a AlwaysShowScrollBars.
	 */	
	public Boolean getAlwaysShowScrollBars(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getAlwaysShowScrollBars(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a TopLeft.
	 * supported by:
	 * <ul>
	 * <li>ViewForm</li>	 
	 * </ul>
	 * 
	 * @param value	 a TopLeft.
	 */
	public SWTQuery setTopLeft(Control value){
		for(Widget each : items){
			WidgetPropertySwitch.setTopLeft(each, value);
		}
		return this;
	}

	/**
	 * Gets a TopLeft.
	 * supported by:
	 * <ul>
	 * <li>ViewForm</li>	 
	 * </ul>
	 * 
	 * @return value	 a TopLeft.
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
	 * Sets a Indent.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>	 
	 * </ul>
	 * 
	 * @param value	 a Indent.
	 */
	public SWTQuery setIndent(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setIndent(each, value);
		}
		return this;
	}

	/**
	 * Gets a Indent.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>	 
	 * </ul>
	 * 
	 * @return value	 a Indent.
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
	 * Sets a ImeInputMode.
	 * supported by:
	 * <ul>
	 * <li>Shell</li>	 
	 * </ul>
	 * 
	 * @param value	 a ImeInputMode.
	 */
	public SWTQuery setImeInputMode(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setImeInputMode(each, value);
		}
		return this;
	}

	/**
	 * Gets a ImeInputMode.
	 * supported by:
	 * <ul>
	 * <li>Shell</li>	 
	 * </ul>
	 * 
	 * @return value	 a ImeInputMode.
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
	 * Sets a TopIndex.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>
	 * <li>Table</li>
	 * <li>List</li>
	 * <li>Text</li>	 
	 * </ul>
	 * 
	 * @param value	 a TopIndex.
	 */
	public SWTQuery setTopIndex(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setTopIndex(each, value);
		}
		return this;
	}

	/**
	 * Gets a TopIndex.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>
	 * <li>Table</li>
	 * <li>List</li>
	 * <li>Text</li>	 
	 * </ul>
	 * 
	 * @return value	 a TopIndex.
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
	 * Sets a RightWidth.
	 * supported by:
	 * <ul>
	 * <li>CBanner</li>	 
	 * </ul>
	 * 
	 * @param value	 a RightWidth.
	 */
	public SWTQuery setRightWidth(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setRightWidth(each, value);
		}
		return this;
	}

	/**
	 * Gets a RightWidth.
	 * supported by:
	 * <ul>
	 * <li>CBanner</li>	 
	 * </ul>
	 * 
	 * @return value	 a RightWidth.
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
	 * Sets a LayoutDeferred.
	 * supported by:
	 * <ul>
	 * <li>Composite</li>	 
	 * </ul>
	 * 
	 * @param value	 a LayoutDeferred.
	 */
	public SWTQuery setLayoutDeferred(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setLayoutDeferred(each, value);
		}
		return this;
	}

	/**
	 * Gets a LayoutDeferred.
	 * supported by:
	 * <ul>
	 * <li>Composite</li>	 
	 * </ul>
	 * 
	 * @return value	 a LayoutDeferred.
	 */	
	public Boolean getLayoutDeferred(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getLayoutDeferred(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a SeparatorControl.
	 * supported by:
	 * <ul>
	 * <li>Section</li>	 
	 * </ul>
	 * 
	 * @param value	 a SeparatorControl.
	 */
	public SWTQuery setSeparatorControl(Control value){
		for(Widget each : items){
			WidgetPropertySwitch.setSeparatorControl(each, value);
		}
		return this;
	}

	/**
	 * Gets a SeparatorControl.
	 * supported by:
	 * <ul>
	 * <li>Section</li>	 
	 * </ul>
	 * 
	 * @return value	 a SeparatorControl.
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
	 * Sets a Foreground.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>
	 * <li>TableCursor</li>
	 * <li>Control</li>
	 * <li>TableItem</li>
	 * <li>TreeItem</li>	 
	 * </ul>
	 * 
	 * @param value	 a Foreground.
	 */
	public SWTQuery setForeground(Color value){
		for(Widget each : items){
			WidgetPropertySwitch.setForeground(each, value);
		}
		return this;
	}

	/**
	 * Gets a Foreground.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>
	 * <li>TableCursor</li>
	 * <li>Control</li>
	 * <li>TableItem</li>
	 * <li>TreeItem</li>	 
	 * </ul>
	 * 
	 * @return value	 a Foreground.
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
	 * Sets a MaximizedControl.
	 * supported by:
	 * <ul>
	 * <li>SashForm</li>	 
	 * </ul>
	 * 
	 * @param value	 a MaximizedControl.
	 */
	public SWTQuery setMaximizedControl(Control value){
		for(Widget each : items){
			WidgetPropertySwitch.setMaximizedControl(each, value);
		}
		return this;
	}

	/**
	 * Gets a MaximizedControl.
	 * supported by:
	 * <ul>
	 * <li>SashForm</li>	 
	 * </ul>
	 * 
	 * @return value	 a MaximizedControl.
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
	 * Sets a ListVisible.
	 * supported by:
	 * <ul>
	 * <li>CCombo</li>
	 * <li>Combo</li>	 
	 * </ul>
	 * 
	 * @param value	 a ListVisible.
	 */
	public SWTQuery setListVisible(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setListVisible(each, value);
		}
		return this;
	}

	/**
	 * Gets a ListVisible.
	 * supported by:
	 * <ul>
	 * <li>CCombo</li>
	 * <li>Combo</li>	 
	 * </ul>
	 * 
	 * @return value	 a ListVisible.
	 */	
	public Boolean getListVisible(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getListVisible(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a HeaderVisible.
	 * supported by:
	 * <ul>
	 * <li>Table</li>
	 * <li>Tree</li>	 
	 * </ul>
	 * 
	 * @param value	 a HeaderVisible.
	 */
	public SWTQuery setHeaderVisible(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setHeaderVisible(each, value);
		}
		return this;
	}

	/**
	 * Gets a HeaderVisible.
	 * supported by:
	 * <ul>
	 * <li>Table</li>
	 * <li>Tree</li>	 
	 * </ul>
	 * 
	 * @return value	 a HeaderVisible.
	 */	
	public Boolean getHeaderVisible(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getHeaderVisible(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a TextClient.
	 * supported by:
	 * <ul>
	 * <li>ExpandableComposite</li>	 
	 * </ul>
	 * 
	 * @param value	 a TextClient.
	 */
	public SWTQuery setTextClient(Control value){
		for(Widget each : items){
			WidgetPropertySwitch.setTextClient(each, value);
		}
		return this;
	}

	/**
	 * Gets a TextClient.
	 * supported by:
	 * <ul>
	 * <li>ExpandableComposite</li>	 
	 * </ul>
	 * 
	 * @return value	 a TextClient.
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
	 * Sets a Month.
	 * supported by:
	 * <ul>
	 * <li>DateTime</li>	 
	 * </ul>
	 * 
	 * @param value	 a Month.
	 */
	public SWTQuery setMonth(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setMonth(each, value);
		}
		return this;
	}

	/**
	 * Gets a Month.
	 * supported by:
	 * <ul>
	 * <li>DateTime</li>	 
	 * </ul>
	 * 
	 * @return value	 a Month.
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
	 * Sets a BlockSelection.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>	 
	 * </ul>
	 * 
	 * @param value	 a BlockSelection.
	 */
	public SWTQuery setBlockSelection(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setBlockSelection(each, value);
		}
		return this;
	}

	/**
	 * Gets a BlockSelection.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>	 
	 * </ul>
	 * 
	 * @return value	 a BlockSelection.
	 */	
	public Boolean getBlockSelection(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getBlockSelection(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a Menu.
	 * supported by:
	 * <ul>
	 * <li>CCombo</li>
	 * <li>Control</li>
	 * <li>MenuItem</li>	 
	 * </ul>
	 * 
	 * @param value	 a Menu.
	 */
	public SWTQuery setMenu(Menu value){
		for(Widget each : items){
			WidgetPropertySwitch.setMenu(each, value);
		}
		return this;
	}

	/**
	 * Gets a Menu.
	 * supported by:
	 * <ul>
	 * <li>CCombo</li>
	 * <li>Control</li>
	 * <li>MenuItem</li>	 
	 * </ul>
	 * 
	 * @return value	 a Menu.
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
	 * Sets a MinimizeVisible.
	 * supported by:
	 * <ul>
	 * <li>CTabFolder</li>	 
	 * </ul>
	 * 
	 * @param value	 a MinimizeVisible.
	 */
	public SWTQuery setMinimizeVisible(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setMinimizeVisible(each, value);
		}
		return this;
	}

	/**
	 * Gets a MinimizeVisible.
	 * supported by:
	 * <ul>
	 * <li>CTabFolder</li>	 
	 * </ul>
	 * 
	 * @return value	 a MinimizeVisible.
	 */	
	public Boolean getMinimizeVisible(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getMinimizeVisible(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a LineSpacing.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>	 
	 * </ul>
	 * 
	 * @param value	 a LineSpacing.
	 */
	public SWTQuery setLineSpacing(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setLineSpacing(each, value);
		}
		return this;
	}

	/**
	 * Gets a LineSpacing.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>	 
	 * </ul>
	 * 
	 * @return value	 a LineSpacing.
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
	 * Sets a ID.
	 * supported by:
	 * <ul>
	 * <li>MenuItem</li>	 
	 * </ul>
	 * 
	 * @param value	 a ID.
	 */
	public SWTQuery setID(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setID(each, value);
		}
		return this;
	}

	/**
	 * Gets a ID.
	 * supported by:
	 * <ul>
	 * <li>MenuItem</li>	 
	 * </ul>
	 * 
	 * @return value	 a ID.
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
	 * Sets a Href.
	 * supported by:
	 * <ul>
	 * <li>AbstractHyperlink</li>	 
	 * </ul>
	 * 
	 * @param value	 a Href.
	 */
	public SWTQuery setHref(Object value){
		for(Widget each : items){
			WidgetPropertySwitch.setHref(each, value);
		}
		return this;
	}

	/**
	 * Gets a Href.
	 * supported by:
	 * <ul>
	 * <li>AbstractHyperlink</li>	 
	 * </ul>
	 * 
	 * @return value	 a Href.
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
	 * Sets a Resizable.
	 * supported by:
	 * <ul>
	 * <li>TableColumn</li>
	 * <li>TreeColumn</li>	 
	 * </ul>
	 * 
	 * @param value	 a Resizable.
	 */
	public SWTQuery setResizable(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setResizable(each, value);
		}
		return this;
	}

	/**
	 * Gets a Resizable.
	 * supported by:
	 * <ul>
	 * <li>TableColumn</li>
	 * <li>TreeColumn</li>	 
	 * </ul>
	 * 
	 * @return value	 a Resizable.
	 */	
	public Boolean getResizable(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getResizable(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a Width.
	 * supported by:
	 * <ul>
	 * <li>TableColumn</li>
	 * <li>ToolItem</li>
	 * <li>TreeColumn</li>	 
	 * </ul>
	 * 
	 * @param value	 a Width.
	 */
	public SWTQuery setWidth(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setWidth(each, value);
		}
		return this;
	}

	/**
	 * Gets a Width.
	 * supported by:
	 * <ul>
	 * <li>TableColumn</li>
	 * <li>ToolItem</li>
	 * <li>TreeColumn</li>	 
	 * </ul>
	 * 
	 * @return value	 a Width.
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
	 * Sets a Justify.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>	 
	 * </ul>
	 * 
	 * @param value	 a Justify.
	 */
	public SWTQuery setJustify(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setJustify(each, value);
		}
		return this;
	}

	/**
	 * Gets a Justify.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>	 
	 * </ul>
	 * 
	 * @return value	 a Justify.
	 */	
	public Boolean getJustify(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getJustify(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a ToolTip.
	 * supported by:
	 * <ul>
	 * <li>TrayItem</li>	 
	 * </ul>
	 * 
	 * @param value	 a ToolTip.
	 */
	public SWTQuery setToolTip(ToolTip value){
		for(Widget each : items){
			WidgetPropertySwitch.setToolTip(each, value);
		}
		return this;
	}

	/**
	 * Gets a ToolTip.
	 * supported by:
	 * <ul>
	 * <li>TrayItem</li>	 
	 * </ul>
	 * 
	 * @return value	 a ToolTip.
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
	 * Sets a HoverDecorationColor.
	 * supported by:
	 * <ul>
	 * <li>ToggleHyperlink</li>	 
	 * </ul>
	 * 
	 * @param value	 a HoverDecorationColor.
	 */
	public SWTQuery setHoverDecorationColor(Color value){
		for(Widget each : items){
			WidgetPropertySwitch.setHoverDecorationColor(each, value);
		}
		return this;
	}

	/**
	 * Gets a HoverDecorationColor.
	 * supported by:
	 * <ul>
	 * <li>ToggleHyperlink</li>	 
	 * </ul>
	 * 
	 * @return value	 a HoverDecorationColor.
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
	 * Sets a Size.
	 * supported by:
	 * <ul>
	 * <li>Caret</li>
	 * <li>Shell</li>
	 * <li>Decorations</li>
	 * <li>Control</li>
	 * <li>CoolItem</li>	 
	 * </ul>
	 * 
	 * @param value	 a Size.
	 */
	public SWTQuery setSize(Point value){
		for(Widget each : items){
			WidgetPropertySwitch.setSize(each, value);
		}
		return this;
	}

	/**
	 * Gets a Size.
	 * supported by:
	 * <ul>
	 * <li>Caret</li>
	 * <li>Shell</li>
	 * <li>Decorations</li>
	 * <li>Control</li>
	 * <li>CoolItem</li>	 
	 * </ul>
	 * 
	 * @return value	 a Size.
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
	 * Sets a Stippled.
	 * supported by:
	 * <ul>
	 * <li>Tracker</li>	 
	 * </ul>
	 * 
	 * @param value	 a Stippled.
	 */
	public SWTQuery setStippled(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setStippled(each, value);
		}
		return this;
	}

	/**
	 * Gets a Stippled.
	 * supported by:
	 * <ul>
	 * <li>Tracker</li>	 
	 * </ul>
	 * 
	 * @return value	 a Stippled.
	 */	
	public Boolean getStippled(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getStippled(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a SelectionBackground.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>
	 * <li>CTabFolder</li>	 
	 * </ul>
	 * 
	 * @param value	 a SelectionBackground.
	 */
	public SWTQuery setSelectionBackground(Color value){
		for(Widget each : items){
			WidgetPropertySwitch.setSelectionBackground(each, value);
		}
		return this;
	}

	/**
	 * Gets a SelectionBackground.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>
	 * <li>CTabFolder</li>	 
	 * </ul>
	 * 
	 * @return value	 a SelectionBackground.
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
	 * Sets a TextLimit.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>
	 * <li>CCombo</li>
	 * <li>Combo</li>
	 * <li>Spinner</li>
	 * <li>Text</li>	 
	 * </ul>
	 * 
	 * @param value	 a TextLimit.
	 */
	public SWTQuery setTextLimit(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setTextLimit(each, value);
		}
		return this;
	}

	/**
	 * Gets a TextLimit.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>
	 * <li>CCombo</li>
	 * <li>Combo</li>
	 * <li>Spinner</li>
	 * <li>Text</li>	 
	 * </ul>
	 * 
	 * @return value	 a TextLimit.
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
	 * Sets a Maximum.
	 * supported by:
	 * <ul>
	 * <li>Spinner</li>
	 * <li>ProgressBar</li>
	 * <li>Scale</li>
	 * <li>Slider</li>
	 * <li>ScrollBar</li>	 
	 * </ul>
	 * 
	 * @param value	 a Maximum.
	 */
	public SWTQuery setMaximum(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setMaximum(each, value);
		}
		return this;
	}

	/**
	 * Gets a Maximum.
	 * supported by:
	 * <ul>
	 * <li>Spinner</li>
	 * <li>ProgressBar</li>
	 * <li>Scale</li>
	 * <li>Slider</li>
	 * <li>ScrollBar</li>	 
	 * </ul>
	 * 
	 * @return value	 a Maximum.
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
	 * Sets a ItemCount.
	 * supported by:
	 * <ul>
	 * <li>Table</li>
	 * <li>Tree</li>
	 * <li>TreeItem</li>	 
	 * </ul>
	 * 
	 * @param value	 a ItemCount.
	 */
	public SWTQuery setItemCount(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setItemCount(each, value);
		}
		return this;
	}

	/**
	 * Gets a ItemCount.
	 * supported by:
	 * <ul>
	 * <li>Table</li>
	 * <li>Tree</li>
	 * <li>TreeItem</li>	 
	 * </ul>
	 * 
	 * @return value	 a ItemCount.
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
	 * Sets a Height.
	 * supported by:
	 * <ul>
	 * <li>ExpandItem</li>	 
	 * </ul>
	 * 
	 * @param value	 a Height.
	 */
	public SWTQuery setHeight(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setHeight(each, value);
		}
		return this;
	}

	/**
	 * Gets a Height.
	 * supported by:
	 * <ul>
	 * <li>ExpandItem</li>	 
	 * </ul>
	 * 
	 * @return value	 a Height.
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
	 * Sets a TopRight.
	 * supported by:
	 * <ul>
	 * <li>CTabFolder</li>
	 * <li>ViewForm</li>	 
	 * </ul>
	 * 
	 * @param value	 a TopRight.
	 */
	public SWTQuery setTopRight(Control value){
		for(Widget each : items){
			WidgetPropertySwitch.setTopRight(each, value);
		}
		return this;
	}

	/**
	 * Gets a TopRight.
	 * supported by:
	 * <ul>
	 * <li>CTabFolder</li>
	 * <li>ViewForm</li>	 
	 * </ul>
	 * 
	 * @return value	 a TopRight.
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
	 * Sets a HyperlinkSettings.
	 * supported by:
	 * <ul>
	 * <li>FormText</li>	 
	 * </ul>
	 * 
	 * @param value	 a HyperlinkSettings.
	 */
	public SWTQuery setHyperlinkSettings(HyperlinkSettings value){
		for(Widget each : items){
			WidgetPropertySwitch.setHyperlinkSettings(each, value);
		}
		return this;
	}

	/**
	 * Gets a HyperlinkSettings.
	 * supported by:
	 * <ul>
	 * <li>FormText</li>	 
	 * </ul>
	 * 
	 * @return value	 a HyperlinkSettings.
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
	 * Sets a MinWidth.
	 * supported by:
	 * <ul>
	 * <li>ScrolledComposite</li>	 
	 * </ul>
	 * 
	 * @param value	 a MinWidth.
	 */
	public SWTQuery setMinWidth(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setMinWidth(each, value);
		}
		return this;
	}

	/**
	 * Gets a MinWidth.
	 * supported by:
	 * <ul>
	 * <li>ScrolledComposite</li>	 
	 * </ul>
	 * 
	 * @return value	 a MinWidth.
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
	 * Sets a Transfer.
	 * supported by:
	 * <ul>
	 * <li>DragSource</li>
	 * <li>DropTarget</li>	 
	 * </ul>
	 * 
	 * @param value	 a Transfer.
	 */
	public SWTQuery setTransfer(Transfer[] value){
		for(Widget each : items){
			WidgetPropertySwitch.setTransfer(each, value);
		}
		return this;
	}

	/**
	 * Gets a Transfer.
	 * supported by:
	 * <ul>
	 * <li>DragSource</li>
	 * <li>DropTarget</li>	 
	 * </ul>
	 * 
	 * @return value	 a Transfer.
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
	 * Sets a ActiveImage.
	 * supported by:
	 * <ul>
	 * <li>ImageHyperlink</li>	 
	 * </ul>
	 * 
	 * @param value	 a ActiveImage.
	 */
	public SWTQuery setActiveImage(Image value){
		for(Widget each : items){
			WidgetPropertySwitch.setActiveImage(each, value);
		}
		return this;
	}

	/**
	 * Gets a ActiveImage.
	 * supported by:
	 * <ul>
	 * <li>ImageHyperlink</li>	 
	 * </ul>
	 * 
	 * @return value	 a ActiveImage.
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
	 * Sets a Editable.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>
	 * <li>CCombo</li>
	 * <li>Text</li>	 
	 * </ul>
	 * 
	 * @param value	 a Editable.
	 */
	public SWTQuery setEditable(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setEditable(each, value);
		}
		return this;
	}

	/**
	 * Gets a Editable.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>
	 * <li>CCombo</li>
	 * <li>Text</li>	 
	 * </ul>
	 * 
	 * @return value	 a Editable.
	 */	
	public Boolean getEditable(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getEditable(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a FormText.
	 * supported by:
	 * <ul>
	 * <li>ScrolledFormText</li>	 
	 * </ul>
	 * 
	 * @param value	 a FormText.
	 */
	public SWTQuery setFormText(FormText value){
		for(Widget each : items){
			WidgetPropertySwitch.setFormText(each, value);
		}
		return this;
	}

	/**
	 * Gets a FormText.
	 * supported by:
	 * <ul>
	 * <li>ScrolledFormText</li>	 
	 * </ul>
	 * 
	 * @return value	 a FormText.
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
	 * Sets a TouchEnabled.
	 * supported by:
	 * <ul>
	 * <li>Control</li>	 
	 * </ul>
	 * 
	 * @param value	 a TouchEnabled.
	 */
	public SWTQuery setTouchEnabled(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setTouchEnabled(each, value);
		}
		return this;
	}

	/**
	 * Gets a TouchEnabled.
	 * supported by:
	 * <ul>
	 * <li>Control</li>	 
	 * </ul>
	 * 
	 * @return value	 a TouchEnabled.
	 */	
	public Boolean getTouchEnabled(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getTouchEnabled(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a DropTargetEffect.
	 * supported by:
	 * <ul>
	 * <li>DropTarget</li>	 
	 * </ul>
	 * 
	 * @param value	 a DropTargetEffect.
	 */
	public SWTQuery setDropTargetEffect(DropTargetEffect value){
		for(Widget each : items){
			WidgetPropertySwitch.setDropTargetEffect(each, value);
		}
		return this;
	}

	/**
	 * Gets a DropTargetEffect.
	 * supported by:
	 * <ul>
	 * <li>DropTarget</li>	 
	 * </ul>
	 * 
	 * @return value	 a DropTargetEffect.
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
	 * Sets a Simple.
	 * supported by:
	 * <ul>
	 * <li>CBanner</li>
	 * <li>CTabFolder</li>	 
	 * </ul>
	 * 
	 * @param value	 a Simple.
	 */
	public SWTQuery setSimple(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setSimple(each, value);
		}
		return this;
	}

	/**
	 * Gets a Simple.
	 * supported by:
	 * <ul>
	 * <li>CBanner</li>
	 * <li>CTabFolder</li>	 
	 * </ul>
	 * 
	 * @return value	 a Simple.
	 */	
	public Boolean getSimple(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getSimple(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a MarginColor.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>	 
	 * </ul>
	 * 
	 * @param value	 a MarginColor.
	 */
	public SWTQuery setMarginColor(Color value){
		for(Widget each : items){
			WidgetPropertySwitch.setMarginColor(each, value);
		}
		return this;
	}

	/**
	 * Gets a MarginColor.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>	 
	 * </ul>
	 * 
	 * @return value	 a MarginColor.
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
	 * Sets a TopPixel.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>	 
	 * </ul>
	 * 
	 * @param value	 a TopPixel.
	 */
	public SWTQuery setTopPixel(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setTopPixel(each, value);
		}
		return this;
	}

	/**
	 * Gets a TopPixel.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>	 
	 * </ul>
	 * 
	 * @return value	 a TopPixel.
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
	 * Sets a Checked.
	 * supported by:
	 * <ul>
	 * <li>TableItem</li>
	 * <li>TreeItem</li>	 
	 * </ul>
	 * 
	 * @param value	 a Checked.
	 */
	public SWTQuery setChecked(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setChecked(each, value);
		}
		return this;
	}

	/**
	 * Gets a Checked.
	 * supported by:
	 * <ul>
	 * <li>TableItem</li>
	 * <li>TreeItem</li>	 
	 * </ul>
	 * 
	 * @return value	 a Checked.
	 */	
	public Boolean getChecked(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getChecked(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a TitleBarBorderColor.
	 * supported by:
	 * <ul>
	 * <li>Section</li>	 
	 * </ul>
	 * 
	 * @param value	 a TitleBarBorderColor.
	 */
	public SWTQuery setTitleBarBorderColor(Color value){
		for(Widget each : items){
			WidgetPropertySwitch.setTitleBarBorderColor(each, value);
		}
		return this;
	}

	/**
	 * Gets a TitleBarBorderColor.
	 * supported by:
	 * <ul>
	 * <li>Section</li>	 
	 * </ul>
	 * 
	 * @return value	 a TitleBarBorderColor.
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
	 * Sets a Locked.
	 * supported by:
	 * <ul>
	 * <li>CoolBar</li>	 
	 * </ul>
	 * 
	 * @param value	 a Locked.
	 */
	public SWTQuery setLocked(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setLocked(each, value);
		}
		return this;
	}

	/**
	 * Gets a Locked.
	 * supported by:
	 * <ul>
	 * <li>CoolBar</li>	 
	 * </ul>
	 * 
	 * @return value	 a Locked.
	 */	
	public Boolean getLocked(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getLocked(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a Caret.
	 * supported by:
	 * <ul>
	 * <li>Canvas</li>	 
	 * </ul>
	 * 
	 * @param value	 a Caret.
	 */
	public SWTQuery setCaret(Caret value){
		for(Widget each : items){
			WidgetPropertySwitch.setCaret(each, value);
		}
		return this;
	}

	/**
	 * Gets a Caret.
	 * supported by:
	 * <ul>
	 * <li>Canvas</li>	 
	 * </ul>
	 * 
	 * @return value	 a Caret.
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
	 * Sets a WrapIndent.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>	 
	 * </ul>
	 * 
	 * @param value	 a WrapIndent.
	 */
	public SWTQuery setWrapIndent(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setWrapIndent(each, value);
		}
		return this;
	}

	/**
	 * Gets a WrapIndent.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>	 
	 * </ul>
	 * 
	 * @return value	 a WrapIndent.
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
	 * Sets a AutoHide.
	 * supported by:
	 * <ul>
	 * <li>ToolTip</li>	 
	 * </ul>
	 * 
	 * @param value	 a AutoHide.
	 */
	public SWTQuery setAutoHide(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setAutoHide(each, value);
		}
		return this;
	}

	/**
	 * Gets a AutoHide.
	 * supported by:
	 * <ul>
	 * <li>ToolTip</li>	 
	 * </ul>
	 * 
	 * @return value	 a AutoHide.
	 */	
	public Boolean getAutoHide(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getAutoHide(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a Enabled.
	 * supported by:
	 * <ul>
	 * <li>Shell</li>
	 * <li>Slider</li>
	 * <li>Control</li>
	 * <li>MenuItem</li>
	 * <li>ToolItem</li>
	 * <li>Menu</li>
	 * <li>ScrollBar</li>	 
	 * </ul>
	 * 
	 * @param value	 a Enabled.
	 */
	public SWTQuery setEnabled(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setEnabled(each, value);
		}
		return this;
	}

	/**
	 * Gets a Enabled.
	 * supported by:
	 * <ul>
	 * <li>Shell</li>
	 * <li>Slider</li>
	 * <li>Control</li>
	 * <li>MenuItem</li>
	 * <li>ToolItem</li>
	 * <li>Menu</li>
	 * <li>ScrollBar</li>	 
	 * </ul>
	 * 
	 * @return value	 a Enabled.
	 */	
	public Boolean getEnabled(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getEnabled(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a DragSourceEffect.
	 * supported by:
	 * <ul>
	 * <li>DragSource</li>	 
	 * </ul>
	 * 
	 * @param value	 a DragSourceEffect.
	 */
	public SWTQuery setDragSourceEffect(DragSourceEffect value){
		for(Widget each : items){
			WidgetPropertySwitch.setDragSourceEffect(each, value);
		}
		return this;
	}

	/**
	 * Gets a DragSourceEffect.
	 * supported by:
	 * <ul>
	 * <li>DragSource</li>	 
	 * </ul>
	 * 
	 * @return value	 a DragSourceEffect.
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
	 * Sets a ShowFocusedControl.
	 * supported by:
	 * <ul>
	 * <li>ScrolledComposite</li>	 
	 * </ul>
	 * 
	 * @param value	 a ShowFocusedControl.
	 */
	public SWTQuery setShowFocusedControl(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setShowFocusedControl(each, value);
		}
		return this;
	}

	/**
	 * Gets a ShowFocusedControl.
	 * supported by:
	 * <ul>
	 * <li>ScrolledComposite</li>	 
	 * </ul>
	 * 
	 * @return value	 a ShowFocusedControl.
	 */	
	public Boolean getShowFocusedControl(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getShowFocusedControl(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a ColumnOrder.
	 * supported by:
	 * <ul>
	 * <li>Table</li>
	 * <li>Tree</li>	 
	 * </ul>
	 * 
	 * @param value	 a ColumnOrder.
	 */
	public SWTQuery setColumnOrder(int[] value){
		for(Widget each : items){
			WidgetPropertySwitch.setColumnOrder(each, value);
		}
		return this;
	}

	/**
	 * Gets a ColumnOrder.
	 * supported by:
	 * <ul>
	 * <li>Table</li>
	 * <li>Tree</li>	 
	 * </ul>
	 * 
	 * @return value	 a ColumnOrder.
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
	 * Sets a MRUVisible.
	 * supported by:
	 * <ul>
	 * <li>CTabFolder</li>	 
	 * </ul>
	 * 
	 * @param value	 a MRUVisible.
	 */
	public SWTQuery setMRUVisible(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setMRUVisible(each, value);
		}
		return this;
	}

	/**
	 * Gets a MRUVisible.
	 * supported by:
	 * <ul>
	 * <li>CTabFolder</li>	 
	 * </ul>
	 * 
	 * @return value	 a MRUVisible.
	 */	
	public Boolean getMRUVisible(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getMRUVisible(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a Grayed.
	 * supported by:
	 * <ul>
	 * <li>Button</li>
	 * <li>TableItem</li>
	 * <li>TreeItem</li>	 
	 * </ul>
	 * 
	 * @param value	 a Grayed.
	 */
	public SWTQuery setGrayed(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setGrayed(each, value);
		}
		return this;
	}

	/**
	 * Gets a Grayed.
	 * supported by:
	 * <ul>
	 * <li>Button</li>
	 * <li>TableItem</li>
	 * <li>TreeItem</li>	 
	 * </ul>
	 * 
	 * @return value	 a Grayed.
	 */	
	public Boolean getGrayed(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getGrayed(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a Day.
	 * supported by:
	 * <ul>
	 * <li>DateTime</li>	 
	 * </ul>
	 * 
	 * @param value	 a Day.
	 */
	public SWTQuery setDay(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setDay(each, value);
		}
		return this;
	}

	/**
	 * Gets a Day.
	 * supported by:
	 * <ul>
	 * <li>DateTime</li>	 
	 * </ul>
	 * 
	 * @return value	 a Day.
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
	 * Sets a Cursor.
	 * supported by:
	 * <ul>
	 * <li>Control</li>	 
	 * </ul>
	 * 
	 * @param value	 a Cursor.
	 */
	public SWTQuery setCursor(Cursor value){
		for(Widget each : items){
			WidgetPropertySwitch.setCursor(each, value);
		}
		return this;
	}

	/**
	 * Gets a Cursor.
	 * supported by:
	 * <ul>
	 * <li>Control</li>	 
	 * </ul>
	 * 
	 * @return value	 a Cursor.
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
	 * Sets a Year.
	 * supported by:
	 * <ul>
	 * <li>DateTime</li>	 
	 * </ul>
	 * 
	 * @param value	 a Year.
	 */
	public SWTQuery setYear(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setYear(each, value);
		}
		return this;
	}

	/**
	 * Gets a Year.
	 * supported by:
	 * <ul>
	 * <li>DateTime</li>	 
	 * </ul>
	 * 
	 * @return value	 a Year.
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
	 * Sets a ToolBarVerticalAlignment.
	 * supported by:
	 * <ul>
	 * <li>Form</li>	 
	 * </ul>
	 * 
	 * @param value	 a ToolBarVerticalAlignment.
	 */
	public SWTQuery setToolBarVerticalAlignment(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setToolBarVerticalAlignment(each, value);
		}
		return this;
	}

	/**
	 * Gets a ToolBarVerticalAlignment.
	 * supported by:
	 * <ul>
	 * <li>Form</li>	 
	 * </ul>
	 * 
	 * @return value	 a ToolBarVerticalAlignment.
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
	 * Sets a Region.
	 * supported by:
	 * <ul>
	 * <li>Shell</li>
	 * <li>Control</li>	 
	 * </ul>
	 * 
	 * @param value	 a Region.
	 */
	public SWTQuery setRegion(Region value){
		for(Widget each : items){
			WidgetPropertySwitch.setRegion(each, value);
		}
		return this;
	}

	/**
	 * Gets a Region.
	 * supported by:
	 * <ul>
	 * <li>Shell</li>
	 * <li>Control</li>	 
	 * </ul>
	 * 
	 * @return value	 a Region.
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
	 * Sets a Accelerator.
	 * supported by:
	 * <ul>
	 * <li>MenuItem</li>	 
	 * </ul>
	 * 
	 * @param value	 a Accelerator.
	 */
	public SWTQuery setAccelerator(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setAccelerator(each, value);
		}
		return this;
	}

	/**
	 * Gets a Accelerator.
	 * supported by:
	 * <ul>
	 * <li>MenuItem</li>	 
	 * </ul>
	 * 
	 * @return value	 a Accelerator.
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
	 * Sets a WrapIndices.
	 * supported by:
	 * <ul>
	 * <li>CoolBar</li>	 
	 * </ul>
	 * 
	 * @param value	 a WrapIndices.
	 */
	public SWTQuery setWrapIndices(int[] value){
		for(Widget each : items){
			WidgetPropertySwitch.setWrapIndices(each, value);
		}
		return this;
	}

	/**
	 * Gets a WrapIndices.
	 * supported by:
	 * <ul>
	 * <li>CoolBar</li>	 
	 * </ul>
	 * 
	 * @return value	 a WrapIndices.
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
	 * Sets a PageIncrement.
	 * supported by:
	 * <ul>
	 * <li>Spinner</li>
	 * <li>Scale</li>
	 * <li>Slider</li>
	 * <li>ScrollBar</li>	 
	 * </ul>
	 * 
	 * @param value	 a PageIncrement.
	 */
	public SWTQuery setPageIncrement(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setPageIncrement(each, value);
		}
		return this;
	}

	/**
	 * Gets a PageIncrement.
	 * supported by:
	 * <ul>
	 * <li>Spinner</li>
	 * <li>Scale</li>
	 * <li>Slider</li>
	 * <li>ScrollBar</li>	 
	 * </ul>
	 * 
	 * @return value	 a PageIncrement.
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
	 * Sets a MatchEmptyString.
	 * supported by:
	 * <ul>
	 * <li>FilteredList</li>	 
	 * </ul>
	 * 
	 * @param value	 a MatchEmptyString.
	 */
	public SWTQuery setMatchEmptyString(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setMatchEmptyString(each, value);
		}
		return this;
	}

	/**
	 * Gets a MatchEmptyString.
	 * supported by:
	 * <ul>
	 * <li>FilteredList</li>	 
	 * </ul>
	 * 
	 * @return value	 a MatchEmptyString.
	 */	
	public Boolean getMatchEmptyString(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getMatchEmptyString(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a CompositionOffset.
	 * supported by:
	 * <ul>
	 * <li>IME</li>	 
	 * </ul>
	 * 
	 * @param value	 a CompositionOffset.
	 */
	public SWTQuery setCompositionOffset(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setCompositionOffset(each, value);
		}
		return this;
	}

	/**
	 * Gets a CompositionOffset.
	 * supported by:
	 * <ul>
	 * <li>IME</li>	 
	 * </ul>
	 * 
	 * @return value	 a CompositionOffset.
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
	 * Sets a Description.
	 * supported by:
	 * <ul>
	 * <li>Section</li>	 
	 * </ul>
	 * 
	 * @param value	 a Description.
	 */
	public SWTQuery setDescription(String value){
		for(Widget each : items){
			WidgetPropertySwitch.setDescription(each, value);
		}
		return this;
	}

	/**
	 * Gets a Description.
	 * supported by:
	 * <ul>
	 * <li>Section</li>	 
	 * </ul>
	 * 
	 * @return value	 a Description.
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
	 * Sets a ShowClose.
	 * supported by:
	 * <ul>
	 * <li>CTabItem</li>	 
	 * </ul>
	 * 
	 * @param value	 a ShowClose.
	 */
	public SWTQuery setShowClose(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setShowClose(each, value);
		}
		return this;
	}

	/**
	 * Gets a ShowClose.
	 * supported by:
	 * <ul>
	 * <li>CTabItem</li>	 
	 * </ul>
	 * 
	 * @return value	 a ShowClose.
	 */	
	public Boolean getShowClose(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getShowClose(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a SelectionForeground.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>
	 * <li>CTabFolder</li>	 
	 * </ul>
	 * 
	 * @param value	 a SelectionForeground.
	 */
	public SWTQuery setSelectionForeground(Color value){
		for(Widget each : items){
			WidgetPropertySwitch.setSelectionForeground(each, value);
		}
		return this;
	}

	/**
	 * Gets a SelectionForeground.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>
	 * <li>CTabFolder</li>	 
	 * </ul>
	 * 
	 * @return value	 a SelectionForeground.
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
	 * Sets a BackgroundMode.
	 * supported by:
	 * <ul>
	 * <li>Composite</li>	 
	 * </ul>
	 * 
	 * @param value	 a BackgroundMode.
	 */
	public SWTQuery setBackgroundMode(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setBackgroundMode(each, value);
		}
		return this;
	}

	/**
	 * Gets a BackgroundMode.
	 * supported by:
	 * <ul>
	 * <li>Composite</li>	 
	 * </ul>
	 * 
	 * @return value	 a BackgroundMode.
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
	 * Sets a TitleBarGradientBackground.
	 * supported by:
	 * <ul>
	 * <li>Section</li>	 
	 * </ul>
	 * 
	 * @param value	 a TitleBarGradientBackground.
	 */
	public SWTQuery setTitleBarGradientBackground(Color value){
		for(Widget each : items){
			WidgetPropertySwitch.setTitleBarGradientBackground(each, value);
		}
		return this;
	}

	/**
	 * Gets a TitleBarGradientBackground.
	 * supported by:
	 * <ul>
	 * <li>Section</li>	 
	 * </ul>
	 * 
	 * @return value	 a TitleBarGradientBackground.
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
	 * Sets a BlockSelectionBounds.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>	 
	 * </ul>
	 * 
	 * @param value	 a BlockSelectionBounds.
	 */
	public SWTQuery setBlockSelectionBounds(Rectangle value){
		for(Widget each : items){
			WidgetPropertySwitch.setBlockSelectionBounds(each, value);
		}
		return this;
	}

	/**
	 * Gets a BlockSelectionBounds.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>	 
	 * </ul>
	 * 
	 * @return value	 a BlockSelectionBounds.
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
	 * Sets a MinHeight.
	 * supported by:
	 * <ul>
	 * <li>ScrolledComposite</li>	 
	 * </ul>
	 * 
	 * @param value	 a MinHeight.
	 */
	public SWTQuery setMinHeight(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setMinHeight(each, value);
		}
		return this;
	}

	/**
	 * Gets a MinHeight.
	 * supported by:
	 * <ul>
	 * <li>ScrolledComposite</li>	 
	 * </ul>
	 * 
	 * @return value	 a MinHeight.
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
	 * Sets a RightMargin.
	 * supported by:
	 * <ul>
	 * <li>CLabel</li>
	 * <li>StyledText</li>	 
	 * </ul>
	 * 
	 * @param value	 a RightMargin.
	 */
	public SWTQuery setRightMargin(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setRightMargin(each, value);
		}
		return this;
	}

	/**
	 * Gets a RightMargin.
	 * supported by:
	 * <ul>
	 * <li>CLabel</li>
	 * <li>StyledText</li>	 
	 * </ul>
	 * 
	 * @return value	 a RightMargin.
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
	 * Sets a Location.
	 * supported by:
	 * <ul>
	 * <li>Caret</li>
	 * <li>Shell</li>
	 * <li>Decorations</li>
	 * <li>Control</li>	 
	 * </ul>
	 * 
	 * @param value	 a Location.
	 */
	public SWTQuery setLocation(Point value){
		for(Widget each : items){
			WidgetPropertySwitch.setLocation(each, value);
		}
		return this;
	}

	/**
	 * Gets a Location.
	 * supported by:
	 * <ul>
	 * <li>Caret</li>
	 * <li>Shell</li>
	 * <li>Decorations</li>
	 * <li>Control</li>	 
	 * </ul>
	 * 
	 * @return value	 a Location.
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
	 * Sets a Spacing.
	 * supported by:
	 * <ul>
	 * <li>ExpandBar</li>	 
	 * </ul>
	 * 
	 * @param value	 a Spacing.
	 */
	public SWTQuery setSpacing(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setSpacing(each, value);
		}
		return this;
	}

	/**
	 * Gets a Spacing.
	 * supported by:
	 * <ul>
	 * <li>ExpandBar</li>	 
	 * </ul>
	 * 
	 * @return value	 a Spacing.
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
	 * Sets a MinimumSize.
	 * supported by:
	 * <ul>
	 * <li>Shell</li>
	 * <li>CoolItem</li>	 
	 * </ul>
	 * 
	 * @param value	 a MinimumSize.
	 */
	public SWTQuery setMinimumSize(Point value){
		for(Widget each : items){
			WidgetPropertySwitch.setMinimumSize(each, value);
		}
		return this;
	}

	/**
	 * Gets a MinimumSize.
	 * supported by:
	 * <ul>
	 * <li>Shell</li>
	 * <li>CoolItem</li>	 
	 * </ul>
	 * 
	 * @return value	 a MinimumSize.
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
	 * Sets a DescriptionControl.
	 * supported by:
	 * <ul>
	 * <li>Section</li>	 
	 * </ul>
	 * 
	 * @param value	 a DescriptionControl.
	 */
	public SWTQuery setDescriptionControl(Control value){
		for(Widget each : items){
			WidgetPropertySwitch.setDescriptionControl(each, value);
		}
		return this;
	}

	/**
	 * Gets a DescriptionControl.
	 * supported by:
	 * <ul>
	 * <li>Section</li>	 
	 * </ul>
	 * 
	 * @return value	 a DescriptionControl.
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
	 * Sets a Text.
	 * supported by:
	 * <ul>
	 * <li>Hyperlink</li>
	 * <li>ScrolledForm</li>
	 * <li>CLabel</li>
	 * <li>StyledText</li>
	 * <li>Decorations</li>
	 * <li>ExpandableComposite</li>
	 * <li>Browser</li>
	 * <li>CCombo</li>
	 * <li>Combo</li>
	 * <li>Group</li>
	 * <li>Form</li>
	 * <li>Text</li>
	 * <li>Button</li>
	 * <li>Label</li>
	 * <li>Link</li>
	 * <li>TableItem</li>
	 * <li>TreeItem</li>
	 * <li>Item</li>
	 * <li>ToolTip</li>	 
	 * </ul>
	 * 
	 * @param value	 a Text.
	 */
	public SWTQuery setText(String value){
		for(Widget each : items){
			WidgetPropertySwitch.setText(each, value);
		}
		return this;
	}

	/**
	 * Gets a Text.
	 * supported by:
	 * <ul>
	 * <li>Hyperlink</li>
	 * <li>ScrolledForm</li>
	 * <li>CLabel</li>
	 * <li>StyledText</li>
	 * <li>Decorations</li>
	 * <li>ExpandableComposite</li>
	 * <li>Browser</li>
	 * <li>CCombo</li>
	 * <li>Combo</li>
	 * <li>Group</li>
	 * <li>Form</li>
	 * <li>Text</li>
	 * <li>Button</li>
	 * <li>Label</li>
	 * <li>Link</li>
	 * <li>TableItem</li>
	 * <li>TreeItem</li>
	 * <li>Item</li>
	 * <li>ToolTip</li>	 
	 * </ul>
	 * 
	 * @return value	 a Text.
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
	 * Sets a Tabs.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>
	 * <li>Text</li>	 
	 * </ul>
	 * 
	 * @param value	 a Tabs.
	 */
	public SWTQuery setTabs(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setTabs(each, value);
		}
		return this;
	}

	/**
	 * Gets a Tabs.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>
	 * <li>Text</li>	 
	 * </ul>
	 * 
	 * @return value	 a Tabs.
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
	 * Sets a BorderVisible.
	 * supported by:
	 * <ul>
	 * <li>CTabFolder</li>	 
	 * </ul>
	 * 
	 * @param value	 a BorderVisible.
	 */
	public SWTQuery setBorderVisible(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setBorderVisible(each, value);
		}
		return this;
	}

	/**
	 * Gets a BorderVisible.
	 * supported by:
	 * <ul>
	 * <li>CTabFolder</li>	 
	 * </ul>
	 * 
	 * @return value	 a BorderVisible.
	 */	
	public Boolean getBorderVisible(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getBorderVisible(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a State.
	 * supported by:
	 * <ul>
	 * <li>ProgressBar</li>	 
	 * </ul>
	 * 
	 * @param value	 a State.
	 */
	public SWTQuery setState(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setState(each, value);
		}
		return this;
	}

	/**
	 * Gets a State.
	 * supported by:
	 * <ul>
	 * <li>ProgressBar</li>	 
	 * </ul>
	 * 
	 * @return value	 a State.
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
	 * Sets a TopItem.
	 * supported by:
	 * <ul>
	 * <li>Tree</li>	 
	 * </ul>
	 * 
	 * @param value	 a TopItem.
	 */
	public SWTQuery setTopItem(TreeItem value){
		for(Widget each : items){
			WidgetPropertySwitch.setTopItem(each, value);
		}
		return this;
	}

	/**
	 * Gets a TopItem.
	 * supported by:
	 * <ul>
	 * <li>Tree</li>	 
	 * </ul>
	 * 
	 * @return value	 a TopItem.
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
	 * Sets a TabList.
	 * supported by:
	 * <ul>
	 * <li>Composite</li>	 
	 * </ul>
	 * 
	 * @param value	 a TabList.
	 */
	public SWTQuery setTabList(Control[] value){
		for(Widget each : items){
			WidgetPropertySwitch.setTabList(each, value);
		}
		return this;
	}

	/**
	 * Gets a TabList.
	 * supported by:
	 * <ul>
	 * <li>Composite</li>	 
	 * </ul>
	 * 
	 * @return value	 a TabList.
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
	 * Sets a TopCenter.
	 * supported by:
	 * <ul>
	 * <li>ViewForm</li>	 
	 * </ul>
	 * 
	 * @param value	 a TopCenter.
	 */
	public SWTQuery setTopCenter(Control value){
		for(Widget each : items){
			WidgetPropertySwitch.setTopCenter(each, value);
		}
		return this;
	}

	/**
	 * Gets a TopCenter.
	 * supported by:
	 * <ul>
	 * <li>ViewForm</li>	 
	 * </ul>
	 * 
	 * @return value	 a TopCenter.
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
	 * Sets a ExpandHorizontal.
	 * supported by:
	 * <ul>
	 * <li>ScrolledComposite</li>	 
	 * </ul>
	 * 
	 * @param value	 a ExpandHorizontal.
	 */
	public SWTQuery setExpandHorizontal(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setExpandHorizontal(each, value);
		}
		return this;
	}

	/**
	 * Gets a ExpandHorizontal.
	 * supported by:
	 * <ul>
	 * <li>ScrolledComposite</li>	 
	 * </ul>
	 * 
	 * @return value	 a ExpandHorizontal.
	 */	
	public Boolean getExpandHorizontal(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getExpandHorizontal(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a TabStops.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>	 
	 * </ul>
	 * 
	 * @param value	 a TabStops.
	 */
	public SWTQuery setTabStops(int[] value){
		for(Widget each : items){
			WidgetPropertySwitch.setTabStops(each, value);
		}
		return this;
	}

	/**
	 * Gets a TabStops.
	 * supported by:
	 * <ul>
	 * <li>StyledText</li>	 
	 * </ul>
	 * 
	 * @return value	 a TabStops.
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
	 * Sets a Minutes.
	 * supported by:
	 * <ul>
	 * <li>DateTime</li>	 
	 * </ul>
	 * 
	 * @param value	 a Minutes.
	 */
	public SWTQuery setMinutes(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setMinutes(each, value);
		}
		return this;
	}

	/**
	 * Gets a Minutes.
	 * supported by:
	 * <ul>
	 * <li>DateTime</li>	 
	 * </ul>
	 * 
	 * @return value	 a Minutes.
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
	 * Sets a ExpandVertical.
	 * supported by:
	 * <ul>
	 * <li>ScrolledComposite</li>	 
	 * </ul>
	 * 
	 * @param value	 a ExpandVertical.
	 */
	public SWTQuery setExpandVertical(Boolean value){
		for(Widget each : items){
			WidgetPropertySwitch.setExpandVertical(each, value);
		}
		return this;
	}

	/**
	 * Gets a ExpandVertical.
	 * supported by:
	 * <ul>
	 * <li>ScrolledComposite</li>	 
	 * </ul>
	 * 
	 * @return value	 a ExpandVertical.
	 */	
	public Boolean getExpandVertical(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getExpandVertical(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a HotImage.
	 * supported by:
	 * <ul>
	 * <li>ToolItem</li>	 
	 * </ul>
	 * 
	 * @param value	 a HotImage.
	 */
	public SWTQuery setHotImage(Image value){
		for(Widget each : items){
			WidgetPropertySwitch.setHotImage(each, value);
		}
		return this;
	}

	/**
	 * Gets a HotImage.
	 * supported by:
	 * <ul>
	 * <li>ToolItem</li>	 
	 * </ul>
	 * 
	 * @return value	 a HotImage.
	 */	
	public Image getHotImage(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getHotImage(items.get(0));
		}
		else{
			return null;
		}
	}
	/**
	 * Sets a MinimumCharacters.
	 * supported by:
	 * <ul>
	 * <li>CTabFolder</li>	 
	 * </ul>
	 * 
	 * @param value	 a MinimumCharacters.
	 */
	public SWTQuery setMinimumCharacters(Integer value){
		for(Widget each : items){
			WidgetPropertySwitch.setMinimumCharacters(each, value);
		}
		return this;
	}

	/**
	 * Gets a MinimumCharacters.
	 * supported by:
	 * <ul>
	 * <li>CTabFolder</li>	 
	 * </ul>
	 * 
	 * @return value	 a MinimumCharacters.
	 */	
	public Integer getMinimumCharacters(){
		if(items.size() > 0){
			return WidgetPropertySwitch.getMinimumCharacters(items.get(0));
		}
		else{
			return null;
		}
	}

}
