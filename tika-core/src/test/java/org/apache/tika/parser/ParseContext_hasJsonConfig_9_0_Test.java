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
