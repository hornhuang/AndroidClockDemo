package com.example.a30797.androidclock;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }

}
