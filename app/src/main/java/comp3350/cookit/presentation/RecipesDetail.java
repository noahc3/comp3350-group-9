package comp3350.cookit.presentation;

import android.app.Activity;
import android.os.Bundle;

import comp3350.cookit.R;
import comp3350.cookit.business.AccessAuthors;
import comp3350.cookit.business.AccessRecipes;
import comp3350.cookit.business.AccessReviews;


public class RecipesDetail extends Activity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_recipesdetail);
//
//    }

    private AccessAuthors accessAuthors;
    private AccessRecipes accessRecipes;
    private AccessReviews accessReviews;
//    private ArrayList<Course> courseList;
//    private ArrayAdapter<Course> courseArrayAdapter;
//    private int selectedCoursePosition = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipesdetail);

        accessAuthors = new AccessAuthors();
        accessRecipes = new AccessRecipes();
        accessReviews = new AccessReviews();


//        courseList = new ArrayList<Course>();
//        String result = accessCourses.getCourses(courseList);
//        if (result != null)
//        {
//            Messages.fatalError(this, result);
//        }
//        else
//        {
//            courseArrayAdapter = new ArrayAdapter<Course>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, courseList)
//            {
//                @Override
//                public View getView(int position, View convertView, ViewGroup parent) {
//                    View view = super.getView(position, convertView, parent);
//
//                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
//                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);
//
//                    text1.setText(courseList.get(position).getCourseID());
//                    text2.setText(courseList.get(position).getCourseName());
//
//                    return view;
//                }
//            };

//            final ListView listView = (ListView)findViewById(R.id.listCourses);
//            listView.setAdapter(courseArrayAdapter);
//
//            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Button updateButton = (Button)findViewById(R.id.buttonCourseUpdate);
//                    Button deleteButton = (Button)findViewById(R.id.buttonCourseDelete);
//
//                    if (position == selectedCoursePosition) {
//                        listView.setItemChecked(position, false);
//                        updateButton.setEnabled(false);
//                        deleteButton.setEnabled(false);
//                        selectedCoursePosition = -1;
//                    } else {
//                        listView.setItemChecked(position, true);
//                        updateButton.setEnabled(true);
//                        deleteButton.setEnabled(true);
//                        selectedCoursePosition = position;
//                        selectCourseAtPosition(position);
//                    }
//                }
//            });
//
//            final EditText editCourseID = (EditText)findViewById(R.id.editCourseID);
//            final Button buttonCourseStudents = (Button)findViewById(R.id.buttonCourseStudents);
//            editCourseID.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before, int count) {}
//
//                @Override
//                public void afterTextChanged(Editable s) {
//                    buttonCourseStudents.setEnabled(editCourseID.getText().toString().length() > 0);
//                }
//            });
//        }
    }


}

