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
  protected final String TEXT_1 = "package kr.or.eclipse.swt.query.util;" + NL + "" + NL + "/*" + NL + " * generated by SWT Query SDK at ";
  protected final String TEXT_2 = NL + " */ ";
  protected final String TEXT_3 = NL + "import ";
  protected final String TEXT_4 = ";";
  protected final String TEXT_5 = NL + NL + "public class WidgetSwitchWithArgument<T, A> {";
  protected final String TEXT_6 = NL + "\tpublic T case";
  protected final String TEXT_7 = "(";
  protected final String TEXT_8 = " ";
  protected final String TEXT_9 = ", A arg){";
  protected final String TEXT_10 = "\t\t" + NL + "\t\treturn case";
  protected final String TEXT_11 = "(";
  protected final String TEXT_12 = ", arg);";
  protected final String TEXT_13 = "\t\t" + NL + "\t\treturn null;";
  protected final String TEXT_14 = NL + "\t}";
  protected final String TEXT_15 = NL + "\tpublic T doSwitch(Widget widget, A arg) {";
  protected final String TEXT_16 = NL + "\t\t";
  protected final String TEXT_17 = "{" + NL + "\t\t\treturn case";
  protected final String TEXT_18 = "((";
  protected final String TEXT_19 = ")widget, arg);" + NL + "\t\t}";
  protected final String TEXT_20 = NL + "    }" + NL + "}";
  protected final String TEXT_21 = NL + "    ";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     
@SuppressWarnings("unchecked")
List<Class<?>> types = (List<Class<?>>)argument; 

    stringBuffer.append(TEXT_1);
    stringBuffer.append(Calendar.getInstance().getTime() );
    stringBuffer.append(TEXT_2);
     for(Class<?> each : types) { 
    stringBuffer.append(TEXT_3);
    stringBuffer.append(each.getCanonicalName() );
    stringBuffer.append(TEXT_4);
     } 
    stringBuffer.append(TEXT_5);
     for(Class<?> each : types) { 
    
String eachTypeName = each.getSimpleName();
StringBuffer buffer = new StringBuffer(eachTypeName);
buffer.setCharAt(0, Character.toLowerCase(eachTypeName.charAt(0)));
String varName = buffer.toString();

    stringBuffer.append(TEXT_6);
    stringBuffer.append(eachTypeName);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(eachTypeName);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(varName );
    stringBuffer.append(TEXT_9);
     if(each != Widget.class) {
    stringBuffer.append(TEXT_10);
    stringBuffer.append(each.getSuperclass().getSimpleName() );
    stringBuffer.append(TEXT_11);
    stringBuffer.append(varName);
    stringBuffer.append(TEXT_12);
    }
     else { 
    stringBuffer.append(TEXT_13);
    }
    stringBuffer.append(TEXT_14);
     } 
    stringBuffer.append(TEXT_15);
     
int size = types.size();
for(int i=0; i< size; i++) { 
Class<?> each = types.get(i);

    stringBuffer.append(TEXT_16);
    stringBuffer.append((i > 0)? "else " :"");
    stringBuffer.append((i < size -1 )? "if(widget instanceof " + each.getSimpleName() +")" :"");
    stringBuffer.append(TEXT_17);
    stringBuffer.append(each.getSimpleName());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(each.getSimpleName());
    stringBuffer.append(TEXT_19);
     } 
    stringBuffer.append(TEXT_20);
    stringBuffer.append(TEXT_21);
    return stringBuffer.toString();
  }
}
