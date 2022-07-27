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
public class ViewRecipesAcceptanceTests {

    @Rule
    public ActivityScenarioRule<HomeListActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(HomeListActivity.class);

    @Test
    public void testRecipeDisplay() {
        onView(withText("Sesame Peanut Noodles")).perform(scrollTo(), click());
        onView(withId(R.id.recipeTitle)).check(matches(isDisplayed())).check(matches(withText("Sesame Peanut Noodles")));
        onView(withId(R.id.recipeAuthor)).check(matches(isDisplayed())).check(matches(withText("Written by Hannah Zimmerman")));
        onView(withId(R.id.recipeInstructions)).check(matches(withText("1. Cook the noodles:\nBring a large pot of water to a boil over high heat. Add the noodles and cook following package instructions. Drain into a colander set in the sink, then rinse with cold running water until cool to the touch. Drain well.\n\nTransfer into a medium bowl and toss them with 1 tablespoon sesame oil so that they don’t stick to each other. Cover with plastic wrap and place them in the fridge to keep cool while you prepare the sauce.\n\n2. Prepare the sauce:\nIn a small bowl, add the remaining 1 tablespoon sesame oil, peanut butter, soy sauce, honey, rice vinegar, and garlic. Whisk with a fork until combined and smooth.\n\n3. Assemble the noodles:\nScrape the peanut sesame sauce on the cold noodles and toss to combine. Garnish with sesame seeds and green onions, if desired.\n\nLeftovers can be stored in an airtight container in the fridge for up to 3 days. Before serving them again, toss with a little warm water to thin out the sauce.")));
        onView(withId(R.id.ingredientList)).check(matches(hasChildCount(9)));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("18 oz soba noodles"))));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("2 tbsp toasted sesame oil"))));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("3 tbsp natural peanut butter"))));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("1/4 cups soy sauce"))));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("1 1/2 tbsp honey"))));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("1 1/2 tbsp rice vinegar"))));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("1 pc garlic clove, grated"))));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("1 tbsp toasted sesame seeds (optional)"))));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("1 green onion, sliced"))));

        Espresso.pressBack();

        onView(withText("Bisquick Apple Coffee Cake")).perform(scrollTo(), click());
        onView(withId(R.id.recipeTitle)).check(matches(isDisplayed())).check(matches(withText("Bisquick Apple Coffee Cake")));
        onView(withId(R.id.recipeAuthor)).check(matches(isDisplayed())).check(matches(withText("Written by Elise Bauer")));
        onView(withId(R.id.recipeInstructions)).check(matches(withText("1. Preheat the oven to 400°F. Grease an 8-inch square pan or a 9-inch round pan.\n\n2. In a large bowl, mix together the 2 cups of baking mix, water or milk, sugar, and egg until just combined. Spread the batter in pan. Insert the apple slices into the batter evenly throughout the cake.\n\n3. In a medium bowl, combine the 1/3 cup of Bisquick mix with the brown sugar and cinnamon. Spread the topping mix over top of the batter in the pan. Place slices of the butter all over the top.\n\n4. Bake 20 minutes at 400°F, or until golden brown, and a tester inserted in the middle comes out clean.")));
        onView(withId(R.id.ingredientList)).check(matches(hasChildCount(9)));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("2 cups baking mix, such as Bisquick"))));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("2/3 cups milk or water"))));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("2 tbsp sugar"))));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("1 large egg"))));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("1 tart green apple, cored, peeled, sliced"))));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("1/3 cups baking mix, such as Bisquick"))));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("1/3 cups packed brown sugar"))));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("1/2 tsp ground cinnamon"))));
        onView(withId(R.id.ingredientList)).check(matches(withChild(withText("4 tbsp butter, thinly sliced"))));
    }
}
