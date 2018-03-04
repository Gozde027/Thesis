package com.smartlifecoach.itu.smartlifecoachforchildren;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Window;

import com.smartlifecoach.itu.smartlifecoachforchildren.LoginPack.LoginPages;
import com.smartlifecoach.itu.smartlifecoachforchildren.SignIn.SignInActivity;

public class SplashScreenActivity extends Activity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 1000;
    private final int SPLASH_DISPLAY_TIME = 2;

    private static String TAG = "SplashScreenActivity";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();

        final Boolean parent = sharedPreferences.contains(Defines.INSTINCE.PARENTSAVED);
        final Boolean child = sharedPreferences.contains(Defines.INSTINCE.CHILDSAVED);


        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent;
                /* Create an Intent that will start the Menu-Activity. */
                if(parent && child) {
                    mainIntent = new Intent(SplashScreenActivity.this, LoginPages.class);
                }
                else
                {
                    mainIntent = new Intent(SplashScreenActivity.this, SignInActivity.class);
                    if(parent)
                        mainIntent.putExtra(Defines.INSTINCE.SIGNIN,"parent");
                    else if(child)
                            mainIntent.putExtra(Defines.INSTINCE.SIGNIN,"child");
                        else
                            mainIntent.putExtra(Defines.INSTINCE.SIGNIN,"none");
                }
                SplashScreenActivity.this.startActivity(mainIntent);
                SplashScreenActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH * SPLASH_DISPLAY_TIME);
    }
}
