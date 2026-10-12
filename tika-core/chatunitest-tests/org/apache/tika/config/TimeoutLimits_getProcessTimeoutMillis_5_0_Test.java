package org.apache.tika.config;

import org.apache.tika.config.JsonConfig;
import org.apache.tika.parser.ParseContext;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.Serializable;
import java.util.Objects;
import org.apache.tika.annotation.TikaComponent;

public class TimeoutLimits_getProcessTimeoutMillis_5_0_Test {

    @Test
    public void testGetProcessTimeoutMillis() {
        ParseContext context = new ParseContext();
        TimeoutLimits limits = new TimeoutLimits();
        // test with null context
        assertEquals(limits.getProcessTimeoutMillis(null, 1000L), 1000L);
        // test with non-null context
        context.set(TimeoutLimits.class, limits);
        assertEquals(limits.getProcessTimeoutMillis(context, 1000L), 1000L);
    }
}
