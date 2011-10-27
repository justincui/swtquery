package kr.or.eclipse.swt.query.internal.generators;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;

public class CreatorTool {
	public static List<Constructor<?>> findBasicConstructor(Class<?> type) {
		ArrayList<Constructor<?>> result = new ArrayList<Constructor<?>>();
		for (Constructor<?> each : type.getConstructors()) {
			Class<?>[] argTypes = each.getParameterTypes();
			if (argTypes.length != 2) {
				continue;
			}

			if (Widget.class.isAssignableFrom(argTypes[0]) && argTypes[1] == int.class) {
				int modifiers = each.getModifiers();
				if (!Modifier.isPublic(modifiers)) {
					continue;
				}
				result.add(each);
			}
		}

		return result;
	}

	public static void main(String[] args) {
		System.out.println(findBasicConstructor(TreeItem.class));
	}
}
