package com.github.lc.oss.commons.serialization;

import java.io.IOException;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.lc.oss.commons.testing.AbstractMockTest;

public class DateSerializerTest extends AbstractMockTest {
    @Mock
    private JsonGenerator generator;
    @Mock
    private SerializerProvider serializer;

    @Test
    public void test_null() {
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

        DateSerializer serializer = new DateSerializer();
        try {
            serializer.serialize(null, this.generator, this.serializer);
        } catch (IOException ex) {
            Assertions.fail("Unexpected exception");
        }
    }

    @Test
    public void test_value() {
        try {
            Mockito.doAnswer(new Answer<Object>() {
                @Override
                public Object answer(InvocationOnMock invocation) throws Throwable {
                    return null;
                }
            }).when(this.generator).writeNumber(100000l);
        } catch (IOException ex) {
            Assertions.fail("Unexpected exception");
        }

        DateSerializer serializer = new DateSerializer();
        Date data = new Date(100000);
        try {
            serializer.serialize(data, this.generator, this.serializer);
        } catch (IOException ex) {
            Assertions.fail("Unexpected exception");
        }
    }
}
