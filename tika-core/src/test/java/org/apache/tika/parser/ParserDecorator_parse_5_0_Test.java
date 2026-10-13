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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.*;
import org.mockito.*;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;

class ParserDecorator_parse_5_0_Test {

    private Parser mockParser;

    private ParserDecorator parserDecorator;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        mockParser = mock(Parser.class);
        Constructor<ParserDecorator> constructor = ParserDecorator.class.getDeclaredConstructor(Parser.class);
        constructor.setAccessible(true);
        parserDecorator = constructor.newInstance(mockParser);
    }

    @Test
    void testParse() throws IOException, SAXException, TikaException, NoSuchFieldException, IllegalAccessException {
        // Prepare the input parameters
        TikaInputStream tis = TikaInputStream.get(new ByteArrayInputStream(new byte[0]));
        ContentHandler handler = mock(ContentHandler.class);
        Metadata metadata = new Metadata();
        ParseContext context = new ParseContext();
        // Invoke the parse method
        parserDecorator.parse(tis, handler, metadata, context);
        // Verify that the parse method of the wrapped parser was called
        verify(mockParser, times(1)).parse(tis, handler, metadata, context);
    }
}
