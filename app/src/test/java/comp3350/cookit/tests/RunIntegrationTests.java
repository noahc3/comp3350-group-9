package comp3350.cookit.tests;

import junit.framework.TestCase;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.cookit.tests.integration.AccessTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessTests.class})
public class RunIntegrationTests extends TestCase {
    public static final boolean USE_STUBDATASTORE = false;
}
