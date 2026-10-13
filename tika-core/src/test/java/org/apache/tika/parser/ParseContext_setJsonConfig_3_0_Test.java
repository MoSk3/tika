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
import java.util.Map;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import org.apache.tika.config.JsonConfig;

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
    
    @Test
    void testSetJsonConfig_Null_RemovesResolvedConfigWhenPresent() throws Exception {
        JsonConfig mockConfig = mock(JsonConfig.class);
        parseContext.setJsonConfig("testConfig", mockConfig);

        // Make resolvedConfigs non-null and containing the key
        Field resolvedConfigsField = ParseContext.class.getDeclaredField("resolvedConfigs");
        resolvedConfigsField.setAccessible(true);
        Map<String, Object> resolved = new java.util.HashMap<>();
        resolved.put("testConfig", "resolvedValue");
        resolved.put("otherConfig", "otherValue");
        resolvedConfigsField.set(parseContext, resolved);

        parseContext.setJsonConfig("testConfig", (JsonConfig) null);

        // Killed if the condition is negated: remove would be skipped
        assertFalse(resolved.containsKey("testConfig"));
        // Other entries untouched
        assertEquals("otherValue", resolved.get("otherConfig"));
    }

    @Test
    void testSetJsonConfig_Null_WhenResolvedConfigsIsNull_DoesNotThrow() throws Exception {
        JsonConfig mockConfig = mock(JsonConfig.class);
        parseContext.setJsonConfig("testConfig", mockConfig);

        Field resolvedConfigsField = ParseContext.class.getDeclaredField("resolvedConfigs");
        resolvedConfigsField.setAccessible(true);
        resolvedConfigsField.set(parseContext, null);

        // With the condition negated this would throw an NPE
        assertDoesNotThrow(() -> parseContext.setJsonConfig("testConfig", (JsonConfig) null));
        assertFalse(parseContext.getJsonConfigs().containsKey("testConfig"));
    }
}
