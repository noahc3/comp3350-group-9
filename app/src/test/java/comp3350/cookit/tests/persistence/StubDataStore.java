package comp3350.cookit.tests.persistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import comp3350.cookit.objects.Author;
import comp3350.cookit.objects.Ingredient;
import comp3350.cookit.objects.IngredientList;
import comp3350.cookit.objects.Recipe;
import comp3350.cookit.objects.Review;
import comp3350.cookit.persistence.IDataStore;

public class StubDataStore implements IDataStore {

    private List<Author> authors;
    private List<Recipe> recipes;
    private List<Review> reviews;

    public StubDataStore() {
    }

    @Override
    public void open(String dbPath) {
        authors = new ArrayList<>(Arrays.asList(
                new Author("0", "bobpiazza", "I love making muffins. Find me on allrecipes: https://www.allrecipes.com/cook/2955506"),
                new Author("1", "Myrna", "Find me on allrecipes: https://www.allrecipes.com/cook/2792648?content=recipes"),
                new Author("2", "Hannah Zimmerman", "Hannah has been working as a food photographer and recipe developer since 2018, and has worked with brands & publications across the U.S. to create mouth-watering food content. She began contributing to Simply Recipes in 2021. See them on Simply Recipes: https://www.simplyrecipes.com/hannah-zimmerman-5195688"),
                new Author("3", "Elise Bauer", "Elise Bauer is the founder of SimplyRecipes.com. A blogging pioneer, Elise first created Simply Recipes in 2003 as a way to document her family’s recipes, and over the years grew it into one of the most popular cooking websites in the world. Finer them on Simply Recipes: https://www.simplyrecipes.com/elise-bauer-5091824"),
                new Author("4", "Sara Bir", "Sara began contributing to Simply Recipes in 2018. She's been writing recipes since 2000, teaching cooking classes since 2008, and giving foraging presentations since 2015. She was the founding food editor for the pop culture Paste Magazine. Find them on Simply Recipes: https://www.simplyrecipes.com/sara-bir-5091784"),
                new Author("5", "Shilpa Uskokovic", "Shilpa Uskokovic is a food editor and recipe developer based in NYC. She was previously a line and pastry cook in some of NYC's top rated restaurants like Marea, The NoMad Hotel, Maialino and Perry Street. A graduate of The Culinary Institute of America, Shilpa loves reading, grocery shopping, and eating a little too much cake . She was born and raised in Chennai, India. Find them on Simply Recipes: https://www.simplyrecipes.com/shilpa-uskokovic-5219561"),
                new Author("6", "Ariane Resnick", "Ariane is the author of The Bone Broth Miracle (2015), The Thinking Girl's Guide to Drinking (2016), Wake/Sleep (2018), How to Be Well When You're Not (foreword by P!nk, 2018), and Disney Princess Healthy Treats (2021). She has been a contributor since 2020 for Simply Recipes but began professional writing 15 years ago for Provincetown, Massachusetts newspaper and Curve Magazine. Find them on Simply Recipes: https://www.simplyrecipes.com/ariane-resnick-5091819"),
                new Author("7", "Nick Evans", "Nick has been creating recipes for home cooks for almost 12 years. He live in Denver, CO with his wife and two kids. Nick published a nationally released cookbook 'Love Your Leftovers' and has been writing for Simply Recipes since 2017. Find them on Simply Recipes: https://www.simplyrecipes.com/nick-evans-5091785"),
                new Author("8", "Georgia Freedman", "Georgia Freedman is a food writer and editor based in Oakland, California. Formerly the managing editor of Saveur, Georgia has written for a variety of food- and travel-focused publications including Food & Wine, The Wall Street Journal, Afar, Martha Stewart Living, the San Francisco Chronicle, and Serious Eats. Find them on Simply Recipes: https://www.simplyrecipes.com/georgia-freedman-5184047"),
                new Author("9", "Coco Morante", "Coco started a food blog in 2011, and soon after that began managing social media and developing recipes for Garlic Gold, an organic foods company. She began developing recipes professionally for The Kitchn in 2014, joined Simply Recipes as a contributor in 2016, and published her first cookbook, The Essential Instant Pot Cookbook, with Ten Speed Press in 2017. Coco has written five cookbooks in total, with a new project in the works presently. Find them on Simply Recipes: https://www.simplyrecipes.com/coco-morante-5091788"),
                new Author("10", "Micah Siva", "Micah Siva is a trained chef, registered dietitian, recipe writer, and food photographer, specializing in modern Jewish cuisine. Find them on Simply Recipes: https://www.simplyrecipes.com/micah-siva-5270458")
        ));

        recipes = new ArrayList<>(Arrays.asList(
                new Recipe(
                        "0",
                        "Lemon Cranberry Muffins",
                        "0",
                        "1. Preheat oven to 400F. Grease 12 muffin cups, or line with paper muffin liners.\n\n2. Combine flour, sugar, baking powder, and salt in a large bowl. Mix lemon juice and milk in a measuring cup, to sour milk; beat eggs, oil, and milk mixture in a bowl. Stir egg mixture into flour mixture until just moistened; fold in cranberries. Fill prepared muffin cups two-thirds full; sprinkle with almonds.\n\n3. Bake in preheated oven until a toothpick inserted into a muffin comes out clean, 18 to 20 minutes. Cool for 5 minutes before removing from pan to wire rack.",
                        IngredientList.Create(
                                new Ingredient("all-purpose flour", 2, "cups"),
                                new Ingredient("white sugar", 1.25, "cups"),
                                new Ingredient("baking powder", 0.5, "tbsp"),
                                new Ingredient("salt", 0.5, "tsp"),
                                new Ingredient("lemon juice", 0.25, "cups"),
                                new Ingredient("milk", 0.75, "cups"),
                                new Ingredient("eggs", 2, "whole"),
                                new Ingredient("vegetable oil", 0.5, "cups"),
                                new Ingredient("cranberries, halved", 1, "cups"),
                                new Ingredient("toasted slivered almonds", 0.33, "cups")
                        ),
                        12,
                        Arrays.asList("Pastry", "Sweet", "Snack", "All Day"),
                        30,
                        20,
                        "Medium",
                        Arrays.asList("muffin0", "muffin1")
                ),
                new Recipe(
                        "1",
                        "Honey-Garlic Slow Cooker Chicken Thighs",
                        "1",
                        "1. Lay chicken thighs into the bottom of a 4-quart slow cooker.\n\n2. Whisk soy sauce, ketchup, honey, garlic, and basil together in a bowl; pour over the chicken.\n\n3. Cook on Low for 6 hours.",
                        IngredientList.Create(
                                new Ingredient("boneless, skinless chicken thighs", 4, "whole"),
                                new Ingredient("soy sauce", 0.5, "cups"),
                                new Ingredient("ketchup", 0.5, "cups"),
                                new Ingredient("honey", 0.33, "cups"),
                                new Ingredient("garlic, minced", 3, "cloves"),
                                new Ingredient("dried basil", 1, "tsp")
                        ),
                        4,
                        Arrays.asList("Culinary", "Savory", "Entree", "Dinner"),
                        30,
                        360,
                        "Easy",
                        Arrays.asList("chicken0", "chicken1")
                ),
                new Recipe(
                        "2",
                        "Sesame Peanut Noodles",
                        "2",
                        "1. Cook the noodles:\nBring a large pot of water to a boil over high heat. Add the noodles and cook following package instructions. Drain into a colander set in the sink, then rinse with cold running water until cool to the touch. Drain well.\n\nTransfer into a medium bowl and toss them with 1 tablespoon sesame oil so that they don’t stick to each other. Cover with plastic wrap and place them in the fridge to keep cool while you prepare the sauce.\n\n2. Prepare the sauce:\nIn a small bowl, add the remaining 1 tablespoon sesame oil, peanut butter, soy sauce, honey, rice vinegar, and garlic. Whisk with a fork until combined and smooth.\n\n3. Assemble the noodles:\nScrape the peanut sesame sauce on the cold noodles and toss to combine. Garnish with sesame seeds and green onions, if desired.\n\nLeftovers can be stored in an airtight container in the fridge for up to 3 days. Before serving them again, toss with a little warm water to thin out the sauce.",
                        IngredientList.Create(
                                new Ingredient("soba noodles", 18.0, "oz"),
                                new Ingredient("toasted sesame oil", 2.0, "tbsp"),
                                new Ingredient("natural peanut butter", 3, "tbsp"),
                                new Ingredient("soy sauce", 0.25, "cups"),
                                new Ingredient("honey", 1.5, "tbsp"),
                                new Ingredient("rice vinegar", 1.5, "tbsp"),
                                new Ingredient("garlic clove, grated", 1.0, "pc"),
                                new Ingredient("toasted sesame seeds (optional)", 1, "tbsp"),
                                new Ingredient("green onion, sliced", 1, "")
                        ),
                        4,
                        Arrays.asList("Culinary", "Savory", "Entree", "Lunch"),
                        10,
                        10,
                        "Easy",
                        Arrays.asList("sesame-peanut-noodles-1", "sesame-peanut-noodles-2", "sesame-peanut-noodles-3")
                ),
                new Recipe(
                        "3",
                        "Grandma's Oatmeal Cookies",
                        "3",
                        "1. Preheat the oven to 350F.\n\n2. Beat together the shortening, brown sugar, and white sugar. Add the eggs and vanilla extract, and beat well.\n\n3. Whisk together the flour, salt, baking soda, and cinnamon. Add to sugar and egg mixture and mix well. Add a tablespoon of water. Add raisins (if using) and nuts. Add oats last.\n\n4. Spoon out by heaping tablespoonfuls on to greased cookie sheets. Bake at 350°F. Bake for 10 minutes. Remove to a wire rack.\n\nCookies will keep for several days in an airtight container on the counter.",
                        IngredientList.Create(
                                new Ingredient("shortening", 1.0, "cups"),
                                new Ingredient("brown sugar", 1.0, "cups"),
                                new Ingredient("white sugar", 1.0, "cups"),
                                new Ingredient("large eggs", 2.0, ""),
                                new Ingredient("vanilla extract", 1.0, "tbsp"),
                                new Ingredient("all-purpose flour", 1.5, "cups"),
                                new Ingredient("kosher salt", 1.0, "tsp"),
                                new Ingredient("baking soda", 1.0, "tsp"),
                                new Ingredient("cinnamon", 1.0, "tbsp"),
                                new Ingredient("water", 1.0, "tbsp"),
                                new Ingredient("raisins", 1.0, "cups"),
                                new Ingredient("rolled oats", 3.0, "cups")
                        ),
                        24,
                        Arrays.asList("Pastry", "Sweet", "Desert", "All Day"),
                        20,
                        10,
                        "Moderate",
                        Arrays.asList("oatmeal-cookies-1", "oatmeal-cookies-2", "oatmeal-cookies-3")
                ),
                new Recipe(
                        "4",
                        "Spinach Tofu Scramble",
                        "4",
                        "1. Heat a medium skillet over medium-high heat. Add oil; when it ripples, add the onion. Cook, stirring occasionally, until the onion is soft, 3 to 5 minutes.\n\n2. Use your fingers to crumble the drained tofu into bite-sized pieces. You can do this directly over the skillet.\n\n3. Add the nutritional yeast, salt, pepper, and turmeric, and stir to combine. Cook, stirring occasionally, until the tofu is hot, about 3 minutes. You’re not aiming to brown the tofu here, but if that happens a little, it’s not an issue.\n\n4. Add the spinach and cook until wilted, 1 minute. Sprinkle the lemon juice over the scramble. Taste and adjust the seasoning with salt and pepper, if needed.\n\nLeftover tofu scramble will keep in a tightly covered container up to four days. I don’t recommend freezing it.",
                        IngredientList.Create(
                                new Ingredient("extra virgin olive oil", 1.0, "tbsp"),
                                new Ingredient("yellow onion, diced", 0.5, "cups"),
                                new Ingredient("firm packed tofu, drained", 1.0, "lb"),
                                new Ingredient("nutritional yeast", 1.0, "tsp"),
                                new Ingredient("kosher salt", 0.5, "tsp"),
                                new Ingredient("ground black pepper", 0.25, "tsp"),
                                new Ingredient("ground turmeric", 0.25, "tsp"),
                                new Ingredient("loosely packed fresh spinach leaves", 4.0, "cups"),
                                new Ingredient("lemon juice", 0.25, "tsp")
                        ),
                        3,
                        Arrays.asList("Culinary", "Savory", "Entree", "Breakfast"),
                        7,
                        8,
                        "Easy",
                        Arrays.asList("tofu-scramble-1", "tofu-scramble-2", "tofu-scramble-3")
                ),
                new Recipe(
                        "5",
                        "Shrimp Ceviche",
                        "3",
                        "1. In a large pot, bring 2 quarts of water to a boil, salted with 1 tablespoon of salt. Add the shrimp and cook for 1 minute to 2 minutes max, depending on size of shrimp. (Over-cooking the shrimp will turn it rubbery.)\n\n2. Remove shrimp with a slotted spoon and place into a bowl of ice water to stop the cooking.\n\n3. Drain the shrimp. Cut each piece of shrimp in half, or into inch-long pieces.\n\n4. Place the shrimp in a glass or ceramic bowl. Mix in the lime and lemon juice. Cover and refrigerate for a half hour.\n\n5. Mix in the chopped red onion and serrano chile. Refrigerate an additional half hour.\n\n6. Right before serving, add the cilantro, cucumber, and avocado. Serve chilled with tortilla chips.",
                        IngredientList.Create(
                                new Ingredient("kosher salt", 1.0, "tbsp"),
                                new Ingredient("medium or small shrimp", 1.0, "lb"),
                                new Ingredient("lime juice", 0.75, "cups"),
                                new Ingredient("lemon juice", 0.75, "cups"),
                                new Ingredient("red onion, finely chopped", 1.0, "cups"),
                                new Ingredient("serrano chili, ribs and seeds removed", 1.0, ""),
                                new Ingredient("cilantro, chopped", 1.0, "cups"),
                                new Ingredient("cucumber, peeled and diced", 1.0, ""),
                                new Ingredient("avocado, diced", 1.0, "")
                        ),
                        6,
                        Arrays.asList("Culinary", "Savory", "Appetizer", "Lunch"),
                        30,
                        10,
                        "Moderate",
                        Arrays.asList("shrimp-ceviche-1", "shrimp-ceviche-2", "shrimp-ceviche-3")
                ),
                new Recipe(
                        "6",
                        "Brazilian Cheese Bread (Pão de Queijo)",
                        "3",
                        "1. Preheat the oven to 400°F. Spread a small amount olive oil around the insides of each well of a non-stick mini muffin tin.\n\n2. Put all of the ingredients into a blender and pulse until smooth. You may need to use a spatula to scrape down the sides of the blender so that everything gets blended well.\n\n3. Pour the batter into the prepared mini muffin tin not quite to the top; leave about 1/8 inch from the top.\n\n4. Bake at 400°F in the oven for 15-20 minutes until all puffy and nicely browned. Remove from the oven and let cool on a rack for a few minutes.\n\nEat while warm or at room temperature (they’re best when fresh). Note that Brazilian cheese bread is very chewy, a lot like Japanese mochi.\n\nStore leftovers in an airtight container at room temperature for 1 day or in the fridge for 1 week. Reheat for several seconds in the microwave or for a 1 to 2 minutes in the toaster oven before serving. They can also be frozen for up to 1 month and reheated in a toaster oven or low oven until thawed and crisp.",
                        IngredientList.Create(
                                new Ingredient("large egg", 1.0, ""),
                                new Ingredient("extra virgin olive oil", 0.33, "cups"),
                                new Ingredient("milk", 0.66, "cups"),
                                new Ingredient("tapioca flour", 1.5, "cups"),
                                new Ingredient("grated or crumbled cheese", 0.5, "cups"),
                                new Ingredient("salt", 1.0, "tsp")
                        ),
                        8,
                        Arrays.asList("Pastry", "Savory", "Appetizer", "Dinner"),
                        5,
                        15,
                        "Easy",
                        Arrays.asList("cheese-bread-1", "cheese-bread-2", "cheese-bread-3")
                ),
                new Recipe(
                        "7",
                        "Sweet and Salty Three-Seed Granola",
                        "5",
                        "1. Position a rack in the center of the oven. Preheat it to 325°F. Line a rimmed baking sheet with parchment paper. Set it aside.\n\n2. In a large bowl, vigorously whisk the maple syrup, brown sugar, olive oil, vanilla, cinnamon (if using), and salt until the oil is emulsified and mixture is smooth, about 2 minutes.\n\n3. Using a large, sturdy spatula or your clean hands, fold in the oats, sunflower seeds, pumpkin seeds, and sesame seeds. Mix until combined well and the dry ingredients are evenly coated.\n\n4. Transfer the oat mixture to the prepared baking sheet. Using the back of a measuring cup, firmly press oats down into a thin, even layer.\n\n5. Bake until evenly light golden brown from edge to edge, 40 to 45 minutes, rotating the baking sheet halfway through. If you like crumbly granola, stir it with a wooden spoon or spatula every 15 minutes.\n\n6. Remove the baking sheet from the oven and let it cool fully, at least 1 hour.\n\n7. Break up the fully cooled granola into bite-sized pieces using your hands. Stir in the dried fruit of your choice along with the flaky salt. ",
                        IngredientList.Create(
                                new Ingredient("maple syrup", 0.5, "cups"),
                                new Ingredient("light brown sugar, packed", 0.5, "cups"),
                                new Ingredient("extra virgin olive oil", 0.5, "cups"),
                                new Ingredient("vanilla extract", 1, "tbsp"),
                                new Ingredient("ground cinnamon (optional)", 1, "tsp"),
                                new Ingredient("kosher salt", 1, "tsp"),
                                new Ingredient("old-fashioned rolled oats", 4, "cups"),
                                new Ingredient("shelled raw sunflower seeds", 1, "cups"),
                                new Ingredient("shelled raw pumpkin seeds", 1, "cups"),
                                new Ingredient("raw sesame seeds", 0.25, "cups"),
                                new Ingredient("dried fruit such as raisins, cranberries, or currants", 1, "cups"),
                                new Ingredient("flaky sea salt", 1, "tsp"),
                                new Ingredient("flaky salt", 3.67, "cups")
                        ),
                        12,
                        Arrays.asList("Culinary", "Sweet", "Snack", "Breakfast"),
                        10,
                        45,
                        "Moderate",
                        Arrays.asList("sweet-salty-granola-1", "sweet-salty-granola-2", "sweet-salty-granola-3")
                ),
                new Recipe(
                        "8",
                        "Bisquick Apple Coffee Cake",
                        "3",
                        "1. Preheat the oven to 400°F. Grease an 8-inch square pan or a 9-inch round pan.\n\n2. In a large bowl, mix together the 2 cups of baking mix, water or milk, sugar, and egg until just combined. Spread the batter in pan. Insert the apple slices into the batter evenly throughout the cake.\n\n3. In a medium bowl, combine the 1/3 cup of Bisquick mix with the brown sugar and cinnamon. Spread the topping mix over top of the batter in the pan. Place slices of the butter all over the top.\n\n4. Bake 20 minutes at 400°F, or until golden brown, and a tester inserted in the middle comes out clean.",
                        IngredientList.Create(
                                new Ingredient("baking mix, such as Bisquick", 2, "cups"),
                                new Ingredient("milk or water", 0.67, "cups"),
                                new Ingredient("sugar", 2, "tbsp"),
                                new Ingredient("large egg", 1, ""),
                                new Ingredient("tart green apple, cored, peeled, sliced", 1, ""),
                                new Ingredient("baking mix, such as Bisquick", 0.33, "cups"),
                                new Ingredient("packed brown sugar", 0.33, "cups"),
                                new Ingredient("ground cinnamon", 0.5, "tsp"),
                                new Ingredient("butter, thinly sliced", 4, "tbsp")
                        ),
                        8,
                        Arrays.asList("Pastry", "Sweet", "Desert", "All Day"),
                        5,
                        20,
                        "Easy",
                        Arrays.asList("bisquick-1", "bisquick-2")
                ),
                new Recipe(
                        "9",
                        "Baked Oatmeal with Mixed Berries",
                        "6",
                        "1. Preheat your oven to 375°F. Grease with butter or lined a 9x9 baking dish with parchment.\n\n2. In a large mixing bowl, add oats, milk, eggs, melted butter, honey, yogurt, vanilla, and salt. Stir with a fork or whisk to combine thoroughly.\n\n3. Fold 3/4 cup each of blueberries and raspberries into the mixture. Pour mixture into prepared casserole dish. Sprinkle all remaining berries over top.\n\n4. Bake the oatmeal casserole for 45 minutes, or until the casserole is shiny on top and firm to the touch.\n\n",
                        IngredientList.Create(
                                new Ingredient("rolled oats", 2, "cups"),
                                new Ingredient("milk", 0.5, "cups"),
                                new Ingredient("eggs, beaten", 3, ""),
                                new Ingredient("melted butter or olive oil", 2, "tbsp"),
                                new Ingredient("honey", 0.33, "cups"),
                                new Ingredient("whole-fat plain yogurt", 0.5, "cups"),
                                new Ingredient("vanilla extract", 0.5, "tsp"),
                                new Ingredient("salt", 0.75, "tsp"),
                                new Ingredient("blueberries, divided", 1, "cups"),
                                new Ingredient("raspberries, divided", 1, "cups")
                        ),
                        6,
                        Arrays.asList("Pastry", "Sweet", "Snack", "Breakfast"),
                        10,
                        45,
                        "Moderate",
                        Arrays.asList("oatmeal-berries-1", "oatmeal-berries-2", "oatmeal-berries-3")
                ),
                new Recipe(
                        "10",
                        "Philly Cheesesteak Sloppy Joes",
                        "7",
                        "1. In a large skillet set over medium heat, melt the butter. Add the onions and bell peppers. Cook until soften, 6 to 7 minutes, stirring frequently. The vegetables shouldn’t brown at this point, just soften.\n\n2. Add the ground beef and use a wooden spoon to break it up into small pieces as it cooks. Cook until the meat is browned, 5 to 6 minutes.\n\n3. Stir in the stock, ketchup, Worcestershire sauce, garlic powder, salt, and black pepper. Turn the heat down to low and simmer for 4 to 5 minutes until the mixture thickens.\n\n4. Stack 6 slices provolone cheese on a cutting board and use a chef’s knife to cut them into 1/2-inch pieces. Right before serving, stir in the cut cheese.\n\n5. Toast the brioche buns in a toaster oven or toaster. Lay the remaining 6 slices provolone cheese on each brioche bun half. Top the cheese with heaping spoonfuls of the sloppy joe mixture and the top bun. Serve warm.",
                        IngredientList.Create(
                                new Ingredient("unsalted butter", 2, "tbsp"),
                                new Ingredient("small white onion, chopped", 1, ""),
                                new Ingredient("green bell pepper, seeded and chopped", 1, ""),
                                new Ingredient("lean beef, 90/10 blend", 1, "lb"),
                                new Ingredient("beef stock", 0.5, "cups"),
                                new Ingredient("ketchup", 0.25, "cups"),
                                new Ingredient("Worcestershire sauce", 2, "tbsp"),
                                new Ingredient("garlic powder", 0.5, "tsp"),
                                new Ingredient("kosher salt", 0.5, "tsp"),
                                new Ingredient("black pepper", 0.5, "tsp"),
                                new Ingredient("slices provolone cheese, divided", 12, ""),
                                new Ingredient("brioche buns, store-bought or homemade", 6, "")
                        ),
                        6,
                        Arrays.asList("Culinary", "Savory", "Entree", "Lunch"),
                        10,
                        30,
                        "Easy",
                        Arrays.asList("philly-sloppy-joes-1", "philly-sloppy-joes-2", "philly-sloppy-joes-3")
                ),
                new Recipe(
                        "11",
                        "Corn and Ricotta Bruschetta",
                        "8",
                        "1. With a sharp knife, cut the corn off of the cobs into a medium bowl, removing as much of the kernels flesh as possible. \n\n2. The method I use: hold your shucked corn cob vertically inside a medium bowl, holding the stem and letting the tip of the cob sit in the bottom of the bowl. Use a chef’s knife to cut the kernels off from top (the end you’re holding) to bottom (the tip in the bowl), in a downward motion, rotating the cob to get all the sides.  \n\n3. Set aside. You should have 2 1/4 cups of shucked corn.\n\n4. In a medium pan, melt 1 tablespoon of butter over medium heat. Add the onion, and cook, stirring frequently, until it begins to soften, about 2 minutes.\n\n5. Add the corn and the remaining 1 tablespoon of butter, and cook, until the corn is softened, about 5 minutes.\n\n6. Season with the salt and pepper. Add the mint, and cook, stirring, until the mint just starts to soften and becomes fragrant, about 30 second.\n\n7. Remove the pan from the heat, and set aside to cool for about 10 minutes, until the corn is warm but not hot.\n\n8. While the corn is cooling, toast the bread in your toaster. (If the bread is tender and thick, you may need to toast each slice twice to get it lightly crispy on its surface. Set aside.\n\n9. When the corn is done cooling, spoon even portions of ricotta onto each slice of toast (about 3 1/2 tablespoons on each slice) and use the back of the spoon to spread it out almost to the edges.\n\n10. Divide the corn mixture between the toasts (about 3 tablespoons per slice) and spread it out almost to the edge of the ricotta.\n\n11. Garnish with mint and serve.",
                        IngredientList.Create(
                                new Ingredient("ears fresh sweet corn, shucked", 2, ""),
                                new Ingredient("unsalted butter, divided", 2, "tbsp"),
                                new Ingredient("red onion, small dice", 0.5, ""),
                                new Ingredient("kosher salt", 0.5, "tsp"),
                                new Ingredient("freshly ground black pepper", 0.25, "tsp"),
                                new Ingredient("mint leaves, very thinly sliced", 8, ""),
                                new Ingredient("thick slices whole-grain batard or boule bread (cut about 4 inches wide, 5 inches long, and 1 inch thick)", 8, ""),
                                new Ingredient("ricotta cheese", 16, "oz")
                        ),
                        4,
                        Arrays.asList("Culinary", "Savory", "Entree", "Dinner"),
                        5,
                        15,
                        "Easy",
                        Arrays.asList("bruschetta-1", "bruschetta-2", "bruschetta-3", "bruschetta-4")
                ),
                new Recipe(
                        "12",
                        "Crash Hot Potatoes with Smoked Salmon",
                        "9",
                        "1. In a medium pot, add the potatoes and enough water to cover them by at least 1 inch. Place the pot on the stovetop over high heat.\n\n2. When the water comes to a boil, reduce the heat to medium and cook uncovered for about 15 to 20 minutes until you can easily pierce the potatoes with a fork or paring knife.\n\n3. Remove the potatoes from the heat and drain them into a colander over the sink.\n\n4. Preheat the oven to 425ºF. Line a large baking sheet with parchment.\n\n5. While the potatoes are still hot, carefully place them on the prepared baking sheet, and use the bottom of a drinking glass, measuring cup, or other flat-bottomed object to smash them to about 1/2-inch thickness. Don’t smash the potato down so much that it separates completely, you’re looking to create approximately 2-inch rounds, which will act as a base for the toppings. If any potatoes do come apart, use your hands to smush them back together.\n\n6. Drizzle the smashed potatoes with 1 tablespoon olive oil, and sprinkle with 1/4 teaspoon salt and 1/8 teaspoon black pepper.\n\n7. Use a spatula to gently turn the potatoes over and season them with the remaining olive oil, salt, and black pepper.\n\n8. Bake the potatoes for 20 to 25 minutes until they’re crisp and golden brown around the edges.\n\n9. Let the potatoes cool for about 15 minutes on the baking sheet.\n\n10. Top each potato with a heaping teaspoon of whipped cream cheese. Top the cream cheese with a small piece of smoked salmon, a sprinkle of red onion, and a few capers.\n\n11. Transfer the potatoes to a serving platter. Serve right away.",
                        IngredientList.Create(
                                new Ingredient("petite gold potatoes", 1.5, "lb"),
                                new Ingredient("olive oil", 2, "tbsp"),
                                new Ingredient("salt", 0.5, "tsp"),
                                new Ingredient("freshly ground black pepper", 0.25, "tsp"),
                                new Ingredient("whipped cream cheese", 6, "oz"),
                                new Ingredient("hot- or cold-smoked salmon", 5, "oz"),
                                new Ingredient("red onion, finely diced", 0.25, "cups"),
                                new Ingredient("capers, drained", 2, "tbsp")
                        ),
                        8,
                        Arrays.asList("Culinary", "Savory", "Appetizer", "Dinner"),
                        15,
                        45,
                        "Challenging",
                        Arrays.asList("crash-hot-potatoes-1", "crash-hot-potatoes-2", "crash-hot-potatoes-3")
                ),
                new Recipe(
                        "13",
                        "Rice Cake with Dulce de Leche and Dark Chocolate",
                        "10",
                        "1. Line a small baking sheet with parchment or wax paper. Spread about 2 tablespoons dulce de leche on each rice cake, leaving a 1/4-inch border around the outer edges. Place them on the baking sheet and sprinkle each with a tiny pinch of salt.\n\n2. Add the chocolate chips and coconut oil to a small microwave-safe bowl. Microwave in 30-second increments, stirring after each time, until fully melted.\n\n3. Dip each rice cake (only the side with the dulce de leche) in the melted chocolate. Return them onto the baking sheet and sprinkle with more salt, if you’d like.\n\n4. Pop the rice cakes in the freezer for about 10 minutes, until the chocolate hardens. \n\nThese treats are best eaten the day of, but you can make extra and keep them frozen for your future happy self! Just make sure to wrap them tightly with plastic wrap and stack them in a freezer-safe bag for up to 1 week in the freezer. \n\n",
                        IngredientList.Create(
                                new Ingredient("plain unsalted rice cakes", 4, ""),
                                new Ingredient("store-bought dulce de leche", 0.5, "cups"),
                                new Ingredient("flaky sea salt, plus more for garnish", 0.25, "tsp"),
                                new Ingredient("dark chocolate chips", 0.5, "cups"),
                                new Ingredient("coconut oil (any kind)", 1, "tbsp")
                        ),
                        4,
                        Arrays.asList("Pastry", "Sweet", "Desert", "All Day"),
                        10,
                        1,
                        "Easy",
                        Arrays.asList("rice-cakes-1", "rice-cakes-2", "rice-cakes-3")
                )

        ));

        reviews = new ArrayList<>(Arrays.asList(
                new Review("0", "0", "Neo Colwyn", "These muffins are really good!", 5),
                new Review("1", "0", "Padma Gauthier", "Should up the cranberry count a little bit, otherwise awesome!", 4),
                new Review("2", "1", "Lara Hanna", "Too much ketchup.", 2),
                new Review("3", "2", "Sheila M. Higgs-Coulthard", "I am restricted with my intake of starch, also various vegetables, this recipe meets all my restrictions yet leaving you happy and satisfied, thank you very much.", 5)
        ));

        System.out.println("Opened stub database.");
    }

    @Override
    public void close() {
        System.out.println("Closed stub database.");
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return new ArrayList<>(this.recipes);
    }

    @Override
    public List<Recipe> getRecipesWithTag(String tag) {
        List<Recipe> recipes = getAllRecipes();
        List<Recipe> taggedRecipes = new ArrayList<>();

        if (recipes != null) {
            for (Recipe r : recipes) {
                if (r.getTags().contains(tag)) {
                    taggedRecipes.add(r);
                }
            }
        }

        return taggedRecipes;
    }

    public Recipe getRecipeById(String id) {
        Recipe result = null;

        for (Recipe r : recipes) {
            if (r.getId().equals(id)) {
                result = r;
                break;
            }
        }

        return result;
    }

    @Override
    public void insertRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        if (recipe != null) {
            for (int i = 0; i < recipes.size(); i++) {
                if (recipes.get(i).getId().equals(recipe.getId())) {
                    recipes.set(i, recipe);
                    break;
                }
            }
        }
    }

    @Override
    public void deleteRecipe(Recipe recipe) {
        if (recipe != null) {
            for (int i = 0; i < recipes.size(); i++) {
                if (recipes.get(i).getId().equals(recipe.getId())) {
                    recipes.remove(i);
                    break;
                }
            }
        }
    }

    @Override
    public List<Author> getAllAuthors() {
        return new ArrayList<>(this.authors);
    }

    @Override
    public Author getAuthorById(String id) {
        Author result = null;

        for (Author a : authors) {
            if (a.getId().equals(id)) {
                result = a;
                break;
            }
        }

        return result;
    }

    @Override
    public void insertAuthor(Author author) {
        authors.add(author);
    }

    @Override
    public void updateAuthor(Author author) {
        if (author != null) {
            for (int i = 0; i < authors.size(); i++) {
                if (authors.get(i).getId().equals(author.getId())) {
                    authors.set(i, author);
                    break;
                }
            }
        }
    }

    @Override
    public void deleteAuthor(Author author) {
        if (author != null) {
            for (int i = 0; i < authors.size(); i++) {
                if (authors.get(i).getId().equals(author.getId())) {
                    authors.remove(i);
                    break;
                }
            }
        }
    }

    @Override
    public List<Review> getAllReviews() {
        List<Review> result = new ArrayList<>(this.reviews);
        Collections.reverse(result);
        return result;
    }

    @Override
    public List<Review> getReviewsForRecipe(String recipeId) {
        List<Review> allReviews = getAllReviews();
        List<Review> recipeReviews = new ArrayList<>();

        for (Review review : allReviews) {
            if (review.getRecipeId().equals(recipeId)) {
                recipeReviews.add(review);
            }
        }

        Collections.reverse(recipeReviews);

        return recipeReviews;
    }

    @Override
    public Review getReviewById(String id) {
        Review result = null;

        for (Review r : reviews) {
            if (r.getId().equals(id)) {
                result = r;
                break;
            }
        }

        return result;
    }

    @Override
    public void insertReview(Review review) {
        reviews.add(review);
    }

    @Override
    public void updateReview(Review review) {
        if (review != null) {
            for (int i = 0; i < reviews.size(); i++) {
                if (reviews.get(i).getId().equals(review.getId())) {
                    reviews.set(i, review);
                    break;
                }
            }
        }
    }

    @Override
    public void deleteReview(Review review) {
        if (review != null) {
            for (int i = 0; i < reviews.size(); i++) {
                if (reviews.get(i).getId().equals(review.getId())) {
                    reviews.remove(i);
                    break;
                }
            }
        }
    }


}
