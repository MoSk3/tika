package org.apache.tika.parser;

import org.apache.tika.config.JsonConfig;
import org.apache.tika.parser.ParseContext;
import java.lang.reflect.Field;
import java.util.Map;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.writefilter.MetadataWriteLimiterFactory;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ParseContext_setJsonConfig_3_0_Test {

    private ParseContext parseContext;

    @BeforeEach
    void setUp() {
        parseContext = new ParseContext();
    }

    @Test
    void testSetJsonConfig_AddConfig() throws NoSuchFieldException, IllegalAccessException {
        // Mock JsonConfig instance
        JsonConfig mockConfig = mock(JsonConfig.class);
        // Invoke setJsonConfig
        parseContext.setJsonConfig("testConfig", mockConfig);
        // Use reflection to access the private jsonConfigs field
        Field jsonConfigsField = ParseContext.class.getDeclaredField("jsonConfigs");
        jsonConfigsField.setAccessible(true);
        Map<String, JsonConfig> jsonConfigs = (Map<String, JsonConfig>) jsonConfigsField.get(parseContext);
        // Assert that the config is added
        assertTrue(jsonConfigs.containsKey("testConfig"));
        assertEquals(mockConfig, jsonConfigs.get("testConfig"));
    }

    @Test
    void testSetJsonConfig_RemoveConfig() throws NoSuchFieldException, IllegalAccessException {
        // Mock JsonConfig instance
        JsonConfig mockConfig = mock(JsonConfig.class);
        // Add config first
        parseContext.setJsonConfig("testConfig", mockConfig);
        // Remove config by setting null
        parseContext.setJsonConfig("testConfig", (JsonConfig) null);
        // Use reflection to access the private jsonConfigs and resolvedConfigs fields
        Field jsonConfigsField = ParseContext.class.getDeclaredField("jsonConfigs");
        jsonConfigsField.setAccessible(true);
        Map<String, JsonConfig> jsonConfigs = (Map<String, JsonConfig>) jsonConfigsField.get(parseContext);
        Field resolvedConfigsField = ParseContext.class.getDeclaredField("resolvedConfigs");
        resolvedConfigsField.setAccessible(true);
        Map<String, Object> resolvedConfigs = (Map<String, Object>) resolvedConfigsField.get(parseContext);
        // Assert that the config is removed from both maps
        assertFalse(jsonConfigs.containsKey("testConfig"));
        assertFalse(resolvedConfigs.containsKey("testConfig"));
    }
}
