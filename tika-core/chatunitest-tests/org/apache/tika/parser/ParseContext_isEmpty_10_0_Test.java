package org.apache.tika.parser;

import org.apache.tika.parser.ParseContext;
import org.apache.tika.config.JsonConfig;
import java.util.Map;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.writefilter.MetadataWriteLimiterFactory;
import java.lang.reflect.Field;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ParseContext_isEmpty_10_0_Test {

    private ParseContext parseContext;

    @BeforeEach
    public void setUp() {
        parseContext = new ParseContext();
    }

    @Test
    public void testIsEmptyWhenBothMapsAreEmpty() {
        assertTrue(parseContext.isEmpty(), "ParseContext should be empty when both context and jsonConfigs are empty.");
    }

    @Test
    public void testIsEmptyWhenContextIsNotEmpty() throws Exception {
        Field contextField = ParseContext.class.getDeclaredField("context");
        contextField.setAccessible(true);
        Map<String, Object> context = (Map<String, Object>) contextField.get(parseContext);
        context.put("key", new Object());
        assertFalse(parseContext.isEmpty(), "ParseContext should not be empty when context is not empty.");
    }

    @Test
    public void testIsEmptyWhenJsonConfigsIsNotEmpty() throws Exception {
        Field jsonConfigsField = ParseContext.class.getDeclaredField("jsonConfigs");
        jsonConfigsField.setAccessible(true);
        Map<String, JsonConfig> jsonConfigs = (Map<String, JsonConfig>) jsonConfigsField.get(parseContext);
        JsonConfig jsonConfigMock = mock(JsonConfig.class);
        jsonConfigs.put("configName", jsonConfigMock);
        assertFalse(parseContext.isEmpty(), "ParseContext should not be empty when jsonConfigs is not empty.");
    }
}
