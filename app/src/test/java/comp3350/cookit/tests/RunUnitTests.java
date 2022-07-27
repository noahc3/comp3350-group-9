package comp3350.cookit.tests;

import junit.framework.TestCase;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.cookit.tests.business.AccessAuthorsTests;
import comp3350.cookit.tests.business.AccessRecipesTests;
import comp3350.cookit.tests.business.AccessReviewsTests;
import comp3350.cookit.tests.business.ServingSizeUtilitiesTests;
import comp3350.cookit.tests.business.StringUtilitiesTests;
import comp3350.cookit.tests.objects.AuthorTests;
import comp3350.cookit.tests.objects.FractionTests;
import comp3350.cookit.tests.objects.IngredientListTests;
import comp3350.cookit.tests.objects.IngredientTests;
import comp3350.cookit.tests.objects.RecipeTests;
import comp3350.cookit.tests.objects.ReviewTests;
import comp3350.cookit.tests.persistence.IDataStoreTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessAuthorsTests.class,
        AccessRecipesTests.class,
        AccessReviewsTests.class,
        IDataStoreTests.class,
        AuthorTests.class,
        FractionTests.class,
        IngredientListTests.class,
        IngredientTests.class,
        RecipeTests.class,
        ReviewTests.class,
        ServingSizeUtilitiesTests.class,
        StringUtilitiesTests.class})
public class RunUnitTests extends TestCase {
}
