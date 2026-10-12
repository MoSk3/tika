package org.apache.tika.config;

import org.apache.tika.config.TimeoutLimits;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.Serializable;
import java.util.Objects;
import org.apache.tika.annotation.TikaComponent;
import org.apache.tika.parser.ParseContext;

public class TimeoutLimits_toString_6_0_Test {

    @Test
    public void testToString() {
        TimeoutLimits limits = new TimeoutLimits(1000, 500);
        assertEquals("TimeoutLimits{totalTaskTimeoutMillis=1000, progressTimeoutMillis=500}", limits.toString());
    }
}
