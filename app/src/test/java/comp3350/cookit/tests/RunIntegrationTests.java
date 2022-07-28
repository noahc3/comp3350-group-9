package comp3350.cookit.tests;

import junit.framework.TestCase;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.cookit.tests.business.AccessAuthorsTests;
import comp3350.cookit.tests.business.AccessRecipesTests;
import comp3350.cookit.tests.business.AccessReviewsTests;
import comp3350.cookit.tests.integration.BusinessPersistenceSeamTest;
import comp3350.cookit.tests.persistence.IDataStoreTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessAuthorsTests.class,
        AccessRecipesTests.class,
        AccessReviewsTests.class,
        IDataStoreTests.class,
        BusinessPersistenceSeamTest.class})
public class RunIntegrationTests extends TestCase {
    public static final boolean USE_STUBDATASTORE = false;
}
