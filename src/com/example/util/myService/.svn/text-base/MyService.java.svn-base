package com.example.util.myService;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.util.timer.Timer;

public class MyService extends Service {
	private IBinder binder = new LocalBind();
	private Timer t;

	@Override
	public void onCreate() {
		super.onCreate();
		t = Timer.getInstance();
		t.start();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		if (Timer.time >= 0) {
			Timer.setColor();
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		t.stop();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		t.stop();
		return super.onUnbind(intent);
	}

	class LocalBind extends Binder {

		public MyService getService() {
			return MyService.this;
		}
	}
}
