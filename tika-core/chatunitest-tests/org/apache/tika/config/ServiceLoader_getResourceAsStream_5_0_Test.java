package org.apache.tika.config;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import aQute.bnd.annotation.spi.ServiceConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.tika.detect.Detector;
import org.apache.tika.detect.EncodingDetector;
import org.apache.tika.exception.TikaConfigException;
import org.apache.tika.language.detect.LanguageDetector;
import org.apache.tika.metadata.filter.MetadataFilter;
import org.apache.tika.parser.Parser;
import org.apache.tika.utils.ServiceLoaderUtils;

@ExtendWith(MockitoExtension.class)
public class ServiceLoader_getResourceAsStream_5_0_Test {

    @Test
    void testGetResourceAsStream_withLoader() {
        // Given
        ClassLoader classLoader = mock(ClassLoader.class);
        when(classLoader.getResourceAsStream(anyString())).thenReturn(null);
        ServiceLoader serviceLoader = new ServiceLoader(classLoader);
        // When
        InputStream actual = serviceLoader.getResourceAsStream("test.txt");
        // Then
        assertEquals(null, actual);
    }

    @Test
    void testGetResourceAsStream_withoutLoader() {
        // Given
        ServiceLoader serviceLoader = new ServiceLoader();
        // When
        InputStream actual = serviceLoader.getResourceAsStream("test.txt");
        // Then
        assertNull(actual);
    }

    @Test
    void testGetResourceAsStream_withDynamic() {
        // Given
        ClassLoader classLoader = mock(ClassLoader.class);
        when(classLoader.getResourceAsStream(anyString())).thenReturn(null);
        ServiceLoader serviceLoader = new ServiceLoader(classLoader, true);
        // When
        InputStream actual = serviceLoader.getResourceAsStream("test.txt");
        // Then
        assertEquals(null, actual);
    }
}
