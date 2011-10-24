package kr.or.eclipse.swt.query.internal.gen;

import kr.or.eclipse.swt.query.internal.gen.Property;
import org.eclipse.swt.widgets.Widget;
import kr.or.eclipse.swt.query.util.WidgetSwitchWithArgument;

public class SetPropertySwitchGenerator
{
  protected static String nl;
  public static synchronized SetPropertySwitchGenerator create(String lineSeparator)
  {
    nl = lineSeparator;
    SetPropertySwitchGenerator result = new SetPropertySwitchGenerator();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/*" + NL + " * Copyright 2011 jeeeyul@gmail.com" + NL + " *" + NL + " * All rights reserved. This program and the accompanying materials" + NL + " * are made available under the terms of the Eclipse Public License v1.0" + NL + " * which accompanies this distribution, and is available at" + NL + " * http://www.eclipse.org/legal/epl-v10.html" + NL + " *" + NL + " */" + NL + "" + NL + "package kr.or.eclipse.swt.query.util.internal;" + NL;
  protected final String TEXT_3 = NL;
  protected final String TEXT_4 = NL;
  protected final String TEXT_5 = NL;
  protected final String TEXT_6 = "/*" + NL + " * This file was generated by SWT Query SDK." + NL + " * So you should not modify it manually." + NL + " * If you want to customize this code, contact below:" + NL + " *" + NL + " * http://code.google.com/p/swtquery/" + NL + " */" + NL + "public class Set";
  protected final String TEXT_7 = "Switch extends WidgetSwitchWithArgument<Object, ";
  protected final String TEXT_8 = "> {" + NL + "\tpublic void setProperty(Widget widget, ";
  protected final String TEXT_9 = " value){" + NL + "\t\tdoSwitch(widget, value);" + NL + "\t}" + NL;
  protected final String TEXT_10 = NL + "\tpublic Object case";
  protected final String TEXT_11 = "(";
  protected final String TEXT_12 = " widget, ";
  protected final String TEXT_13 = " value){" + NL + "\t\twidget.set";
  protected final String TEXT_14 = "(value);" + NL + "\t\treturn null;" + NL + "\t}";
  protected final String TEXT_15 = NL + "}";
  protected final String TEXT_16 = NL + "    ";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     
Property property = (Property)argument; 

    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
     GenUtil.startImport(); 
     GenUtil.addImportStatement(property.propertyType);
     GenUtil.addImportStatement(Widget.class);
     GenUtil.addImportStatement(WidgetSwitchWithArgument.class);
     for(Class<?> each : property.types) { 
     GenUtil.addImportStatement(each);
     } 
    stringBuffer.append(TEXT_3);
    stringBuffer.append(GenUtil.endImport() );
    stringBuffer.append(TEXT_4);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(property.propertyName );
    stringBuffer.append(TEXT_7);
    stringBuffer.append(property.propertyType.getSimpleName());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(property.propertyType.getSimpleName());
    stringBuffer.append(TEXT_9);
     for(Class<?> each : property.types) { 
    stringBuffer.append(TEXT_10);
    stringBuffer.append(each.getSimpleName());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(each.getSimpleName());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(property.propertyType.getSimpleName());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(property.propertyName);
    stringBuffer.append(TEXT_14);
     } 
    stringBuffer.append(TEXT_15);
    stringBuffer.append(TEXT_16);
    return stringBuffer.toString();
  }
}
