package comp3350.cookit.tests.acceptance;


import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.cookit.R;
import comp3350.cookit.presentation.HomeListActivity;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class StoreRecipeContentAcceptanceTests {

    @Rule
    public ActivityScenarioRule<HomeListActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(HomeListActivity.class);

    @Test
    public void testPublishValidRecipe() {
        onView(withText("Publish New Recipe")).perform(click());

        onView(withId(R.id.recipeName)).perform(scrollTo(), click(), replaceText("Valid Recipe"));
        closeSoftKeyboard();
        onView(withId(R.id.authorName)).perform(scrollTo(), click(), replaceText("Valid Author"));
        closeSoftKeyboard();
        onView(withId(R.id.prepTime)).perform(scrollTo(), click(), replaceText("10"));
        closeSoftKeyboard();
        onView(withId(R.id.cookTime)).perform(scrollTo(), click(), replaceText("20"));
        closeSoftKeyboard();
        onView(withId(R.id.servingSize)).perform(scrollTo(), click(), replaceText("3"));
        closeSoftKeyboard();

        onView(withId(R.id.difficulty)).perform(scrollTo(), click(), replaceText("Moderate"));
        closeSoftKeyboard();

        // set tags

        onView(withId(R.id.tagsType)).perform(scrollTo(), click(), replaceText("Pastry"));
        closeSoftKeyboard();
        onView(withId(R.id.tagsTaste)).perform(scrollTo(), click(), replaceText("Sweet"));
        closeSoftKeyboard();
        onView(withId(R.id.tagsCourse)).perform(scrollTo(), click(), replaceText("Appetizer"));
        closeSoftKeyboard();
        onView(withId(R.id.tagsTimeOfDay)).perform(scrollTo(), click(), replaceText("All Day"));
        closeSoftKeyboard();
        onView(withId(R.id.tagsDiet)).perform(scrollTo(), click(), replaceText("Keto"));
        closeSoftKeyboard();
        onView(withId(R.id.tagsRegion)).perform(scrollTo(), click(), replaceText("African"));
        closeSoftKeyboard();
        onView(withId(R.id.tagsOccasion)).perform(scrollTo(), click(), replaceText("Christmas"));
        closeSoftKeyboard();
        onView(withId(R.id.tagsSeason)).perform(scrollTo(), click(), replaceText("Winter"));
        closeSoftKeyboard();

        // add ingredient A

        onView(withId(R.id.ingredientName)).perform(scrollTo(), click(), replaceText("Ingredient A"));
        closeSoftKeyboard();
        onView(withId(R.id.amountWhole)).perform(scrollTo(), click(), replaceText("3"));
        closeSoftKeyboard();

        onView(withId(R.id.units)).perform(scrollTo(), click(), replaceText("cup"));
        closeSoftKeyboard();

        onView(withId(R.id.addToList)).perform(click());

        onView(withId(R.id.ingredientList)).check(matches(withText("3 cup Ingredient A")));

        // add ingredient B

        onView(withId(R.id.ingredientName)).perform(scrollTo(), click(), replaceText("Ingredient B"));
        closeSoftKeyboard();
        onView(withId(R.id.amountWhole)).perform(scrollTo(), click(), replaceText("2"));
        closeSoftKeyboard();

        onView(withId(R.id.amountFraction)).perform(scrollTo(), click(), replaceText("1/2"));
        closeSoftKeyboard();

        onView(withId(R.id.units)).perform(scrollTo(), click(), replaceText("tsp"));
        closeSoftKeyboard();

        onView(withId(R.id.addToList)).perform(click());

        onView(withId(R.id.ingredientList)).check(matches(withText("3 cup Ingredient A\n2 1/2 tsp Ingredient B")));
        closeSoftKeyboard();

        // end ingredients

        onView(withId(R.id.directionList)).perform(scrollTo(), click(), replaceText("Some directions."));

        closeSoftKeyboard();

        onView(withId(R.id.publish)).perform(scrollTo(), click());


        // navigate to new recipe
        onView(withText("Valid Recipe")).perform(scrollTo(), click());
        onView(withId(R.id.recipeTitle)).perform(scrollTo()).check(matches(withText("Valid Recipe")));
        onView(withId(R.id.recipeAuthor)).perform(scrollTo()).check(matches(withText("Written by Valid Author")));
        onView(withId(R.id.recipeInstructions)).perform(scrollTo()).check(matches(withText("Some directions.")));
        onView(withId(R.id.ingredientList)).perform(scrollTo()).check(matches(hasChildCount(2)));
        onView(withId(R.id.ingredientList)).perform(scrollTo()).check(matches(withChild(withText("3 cup Ingredient A"))));
        onView(withId(R.id.ingredientList)).perform(scrollTo()).check(matches(withChild(withText("2 1/2 tsp Ingredient B"))));
        onView(withId(R.id.tagsList)).perform(scrollTo()).check(matches(hasChildCount(8)));
        onView(withId(R.id.tagsList)).perform(scrollTo()).check(matches(withChild(withText("Pastry"))));
        onView(withId(R.id.tagsList)).perform(scrollTo()).check(matches(withChild(withText("Sweet"))));
        onView(withId(R.id.tagsList)).perform(scrollTo()).check(matches(withChild(withText("Appetizer"))));
        onView(withId(R.id.tagsList)).perform(scrollTo()).check(matches(withChild(withText("All Day"))));
        onView(withId(R.id.tagsList)).perform(scrollTo()).check(matches(withChild(withText("Keto"))));
        onView(withId(R.id.tagsList)).perform(scrollTo()).check(matches(withChild(withText("African"))));
        onView(withId(R.id.tagsList)).perform(scrollTo()).check(matches(withChild(withText("Christmas"))));
        onView(withId(R.id.tagsList)).perform(scrollTo()).check(matches(withChild(withText("Winter"))));
        onView(withId(R.id.difficulty_display)).perform(scrollTo()).check(matches(withText("Difficulty: Moderate")));
        onView(withId(R.id.prep_time_display)).perform(scrollTo()).check(matches(withText("Prep Time: 10 minutes")));
        onView(withId(R.id.cook_time_display)).perform(scrollTo()).check(matches(withText("Cook Time: 20 minutes")));
        onView(withId(R.id.servingsText)).perform(scrollTo()).check(matches(withText("Creates 3 servings")));

        closeSoftKeyboard();
    }

    @Test
    public void testPublishRecipeWithValidationErrors() {
        onView(withText("Publish New Recipe")).perform(click());

        onView(withId(R.id.recipeName)).perform(scrollTo(), click(), replaceText("Invalid Recipe"));
        closeSoftKeyboard();
        onView(withId(R.id.prepTime)).perform(scrollTo(), click(), replaceText("10"));
        closeSoftKeyboard();
        onView(withId(R.id.servingSize)).perform(scrollTo(), click(), replaceText("3"));
        closeSoftKeyboard();

        onView(withId(R.id.difficulty)).perform(scrollTo(), click(), replaceText("Moderate"));
        closeSoftKeyboard();

        // set tags

        onView(withId(R.id.tagsType)).perform(scrollTo(), click(), replaceText("Pastry"));
        closeSoftKeyboard();
        onView(withId(R.id.tagsTaste)).perform(scrollTo(), click(), replaceText("Sweet"));
        closeSoftKeyboard();
        onView(withId(R.id.tagsCourse)).perform(scrollTo(), click(), replaceText("Appetizer"));
        closeSoftKeyboard();
        onView(withId(R.id.tagsTimeOfDay)).perform(scrollTo(), click(), replaceText("All Day"));
        closeSoftKeyboard();

        // add ingredient A

        onView(withId(R.id.ingredientName)).perform(scrollTo(), click(), replaceText("Ingredient A"));
        closeSoftKeyboard();
        onView(withId(R.id.amountWhole)).perform(scrollTo(), click(), replaceText("3"));
        closeSoftKeyboard();

        onView(withId(R.id.units)).perform(scrollTo(), click(), replaceText("cup"));
        closeSoftKeyboard();

        onView(withId(R.id.addToList)).perform(click());

        onView(withId(R.id.ingredientList)).check(matches(withText("3 cup Ingredient A")));

        // add ingredient B

        onView(withId(R.id.ingredientName)).perform(scrollTo(), click(), replaceText("Ingredient B"));
        closeSoftKeyboard();
        onView(withId(R.id.amountWhole)).perform(scrollTo(), click(), replaceText("2"));
        closeSoftKeyboard();

        onView(withId(R.id.amountFraction)).perform(scrollTo(), click(), replaceText("1/2"));
        closeSoftKeyboard();

        onView(withId(R.id.units)).perform(scrollTo(), click(), replaceText("tsp"));
        closeSoftKeyboard();

        onView(withId(R.id.addToList)).perform(click());

        onView(withId(R.id.ingredientList)).check(matches(withText("3 cup Ingredient A\n2 1/2 tsp Ingredient B")));

        // end ingredients

        onView(withId(R.id.directionList)).perform(scrollTo(), click(), replaceText("Some directions."));
        closeSoftKeyboard();

        closeSoftKeyboard();
        onView(withId(R.id.publish)).perform(scrollTo(), click());

        onView(withId(R.id.cookTime)).check(matches(hasErrorText("You must specify the cook time")));
        onView(withId(R.id.authorName)).check(matches(hasErrorText("You must provide an author")));

        onView(withId(R.id.authorName)).perform(scrollTo(), click(), replaceText("Invalid Author"));

        closeSoftKeyboard();
        onView(withId(R.id.publish)).perform(scrollTo(), click());

        onView(withId(R.id.cookTime)).check(matches(hasErrorText("You must specify the cook time")));

        onView(withId(R.id.cookTime)).perform(scrollTo(), click(), replaceText("20"));

        closeSoftKeyboard();
        onView(withId(R.id.publish)).perform(scrollTo(), click());

        // navigate to new recipe
        onView(withText("Invalid Recipe")).perform(scrollTo(), click());
        onView(withId(R.id.recipeTitle)).perform(scrollTo()).check(matches(withText("Invalid Recipe")));
        onView(withId(R.id.recipeAuthor)).perform(scrollTo()).check(matches(withText("Written by Invalid Author")));
        onView(withId(R.id.recipeInstructions)).perform(scrollTo()).check(matches(withText("Some directions.")));
        onView(withId(R.id.ingredientList)).perform(scrollTo()).check(matches(hasChildCount(2)));
        onView(withId(R.id.ingredientList)).perform(scrollTo()).check(matches(withChild(withText("3 cup Ingredient A"))));
        onView(withId(R.id.ingredientList)).perform(scrollTo()).check(matches(withChild(withText("2 1/2 tsp Ingredient B"))));
        onView(withId(R.id.tagsList)).perform(scrollTo()).check(matches(hasChildCount(4)));
        onView(withId(R.id.tagsList)).perform(scrollTo()).check(matches(withChild(withText("Pastry"))));
        onView(withId(R.id.tagsList)).perform(scrollTo()).check(matches(withChild(withText("Sweet"))));
        onView(withId(R.id.tagsList)).perform(scrollTo()).check(matches(withChild(withText("Appetizer"))));
        onView(withId(R.id.tagsList)).perform(scrollTo()).check(matches(withChild(withText("All Day"))));
        onView(withId(R.id.difficulty_display)).perform(scrollTo()).check(matches(withText("Difficulty: Moderate")));
        onView(withId(R.id.prep_time_display)).perform(scrollTo()).check(matches(withText("Prep Time: 10 minutes")));
        onView(withId(R.id.cook_time_display)).perform(scrollTo()).check(matches(withText("Cook Time: 20 minutes")));
        onView(withId(R.id.servingsText)).perform(scrollTo()).check(matches(withText("Creates 3 servings")));

        closeSoftKeyboard();
    }
}
