package comp3350.cookit.presentation.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import comp3350.cookit.R;
import comp3350.cookit.objects.Review;

public class ReviewView extends LinearLayout {
    RatingBar rating;
    TextView author;
    TextView content;

    public ReviewView(Context context) {
        super(context);
        initControl(context);
    }

    public ReviewView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initControl(context);
    }

    public ReviewView(Context context, Review review) {
        this(context);
        setReview(review);
    }

    public void setReview(Review review) {
        rating.setNumStars(5);
        rating.setRating(review.getRating());
        author.setText(review.getAuthor());
        content.setText(review.getContent());
    }

    private void initControl(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.component_review_view, this);

        rating = findViewById(R.id.reviewViewRating);
        author = findViewById(R.id.reviewViewAuthor);
        content = findViewById(R.id.reviewViewContent);
    }
}
