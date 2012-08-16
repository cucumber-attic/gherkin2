package gherkin.formatter;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Mappable implements Serializable {
    private static final Integer NO_LINE = -1;

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Field> mappableFields = getMappableFields();
        for (Field field : mappableFields) {
            Object value;
            value = getValue(field);
            if (value != null && Mappable.class.isAssignableFrom(value.getClass())) {
                value = ((Mappable) value).toMap();
            }
            if (value != null && Collection.class.isAssignableFrom(value.getClass())) {
                List<Object> mappedValue = new ArrayList<Object>();
                for (Object o : (Collection) value) {
                    if (Mappable.class.isAssignableFrom(o.getClass())) {
                        mappedValue.add(((Mappable) o).toMap());
                    } else {
                        mappedValue.add(o);
                    }
                }
                value = mappedValue;
            }
            if (value != null && !Collections.EMPTY_LIST.equals(value) && !NO_LINE.equals(value)) {
                map.put(field.getName(), value);
            }
        }
        return map;
    }

    private Object getValue(Field field) {
        try {
            field.setAccessible(true);
            return field.get(this);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Field> getMappableFields() {
        List<Field> fields = new ArrayList<Field>();
        Class c = getClass();
        while (c != null) {
            for (Field field : c.getDeclaredFields()) {
                if (isMappable(field)) {
                    fields.add(field);
                }
            }
            c = c.getSuperclass();
        }
        return fields;
    }

    private boolean isMappable(Field field) {
        boolean transientField = Modifier.isTransient(field.getModifiers());
        boolean instanceField = !Modifier.isStatic(field.getModifiers());
        boolean mappableType = isMappableType(field.getType(), field.getGenericType());
        return !transientField && instanceField && mappableType;
    }

    private boolean isMappableType(Class type, Type genericType) {
        return String.class.equals(type) ||
                type.isPrimitive() ||
                Number.class.isAssignableFrom(type) ||
                Mappable.class.isAssignableFrom(type) ||
                genericType != null && Collection.class.isAssignableFrom(type) && isMappableCollection(genericType);
    }

    private boolean isMappableCollection(Type genericType) {
        if (genericType instanceof ParameterizedType) {
            Type[] parameters = ((ParameterizedType) genericType).getActualTypeArguments();
            return parameters[0] instanceof Class && isMappableType((Class) parameters[0], null);
        } else {
            return false;
        }
    }
}
