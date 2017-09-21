package tecnest.manage.myconnectbig;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash_screen);
   refreshUI();
    }
    private void refreshUI() {
        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {

//                                          if (mSession.getSkipped()) {
                                          Intent in = new Intent(SplashScreenActivity.this, LoginActivity.class);
                                          in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                          startActivity(in);
                                          //finish();
//                                          } else {
//                                              Intent in = new Intent(SpashScreenActivity.this, LoginActivity.class);
//                                              in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                                              startActivity(in);
//                                              // finish();
//                                          }
                                      }
                                  },

                SPLASH_DISPLAY_LENGTH);
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

}
