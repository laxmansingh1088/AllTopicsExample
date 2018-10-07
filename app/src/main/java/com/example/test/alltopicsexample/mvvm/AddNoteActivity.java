package com.example.test.alltopicsexample.mvvm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.test.alltopicsexample.R;
import com.example.test.alltopicsexample.utils.BaseActivity;

public class AddNoteActivity extends BaseActivity {

    public static final String EXTRA_TITLE = "com.example.test.alltopicsexample.mvvm.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.test.alltopicsexample.mvvm.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.example.test.alltopicsexample.mvvm.EXTRA_PRIORITY";

    private EditText et_title, et_description;
    private NumberPicker number_picker;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note_activity);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white);
        setTitle(getString(R.string.add_note));
        onFindView();
        onInView();
        onBindView();
    }

    @Override
    protected void onFindView() {
        et_title = findViewById(R.id.et_title);
        et_description = findViewById(R.id.et_description);
        number_picker = findViewById(R.id.number_picker);
    }

    @Override
    protected void onInView() {


        number_picker.setMaxValue(10);
        number_picker.setMinValue(1);

    }

    @Override
    protected void onBindView() {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.save_note_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.save_note:
                saveNote();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void saveNote() {

        String title = et_title.getText().toString().trim();
        String description = et_description.getText().toString().trim();
        int priority = number_picker.getValue();

        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Please enter title and descrition.", Toast.LENGTH_SHORT).show();
            return;
        } else {

            Intent intent = new Intent();
            intent.putExtra(EXTRA_TITLE, title);
            intent.putExtra(EXTRA_DESCRIPTION, description);
            intent.putExtra(EXTRA_PRIORITY, priority);

            setResult(Activity.RESULT_OK, intent);
            finish();

        }
    }


}
