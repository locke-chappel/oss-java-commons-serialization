package io.github.lc.oss.commons.serialization;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PagedResultTest {
    @Test
    public void test_nulls() {
        PagedResult<Jsonable> pr = new PagedResult<>();

        Assertions.assertEquals(-1, pr.getCount());
        Assertions.assertEquals(0, pr.getTotal());
        Assertions.assertNull(pr.getData());
    }

    @Test
    public void test_withData() {
        Set<Jsonable> src = new HashSet<>();
        src.add(new Jsonable() {
        });
        src.add(new Jsonable() {
        });

        PagedResult<Jsonable> pr = new PagedResult<>();
        pr.setData(src);
        pr.setTotal(73);

        Assertions.assertEquals(2, pr.getCount());
        Assertions.assertEquals(73, pr.getTotal());
        JsonableArrayList<Jsonable> data = pr.getData();
        Assertions.assertNotNull(data);
        Assertions.assertEquals(2, pr.getData().size());
        Assertions.assertNotSame(src, data);
    }

    @Test
    public void test_withData_v2() {
        JsonableArrayList<Jsonable> src = new JsonableArrayList<>();
        src.add(new Jsonable() {
        });
        src.add(new Jsonable() {
        });

        PagedResult<Jsonable> pr = new PagedResult<>();
        pr.setData(src);
        pr.setTotal(1);

        Assertions.assertEquals(2, pr.getCount());
        Assertions.assertEquals(1, pr.getTotal());
        JsonableArrayList<Jsonable> data = pr.getData();
        Assertions.assertNotNull(data);
        Assertions.assertEquals(2, pr.getData().size());
        Assertions.assertSame(src, data);
    }
}
