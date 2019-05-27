package com.example.a30797.androidclock.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.a30797.androidclock.R;

public class GlideLoadActivity extends AppCompatActivity {

    private ImageView mGlideImage;
    private ImageView mGlideGifImage;

    private int num = 0;
    private int[] imageIds = new int[]{
            R.drawable.bac_1,
            R.drawable.bac_3,
            R.drawable.bac_2,
            R.drawable.bac_4,
            R.drawable.bac_5,
            R.drawable.bac_7,
            R.drawable.bac_6
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_load);

        iniViews();
        iniGifs();
    }

    private void iniViews(){
        mGlideImage = findViewById(R.id.normal_image);
        mGlideGifImage = findViewById(R.id.gif_image);

        mGlideImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num %= 7;
                Glide.with(getApplicationContext())
                        .load(imageIds[num++])
                        .placeholder(R.drawable.bac_1)
                        .error(R.drawable.app_icon)
                        .into(mGlideImage);
            }
        });
    }

    private void iniGifs(){
        Glide.with(getApplicationContext())
                .load(R.drawable.gif_text)
                .asGif()
                .error(R.drawable.app_icon)
                .placeholder(R.drawable.bac_1)
                .into(mGlideGifImage);
    }

    public static void anctionStart(AppCompatActivity activity){
        activity.startActivity(new Intent(activity, GlideLoadActivity.class));
    }
}
