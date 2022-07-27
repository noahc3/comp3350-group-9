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
public class ServingSizeConversionsAcceptanceTests {

    @Rule
    public ActivityScenarioRule<HomeListActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(HomeListActivity.class);

    @Test
    public void testServingSizeConversions() {
        onView(withText("Brazilian Cheese Bread (Pão de Queijo)")).perform(scrollTo(), click());
        onView(withId(R.id.recipeTitle)).check(matches(isDisplayed())).check(matches(withText("Brazilian Cheese Bread (Pão de Queijo)")));
        onView(withId(R.id.ingredientList)).check(matches(hasChildCount(6)));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("1 large egg"))));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("1/3 cups extra virgin olive oil"))));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("2/3 cups milk"))));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("1 1/2 cups tapioca flour"))));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("1/2 cups grated or crumbled cheese"))));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("1 tsp salt"))));

        onView(withId(R.id.servingsDropdown)).perform(click());
        onView(withText("2x")).perform(click());

        onView(withId(R.id.ingredientList)).check(matches(hasChildCount(6)));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("2 large egg"))));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("2/3 cups extra virgin olive oil"))));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("1 1/3 cups milk"))));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("3 cups tapioca flour"))));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("1 cups grated or crumbled cheese"))));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("2 tsp salt"))));

        onView(withId(R.id.servingsDropdown)).perform(click());
        onView(withText("4x")).perform(click());

        onView(withId(R.id.ingredientList)).check(matches(hasChildCount(6)));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("4 large egg"))));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("1 1/3 cups extra virgin olive oil"))));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("2 2/3 cups milk"))));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("6 cups tapioca flour"))));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("2 cups grated or crumbled cheese"))));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("4 tsp salt"))));

        onView(withId(R.id.servingsDropdown)).perform(click());
        onView(withText("1x")).perform(click());

        onView(withId(R.id.ingredientList)).check(matches(hasChildCount(6)));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("1 large egg"))));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("1/3 cups extra virgin olive oil"))));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("2/3 cups milk"))));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("1 1/2 cups tapioca flour"))));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("1/2 cups grated or crumbled cheese"))));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("1 tsp salt"))));
    }

}
