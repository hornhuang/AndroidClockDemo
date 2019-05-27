package com.example.a30797.androidclock.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.a30797.androidclock.R;

public class GlideLoadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_load);
    }

    public static void anctionStart(AppCompatActivity activity){
        activity.startActivity(new Intent(activity, GlideLoadActivity.class));
    }
}
