package kr.or.eclipse.swt.query.internal.generators;

import java.util.Comparator;

import org.eclipse.swt.widgets.Widget;

public final class ClassHierachyComparator implements Comparator<Class<?>> {
	public static final ClassHierachyComparator INSTANCE = new ClassHierachyComparator();

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
}