package comp3350.cookit.presentation;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

import comp3350.cookit.business.AccessAuthors;
import comp3350.cookit.business.AccessRecipes;
import comp3350.cookit.business.AccessReviews;
import comp3350.cookit.objects.Author;
import comp3350.cookit.objects.Recipe;
import comp3350.cookit.objects.Review;

public class CLI  // command-line interface
{
	public static BufferedReader console;
	public static String inputLine;
	public static String[] inputTokens;

	public static AccessRecipes accessRecipes;
	public static AccessAuthors accessAuthors;
	public static AccessReviews accessReviews;
	
	public static String indent = "  ";
	
	public static void run()
	{
		try
		{
			console = new BufferedReader(new InputStreamReader(System.in));
			accessRecipes = new AccessRecipes();
			accessAuthors = new AccessAuthors();
			process();
			console.close();
		}
		catch (IOException ioe)
		{
			System.out.println(ioe.getMessage());
			ioe.printStackTrace();
		}
	}

	public static void process()
	{
		readLine();
		while ((inputLine != null) && (!inputLine.equalsIgnoreCase("exit"))
				&& (!inputLine.equalsIgnoreCase("quit"))
				&& (!inputLine.equalsIgnoreCase("q"))
				&& (!inputLine.equalsIgnoreCase("bye")))
		{	// use cntl-c or exit to exit
			inputTokens = inputLine.split("\\s+");
			parse();
			readLine();
		}
	}

	public static void readLine()
	{
		try
		{
			System.out.print(">");
			inputLine = console.readLine();
		}
		catch (IOException ioe)
		{
			System.out.println(ioe.getMessage());
			ioe.printStackTrace();
		}
	}

	public static void parse()
	{
		if (inputTokens[0].equalsIgnoreCase("get"))
		{
			processGet();
		}
		else
		{
			System.out.println("Invalid command.");
		}
	}

	public static void processGet()
	{
		if (inputTokens[1].equalsIgnoreCase("Recipe"))
		{
			processGetRecipe();
		}
		else if (inputTokens[1].equalsIgnoreCase("Course"))
		{
			processGetAuthor();
		}
		else if (inputTokens[1].equalsIgnoreCase("Review"))
		{
			processGetReview();
		}
		else
		{
			System.out.println("Invalid data type");
		}
	}

	public static void processGetRecipe()
	{
		Recipe currentRecipe;
		String id;
		
		if (inputTokens.length > 2)
		{
			id = inputTokens[2];
			currentRecipe = accessRecipes.getRecipeById(id);
			System.out.println(indent + currentRecipe);
		}
		else
		{
			for (Recipe recipe : accessRecipes.getRecipes()) {
				System.out.println(indent + recipe);
			}
		}
	}

	public static void processGetAuthor()
	{
		Author currentAuthor;
		String id;

		if (inputTokens.length > 2)
		{
			id = inputTokens[2];
			currentAuthor = accessAuthors.getAuthorById(id);
			System.out.println(indent + currentAuthor);
		}
		else
		{
			for (Recipe recipe : accessRecipes.getRecipes()) {
				System.out.println(indent + recipe);
			}
		}
	}

	public static void processGetReview()
	{
		Review currentReview;
		String id;

		if (inputTokens.length > 2)
		{
			id = inputTokens[2];
			currentReview = accessReviews.getReviewById(id);
			System.out.println(indent + currentReview);
		}
		else
		{
			for (Review review : accessReviews.getReviews()) {
				System.out.println(indent + review);
			}
		}
	}
}