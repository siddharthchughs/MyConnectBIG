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

public class ListOfCompaniesActivity extends AppCompatActivity {

    private Intent inreceive;
    String heading=null;
    String subheading=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_companies);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences shareAccountInformation = getSharedPreferences("State", Context.MODE_PRIVATE);
        String statename = shareAccountInformation.getString("name", "No value found").toString();

        inreceive = getIntent();
//        String nm = inreceive.getStringExtra("name");
        //  heading = inreceive.getStringExtra("name");
//        String nm = inreceive.getStringExtra("name");

//        subheading = inreceive.getStringExtra("category_name");
        //  getSupportActionBar().setTitle(nm);
        getSupportActionBar().setSubtitle("State :"+statename);
        toolbar.setTitleTextColor(Color.WHITE);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_chevron_left);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
