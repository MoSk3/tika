package org.apache.tika.config;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.Serializable;
import java.util.Objects;
import org.apache.tika.annotation.TikaComponent;
import org.apache.tika.parser.ParseContext;

@ExtendWith(MockitoExtension.class)
public class TimeoutLimits_hashCode_8_0_Test {

    @Mock
    private TimeoutLimits limits;

    @Test
    public void testHashCode() {
        // Test with default values
        when(limits.getTotalTaskTimeoutMillis()).thenReturn(TimeoutLimits.DEFAULT_TOTAL_TASK_TIMEOUT_MILLIS);
        when(limits.getProgressTimeoutMillis()).thenReturn(TimeoutLimits.DEFAULT_PROGRESS_TIMEOUT_MILLIS);
        assertEquals(0, limits.hashCode());
        // Test with different values
        when(limits.getTotalTaskTimeoutMillis()).thenReturn(1000L);
        when(limits.getProgressTimeoutMillis()).thenReturn(500L);
        assertEquals(1000 + 500, limits.hashCode());
        // Test with different values again
        when(limits.getTotalTaskTimeoutMillis()).thenReturn(2000L);
        when(limits.getProgressTimeoutMillis()).thenReturn(1000L);
        assertEquals(2000 + 1000, limits.hashCode());
    }
}
