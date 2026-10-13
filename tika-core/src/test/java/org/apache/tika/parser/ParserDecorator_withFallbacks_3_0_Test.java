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

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.*;
import org.mockito.*;

import org.apache.tika.mime.MediaType;
import org.apache.tika.mime.MediaTypeRegistry;
import org.apache.tika.parser.multiple.AbstractMultipleParser.MetadataPolicy;
import org.apache.tika.parser.multiple.FallbackParser;

public class ParserDecorator_withFallbacks_3_0_Test {

    @Test
    public void testWithFallbacks_NoTypes() throws Exception {
        // Mock dependencies
        Parser mockParser1 = mock(Parser.class);
        Parser mockParser2 = mock(Parser.class);
        Collection<Parser> parsers = new HashSet<>();
        parsers.add(mockParser1);
        parsers.add(mockParser2);
        // Call the method with no types
        Parser result = ParserDecorator.withFallbacks(parsers, null);
        // Assert that the result is a FallbackParser
        assertTrue(result instanceof FallbackParser);
    }

    @Test
    public void testWithFallbacks_WithTypes() throws Exception {
        // Mock dependencies
        Parser mockParser1 = mock(Parser.class);
        Parser mockParser2 = mock(Parser.class);
        Collection<Parser> parsers = new HashSet<>();
        parsers.add(mockParser1);
        parsers.add(mockParser2);
        Set<MediaType> types = new HashSet<>();
        types.add(MediaType.TEXT_PLAIN);
        // Use reflection to access the private method withTypes
        ParserDecorator parserDecorator = new ParserDecorator(mockParser1);
        java.lang.reflect.Method withTypesMethod = ParserDecorator.class.getDeclaredMethod("withTypes", Parser.class, Set.class);
        withTypesMethod.setAccessible(true);
        // Call the method with types
        Parser result = (Parser) withTypesMethod.invoke(parserDecorator, new FallbackParser(MediaTypeRegistry.getDefaultRegistry(), MetadataPolicy.KEEP_ALL, parsers), types);
        // Assert that the result is not null
        assertNotNull(result);
    }
}
