package io.github.lc.oss.commons.serialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JsonableArrayList<T extends Jsonable> extends ArrayList<T> implements JsonableCollection<T> {
    private static final long serialVersionUID = 8924758345533301806L;

    public JsonableArrayList() {
    }

    public JsonableArrayList(Collection<T> source) {
        super(source);
    }

    public <Source> JsonableArrayList(Collection<Source> source, Function<Source, T> transformer) {
        super(source.stream().map(transformer).collect(Collectors.toList()));
    }
}
