package com.gracetee.meetapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.Gson;
import com.gracetee.meetapp.R;
import com.gracetee.meetapp.Utils.Const;
import com.parse.ParseUser;

public class SplashScreen extends Activity {

    //use the default shared preference file
    private SharedPreferences preferenceSettings;
    private SharedPreferences.Editor preferenceEditor;

    //constant for operating mode
    private static final int PREFERENCE_MODE_PRIVATE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    preferenceSettings = getSharedPreferences("LoginUser",PREFERENCE_MODE_PRIVATE);

                    if (preferenceSettings.contains("User")) {
                        String jsonFavorites = preferenceSettings.getString("User", null);
                        Gson gson = new Gson();
                        Const.user = gson.fromJson(jsonFavorites, ParseUser.class);
                        startActivity(new Intent(getApplicationContext(), ChatActivity.class));
                    } else {
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    }
                    finish();
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

    @Override
    public void onBackPressed() {
        System.exit(0);
    }

}