package comp3350.cookit.tests.acceptance;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
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
public class TagsPageAcceptanceTests {

    @Rule
    public ActivityScenarioRule<HomeListActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(HomeListActivity.class);

    @Test
    public void testTagsPage() {
        onView(withId(R.id.seeAll)).perform(click());

        onView(withId(R.id.tagsLayout)).check(matches(withChild(withText("Time of Day"))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("Breakfast"))))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("Lunch"))))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("Dinner"))))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("Snack"))))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("All Day"))))));

        onView(withId(R.id.tagsLayout)).check(matches(withChild(withText("Type"))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("Pastry"))))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("Culinary"))))));

        onView(withId(R.id.tagsLayout)).check(matches(withChild(withText("Taste"))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("Sweet"))))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("Savory"))))));

        onView(withId(R.id.tagsLayout)).check(matches(withChild(withText("Course"))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("Snack"))))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("Appetizer"))))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("Entree"))))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("Dessert"))))));

        onView(withId(R.id.tagsLayout)).check(matches(withChild(withText("Diet"))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("Vegan"))))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("Vegetarian"))))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("Pescatarian"))))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("Mediterranean"))))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("Keto"))))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("Paleo"))))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("Gluten-Free"))))));

        onView(withId(R.id.tagsLayout)).check(matches(withChild(withText("Region"))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("Western"))))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("Middle Eastern"))))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("West Asian"))))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("East Asian"))))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("European"))))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("African"))))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("Caribbean"))))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("Fusion"))))));

        onView(withId(R.id.tagsLayout)).check(matches(withChild(withText("Occasion"))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("New Year's Eve"))))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("Easter"))))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("Halloween"))))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("Thanksgiving"))))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("Christmas"))))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("Cultural"))))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("Birthday"))))));

        onView(withId(R.id.tagsLayout)).check(matches(withChild(withText("Season"))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("Winter"))))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("Spring"))))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("Summer"))))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("Autumn/Fall"))))));
        onView(withId(R.id.tagsLayout)).check(matches(withChild(withChild(withChild(withText("All Year"))))));

        onView(withText("Dinner")).perform(scrollTo(), click());
        onView(withId(R.id.taggedListHeader)).check(matches(withText("Recipes tagged as 'Dinner'")));

        Espresso.pressBack();

        onView(withText("Birthday")).perform(scrollTo(), click());
        onView(withId(R.id.taggedListHeader)).check(matches(withText("Recipes tagged as 'Birthday'")));
    }
}
