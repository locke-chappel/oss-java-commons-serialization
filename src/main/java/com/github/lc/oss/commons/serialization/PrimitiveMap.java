package com.github.lc.oss.commons.serialization;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Specialized map that meets Jsonable requirements safely
 */
@JsonInclude(value = Include.NON_EMPTY)
public class PrimitiveMap<T> extends HashMap<String, T> implements Jsonable {
    private static final Set<Class<?>> EXTRA_ALLOWED_TYPES = Collections.unmodifiableSet(new HashSet<>(Arrays.asList( //
            Jsonable.class//
    )));

    static boolean isAllowed(Object value) {
        if (value == null) {
            return true;
        }

        if (Primitive.isAllowed(value)) {
            return true;
        }

        if (PrimitiveMap.EXTRA_ALLOWED_TYPES.stream().anyMatch(c -> Primitive.is(value, c))) {
            return true;
        }

        if (PrimitiveMap.isCollectionOf(value, String.class)) {
            /* String collections are OK */
            return true;
        }

        return false;
    }

    static boolean isCollectionOf(Object value, Class<?> type) {
        if (!(value instanceof Collection)) {
            return false;
        }

        Collection<?> c = (Collection<?>) value;
        if (c.isEmpty()) {
            /* Unable to determine type of empty collection, assume its bad */
            return false;
        }

        return Primitive.is(c.iterator().next(), type);
    }

    private static final long serialVersionUID = -1093965335422756542L;

    public PrimitiveMap() {
        super();
    }

    public PrimitiveMap(Map<String, ? extends T> src) {
        this.putAll(src);
    }

    public <SourceType> PrimitiveMap(Collection<SourceType> src, Function<SourceType, String> keyTransformer, Function<SourceType, T> valueTransformer) {
        super(src.stream(). //
                collect(Collectors.toMap( //
                        v -> keyTransformer.apply(v), //
                        v -> valueTransformer.apply(v))));
    }

    @Override
    public T put(String key, T value) {
        if (!PrimitiveMap.isAllowed(value)) {
            throw new IllegalArgumentException("Illegal value type: " + value.getClass().getCanonicalName());
        }

        return super.put(key, value);
    }

    @Override
    public void putAll(Map<? extends String, ? extends T> m) {
        if (m.isEmpty()) {
            return;
        }

        boolean allAreAllowed = m.values().parallelStream().allMatch(v -> PrimitiveMap.isAllowed(v));
        if (!allAreAllowed) {
            throw new IllegalArgumentException("Illegal value type detected");
        }
        super.putAll(m);
    }
}
