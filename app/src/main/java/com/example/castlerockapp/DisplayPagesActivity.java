package com.example.castlerockapp;

import android.content.Context;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import static android.graphics.Typeface.BOLD;

public class DisplayPagesActivity extends AppCompatActivity {

    Context mContext;
    private static final String TAG = DisplayPagesActivity.class.getSimpleName();
    private ArrayList<String> listFromIntent = new ArrayList<String>();
    private String mDisplayPagesLinesString;
    private String mSignNameString;

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_pages);

        mContext = DisplayPagesActivity.this;

        Toolbar toolbar   = findViewById(R.id.toolbar);
        TextView mTitleTv = findViewById(R.id.toolbar_title);
        TextView mDisplayPagesLinesTv = findViewById(R.id.display_pages_tv);

        setSupportActionBar(toolbar);

        Bundle b = getIntent().getExtras();

        if(b != null) {

            listFromIntent = b.getStringArrayList("sign_details");

            mSignNameString          = listFromIntent.get(0);
            mDisplayPagesLinesString = listFromIntent.get(1);

            mTitleTv.setText(mSignNameString);

            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            //mDisplayPagesLinesTv.setText(mDisplayPagesLinesString);
            mDisplayPagesLinesTv.setText(formatAStringPortionInBold(
                    mDisplayPagesLinesString,
                    mDisplayPagesLinesString,
                    false
            ));

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_display_pages, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_back) {

            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Makes a portion of String formatted in BOLD.
     *
     * https://stackoverflow.com/questions/14371092/how-to-make-a-specific-text-on-textview-bold
     *
     * @param completeString       String from which a portion needs to be extracted and formatted.<br> eg. I am BOLD.
     * @param targetStringToFormat Target String value to format. <br>eg. BOLD
     * @param matchCase Match by target character case or not. If true, BOLD != bold
     * @return A string with a portion formatted in BOLD. <br> I am <b>BOLD</b>.
     */
    public static SpannableStringBuilder formatAStringPortionInBold(String completeString, String targetStringToFormat, boolean matchCase) {
        //Null complete string return empty
        if (TextUtils.isEmpty(completeString)) {
            return new SpannableStringBuilder("");
        }

        SpannableStringBuilder str = new SpannableStringBuilder(completeString);
        int start_index = 0;

        //if matchCase is true, match exact string
        if (matchCase) {
            if (TextUtils.isEmpty(targetStringToFormat) ||
                    !completeString.contains(targetStringToFormat)) {
                return str;
            }

            start_index = str.toString().indexOf(targetStringToFormat);
        } else {
            //else find in lower cases
            if (TextUtils.isEmpty(targetStringToFormat) ||
                    !completeString.toLowerCase().contains(targetStringToFormat.toLowerCase()))
            {
                return str;
            }

            start_index = str.toString().toLowerCase().indexOf(targetStringToFormat.toLowerCase());
        }

        int end_index = start_index + targetStringToFormat.length();
        str.setSpan(new StyleSpan(BOLD), start_index, end_index, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return str;
    }

}