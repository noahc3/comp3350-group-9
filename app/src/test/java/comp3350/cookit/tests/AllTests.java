package comp3350.cookit.tests;

import junit.framework.TestCase;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.cookit.tests.business.AccessAuthorsTests;
import comp3350.cookit.tests.business.AccessRecipesTests;
import comp3350.cookit.tests.business.AccessReviewsTests;
import comp3350.cookit.tests.business.MultiplyServingSizeTests;
import comp3350.cookit.tests.objects.AuthorTests;
import comp3350.cookit.tests.objects.FractionTests;
import comp3350.cookit.tests.objects.IngredientListTests;
import comp3350.cookit.tests.objects.IngredientTests;
import comp3350.cookit.tests.objects.RecipeTests;
import comp3350.cookit.tests.objects.ReviewTests;
import comp3350.cookit.tests.persistence.IDataStoreTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AuthorTests.class,
        FractionTests.class,
        IngredientListTests.class,
        IngredientTests.class,
        RecipeTests.class,
        ReviewTests.class,
        MultiplyServingSizeTests.class,
        AccessAuthorsTests.class,
        AccessRecipesTests.class,
        AccessReviewsTests.class,
        IDataStoreTests.class})
public class AllTests extends TestCase {
}
