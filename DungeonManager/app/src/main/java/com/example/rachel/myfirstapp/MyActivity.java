package com.example.rachel.myfirstapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

public class MyActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.rachel.myfirstapp.MESSAGE";

    private static final String PREFS_NAME = "CharacterSheet";
    private static final String DEFAULT_CHAR_NAME = "THISisNoTAcharACtERName";
    private CharacterSheet chac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences settings = getSharedPreferences(CharacterSheet.getPrefsName(0), 0);
        chac = CharacterSheet.loadSheet(settings);

        EditText nameText =(EditText) findViewById(R.id.name_message);
        EditText typeText =(EditText) findViewById(R.id.type_message);
        EditText classText =(EditText) findViewById(R.id.class_message);

        nameText.setText(chac.name);
        typeText.setText(chac.race);
        classText.setText(chac.cclass);

        String [] DD = {"Name", "Title", "Class", "Stuff"};

        ListAdapter theAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, DD);

        ListView theListView  = (ListView) findViewById(R.id.theListView);

        theListView.setAdapter(theAdapter);

        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   String DD_picked = "You selected " + String.valueOf(parent.getItemAtPosition(position));

                   Toast.makeText(MyActivity.this, DD_picked, Toast.LENGTH_SHORT).show();
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

    /** Called when the user clicks the Submit button */
    public void submitFields(View view){

        NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(this);

        dialogBuilder
                .withTitle(getString(R.string.submit_dialog_title))
                .withMessage(getString(R.string.submit_fields_dialog_message))
                .withButton1Text(getString(R.string.dialog_yes))
                .setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MyActivity.this, DisplayMessageActivity.class);
                        EditText editText = (EditText) findViewById(R.id.edit_message);
                        EditText nameText = (EditText) findViewById(R.id.name_message);
                        EditText typeText = (EditText) findViewById(R.id.type_message);
                        EditText classText = (EditText) findViewById(R.id.class_message);

                        String message = editText.getText().toString();
                        String name_message = nameText.getText().toString();
                        String type_message = typeText.getText().toString();
                        String class_message = classText.getText().toString();

                        chac.cclass = class_message;
                        chac.name = name_message;
                        chac.race = type_message;

                        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                        chac.saveSheet(settings);

                        intent.putExtra(EXTRA_MESSAGE, message);
                        intent.putExtra("name", name_message);
                        intent.putExtra("type", type_message);
                        intent.putExtra("class", class_message);

                        startActivity(intent);
                    }
                })
                .withButton2Text(getString(R.string.dialog_no))
                .show();

    }
}