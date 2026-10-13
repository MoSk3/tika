package org.apache.tika.parser;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.Serializable;
import java.util.Collections;
import org.apache.tika.config.JsonConfig;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.writefilter.MetadataWriteLimiterFactory;

public class ParseContext_get_2_0_Test {

    private ParseContext parseContext;

    @BeforeEach
    public void setUp() {
        parseContext = new ParseContext();
    }

    @Test
    public void testGetWithExistingValue() throws Exception {
        Class<String> key = String.class;
        String expectedValue = "testValue";
        // Use reflection to access the private 'context' field
        Field contextField = ParseContext.class.getDeclaredField("context");
        contextField.setAccessible(true);
        Map<String, Object> contextMap = (Map<String, Object>) contextField.get(parseContext);
        contextMap.put(key.getName(), expectedValue);
        String result = parseContext.get(key, "defaultValue");
        assertEquals(expectedValue, result);
    }

    @Test
    public void testGetWithDefaultValue() {
        Class<String> key = String.class;
        String defaultValue = "defaultValue";
        String result = parseContext.get(key, defaultValue);
        assertEquals(defaultValue, result);
    }

    @Test
    public void testGetWithNullDefaultValue() {
        Class<String> key = String.class;
        String result = parseContext.get(key, null);
        assertNull(result);
    }
}
