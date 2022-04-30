package com.example.socialmediaapp;

import com.parse.Parse;
import com.parse.ParseObject;
import android.app.Application;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        String appId = BuildConfig.BACK4APP_APP_ID;
        String clientKey = BuildConfig.BACK4APP_CLIENT_KEY;
        String serverUrl = BuildConfig.BACK4APP_SERVER_URL;

        // register Parse models
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(appId)
                .clientKey(clientKey)
                .server(serverUrl)
                .build()
        );
    }
}
