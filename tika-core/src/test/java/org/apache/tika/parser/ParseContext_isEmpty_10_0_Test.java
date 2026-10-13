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
