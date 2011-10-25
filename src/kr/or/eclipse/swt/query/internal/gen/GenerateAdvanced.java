package kr.or.eclipse.swt.query.internal.gen;

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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.eclipse.swt.widgets.Widget;

public class GenerateAdvanced {
	private static File srcFolder;
	private static ArrayList<Class<?>> types;
	private static Map<String, Property> properties;

	public static void main(String[] args) throws IOException,
			ClassNotFoundException, SecurityException, NoSuchMethodException {
		srcFolder = new File(args[0]);

		System.out.print("타입을 로드하는 중...");
		types = loadTypes();
		System.out.println("완료.");

		System.out.print("프로퍼티를 로드하는 중...");
		loadProperties();
		System.out.println("완료.");

		System.out.print("프로퍼티 생성 중...");
		generatePropertySwitches();
		System.out.println("완료.");

		generateSWTTools();
	}

	private static void generateSWTTools() throws FileNotFoundException,
			IOException {
		File utilFolder = new File(srcFolder, "kr/or/eclipse/swt/query/util");
		if (!utilFolder.exists()) {
			utilFolder.mkdir();
		}

		SWTConstantsGenerator generator = new SWTConstantsGenerator();
		File file = new File(utilFolder, "SWTConstants.java");
		write(generator.generate(null), new FileOutputStream(file));
	}

	private static void generatePropertySwitches()
			throws FileNotFoundException, IOException, SecurityException,
			NoSuchMethodException {
		File utilFolder = new File(srcFolder, "kr/or/eclipse/swt/query/util");
		if (!utilFolder.exists()) {
			utilFolder.mkdir();
		}

		File internalUtilFolder = new File(utilFolder, "internal");
		if (!internalUtilFolder.exists()) {
			internalUtilFolder.mkdir();
		}
		GetPropertySwitchGenerator getterGenerator = new GetPropertySwitchGenerator();
		SetPropertySwitchGenerator setterGenerator = new SetPropertySwitchGenerator();

		for (Property each : properties.values()) {
			if (!each.isValid) {
				continue;
			}

			File getterFile = new File(internalUtilFolder, "Get"
					+ each.propertyName + "Switch.java");
			File setterFile = new File(internalUtilFolder, "Set"
					+ each.propertyName + "Switch.java");
			write(getterGenerator.generate(each), new FileOutputStream(
					getterFile));
			write(setterGenerator.generate(each), new FileOutputStream(
					setterFile));
		}

		PropertySwitchGenerator psGenerator = new PropertySwitchGenerator();
		File psFile = new File(utilFolder, "WidgetPropertySwitch.java");
		write(psGenerator.generate(properties), new FileOutputStream(psFile));
	}

	private static Map<String, Property> loadProperties() throws IOException {
		InputStream is = GenerateAdvanced.class
				.getResourceAsStream("filtered-property.txt");
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
				if ((eachMethod.getModifiers() & Modifier.PUBLIC) == 0) {
					continue;
				}

				if (!eachMethod.getName().startsWith("get")) {
					continue;
				}

				if (eachMethod.getReturnType() == null) {
					continue;
				}

				if (eachMethod.getParameterTypes().length > 0) {
					continue;
				}

				if (eachMethod.getAnnotation(Deprecated.class) != null) {
					continue;
				}

				String propertyName = eachMethod.getName().substring(3);
				if (filters.contains(propertyName)) {
					continue;
				}
				Class<?> propertyType = eachMethod.getReturnType();
				String setterName = "set" + propertyName;
				try {
					Method setter = each.getMethod(setterName, propertyType);
					if (setter == null) {
						continue;
					}
				} catch (NoSuchMethodException e) {
					continue;
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

				if (!property.types.contains(each)) {
					property.types.add(each);
				}
				if (propertyType != property.propertyType) {
					property.isValid = false;
				}
			}
		}

		for (Property each : properties.values().toArray(
				new Property[properties.size()])) {
			if (!each.isValid) {
				properties.remove(each.propertyName);
			}
		}
		return properties;
	}

	public static void write(String content, OutputStream stream,
			String encoding) throws IOException {
		byte[] data = content.getBytes(encoding);
		int block = 512;
		for (int i = 0; i < data.length; i += block) {
			int size = Math.min(data.length - i, block);
			stream.write(data, i, size);
		}
		stream.close();
	}

	public static void write(String content, OutputStream stream)
			throws IOException {
		write(content, stream, System.getProperty("file.encoding"));
	}

	private static ArrayList<Class<?>> loadTypes() throws IOException,
			ClassNotFoundException {
		InputStream is = GenerateAdvanced.class
				.getResourceAsStream("target.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line = null;

		ArrayList<Class<?>> types = new ArrayList<Class<?>>();

		while ((line = reader.readLine()) != null) {
			if (line.trim().isEmpty()) {
				continue;
			}
			Class<?> eachType = Class.forName(line, false,
					GenerateAdvanced.class.getClassLoader());
			types.add(eachType);
		}
		reader.close();

		Comparator<Class<?>> comparator = new Comparator<Class<?>>() {
			@Override
			public int compare(Class<?> o1, Class<?> o2) {
				if (o1 == o2) {
					return 0;
				}

				if (o1.getSuperclass() == Widget.class
						&& o2.getSuperclass() == Widget.class) {
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

	public static String read(InputStream stream, String encoding)
			throws IOException {
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
