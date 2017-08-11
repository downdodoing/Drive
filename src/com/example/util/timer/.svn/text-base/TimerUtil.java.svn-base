package com.example.util.timer;

import java.util.TimerTask;

import android.graphics.Color;
import android.os.Handler;

import com.example.drive.startPractice.StartPractice;

public class TimerUtil {

    public static int Color_STUDYING = Color.parseColor("#FFC925");// 正在练车

    // public static int Color_MAIN_BACKGROUND = Color.parseColor("#F2F2F5");// 主题背景色 灰色
    public static TimerUtil timer;

    public static boolean isContinue;// 用于消除计时

    public static int time;

    private static Thread thread;

    // 存储计时的时间
    private static int second;

    private static int minute;

    private static int hour;

    private static String second_;

    private static String minite_;

    private static String hour_;

    TimerTask mTimerTask = null;

    private static Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {

            getFormatTime();
            if (isContinue) {
                StartPractice.time.setText(hour_ + ":" + minite_ + ":" + second_);
            } else {
                time = 0;
                if (timer != null) {
                    timer.stop();
                }
            }
        };
    };

    public static void getFormatTime() {
        second = time % 60;
        minute = time / 60 % 60;
        hour = time / 3600 % 24;

        second_ = second + "";
        minite_ = minute + "";
        hour_ = hour + "";

        if (second < 10) {
            second_ = "0" + second_;
        }
        if (minute < 10) {
            minite_ = "0" + minite_;
        }
        if (hour < 10) {
            hour_ = "0" + hour_;
        }
    }

    public static TimerUtil getInstance() {
        if (null == timer) {
            timer = new TimerUtil();
        }
        getFormatTime();
        // 填充计时的初始值
        if (0 == second) {
            second_ = "00";
        }
        if (0 == minute) {
            minite_ = "00";
        }
        if (0 == hour) {
            hour_ = "00";
        }
        if (0 != hour || 0 != minute || 0 != second) {
            StartPractice.time.setTextColor(Color_STUDYING);
        } else {
            StartPractice.time.setTextColor(Color.parseColor("#ffffff"));
        }
        StartPractice.time.setText(hour_ + ":" + minite_ + ":" + second_);
        return timer;
    }

    public void start() {
        // StartPractice.time.setTextColor(Color.parseColor("#E7E500"));
        StartPractice.time.setTextColor(Color_STUDYING);
        isContinue = true;
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isContinue) {
                    time++;
                    handler.sendEmptyMessage(1);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    public void stop() {
        StartPractice.time.setTextColor(Color.parseColor("#ffffff"));
        isContinue = false;
        time = 0;
        timer = null;
    }
}
