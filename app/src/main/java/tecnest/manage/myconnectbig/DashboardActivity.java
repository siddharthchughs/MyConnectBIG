package tecnest.manage.myconnectbig;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import tecnest.manage.myconnectbig.Pojo.Dashboard;

public class DashboardActivity extends AppCompatActivity  implements DashboardActivityFragment.OnClick{
    private Intent enList;
    Toolbar toolbar;
    private Context context;
    private  CoordinatorLayout coordinatorLayout;
    View.OnClickListener mOnClickListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        toolbar.setLogo(R.mipmap.toolic_launcher);
         toolbar.setTitleMarginStart(12);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.findViewById(R.id.enlist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                         enList = new Intent(DashboardActivity.this,EnlistActivity.class);
                        startActivity(enList);
            }
        });
        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id
                .constraintLayout);
       // final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);




    }


//        @Override
//        public void onListItemClick(View v,int position) {
////            View contextView = findViewById(R.id.stateName);
//            final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id
//                    .constraintLayout);
//
//            Snackbar snackbar = Snackbar
//                    .make(coordinatorLayout, "Had a snack at Snackbar", Snackbar.LENGTH_LONG)
//                    .setAction("Undo", (View.OnClickListener) v);
//            snackbar.setActionTextColor(Color.RED);
//            View snackbarView = snackbar.getView();
//            snackbarView.setBackgroundColor(Color.DKGRAY);
//            TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//            textView.setTextColor(Color.YELLOW);
//            snackbar.show();
//        }


    @Override
    public void onItemClick(View v) {

        toolbar.findViewById(R.id.enlist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                                  enList = new Intent(DashboardActivity.this,EnlistActivity.class);
  //                             startActivity(enList);
                //            final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id
//                    .constraintLayout);

//            Snackbar snackbar = Snackbar
//                    .make(coordinatorLayout, "Had a snack at Snackbar", Snackbar.LENGTH_LONG).setAction(R.string.app_name,mOnClickListener);
//                    ;
//            snackbar.setActionTextColor(Color.RED);
//            View snackbarView = snackbar.getView();
//            snackbarView.setBackgroundColor(Color.DKGRAY);
//            TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//            textView.setTextColor(Color.YELLOW);
//            snackbar.show();

            }
        });


    }
}