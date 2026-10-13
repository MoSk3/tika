package org.apache.tika.parser;

import org.apache.tika.config.JsonConfig;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.Serializable;
import java.util.Collections;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.writefilter.MetadataWriteLimiterFactory;

@ExtendWith(MockitoExtension.class)
public class ParseContext_getJsonConfig_5_0_Test {

    private ParseContext parseContext;

    @BeforeEach
    public void setUp() {
        parseContext = new ParseContext();
    }

    @Test
    public void testGetJsonConfigExistingConfig() throws NoSuchFieldException, IllegalAccessException {
        // Arrange
        String configName = "testConfig";
        JsonConfig expectedConfig = mock(JsonConfig.class);
        // Use reflection to set the private field jsonConfigs
        Field jsonConfigsField = ParseContext.class.getDeclaredField("jsonConfigs");
        jsonConfigsField.setAccessible(true);
        Map<String, JsonConfig> jsonConfigs = new HashMap<>();
        jsonConfigs.put(configName, expectedConfig);
        jsonConfigsField.set(parseContext, jsonConfigs);
        // Act
        JsonConfig actualConfig = parseContext.getJsonConfig(configName);
        // Assert
        assertEquals(expectedConfig, actualConfig);
    }

    @Test
    public void testGetJsonConfigNonExistingConfig() {
        // Arrange
        String configName = "nonExistingConfig";
        // Act
        JsonConfig actualConfig = parseContext.getJsonConfig(configName);
        // Assert
        assertNull(actualConfig);
    }
}
