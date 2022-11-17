module com.github.lc.oss.commons.serialization {
    requires com.github.lc.oss.commons.util;

    requires transitive com.fasterxml.jackson.annotation;
    requires transitive com.fasterxml.jackson.core;
    requires transitive com.fasterxml.jackson.databind;

    // for testing
    opens com.github.lc.oss.commons.serialization to com.fasterxml.jackson.databind;

    exports com.github.lc.oss.commons.serialization;
}
