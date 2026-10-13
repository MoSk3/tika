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
import org.mockito.*;

import org.apache.tika.config.JsonConfig;

public class ParseContext_hashCode_15_0_Test {

    @Test
    public void testHashCode() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        ParseContext parseContext = new ParseContext();
        // Access the private fields using reflection
        Field contextField = ParseContext.class.getDeclaredField("context");
        contextField.setAccessible(true);
        Map<String, Object> contextMap = (Map<String, Object>) contextField.get(parseContext);
        Field jsonConfigsField = ParseContext.class.getDeclaredField("jsonConfigs");
        jsonConfigsField.setAccessible(true);
        Map<String, JsonConfig> jsonConfigsMap = (Map<String, JsonConfig>) jsonConfigsField.get(parseContext);
        // Test with empty maps
        int expectedHashCode = contextMap.hashCode() * 31 + jsonConfigsMap.hashCode();
        assertEquals(expectedHashCode, parseContext.hashCode());
        // Test with populated maps
        contextMap.put("key1", "value1");
        jsonConfigsMap.put("config1", mock(JsonConfig.class));
        expectedHashCode = contextMap.hashCode() * 31 + jsonConfigsMap.hashCode();
        assertEquals(expectedHashCode, parseContext.hashCode());
        // Test with different values
        contextMap.put("key2", "value2");
        jsonConfigsMap.put("config2", mock(JsonConfig.class));
        expectedHashCode = contextMap.hashCode() * 31 + jsonConfigsMap.hashCode();
        assertEquals(expectedHashCode, parseContext.hashCode());
    }
}
