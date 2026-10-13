package org.apache.tika.parser;

import org.apache.tika.parser.ParseContext;
import org.apache.tika.config.JsonConfig;
import java.util.Map;
import java.util.HashMap;
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
public class ParseContext_copyFrom_11_0_Test {

    private ParseContext sourceContext;

    private ParseContext targetContext;

    @BeforeEach
    public void setUp() {
        sourceContext = new ParseContext();
        targetContext = new ParseContext();
    }

    @Test
    public void testCopyFromWithNullSource() {
        targetContext.copyFrom(null);
        assertTrue(targetContext.getContextMap().isEmpty());
        assertTrue(targetContext.getJsonConfigs().isEmpty());
    }

    @Test
    public void testCopyFromWithEmptySource() {
        targetContext.copyFrom(sourceContext);
        assertTrue(targetContext.getContextMap().isEmpty());
        assertTrue(targetContext.getJsonConfigs().isEmpty());
    }

    @Test
    public void testCopyFromWithPopulatedSource() throws Exception {
        // Set up source context with data
        Class<String> key = String.class;
        String value = "value";
        sourceContext.set(key, value);
        // Use mock for abstract class
        JsonConfig jsonConfig = mock(JsonConfig.class);
        sourceContext.setJsonConfig("configKey", jsonConfig);
        Object resolvedConfig = new Object();
        sourceContext.setResolvedConfig("resolvedKey", resolvedConfig);
        // Copy from source to target
        targetContext.copyFrom(sourceContext);
        // Verify context map
        Map<String, Object> targetContextMap = targetContext.getContextMap();
        assertEquals(value, targetContextMap.get(key.getName()));
        // Verify json configs
        Map<String, JsonConfig> targetJsonConfigs = targetContext.getJsonConfigs();
        assertEquals(jsonConfig, targetJsonConfigs.get("configKey"));
        // Use reflection to access private resolvedConfigs
        java.lang.reflect.Field resolvedConfigsField = ParseContext.class.getDeclaredField("resolvedConfigs");
        resolvedConfigsField.setAccessible(true);
        Map<String, Object> targetResolvedConfigs = (Map<String, Object>) resolvedConfigsField.get(targetContext);
        // Verify resolved configs
        assertEquals(resolvedConfig, targetResolvedConfigs.get("resolvedKey"));
    }
}
