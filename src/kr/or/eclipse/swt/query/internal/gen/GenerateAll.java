package kr.or.eclipse.swt.query.internal.gen;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.eclipse.swt.widgets.Widget;

public class GenerateAll {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayList<Class<?>> types = loadTypes();

        File srcFolder = new File(args[0]);
        File utilFolder = new File(srcFolder, "kr/or/eclipse/swt/query/util");

        WidgetSwitchGenerator widgetSwitchGenerator = new WidgetSwitchGenerator();
        File widgetSwitchJavaFile = new File(utilFolder, "WidgetSwitch.java");
        FileOutputStream fos = new FileOutputStream(widgetSwitchJavaFile);
        write(widgetSwitchGenerator.generate(types), fos, "UTF-8");


        WidgetSwitchWithArgumentGenerator widgetSwitchWithArgumentGenerator = new WidgetSwitchWithArgumentGenerator();
        File widgetSwitchWithArgumentJavaFile = new File(utilFolder, "WidgetSwitchWithArgument.java");
        write(widgetSwitchWithArgumentGenerator.generate(types), new FileOutputStream(widgetSwitchWithArgumentJavaFile), "utf-8");

    }

    public static void write(String content, OutputStream stream, String encoding) throws IOException {
        byte[] data = content.getBytes(encoding);
        int block = 512;
        for (int i = 0; i < data.length; i += block) {
            int size = Math.min(data.length - i, block);
            stream.write(data, i, size);
        }
        stream.close();
    }

    private static ArrayList<Class<?>> loadTypes() throws IOException, ClassNotFoundException {
        InputStream is = GenerateAll.class.getResourceAsStream("target.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line = null;

        ArrayList<Class<?>> types = new ArrayList<Class<?>>();

        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) {
                continue;
            }
            Class<?> eachType = Class.forName(line, false, GenerateAll.class.getClassLoader());
            types.add(eachType);
        }
        reader.close();

        Comparator<Class<?>> comparator = new Comparator<Class<?>>() {
            @Override
            public int compare(Class<?> o1, Class<?> o2) {
                if (o1 == o2) {
                    return 0;
                }

                if (o1.getSuperclass() == Widget.class && o2.getSuperclass() == Widget.class) {
                    return o1.getName().compareTo(o2.getName());
                }

                if (isSuperType(o1, o2)) {
                    return 1;
                } else if (isSuperType(o2, o1)) {
                    return -1;
                }

                if (o1.getSuperclass() != Widget.class) {
                    o1 = o1.getSuperclass();
                }

                if (o2.getSuperclass() != Widget.class) {
                    o2 = o2.getSuperclass();
                }
                return compare(o1, o2);
            }

            boolean isSuperType(Class<?> mayParent, Class<?> mayChild) {
                Class<?> finger = mayChild;

                while (finger.getSuperclass() != null) {
                    finger = finger.getSuperclass();
                    if (finger.equals(mayParent)) {
                        return true;
                    }
                }
                return false;
            }
        };
        Collections.sort(types, comparator);
        return types;
    }

    public static String read(InputStream stream, String encoding) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte buf[] = new byte[512];
        int len = -1;
        while ((len = stream.read(buf)) != -1) {
            buffer.write(buf, 0, len);
        }
        stream.close();
        buffer.close();
        String result = new String(buffer.toByteArray(), encoding);

        return result;
    }
}
