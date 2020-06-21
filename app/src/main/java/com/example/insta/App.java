package com.example.insta;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("44z72KeEzqsTQI7T4LJ8bOL6QHGJieQDv4maM00s")
                .clientKey("JIkHzrCAn83QQIQ4YNeF7dbmiQnEebVUjfpos3Vd")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
