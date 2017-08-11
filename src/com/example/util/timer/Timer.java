package com.example.util.timer;

import android.graphics.Color;
import android.os.Handler;
import android.util.Log;

import com.example.drive.startPractice.StartPractice;

public class Timer {
	private static Timer timer;
	private static boolean isContinue;// 用于消除计时
	public static int time;
	private Thread t;
	// 存储计时的时间
	private static int second;
	private static int minute;
	private static int hour;

	private static String second_;
	private static String minite_;
	private static String hour_;

	private Timer() {

	}

	private static Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			getFormatTime();
			if (isContinue) {
				StartPractice.time.setText(hour_ + ":" + minite_ + ":"
						+ second_);
			} else {
				time = 0;
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

	public static Timer getInstance() {
		if (null == timer) {
			timer = new Timer();
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
			setColor();
		} else {
			StartPractice.time.setTextColor(Color.parseColor("#ffffff"));
		}
		StartPractice.time.setText(hour_ + ":" + minite_ + ":" + second_);
		return timer;
	}

	public static void setColor() {
		StartPractice.time.setTextColor(Color.parseColor("#FFC925"));
	}

	public void start() {

		setColor();
		isContinue = true;
		t = new Thread(new Runnable() {
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
		t.start();
	}

	public void stop() {
		StartPractice.time.setTextColor(Color.parseColor("#ffffff"));
		isContinue = false;
		time = 0;
		timer = null;
		// 为了能够使上一个计时线程运行完
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
