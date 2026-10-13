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

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import org.apache.tika.config.JsonConfig;

@ExtendWith(MockitoExtension.class)
public class ParseContext_equals_14_0_Test {

    @Test
    public void testEqualsSameObject() {
        ParseContext context = new ParseContext();
        assertTrue(context.equals(context), "A ParseContext object should be equal to itself.");
    }

    @Test
    public void testEqualsNullObject() {
        ParseContext context = new ParseContext();
        assertFalse(context.equals(null), "A ParseContext object should not be equal to null.");
    }

    @Test
    public void testEqualsDifferentClass() {
        ParseContext context = new ParseContext();
        assertFalse(context.equals(new Object()), "A ParseContext object should not be equal to an object of a different class.");
    }

    @Test
    public void testEqualsDifferentContext() throws Exception {
        ParseContext context1 = new ParseContext();
        ParseContext context2 = new ParseContext();
        context1.set(String.class, "value1");
        context2.set(String.class, "value2");
        assertFalse(context1.equals(context2), "ParseContext objects with different context maps should not be equal.");
    }

    @Test
    public void testEqualsDifferentJsonConfigs() throws Exception {
        ParseContext context1 = new ParseContext();
        ParseContext context2 = new ParseContext();
        JsonConfig config1 = mock(JsonConfig.class);
        JsonConfig config2 = mock(JsonConfig.class);
        context1.setJsonConfig("config1", config1);
        context2.setJsonConfig("config2", config2);
        assertFalse(context1.equals(context2), "ParseContext objects with different jsonConfigs maps should not be equal.");
    }

    @Test
    public void testEqualsSameContent() throws Exception {
        ParseContext context1 = new ParseContext();
        ParseContext context2 = new ParseContext();
        context1.set(String.class, "value");
        context2.set(String.class, "value");
        JsonConfig config = mock(JsonConfig.class);
        context1.setJsonConfig("config", config);
        context2.setJsonConfig("config", config);
        assertTrue(context1.equals(context2), "ParseContext objects with the same context and jsonConfigs should be equal.");
    }
}
