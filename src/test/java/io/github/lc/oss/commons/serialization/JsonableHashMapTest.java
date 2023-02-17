package io.github.lc.oss.commons.serialization;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.lc.oss.commons.testing.AbstractTest;

public class JsonableHashMapTest extends AbstractTest {
    @Test
    public void test_constructors() {
        JsonableHashMap<Jsonable> map = new JsonableHashMap<>();
        Assertions.assertTrue(map.isEmpty());

        Jsonable a = new Jsonable() {
        };

        Map<String, Jsonable> src = new HashMap<>();
        src.put("a", a);
        map = new JsonableHashMap<>(src);
        Assertions.assertEquals(1, map.size());
        Assertions.assertSame(a, map.get("a"));

        Map<Object, Object> srcO = new HashMap<>();
        srcO.put(new Object(), new Object());
        map = new JsonableHashMap<>(srcO, e -> "a", e -> a);
        Assertions.assertEquals(1, map.size());
        Assertions.assertSame(a, map.get("a"));

        Set<String> srcS = new HashSet<>();
        srcS.add("a");
        map = new JsonableHashMap<>(srcS, e -> "a", e -> a);
        Assertions.assertEquals(1, map.size());
        Assertions.assertSame(a, map.get("a"));
    }
}
