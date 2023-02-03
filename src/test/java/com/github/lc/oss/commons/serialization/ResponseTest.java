package com.github.lc.oss.commons.serialization;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.github.lc.oss.commons.testing.AbstractTest;

public class ResponseTest extends AbstractTest {
    private enum Category implements Message.Category {
        C
    }

    private enum Severity implements Message.Severity {
        S,
        T
    }

    @Test
    public void test_v1() {
        Response<Jsonable> response = new Response<>();
        Assertions.assertNull(response.getBody());
        Assertions.assertNull(response.getMessages());
        Assertions.assertFalse(response.removeMessage((Message) null));
        Assertions.assertFalse(response.hasMessages());
        Assertions.assertFalse(response.hasSeverity(null));
        Assertions.assertFalse(response.hasSeverity(Severity.S));

        Jsonable body = new Jsonable() {
        };
        JsonableCollection<JsonMessage> messages = new JsonableHashSet<>();

        response.setBody(body);
        response.setMessages(messages);
        Assertions.assertSame(body, response.getBody());
        Assertions.assertSame(messages, response.getMessages());

        Message message1 = new JsonMessage(Category.C, Severity.S, 1, "text");
        Message message2 = new JsonMessage(Category.C, Severity.S, 2, "text");
        Assertions.assertTrue(response.addMessages(message1, message1));
        Assertions.assertEquals(1, response.getMessages().size());
        Assertions.assertTrue(response.getMessages().contains(message1));
        Assertions.assertFalse(response.getMessages().contains(message2));
        Assertions.assertFalse(response.removeMessage(message2));
        Assertions.assertTrue(response.hasMessages());
        Assertions.assertFalse(response.hasSeverity(null));
        Assertions.assertTrue(response.hasSeverity(Severity.S));

        Assertions.assertTrue(response.addMessages(message2));
        Assertions.assertEquals(2, response.getMessages().size());
        Assertions.assertTrue(response.getMessages().contains(message1));
        Assertions.assertTrue(response.getMessages().contains(message2));

        Assertions.assertTrue(response.removeMessage(message1, message2));
        Assertions.assertTrue(response.getMessages().isEmpty());
        Assertions.assertFalse(response.removeMessage((Message) null));
        Assertions.assertFalse(response.hasMessages());
        Assertions.assertFalse(response.hasSeverity(null));
        Assertions.assertFalse(response.hasSeverity(Severity.S));
    }

    @Test
    public void test_v2() {
        Jsonable body = new Jsonable() {
        };
        JsonableCollection<JsonMessage> messages = new JsonableHashSet<>();
        Response<Jsonable> response = new Response<>(body, messages);
        Assertions.assertSame(body, response.getBody());
        Assertions.assertSame(messages, response.getMessages());
        Assertions.assertFalse(response.hasMessages());
        Assertions.assertFalse(response.hasSeverity(null));
        Assertions.assertFalse(response.hasSeverity(Severity.S));

        response.setBody(null);
        response.setMessages(null);
        Assertions.assertNull(response.getBody());
        Assertions.assertNull(response.getMessages());
        Assertions.assertFalse(response.removeMessage((Message) null));

        Message message1 = new JsonMessage(Category.C, Severity.S, 1, "text");
        Message message2 = new JsonMessage(Category.C, Severity.S, 2, "text");
        Assertions.assertTrue(response.addMessages(message1, message1));
        Assertions.assertEquals(1, response.getMessages().size());
        Assertions.assertTrue(response.getMessages().contains(message1));
        Assertions.assertFalse(response.getMessages().contains(message2));
        Assertions.assertFalse(response.removeMessage(message2));
        Assertions.assertTrue(response.hasMessages());
        Assertions.assertFalse(response.hasSeverity(null));
        Assertions.assertTrue(response.hasSeverity(Severity.S));

        Assertions.assertTrue(response.addMessages(message2));
        Assertions.assertEquals(2, response.getMessages().size());
        Assertions.assertTrue(response.getMessages().contains(message1));
        Assertions.assertTrue(response.getMessages().contains(message2));

        Assertions.assertTrue(response.removeMessage(message1, message2));
        Assertions.assertTrue(response.getMessages().isEmpty());
        Assertions.assertFalse(response.removeMessage((Message) null));
        Assertions.assertFalse(response.hasMessages());
        Assertions.assertFalse(response.hasSeverity(null));
        Assertions.assertFalse(response.hasSeverity(Severity.S));
    }

    @Test
    public void test_v3() {
        Jsonable body = new Jsonable() {
        };
        Response<Jsonable> response = new Response<>(body);
        Assertions.assertSame(body, response.getBody());
        Assertions.assertNull(response.getMessages());
        Assertions.assertFalse(response.hasMessages());
        Assertions.assertFalse(response.hasSeverity(null));
        Assertions.assertFalse(response.hasSeverity(Severity.S));

        response.setBody(null);
        response.setMessages(null);
        Assertions.assertNull(response.getBody());
        Assertions.assertNull(response.getMessages());
        Assertions.assertFalse(response.removeMessage((Message) null));

        Message message1 = new JsonMessage(Category.C, Severity.S, 1, "text");
        Message message2 = new JsonMessage(Category.C, Severity.S, 2, "text");
        Assertions.assertTrue(response.addMessages(message1, message1));
        Assertions.assertEquals(1, response.getMessages().size());
        Assertions.assertTrue(response.getMessages().contains(message1));
        Assertions.assertFalse(response.getMessages().contains(message2));
        Assertions.assertFalse(response.removeMessage(message2));
        Assertions.assertTrue(response.hasMessages());
        Assertions.assertFalse(response.hasSeverity(null));
        Assertions.assertTrue(response.hasSeverity(Severity.S));

        Assertions.assertTrue(response.addMessages(message2));
        Assertions.assertEquals(2, response.getMessages().size());
        Assertions.assertTrue(response.getMessages().contains(message1));
        Assertions.assertTrue(response.getMessages().contains(message2));

        Assertions.assertTrue(response.removeMessage(message1, message2));
        Assertions.assertTrue(response.getMessages().isEmpty());
        Assertions.assertFalse(response.removeMessage((Message) null));
        Assertions.assertFalse(response.hasMessages());
        Assertions.assertFalse(response.hasSeverity(null));
        Assertions.assertFalse(response.hasSeverity(Severity.S));
    }

    @Test
    public void test_v4() {
        JsonableCollection<JsonMessage> messages = new JsonableHashSet<>();
        Response<Jsonable> response = new Response<>(messages);
        Assertions.assertNull(response.getBody());
        Assertions.assertSame(messages, response.getMessages());
        Assertions.assertFalse(response.removeMessage((Message) null));
        Assertions.assertFalse(response.hasMessages());
        Assertions.assertFalse(response.hasSeverity(null));
        Assertions.assertFalse(response.hasSeverity(Severity.S));

        Jsonable body = new Jsonable() {
        };

        response.setBody(body);
        Assertions.assertSame(body, response.getBody());
        Assertions.assertSame(messages, response.getMessages());

        Message message1 = new JsonMessage(Category.C, Severity.S, 1, "text");
        Message message2 = new JsonMessage(Category.C, null, 2, "text");
        Assertions.assertTrue(response.addMessages(message1, message1));
        Assertions.assertEquals(1, response.getMessages().size());
        Assertions.assertTrue(response.getMessages().contains(message1));
        Assertions.assertFalse(response.getMessages().contains(message2));
        Assertions.assertFalse(response.removeMessage(message2));
        Assertions.assertTrue(response.hasMessages());
        Assertions.assertFalse(response.hasSeverity(null));
        Assertions.assertTrue(response.hasSeverity(Severity.S));

        Assertions.assertTrue(response.addMessages(message2));
        Assertions.assertEquals(2, response.getMessages().size());
        Assertions.assertTrue(response.getMessages().contains(message1));
        Assertions.assertTrue(response.getMessages().contains(message2));
        Assertions.assertTrue(response.hasSeverity(null));
        Assertions.assertTrue(response.hasSeverity(Severity.S));

        Assertions.assertTrue(response.removeMessage(message1, message2));
        Assertions.assertTrue(response.getMessages().isEmpty());
        Assertions.assertFalse(response.removeMessage((Message) null));
        Assertions.assertFalse(response.hasMessages());
        Assertions.assertFalse(response.hasSeverity(null));
        Assertions.assertFalse(response.hasSeverity(Severity.S));
    }

    @Test
    public void test_hasSeverity() {
        Response<Jsonable> response = new Response<>();

        Assertions.assertFalse(response.hasSeverity(Severity.S));

        response.addMessages((Message) null);
        Assertions.assertFalse(response.hasSeverity(Severity.S));

        response.setMessages(new JsonableHashSet<>(Arrays.asList((JsonMessage) null)));
        Assertions.assertFalse(response.hasSeverity(Severity.S));

        Message message = new Message() {
            @Override
            public Category getCategory() {
                return ResponseTest.Category.C;
            }

            @Override
            public Severity getSeverity() {
                return ResponseTest.Severity.T;
            }

            @Override
            public int getNumber() {
                return 2;
            }
        };
        response.addMessages(message);
        Assertions.assertTrue(response.hasSeverity(Severity.T));

        JsonMessage jsonMessage = new JsonMessage(Category.C, null, 2, "text");
        response.addMessages(jsonMessage);
        Assertions.assertFalse(response.hasSeverity(Severity.S));

        message = new JsonMessage(Category.C, new Message.Severity() {
            @Override
            public String name() {
                return "R";
            }
        }, 2, "text");
        response.setMessages(new JsonableHashSet<>(Arrays.asList(jsonMessage)));
        Assertions.assertFalse(response.hasSeverity(Severity.S));

        jsonMessage = new JsonMessage(Category.C, Severity.S, 2, "text");
        response.setMessages(new JsonableHashSet<>(Arrays.asList(jsonMessage)));
        Assertions.assertTrue(response.hasSeverity(Severity.S));
    }
}
