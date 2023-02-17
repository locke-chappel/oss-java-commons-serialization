package io.github.lc.oss.commons.serialization;

import java.util.Map;

public interface JsonableMap<T extends Jsonable> extends Map<String, T>, Jsonable {

}
