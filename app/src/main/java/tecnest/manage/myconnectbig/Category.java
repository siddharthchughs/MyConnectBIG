package tecnest.manage.myconnectbig;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Category extends AppCompatActivity {

    private Intent in;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        in = getIntent();
        SharedPreferences shareAccountInformation = getSharedPreferences("State", Context.MODE_PRIVATE);
        String statename = shareAccountInformation.getString("name", "No value found").toString();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String nm = in.getStringExtra("name");

        getSupportActionBar().setTitle("Categories");
        getSupportActionBar().setSubtitle("State :" + statename);

        toolbar.setTitleTextColor(Color.WHITE);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_chevron_left);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}