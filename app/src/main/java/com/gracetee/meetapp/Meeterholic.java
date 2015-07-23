package com.gracetee.meetapp;

import android.app.Application;

import com.gracetee.meetapp.Model.Event;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by Grace on 13/6/2015.
 */
public class Meeterholic extends Application {

    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Event.class);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "EV2EhFCDxC0KCStaex9aHtonpyFrymzLWtx9wG2x", "gTFSuR2qwRFJyPa2WOpI9UJNtk6OiAROdxy3k39Q");
        ParseUser.enableRevocableSessionInBackground();
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        ParseACL.setDefaultACL(defaultACL, true);
    }

}
