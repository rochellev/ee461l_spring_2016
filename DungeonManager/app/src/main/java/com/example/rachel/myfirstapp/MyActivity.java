package com.example.rachel.myfirstapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MyActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.rachel.myfirstapp.MESSAGE";

    private static final String PREFS_NAME = "CharacterSheet";
    private static final String DEFAULT_CHAR_NAME = "THISisNoTAcharACtERName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        if(settings.getString("cName", DEFAULT_CHAR_NAME) != DEFAULT_CHAR_NAME){
            EditText editText =(EditText) findViewById(R.id.edit_message);
            EditText nameText =(EditText) findViewById(R.id.name_message);
            EditText typeText =(EditText) findViewById(R.id.type_message);
            EditText classText =(EditText) findViewById(R.id.class_message);

            nameText.setText(settings.getString("cName", DEFAULT_CHAR_NAME));
            typeText.setText(settings.getString("cType", "noType"));
            classText.setText(settings.getString("cClass", "noClass"));
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Hello Again", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view){
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText =(EditText) findViewById(R.id.edit_message);
        EditText nameText =(EditText) findViewById(R.id.name_message);
        EditText typeText =(EditText) findViewById(R.id.type_message);
        EditText classText =(EditText) findViewById(R.id.class_message);

        String message = editText.getText().toString();
        String name_message = nameText.getText().toString();
        String type_message = typeText.getText().toString();
        String class_message = classText.getText().toString();

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("cName", name_message);
        editor.putString("cType", type_message);
        editor.putString("cClass",class_message);
        // Commit the edits!
        editor.commit();

        intent.putExtra(EXTRA_MESSAGE, message);
        intent.putExtra("name", name_message);
        intent.putExtra("type", type_message);
        intent.putExtra("class", class_message);

        startActivity(intent);
    }
}
