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

import java.lang.reflect.Field;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParseContext_copyFrom_ResolvedConfigsNull_Test {

    private ParseContext sourceContext;

    private ParseContext targetContext;

    @BeforeEach
    public void setUp() {
        sourceContext = new ParseContext();
        targetContext = new ParseContext();
    }

    @Test
    public void testCopyFromCreatesResolvedConfigsWhenTargetMapIsNull() throws Exception {
        sourceContext.setResolvedConfig("resolvedKey", "resolvedValue");

        // Force the target's resolvedConfigs to null so the lazy-init branch is taken
        setResolvedConfigs(targetContext, null);
        assertNull(getResolvedConfigs(targetContext));

        targetContext.copyFrom(sourceContext);

        Map<String, Object> result = getResolvedConfigs(targetContext);
        assertNotNull(result, "resolvedConfigs should be created when it was null");
        assertEquals(1, result.size());
        assertEquals("resolvedValue", result.get("resolvedKey"));
        // The map must be a copy, not the source's map instance
        assertNotSame(getResolvedConfigs(sourceContext), result);
    }

    @Test
    public void testCopyFromKeepsExistingResolvedConfigsWhenTargetMapNotNull() throws Exception {
        targetContext.setResolvedConfig("existingKey", "existingValue");
        sourceContext.setResolvedConfig("resolvedKey", "resolvedValue");

        Map<String, Object> mapBefore = getResolvedConfigs(targetContext);
        assertNotNull(mapBefore);

        targetContext.copyFrom(sourceContext);

        Map<String, Object> result = getResolvedConfigs(targetContext);
        // Existing map is reused and merged, not replaced
        assertSame(mapBefore, result);
        assertEquals("existingValue", result.get("existingKey"));
        assertEquals("resolvedValue", result.get("resolvedKey"));
        assertEquals(2, result.size());
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getResolvedConfigs(ParseContext context) throws Exception {
        Field field = ParseContext.class.getDeclaredField("resolvedConfigs");
        field.setAccessible(true);
        return (Map<String, Object>) field.get(context);
    }

    private void setResolvedConfigs(ParseContext context, Map<String, Object> value)
            throws Exception {
        Field field = ParseContext.class.getDeclaredField("resolvedConfigs");
        field.setAccessible(true);
        field.set(context, value);
    }
}