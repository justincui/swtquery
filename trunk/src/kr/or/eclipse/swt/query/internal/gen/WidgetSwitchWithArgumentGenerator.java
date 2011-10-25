package kr.or.eclipse.swt.query.internal.gen;

import java.util.*;
import org.eclipse.swt.widgets.Widget;

public class WidgetSwitchWithArgumentGenerator
{
  protected static String nl;
  public static synchronized WidgetSwitchWithArgumentGenerator create(String lineSeparator)
  {
    nl = lineSeparator;
    WidgetSwitchWithArgumentGenerator result = new WidgetSwitchWithArgumentGenerator();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**" + NL + " * Copyright 2011 jeeeyul@gmail.com" + NL + " *" + NL + " * All rights reserved. This program and the accompanying materials" + NL + " * are made available under the terms of the Eclipse Public License v1.0" + NL + " * which accompanies this distribution, and is available at" + NL + " * http://www.eclipse.org/legal/epl-v10.html" + NL + " *" + NL + " */" + NL + "" + NL + "package kr.or.eclipse.swt.query.util;" + NL;
  protected final String TEXT_3 = NL + "import ";
  protected final String TEXT_4 = ";";
  protected final String TEXT_5 = NL;
  protected final String TEXT_6 = NL;
  protected final String TEXT_7 = "/*" + NL + " * This file was generated by SWT Query SDK." + NL + " * So you should not modify it manually." + NL + " * If you want to customize this code, contact below:" + NL + " *" + NL + " * http://code.google.com/p/swtquery/" + NL + " */" + NL + "public class WidgetSwitchWithArgument<T, A> {";
  protected final String TEXT_8 = NL + "    /**" + NL + "     * do switching for ";
  protected final String TEXT_9 = "." + NL + "     * @param ";
  protected final String TEXT_10 = " ";
  protected final String TEXT_11 = " to switch." + NL + "     * @param arg Argument object." + NL + "     * @return switching result." + NL + "     */" + NL + "\tpublic T case";
  protected final String TEXT_12 = "(";
  protected final String TEXT_13 = " ";
  protected final String TEXT_14 = ", A arg){";
  protected final String TEXT_15 = "\t\t" + NL + "\t\treturn case";
  protected final String TEXT_16 = "(";
  protected final String TEXT_17 = ", arg);";
  protected final String TEXT_18 = "\t\t" + NL + "\t\treturn null;";
  protected final String TEXT_19 = NL + "\t}";
  protected final String TEXT_20 = NL + "\tpublic T doSwitch(Widget widget, A arg) {";
  protected final String TEXT_21 = NL + "\t\t";
  protected final String TEXT_22 = "{" + NL + "\t\t\treturn case";
  protected final String TEXT_23 = "((";
  protected final String TEXT_24 = ")widget, arg);" + NL + "\t\t}";
  protected final String TEXT_25 = NL + "    }" + NL + "}";
  protected final String TEXT_26 = NL + "    ";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     
@SuppressWarnings("unchecked")
List<Class<?>> types = (List<Class<?>>)argument; 

    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
     GenUtil.startImport(); 
     for(Class<?> each : types) { 
    stringBuffer.append(TEXT_3);
    stringBuffer.append(each.getCanonicalName() );
    stringBuffer.append(TEXT_4);
     } 
     GenUtil.endImport(); 
    stringBuffer.append(TEXT_5);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(TEXT_7);
     for(Class<?> each : types) { 
    
String eachTypeName = each.getSimpleName();
StringBuffer buffer = new StringBuffer(eachTypeName);
buffer.setCharAt(0, Character.toLowerCase(eachTypeName.charAt(0)));
String varName = buffer.toString();

    stringBuffer.append(TEXT_8);
    stringBuffer.append(eachTypeName);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(varName);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(eachTypeName);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(eachTypeName);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(eachTypeName);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(varName );
    stringBuffer.append(TEXT_14);
     if(each != Widget.class) {
    stringBuffer.append(TEXT_15);
    stringBuffer.append(each.getSuperclass().getSimpleName() );
    stringBuffer.append(TEXT_16);
    stringBuffer.append(varName);
    stringBuffer.append(TEXT_17);
    }
     else { 
    stringBuffer.append(TEXT_18);
    }
    stringBuffer.append(TEXT_19);
     } 
    stringBuffer.append(TEXT_20);
     
int size = types.size();
for(int i=0; i< size; i++) { 
Class<?> each = types.get(i);

    stringBuffer.append(TEXT_21);
    stringBuffer.append((i > 0)? "else " :"");
    stringBuffer.append((i < size -1 )? "if(widget instanceof " + each.getSimpleName() +")" :"");
    stringBuffer.append(TEXT_22);
    stringBuffer.append(each.getSimpleName());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(each.getSimpleName());
    stringBuffer.append(TEXT_24);
     } 
    stringBuffer.append(TEXT_25);
    stringBuffer.append(TEXT_26);
    return stringBuffer.toString();
  }
}
