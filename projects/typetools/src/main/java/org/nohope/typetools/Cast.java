package org.nohope.typetools;

import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Date: 31.07.12
 * Time: 15:12
 */
public final class Cast {
    private Cast() {

    }

    @SuppressWarnings("unchecked")
    public static <T> T as(final Object value, final Class<T> clazz) {
        if (null == value) {
            return null;
        }

        if (clazz.isAssignableFrom(value.getClass())) {
            return (T) value;
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> T as(final Object value, final TypeReference<T> ref) {
        final Class<T> clazz = (Class)ref.getType();
        return as(value, clazz);
    }
}
