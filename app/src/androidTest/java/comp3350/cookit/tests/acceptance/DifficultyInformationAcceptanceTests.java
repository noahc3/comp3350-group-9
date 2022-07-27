package comp3350.cookit.tests.acceptance;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

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
public class DifficultyInformationAcceptanceTests {

    @Rule
    public ActivityScenarioRule<HomeListActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(HomeListActivity.class);

    @Test
    public void testDifficultyInformationDisplay() {
        onView(withText("Philly Cheesesteak Sloppy Joes")).perform(scrollTo(), click());
        onView(withId(R.id.recipeTitle)).check(matches(isDisplayed())).check(matches(withText("Philly Cheesesteak Sloppy Joes")));
        onView(withId(R.id.difficulty_display)).check(matches(withText("Difficulty: Easy")));
        onView(withId(R.id.prep_time_display)).check(matches(withText("Prep Time: 10 minutes")));
        onView(withId(R.id.cook_time_display)).check(matches(withText("Cook Time: 30 minutes")));

        Espresso.pressBack();

        onView(withText("Shrimp Ceviche")).perform(scrollTo(), click());
        onView(withId(R.id.recipeTitle)).check(matches(isDisplayed())).check(matches(withText("Shrimp Ceviche")));
        onView(withId(R.id.difficulty_display)).check(matches(withText("Difficulty: Moderate")));
        onView(withId(R.id.prep_time_display)).check(matches(withText("Prep Time: 30 minutes")));
        onView(withId(R.id.cook_time_display)).check(matches(withText("Cook Time: 10 minutes")));
    }
}
