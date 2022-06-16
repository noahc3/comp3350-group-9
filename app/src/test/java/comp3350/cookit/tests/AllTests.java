package comp3350.cookit.tests;

import junit.framework.TestCase;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.cookit.tests.business.MultiplyServingSizeTests;
import comp3350.cookit.tests.objects.AuthorTests;
import comp3350.cookit.tests.objects.FractionTests;
import comp3350.cookit.tests.objects.IngredientListTests;
import comp3350.cookit.tests.objects.IngredientTests;
import comp3350.cookit.tests.objects.RecipeTests;
import comp3350.cookit.tests.objects.ReviewTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AuthorTests.class,
        FractionTests.class,
        IngredientListTests.class,
        IngredientTests.class,
        RecipeTests.class,
        ReviewTests.class,
        MultiplyServingSizeTests.class})
public class AllTests extends TestCase {
}
