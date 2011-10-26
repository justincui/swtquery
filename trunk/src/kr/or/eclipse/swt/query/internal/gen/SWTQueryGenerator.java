package kr.or.eclipse.swt.query.internal.gen;

import java.util.*;
import org.eclipse.swt.*;
import kr.or.eclipse.swt.query.*;
import kr.or.eclipse.swt.query.internal.grammar.*;
import kr.or.eclipse.swt.query.internal.gen.*;
import kr.or.eclipse.swt.query.filter.*;
import kr.or.eclipse.swt.query.internal.*;
import kr.or.eclipse.swt.query.util.*;

public class SWTQueryGenerator
{
  protected static String nl;
  public static synchronized SWTQueryGenerator create(String lineSeparator)
  {
    nl = lineSeparator;
    SWTQueryGenerator result = new SWTQueryGenerator();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**" + NL + " * Copyright 2011 jeeeyul@gmail.com" + NL + " *" + NL + " * All rights reserved. This program and the accompanying materials" + NL + " * are made available under the terms of the Eclipse Public License v1.0" + NL + " * which accompanies this distribution, and is available at" + NL + " * http://www.eclipse.org/legal/epl-v10.html" + NL + " *" + NL + " */";
  protected final String TEXT_3 = NL + "package kr.or.eclipse.swt.query;" + NL;
  protected final String TEXT_4 = NL + "import org.eclipse.swt.layout.*;" + NL + "import org.eclipse.swt.widgets.*;" + NL + "import org.eclipse.swt.*;" + NL + "import org.eclipse.swt.events.*;" + NL + "import org.eclipse.swt.graphics.*;" + NL + "import org.eclipse.swt.dnd.*;" + NL + "import java.util.*;" + NL;
  protected final String TEXT_5 = NL;
  protected final String TEXT_6 = NL + NL + "public final class SWTQuery {" + NL + "\tprivate static int CURRENT_DEBUG_COLOR = 0;" + NL + "\t" + NL + "\tpublic static SWTQuery $(Widget... w) {" + NL + "\t\tList<Widget> list = Arrays.asList(w);" + NL + "\t\treturn new SWTQuery(list);" + NL + "\t}" + NL + "\t" + NL + "\tpublic static SWTQuery $(Collection<Widget> itemList) {" + NL + "\t\treturn $(itemList.toArray(new Widget[itemList.size()]));" + NL + "\t}" + NL + "\t" + NL + "\tpublic static SWTQuery $(Event event) {" + NL + "\t\treturn new SWTQuery(event.widget);" + NL + "\t}" + NL + "\t" + NL + "\tpublic static SWTQuery $(TypedEvent event){" + NL + "\t\treturn new SWTQuery(event.widget);" + NL + "\t}" + NL + "\t" + NL + "\t/**" + NL + "\t * Creates {@link SWTQuery} for sender of {@link DropTargetEvent}." + NL + "\t * " + NL + "\t * @param event" + NL + "\t * @return {@link SWTQuery} for {@link DropTarget}." + NL + "\t */" + NL + "\tpublic static SWTQuery $(DropTargetEvent event) {" + NL + "\t\treturn new SWTQuery(event.widget);" + NL + "\t}" + NL + "\t" + NL + "\tpublic static SWTQuery $(Widget context, String selector) {" + NL + "\t\treturn new SWTQuery(context).select(selector);" + NL + "\t}" + NL + "\t" + NL + "\tprivate List<Widget> items;" + NL + "" + NL + "\tprivate SWTQuery(List<Widget> itemList) {" + NL + "\t\tthis.items = itemList;" + NL + "\t}" + NL + "" + NL + "\tprivate SWTQuery(Widget... items) {" + NL + "\t\tthis.items = new ArrayList<Widget>();" + NL + "\t\tfor (Widget each : items) {" + NL + "\t\t\tthis.items.add(each);" + NL + "\t\t}" + NL + "\t}" + NL + "\t" + NL + "\tpublic SWTQuery setGridLayoutData(int style) {" + NL + "\t\tfor (Widget each : items) {" + NL + "\t\t\tWidgetPropertySwitch.setLayoutData(each, new GridData(style));" + NL + "\t\t}" + NL + "\t\treturn this;" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery setGridLayoutData(int horizontalAlignment, int verticalAlignment, boolean grabExcessHorizontalSpace," + NL + "\t\t\tboolean grabExcessVerticalSpace, int horizontalSpan, int verticalSpan) {" + NL + "\t\tfor (Widget each : items) {" + NL + "\t\t\tWidgetPropertySwitch.setLayoutData(each, new GridData(horizontalAlignment, verticalAlignment," + NL + "\t\t\t\t\tgrabExcessHorizontalSpace, grabExcessVerticalSpace, horizontalSpan, verticalSpan));" + NL + "\t\t}" + NL + "\t\treturn this;" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery select(String selector) {" + NL + "\t\tList<Selector> selectors = SelectorInterpreter.build(selector);" + NL + "" + NL + "\t\tList<Widget> result = new UniqueList<Widget>();" + NL + "\t\tfor (Selector each : selectors) {" + NL + "\t\t\tresult.addAll(each.select(items));" + NL + "\t\t}" + NL + "\t\treturn new SWTQuery(result);" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery addListener(int eventType, Listener listener) {" + NL + "\t\tfor (Widget each : this.items) {" + NL + "\t\t\teach.addListener(eventType, listener);" + NL + "\t\t}" + NL + "\t\treturn this;" + NL + "\t}" + NL + "\t" + NL + "\tpublic SWTQuery removeListener(int eventType, Listener listener) {" + NL + "\t\tfor (Widget each : this.items) {" + NL + "\t\t\teach.removeListener(eventType, listener);" + NL + "\t\t}" + NL + "\t\treturn this;" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery each(IWidgetFunction f) {" + NL + "\t\tfor (Widget each : this.items) {" + NL + "\t\t\tf.doFunction(each);" + NL + "\t\t}" + NL + "\t\treturn this;" + NL + "\t}" + NL + "" + NL + "\t@SuppressWarnings(\"unchecked\")" + NL + "\tpublic <T> T getData(String key) {" + NL + "\t\tif (items.size() > 0) {" + NL + "\t\t\treturn (T) items.get(0).getData(key);" + NL + "\t\t} else {" + NL + "\t\t\treturn null;" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery layout() {" + NL + "\t\tfor (Widget each : this.items) {" + NL + "\t\t\tif (each instanceof Composite) {" + NL + "\t\t\t\t((Composite) each).layout();" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn this;" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery layout(boolean changed) {" + NL + "\t\tfor (Widget each : this.items) {" + NL + "\t\t\tif (each instanceof Composite) {" + NL + "\t\t\t\t((Composite) each).layout(changed);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn this;" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery layout(boolean changed, boolean all) {" + NL + "\t\tfor (Widget each : this.items) {" + NL + "\t\t\tif (each instanceof Composite) {" + NL + "\t\t\t\t((Composite) each).layout(changed, all);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn this;" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery next() {" + NL + "\t\treturn next(1);" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery next(int delta) {" + NL + "\t\treturn translate(delta);" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery parent() {" + NL + "\t\tArrayList<Widget> parents = new UniqueList<Widget>();" + NL + "\t\tfor (Widget each : this.items) {" + NL + "\t\t\tWidget eachParent = ParentSwitch.INSTANCE.doSwitch(each);" + NL + "\t\t\tif (eachParent != null) {" + NL + "\t\t\t\tparents.add(eachParent);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn new SWTQuery(parents);" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery prev() {" + NL + "\t\treturn prev(-1);" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery prev(int delta) {" + NL + "\t\treturn translate(delta);" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery redraw() {" + NL + "\t\tfor (Widget each : this.items) {" + NL + "\t\t\tif (each instanceof Control) {" + NL + "\t\t\t\t((Control) each).redraw();" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn this;" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery redraw(int x, int y, int w, int h, boolean redrawChildren) {" + NL + "\t\tfor (Widget each : this.items) {" + NL + "\t\t\tif (each instanceof Control) {" + NL + "\t\t\t\t((Control) each).redraw(x, y, w, h, redrawChildren);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn this;" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery setData(String key, Object data) {" + NL + "\t\tfor (Widget each : items) {" + NL + "\t\t\teach.setData(key, data);" + NL + "\t\t}" + NL + "\t\treturn this;" + NL + "\t}" + NL + "" + NL + "\tprivate SWTQuery translate(int delta) {" + NL + "\t\tArrayList<Widget> result = new ArrayList<Widget>();" + NL + "" + NL + "\t\tfor (Widget each : this.items) {" + NL + "\t\t\tWidget parent = ParentSwitch.INSTANCE.doSwitch(each);" + NL + "\t\t\tif (parent == null) {" + NL + "\t\t\t\tcontinue;" + NL + "\t\t\t}" + NL + "\t\t\tList<Widget> sibilings = ChildrenSwitch.INSTANCE.doSwitch(parent);" + NL + "\t\t\tint index = sibilings.indexOf(each);" + NL + "" + NL + "\t\t\tint newIndex = index + delta;" + NL + "\t\t\tif (newIndex >= 0 && newIndex < sibilings.size()) {" + NL + "\t\t\t\tresult.add(sibilings.get(newIndex));" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "" + NL + "\t\treturn new SWTQuery(result);" + NL + "\t}" + NL + "\t" + NL + "\tpublic SWTQuery schedule(final IWidgetFunction function) {" + NL + "\t\tDisplay.getCurrent().asyncExec(new Runnable() {" + NL + "\t\t\t@Override" + NL + "\t\t\tpublic void run() {" + NL + "\t\t\t\tSWTQuery.this.each(function);" + NL + "\t\t\t}" + NL + "\t\t});" + NL + "\t\treturn this;" + NL + "\t}" + NL + "\t" + NL + "\tpublic SWTQuery addProperties(Map<String, Object> map) {" + NL + "\t\tfor (String propName : map.keySet()) {" + NL + "\t\t\tfor (Widget item : items) {" + NL + "\t\t\t\tWidgetPropertySwitch.setProperty(item, propName, map.get(propName));" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn this;" + NL + "\t}" + NL;
  protected final String TEXT_7 = NL + "\t/**" + NL + "\t * Sets a ";
  protected final String TEXT_8 = " property." + NL + "\t * " + NL + "\t * @param value a ";
  protected final String TEXT_9 = " value to set." + NL + "\t *";
  protected final String TEXT_10 = NL + "\t * @see ";
  protected final String TEXT_11 = "\t " + NL + "\t */" + NL + "\tpublic SWTQuery set";
  protected final String TEXT_12 = "(";
  protected final String TEXT_13 = " value){" + NL + "\t\tfor(Widget each : items){" + NL + "\t\t\tWidgetPropertySwitch.set";
  protected final String TEXT_14 = "(each, value);" + NL + "\t\t}" + NL + "\t\treturn this;" + NL + "\t}";
  protected final String TEXT_15 = NL + NL + "\t/**" + NL + "\t *Inverts ";
  protected final String TEXT_16 = " property." + NL + "\t *";
  protected final String TEXT_17 = NL + "\t * @see ";
  protected final String TEXT_18 = "\t " + NL + "\t */" + NL + "\tpublic SWTQuery toggle";
  protected final String TEXT_19 = "(){" + NL + "\t\tfor(Widget each : items){" + NL + "\t\t\tBoolean old = WidgetPropertySwitch.get";
  protected final String TEXT_20 = "(each);" + NL + "\t\t\tWidgetPropertySwitch.set";
  protected final String TEXT_21 = "(each, !old);" + NL + "\t\t}" + NL + "\t\treturn this;" + NL + "\t}";
  protected final String TEXT_22 = "\t" + NL;
  protected final String TEXT_23 = NL;
  protected final String TEXT_24 = NL + "\t/**" + NL + "\t * Gets a ";
  protected final String TEXT_25 = " property value." + NL + "\t * " + NL + "\t * @return value a ";
  protected final String TEXT_26 = "." + NL + "\t *";
  protected final String TEXT_27 = NL + "\t * @see ";
  protected final String TEXT_28 = "\t " + NL + "\t */\t" + NL + "\tpublic ";
  protected final String TEXT_29 = " ";
  protected final String TEXT_30 = "(){" + NL + "\t\tif(items.size() > 0){" + NL + "\t\t\treturn WidgetPropertySwitch.get";
  protected final String TEXT_31 = "(items.get(0));" + NL + "\t\t}" + NL + "\t\telse{" + NL + "\t\t\treturn null;" + NL + "\t\t}" + NL + "\t}";
  protected final String TEXT_32 = NL + "\tpublic SWTQuery debug(final String text) {" + NL + "\t\tfinal int color = (CURRENT_DEBUG_COLOR + 2);" + NL + "\t\tCURRENT_DEBUG_COLOR = (++CURRENT_DEBUG_COLOR % 15);" + NL + "\t\t" + NL + "\t\tthis.addListener(SWT.Paint, new Listener() {" + NL + "\t\t\t@Override" + NL + "\t\t\tpublic void handleEvent(Event event) {" + NL + "\t\t\t\tRectangle bounds = $(event).getBounds();" + NL + "\t\t\t\tGC gc = event.gc;" + NL + "\t\t\t\tgc.setAlpha(150);" + NL + "\t\t\t\tgc.setBackground(Display.getDefault().getSystemColor(color));" + NL + "\t\t\t\tgc.fillRectangle(0, 0, bounds.width - 1, bounds.height - 1);" + NL + "\t\t\t\tgc.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));" + NL + "\t\t\t\tgc.setAlpha(255);" + NL + "\t\t\t\tgc.drawText(text, 1, 1, true);" + NL + "\t\t\t}" + NL + "\t\t});" + NL + "\t\t" + NL + "\t\tthis.redraw();" + NL + "\t\treturn this;" + NL + "\t}" + NL + "\t" + NL + "\tpublic SWTQuery debug() {" + NL + "\t\tfinal int color = (CURRENT_DEBUG_COLOR + 2);" + NL + "\t\tCURRENT_DEBUG_COLOR = (++CURRENT_DEBUG_COLOR % 15);" + NL + "\t\t" + NL + "\t\tthis.addListener(SWT.Paint, new Listener() {" + NL + "\t\t\t@Override" + NL + "\t\t\tpublic void handleEvent(Event event) {" + NL + "\t\t\t\tRectangle bounds = $(event).getBounds();" + NL + "\t\t\t\tGC gc = event.gc;" + NL + "\t\t\t\tgc.setAlpha(150);" + NL + "\t\t\t\tgc.setBackground(Display.getDefault().getSystemColor(color));" + NL + "\t\t\t\tgc.fillRectangle(0, 0, bounds.width - 1, bounds.height - 1);" + NL + "\t\t\t\tgc.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));" + NL + "\t\t\t}" + NL + "\t\t});" + NL + "\t\tthis.redraw();" + NL + "\t\treturn this;" + NL + "\t}" + NL + "\t" + NL + "\tpublic SWTQuery createDropTarget(int style) {" + NL + "\t\tArrayList<Widget> dropTargets = new ArrayList<Widget>();" + NL + "\t\tfor (Widget each : items) {" + NL + "\t\t\tif (each instanceof Control) {" + NL + "\t\t\t\tdropTargets.add(new DropTarget((Control) each, style));" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn new SWTQuery(dropTargets);" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery getDropTarget(int style) {" + NL + "\t\tArrayList<Widget> dropTargets = new ArrayList<Widget>();" + NL + "\t\tfor (Widget each : items) {" + NL + "\t\t\tif (each instanceof Control) {" + NL + "\t\t\t\tObject target = ((Control) each).getData(DND.DROP_TARGET_KEY);" + NL + "\t\t\t\tif (target instanceof DropTarget) {" + NL + "\t\t\t\t\tdropTargets.add((Widget) target);" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn new SWTQuery(dropTargets);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * " + NL + "\t * @return Number of selected widgets." + NL + "\t */" + NL + "\tpublic int size() {" + NL + "\t\treturn items.size();" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Select a first widget from current selection." + NL + "\t * " + NL + "\t * @return {@link SWTQuery} for first widget of current context." + NL + "\t */" + NL + "\tpublic SWTQuery first() {" + NL + "\t\tif (items.size() > 0) {" + NL + "\t\t\treturn new SWTQuery(items.get(0));" + NL + "\t\t} else {" + NL + "\t\t\treturn new SWTQuery(new UniqueList<Widget>());" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Select a last widget from current selection." + NL + "\t * " + NL + "\t * @return {@link SWTQuery} for last widget of current context." + NL + "\t */" + NL + "\tpublic SWTQuery last() {" + NL + "\t\tif (items.size() > 0) {" + NL + "\t\t\treturn new SWTQuery(items.get(items.size() - 1));" + NL + "\t\t} else {" + NL + "\t\t\treturn new SWTQuery(new UniqueList<Widget>());" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Get {@link SWTQuery} for a widget with specified index." + NL + "\t * " + NL + "\t * @param index" + NL + "\t * @return {@link SWTQuery} for a specified widget." + NL + "\t */" + NL + "\tpublic SWTQuery get(int index) {" + NL + "\t\tif (items.size() > index) {" + NL + "\t\t\treturn new SWTQuery(items.get(index));" + NL + "\t\t} else {" + NL + "\t\t\treturn new SWTQuery(new UniqueList<Widget>());" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Reverese order of selected widgets, Some methods likes" + NL + "\t * {@link #setEnabled(Boolean)} needs to be applied reverse order, because" + NL + "\t * disabled parent widget prevents messages to child widgets." + NL + "\t * " + NL + "\t * @return Reveresed {@link SWTQuery}." + NL + "\t * " + NL + "\t * @see #toggleEnabled()" + NL + "\t * @see #setEnabled(Boolean)" + NL + "\t */" + NL + "\tpublic SWTQuery reverse() {" + NL + "\t\tArrayList<Widget> copied = new ArrayList<Widget>(items);" + NL + "\t\tCollections.reverse(copied);" + NL + "\t\treturn new SWTQuery(copied);" + NL + "\t}" + NL + "\t" + NL + "\t/**" + NL + "\t * Adds Transfer into {@link DragSource} or {@link DropTarget}." + NL + "\t * " + NL + "\t * @param transfer" + NL + "\t *        {@link Transfer} to add." + NL + "\t * @return {@link SWTQuery}." + NL + "\t * " + NL + "\t * @see DragSource" + NL + "\t * @see DropTarget" + NL + "\t * @see #createDropTarget(int)" + NL + "\t * @see #getDropTarget(int)" + NL + "\t */" + NL + "\tpublic SWTQuery addTransfer(Transfer... transfers) {" + NL + "\t\tfor (Widget each : items) {" + NL + "\t\t\tif (each instanceof DropTarget) {" + NL + "\t\t\t\tDropTarget dropTarget = (DropTarget) each;" + NL + "\t\t\t\tList<Transfer> list = new ArrayList<Transfer>(Arrays.asList(dropTarget.getTransfer()));" + NL + "" + NL + "\t\t\t\tfor (Transfer transfer : transfers) {" + NL + "\t\t\t\t\tif (!list.contains(transfer)) {" + NL + "\t\t\t\t\t\tlist.add(transfer);" + NL + "\t\t\t\t\t\tdropTarget.setTransfer(list.toArray(new Transfer[list.size()]));" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "" + NL + "\t\t\tif (each instanceof DragSource) {" + NL + "\t\t\t\tDragSource dragSource = (DragSource) each;" + NL + "\t\t\t\tList<Transfer> list = new ArrayList<Transfer>(Arrays.asList(dragSource.getTransfer()));" + NL + "\t\t\t\tfor (Transfer transfer : transfers) {" + NL + "\t\t\t\t\tif (!list.contains(transfer)) {" + NL + "\t\t\t\t\t\tlist.add(transfer);" + NL + "\t\t\t\t\t\tdragSource.setTransfer(list.toArray(new Transfer[list.size()]));" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn this;" + NL + "\t}" + NL + "\t" + NL + "\tpublic SWTQuery addDropListener(DropTargetListener listener){" + NL + "\t\tfor(Widget each : items){" + NL + "\t\t\tif(each instanceof DropTarget){" + NL + "\t\t\t\t((DropTarget)each).addDropListener(listener);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn this;" + NL + "\t}" + NL + "\t" + NL + "\t/**" + NL + "\t * Let an Animator takes animation start key frame from currently selected" + NL + "\t * {@link Widget}s. Marked widgets will be animated automatically if they" + NL + "\t * have changed properties which can be animated." + NL + "\t * " + NL + "\t * @param duration" + NL + "\t *        Animation length in seconds. must be in 0.1 ~ 5." + NL + "\t * " + NL + "\t * @param timingFunction" + NL + "\t *        timing function. {@link TimingFunction#EASE_IN}," + NL + "\t *        {@link TimingFunction#EASE_IN_OUT} and" + NL + "\t *        {@link TimingFunction#EASE_OUT} are provded in" + NL + "\t *        {@link TimingFunction}. can be null." + NL + "\t * " + NL + "\t * @see #setBounds(Rectangle)" + NL + "\t * @see #setLocation(Point)" + NL + "\t * @see #setSize(Point)" + NL + "\t * @see #setForeground(Color)" + NL + "\t * @see #setBackground(Color)" + NL + "\t * @see TimingFunction#EASE_IN" + NL + "\t * @see TimingFunction#EASE_IN_OUT" + NL + "\t * @see TimingFunction#EASE_OUT" + NL + "\t * " + NL + "\t * @return {@link SWTQuery} same context." + NL + "\t */" + NL + "\tpublic SWTQuery markAnimationStart(double duration, TimingFunction timingFunction) {" + NL + "\t\tif (duration < 0.1 || duration > 5) {" + NL + "\t\t\tthrow new IllegalArgumentException(\"duration must be in 0.1 to 5.0\");" + NL + "\t\t}" + NL + "" + NL + "\t\tfinal Animator animator = new Animator();" + NL + "\t\tanimator.setLength((long) (duration * 1000L));" + NL + "\t\tanimator.setTimingFunction(timingFunction);" + NL + "" + NL + "\t\tfor (Widget each : items) {" + NL + "\t\t\tanimator.mark(each);" + NL + "\t\t}" + NL + "" + NL + "\t\tDisplay.getDefault().asyncExec(new Runnable() {" + NL + "\t\t\t@Override" + NL + "\t\t\tpublic void run() {" + NL + "\t\t\t\tanimator.schedule();" + NL + "\t\t\t}" + NL + "\t\t});" + NL + "" + NL + "\t\treturn this;" + NL + "\t}" + NL + "\t" + NL + "\t" + NL + "\t/**" + NL + "\t * Let an Animator takes animation start key frame from currently selected" + NL + "\t * {@link Widget}s. Marked widgets will be animated automatically if they" + NL + "\t * have changed properties which can be animated." + NL + "\t * " + NL + "\t * @param duration" + NL + "\t *        Animation length in seconds. must be in 0.1 ~ 5." + NL + "\t * " + NL + "\t * @see #setBounds(Rectangle)" + NL + "\t * @see #setLocation(Point)" + NL + "\t * @see #setSize(Point)" + NL + "\t * @see #setForeground(Color)" + NL + "\t * @see #setBackground(Color)" + NL + "\t * @see TimingFunction#EASE_IN" + NL + "\t * @see TimingFunction#EASE_IN_OUT" + NL + "\t * @see TimingFunction#EASE_OUT" + NL + "\t * " + NL + "\t * @return {@link SWTQuery} same context." + NL + "\t */" + NL + "\tpublic SWTQuery markAnimationStart(double duration) {" + NL + "\t\treturn markAnimationStart(duration, TimingFunction.EASE_IN_OUT);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Let an Animator takes animation start key frame from currently selected" + NL + "\t * {@link Widget}s. Marked widgets will be animated automatically if they" + NL + "\t * have changed properties which can be animated." + NL + "\t * " + NL + "\t * @see #setBounds(Rectangle)" + NL + "\t * @see #setLocation(Point)" + NL + "\t * @see #setSize(Point)" + NL + "\t * @see #setForeground(Color)" + NL + "\t * @see #setBackground(Color)" + NL + "\t * @see TimingFunction#EASE_IN" + NL + "\t * @see TimingFunction#EASE_IN_OUT" + NL + "\t * @see TimingFunction#EASE_OUT" + NL + "\t * " + NL + "\t * @return {@link SWTQuery} same context." + NL + "\t */" + NL + "\tpublic SWTQuery markAnimationStart() {" + NL + "\t\treturn markAnimationStart(0.5, TimingFunction.EASE_IN_OUT);" + NL + "\t}" + NL + "}";
  protected final String TEXT_33 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
     
@SuppressWarnings("unchecked")
Collection<Property> properties = (Collection<Property>)argument; 

    stringBuffer.append(TEXT_3);
     GenUtil.startImport();
GenUtil.addImportStatement(ArrayList.class);
GenUtil.addImportStatement(List.class);
GenUtil.addImportStatement(Map.class);
GenUtil.addImportStatement(IWidgetFilter.class);
GenUtil.addImportStatement(ChildrenSwitch.class);
GenUtil.addImportStatement(ParentSwitch.class);
GenUtil.addImportStatement(WidgetPropertySwitch.class);
GenUtil.addImportStatement(UniqueList.class);
GenUtil.addImportStatement(Selector.class);
GenUtil.addImportStatement(SelectorInterpreter.class);
for(Property each : properties){
	if(each.gettableTypes.size() + each.settableTypes.size() > 0){
		GenUtil.addImportStatement(each.propertyType);
	}
}

    stringBuffer.append(TEXT_4);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(GenUtil.endImport());
    stringBuffer.append(TEXT_6);
     for(Property each : properties){
	if(each.settableTypes.size() == 0) continue;
 
    stringBuffer.append(TEXT_7);
    stringBuffer.append(each.propertyName);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(each.propertyName);
    stringBuffer.append(TEXT_9);
     for(Class<?> eachWidgetType : each.settableTypes){ 
    stringBuffer.append(TEXT_10);
    stringBuffer.append(eachWidgetType.getSimpleName());
     } 
    stringBuffer.append(TEXT_11);
    stringBuffer.append(each.propertyName);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(each.propertyType.getSimpleName());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(each.propertyName);
    stringBuffer.append(TEXT_14);
     if(each.propertyType == Boolean.class) { 
    stringBuffer.append(TEXT_15);
    stringBuffer.append(each.propertyName);
    stringBuffer.append(TEXT_16);
     for(Class<?> eachWidgetType : each.settableTypes){ 
    stringBuffer.append(TEXT_17);
    stringBuffer.append(eachWidgetType.getSimpleName());
     } 
    stringBuffer.append(TEXT_18);
    stringBuffer.append(each.propertyName);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(each.propertyName);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(each.propertyName);
    stringBuffer.append(TEXT_21);
     } 
    stringBuffer.append(TEXT_22);
     } 
    stringBuffer.append(TEXT_23);
     for(Property each : properties){
	if(each.gettableTypes.size() == 0) continue;
 
    stringBuffer.append(TEXT_24);
    stringBuffer.append(each.propertyName);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(each.propertyName);
    stringBuffer.append(TEXT_26);
     for(Class<?> eachWidgetType : each.gettableTypes){ 
    stringBuffer.append(TEXT_27);
    stringBuffer.append(eachWidgetType.getSimpleName());
     } 
    stringBuffer.append(TEXT_28);
    stringBuffer.append(each.propertyType.getSimpleName());
    stringBuffer.append(TEXT_29);
    stringBuffer.append(each.propertyType == Boolean.class ? "is" : "get");
    stringBuffer.append(each.propertyName);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(each.propertyName);
    stringBuffer.append(TEXT_31);
     } 
    stringBuffer.append(TEXT_32);
    stringBuffer.append(TEXT_33);
    return stringBuffer.toString();
  }
}
