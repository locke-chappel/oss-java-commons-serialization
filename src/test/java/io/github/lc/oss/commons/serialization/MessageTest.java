package io.github.lc.oss.commons.serialization;

import java.util.Arrays;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.lc.oss.commons.serialization.Message.Category;
import io.github.lc.oss.commons.serialization.Message.Severities;
import io.github.lc.oss.commons.serialization.Message.Severity;
import io.github.lc.oss.commons.testing.AbstractTest;

public class MessageTest extends AbstractTest {
    private static enum Categories implements Message.Category {
        App,
        Other
    }

    @Test
    public void test_categories() {
        Message.Categories[] values = Message.Categories.values();
        Set<Category> all = Message.Categories.all();
        Assertions.assertEquals(values.length, all.size());
        Assertions.assertSame(all, Message.Categories.all());
        Arrays.stream(values).forEach(v -> Assertions.assertTrue(all.contains(v)));
        all.stream().forEach(a -> {
            Assertions.assertTrue(Message.Categories.hasName(a.name()));
            Assertions.assertSame(a, Message.Categories.byName(a.name()));
            Assertions.assertSame(a, Message.Categories.tryParse(a.name()));
        });
    }

    @Test
    public void test_severities() {
        Severities[] values = Message.Severities.values();
        Set<Severity> all = Message.Severities.all();
        Assertions.assertEquals(values.length, all.size());
        Assertions.assertSame(all, Message.Severities.all());
        Arrays.stream(values).forEach(v -> Assertions.assertTrue(all.contains(v)));
        all.stream().forEach(a -> {
            Assertions.assertTrue(Message.Severities.hasName(a.name()));
            Assertions.assertSame(a, Message.Severities.byName(a.name()));
            Assertions.assertSame(a, Message.Severities.tryParse(a.name()));
        });
    }

    @Test
    public void test_defaultText() {
        Message m = new Message() {
            @Override
            public Category getCategory() {
                return null;
            }

            @Override
            public Severity getSeverity() {
                return null;
            }

            @Override
            public int getNumber() {
                return 0;
            }
        };

        Assertions.assertNull(m.getText());
    }

    @Test
    public void test_isSame() {
        Message n1 = new JsonMessage(null, null, -1);
        Message n2 = new JsonMessage(Categories.App, null, -1);
        Message m1 = new JsonMessage(Categories.App, Message.Severities.Error, 1);
        Message m2 = new JsonMessage(Categories.App, Message.Severities.Error, 1);
        Message m3 = new JsonMessage(Categories.App, Message.Severities.Warning, 1);
        Message m4 = new JsonMessage(Categories.Other, Message.Severities.Error, 1);
        Message m5 = new JsonMessage(Categories.App, Message.Severities.Error, 2);

        Assertions.assertTrue(n1.isSame(n1));
        Assertions.assertFalse(n1.isSame(null));

        Assertions.assertNotEquals(m1, m2);
        Assertions.assertNotSame(m1, m2);
        Assertions.assertFalse(m1 == m2);
        Assertions.assertFalse(m1.isSame(n1));
        Assertions.assertFalse(m1.isSame(n2));
        Assertions.assertTrue(m1.isSame(m2));
        Assertions.assertFalse(m1.isSame(m3));
        Assertions.assertFalse(m1.isSame(m4));
        Assertions.assertFalse(m1.isSame(m5));
    }
}
