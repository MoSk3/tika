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

import java.lang.reflect.Method;
import java.util.Map;

import org.junit.jupiter.api.*;
import org.mockito.*;

import org.apache.tika.config.JsonConfig;

public class ParseContext_setJsonConfig_4_0_Test {

    private ParseContext parseContext;

    @BeforeEach
    public void setUp() {
        parseContext = new ParseContext();
    }

    @Test
    public void testSetJsonConfigWithValidJson() throws Exception {
        String name = "testConfig";
        String json = "{\"key\":\"value\"}";
        Method setJsonConfigMethod = ParseContext.class.getDeclaredMethod("setJsonConfig", String.class, String.class);
        setJsonConfigMethod.setAccessible(true);
        setJsonConfigMethod.invoke(parseContext, name, json);
        Map<String, JsonConfig> jsonConfigs = parseContext.getJsonConfigs();
        assertTrue(jsonConfigs.containsKey(name));
        assertNotNull(jsonConfigs.get(name));
    }

    @Test
    public void testSetJsonConfigWithNullJson() throws Exception {
        String name = "testConfig";
        String json = null;
        Method setJsonConfigMethod = ParseContext.class.getDeclaredMethod("setJsonConfig", String.class, String.class);
        setJsonConfigMethod.setAccessible(true);
        setJsonConfigMethod.invoke(parseContext, name, json);
        Map<String, JsonConfig> jsonConfigs = parseContext.getJsonConfigs();
        assertFalse(jsonConfigs.containsKey(name));
        assertNull(jsonConfigs.get(name));
    }
}
