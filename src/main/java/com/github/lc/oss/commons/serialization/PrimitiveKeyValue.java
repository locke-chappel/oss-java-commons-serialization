package com.github.lc.oss.commons.serialization;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_EMPTY)
public class PrimitiveKeyValue<KeyType, ValueType> implements Jsonable {
    private final KeyType key;
    private final ValueType value;

    @JsonCreator
    public PrimitiveKeyValue(@JsonProperty("key") KeyType key, @JsonProperty("value") ValueType value) {
        if (!Primitive.isAllowed(key) || !Primitive.isAllowed(value)) {
            throw new IllegalArgumentException("Illegal type detected");
        }
        this.key = key;
        this.value = value;
    }

    public KeyType getKey() {
        return this.key;
    }

    public ValueType getValue() {
        return this.value;
    }
}
