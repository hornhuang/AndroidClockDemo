package com.example.a30797.androidclock;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private int[] imageIds = new int[]{
            R.drawable.bac_1,
            R.drawable.bac_2,
            R.drawable.bac_3,
            R.drawable.bac_4
    };
    private int num = 1;         //num用于确定背景图
    private boolean flagI = true;//i 用于控制日期显隐
    private ImageView imageView;
    private TextView textView;
    private TextView textViewDate;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");// HH:mm:ss
            //获取当前时间
            Date date = new Date(System.currentTimeMillis());
            textView.setText("" + simpleDateFormat.format(date));
            simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");// HH:mm:ss
            textViewDate.setText("" + simpleDateFormat.format(date));
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        transparency();//系统状态栏透明
        textView = (TextView) findViewById(R.id.txt);
        imageView = (ImageView) findViewById(R.id.background);
        textViewDate = (TextView) findViewById(R.id.date);
        refreshTime();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
    //事件刷新线程
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
    //日期显隐点击事件
    public void show(View view){
        if(flagI) {
            textViewDate.setVisibility(View.GONE);
            flagI = false;
        }//重新显现方法在背景按钮上
    }
    //悬浮按钮 更换背景
    public void change(View view){
        imageView.setImageResource(imageIds[num++]);
        num %= 4;
        textViewDate.setVisibility(View.VISIBLE);
        flagI = true;
    }
    //设置系统菜单为透明
    private void transparency(){
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }
}