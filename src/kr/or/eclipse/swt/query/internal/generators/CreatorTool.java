package kr.or.eclipse.swt.query.internal.generators;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Widget;

public class CreatorTool {
	public static List<Constructor<?>> findBasicConstructor(Class<?> type) {
		ArrayList<Constructor<?>> result = new ArrayList<Constructor<?>>();
		for (Constructor<?> each : type.getConstructors()) {
			Class<?>[] argTypes = each.getParameterTypes();
			int modifiers = each.getModifiers();
			if (!Modifier.isPublic(modifiers)) {
				continue;
			}
			if (argTypes.length == 2 && Widget.class.isAssignableFrom(argTypes[0]) && argTypes[1] == int.class) {
				result.add(each);
			}

			else if (argTypes.length == 1 && Widget.class.isAssignableFrom(argTypes[0])) {
				result.add(each);
			}
		}

		return result;
	}

	public static void main(String[] args) {
		System.out.println(findBasicConstructor(Menu.class));
	}
}
