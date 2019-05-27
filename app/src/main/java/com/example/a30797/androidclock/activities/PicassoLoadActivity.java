package com.example.a30797.androidclock.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.a30797.androidclock.R;
import com.squareup.picasso.Picasso;

public class PicassoLoadActivity extends AppCompatActivity {

    private ImageView mPicassoImage;

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
        setContentView(R.layout.activity_picasso_load);

        iniViews();
        loadImage();
    }

    private void iniViews(){
        mPicassoImage = findViewById(R.id.picasso_image);

        mPicassoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImage();
                num %= 7;
            }
        });
    }

    private void loadImage(){
        num %= 7;
        Uri uri = Uri.parse("res://com.example.a30797.androidclock/" + imageIds[num++]);
        Picasso.get()
                .load(uri)
                .resize(300, 600)// 裁剪
                .rotate(50)// 旋转大小
                .into(mPicassoImage);
    }

    public static void anctionStart(AppCompatActivity activity){
        activity.startActivity(new Intent(activity, PicassoLoadActivity.class));
    }
}

