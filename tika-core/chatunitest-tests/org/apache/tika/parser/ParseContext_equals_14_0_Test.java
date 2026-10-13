package org.apache.tika.parser;

import org.apache.tika.parser.ParseContext;
import org.apache.tika.config.JsonConfig;
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
public class ParseContext_equals_14_0_Test {

    @Test
    public void testEqualsSameObject() {
        ParseContext context = new ParseContext();
        assertTrue(context.equals(context), "A ParseContext object should be equal to itself.");
    }

    @Test
    public void testEqualsNullObject() {
        ParseContext context = new ParseContext();
        assertFalse(context.equals(null), "A ParseContext object should not be equal to null.");
    }

    @Test
    public void testEqualsDifferentClass() {
        ParseContext context = new ParseContext();
        assertFalse(context.equals(new Object()), "A ParseContext object should not be equal to an object of a different class.");
    }

    @Test
    public void testEqualsDifferentContext() throws Exception {
        ParseContext context1 = new ParseContext();
        ParseContext context2 = new ParseContext();
        context1.set(String.class, "value1");
        context2.set(String.class, "value2");
        assertFalse(context1.equals(context2), "ParseContext objects with different context maps should not be equal.");
    }

    @Test
    public void testEqualsDifferentJsonConfigs() throws Exception {
        ParseContext context1 = new ParseContext();
        ParseContext context2 = new ParseContext();
        JsonConfig config1 = mock(JsonConfig.class);
        JsonConfig config2 = mock(JsonConfig.class);
        context1.setJsonConfig("config1", config1);
        context2.setJsonConfig("config2", config2);
        assertFalse(context1.equals(context2), "ParseContext objects with different jsonConfigs maps should not be equal.");
    }

    @Test
    public void testEqualsSameContent() throws Exception {
        ParseContext context1 = new ParseContext();
        ParseContext context2 = new ParseContext();
        context1.set(String.class, "value");
        context2.set(String.class, "value");
        JsonConfig config = mock(JsonConfig.class);
        context1.setJsonConfig("config", config);
        context2.setJsonConfig("config", config);
        assertTrue(context1.equals(context2), "ParseContext objects with the same context and jsonConfigs should be equal.");
    }
}
