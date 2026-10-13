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

import org.junit.jupiter.api.*;
import org.mockito.*;

import org.apache.tika.metadata.Metadata;

public class ParseContext_newMetadata_12_0_Test {

    @Test
    public void testNewMetadata() throws Exception {
        // Create an instance of ParseContext
        ParseContext parseContext = new ParseContext();
        // Use reflection to access the private method newMetadata
        Method newMetadataMethod = ParseContext.class.getDeclaredMethod("newMetadata");
        newMetadataMethod.setAccessible(true);
        // Invoke the newMetadata method
        Metadata metadata = (Metadata) newMetadataMethod.invoke(parseContext);
        // Assert that the returned Metadata object is not null
        assertNotNull(metadata, "The Metadata object should not be null");
        // Verify that the Metadata object is an instance of Metadata class
        assertTrue(metadata instanceof Metadata, "The object should be an instance of Metadata");
        // Use reflection to access the private field 'metadataMap' inside the Metadata class
        Method getNamesMethod = Metadata.class.getDeclaredMethod("names");
        getNamesMethod.setAccessible(true);
        String[] names = (String[]) getNamesMethod.invoke(metadata);
        // Additional checks can be performed on the Metadata object if needed
        // For example, checking the initial state of the Metadata object
        assertEquals(0, names.length, "The Metadata object should be initialized with an empty array of names");
    }
}
