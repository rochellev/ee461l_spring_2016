package com.example.rachel.myfirstapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.content.res.Configuration;

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

public class MyActivity extends AppCompatActivity {
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;

    public final static String EXTRA_MESSAGE = "com.example.rachel.myfirstapp.MESSAGE";
    private CharacterSheet chac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        //maybe delete???
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerList = (ListView)findViewById(R.id.navList);mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        SharedPreferences settings = getSharedPreferences(CharacterSheet.getPrefsName(0), 0);
        chac = CharacterSheet.loadSheet(settings);

        EditText nameText =(EditText) findViewById(R.id.name_message);
        EditText typeText =(EditText) findViewById(R.id.type_message);
        EditText classText =(EditText) findViewById(R.id.class_message);

        nameText.setText(chac.name);
        typeText.setText(chac.race);
        classText.setText(chac.cclass);

    }
    private void addDrawerItems() {
        String[] osArray = { "Android", "iOS", "Windows", "OS X", "Linux" };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MyActivity.this, "YESSS!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
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
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /** Called when the user clicks the Submit button */
    public void submitFields(View view){

        NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(this);

        chac.cclass = class_message;
        chac.name = name_message;
        chac.race = type_message;
        
        SharedPreferences settings = getSharedPreferences(CharacterSheet.getPrefsName(0), 0);
        chac.saveSheet(settings);
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

        intent.putExtra(EXTRA_MESSAGE, message);
        //intent.putExtra("name", name_message);
        //intent.putExtra("type", type_message);
        //intent.putExtra("class", class_message);
        intent.putExtra("CharacterSheet", chac);
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
