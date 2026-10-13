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

public class ParseContext_setResolvedConfig_8_0_Test {

    private ParseContext parseContext;

    @BeforeEach
    public void setUp() {
        parseContext = new ParseContext();
    }

    @Test
    public void testSetResolvedConfig_AddConfig() throws NoSuchFieldException, IllegalAccessException {
        String name = "testConfig";
        Object config = new Object();
        parseContext.setResolvedConfig(name, config);
        Map<String, Object> resolvedConfigs = getResolvedConfigs(parseContext);
        assertNotNull(resolvedConfigs);
        assertEquals(config, resolvedConfigs.get(name));
    }

    @Test
    public void testSetResolvedConfig_RemoveConfig() throws NoSuchFieldException, IllegalAccessException {
        String name = "testConfig";
        Object config = new Object();
        parseContext.setResolvedConfig(name, config);
        parseContext.setResolvedConfig(name, null);
        Map<String, Object> resolvedConfigs = getResolvedConfigs(parseContext);
        assertNotNull(resolvedConfigs);
        assertFalse(resolvedConfigs.containsKey(name));
    }

    @Test
    public void testSetResolvedConfig_NullResolvedConfigs() throws NoSuchFieldException, IllegalAccessException {
        String name = "testConfig";
        Object config = new Object();
        // Simulate resolvedConfigs being null
        Field field = ParseContext.class.getDeclaredField("resolvedConfigs");
        field.setAccessible(true);
        field.set(parseContext, null);
        parseContext.setResolvedConfig(name, config);
        Map<String, Object> resolvedConfigs = getResolvedConfigs(parseContext);
        assertNotNull(resolvedConfigs);
        assertEquals(config, resolvedConfigs.get(name));
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getResolvedConfigs(ParseContext parseContext) throws NoSuchFieldException, IllegalAccessException {
        Field field = ParseContext.class.getDeclaredField("resolvedConfigs");
        field.setAccessible(true);
        return (Map<String, Object>) field.get(parseContext);
    }
}
