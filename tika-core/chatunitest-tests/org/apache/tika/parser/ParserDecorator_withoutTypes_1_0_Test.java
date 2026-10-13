package org.apache.tika.parser;

import org.apache.tika.parser.Parser;
import org.apache.tika.mime.MediaType;
import java.util.Set;
import java.util.HashSet;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaTypeRegistry;
import org.apache.tika.parser.multiple.AbstractMultipleParser.MetadataPolicy;
import org.apache.tika.parser.multiple.FallbackParser;

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
        assertEquals("org.apache.tika.parser.MimeFilteringDecorator", resultParser.getClass().getName());
        // Further assertions can be added here to verify the behavior of the MimeFilteringDecorator
    }
}
