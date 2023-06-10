package io.github.lc.oss.commons.serialization;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.github.lc.oss.commons.serialization.Message.Severity;

@JsonInclude(Include.NON_EMPTY)
public class Response<T extends Jsonable> implements Jsonable {
    private T body;
    private JsonableCollection<JsonMessage> messages;

    public Response() {
    }

    public Response(T body) {
        this.body = body;
    }

    public Response(JsonableCollection<JsonMessage> messages) {
        this.messages = messages;
    }

    public Response(T body, JsonableCollection<JsonMessage> messages) {
        this.body = body;
        this.messages = messages;
    }

    public T getBody() {
        return this.body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public JsonableCollection<JsonMessage> getMessages() {
        return this.messages;
    }

    public void setMessages(JsonableCollection<JsonMessage> messages) {
        this.messages = messages;
    }

    public boolean addMessages(Message... messages) {
        if (this.messages == null) {
            this.messages = new JsonableHashSet<>();
        }
        return this.messages.addAll( //
                Arrays.stream(messages). //
                        filter(m -> m != null). //
                        map(m -> m instanceof JsonMessage ? (JsonMessage) m : new JsonMessage(m)). //
                        collect(Collectors.toSet()));
    }

    public boolean removeMessage(Message... message) {
        if (this.messages == null) {
            return false;
        }
        return this.messages.removeAll(Arrays.asList(message));
    }

    @JsonIgnore
    public boolean hasMessages() {
        if (this.messages == null) {
            return false;
        }
        return !this.messages.isEmpty();
    }

    @JsonIgnore
    public boolean hasSeverity(Severity severity) {
        if (this.messages == null) {
            return false;
        }

        if (severity == null) {
            return this.messages.stream(). //
                    filter(m -> m != null). //
                    anyMatch(m -> m.getSeverity() == null);
        }

        return this.messages.stream(). //
                filter(m -> m != null). //
                filter(m -> m.getSeverity() != null). //
                anyMatch(m -> m.getSeverity().name().equals(severity.name()));
    }
}
