package comp3350.cookit.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.cookit.tests.acceptance.DifficultyInformationAcceptanceTests;
import comp3350.cookit.tests.acceptance.RecipeBrowsingAcceptanceTests;
import comp3350.cookit.tests.acceptance.RecipeFavoritesAcceptanceTests;
import comp3350.cookit.tests.acceptance.RecipeTagsAcceptanceTests;
import comp3350.cookit.tests.acceptance.ReviewsAcceptanceTests;
import comp3350.cookit.tests.acceptance.ServingSizeConversionsAcceptanceTests;
import comp3350.cookit.tests.acceptance.StoreRecipeContentAcceptanceTests;
import comp3350.cookit.tests.acceptance.TagsPageAcceptanceTests;
import comp3350.cookit.tests.acceptance.ViewRecipesAcceptanceTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DifficultyInformationAcceptanceTests.class,
        RecipeBrowsingAcceptanceTests.class,
        RecipeFavoritesAcceptanceTests.class,
        RecipeTagsAcceptanceTests.class,
        ReviewsAcceptanceTests.class,
        ServingSizeConversionsAcceptanceTests.class,
        StoreRecipeContentAcceptanceTests.class,
        TagsPageAcceptanceTests.class,
        ViewRecipesAcceptanceTests.class})
public class RunAcceptanceTests {
}
