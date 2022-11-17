package com.github.lc.oss.commons.serialization;

import java.io.IOException;
import java.time.Instant;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class InstantSerializer extends JsonSerializer<Instant> {
    @Override
    public void serialize(Instant value, JsonGenerator gen, SerializerProvider serializer) throws IOException {
        if (value == null) {
            gen.writeNull();
        } else {
            gen.writeNumber(value.toEpochMilli());
        }
    }
}
