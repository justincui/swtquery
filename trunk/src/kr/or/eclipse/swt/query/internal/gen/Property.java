package kr.or.eclipse.swt.query.internal.gen;

import java.util.ArrayList;
import java.util.List;

public class Property {
    public String propertyName;
    public Class<?> propertyType;
    public List<Class<?>> gettableTypes = new ArrayList<Class<?>>();
    public List<Class<?>> settableTypes = new ArrayList<Class<?>>();
    public boolean isValid = true;

    public String importStatement() {
        if (this.propertyType.isArray()) {
            return "import " + this.propertyType.getComponentType().getCanonicalName() + ";";
        } else {
            return "import " + this.propertyType.getCanonicalName() + ";";
        }
    }
}
