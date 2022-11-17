package com.github.lc.oss.commons.serialization;

import java.util.Set;

import com.github.lc.oss.commons.util.TypedEnumCache;

public interface Message {
    interface Category {
        String name();
    }

    interface Severity {
        String name();
    }

    enum Categories implements Category {
        Application;

        private static final TypedEnumCache<Categories, Category> CACHE = new TypedEnumCache<>(Categories.class);

        public static Set<Category> all() {
            return Categories.CACHE.values();
        }

        public static Category byName(String name) {
            return Categories.CACHE.byName(name);
        }

        public static boolean hasName(String name) {
            return Categories.CACHE.hasName(name);
        }

        public static Category tryParse(String name) {
            return Categories.CACHE.tryParse(name);
        }
    }

    enum Severities implements Severity {
        Info,
        Warning,
        Error,
        Success;

        private static final TypedEnumCache<Severities, Severity> CACHE = new TypedEnumCache<>(Severities.class);

        public static Set<Severity> all() {
            return Severities.CACHE.values();
        }

        public static Severity byName(String name) {
            return Severities.CACHE.byName(name);
        }

        public static boolean hasName(String name) {
            return Severities.CACHE.hasName(name);
        }

        public static Severity tryParse(String name) {
            return Severities.CACHE.tryParse(name);
        }
    }

    Category getCategory();

    Severity getSeverity();

    int getNumber();

    String getText();

    default boolean isSame(Message other) {
        if (this == other) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (other.getCategory() == null || !this.getCategory().name().equals(other.getCategory().name())) {
            return false;
        }

        if (other.getSeverity() == null || !this.getSeverity().name().equals(other.getSeverity().name())) {
            return false;
        }

        if (this.getNumber() != other.getNumber()) {
            return false;
        }

        return true;
    }
}
