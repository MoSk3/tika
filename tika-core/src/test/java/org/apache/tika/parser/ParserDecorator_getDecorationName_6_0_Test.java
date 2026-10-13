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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.*;
import org.mockito.*;

public class ParserDecorator_getDecorationName_6_0_Test {

    private ParserDecorator parserDecorator;

    @BeforeEach
    public void setUp() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // Using reflection to create an instance of ParserDecorator
        Constructor<ParserDecorator> constructor = ParserDecorator.class.getDeclaredConstructor(Parser.class);
        constructor.setAccessible(true);
        parserDecorator = constructor.newInstance((Parser) null);
    }

    @Test
    public void testGetDecorationName() {
        // Testing the getDecorationName method
        String decorationName = parserDecorator.getDecorationName();
        assertNull(decorationName, "Expected decoration name to be null");
    }
}
