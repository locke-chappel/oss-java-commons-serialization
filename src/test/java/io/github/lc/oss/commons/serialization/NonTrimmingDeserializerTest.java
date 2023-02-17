package io.github.lc.oss.commons.serialization;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;

import io.github.lc.oss.commons.testing.AbstractMockTest;

public class NonTrimmingDeserializerTest extends AbstractMockTest {
    @Mock
    private JsonParser parser;
    @Mock
    private DeserializationContext context;

    @Test
    public void test_null() {
        NonTrimmingDeserializer deserializer = new NonTrimmingDeserializer();

        try {
            Mockito.when(this.parser.getValueAsString()).thenReturn(null);
        } catch (IOException ex) {
            Assertions.fail("Unexpected exception");
        }

        try {
            String result = deserializer.deserialize(this.parser, this.context);
            Assertions.assertNull(result);
        } catch (IOException ex) {
            Assertions.fail("Unexpected exception");
        }
    }

    @Test
    public void test_blank() {
        NonTrimmingDeserializer deserializer = new NonTrimmingDeserializer();

        try {
            Mockito.when(this.parser.getValueAsString()).thenReturn(" ");
        } catch (IOException ex) {
            Assertions.fail("Unexpected exception");
        }

        try {
            String result = deserializer.deserialize(this.parser, this.context);
            Assertions.assertNotNull(result);
            Assertions.assertEquals(" ", result);
        } catch (IOException ex) {
            Assertions.fail("Unexpected exception");
        }
    }

    @Test
    public void test_value() {
        NonTrimmingDeserializer deserializer = new NonTrimmingDeserializer();

        try {
            Mockito.when(this.parser.getValueAsString()).thenReturn("value");
        } catch (IOException ex) {
            Assertions.fail("Unexpected exception");
        }

        try {
            String result = deserializer.deserialize(this.parser, this.context);
            Assertions.assertNotNull(result);
            Assertions.assertEquals("value", result);
        } catch (IOException ex) {
            Assertions.fail("Unexpected exception");
        }
    }

    @Test
    public void test_value_whitepace() {
        NonTrimmingDeserializer deserializer = new NonTrimmingDeserializer();

        try {
            Mockito.when(this.parser.getValueAsString()).thenReturn(" value\r\nvalue\t");
        } catch (IOException ex) {
            Assertions.fail("Unexpected exception");
        }

        try {
            String result = deserializer.deserialize(this.parser, this.context);
            Assertions.assertNotNull(result);
            Assertions.assertEquals(" value\r\nvalue\t", result);
        } catch (IOException ex) {
            Assertions.fail("Unexpected exception");
        }
    }
}
