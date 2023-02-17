package io.github.lc.oss.commons.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * This is a special deserializer override for the {@linkplain TrimmingModule}.
 * In some cases empty string should be preserved, this will enable that for
 * specifically annotated fields.
 *
 */
public class NonTrimmingDeserializer extends JsonDeserializer<String> {
    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return p.getValueAsString();
    }
}
