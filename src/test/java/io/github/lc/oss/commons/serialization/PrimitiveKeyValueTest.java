package io.github.lc.oss.commons.serialization;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.lc.oss.commons.testing.AbstractTest;

public class PrimitiveKeyValueTest extends AbstractTest {
    @Test
    public void test_valid() {
        PrimitiveKeyValue<?, ?> p = new PrimitiveKeyValue<>(null, null);
        Assertions.assertNull(p.getValue());

        p = new PrimitiveKeyValue<>("k", "v");
        Assertions.assertEquals("k", p.getKey());
        Assertions.assertEquals("v", p.getValue());

        p = new PrimitiveKeyValue<>(1, "1");
        Assertions.assertEquals(1, p.getKey());
        Assertions.assertEquals("1", p.getValue());

        p = new PrimitiveKeyValue<>(false, true);
        Assertions.assertEquals(false, p.getKey());
        Assertions.assertEquals(true, p.getValue());

        Date d1 = new Date();
        Date d2 = new Date(1000);
        p = new PrimitiveKeyValue<>(d1, d2);
        Assertions.assertSame(d1, p.getKey());
        Assertions.assertSame(d2, p.getValue());
    }

    @Test
    public void test_invalid() {
        try {
            new PrimitiveKeyValue<>(new Object(), new Object());
            Assertions.fail("Expected exception");
        } catch (IllegalArgumentException ex) {
            Assertions.assertEquals("Illegal type detected", ex.getMessage());
        }

        try {
            new PrimitiveKeyValue<>("k", new Object());
            Assertions.fail("Expected exception");
        } catch (IllegalArgumentException ex) {
            Assertions.assertEquals("Illegal type detected", ex.getMessage());
        }

        try {
            new PrimitiveKeyValue<>(new Object(), "v");
            Assertions.fail("Expected exception");
        } catch (IllegalArgumentException ex) {
            Assertions.assertEquals("Illegal type detected", ex.getMessage());
        }
    }
}
