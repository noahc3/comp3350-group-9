package comp3350.cookit.presentation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import comp3350.cookit.R;
import comp3350.cookit.application.Main;
import comp3350.cookit.business.AccessAuthors;
import comp3350.cookit.business.AccessRecipes;
import comp3350.cookit.business.StringUtilities;
import comp3350.cookit.objects.Author;
import comp3350.cookit.objects.Ingredient;
import comp3350.cookit.objects.IngredientList;
import comp3350.cookit.objects.Recipe;

public class NewRecipeActivity extends Activity {
    private final List<Ingredient> list = new ArrayList<>();
    private final ArrayList<String> ingredientListLayout = new ArrayList<>();

    private AutoCompleteTextView fraction;
    private AutoCompleteTextView units;
    private AutoCompleteTextView difficulty;
    private AutoCompleteTextView tagsType;
    private AutoCompleteTextView tagsTaste;
    private AutoCompleteTextView tagsTime;
    private AutoCompleteTextView tagsCourse;

    private TextInputEditText recipeName;
    private TextInputEditText author;
    private TextInputEditText servingSize;
    private TextInputEditText ingredientName;
    private TextInputEditText ingredientLayout;
    private TextInputEditText amountWhole;
    private TextInputEditText directions;
    private TextInputEditText prepTime;
    private TextInputEditText cookTime;

    private Button addPhotosButton;
    private TextView numPhotosText;

    private List<Uri> photos;

    private final AccessRecipes accessRecipes = new AccessRecipes();
    private final AccessAuthors accessAuthors = new AccessAuthors();

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);


        fraction = findViewById(R.id.amountFraction);
        initializeDropdowns(fraction, R.array.fraction_dropdown);

        units = findViewById(R.id.units);
        initializeDropdowns(units, R.array.unit_dropdown);

        difficulty = findViewById(R.id.difficulty);
        initializeDropdowns(difficulty, R.array.difficulty_dropdown);

        tagsType = findViewById(R.id.tagsType);
        initializeDropdowns(tagsType, R.array.tags_type);

        tagsTaste = findViewById(R.id.tagsTaste);
        initializeDropdowns(tagsTaste, R.array.tags_taste);

        tagsTime = findViewById(R.id.tagsTimeOfDay);
        initializeDropdowns(tagsTime, R.array.tags_time_of_day);

        tagsCourse = findViewById(R.id.tagsCourse);
        initializeDropdowns(tagsCourse, R.array.tags_course);

        recipeName = findViewById(R.id.recipeName);
        author = findViewById(R.id.authorName);
        servingSize = findViewById(R.id.servingSize);
        ingredientName = findViewById(R.id.ingredientName);
        ingredientLayout = findViewById(R.id.ingredientList);
        amountWhole = findViewById(R.id.amountWhole);
        directions = findViewById(R.id.directionList);
        prepTime = findViewById(R.id.prepTime);
        cookTime = findViewById(R.id.cookTime);

        addPhotosButton = findViewById(R.id.addPhoto);
        numPhotosText = findViewById(R.id.numPhotosDisplay);
    }

    public void onAddToList(View v) {
        if (validateIngredient()) {
            // Assume numWhole = 0 if the field is empty AND a numFraction is provided
            // validateIngredient() ensures this is never reached if both amountWhole and amountFraction are empty
            String numWhole = TextUtils.isEmpty(amountWhole.getText()) ? "0" : amountWhole.getText().toString();
            String numFraction = fraction.getText().toString();
            String strUnits = units.getText().toString();

            double amount = (double) Integer.parseInt(numWhole) + getDouble(numFraction);

            // Display nothing if selected == "none"
            String wholeString = !numWhole.equals("0") ? numWhole + " " : "";
            String fractionString = !numFraction.equals("") ? numFraction + " " : "";
            String ingredientString = ingredientName.getText().toString();

            String currIngredients = !TextUtils.isEmpty(ingredientLayout.getText()) ? ingredientLayout.getText().toString() + "\n" : "";
            String newIngredient = wholeString + fractionString + strUnits + " " + StringUtilities.toCapitalized(ingredientString);
            String ingredientDisplay = currIngredients + newIngredient;
            ingredientLayout.setText(ingredientDisplay);
            ingredientLayout.setError(null);
            ingredientListLayout.add(newIngredient);

            Ingredient ingredient = new Ingredient(currIngredients, amount, strUnits);
            list.add(ingredient);

            clearIngredientFields();
        } else {
            showIngredientErrors();
        }
    }

    public void onCancel(View v) {
        finish();
    }

    public void onSubmit(View v) {
        if (validateInput()) {
            String recipeId = UUID.randomUUID().toString();
            String authorId = UUID.randomUUID().toString();

            int servingSize = Integer.parseInt(this.servingSize.getText().toString());
            int prepTime = Integer.parseInt(this.prepTime.getText().toString());
            int cookTime = Integer.parseInt(this.cookTime.getText().toString());

            String difficulty = this.difficulty.getText().toString();
            List<String> tagList = attachTags();

            List<String> images = importPhotosToApplicationData(photos);

            Author newAuthor = new Author(authorId, author.getText().toString(), "");
            Recipe newRecipe = new Recipe(recipeId, recipeName.getText().toString(), authorId, directions.getText().toString(), new IngredientList(list), servingSize, tagList, prepTime, cookTime, difficulty, images);

            accessAuthors.insertAuthor(newAuthor);
            accessRecipes.insertRecipe(newRecipe);
            finish();
        } else {
            showTextInputErrors();
        }
    }

    public void addPhotos(View v) {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        } else {
            selectPhotos();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                selectPhotos();
            }
        }
    }

    private void selectPhotos() {
        Intent choosePhoto = new Intent();
        choosePhoto.setType("image/*");
        choosePhoto.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        choosePhoto.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(choosePhoto, "Select Photos"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        photos = new ArrayList<>();
        if (resultCode == RESULT_OK && requestCode == 1) {
            if (data.getClipData() != null) {
                int count = data.getClipData().getItemCount();
                for (int i = 0; i < count; i++) {
                    if (data.getClipData().getItemAt(i) != null)
                        photos.add(data.getClipData().getItemAt(i).getUri());
                }
            } else if (data.getData() != null) {
                photos.add(data.getData());
            }
        }

        addPhotosButton.setText(getString(R.string.reselect_photos));
        numPhotosText.setText(getString(R.string.selected_photo_count, photos.size()));
    }

    private List<String> importPhotosToApplicationData(List<Uri> photos) {
        List<String> resultIds = new ArrayList<>();

        if (photos != null && photos.size() > 0) {
            Context context = getApplicationContext();
            File dataDirectory = context.getDir(Main.getImgAssetsPath(), Context.MODE_PRIVATE);

            for (Uri uri : photos) {
                String fileName = UUID.randomUUID().toString();
                String filePath = dataDirectory.getPath() + "/" + fileName;

                try {
                    copyFile(uri, filePath);
                    resultIds.add(fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return resultIds;
    }

    private void copyFile(Uri src, String dst) throws IOException {
        byte[] buffer = new byte[1024];
        int count;

        File outFile = new File(dst);

        if (!outFile.exists()) {
            InputStream in = getContentResolver().openInputStream(src);
            FileOutputStream out = new FileOutputStream(outFile);

            count = in.read(buffer);
            while (count != -1) {
                out.write(buffer, 0, count);
                count = in.read(buffer);
            }

            out.close();
            in.close();
        }
    }

    // private as these are helper methods
    private boolean validateInput() { // verify if every required field is filled
        boolean isValid = true; // initially true

        if (TextUtils.isEmpty(recipeName.getText())
                || TextUtils.isEmpty(author.getText())
                || TextUtils.isEmpty(servingSize.getText())
                || TextUtils.isEmpty(directions.getText())
                || TextUtils.isEmpty(prepTime.getText())
                || TextUtils.isEmpty(cookTime.getText())
                || TextUtils.isEmpty(difficulty.getText())
                || ingredientListLayout.isEmpty())
            isValid = false; // change to false if one of the fields are empty

        return isValid;
    }

    private boolean validateIngredient() { // verify if every required ingredient field is filled
        boolean isValid = true; // initially true

        if (TextUtils.isEmpty(ingredientName.getText()) || TextUtils.isEmpty(units.getText()))
            isValid = false; // change to false if one of the fields are empty

        // Positive integer + no fraction is valid,
        // Empty whole number field (assumed 0) + fraction is valid,
        // Empty whole number field + no fraction is invalid
        if (TextUtils.isEmpty(amountWhole.getText()) && TextUtils.isEmpty(fraction.getText()))
            isValid = false;

        return isValid;
    }

    private void showTextInputErrors() {
        // Show an error message for each text input that is missing
        if (TextUtils.isEmpty(recipeName.getText()))
            recipeName.setError("You must provide a name for the recipe");

        if (TextUtils.isEmpty(author.getText()))
            author.setError("You must provide an author");

        if (TextUtils.isEmpty(servingSize.getText()))
            servingSize.setError("You must provide a serving size");

        if (TextUtils.isEmpty(directions.getText()))
            directions.setError("You must give directions for the recipe");

        if (ingredientListLayout.isEmpty())
            ingredientLayout.setError("You must add at least one (1) ingredient");

        if (TextUtils.isEmpty(prepTime.getText()))
            prepTime.setError("You must specify the prep time");

        if (TextUtils.isEmpty(cookTime.getText()))
            cookTime.setError("You must specify the cook time");

        if (TextUtils.isEmpty(difficulty.getText()))
            difficulty.setError("You must indicate the difficulty level of the recipe");
    }

    private void showIngredientErrors() {
        // Show an error message for each text input that is missing
        if (TextUtils.isEmpty(ingredientName.getText()))
            ingredientName.setError("You must provide an ingredient");

        if (TextUtils.isEmpty(amountWhole.getText()) && TextUtils.isEmpty(fraction.getText())) {
            String errorMsg = "You must provide one or both of a whole number and/or a fraction";
            amountWhole.setError(errorMsg);
            fraction.setError(errorMsg);
        }

        if (TextUtils.isEmpty(units.getText()))
            units.setError("You must provide a unit of measurement");
    }

    private ArrayList<String> attachTags() {
        ArrayList<String> tagList = new ArrayList<>();

        if (!TextUtils.isEmpty(tagsType.getText()))
            tagList.add(tagsType.getText().toString());

        if (!TextUtils.isEmpty(tagsTaste.getText()))
            tagList.add(tagsTaste.getText().toString());

        if (!TextUtils.isEmpty(tagsTime.getText()))
            tagList.add(tagsTime.getText().toString());

        if (!TextUtils.isEmpty(tagsCourse.getText()))
            tagList.add(tagsCourse.getText().toString());

        return tagList;
    }

    private double getDouble(String selected) {
        double doubleVal = 0;
        int numerator;
        int denominator;

        if (selected != null && !selected.equals("")) {
            numerator = selected.charAt(0) - '0';
            denominator = selected.charAt(2) - '0';
            doubleVal = (double) numerator / (double) denominator;
        }

        return doubleVal;
    }

    private void initializeDropdowns(final AutoCompleteTextView dropdown, int id) {
        String[] dropdownOptions = getResources().getStringArray(id);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.dropdown_option, dropdownOptions);
        dropdown.setAdapter(adapter);

        // Show all text suggestions to mimic a dropdown list's behavior
        dropdown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    dropdown.showDropDown(); // show all dropdown options
                    dropdown.setError(null); // clear any preexisting error indicators
                }

                return false;
            }
        });
    }

    private void clearIngredientFields() {
        ingredientName.getText().clear();
        amountWhole.getText().clear();
        fraction.getText().clear();
        units.getText().clear();
    }


}