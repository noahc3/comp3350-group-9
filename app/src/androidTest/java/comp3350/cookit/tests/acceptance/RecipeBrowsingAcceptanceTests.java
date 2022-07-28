package comp3350.cookit.tests.acceptance;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

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
public class RecipeBrowsingAcceptanceTests {

    @Rule
    public ActivityScenarioRule<HomeListActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(HomeListActivity.class);

    @Test
    public void testRecipeList() {
        onView(withId(R.id.header)).check(matches(withText("Recipes")));
        onView(withId(R.id.recipeList)).check(matches(withChild(withChild(withChild(allOf(withChild(withText("Lemon Cranberry Muffins")), withChild(withText("Written by bobpiazza"))))))));
        onView(withId(R.id.recipeList)).check(matches(withChild(withChild(withChild(allOf(withChild(withText("Honey-Garlic Slow Cooker Chicken Thighs")), withChild(withText("Written by Myrna"))))))));
        onView(withId(R.id.recipeList)).check(matches(withChild(withChild(withChild(allOf(withChild(withText("Sesame Peanut Noodles")), withChild(withText("Written by Hannah Zimmerman"))))))));
        onView(withId(R.id.recipeList)).check(matches(withChild(withChild(withChild(allOf(withChild(withText("Grandma's Oatmeal Cookies")), withChild(withText("Written by Elise Bauer"))))))));
        onView(withId(R.id.recipeList)).check(matches(withChild(withChild(withChild(allOf(withChild(withText("Spinach Tofu Scramble")), withChild(withText("Written by Sara Bir"))))))));
        onView(withId(R.id.recipeList)).check(matches(withChild(withChild(withChild(allOf(withChild(withText("Shrimp Ceviche")), withChild(withText("Written by Elise Bauer"))))))));
        onView(withId(R.id.recipeList)).check(matches(withChild(withChild(withChild(allOf(withChild(withText("Brazilian Cheese Bread (Pão de Queijo)")), withChild(withText("Written by Elise Bauer"))))))));
        onView(withId(R.id.recipeList)).check(matches(withChild(withChild(withChild(allOf(withChild(withText("Sweet and Salty Three-Seed Granola")), withChild(withText("Written by Shilpa Uskokovic"))))))));
        onView(withId(R.id.recipeList)).check(matches(withChild(withChild(withChild(allOf(withChild(withText("Bisquick Apple Coffee Cake")), withChild(withText("Written by Elise Bauer"))))))));
        onView(withId(R.id.recipeList)).check(matches(withChild(withChild(withChild(allOf(withChild(withText("Baked Oatmeal with Mixed Berries")), withChild(withText("Written by Ariane Resnick"))))))));
        onView(withId(R.id.recipeList)).check(matches(withChild(withChild(withChild(allOf(withChild(withText("Philly Cheesesteak Sloppy Joes")), withChild(withText("Written by Nick Evans"))))))));
        onView(withId(R.id.recipeList)).check(matches(withChild(withChild(withChild(allOf(withChild(withText("Corn and Ricotta Bruschetta")), withChild(withText("Written by Georgia Freedman"))))))));
        onView(withId(R.id.recipeList)).check(matches(withChild(withChild(withChild(allOf(withChild(withText("Crash Hot Potatoes with Smoked Salmon")), withChild(withText("Written by Coco Morante"))))))));
        onView(withId(R.id.recipeList)).check(matches(withChild(withChild(withChild(allOf(withChild(withText("Rice Cake with Dulce de Leche and Dark Chocolate")), withChild(withText("Written by Micah Siva"))))))));

        onView(withText("Lemon Cranberry Muffins")).perform(scrollTo(), click());
        onView(withId(R.id.recipeTitle)).check(matches(isDisplayed())).check(matches(withText("Lemon Cranberry Muffins")));

        Espresso.pressBack();

        onView(withId(R.id.header)).check(matches(withText("Recipes")));
        onView(withId(R.id.recipeList)).check(matches(withChild(withChild(withChild(allOf(withChild(withText("Lemon Cranberry Muffins")), withChild(withText("Written by bobpiazza"))))))));
        onView(withId(R.id.recipeList)).check(matches(withChild(withChild(withChild(allOf(withChild(withText("Honey-Garlic Slow Cooker Chicken Thighs")), withChild(withText("Written by Myrna"))))))));
        onView(withId(R.id.recipeList)).check(matches(withChild(withChild(withChild(allOf(withChild(withText("Sesame Peanut Noodles")), withChild(withText("Written by Hannah Zimmerman"))))))));
        onView(withId(R.id.recipeList)).check(matches(withChild(withChild(withChild(allOf(withChild(withText("Grandma's Oatmeal Cookies")), withChild(withText("Written by Elise Bauer"))))))));
        onView(withId(R.id.recipeList)).check(matches(withChild(withChild(withChild(allOf(withChild(withText("Spinach Tofu Scramble")), withChild(withText("Written by Sara Bir"))))))));
        onView(withId(R.id.recipeList)).check(matches(withChild(withChild(withChild(allOf(withChild(withText("Shrimp Ceviche")), withChild(withText("Written by Elise Bauer"))))))));
        onView(withId(R.id.recipeList)).check(matches(withChild(withChild(withChild(allOf(withChild(withText("Brazilian Cheese Bread (Pão de Queijo)")), withChild(withText("Written by Elise Bauer"))))))));
        onView(withId(R.id.recipeList)).check(matches(withChild(withChild(withChild(allOf(withChild(withText("Sweet and Salty Three-Seed Granola")), withChild(withText("Written by Shilpa Uskokovic"))))))));
        onView(withId(R.id.recipeList)).check(matches(withChild(withChild(withChild(allOf(withChild(withText("Bisquick Apple Coffee Cake")), withChild(withText("Written by Elise Bauer"))))))));
        onView(withId(R.id.recipeList)).check(matches(withChild(withChild(withChild(allOf(withChild(withText("Baked Oatmeal with Mixed Berries")), withChild(withText("Written by Ariane Resnick"))))))));
        onView(withId(R.id.recipeList)).check(matches(withChild(withChild(withChild(allOf(withChild(withText("Philly Cheesesteak Sloppy Joes")), withChild(withText("Written by Nick Evans"))))))));
        onView(withId(R.id.recipeList)).check(matches(withChild(withChild(withChild(allOf(withChild(withText("Corn and Ricotta Bruschetta")), withChild(withText("Written by Georgia Freedman"))))))));
        onView(withId(R.id.recipeList)).check(matches(withChild(withChild(withChild(allOf(withChild(withText("Crash Hot Potatoes with Smoked Salmon")), withChild(withText("Written by Coco Morante"))))))));
        onView(withId(R.id.recipeList)).check(matches(withChild(withChild(withChild(allOf(withChild(withText("Rice Cake with Dulce de Leche and Dark Chocolate")), withChild(withText("Written by Micah Siva"))))))));

        onView(withText("Brazilian Cheese Bread (Pão de Queijo)")).perform(scrollTo(), click());
        onView(withId(R.id.recipeTitle)).check(matches(isDisplayed())).check(matches(withText("Brazilian Cheese Bread (Pão de Queijo)")));
    }
}
