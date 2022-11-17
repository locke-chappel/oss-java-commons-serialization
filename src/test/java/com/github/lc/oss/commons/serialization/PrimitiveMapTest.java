package com.github.lc.oss.commons.serialization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.github.lc.oss.commons.testing.AbstractTest;

public class PrimitiveMapTest extends AbstractTest {
    @Test
    public void test_valid() {
        PrimitiveMap<Object> map = new PrimitiveMap<>();
        Assertions.assertTrue(map.isEmpty());

        map.putAll(new HashMap<>());
        Assertions.assertTrue(map.isEmpty());

        Date date = new Date();
        Jsonable jsonable = new Jsonable() {
        };
        Set<String> strings = new HashSet<>();
        strings.add("s");
        Map<String, Object> src = new HashMap<>();
        src.put("A", "Value");
        src.put("B", 1);
        src.put("C", false);
        src.put("D", date);
        src.put("J", jsonable);
        src.put("S", strings);
        src.put("null", null);
        map.putAll(src);
        Assertions.assertEquals(7, map.size());
        Assertions.assertSame("Value", map.get("A"));
        Assertions.assertEquals(1, map.get("B"));
        Assertions.assertEquals(false, map.get("C"));
        Assertions.assertSame(date, map.get("D"));
        Assertions.assertSame(jsonable, map.get("J"));
        Assertions.assertSame(strings, map.get("S"));
        Assertions.assertNull(map.get("null"));

        PrimitiveMap<Object> map2 = new PrimitiveMap<>(src);
        Assertions.assertEquals(7, map2.size());
        Assertions.assertSame("Value", map2.get("A"));
        Assertions.assertEquals(1, map2.get("B"));
        Assertions.assertEquals(false, map2.get("C"));
        Assertions.assertSame(date, map2.get("D"));
        Assertions.assertSame(jsonable, map2.get("J"));
        Assertions.assertSame(strings, map2.get("S"));
        Assertions.assertNull(map2.get("null"));

        map.put("Q", "Q");
        Assertions.assertEquals(8, map.size());
        Assertions.assertEquals("Q", map.get("Q"));
        Assertions.assertNull(map.get("q"));
    }

    @Test
    public void test_collection_constructor() {
        Set<Object> src = new HashSet<>();
        src.add("a");

        PrimitiveMap<String> map = new PrimitiveMap<>(src, v -> "a", v -> "a");
        Assertions.assertEquals(1, map.size());
        Assertions.assertEquals("a", map.get("a"));
    }

    @Test
    public void test_invalid() {
        PrimitiveMap<Object> map = new PrimitiveMap<>();

        List<Object> bad = new ArrayList<>();
        bad.add(new Object());
        bad.add(new ArrayList<>());
        bad.add(new HashSet<>(Arrays.asList(1, 2)));

        bad.forEach(o -> {
            try {
                map.put("K", o);
                Assertions.fail("Expected exception");
            } catch (IllegalArgumentException ex) {
                Assertions.assertEquals("Illegal value type: " + o.getClass().getCanonicalName(), ex.getMessage());
            }
        });

        Map<String, Object> src = new HashMap<>();
        src.put("A", "Value");
        src.put("O", new Object());
        try {
            map.putAll(src);
            Assertions.fail("Expected exception");
        } catch (IllegalArgumentException ex) {
            Assertions.assertEquals("Illegal value type detected", ex.getMessage());
        }
    }
}
