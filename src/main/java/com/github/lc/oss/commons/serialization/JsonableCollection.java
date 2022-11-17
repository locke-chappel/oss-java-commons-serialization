package com.github.lc.oss.commons.serialization;

import java.util.Collection;

public interface JsonableCollection<T extends Jsonable> extends Collection<T>, Jsonable {

}
