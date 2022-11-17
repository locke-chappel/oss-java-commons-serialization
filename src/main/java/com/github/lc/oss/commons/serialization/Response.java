package com.github.lc.oss.commons.serialization;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.github.lc.oss.commons.serialization.Message.Severity;

@JsonInclude(Include.NON_EMPTY)
public class Response<T extends Jsonable> implements Jsonable {
    private T body;
    private Collection<Message> messages;

    public Response() {
    }

    public Response(T body) {
        this.body = body;
    }

    public Response(Collection<Message> messages) {
        this.messages = messages;
    }

    public Response(T body, Collection<Message> messages) {
        this.body = body;
        this.messages = messages;
    }

    public T getBody() {
        return this.body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public Collection<Message> getMessages() {
        return this.messages;
    }

    public void setMessages(Collection<Message> messages) {
        this.messages = messages;
    }

    public boolean addMessages(Message... messages) {
        if (this.messages == null) {
            this.messages = new HashSet<>();
        }
        return this.messages.addAll(Arrays.asList(messages));
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
                    anyMatch(m -> m.getSeverity() == null);
        }

        return this.messages.stream(). //
                filter(m -> m != null). //
                filter(m -> m.getSeverity() != null). //
                anyMatch(m -> m.getSeverity().name().equals(severity.name()));
    }
}
