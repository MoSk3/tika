package org.apache.tika.config;

import org.junit.runner.RunWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;

@RunWith(value = JUnitPlatform.class)
@SelectClasses(value = { TimeoutLimits_equals_7_0_Test.class, TimeoutLimits_getProcessTimeoutMillis_5_0_Test.class, TimeoutLimits_hashCode_8_0_Test.class, TimeoutLimits_toString_6_0_Test.class })
public class TimeoutLimits_Suite {
}
