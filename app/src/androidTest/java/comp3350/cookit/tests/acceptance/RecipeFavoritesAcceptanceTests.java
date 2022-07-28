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
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

import androidx.test.espresso.Espresso;
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
public class RecipeFavoritesAcceptanceTests {

    @Rule
    public ActivityScenarioRule<HomeListActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(HomeListActivity.class);

    @Test
    public void testFavorites() {
        onView(withId(R.id.favoriteList)).perform(click());
        onView(withId(R.id.favoriteListHeader)).check(matches(withText("Favorite List")));

        onView(withId(R.id.favoriteRecipeList)).check(matches(hasChildCount(3)));
        onView(withId(R.id.favoriteRecipeList)).check(matches(withChild(withChild(withChild(allOf(withChild(withText("Honey-Garlic Slow Cooker Chicken Thighs")), withChild(withText("Written by Myrna"))))))));
        onView(withId(R.id.favoriteRecipeList)).check(matches(withChild(withChild(withChild(allOf(withChild(withText("Grandma's Oatmeal Cookies")), withChild(withText("Written by Elise Bauer"))))))));
        onView(withId(R.id.favoriteRecipeList)).check(matches(withChild(withChild(withChild(allOf(withChild(withText("Sweet and Salty Three-Seed Granola")), withChild(withText("Written by Shilpa Uskokovic"))))))));

        onView(withText("Grandma's Oatmeal Cookies")).perform(scrollTo(), click());
        onView(withId(R.id.recipeTitle)).check(matches(isDisplayed())).check(matches(withText("Grandma's Oatmeal Cookies")));

        onView(withId(R.id.favorite)).perform(click());

        Espresso.pressBack();

        onView(withId(R.id.favoriteRecipeList)).check(matches(hasChildCount(2)));
        onView(withId(R.id.favoriteRecipeList)).check(matches(not(withChild(withChild(withChild(allOf(withChild(withText("Grandma's Oatmeal Cookies")), withChild(withText("Written by Elise Bauer")))))))));
        onView(withId(R.id.favoriteRecipeList)).check(matches(withChild(withChild(withChild(allOf(withChild(withText("Sweet and Salty Three-Seed Granola")), withChild(withText("Written by Shilpa Uskokovic"))))))));
        onView(withId(R.id.favoriteRecipeList)).check(matches(withChild(withChild(withChild(allOf(withChild(withText("Honey-Garlic Slow Cooker Chicken Thighs")), withChild(withText("Written by Myrna"))))))));

        Espresso.pressBack();

        onView(withText("Sesame Peanut Noodles")).perform(scrollTo(), click());
        onView(withId(R.id.recipeTitle)).check(matches(isDisplayed())).check(matches(withText("Sesame Peanut Noodles")));
        onView(withId(R.id.favorite)).perform(click());

        Espresso.pressBack();

        onView(withId(R.id.favoriteList)).perform(click());
        onView(withId(R.id.favoriteListHeader)).check(matches(withText("Favorite List")));

        onView(withId(R.id.favoriteRecipeList)).check(matches(not(withChild(withChild(withChild(allOf(withChild(withText("Grandma's Oatmeal Cookies")), withChild(withText("Written by Elise Bauer")))))))));
        onView(withId(R.id.favoriteRecipeList)).check(matches(withChild(withChild(withChild(allOf(withChild(withText("Sweet and Salty Three-Seed Granola")), withChild(withText("Written by Shilpa Uskokovic"))))))));
        onView(withId(R.id.favoriteRecipeList)).check(matches(withChild(withChild(withChild(allOf(withChild(withText("Honey-Garlic Slow Cooker Chicken Thighs")), withChild(withText("Written by Myrna"))))))));
        onView(withId(R.id.favoriteRecipeList)).check(matches(withChild(withChild(withChild(allOf(withChild(withText("Sesame Peanut Noodles")), withChild(withText("Written by Hannah Zimmerman"))))))));

        onView(withText("Sesame Peanut Noodles")).perform(scrollTo(), click());
        onView(withId(R.id.recipeTitle)).check(matches(isDisplayed())).check(matches(withText("Sesame Peanut Noodles")));
    }
}
