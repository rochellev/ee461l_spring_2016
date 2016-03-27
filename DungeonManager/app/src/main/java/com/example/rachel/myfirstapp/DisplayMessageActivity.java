package com.example.rachel.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        CharacterSheet ch = (CharacterSheet) intent.getSerializableExtra("CharacterSheet");
//        String message = intent.getStringExtra(MyActivity.EXTRA_MESSAGE);
//        String name = intent.getStringExtra("name");
//        String cclass = intent.getStringExtra("class");
//        String race = intent.getStringExtra("type");



//        TextView classTxt = (TextView) findViewById(R.id.CharacterClass);
//        TextView raceTxt = (TextView) findViewById(R.id.CharacterRace);
//        TextView nameTxt = (TextView) findViewById(R.id.CharacterName);
//
//        nameTxt.setText(name);
//        raceTxt.setText(type);
//        classTxt.setText(class_message);

        String [] DD = {
                "Name  : " + ch.name,
                "Race  : " + ch.race,
                "Class : " + ch.cclass,
                "Level : " + ch.level,
                "Init  : " + ch.init,
                "Spd   : " + ch.spd,
                "Str   : " + ch.str,
                "Dex   : " + ch.dex,
                "Con   : " + ch.con,
                "Int   : " + ch.inl,
                "Wis   : " + ch.wis,
                "Cha   : " + ch.cha,
        };

        ListAdapter theAdapter = new ArrayAdapter<String>(this, R.layout.row_layout1, R.id.textView1, DD);

        ListView theListView  = (ListView) findViewById(R.id.theListView);

        theListView.setAdapter(theAdapter);

        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String DD_picked = "You selected " + String.valueOf(parent.getItemAtPosition(position));

                Toast.makeText(DisplayMessageActivity.this, DD_picked, Toast.LENGTH_SHORT).show();
            }
        });
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

}
