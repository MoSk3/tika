package org.apache.tika.parser;

import org.apache.tika.config.JsonConfig;
import org.apache.tika.parser.ParseContext;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.Serializable;
import java.util.Collections;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.writefilter.MetadataWriteLimiterFactory;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ParseContext_hasJsonConfig_9_0_Test {

    private ParseContext parseContext;

    @BeforeEach
    public void setUp() {
        parseContext = new ParseContext();
    }

    @Test
    public void testHasJsonConfigWhenExists() throws NoSuchFieldException, IllegalAccessException {
        String configName = "testConfig";
        // Use a mock for the abstract class
        JsonConfig jsonConfig = mock(JsonConfig.class);
        // Use reflection to access the private jsonConfigs field
        Field jsonConfigsField = ParseContext.class.getDeclaredField("jsonConfigs");
        jsonConfigsField.setAccessible(true);
        // Add a JsonConfig to the jsonConfigs map
        Map<String, JsonConfig> jsonConfigs = new HashMap<>();
        jsonConfigs.put(configName, jsonConfig);
        jsonConfigsField.set(parseContext, jsonConfigs);
        // Test if the config is present
        assertTrue(parseContext.hasJsonConfig(configName));
    }

    @Test
    public void testHasJsonConfigWhenNotExists() {
        String configName = "nonExistentConfig";
        // Test if the config is absent
        assertFalse(parseContext.hasJsonConfig(configName));
    }
}
