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

public class ParseContext_setResolvedConfig_8_0_Test {

    private ParseContext parseContext;

    @BeforeEach
    public void setUp() {
        parseContext = new ParseContext();
    }

    @Test
    public void testSetResolvedConfig_AddConfig() throws NoSuchFieldException, IllegalAccessException {
        String name = "testConfig";
        Object config = new Object();
        parseContext.setResolvedConfig(name, config);
        Map<String, Object> resolvedConfigs = getResolvedConfigs(parseContext);
        assertNotNull(resolvedConfigs);
        assertEquals(config, resolvedConfigs.get(name));
    }

    @Test
    public void testSetResolvedConfig_RemoveConfig() throws NoSuchFieldException, IllegalAccessException {
        String name = "testConfig";
        Object config = new Object();
        parseContext.setResolvedConfig(name, config);
        parseContext.setResolvedConfig(name, null);
        Map<String, Object> resolvedConfigs = getResolvedConfigs(parseContext);
        assertNotNull(resolvedConfigs);
        assertFalse(resolvedConfigs.containsKey(name));
    }

    @Test
    public void testSetResolvedConfig_NullResolvedConfigs() throws NoSuchFieldException, IllegalAccessException {
        String name = "testConfig";
        Object config = new Object();
        // Simulate resolvedConfigs being null
        Field field = ParseContext.class.getDeclaredField("resolvedConfigs");
        field.setAccessible(true);
        field.set(parseContext, null);
        parseContext.setResolvedConfig(name, config);
        Map<String, Object> resolvedConfigs = getResolvedConfigs(parseContext);
        assertNotNull(resolvedConfigs);
        assertEquals(config, resolvedConfigs.get(name));
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getResolvedConfigs(ParseContext parseContext) throws NoSuchFieldException, IllegalAccessException {
        Field field = ParseContext.class.getDeclaredField("resolvedConfigs");
        field.setAccessible(true);
        return (Map<String, Object>) field.get(parseContext);
    }
}
