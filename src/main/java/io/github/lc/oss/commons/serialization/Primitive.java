package io.github.lc.oss.commons.serialization;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_EMPTY)
public class Primitive<T> implements Jsonable {
    static final Set<Class<?>> ALLOWED_TYPES = Collections.unmodifiableSet(new HashSet<>(Arrays.asList( //
            String.class, //
            Date.class, //
            Number.class, //
            Boolean.class)));

    static boolean isAllowed(Object value) {
        if (value == null) {
            return true;
        }

        if (Primitive.ALLOWED_TYPES.stream().anyMatch(c -> Primitive.is(value, c))) {
            return true;
        }

        return false;
    }

    static boolean is(Object value, Class<?> a) {
        return a.isAssignableFrom(value.getClass());
    }

    private final T value;

    @JsonCreator
    public Primitive(@JsonProperty("value") T value) {
        if (!Primitive.isAllowed(value)) {
            throw new IllegalArgumentException("Illegal value type detected");
        }
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }
}
