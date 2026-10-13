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
public class ParseContext_toString_16_0_Test {

    @Test
    public void testToString() throws NoSuchFieldException, IllegalAccessException {
        // Create an instance of ParseContext
        ParseContext parseContext = new ParseContext();
        // Use reflection to access the private fields
        Field contextField = ParseContext.class.getDeclaredField("context");
        contextField.setAccessible(true);
        Map<String, Object> contextMap = new HashMap<>();
        contextMap.put("key1", "value1");
        contextField.set(parseContext, contextMap);
        Field jsonConfigsField = ParseContext.class.getDeclaredField("jsonConfigs");
        jsonConfigsField.setAccessible(true);
        Map<String, JsonConfig> jsonConfigsMap = new HashMap<>();
        // Use Mockito to mock JsonConfig
        JsonConfig jsonConfig = mock(JsonConfig.class);
        when(jsonConfig.toString()).thenReturn("MockedJsonConfig");
        jsonConfigsMap.put("config1", jsonConfig);
        jsonConfigsField.set(parseContext, jsonConfigsMap);
        // Expected string representation
        String expectedString = "ParseContext{" + "context={key1=value1}" + ", jsonConfigs={config1=MockedJsonConfig}" + '}';
        // Assert the toString output
        assertEquals(expectedString, parseContext.toString());
    }
}
