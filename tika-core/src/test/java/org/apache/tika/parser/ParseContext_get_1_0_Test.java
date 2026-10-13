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
import org.mockito.*;

public class ParseContext_get_1_0_Test {

    private ParseContext parseContext;

    @BeforeEach
    public void setUp() {
        parseContext = new ParseContext();
    }

    @Test
    public void testGetExistingKey() throws NoSuchFieldException, IllegalAccessException {
        // Arrange
        String key = "java.lang.String";
        String expectedValue = "TestValue";
        // Use reflection to set the private 'context' field
        Field contextField = ParseContext.class.getDeclaredField("context");
        contextField.setAccessible(true);
        Map<String, Object> contextMap = new HashMap<>();
        contextMap.put(key, expectedValue);
        contextField.set(parseContext, contextMap);
        // Act
        String actualValue = parseContext.get(String.class);
        // Assert
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testGetNonExistingKey() {
        // Act
        String actualValue = parseContext.get(String.class);
        // Assert
        assertNull(actualValue);
    }
}
