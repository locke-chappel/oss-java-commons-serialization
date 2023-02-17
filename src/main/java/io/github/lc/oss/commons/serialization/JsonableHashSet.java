package io.github.lc.oss.commons.serialization;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JsonableHashSet<T extends Jsonable> extends HashSet<T> implements JsonableCollection<T> {
    private static final long serialVersionUID = -8653108380771105855L;

    public JsonableHashSet() {
    }

    public JsonableHashSet(Collection<T> source) {
        super(source);
    }

    public <Source> JsonableHashSet(Collection<Source> source, Function<Source, T> transformer) {
        super(source.stream().map(transformer).collect(Collectors.toSet()));
    }
}
