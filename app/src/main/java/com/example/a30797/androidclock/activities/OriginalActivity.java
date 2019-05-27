package com.example.a30797.androidclock.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.a30797.androidclock.R;
import com.example.a30797.androidclock.image.ImageManager;
import com.facebook.drawee.view.SimpleDraweeView;

import java.lang.ref.WeakReference;

public class OriginalActivity extends AppCompatActivity {

    private int num = 0;

    private BitmapWorkerTask task;
    private ImageView imageView;

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
        setContentView(R.layout.activity_original);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        iniViews();
    }

    private void iniViews(){
        imageView = findViewById(R.id.image);
    }

    public void change(View view){
        task = new BitmapWorkerTask(imageView, 1000);
        task.execute(imageIds[num++]);
    }

    public static void anctionStart(AppCompatActivity activity){
        activity.startActivity(new Intent(activity, OriginalActivity.class));
    }

    // 将加载过程放到后台执行
    class BitmapWorkerTask extends AsyncTask {

        private final WeakReference imageViewReference;
        private int data = 0;
        private int width;
        private Bitmap bitmap;

        public BitmapWorkerTask(ImageView imageView, int width) {
            // 使用弱引用
            imageViewReference = new WeakReference(imageView);
            this.width = width;
        }

        @Override
        protected void onPostExecute(Object o) {
            bitmap = (Bitmap) o;
            if (imageViewReference != null && bitmap != null) {
                final ImageView imageView = (ImageView) imageViewReference.get();
                if (imageView != null) {
                    //加载本地图片              "res://包名/"+R.mipmap.图片id
                    imageView.setImageBitmap(bitmap);
                }
            }
        }

        // 在后台线程压缩图片
        @Override
        protected Object doInBackground(Object[] objects) {
            data = (int) objects[0];
            if ( bitmap!=null && !bitmap.isRecycled() ){
                bitmap.recycle();
            }
            bitmap = ImageManager.decodeSampledBitmapFromResource(getResources(), data, width, width/2 + 10);
            return bitmap;
        }
    }
}
