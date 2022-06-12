package comp3350.cookit.persistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import comp3350.cookit.application.Main;
import comp3350.cookit.objects.Author;
import comp3350.cookit.objects.Ingredient;
import comp3350.cookit.objects.IngredientList;
import comp3350.cookit.objects.Recipe;
import comp3350.cookit.objects.Student;
import comp3350.cookit.objects.Course;
import comp3350.cookit.objects.SC;

public class DataAccessStub
{
	private String dbName;
	private String dbType = "stub";

	private List<Author> authors;
	private List<Recipe> recipes;

	public DataAccessStub(String dbName)
	{
		this.dbName = dbName;
	}

	public DataAccessStub()
	{
		this(Main.dbName);
	}

	public void open(String dbName)
	{
		Student student;
		Course course;
		SC mySC;

		authors = Arrays.asList(
				new Author("0", "bobpiazza", "I love making muffins. Find me on allrecipes: https://www.allrecipes.com/cook/2955506"),
				new Author("1", "Myrna", "Find me on allrecipes: https://www.allrecipes.com/cook/2792648?content=recipes")
		);

		recipes = Arrays.asList(
				new Recipe(
						"Lemon Cranberry Muffins",
						"0",
						"1. Preheat oven to 400F. Grease 12 muffin cups, or line with paper muffin liners.\n2. Combine flour, sugar, baking powder, and salt in a large bowl. Mix lemon juice and milk in a measuring cup, to sour milk; beat eggs, oil, and milk mixture in a bowl. Stir egg mixture into flour mixture until just moistened; fold in cranberries. Fill prepared muffin cups two-thirds full; sprinkle with almonds.\n3. Bake in preheated oven until a toothpick inserted into a muffin comes out clean, 18 to 20 minutes. Cool for 5 minutes before removing from pan to wire rack.",
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
						Arrays.asList("Breakfast", "Comfort Food", "Easy", "Snack")
					),
				new Recipe(
						"Honey-Garlic Slow Cooker Chicken Thighs",
						"1",
						"1. Lay chicken thighs into the bottom of a 4-quart slow cooker.\n2. Whisk soy sauce, ketchup, honey, garlic, and basil together in a bowl; pour over the chicken.\n3. Cook on Low for 6 hours.",
						IngredientList.Create(
								new Ingredient("boneless, skinless chicken thighs", 4, "whole"),
								new Ingredient("soy sauce", 0.5, "cups"),
								new Ingredient("ketchup", 0.5, "cups"),
								new Ingredient("honey", 0.33, "cups"),
								new Ingredient("garlic, minced", 3, "cloves"),
								new Ingredient("dried basil", 1, "tsp")
						),
						4,
						Arrays.asList("Dinner", "Chicken", "Slow Cooker")
				)
		);

		System.out.println("Opened " +dbType +" database " +dbName);
	}

	public void close()
	{
		System.out.println("Closed " +dbType +" database " +dbName);
	}

	public String getAllRecipes(List<Recipe> recipes) {
		recipes.addAll(this.recipes);
		return null;
	}

	public String insertRecipe(Recipe recipe) {
		recipes.add(recipe);
		return null;
	}

	public String updateRecipe(Recipe recipe) {
		for(int i = 0; i < recipes.size(); i++) {
			if (recipes.get(i).getId().equals(recipe.getId())) {
				recipes.set(i, recipe);
				break;
			}
		}

		return null;
	}

	public String deleteRecipe(Recipe recipe) {
		for(int i = 0; i < recipes.size(); i++) {
			if (recipes.get(i).getId().equals(recipe.getId())) {
				recipes.remove(i);
				break;
			}
		}

		return null;
	}

	public String getAllAuthors(List<Author> authors) {
		authors.addAll(this.authors);
		return null;
	}

	public String insertAuthor(Author author) {
		authors.add(author);
		return null;
	}

	public String updateAuthor(Author author) {
		for(int i = 0; i < authors.size(); i++) {
			if (authors.get(i).getId().equals(author.getId())) {
				authors.set(i, author);
				break;
			}
		}

		return null;
	}

	public String deleteAuthor(Author author) {
		for(int i = 0; i < authors.size(); i++) {
			if (authors.get(i).getId().equals(author.getId())) {
				authors.remove(i);
				break;
			}
		}

		return null;
	}


}
