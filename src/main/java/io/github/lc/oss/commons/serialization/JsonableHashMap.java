package io.github.lc.oss.commons.serialization;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JsonableHashMap<T extends Jsonable> extends HashMap<String, T> implements JsonableMap<T> {
    private static final long serialVersionUID = 3114550393144685751L;

    public JsonableHashMap() {
    }

    public JsonableHashMap(Map<String, T> source) {
        super(source);
    }

    public <SourceKey, SourceType> JsonableHashMap(Map<SourceKey, SourceType> source, Function<SourceKey, String> keyTransformer,
            Function<SourceType, T> valueTransformer) {
        super(source.entrySet().stream().collect(Collectors.toMap( //
                e -> keyTransformer.apply(e.getKey()), //
                e -> valueTransformer.apply(e.getValue()))));
    }

    public <SourceType> JsonableHashMap(Collection<SourceType> src, Function<SourceType, String> keyTransformer,
            Function<SourceType, T> valueTransformer) {
        super(src.stream(). //
                collect(Collectors.toMap( //
                        v -> keyTransformer.apply(v), //
                        v -> valueTransformer.apply(v))));
    }
}
