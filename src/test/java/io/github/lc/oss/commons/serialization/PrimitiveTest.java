package io.github.lc.oss.commons.serialization;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.lc.oss.commons.testing.AbstractTest;

public class PrimitiveTest extends AbstractTest {
    @Test
    public void test_valid() {
        Primitive<?> p = new Primitive<>(null);
        Assertions.assertNull(p.getValue());

        p = new Primitive<>("a");
        Assertions.assertEquals("a", p.getValue());

        p = new Primitive<>(1);
        Assertions.assertEquals(1, p.getValue());

        p = new Primitive<>(false);
        Assertions.assertEquals(false, p.getValue());

        Date d = new Date();
        p = new Primitive<>(d);
        Assertions.assertEquals(d, p.getValue());
    }

    @Test
    public void test_invalid() {
        try {
            new Primitive<>(new Object());
            Assertions.fail("Expected exception");
        } catch (IllegalArgumentException ex) {
            Assertions.assertEquals("Illegal value type detected", ex.getMessage());
        }
    }
}
