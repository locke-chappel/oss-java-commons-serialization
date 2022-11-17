package com.github.lc.oss.commons.serialization;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_EMPTY)
public class PagedResult<T extends Jsonable> implements Jsonable {
    private long total;
    private JsonableArrayList<T> data;

    public long getCount() {
        if (this.data == null) {
            return -1;
        }
        return this.data.size();
    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public JsonableArrayList<T> getData() {
        return this.data;
    }

    public void setData(JsonableArrayList<T> data) {
        this.data = data;
    }

    public void setData(Collection<T> data) {
        this.data = new JsonableArrayList<>(data);
    }
}
