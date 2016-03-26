package com.example.rachel.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MyActivity.EXTRA_MESSAGE);
        String name = intent.getStringExtra("name");
        String class_message = intent.getStringExtra("class");
        String type = intent.getStringExtra("type");

        TextView textView1 = new TextView(this);
        textView1.setTextSize(40);
        textView1.setText(type);

        TextView textView2 = new TextView(this);
        textView2.setTextSize(40);
        textView2.setText(name);

        TextView textView3 = new TextView(this);
        textView3.setTextSize(40);
        textView3.setText(message);

        TextView textView4 = new TextView(this);
        textView4.setTextSize(40);
        textView4.setText(class_message);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.content);
        RelativeLayout.LayoutParams param1 = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        param1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

        layout.addView(textView4, param1);
        layout.addView(textView2, param1);
        layout.addView(textView3, param1);
        layout.addView(textView4, param1);

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
