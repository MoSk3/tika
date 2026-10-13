package org.apache.tika.parser;

import java.lang.reflect.Method;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.tika.config.JsonConfig;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.writefilter.MetadataWriteLimiterFactory;

public class ParseContext_setJsonConfig_4_0_Test {

    private ParseContext parseContext;

    @BeforeEach
    public void setUp() {
        parseContext = new ParseContext();
    }

    @Test
    public void testSetJsonConfigWithValidJson() throws Exception {
        String name = "testConfig";
        String json = "{\"key\":\"value\"}";
        Method setJsonConfigMethod = ParseContext.class.getDeclaredMethod("setJsonConfig", String.class, String.class);
        setJsonConfigMethod.setAccessible(true);
        setJsonConfigMethod.invoke(parseContext, name, json);
        Map<String, JsonConfig> jsonConfigs = parseContext.getJsonConfigs();
        assertTrue(jsonConfigs.containsKey(name));
        assertNotNull(jsonConfigs.get(name));
    }

    @Test
    public void testSetJsonConfigWithNullJson() throws Exception {
        String name = "testConfig";
        String json = null;
        Method setJsonConfigMethod = ParseContext.class.getDeclaredMethod("setJsonConfig", String.class, String.class);
        setJsonConfigMethod.setAccessible(true);
        setJsonConfigMethod.invoke(parseContext, name, json);
        Map<String, JsonConfig> jsonConfigs = parseContext.getJsonConfigs();
        assertTrue(jsonConfigs.containsKey(name));
        assertNull(jsonConfigs.get(name));
    }
}
