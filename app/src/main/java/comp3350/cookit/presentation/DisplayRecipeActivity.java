package comp3350.cookit.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import comp3350.cookit.R;
import comp3350.cookit.application.Main;
import comp3350.cookit.business.AccessAuthors;
import comp3350.cookit.business.AccessRecipes;
import comp3350.cookit.business.AccessReviews;
import comp3350.cookit.business.ServingSizeUtilities;
import comp3350.cookit.objects.Author;
import comp3350.cookit.objects.Fraction;
import comp3350.cookit.objects.Ingredient;
import comp3350.cookit.objects.Recipe;
import comp3350.cookit.objects.Review;
import comp3350.cookit.presentation.components.ReviewView;

public class DisplayRecipeActivity extends Activity {
    private static final int defaultServingSize = 1;

    private ScrollView rootScrollView;
    private TextView recipeTitle;
    private TextView recipeAuthor;
    private TextView difficultyText;
    private TextView prepTimeText;
    private TextView cookTimeText;
    private TextView servingsText;
    private TextView recipeInstructions;
    private LinearLayout ingredientsList;
    private LinearLayout tagsList;
    private Spinner servingsDropdown;
    private LinearLayout imageViewLayout;
    private LinearLayout imageButtonsLayout;
    private ImageView imageView;

    private ImageView favorite;
    ImageView favoriteImage;

    private RatingBar averageRating;
    private TextView ratingCount;

    private EditText reviewContent;
    private EditText reviewAuthor;
    private RatingBar reviewRating;

    private LinearLayout reviewsLayout;

    private int selectedImage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Main.startUp();
        setContentView(R.layout.activity_display_recipe);

        rootScrollView = findViewById(R.id.displayRecipeScrollView);

        recipeTitle = findViewById(R.id.recipeTitle);
        recipeAuthor = findViewById(R.id.recipeAuthor);
        difficultyText = findViewById(R.id.difficulty_display);
        prepTimeText = findViewById(R.id.prep_time_display);
        cookTimeText = findViewById(R.id.cook_time_display);
        servingsText = findViewById(R.id.servingsText);
        recipeInstructions = findViewById(R.id.recipeInstructions);
        ingredientsList = findViewById(R.id.ingredientList);
        tagsList = findViewById(R.id.tagsList);
        servingsDropdown = findViewById(R.id.servingsDropdown);
        imageViewLayout = findViewById(R.id.recipeImageLayout);
        imageButtonsLayout = findViewById(R.id.recipeImageButtonsLayout);
        imageView = findViewById(R.id.recipeImageView);

        averageRating = findViewById(R.id.recipeAverageRating);
        ratingCount = findViewById(R.id.recipeRatingCount);

        reviewContent = findViewById(R.id.reviewMultiline);
        reviewAuthor = findViewById(R.id.reviewName);
        reviewRating = findViewById(R.id.reviewRating);

        reviewsLayout = findViewById(R.id.recipeReviewsLayout);

        favorite = findViewById(R.id.favorite);

        displayRecipe(defaultServingSize);

        servingsDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                displayRecipe(position + 1);
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        reviewRating.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            if (rating < 1.0f) {
                ratingBar.setRating(1.0f);
            }
        });

        favoriteImage = (ImageView) findViewById(R.id.favorite);
        clickFavorite();
    }

    public void clickFavorite() {
        AccessRecipes recipes = new AccessRecipes();
        if (recipes.isRecipeFavorited(getRecipeToDisplay())) {
            favoriteImage.setImageResource(R.drawable.ic_baseline_favorite_24);
            favoriteImage.setOnClickListener(this::deleteFavorite);
        } else {
            favoriteImage.setImageResource(R.drawable.ic_baseline_favorite_border_24);
            favoriteImage.setOnClickListener(this::addFavorite);
        }
    }

    public void addFavorite(View v) {
        favoriteImage.setImageResource(R.drawable.ic_baseline_favorite_24);
        AccessRecipes recipes = new AccessRecipes();
        recipes.insertFavoriteRecipe(getRecipeToDisplay());
        Messages.toastShort(this, getString(R.string.favorited));
        clickFavorite();
    }

    public void deleteFavorite(View v) {
        favoriteImage.setImageResource(R.drawable.ic_baseline_favorite_border_24);
        AccessRecipes recipes = new AccessRecipes();
        recipes.deleteFavoriteRecipe(getRecipeToDisplay());
        Messages.toastShort(this, getString(R.string.defavorited));
        clickFavorite();
    }

    public void displayRecipe(int servingSize) {
        Recipe recipe = getRecipeToDisplay();
        AccessAuthors authors = new AccessAuthors();
        Author author = authors.getAuthorById(recipe.getAuthorId());
        recipe = ServingSizeUtilities.multiplyServingSize(recipe, servingSize);

        recipeTitle.setText(recipe.getTitle());
        recipeAuthor.setText(getString(R.string.written_by, author.getName()));
        difficultyText.setText(getString(R.string.difficulty_display, recipe.getDifficulty()));
        prepTimeText.setText(getString(R.string.prep_time_display, recipe.getPrepTime()));
        cookTimeText.setText(getString(R.string.cook_time_display, recipe.getCookTime()));
        displayTags(recipe);
        displayReviews(recipe);
        servingsText.setText(getString(R.string.creates_servings, recipe.getServingSize()));
        recipeInstructions.setText(recipe.getContent());

        if (recipe.getImages().size() == 0) {
            imageViewLayout.removeAllViews();
        } else {
            if (recipe.getImages().size() == 1) {
                imageViewLayout.removeView(imageButtonsLayout);
            }

            displaySelectedImage(recipe);
        }

        ingredientsList.removeAllViews();
        for (Ingredient i : recipe.getIngredientList().getIngredients()) {
            TextView text = new TextView(DisplayRecipeActivity.this);
            if (i.getMeasurement().isEmpty()) {
                text.setText(getString(R.string.ingredient_format_no_unit, new Fraction(i.getQuantity()).getMixedFraction(), i.getName()));
            } else {
                text.setText(getString(R.string.ingredient_format, new Fraction(i.getQuantity()).getMixedFraction(), i.getMeasurement(), i.getName()));
            }
            ingredientsList.addView(text);
            text.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        }
        ingredientsList.requestLayout();


    }

    public void prevPhoto(View view) {
        Recipe recipe = getRecipeToDisplay();

        selectedImage = (selectedImage + recipe.getImages().size() - 1) % recipe.getImages().size();
        imageView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left));
        displaySelectedImage(recipe);
    }

    public void nextPhoto(View view) {
        Recipe recipe = getRecipeToDisplay();

        selectedImage = (selectedImage + 1) % recipe.getImages().size();
        imageView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right));
        displaySelectedImage(recipe);
    }

    public void navigateToTaggedRecipeList(View view) {
        Button btn = (Button) view;
        String tag = btn.getText().toString();

        Intent taggedRecipeListIntent = new Intent(this, TaggedListActivity.class);
        taggedRecipeListIntent.putExtra("recipeTag", tag);
        startActivity(taggedRecipeListIntent);
    }

    private void displaySelectedImage(Recipe recipe) {
        //I would prefer to query the images through the persistence package, but we need the app context
        //to resolve the file paths, similar to the SQL script init. Android docs strongly discourage
        //passing contexts outside of activity classes, so we have to load the image files here.
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(Main.getImgAssetsPath(), Context.MODE_PRIVATE);
        String imageName = recipe.getImages().get(selectedImage);
        try {
            InputStream in = getContentResolver().openInputStream(Uri.fromFile(new File(dataDirectory.getPath(), imageName)));
            imageView.setImageBitmap(BitmapFactory.decodeStream(in));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayTags(Recipe recipe) {
        if (!recipe.getTags().isEmpty()) {
            tagsList.removeAllViews();
            for (String tag : recipe.getTags()) {
                // Create a button programmatically to act as a tag
                Button btnTag = new Button(DisplayRecipeActivity.this);

                // Set the parameters for the button programmatically
                btnTag.setText(tag);
                btnTag.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
                btnTag.setTextColor(getResources().getColor(R.color.colorWhite));
                btnTag.getBackground().setColorFilter(btnTag.getContext().getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                btnTag.setOnClickListener(this::navigateToTaggedRecipeList);
                btnTag.setPadding(1, 1, 1, 1);

                tagsList.addView(btnTag);

                /**
                 * Re-adjust the width after the Button is added to the View
                 *
                 * The LayoutParams approach (below) does not seem to behave as how the
                 * documentation says it would; it does not apply any change at all.
                 * LinearLayout.LayoutParams layoutParams...
                 * ... btnTag.setLayoutParams(layoutParams);
                 *
                 * Instead, for longer strings, we change the width of button to a size relative
                 * to their length; Android Studio seems to resize it accordingly if it is close
                 * enough, thus the hard-coded scaling "22"
                 */
                if (tag.length() >= 8)
                    btnTag.getLayoutParams().width = tag.length() * 22;
            }
            tagsList.requestLayout();
        }
        // else do nothing and retain the "no tags have been attached" message
    }

    public void displayReviews(Recipe recipe) {
        AccessReviews ar = new AccessReviews();
        List<Review> reviews = ar.getReviewsForRecipe(recipe);

        if (reviews.size() > 0) {
            reviewsLayout.removeAllViews();
            for (Review review : reviews) {
                ReviewView view = new ReviewView(this, review);
                reviewsLayout.addView(view);
            }
        }

        ratingCount.setText(getString(R.string.review_count, reviews.size()));
        averageRating.setNumStars(5);
        averageRating.setRating(ar.getAverageReviewScoreForRecipe(recipe));
    }

    public void onSubmitReview(View v) {
        if (validateReviewSubmission()) {
            AccessReviews reviews = new AccessReviews();
            String reviewId = UUID.randomUUID().toString();
            String recipeId = getIntent().getExtras().getString("recipeId");
            String author = reviewAuthor.getText().toString();
            String content = reviewContent.getText().toString();
            int rating = Math.round(reviewRating.getRating());

            Review review = new Review(reviewId, recipeId, author, content, rating);
            reviews.insertReview(review);

            reloadAfterReviewSubmission();

            Messages.toastShort(this, getString(R.string.review_submitted));
        } else {
            displayReviewSubmissionErrors();
        }
    }

    private boolean validateReviewSubmission() {
        boolean invalid;

        invalid = TextUtils.isEmpty(reviewContent.getText());
        invalid = invalid || TextUtils.isEmpty(reviewAuthor.getText());

        return !invalid;
    }

    private void displayReviewSubmissionErrors() {
        if (TextUtils.isEmpty(reviewContent.getText())) {
            reviewContent.setError(getString(R.string.review_error_empty_content));
        }

        if (TextUtils.isEmpty(reviewAuthor.getText())) {
            reviewAuthor.setError(getString(R.string.review_error_empty_author));
        }

        Messages.toastLong(this, getString(R.string.submission_error));
    }

    private void reloadAfterReviewSubmission() {
        reviewContent.setText("");
        reviewAuthor.setText("");
        reviewRating.setRating(3);

        displayReviews(getRecipeToDisplay());
    }

    private Recipe getRecipeToDisplay() {
        AccessRecipes recipes = new AccessRecipes();
        String recipeId = getIntent().getExtras().getString("recipeId");
        return recipes.getRecipeById(recipeId);
    }
}
