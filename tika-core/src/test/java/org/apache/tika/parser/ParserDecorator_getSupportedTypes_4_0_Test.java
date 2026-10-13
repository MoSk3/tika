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

import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.*;
import org.mockito.*;

import org.apache.tika.mime.MediaType;

public class ParserDecorator_getSupportedTypes_4_0_Test {

    private Parser mockParser;

    private ParserDecorator parserDecorator;

    private ParseContext mockContext;

    @BeforeEach
    public void setUp() {
        mockParser = mock(Parser.class);
        parserDecorator = new ParserDecorator(mockParser);
        mockContext = mock(ParseContext.class);
    }

    @Test
    public void testGetSupportedTypes() {
        // Setup
        Set<MediaType> expectedTypes = Collections.singleton(MediaType.TEXT_PLAIN);
        when(mockParser.getSupportedTypes(mockContext)).thenReturn(expectedTypes);
        // Execute
        Set<MediaType> result = parserDecorator.getSupportedTypes(mockContext);
        // Verify
        assertEquals(expectedTypes, result, "The supported types should match the expected types.");
    }
}
