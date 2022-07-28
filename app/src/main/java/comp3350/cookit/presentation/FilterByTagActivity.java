package comp3350.cookit.presentation;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import comp3350.cookit.R;
import comp3350.cookit.application.Main;

public class FilterByTagActivity extends Activity {
    private LinearLayout tagsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Main.startUp();

        setContentView(R.layout.activity_filter_by_tag);

        tagsLayout = findViewById(R.id.tagsLayout);

        showTagsByCategory();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void showTagsByCategory() {
        String[][] allTags = getAllTags();
        if (allTags.length != 0) {
            tagsLayout.removeAllViews();
            for (int i = 0; i < allTags.length - 1; i++) {
                createHeader(tagsLayout, allTags[8][i]);

                HorizontalScrollView hScrollView = createHorizontalScrollView(tagsLayout);
                LinearLayout tagListLayout = createHorizontalLinearLayout(hScrollView);

                for (int j = 0; j < allTags[i].length; j++) {
                    createTagButton(tagListLayout, allTags[i][j]);
                }

            }
            tagsLayout.requestLayout();
        }
    }

    public String[][] getAllTags() {
        String[][] tagsArray = new String[9][];
        tagsArray[0] = getResources().getStringArray(R.array.tags_time_of_day);
        tagsArray[1] = getResources().getStringArray(R.array.tags_type);
        tagsArray[2] = getResources().getStringArray(R.array.tags_taste);
        tagsArray[3] = getResources().getStringArray(R.array.tags_course);
        tagsArray[4] = getResources().getStringArray(R.array.tags_diet);
        tagsArray[5] = getResources().getStringArray(R.array.tags_region);
        tagsArray[6] = getResources().getStringArray(R.array.tags_occasion);
        tagsArray[7] = getResources().getStringArray(R.array.tags_season);
        tagsArray[8] = new String[]{"Time of Day", "Type", "Taste", "Course", "Diet", "Region", "Occasion", "Season"};

        return tagsArray;
    }

    private LinearLayout createHorizontalLinearLayout(HorizontalScrollView parentLayout) {
        LinearLayout newLayout = new LinearLayout(FilterByTagActivity.this);
        newLayout.setOrientation(LinearLayout.HORIZONTAL);
        newLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        parentLayout.addView(newLayout);
        return newLayout;
    }

    private HorizontalScrollView createHorizontalScrollView(LinearLayout parentLayout) {
        HorizontalScrollView newScrollView = new HorizontalScrollView(FilterByTagActivity.this);
        newScrollView.setHorizontalScrollBarEnabled(false);

        parentLayout.addView(newScrollView);
        return newScrollView;
    }

    private TextView createHeader(LinearLayout parentLayout, String text) {
        TextView newTextView = new TextView(FilterByTagActivity.this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 40, 0, 0);
        newTextView.setLayoutParams(params);
        newTextView.setText(text);
        newTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
        newTextView.setTypeface(null, Typeface.BOLD);
        newTextView.setTextColor(getResources().getColor(R.color.colorBlack));

        parentLayout.addView(newTextView);
        return newTextView;
    }

    private Button createTagButton(LinearLayout parentLayout, String tag) {
        // Create a button programmatically to act as a tag
        Button btnTag = new Button(FilterByTagActivity.this);

        // Set the parameters for the button programmatically
        btnTag.setText(tag);
        btnTag.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        btnTag.setTextColor(getResources().getColor(R.color.colorWhite));
        btnTag.getBackground().setColorFilter(btnTag.getContext().getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
        btnTag.setOnClickListener(v -> {
            Intent taggedRecipeListIntent = new Intent(v.getContext(), TaggedListActivity.class);
            taggedRecipeListIntent.putExtra("recipeTag", tag);
            startActivity(taggedRecipeListIntent);
        });
        btnTag.setPadding(1, 1, 1, 1);

        parentLayout.addView(btnTag);

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
        btnTag.getLayoutParams().width = tag.length() >= 8 ? tag.length() * 22 : tag.length() * 30;

        return btnTag;
    }
}
