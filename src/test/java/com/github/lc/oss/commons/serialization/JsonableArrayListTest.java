package com.github.lc.oss.commons.serialization;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.github.lc.oss.commons.testing.AbstractTest;

public class JsonableArrayListTest extends AbstractTest {
    @Test
    public void test_constructors() {
        JsonableArrayList<Jsonable> set = new JsonableArrayList<>();
        Assertions.assertTrue(set.isEmpty());

        Jsonable a = new Jsonable() {
        };

        set = new JsonableArrayList<>(Arrays.asList(a));
        Assertions.assertEquals(1, set.size());
        Assertions.assertSame(a, set.iterator().next());

        Object oa = new Object();
        set = new JsonableArrayList<>(Arrays.asList(oa), i -> a);
        Assertions.assertEquals(1, set.size());
        Assertions.assertSame(a, set.iterator().next());
    }
}
