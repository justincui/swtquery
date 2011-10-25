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
  protected final String TEXT_4 = NL + "import org.eclipse.swt.layout.*;" + NL + "import org.eclipse.swt.widgets.*;" + NL + "import org.eclipse.swt.*;" + NL + "import org.eclipse.swt.graphics.GC;" + NL;
  protected final String TEXT_5 = NL;
  protected final String TEXT_6 = NL + NL + "public final class SWTQuery {" + NL + "\tprivate static int CURRENT_DEBUG_COLOR = 0;" + NL + "\t" + NL + "\tpublic static SWTQuery $(Widget w) {" + NL + "\t\treturn new SWTQuery(w);" + NL + "\t}" + NL + "\t" + NL + "\tpublic static SWTQuery $(Event event) {" + NL + "\t\treturn new SWTQuery(event.widget);" + NL + "\t}" + NL + "\t" + NL + "\tpublic static SWTQuery $(Widget context, String selector) {" + NL + "\t\treturn new SWTQuery(context).select(selector);" + NL + "\t}" + NL + "\t" + NL + "\tprivate List<Widget> items;" + NL + "" + NL + "\tpublic SWTQuery(List<Widget> itemList) {" + NL + "\t\tthis.items = itemList;" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery(Widget... items) {" + NL + "\t\tthis.items = new ArrayList<Widget>();" + NL + "\t\tfor (Widget each : items) {" + NL + "\t\t\tthis.items.add(each);" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery setGridLayoutData(int style) {" + NL + "\t\tfor (Widget each : items) {" + NL + "\t\t\tWidgetPropertySwitch.setLayoutData(each, new GridData(style));" + NL + "\t\t}" + NL + "\t\treturn this;" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery setGridLayoutData(int horizontalAlignment, int verticalAlignment, boolean grabExcessHorizontalSpace," + NL + "\t\t\tboolean grabExcessVerticalSpace, int horizontalSpan, int verticalSpan) {" + NL + "\t\tfor (Widget each : items) {" + NL + "\t\t\tWidgetPropertySwitch.setLayoutData(each, new GridData(horizontalAlignment, verticalAlignment," + NL + "\t\t\t\t\tgrabExcessHorizontalSpace, grabExcessVerticalSpace, horizontalSpan, verticalSpan));" + NL + "\t\t}" + NL + "\t\treturn this;" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery select(String selector) {" + NL + "\t\tList<Selector> selectors = SelectorInterpreter.build(selector);" + NL + "" + NL + "\t\tList<Widget> result = new UniqueList<Widget>();" + NL + "\t\tfor (Selector each : selectors) {" + NL + "\t\t\tresult.addAll(each.select(items));" + NL + "\t\t}" + NL + "\t\treturn new SWTQuery(result);" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery addListener(int eventType, Listener listener) {" + NL + "\t\tfor (Widget each : this.items) {" + NL + "\t\t\teach.addListener(eventType, listener);" + NL + "\t\t}" + NL + "\t\treturn this;" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery each(IWidgetFunction f) {" + NL + "\t\tfor (Widget each : this.items) {" + NL + "\t\t\tf.doFunction(each);" + NL + "\t\t}" + NL + "\t\treturn this;" + NL + "\t}" + NL + "" + NL + "\t@SuppressWarnings(\"unchecked\")" + NL + "\tpublic <T> T getData(String key) {" + NL + "\t\tif (items.size() > 0) {" + NL + "\t\t\treturn (T) items.get(0).getData(key);" + NL + "\t\t} else {" + NL + "\t\t\treturn null;" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery layout() {" + NL + "\t\tfor (Widget each : this.items) {" + NL + "\t\t\tif (each instanceof Composite) {" + NL + "\t\t\t\t((Composite) each).layout();" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn this;" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery layout(boolean changed) {" + NL + "\t\tfor (Widget each : this.items) {" + NL + "\t\t\tif (each instanceof Composite) {" + NL + "\t\t\t\t((Composite) each).layout(changed);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn this;" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery layout(boolean changed, boolean all) {" + NL + "\t\tfor (Widget each : this.items) {" + NL + "\t\t\tif (each instanceof Composite) {" + NL + "\t\t\t\t((Composite) each).layout(changed, all);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn this;" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery next() {" + NL + "\t\treturn next(1);" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery next(int delta) {" + NL + "\t\treturn translate(delta);" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery parent() {" + NL + "\t\tArrayList<Widget> parents = new UniqueList<Widget>();" + NL + "\t\tfor (Widget each : this.items) {" + NL + "\t\t\tWidget eachParent = ParentSwitch.INSTANCE.doSwitch(each);" + NL + "\t\t\tif (eachParent != null) {" + NL + "\t\t\t\tparents.add(eachParent);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn new SWTQuery(parents);" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery prev() {" + NL + "\t\treturn prev(-1);" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery prev(int delta) {" + NL + "\t\treturn translate(delta);" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery redraw() {" + NL + "\t\tfor (Widget each : this.items) {" + NL + "\t\t\tif (each instanceof Control) {" + NL + "\t\t\t\t((Control) each).redraw();" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn this;" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery setData(String key, Object data) {" + NL + "\t\tfor (Widget each : items) {" + NL + "\t\t\teach.setData(key, data);" + NL + "\t\t}" + NL + "\t\treturn this;" + NL + "\t}" + NL + "" + NL + "\tprivate SWTQuery translate(int delta) {" + NL + "\t\tArrayList<Widget> result = new ArrayList<Widget>();" + NL + "" + NL + "\t\tfor (Widget each : this.items) {" + NL + "\t\t\tWidget parent = ParentSwitch.INSTANCE.doSwitch(each);" + NL + "\t\t\tif (parent == null) {" + NL + "\t\t\t\tcontinue;" + NL + "\t\t\t}" + NL + "\t\t\tList<Widget> sibilings = ChildrenSwitch.INSTANCE.doSwitch(parent);" + NL + "\t\t\tint index = sibilings.indexOf(each);" + NL + "" + NL + "\t\t\tint newIndex = index + delta;" + NL + "\t\t\tif (newIndex >= 0 && newIndex < sibilings.size()) {" + NL + "\t\t\t\tresult.add(sibilings.get(newIndex));" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "" + NL + "\t\treturn new SWTQuery(result);" + NL + "\t}" + NL + "\t" + NL + "\tpublic SWTQuery schedule(final IWidgetFunction function) {" + NL + "\t\tDisplay.getCurrent().asyncExec(new Runnable() {" + NL + "\t\t\t@Override" + NL + "\t\t\tpublic void run() {" + NL + "\t\t\t\tSWTQuery.this.each(function);" + NL + "\t\t\t}" + NL + "\t\t});" + NL + "\t\treturn this;" + NL + "\t}" + NL + "\t" + NL + "\tpublic SWTQuery addProperties(Map<String, Object> wClass) {" + NL + "\t\tfor (String each : wClass.keySet()) {" + NL + "\t\t\tfor (Widget item : items) {" + NL + "\t\t\t\tWidgetPropertySwitch.setProperty(item, each, wClass.get(each));" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn this;" + NL + "\t}" + NL;
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
  protected final String TEXT_32 = NL + "\tpublic SWTQuery debug(final String text) {" + NL + "\t\tfinal int color = (CURRENT_DEBUG_COLOR + 2);" + NL + "\t\tCURRENT_DEBUG_COLOR = (++CURRENT_DEBUG_COLOR % 15);" + NL + "\t\t" + NL + "\t\tthis.addListener(SWT.Paint, new Listener() {" + NL + "\t\t\t@Override" + NL + "\t\t\tpublic void handleEvent(Event event) {" + NL + "\t\t\t\tRectangle bounds = $(event).getBounds();" + NL + "\t\t\t\tGC gc = event.gc;" + NL + "\t\t\t\tgc.setAlpha(150);" + NL + "\t\t\t\tgc.setBackground(Display.getDefault().getSystemColor(color));" + NL + "\t\t\t\tgc.fillRectangle(0, 0, bounds.width - 1, bounds.height - 1);" + NL + "\t\t\t\tgc.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));" + NL + "\t\t\t\tgc.setAlpha(255);" + NL + "\t\t\t\tgc.drawText(text, 1, 1, true);" + NL + "\t\t\t}" + NL + "\t\t});" + NL + "\t\t" + NL + "\t\tthis.redraw();" + NL + "\t\treturn this;" + NL + "\t}" + NL + "\t" + NL + "\tpublic SWTQuery debug() {" + NL + "\t\tfinal int color = (CURRENT_DEBUG_COLOR + 2);" + NL + "\t\tCURRENT_DEBUG_COLOR = (++CURRENT_DEBUG_COLOR % 15);" + NL + "\t\t" + NL + "\t\tthis.addListener(SWT.Paint, new Listener() {" + NL + "\t\t\t@Override" + NL + "\t\t\tpublic void handleEvent(Event event) {" + NL + "\t\t\t\tRectangle bounds = $(event).getBounds();" + NL + "\t\t\t\tGC gc = event.gc;" + NL + "\t\t\t\tgc.setAlpha(150);" + NL + "\t\t\t\tgc.setBackground(Display.getDefault().getSystemColor(color));" + NL + "\t\t\t\tgc.fillRectangle(0, 0, bounds.width - 1, bounds.height - 1);" + NL + "\t\t\t\tgc.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));" + NL + "\t\t\t}" + NL + "\t\t});" + NL + "\t\tthis.redraw();" + NL + "\t\treturn this;" + NL + "\t}" + NL + "\t" + NL + "\tpublic SWTQuery createDropTarget(int style) {" + NL + "\t\tArrayList<Widget> dropTargets = new ArrayList<Widget>();" + NL + "\t\tfor (Widget each : items) {" + NL + "\t\t\tif (each instanceof Control) {" + NL + "\t\t\t\tdropTargets.add(new DropTarget((Control) each, style));" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn new SWTQuery(dropTargets);" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery getDropTarget(int style) {" + NL + "\t\tArrayList<Widget> dropTargets = new ArrayList<Widget>();" + NL + "\t\tfor (Widget each : items) {" + NL + "\t\t\tif (each instanceof Control) {" + NL + "\t\t\t\tObject target = ((Control) each).getData(DND.DROP_TARGET_KEY);" + NL + "\t\t\t\tif (target instanceof DropTarget) {" + NL + "\t\t\t\t\tdropTargets.add((Widget) target);" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn new SWTQuery(dropTargets);" + NL + "\t}" + NL + "" + NL + "\tpublic int size() {" + NL + "\t\treturn items.size();" + NL + "\t}" + NL + "\t" + NL + "\tpublic SWTQuery first(){" + NL + "\t\tif(items.size() > 0){" + NL + "\t\t\treturn new SWTQuery(items.get(0));" + NL + "\t\t}" + NL + "\t\telse {" + NL + "\t\t\treturn new SWTQuery(new UniqueList<Widget>());" + NL + "\t\t}" + NL + "\t}" + NL + "\t" + NL + "\tpublic SWTQuery last(){" + NL + "\t\tif(items.size() > 0){" + NL + "\t\t\treturn new SWTQuery(items.get(items.size()-1));" + NL + "\t\t}" + NL + "\t\telse {" + NL + "\t\t\treturn new SWTQuery(new UniqueList<Widget>());" + NL + "\t\t}" + NL + "\t}" + NL + "\t" + NL + "\tpublic SWTQuery get(int index){" + NL + "\t\tif(items.size() > index){" + NL + "\t\t\treturn new SWTQuery(items.get(index));" + NL + "\t\t}" + NL + "\t\telse {" + NL + "\t\t\treturn new SWTQuery(new UniqueList<Widget>());" + NL + "\t\t}" + NL + "\t}" + NL + "}";
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
GenUtil.addImportStatement(org.eclipse.swt.dnd.DropTarget.class);
GenUtil.addImportStatement(org.eclipse.swt.dnd.DND.class);
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
