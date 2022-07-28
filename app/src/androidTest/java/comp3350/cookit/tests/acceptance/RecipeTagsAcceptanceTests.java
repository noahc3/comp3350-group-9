package comp3350.cookit.tests.acceptance;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
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
public class RecipeTagsAcceptanceTests {

    @Rule
    public ActivityScenarioRule<HomeListActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(HomeListActivity.class);

    @Test
    public void testTaggedRecipes() {
        onView(withText("Grandma's Oatmeal Cookies")).perform(scrollTo(), click());
        onView(withId(R.id.recipeTitle)).check(matches(isDisplayed())).check(matches(withText("Grandma's Oatmeal Cookies")));
        onView(withId(R.id.tagsList)).check(matches(hasChildCount(4)));
        onView(withId(R.id.tagsList)).check(matches(withChild(withText("Pastry"))));
        onView(withId(R.id.tagsList)).check(matches(withChild(withText("Sweet"))));
        onView(withId(R.id.tagsList)).check(matches(withChild(withText("Desert"))));
        onView(withId(R.id.tagsList)).check(matches(withChild(withText("All Day"))));

        onView(withText("Sweet")).perform(click());

        onView(withId(R.id.taggedListHeader)).check(matches(withText("Recipes tagged as 'Sweet'")));
        onView(withId(R.id.taggedRecipeList)).check(matches(hasChildCount(6)));
        onView(withId(R.id.taggedRecipeList)).check(matches(withChild(withChild(withChild(withChild(withText("Lemon Cranberry Muffins")))))));
        onView(withId(R.id.taggedRecipeList)).check(matches(withChild(withChild(withChild(withChild(withText("Rice Cake with Dulce de Leche and Dark Chocolate")))))));
        onView(withId(R.id.taggedRecipeList)).check(matches(withChild(withChild(withChild(withChild(withText("Grandma's Oatmeal Cookies")))))));
        onView(withId(R.id.taggedRecipeList)).check(matches(withChild(withChild(withChild(withChild(withText("Sweet and Salty Three-Seed Granola")))))));
        onView(withId(R.id.taggedRecipeList)).check(matches(withChild(withChild(withChild(withChild(withText("Bisquick Apple Coffee Cake")))))));
        onView(withId(R.id.taggedRecipeList)).check(matches(withChild(withChild(withChild(withChild(withText("Baked Oatmeal with Mixed Berries")))))));

        onView(withText("Lemon Cranberry Muffins")).perform(scrollTo(), click());

        onView(withId(R.id.recipeTitle)).check(matches(isDisplayed())).check(matches(withText("Lemon Cranberry Muffins")));
        onView(withId(R.id.tagsList)).check(matches(hasChildCount(4)));
        onView(withId(R.id.tagsList)).check(matches(withChild(withText("Pastry"))));
        onView(withId(R.id.tagsList)).check(matches(withChild(withText("Sweet"))));
        onView(withId(R.id.tagsList)).check(matches(withChild(withText("Snack"))));
        onView(withId(R.id.tagsList)).check(matches(withChild(withText("All Day"))));

        onView(withText("Snack")).perform(click());

        onView(withId(R.id.taggedListHeader)).check(matches(withText("Recipes tagged as 'Snack'")));
        onView(withId(R.id.taggedRecipeList)).check(matches(hasChildCount(3)));
        onView(withId(R.id.taggedRecipeList)).check(matches(withChild(withChild(withChild(withChild(withText("Lemon Cranberry Muffins")))))));
        onView(withId(R.id.taggedRecipeList)).check(matches(withChild(withChild(withChild(withChild(withText("Sweet and Salty Three-Seed Granola")))))));
        onView(withId(R.id.taggedRecipeList)).check(matches(withChild(withChild(withChild(withChild(withText("Baked Oatmeal with Mixed Berries")))))));
    }
}
