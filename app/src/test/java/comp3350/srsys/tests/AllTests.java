package comp3350.srsys.tests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import comp3350.srsys.tests.objects.CourseTest;
import comp3350.srsys.tests.objects.SCTest;
import comp3350.srsys.tests.objects.StudentTest;
import comp3350.srsys.tests.business.CalculateGPATest;

public class AllTests extends TestCase
{
	public static TestSuite suite;

    public static Test suite()
    {
        System.out.println("Launching Test Suite.");
        suite = new TestSuite("All tests");
        tObjects();
        tBusiness();
        return suite;
    }

    private static void tObjects()
    {
        suite.addTestSuite(StudentTest.class);
        suite.addTestSuite(CourseTest.class);
        suite.addTestSuite(SCTest.class);
    }

    private static void tBusiness()
    {
        suite.addTestSuite(CalculateGPATest.class);
    }
}
