package kr.or.eclipse.swt.query.internal.generators;

import java.util.HashSet;

public class GenUtil {
	private static HashSet<String> alreadyImported = new HashSet<String>();

	public static String addImportStatement(Class<?> type) {
		while (type.isArray()) {
			type = type.getComponentType();
		}

		if (type.isPrimitive()) {
			return "";
		}
		String canonicalName = type.getCanonicalName();
		if (alreadyImported.contains(canonicalName)) {
			return "";
		} else {
			alreadyImported.add(canonicalName);
			return "import " + canonicalName + ";";
		}
	}

	public static String getImportStatement(Class<?> type) {
		while (type.isArray()) {
			type = type.getComponentType();
		}

		if (type.isPrimitive()) {
			return "";
		}
		String canonicalName = type.getCanonicalName();
		return "import " + canonicalName + ";";
	}

	public static final void startImport() {
		alreadyImported.clear();
	}

	public static final String endImport() {
		StringBuffer buffer = new StringBuffer();
		for (String each : alreadyImported) {
			if (each.trim().isEmpty()) {
				continue;
			}
			buffer.append("import " + each + ";\r\n");
		}
		alreadyImported.clear();
		return buffer.toString();
	}
}
