package com.example.socialmediaapp;

import com.parse.Parse;
import com.parse.ParseObject;
import android.app.Application;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("OwGQOdSVMzaG1e7bve69awDumTXYttn8VDvGAeSo")
                .clientKey("9tiTj1ichJXW7DV4qzPeiz72pMW4qVeUdhXd7yIA")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
