package kr.or.eclipse.swt.query.internal.gen;

import java.util.HashSet;

public class GenUtil {
    private static HashSet<String> alreadyImported = new HashSet<String>();

    public static String getImportStatement(Class<?> type) {
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

    public static final void startImport() {
        alreadyImported.clear();
    }

    public static final void endImport() {
        alreadyImported.clear();
    }
}
