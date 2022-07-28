package comp3350.cookit.presentation.components;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import comp3350.cookit.R;
import comp3350.cookit.application.Main;
import comp3350.cookit.business.AccessAuthors;
import comp3350.cookit.business.AccessReviews;
import comp3350.cookit.objects.Author;
import comp3350.cookit.objects.Recipe;

public class RecipeBoxView extends LinearLayout {
    RatingBar rating;
    TextView author;
    TextView title;
    ImageView photo;

    public RecipeBoxView(Context context) {
        super(context);
        initControl(context);
    }

    public RecipeBoxView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initControl(context);
    }

    public RecipeBoxView(Context context, Recipe recipe) {
        this(context);
        setRecipeBox(recipe, context);
    }

    public void setRecipeBox(Recipe recipe, Context context) {
        AccessAuthors authors = new AccessAuthors();
        AccessReviews reviews = new AccessReviews();

        Author authorData = authors.getAuthorById(recipe.getAuthorId());
        float reviewAvg = reviews.getAverageReviewScoreForRecipe(recipe);

        title.setText(recipe.getTitle());
        author.setText(context.getString(R.string.written_by, authorData.getName()));
        rating.setNumStars(5);
        rating.setRating(reviewAvg);

        displayImage(recipe, context);
    }

    public void displayImage(Recipe recipe, Context context) {
        File dataDirectory = context.getDir(Main.getImgAssetsPath(), Context.MODE_PRIVATE);
        List<String> images = recipe.getImages();
        if (images.size() > 0) {
            try {
                String imageName = recipe.getImages().get(0);
                InputStream in = context.getContentResolver().openInputStream(Uri.fromFile(new File(dataDirectory.getPath(), imageName)));
                photo.setImageBitmap(BitmapFactory.decodeStream(in));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void initControl(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.component_recipebox_view, this);

        photo = findViewById(R.id.recipeBoxImageView);
        title = findViewById(R.id.recipeBoxTitle);
        author = findViewById(R.id.recipeBoxAuthor);
        rating = findViewById(R.id.recipeBoxAverageRating);

        this.setOnClickListener(v -> {

        });
    }
}