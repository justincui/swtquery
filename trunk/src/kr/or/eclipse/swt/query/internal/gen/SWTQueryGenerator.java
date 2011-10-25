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
  protected final String TEXT_4 = NL + "import org.eclipse.swt.layout.*;" + NL + "import org.eclipse.swt.widgets.*;" + NL + "import org.eclipse.swt.*;" + NL;
  protected final String TEXT_5 = NL;
  protected final String TEXT_6 = NL + NL + "public final class SWTQuery {" + NL + "" + NL + "\tpublic static SWTQuery $(Widget w) {" + NL + "\t\treturn new SWTQuery(w);" + NL + "\t}" + NL + "\t" + NL + "\tpublic static SWTQuery $(Event event) {" + NL + "\t\treturn new SWTQuery(event.widget);" + NL + "\t}" + NL + "\t" + NL + "\tpublic static SWTQuery $(Widget context, String selector) {" + NL + "\t\treturn new SWTQuery(context).select(selector);" + NL + "\t}" + NL + "\t" + NL + "\tprivate List<Widget> items;" + NL + "" + NL + "\tpublic SWTQuery(List<Widget> itemList) {" + NL + "\t\tthis.items = itemList;" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery(Widget... items) {" + NL + "\t\tthis.items = new ArrayList<Widget>();" + NL + "\t\tfor (Widget each : items) {" + NL + "\t\t\tthis.items.add(each);" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery setGridLayoutData(int style) {" + NL + "\t\tfor (Widget each : items) {" + NL + "\t\t\tWidgetPropertySwitch.setLayoutData(each, new GridData(style));" + NL + "\t\t}" + NL + "\t\treturn this;" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery setGridLayoutData(int horizontalAlignment, int verticalAlignment, boolean grabExcessHorizontalSpace," + NL + "\t\t\tboolean grabExcessVerticalSpace, int horizontalSpan, int verticalSpan) {" + NL + "\t\tfor (Widget each : items) {" + NL + "\t\t\tWidgetPropertySwitch.setLayoutData(each, new GridData(horizontalAlignment, verticalAlignment," + NL + "\t\t\t\t\tgrabExcessHorizontalSpace, grabExcessVerticalSpace, horizontalSpan, verticalSpan));" + NL + "\t\t}" + NL + "\t\treturn this;" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery select(String selector) {" + NL + "\t\tList<Selector> selectors = SelectorInterpreter.build(selector);" + NL + "" + NL + "\t\tList<Widget> result = new UniqueList<Widget>();" + NL + "\t\tfor (Selector each : selectors) {" + NL + "\t\t\tresult.addAll(each.select(items));" + NL + "\t\t}" + NL + "\t\treturn new SWTQuery(result);" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery addListener(int eventType, Listener listener) {" + NL + "\t\tfor (Widget each : this.items) {" + NL + "\t\t\teach.addListener(eventType, listener);" + NL + "\t\t}" + NL + "\t\treturn this;" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery each(IWidgetFunction f) {" + NL + "\t\tfor (Widget each : this.items) {" + NL + "\t\t\tf.doFunction(each);" + NL + "\t\t}" + NL + "\t\treturn this;" + NL + "\t}" + NL + "" + NL + "\t@SuppressWarnings(\"unchecked\")" + NL + "\tpublic <T> T getData(String key) {" + NL + "\t\tif (items.size() > 0) {" + NL + "\t\t\treturn (T) items.get(0).getData(key);" + NL + "\t\t} else {" + NL + "\t\t\treturn null;" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery layout() {" + NL + "\t\tfor (Widget each : this.items) {" + NL + "\t\t\tif (each instanceof Composite) {" + NL + "\t\t\t\t((Composite) each).layout();" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn this;" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery layout(boolean changed) {" + NL + "\t\tfor (Widget each : this.items) {" + NL + "\t\t\tif (each instanceof Composite) {" + NL + "\t\t\t\t((Composite) each).layout(changed);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn this;" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery layout(boolean changed, boolean all) {" + NL + "\t\tfor (Widget each : this.items) {" + NL + "\t\t\tif (each instanceof Composite) {" + NL + "\t\t\t\t((Composite) each).layout(changed, all);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn this;" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery next() {" + NL + "\t\treturn next(1);" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery next(int delta) {" + NL + "\t\treturn translate(delta);" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery parent() {" + NL + "\t\tArrayList<Widget> parents = new ArrayList<Widget>();" + NL + "\t\tfor (Widget each : this.items) {" + NL + "\t\t\tWidget eachParent = ParentSwitch.INSTANCE.doSwitch(each);" + NL + "\t\t\tif (eachParent != null) {" + NL + "\t\t\t\tparents.add(eachParent);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn parent();" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery prev() {" + NL + "\t\treturn prev(-1);" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery prev(int delta) {" + NL + "\t\treturn translate(delta);" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery redraw() {" + NL + "\t\tfor (Widget each : this.items) {" + NL + "\t\t\tif (each instanceof Control) {" + NL + "\t\t\t\t((Control) each).redraw();" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn this;" + NL + "\t}" + NL + "" + NL + "\tpublic SWTQuery setData(String key, Object data) {" + NL + "\t\tfor (Widget each : items) {" + NL + "\t\t\teach.setData(key, data);" + NL + "\t\t}" + NL + "\t\treturn this;" + NL + "\t}" + NL + "" + NL + "\tprivate SWTQuery translate(int delta) {" + NL + "\t\tArrayList<Widget> result = new ArrayList<Widget>();" + NL + "" + NL + "\t\tfor (Widget each : this.items) {" + NL + "\t\t\tWidget parent = ParentSwitch.INSTANCE.doSwitch(each);" + NL + "\t\t\tif (parent == null) {" + NL + "\t\t\t\tcontinue;" + NL + "\t\t\t}" + NL + "\t\t\tList<Widget> sibilings = ChildrenSwitch.INSTANCE.doSwitch(parent);" + NL + "\t\t\tint index = sibilings.indexOf(each);" + NL + "" + NL + "\t\t\tint newIndex = index + delta;" + NL + "\t\t\tif (newIndex >= 0 && newIndex < sibilings.size()) {" + NL + "\t\t\t\tresult.add(sibilings.get(newIndex));" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "" + NL + "\t\treturn new SWTQuery(result);" + NL + "\t}" + NL + "\t" + NL + "\tpublic SWTQuery schedule(final IWidgetFunction function) {" + NL + "\t\tDisplay.getCurrent().asyncExec(new Runnable() {" + NL + "\t\t\t@Override" + NL + "\t\t\tpublic void run() {" + NL + "\t\t\t\tSWTQuery.this.each(function);" + NL + "\t\t\t}" + NL + "\t\t});" + NL + "\t\treturn this;" + NL + "\t}" + NL;
  protected final String TEXT_7 = NL + "\t" + NL + "\tpublic SWTQuery set";
  protected final String TEXT_8 = "(";
  protected final String TEXT_9 = " value){" + NL + "\t\tfor(Widget each : items){" + NL + "\t\t\tWidgetPropertySwitch.set";
  protected final String TEXT_10 = "(each, value);" + NL + "\t\t}" + NL + "\t\treturn this;" + NL + "\t}" + NL + "\t" + NL + "\tpublic ";
  protected final String TEXT_11 = " get";
  protected final String TEXT_12 = "(){" + NL + "\t\tif(items.size() > 0){" + NL + "\t\t\treturn WidgetPropertySwitch.get";
  protected final String TEXT_13 = "(items.get(0));" + NL + "\t\t}" + NL + "\t\telse{" + NL + "\t\t\treturn null;" + NL + "\t\t}" + NL + "\t}";
  protected final String TEXT_14 = NL + NL + "}";
  protected final String TEXT_15 = NL;

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
GenUtil.addImportStatement(IWidgetFilter.class);
GenUtil.addImportStatement(ChildrenSwitch.class);
GenUtil.addImportStatement(ParentSwitch.class);
GenUtil.addImportStatement(WidgetPropertySwitch.class);
GenUtil.addImportStatement(UniqueList.class);
GenUtil.addImportStatement(Selector.class);
GenUtil.addImportStatement(SelectorInterpreter.class);

for(Property each : properties){
	GenUtil.addImportStatement(each.propertyType);
}

    stringBuffer.append(TEXT_4);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(GenUtil.endImport());
    stringBuffer.append(TEXT_6);
     for(Property each : properties){ 
    stringBuffer.append(TEXT_7);
    stringBuffer.append(each.propertyName);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(each.propertyType.getSimpleName());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(each.propertyName);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(each.propertyType.getSimpleName());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(each.propertyName);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(each.propertyName);
    stringBuffer.append(TEXT_13);
     } 
    stringBuffer.append(TEXT_14);
    stringBuffer.append(TEXT_15);
    return stringBuffer.toString();
  }
}
