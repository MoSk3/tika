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

import java.util.Map;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import org.apache.tika.config.JsonConfig;

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
