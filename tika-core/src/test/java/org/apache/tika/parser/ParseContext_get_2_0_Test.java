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

public class ParseContext_get_2_0_Test {

    private ParseContext parseContext;

    @BeforeEach
    public void setUp() {
        parseContext = new ParseContext();
    }

    @Test
    public void testGetWithExistingValue() throws Exception {
        Class<String> key = String.class;
        String expectedValue = "testValue";
        // Use reflection to access the private 'context' field
        Field contextField = ParseContext.class.getDeclaredField("context");
        contextField.setAccessible(true);
        Map<String, Object> contextMap = (Map<String, Object>) contextField.get(parseContext);
        contextMap.put(key.getName(), expectedValue);
        String result = parseContext.get(key, "defaultValue");
        assertEquals(expectedValue, result);
    }

    @Test
    public void testGetWithDefaultValue() {
        Class<String> key = String.class;
        String defaultValue = "defaultValue";
        String result = parseContext.get(key, defaultValue);
        assertEquals(defaultValue, result);
    }

    @Test
    public void testGetWithNullDefaultValue() {
        Class<String> key = String.class;
        String result = parseContext.get(key, null);
        assertNull(result);
    }
}
