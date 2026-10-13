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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import org.apache.tika.mime.MediaType;

@ExtendWith(MockitoExtension.class)
public class ParserDecorator_withTypes_0_0_Test {

    @Test
    public void testWithTypes() throws Exception {
        // Create a mock Parser instance using Mockito
        Parser mockParser = mock(Parser.class);
        // Create a set of MediaTypes
        Set<MediaType> mediaTypes = new HashSet<>();
        mediaTypes.add(MediaType.application("pdf"));
        mediaTypes.add(MediaType.text("plain"));
        // Call the withTypes method
        Parser result = ParserDecorator.withTypes(mockParser, mediaTypes);
        // Verify the result is not null
        assertNotNull(result);
        // Verify the result is an instance of MimeFilteringDecorator using reflection
        assertTrue(result.getClass().getSimpleName().equals("MimeFilteringDecorator"));
        // Additional assertions can be added here if necessary
    }
}
