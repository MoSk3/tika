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

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.*;
import org.mockito.*;

import org.apache.tika.mime.MediaType;

class ParserDecorator_withoutTypes_1_0_Test {

    private Parser mockParser;

    @BeforeEach
    void setUp() {
        mockParser = Mockito.mock(Parser.class);
    }

    @Test
    void testWithoutTypes() throws Exception {
        // Setup test data
        Set<MediaType> excludeTypes = new HashSet<>();
        excludeTypes.add(MediaType.application("pdf"));
        excludeTypes.add(MediaType.image("jpeg"));
        // Invoke the method using reflection
        Parser resultParser = ParserDecorator.withoutTypes(mockParser, excludeTypes);
        // Assert that the result is not null
        assertNotNull(resultParser);
        // Assert that the result is an instance of MimeFilteringDecorator
        assertEquals("org.apache.tika.parser.ParserDecorator$MimeFilteringDecorator", resultParser.getClass().getName());
    }
}
