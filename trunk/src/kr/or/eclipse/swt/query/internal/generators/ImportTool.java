package kr.or.eclipse.swt.query.internal.generators;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.widgets.Menu;

public class ImportTool {
	private static Map<Class<?>, String> typeMap = new HashMap<Class<?>, String>();
	private static Map<String, Class<?>> simpleNameMap = new HashMap<String, Class<?>>();

	public static void startSession() {
		typeMap.clear();
		simpleNameMap.clear();
	}

	public static String getImportStatements() {
		StringBuffer result = new StringBuffer();
		for (Class<?> each : simpleNameMap.values()) {
			result.append(MessageFormat.format("import {0};\r\n", each.getCanonicalName()));
		}
		return result.toString().trim();
	}

	public static void addClass(Class<?> type) {
		if (typeMap.containsKey(type)) {
			return;
		}

		Class<?> importingType = getComponent(type);
		String simpleName = importingType.getSimpleName();
		if (simpleNameMap.containsKey(simpleName)) {
			typeMap.put(importingType, importingType.getCanonicalName());
		} else {
			typeMap.put(importingType, simpleName);
			simpleNameMap.put(simpleName, importingType);
		}
	}

	public static String getQualifier(Class<?> type) {
		Class<?> elementType = getComponent(type);
		String typeExpression = typeMap.get(elementType);
		if (typeExpression == null) {
			typeExpression = elementType.getCanonicalName();
			if (typeExpression.startsWith("java.lang.")) {
				typeExpression = typeExpression.substring(10);
			}
		}
		return typeExpression + getRepeated("[]", getArrayDimension(type));
	}

	public static void main(String[] args) {
		startSession();
		addClass(String[][][].class);
		addClass(String[][].class);
		addClass(Menu.class);
		addClass(java.awt.Menu.class);
		System.out.println(getImportStatements());
		System.out.println(getQualifier(java.awt.Menu.class));
	}

	private static Class<?> getComponent(Class<?> type) {
		while (type.isArray()) {
			type = type.getComponentType();
		}
		return type;
	}

	private static int getArrayDimension(Class<?> type) {
		int result = 0;
		while (type.isArray()) {
			type = type.getComponentType();
			result++;
		}
		return result;
	}

	private static String getRepeated(String word, int count) {
		StringBuffer buffer = new StringBuffer();
		while (count-- > 0) {
			buffer.append(word);
		}
		return buffer.toString();
	}
}
