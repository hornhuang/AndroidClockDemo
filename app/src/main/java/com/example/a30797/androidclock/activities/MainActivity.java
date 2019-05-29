package com.example.a30797.androidclock.activities;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a30797.androidclock.R;
import com.example.a30797.androidclock.image.ImageManager;
import com.facebook.drawee.view.SimpleDraweeView;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int num = 1;         //num用于确定背景图
    private boolean flagI = true;//i 用于控制日期显隐
    private SimpleDraweeView imageView;
    private TextView textView;
    private TextView textViewDate;
    private FloatingActionButton fab;

    private boolean isFABOpen = true;
    private FloatingActionButton fab1,fab2,fab3, fab4;

    private int[] imageIds = new int[]{
            R.drawable.bac_1,
            R.drawable.bac_3,
            R.drawable.bac_2,
            R.drawable.bac_4,
            R.drawable.bac_5,
            R.drawable.bac_7,
            R.drawable.bac_6
    };

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");// HH:mm:ss
            //获取当前时间
            Date date = new Date(System.currentTimeMillis());
            textView.setText("" + simpleDateFormat.format(date));
            simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");// HH:mm:ss
            textViewDate.setText("" + simpleDateFormat.format(date));
            fab.setClickable(true);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        iniViews();
        transparency();//系统状态栏透明
        refreshTime();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    }

    private void iniViews(){
        textView = findViewById(R.id.txt);
        imageView = findViewById(R.id.background);
        textViewDate = findViewById(R.id.date);
        fab = findViewById(R.id.fab);
        fab1 = findViewById(R.id.fab1);
        fab2 = findViewById(R.id.fab2);
        fab3 = findViewById(R.id.fab3);
        fab4 = findViewById(R.id.fab4);

        textView.setOnClickListener(this);
        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);
        fab4.setOnClickListener(this);

        int textSise = getWindowManager().getDefaultDisplay().getWidth()/20;
        textView.setTextSize(adjustFontSize(textSise*10));

        Uri uri = Uri.parse("res://com.example.a30797.androidclock/" + imageIds[0]);
        imageView.setImageURI(uri);
    }

    // 事件刷新线程
    private void refreshTime(){
        new Thread(){//每秒更新时间
            @Override
            public void run() {
                while (true){
                    Message meg  = new Message();
                    handler.sendMessage(meg);
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    // 日期显隐点击事件
    public void show(View view){
        if(flagI) {
            textViewDate.setVisibility(View.GONE);
            flagI = false;
        }// 重新显现方法在背景按钮上
    }

    // 悬浮按钮 更换背景
    public void change(){
        Uri uri = Uri.parse("res://com.example.a30797.androidclock/" + imageIds[num++]);
        imageView.setImageURI(uri);
        num %= 7;
        textViewDate.setVisibility(View.VISIBLE);
        flagI = true;
        fab.setClickable(false);
    }

    // 设置系统菜单为透明
    private void transparency(){
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    // 更具屏幕配适字体
    public static int adjustFontSize(int screenWidth){
        if (screenWidth <= 240) { 		// 240X320 屏幕
            return 40;

        }else if (screenWidth <= 320){	// 320X480 屏幕

            return 56;

        }else if (screenWidth <= 480){	// 480X800 或 480X854 屏幕

            return 96;

        }else if (screenWidth <= 540){	// 540X960 屏幕

            return 104;

        }else if(screenWidth <= 800){	// 800X1280 屏幕

            return 120;

        }else{							// 大于 800X1280

            return 120;

        }
    }

    private void showFABMenu(){
        isFABOpen=true;
        fab.animate().rotation(180).setDuration(200);
        fab1.animate().translationY(-getResources().getDimension(R.dimen.standard_65)).setDuration(50);
        fab2.animate().translationY(-getResources().getDimension(R.dimen.standard_125)).setDuration(100);
        fab3.animate().translationY(-getResources().getDimension(R.dimen.standard_185)).setDuration(150);
        fab4.animate().translationY(-getResources().getDimension(R.dimen.standard_225)).setDuration(200);
    }

    private void closeFABMenu(){
        isFABOpen=false;
        fab.animate().rotation(0).setDuration(200);
        fab1.animate().translationY(0).setDuration(40);
        fab2.animate().translationY(0).setDuration(80);
        fab3.animate().translationY(0).setDuration(140);
        fab4.animate().translationY(0).setDuration(220);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt:
                if (flagI){
                    textView.setTextColor(getResources().getColor(R.color.colorTextWhite));
                    flagI = !flagI;
                }else {
                    textView.setTextColor(getResources().getColor(R.color.colorTextGray));
                    flagI = !flagI;
                }

                break;

            case R.id.fab:
                if (!isFABOpen){
                    showFABMenu();
                }else {
                    closeFABMenu();
                }
                break;

            case R.id.fab1:
                change();
                break;

            case R.id.fab2:
                GlideLoadActivity.anctionStart(MainActivity.this);
                break;

            case R.id.fab3:
                PicassoLoadActivity.anctionStart(MainActivity.this);
                break;

            case R.id.fab4:
                OriginalActivity.anctionStart(MainActivity.this);
                break;

            default:

                break;
        }
    }



}
