package com.example.a30797.androidclock.image;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

public class BitmapWorkerTask {

//    private final WeakReference imageViewReference;
//    private int data = 0;
//
//    public BitmapWorkerTask(ImageView imageView) {
//        // 使用弱引用
//        imageViewReference = new WeakReference(imageView);
//    }
//
//    @Override
//    protected void onPostExecute(Object o) {
//        Bitmap bitmap = (Bitmap) o;
//        if (imageViewReference != null && bitmap != null) {
//            final ImageView imageView = (ImageView) imageViewReference.get();
//            if (imageView != null) {
//                imageView.setImageBitmap(bitmap);
//            }
//        }
//    }
//
//    // 在后台线程压缩图片
//    @Override
//    protected Object doInBackground(Object[] objects) {
//        return ImageManager.decodeSampledBitmapFromResource(getResources(), data, 100, 100));;
//    }
}
