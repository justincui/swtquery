package kr.or.eclipse.swt.query.internal.gen;

import java.util.*;
import kr.or.eclipse.swt.query.internal.gen.Property;

public class PropertySwitchGenerator
{
  protected static String nl;
  public static synchronized PropertySwitchGenerator create(String lineSeparator)
  {
    nl = lineSeparator;
    PropertySwitchGenerator result = new PropertySwitchGenerator();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**" + NL + " * Copyright 2011 jeeeyul@gmail.com" + NL + " *" + NL + " * All rights reserved. This program and the accompanying materials" + NL + " * are made available under the terms of the Eclipse Public License v1.0" + NL + " * which accompanies this distribution, and is available at" + NL + " * http://www.eclipse.org/legal/epl-v10.html" + NL + " *" + NL + " */" + NL + "" + NL + "package kr.or.eclipse.swt.query.util;" + NL;
  protected final String TEXT_3 = NL + "import java.util.*;" + NL + "import org.eclipse.swt.widgets.Widget;" + NL + "import kr.or.eclipse.swt.query.util.internal.*;";
  protected final String TEXT_4 = NL;
  protected final String TEXT_5 = NL;
  protected final String TEXT_6 = NL;
  protected final String TEXT_7 = "/*" + NL + " * This file was generated by SWT Query SDK." + NL + " * So you should not modify it manually." + NL + " * If you want to customize this code, contact below:" + NL + " *" + NL + " * http://code.google.com/p/swtquery/" + NL + " */" + NL + "public class WidgetPropertySwitch{" + NL + "\tprivate static final Map<String, PropertyEntry> map = new HashMap<String, PropertyEntry>();" + NL + "\t" + NL + "\tprivate static class PropertyEntry{" + NL + "\t\tpublic String name;" + NL + "\t\tpublic Class<?> type;" + NL + "\t\t" + NL + "\t\tpublic PropertyEntry(String name, Class<?> type){" + NL + "\t\t\tthis.name = name.trim().toLowerCase();" + NL + "\t\t\tthis.type = type;" + NL + "\t\t}" + NL + "\t}" + NL + "\t" + NL + "\tstatic{";
  protected final String TEXT_8 = NL + "\t\tmap.put(\"";
  protected final String TEXT_9 = "\", new PropertyEntry(\"";
  protected final String TEXT_10 = "\", ";
  protected final String TEXT_11 = ".class));";
  protected final String TEXT_12 = "\t" + NL + "\t}" + NL;
  protected final String TEXT_13 = NL + "\tprivate static final Get";
  protected final String TEXT_14 = "Switch get";
  protected final String TEXT_15 = "Switch = new Get";
  protected final String TEXT_16 = "Switch();";
  protected final String TEXT_17 = NL + "\tprivate static final Set";
  protected final String TEXT_18 = "Switch set";
  protected final String TEXT_19 = "Switch = new Set";
  protected final String TEXT_20 = "Switch();";
  protected final String TEXT_21 = NL + "\t@SuppressWarnings(\"unchecked\")" + NL + "\tpublic static <T> T getProperty(Widget widget, String propertyName){";
  protected final String TEXT_22 = NL + "\t\t";
  protected final String TEXT_23 = " (propertyName.equalsIgnoreCase(\"";
  protected final String TEXT_24 = "\")){" + NL + "\t\t\treturn (T) get";
  protected final String TEXT_25 = "Switch.getProperty(widget);" + NL + "\t\t}" + NL + "\t\t";
  protected final String TEXT_26 = "\t" + NL + "\t\treturn null;" + NL + "\t}" + NL + "\t" + NL + "\tpublic static void setProperty(Widget widget, String propertyName, Object value){";
  protected final String TEXT_27 = NL + "\t\t";
  protected final String TEXT_28 = " (propertyName.equalsIgnoreCase(\"";
  protected final String TEXT_29 = "\")){" + NL + "\t\t\tset";
  protected final String TEXT_30 = "Switch.setProperty(widget, (";
  protected final String TEXT_31 = ")value);" + NL + "\t\t}" + NL + "\t\t";
  protected final String TEXT_32 = "\t" + NL + "\t}";
  protected final String TEXT_33 = NL + "\tpublic static ";
  protected final String TEXT_34 = " get";
  protected final String TEXT_35 = "(Widget widget){" + NL + "\t\treturn get";
  protected final String TEXT_36 = "Switch.getProperty(widget);" + NL + "\t}";
  protected final String TEXT_37 = NL + "\tpublic static void set";
  protected final String TEXT_38 = "(Widget widget, ";
  protected final String TEXT_39 = " value){" + NL + "\t\tset";
  protected final String TEXT_40 = "Switch.setProperty(widget, value);" + NL + "\t}";
  protected final String TEXT_41 = "\t" + NL + "" + NL + "\tpublic static Class<?>getPropertyType(String propertyName){" + NL + "\t\tPropertyEntry entry = map.get(propertyName.trim().toLowerCase());" + NL + "\t\tif(entry == null){" + NL + "\t\t\treturn null;" + NL + "\t\t}" + NL + "\t\telse{" + NL + "\t\t\treturn entry.type;" + NL + "\t\t}\t" + NL + "\t}" + NL + "" + NL + "\t\t" + NL + "}";
  protected final String TEXT_42 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     
@SuppressWarnings("unchecked")
Map<String, Property> properties = (Map<String, Property>)argument; 

    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
     GenUtil.startImport(); 
    stringBuffer.append(TEXT_3);
     for(Property each : properties.values()){ 
     GenUtil.addImportStatement(each.propertyType);
    }
    stringBuffer.append(TEXT_4);
    stringBuffer.append( GenUtil.endImport() );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(TEXT_7);
    for(Property each : properties.values()){ 
    stringBuffer.append(TEXT_8);
    stringBuffer.append(each.propertyName.trim().toLowerCase());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(each.propertyName);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(each.propertyType.getSimpleName());
    stringBuffer.append(TEXT_11);
    }
    stringBuffer.append(TEXT_12);
     for(String eachKey : properties.keySet()){ 
	Property each = properties.get(eachKey);  
	if(each.gettableTypes.size()>0){ 
    stringBuffer.append(TEXT_13);
    stringBuffer.append(eachKey);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(eachKey);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(eachKey);
    stringBuffer.append(TEXT_16);
     
	}
	if(each.settableTypes.size()>0){ 
    stringBuffer.append(TEXT_17);
    stringBuffer.append(eachKey);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(eachKey);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(eachKey);
    stringBuffer.append(TEXT_20);
     
	} 
} 

    stringBuffer.append(TEXT_21);
     boolean isFirst = true;
for(Property each : properties.values()){ 
    stringBuffer.append(TEXT_22);
    stringBuffer.append( isFirst ? "if" : "else if" );
    stringBuffer.append(TEXT_23);
    stringBuffer.append(each.propertyName);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(each.propertyName);
    stringBuffer.append(TEXT_25);
     isFirst = false; 
    }
    stringBuffer.append(TEXT_26);
     isFirst = true;
for(Property each : properties.values()){ 
if(each.settableTypes.size() == 0){
	continue;
}

    stringBuffer.append(TEXT_27);
    stringBuffer.append( isFirst ? "if" : "else if" );
    stringBuffer.append(TEXT_28);
    stringBuffer.append(each.propertyName);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(each.propertyName);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(each.propertyType.getSimpleName() );
    stringBuffer.append(TEXT_31);
     isFirst = false; 
    }
    stringBuffer.append(TEXT_32);
     for(Property each : properties.values()){
     if(each.gettableTypes.size() > 0) {
    stringBuffer.append(TEXT_33);
    stringBuffer.append(each.propertyType.getSimpleName());
    stringBuffer.append(TEXT_34);
    stringBuffer.append(each.propertyName);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(each.propertyName);
    stringBuffer.append(TEXT_36);
     } 
     if(each.settableTypes.size() > 0) { 
    stringBuffer.append(TEXT_37);
    stringBuffer.append(each.propertyName);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(each.propertyType.getSimpleName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(each.propertyName);
    stringBuffer.append(TEXT_40);
     } 
     } 
    stringBuffer.append(TEXT_41);
    stringBuffer.append(TEXT_42);
    return stringBuffer.toString();
  }
}
