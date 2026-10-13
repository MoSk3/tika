package org.apache.tika.parser;

import org.apache.tika.config.JsonConfig;
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

public class ParseContext_hashCode_15_0_Test {

    @Test
    public void testHashCode() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        ParseContext parseContext = new ParseContext();
        // Access the private fields using reflection
        Field contextField = ParseContext.class.getDeclaredField("context");
        contextField.setAccessible(true);
        Map<String, Object> contextMap = (Map<String, Object>) contextField.get(parseContext);
        Field jsonConfigsField = ParseContext.class.getDeclaredField("jsonConfigs");
        jsonConfigsField.setAccessible(true);
        Map<String, JsonConfig> jsonConfigsMap = (Map<String, JsonConfig>) jsonConfigsField.get(parseContext);
        // Test with empty maps
        int expectedHashCode = contextMap.hashCode() * 31 + jsonConfigsMap.hashCode();
        assertEquals(expectedHashCode, parseContext.hashCode());
        // Test with populated maps
        contextMap.put("key1", "value1");
        jsonConfigsMap.put("config1", mock(JsonConfig.class));
        expectedHashCode = contextMap.hashCode() * 31 + jsonConfigsMap.hashCode();
        assertEquals(expectedHashCode, parseContext.hashCode());
        // Test with different values
        contextMap.put("key2", "value2");
        jsonConfigsMap.put("config2", mock(JsonConfig.class));
        expectedHashCode = contextMap.hashCode() * 31 + jsonConfigsMap.hashCode();
        assertEquals(expectedHashCode, parseContext.hashCode());
    }
}
