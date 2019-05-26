package com.example.a30797.androidclock.image;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageManager {
    /**
     * 计算inSampleSize值
     *
     * @param options
     *          用于获取原图的长宽
     * @param reqWidth
     *          要求压缩后的图片宽度
     * @param reqHeight
     *          要求压缩后的图片长度
     * @return
     *          返回计算后的inSampleSize值
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // 原图片的宽高
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;


            // 计算inSampleSize值
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {
        // 先将inJustDecodeBounds设置为true来获取图片的长宽属性
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // 计算inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // 加载压缩版图片
        options.inJustDecodeBounds = false;
        // 根据具体情况选择具体的解码方法
        return BitmapFactory.decodeResource(res, resId, options);
    }
}
