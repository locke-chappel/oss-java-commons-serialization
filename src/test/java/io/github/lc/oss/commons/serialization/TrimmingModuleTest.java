package io.github.lc.oss.commons.serialization;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;

import io.github.lc.oss.commons.testing.AbstractMockTest;

public class TrimmingModuleTest extends AbstractMockTest {
    private static class TestClass {
        public String a;
        public Boolean b;
        public TestClass c;
        public Integer i;
    }

    private JsonGenerator generator;
    private SerializerProvider provider;
    private JsonParser parser;
    private DeserializationContext context;

    private ObjectMapper mapper;

    @BeforeEach
    public void init() {
        this.generator = Mockito.mock(JsonGenerator.class);
        this.provider = Mockito.mock(SerializerProvider.class);
        this.parser = Mockito.mock(JsonParser.class);
        this.context = Mockito.mock(DeserializationContext.class);

        TrimmingModule module = new TrimmingModule();

        this.mapper = new ObjectMapper();
        this.mapper.registerModule(module);
    }

    @Test
    public void test_null() {
        TestClass src = new TestClass();

        try {
            String json = this.mapper.writeValueAsString(src);
            Assertions.assertNotNull(json);
            Assertions.assertEquals("{\"a\":null,\"b\":null,\"c\":null,\"i\":null}", json);

            TestClass obj = this.mapper.readValue(json, TestClass.class);
            Assertions.assertNotNull(obj);
            Assertions.assertNull(obj.a);
            Assertions.assertNull(obj.b);
            Assertions.assertNull(obj.c);
            Assertions.assertNull(obj.i);

            obj = this.mapper.readValue("{}", TestClass.class);
            Assertions.assertNotNull(obj);
            Assertions.assertNull(obj.a);
            Assertions.assertNull(obj.b);
            Assertions.assertNull(obj.c);
            Assertions.assertNull(obj.i);
        } catch (JsonProcessingException e) {
            Assertions.fail("Unexpected excetion");
        }
    }

    @Test
    public void test_whitepsace() {
        TestClass src = new TestClass();
        src.a = "";
        src.i = 0;
        src.b = false;
        src.c = new TestClass();
        src.c.a = " ";
        src.c.c = new TestClass();
        src.c.c.a = " \r \n \t ";

        try {
            String json = this.mapper.writeValueAsString(src);
            Assertions.assertNotNull(json);
            Assertions.assertEquals(
                    "{\"a\":null,\"b\":false,\"c\":{\"a\":null,\"b\":null,\"c\":{\"a\":null,\"b\":null,\"c\":null,\"i\":null},\"i\":null},\"i\":0}", json);

            TestClass obj = this.mapper.readValue(json, TestClass.class);
            Assertions.assertNotNull(obj);
            Assertions.assertNull(obj.a);
            Assertions.assertEquals(false, obj.b);
            Assertions.assertEquals(0, obj.i);
            Assertions.assertNotNull(obj.c);
            Assertions.assertNull(obj.c.a);
            Assertions.assertNotNull(obj.c.c);
            Assertions.assertNull(obj.c.c.a);

            obj = this.mapper.readValue("{\"b\":false,\"c\":{\"c\":{}},\"i\":0}", TestClass.class);
            Assertions.assertNotNull(obj);
            Assertions.assertNull(obj.a);
            Assertions.assertEquals(false, obj.b);
            Assertions.assertEquals(0, obj.i);
            Assertions.assertNotNull(obj.c);
            Assertions.assertNull(obj.c.a);
            Assertions.assertNotNull(obj.c.c);
            Assertions.assertNull(obj.c.c.a);
        } catch (JsonProcessingException e) {
            Assertions.fail("Unexpected excetion");
        }
    }

    @Test
    public void test_trimmed() {
        TestClass src = new TestClass();
        src.a = " \ttest \t test\r\n";

        try {
            String json = this.mapper.writeValueAsString(src);
            Assertions.assertNotNull(json);
            Assertions.assertEquals("{\"a\":\"test \\t test\",\"b\":null,\"c\":null,\"i\":null}", json);

            TestClass obj = this.mapper.readValue(json, TestClass.class);
            Assertions.assertNotNull(obj);
            Assertions.assertEquals("test \t test", obj.a);
            Assertions.assertNull(obj.b);
            Assertions.assertNull(obj.c);
            Assertions.assertNull(obj.i);

            obj = this.mapper.readValue("{\"a\":\"test \\t test\"}", TestClass.class);
            Assertions.assertNotNull(obj);
            Assertions.assertEquals("test \t test", obj.a);
            Assertions.assertNull(obj.b);
            Assertions.assertNull(obj.c);
            Assertions.assertNull(obj.i);
        } catch (JsonProcessingException e) {
            Assertions.fail("Unexpected excetion");
        }
    }

    @Test
    public void test_serializer_null() {
        try {
            Mockito.doAnswer(new Answer<Object>() {
                @Override
                public Object answer(InvocationOnMock invocation) throws Throwable {
                    return null;
                }
            }).when(this.generator).writeNull();
        } catch (IOException ex) {
            Assertions.fail("Unexpected exception");
        }

        StdScalarSerializer<String> serializer = TrimmingModule.TRIMMING_SERIALIZER;
        try {
            serializer.serialize(null, this.generator, this.provider);
        } catch (IOException ex) {
            Assertions.fail("Unexpected exception");
        }
    }

    @Test
    public void test_deserializer_null() {
        try {
            Mockito.when(this.parser.getValueAsString()).thenReturn(null);
        } catch (IOException ex) {
            Assertions.fail("Unexpected exception");
        }

        StdScalarDeserializer<String> serializer = TrimmingModule.TRIMMING_DESERIALIZER;
        try {
            String result = serializer.deserialize(this.parser, this.context);
            Assertions.assertNull(result);
        } catch (IOException ex) {
            Assertions.fail("Unexpected exception");
        }
    }

    @Test
    public void test_deserializer_empty() {
        try {
            Mockito.when(this.parser.getValueAsString()).thenReturn("");
        } catch (IOException ex) {
            Assertions.fail("Unexpected exception");
        }

        StdScalarDeserializer<String> serializer = TrimmingModule.TRIMMING_DESERIALIZER;
        try {
            String result = serializer.deserialize(this.parser, this.context);
            Assertions.assertNull(result);
        } catch (IOException ex) {
            Assertions.fail("Unexpected exception");
        }
    }

    @Test
    public void test_deserializer_blank() {
        try {
            Mockito.when(this.parser.getValueAsString()).thenReturn(" \t \r \n \t ");
        } catch (IOException ex) {
            Assertions.fail("Unexpected exception");
        }

        StdScalarDeserializer<String> serializer = TrimmingModule.TRIMMING_DESERIALIZER;
        try {
            String result = serializer.deserialize(this.parser, this.context);
            Assertions.assertNull(result);
        } catch (IOException ex) {
            Assertions.fail("Unexpected exception");
        }
    }
}
