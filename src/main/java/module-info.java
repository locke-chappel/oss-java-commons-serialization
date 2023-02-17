module io.github.lc.oss.commons.serialization {
    requires io.github.lc.oss.commons.util;

    requires transitive com.fasterxml.jackson.annotation;
    requires transitive com.fasterxml.jackson.core;
    requires transitive com.fasterxml.jackson.databind;

    // for testing
    opens io.github.lc.oss.commons.serialization to com.fasterxml.jackson.databind;

    exports io.github.lc.oss.commons.serialization;
}
