/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.tika.parser;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import org.apache.tika.config.JsonConfig;

@ExtendWith(MockitoExtension.class)
public class ParseContext_copyFrom_11_1_Test {

    private ParseContext sourceContext;

    private ParseContext targetContext;

    @BeforeEach
    public void setUp() {
        sourceContext = new ParseContext();
        targetContext = new ParseContext();
    }

    @Test
    public void testCopyFromWithNullSource() throws NoSuchFieldException, IllegalAccessException {
        targetContext.copyFrom(null);
        assertEquals(0, ((Map<?, ?>) getContextField(targetContext, "context")).size());
        assertEquals(0, ((Map<?, ?>) getContextField(targetContext, "jsonConfigs")).size());
        assertEquals(0, ((Map<?, ?>) getContextField(targetContext, "resolvedConfigs")).size());
    }

    @Test
    public void testCopyFromWithEmptySource() throws NoSuchFieldException, IllegalAccessException {
        targetContext.copyFrom(sourceContext);
        assertEquals(0, ((Map<?, ?>) getContextField(targetContext, "context")).size());
        assertEquals(0, ((Map<?, ?>) getContextField(targetContext, "jsonConfigs")).size());
        assertEquals(0, ((Map<?, ?>) getContextField(targetContext, "resolvedConfigs")).size());
    }

    @Test
    public void testCopyFromWithNonEmptySource() throws NoSuchFieldException, IllegalAccessException {
        // Set up source context
        sourceContext.set(String.class, "TestValue");
        JsonConfig jsonConfig = mock(JsonConfig.class);
        sourceContext.setJsonConfig("testJson", jsonConfig);
        sourceContext.setResolvedConfig("testResolved", "ResolvedValue");
        // Copy from source to target
        targetContext.copyFrom(sourceContext);
        // Verify context map
        Map<String, Object> expectedContext = new HashMap<>();
        expectedContext.put(String.class.getName(), "TestValue");
        assertEquals(expectedContext, getContextField(targetContext, "context"));
        // Verify jsonConfigs map
        Map<String, JsonConfig> expectedJsonConfigs = new HashMap<>();
        expectedJsonConfigs.put("testJson", jsonConfig);
        assertEquals(expectedJsonConfigs, getContextField(targetContext, "jsonConfigs"));
        // Verify resolvedConfigs map
        Map<String, Object> expectedResolvedConfigs = new HashMap<>();
        expectedResolvedConfigs.put("testResolved", "ResolvedValue");
        assertEquals(expectedResolvedConfigs, getContextField(targetContext, "resolvedConfigs"));
    }

    @Test
    public void testCopyFromWithOverriddenJsonConfig() throws NoSuchFieldException, IllegalAccessException {
        JsonConfig originalConfig = mock(JsonConfig.class);
        JsonConfig newConfig = mock(JsonConfig.class);
        sourceContext.setJsonConfig("testJson", newConfig);
        targetContext.setJsonConfig("testJson", originalConfig);
        targetContext.copyFrom(sourceContext);
        assertEquals(newConfig, ((Map<?, ?>) getContextField(targetContext, "jsonConfigs")).get("testJson"));
        assertNull(((Map<?, ?>) getContextField(targetContext, "resolvedConfigs")).get("testJson"));
    }

    @SuppressWarnings("unchecked")
    private <T> T getContextField(ParseContext context, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = ParseContext.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        return (T) field.get(context);
    }
}
