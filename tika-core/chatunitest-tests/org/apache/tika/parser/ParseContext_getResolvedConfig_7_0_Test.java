package org.apache.tika.parser;

import org.apache.tika.config.JsonConfig;
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
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.writefilter.MetadataWriteLimiterFactory;

public class ParseContext_getResolvedConfig_7_0_Test {

    private ParseContext parseContext;

    @BeforeEach
    public void setUp() {
        parseContext = new ParseContext();
    }

    @Test
    public void testGetResolvedConfig_WhenResolvedConfigsIsNull() throws NoSuchFieldException, IllegalAccessException {
        // Use reflection to set the private field resolvedConfigs to null
        Field resolvedConfigsField = ParseContext.class.getDeclaredField("resolvedConfigs");
        resolvedConfigsField.setAccessible(true);
        resolvedConfigsField.set(parseContext, null);
        // Test getResolvedConfig with null resolvedConfigs
        assertNull(parseContext.getResolvedConfig("testName"));
    }

    @Test
    public void testGetResolvedConfig_WhenNameExists() throws NoSuchFieldException, IllegalAccessException {
        // Use reflection to set the private field resolvedConfigs
        Field resolvedConfigsField = ParseContext.class.getDeclaredField("resolvedConfigs");
        resolvedConfigsField.setAccessible(true);
        Map<String, Object> resolvedConfigs = new HashMap<>();
        resolvedConfigs.put("testName", "testValue");
        resolvedConfigsField.set(parseContext, resolvedConfigs);
        // Test getResolvedConfig with existing name
        assertEquals("testValue", parseContext.getResolvedConfig("testName"));
    }

    @Test
    public void testGetResolvedConfig_WhenNameDoesNotExist() throws NoSuchFieldException, IllegalAccessException {
        // Use reflection to set the private field resolvedConfigs
        Field resolvedConfigsField = ParseContext.class.getDeclaredField("resolvedConfigs");
        resolvedConfigsField.setAccessible(true);
        Map<String, Object> resolvedConfigs = new HashMap<>();
        resolvedConfigsField.set(parseContext, resolvedConfigs);
        // Test getResolvedConfig with non-existing name
        assertNull(parseContext.getResolvedConfig("nonExistentName"));
    }
}
