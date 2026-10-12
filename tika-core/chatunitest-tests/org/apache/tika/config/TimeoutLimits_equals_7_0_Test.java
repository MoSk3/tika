package org.apache.tika.config;

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

public class TimeoutLimits_equals_7_0_Test {

    @Test
    public void testEquals_SameInstance() {
        TimeoutLimits limits = new TimeoutLimits();
        assertTrue(limits.equals(limits));
    }

    @Test
    public void testEquals_SameValues() {
        TimeoutLimits limits1 = new TimeoutLimits(1000L, 2000L);
        TimeoutLimits limits2 = new TimeoutLimits(1000L, 2000L);
        assertTrue(limits1.equals(limits2));
    }

    @Test
    public void testEquals_DifferentValues() {
        TimeoutLimits limits1 = new TimeoutLimits(1000L, 2000L);
        TimeoutLimits limits2 = new TimeoutLimits(2000L, 1000L);
        assertFalse(limits1.equals(limits2));
    }

    @Test
    public void testEquals_DifferentClass() {
        TimeoutLimits limits = new TimeoutLimits();
        assertFalse(limits.equals(new Object()));
    }

    @Test
    public void testEquals_Null() {
        TimeoutLimits limits = new TimeoutLimits();
        assertFalse(limits.equals(null));
    }
}
