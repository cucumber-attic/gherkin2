package gherkin.formatter;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

public class Mappable {
    private static final Integer NO_LINE = -1;

    public Map<Object, Object> toMap() {
        Map<Object, Object> map = new HashMap<Object, Object>();
        for (Field field : getFields()) {
            Object value;
            try {
                field.setAccessible(true);
                value = field.get(this);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            if (value != null && Mappable.class.isAssignableFrom(value.getClass())) {
                value = ((Mappable) value).toMap();
            }
            if (value != null && List.class.isAssignableFrom(value.getClass())) {
                List<Object> mappedValue = new ArrayList<Object>();
                for (Object o : (List) value) {
                    if (Mappable.class.isAssignableFrom(o.getClass())) {
                        mappedValue.add(((Mappable) o).toMap());
                    } else {
                        mappedValue.add(o);
                    }
                }
                value = mappedValue;
            }
            if (value != null && !Collections.emptyList().equals(value) && !NO_LINE.equals(value)) {
                map.put(field.getName(), value);
            }
        }
        return map;
    }

    private List<Field> getFields() {
        List<Field> fields = new ArrayList<Field>();
        Class c = getClass();
        while (c != null) {
            for (Field field : c.getDeclaredFields()) {
                if (!Modifier.isStatic(field.getModifiers()) && !Modifier.isVolatile(field.getModifiers())) {
                    fields.add(field);
                }
            }
            c = c.getSuperclass();
        }
        return fields;
    }

}
