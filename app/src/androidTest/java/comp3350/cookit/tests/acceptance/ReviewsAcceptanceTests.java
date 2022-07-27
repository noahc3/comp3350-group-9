package comp3350.cookit.tests.acceptance;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
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
public class ReviewsAcceptanceTests {

    @Rule
    public ActivityScenarioRule<HomeListActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(HomeListActivity.class);

    @Test
    public void testReadReviews() {
        onView(withText("Lemon Cranberry Muffins")).perform(scrollTo(), click());
        onView(withId(R.id.recipeTitle)).check(matches(isDisplayed())).check(matches(withText("Lemon Cranberry Muffins")));
        onView(withId(R.id.recipeRatingCount)).check(matches(withText("2 ratings")));
        onView(withId(R.id.recipeReviewsLayout)).check(matches(hasChildCount(2)));

        onView(withId(R.id.recipeReviewsLayout)).check(matches(withChild(withChild(withChild(withChild(withText("Padma Gauthier")))))));
        onView(withId(R.id.recipeReviewsLayout)).check(matches(withChild(withChild(withChild(withText("Should up the cranberry count a little bit, otherwise awesome!"))))));

        onView(withId(R.id.recipeReviewsLayout)).check(matches(withChild(withChild(withChild(withChild(withText("Neo Colwyn")))))));
        onView(withId(R.id.recipeReviewsLayout)).check(matches(withChild(withChild(withChild(withText("These muffins are really good!"))))));
    }

    @Test
    public void testSubmitValidReview() {
        onView(withText("Sesame Peanut Noodles")).perform(scrollTo(), click());
        onView(withId(R.id.recipeTitle)).check(matches(isDisplayed())).check(matches(withText("Sesame Peanut Noodles")));
        onView(withId(R.id.recipeRatingCount)).check(matches(withText("1 ratings")));
        onView(withId(R.id.recipeReviewsLayout)).check(matches(hasChildCount(1)));

        onView(withId(R.id.reviewMultiline)).perform(scrollTo(), click(), replaceText("A little sweet, too much peanut butter?"));
        onView(withId(R.id.reviewName)).perform(scrollTo(), click(), replaceText("Mark Scout"));
        onView(withId(R.id.reviewSubmit)).perform(scrollTo(), click());

        onView(withId(R.id.reviewMultiline)).check(matches(withText("")));
        onView(withId(R.id.reviewName)).check(matches(withText("")));

        onView(withId(R.id.recipeRatingCount)).check(matches(withText("2 ratings")));
        onView(withId(R.id.recipeReviewsLayout)).check(matches(hasChildCount(2)));

        onView(withId(R.id.recipeReviewsLayout)).check(matches(withChild(withChild(withChild(withChild(withText("Mark Scout")))))));
        onView(withId(R.id.recipeReviewsLayout)).check(matches(withChild(withChild(withChild(withText("A little sweet, too much peanut butter?"))))));
    }

    @Test
    public void testSubmitReviewWithValidationErrors() {
        onView(withText("Corn and Ricotta Bruschetta")).perform(scrollTo(), click());
        onView(withId(R.id.recipeTitle)).check(matches(isDisplayed())).check(matches(withText("Corn and Ricotta Bruschetta")));
        onView(withId(R.id.recipeRatingCount)).check(matches(withText("0 ratings")));
        onView(withId(R.id.recipeReviewsLayout)).check(matches(hasChildCount(1)));
        onView(withId(R.id.recipeReviewsLayout)).check(matches(withChild(withText("(No reviews have been submitted yet, you can be the first!)"))));

        onView(withId(R.id.reviewSubmit)).perform(scrollTo(), click());

        onView(withId(R.id.reviewMultiline)).check(matches(hasErrorText("Please include a comment with your review.")));
        onView(withId(R.id.reviewName)).check(matches(hasErrorText("Please include your name with your review.")));

        onView(withId(R.id.reviewMultiline)).perform(scrollTo(), click(), replaceText("Toast became a little soggy."));
        onView(withId(R.id.reviewSubmit)).perform(scrollTo(), click());

        onView(withId(R.id.reviewName)).check(matches(hasErrorText("Please include your name with your review.")));

        onView(withId(R.id.reviewName)).perform(scrollTo(), click(), replaceText("Light Yagami"));
        onView(withId(R.id.reviewSubmit)).perform(scrollTo(), click());

        onView(withId(R.id.reviewMultiline)).check(matches(withText("")));
        onView(withId(R.id.reviewName)).check(matches(withText("")));

        onView(withId(R.id.recipeRatingCount)).check(matches(withText("1 ratings")));
        onView(withId(R.id.recipeReviewsLayout)).check(matches(hasChildCount(1)));

        onView(withId(R.id.recipeReviewsLayout)).check(matches(withChild(withChild(withChild(withChild(withText("Light Yagami")))))));
        onView(withId(R.id.recipeReviewsLayout)).check(matches(withChild(withChild(withChild(withText("Toast became a little soggy."))))));
    }
}
