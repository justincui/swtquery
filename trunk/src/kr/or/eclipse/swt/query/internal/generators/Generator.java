package kr.or.eclipse.swt.query.internal.generators;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.eclipse.swt.widgets.Widget;

public class Generator {
	private static Map<String, Property> properties;
	private static File srcFolder;
	private static ArrayList<Class<?>> types;

	private static File ensureFolder(File file) {
		Stack<File> stack = new Stack<File>();
		File finger = file;
		while (!finger.exists()) {
			stack.push(finger);
			finger = finger.getParentFile();
		}

		while (!stack.isEmpty()) {
			stack.pop().mkdir();
		}

		return file;
	}

	private static void generatePropertySwitches() throws FileNotFoundException, IOException, SecurityException,
	NoSuchMethodException {
		File utilFolder = getFolder("kr/or/eclipse/swt/query/util");
		File internalUtilFolder = getFolder("kr/or/eclipse/swt/query/util/internal");

		GetPropertySwitchGenerator getterGenerator = new GetPropertySwitchGenerator();
		SetPropertySwitchGenerator setterGenerator = new SetPropertySwitchGenerator();

		for (Property each : properties.values()) {
			if (!each.isValid) {
				continue;
			}

			File getterFile = new File(internalUtilFolder, "Get" + each.propertyName + "Switch.java");
			File setterFile = new File(internalUtilFolder, "Set" + each.propertyName + "Switch.java");
			if (each.gettableTypes.size() > 0) {
				write(getterGenerator.generate(each), new FileOutputStream(getterFile));
			}

			if (each.settableTypes.size() > 0) {
				write(setterGenerator.generate(each), new FileOutputStream(setterFile));
			}
		}

		PropertySwitchGenerator psGenerator = new PropertySwitchGenerator();
		File psFile = new File(utilFolder, "WidgetPropertySwitch.java");
		write(psGenerator.generate(properties), new FileOutputStream(psFile));
	}

	private static void generateSwitch() throws FileNotFoundException, IOException {
		File utilFolder = getFolder("kr/or/eclipse/swt/query/util");

		WidgetSwitchGenerator widgetSwitchGenerator = new WidgetSwitchGenerator();
		File widgetSwitchJavaFile = new File(utilFolder, "WidgetSwitch.java");
		FileOutputStream fos = new FileOutputStream(widgetSwitchJavaFile);
		write(widgetSwitchGenerator.generate(types), fos);

		WidgetSwitchWithArgumentGenerator widgetSwitchWithArgumentGenerator = new WidgetSwitchWithArgumentGenerator();
		File widgetSwitchWithArgumentJavaFile = new File(utilFolder, "WidgetSwitchWithArgument.java");
		write(widgetSwitchWithArgumentGenerator.generate(types), new FileOutputStream(widgetSwitchWithArgumentJavaFile));

		WidgetClassSwitchGenerator cGenerator = new WidgetClassSwitchGenerator();
		File wcsgFile = new File(utilFolder, "WidgetClassSwitch.java");
		write(cGenerator.generate(types), new FileOutputStream(wcsgFile));

		WidgetClassSwitchWithArgumentGenerator cGenerator2 = new WidgetClassSwitchWithArgumentGenerator();
		File wcsgFile2 = new File(utilFolder, "WidgetClassSwitchWithArgument.java");
		write(cGenerator2.generate(types), new FileOutputStream(wcsgFile2));
	}

	private static void generateSWTQuery() throws FileNotFoundException, IOException {
		File folder = getFolder("kr/or/eclipse/swt/query");
		SWTQueryGenerator generator = new SWTQueryGenerator();

		File file = new File(folder, "SWTQuery.java");
		write(generator.generate(properties.values()), new FileOutputStream(file));
	}

	private static void generateSWTTools() throws FileNotFoundException, IOException {
		File utilFolder = getFolder("kr/or/eclipse/swt/query/util");
		if (!utilFolder.exists()) {
			utilFolder.mkdir();
		}

		SWTConstantsGenerator generator = new SWTConstantsGenerator();
		File file = new File(utilFolder, "SWTConstants.java");
		write(generator.generate(null), new FileOutputStream(file));

		EventUtilGenerator eventUtilGenerator = new EventUtilGenerator();
		write(eventUtilGenerator.generate(null), new FileOutputStream(new File(utilFolder, "EventUtil.java")));
	}

	private static File getFolder(String path) {
		return ensureFolder(new File(srcFolder, path));
	}

	private static Map<String, Property> loadProperties() throws IOException {
		InputStream is = Generator.class.getResourceAsStream("filtered-property.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String eachFilter = null;
		HashSet<String> filters = new HashSet<String>();
		while ((eachFilter = reader.readLine()) != null) {
			if (!eachFilter.trim().isEmpty()) {
				filters.add(eachFilter.trim());
			}
		}

		properties = new HashMap<String, Property>();

		for (Class<?> each : types) {
			Method[] methods = each.getDeclaredMethods();
			for (Method eachMethod : methods) {
				boolean setterExist = true;

				if ((eachMethod.getModifiers() & Modifier.PUBLIC) == 0) {
					continue;
				}
				if (eachMethod.getParameterTypes().length > 0) {
					continue;
				}

				if (eachMethod.getReturnType() == null) {
					continue;
				}
				Class<?> propertyType = eachMethod.getReturnType();

				String propertyName;
				if (propertyType == Boolean.class || propertyType == boolean.class) {
					if (!eachMethod.getName().startsWith("is")) {
						continue;
					}
					propertyName = eachMethod.getName().substring(2);

				} else {
					propertyName = eachMethod.getName().substring(3);
					if (!eachMethod.getName().startsWith("get")) {
						continue;
					}
				}

				if (eachMethod.getAnnotation(Deprecated.class) != null) {
					continue;
				}

				if (filters.contains(propertyName)) {
					continue;
				}

				String setterName = "set" + propertyName;
				try {
					each.getMethod(setterName, propertyType);
				} catch (NoSuchMethodException e) {
					setterExist = false;
				}

				if (propertyType.isPrimitive()) {
					if (propertyType == int.class) {
						propertyType = Integer.class;
					} else if (propertyType == long.class) {
						propertyType = Long.class;
					} else if (propertyType == boolean.class) {
						propertyType = Boolean.class;
					} else if (propertyType == double.class) {
						propertyType = Double.class;
					} else if (propertyType == char.class) {
						propertyType = Character.class;
					}
				}

				Property property = properties.get(propertyName);
				if (property == null) {
					property = new Property();
					property.propertyName = propertyName;
					property.propertyType = propertyType;
					properties.put(propertyName, property);
				}

				if (propertyType != property.propertyType) {
					property.isValid = false;
				}

				if (!property.gettableTypes.contains(each)) {
					property.gettableTypes.add(each);
				}
				if (setterExist && !property.settableTypes.contains(each)) {
					property.settableTypes.add(each);
				}
			}
		}

		for (Property each : properties.values().toArray(new Property[properties.size()])) {
			if (!each.isValid) {
				properties.remove(each.propertyName);
			}
		}
		return properties;
	}

	private static ArrayList<Class<?>> loadTypes() throws IOException, ClassNotFoundException {
		InputStream is = Generator.class.getResourceAsStream("target.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line = null;

		ArrayList<Class<?>> types = new ArrayList<Class<?>>();

		while ((line = reader.readLine()) != null) {
			if (line.trim().isEmpty()) {
				continue;
			}
			Class<?> eachType = Class.forName(line, false, Generator.class.getClassLoader());
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

	public static void main(String[] args) throws IOException, ClassNotFoundException, SecurityException, NoSuchMethodException {
		List<String> argList = Arrays.asList(args);

		srcFolder = new File(args[0]);

		System.out.println("Loading types...");
		types = loadTypes();

		System.out.println("Loading properties...");
		loadProperties();

		if (argList.contains("basic")) {
			System.out.println("Generating switches...");
			generateSwitch();
		}

		if (argList.contains("advanced")) {
			System.out.println("Generating property switches...");
			generatePropertySwitches();

			System.out.println("Generating widget class switches...");
			generateCreatorSwitches();

			System.out.println("Generating other tools...");
			generateSWTTools();

			System.out.println("Generating SWTQuery...");
			generateSWTQuery();
		}
	}

	private static void generateCreatorSwitches() throws FileNotFoundException, IOException {
		File internalUtilFolder = getFolder("kr/or/eclipse/swt/query/util/internal");
		File file = new File(internalUtilFolder, "CreatorSwitch.java");

		String contents = new CreatorSwitchGenerator().generate(types);
		write(contents, new FileOutputStream(file));

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

	public static void write(String content, OutputStream stream) throws IOException {
		write(content, stream, System.getProperty("file.encoding"));
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
}
