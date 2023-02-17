package io.github.lc.oss.commons.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;

public class TrimmingModule extends SimpleModule {
    private static final long serialVersionUID = -248907404259228257L;

    protected static final StdScalarSerializer<String> TRIMMING_SERIALIZER = new StdScalarSerializer<String>(String.class) {
        private static final long serialVersionUID = -5360735332345360569L;

        @Override
        public void serialize(String value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            if (value == null) {
                gen.writeNull();
            } else {
                String text = value.trim();
                if (text.equals("")) {
                    gen.writeNull();
                } else {
                    gen.writeString(text);
                }
            }
        }
    };

    protected static final StdScalarDeserializer<String> TRIMMING_DESERIALIZER = new StdScalarDeserializer<String>(String.class) {
        private static final long serialVersionUID = 3641973681113158294L;

        @Override
        public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            String value = p.getValueAsString();
            if (value == null) {
                return null;
            }
            value = value.trim();
            if (value.equals("")) {
                return null;
            }
            return value;
        }
    };

    public TrimmingModule() {
        this.addSerializer(String.class, TrimmingModule.TRIMMING_SERIALIZER);
        this.addDeserializer(String.class, TrimmingModule.TRIMMING_DESERIALIZER);
    }
}
