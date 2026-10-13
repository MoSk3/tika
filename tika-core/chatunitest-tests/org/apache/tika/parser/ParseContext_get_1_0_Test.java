package org.apache.tika.parser;

import org.apache.tika.parser.ParseContext;
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

public class ParseContext_get_1_0_Test {

    private ParseContext parseContext;

    @BeforeEach
    public void setUp() {
        parseContext = new ParseContext();
    }

    @Test
    public void testGetExistingKey() throws NoSuchFieldException, IllegalAccessException {
        // Arrange
        String key = "java.lang.String";
        String expectedValue = "TestValue";
        // Use reflection to set the private 'context' field
        Field contextField = ParseContext.class.getDeclaredField("context");
        contextField.setAccessible(true);
        Map<String, Object> contextMap = new HashMap<>();
        contextMap.put(key, expectedValue);
        contextField.set(parseContext, contextMap);
        // Act
        String actualValue = parseContext.get(String.class);
        // Assert
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testGetNonExistingKey() {
        // Act
        String actualValue = parseContext.get(String.class);
        // Assert
        assertNull(actualValue);
    }
}
