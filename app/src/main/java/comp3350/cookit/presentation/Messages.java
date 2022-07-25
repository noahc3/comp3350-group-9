package comp3350.cookit.presentation;

import android.app.Activity;
import android.widget.Toast;

public class Messages {
    public static void toastShort(Activity owner, String message) {
        Toast.makeText(owner.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public static void toastLong(Activity owner, String message) {
        Toast.makeText(owner.getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
