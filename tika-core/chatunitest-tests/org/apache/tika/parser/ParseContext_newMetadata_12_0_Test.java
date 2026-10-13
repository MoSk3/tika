package org.apache.tika.parser;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import java.lang.reflect.Method;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.tika.config.JsonConfig;
import org.apache.tika.metadata.writefilter.MetadataWriteLimiterFactory;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

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
