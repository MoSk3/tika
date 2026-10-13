package org.apache.tika.parser;

import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.ParserDecorator;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import java.util.Collection;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaTypeRegistry;
import org.apache.tika.parser.multiple.AbstractMultipleParser.MetadataPolicy;
import org.apache.tika.parser.multiple.FallbackParser;
import java.lang.reflect.Constructor;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ParserDecorator_withMimeFilters_2_0_Test {

    private Parser mockParser;

    @BeforeEach
    public void setUp() {
        mockParser = mock(Parser.class);
    }

    @Test
    public void testWithMimeFilters_NoIncludeNoExclude_ReturnsOriginalParser() {
        Parser result = ParserDecorator.withMimeFilters(mockParser, Collections.emptySet(), Collections.emptySet());
        assertSame(mockParser, result);
    }
}
