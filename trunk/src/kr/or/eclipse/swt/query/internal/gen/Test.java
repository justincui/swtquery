package kr.or.eclipse.swt.query.internal.gen;

import java.lang.reflect.Method;

import org.eclipse.ui.forms.widgets.Section;

public class Test {
    public static void main(String[] args) throws SecurityException, NoSuchMethodException {
        try {
            Method m = Section.class.getDeclaredMethod("getText");
            System.out.println(m);
        } catch (NoSuchMethodException nsme) {

        }
    }
}
