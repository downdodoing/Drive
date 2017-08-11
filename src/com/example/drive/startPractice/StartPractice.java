package com.example.drive.startPractice;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.configuration.ConfigurationF;
import com.cqut.entity.Uri;
import com.cqut.http.GetData;
import com.example.drive.R;
import com.example.util.map.UtilAMap;
import com.example.util.map.UtilString;
import com.example.util.myService.MyService;
import com.example.util.refresh.RefreshableView;
import com.example.util.refresh.RefreshableView.RefreshListener;
import com.example.util.sliderBnt.SliderBnt;
import com.example.util.sliderBnt.MyInterface.SliderListener;
import com.example.util.timer.Timer;

@SuppressLint({ "HandlerLeak", "SimpleDateFormat" })
@EActivity(R.layout.start_practice)
@WindowFeature(Window.FEATURE_NO_TITLE)
public class StartPractice extends Activity implements RefreshListener {
	@ViewById(R.id.time)
	public static TextView time;

	public static String practiseStartTime;

	// private TimerUtil timer;

	@ViewById(R.id.leaner_name)
	TextView leaner_name;

	@ViewById(R.id.leaner_date)
	TextView leaner_date;

	@ViewById(R.id.leaner_time)
	TextView leaner_time;

	@ViewById(R.id.time_price_company)
	TextView time_price_company;

	@ViewById(R.id.total_spent)
	TextView total_spent;

	@ViewById(R.id.hint_text)
	TextView hint_text;

	@ViewById(R.id.sliderbnt_hint)
	TextView sliderbnt_hint;

	@ViewById(R.id.sliderbnt_hint1)
	TextView sliderbnt_hint1;

	@ViewById(R.id.practice_data)
	LinearLayout practice_data;

	@ViewById(R.id.sliderBnt_show)
	RelativeLayout sliderBnt_show;

	@ViewById(R.id.hint_show)
	RelativeLayout hint_show;

	@ViewById(R.id.hint_show1)
	RelativeLayout hint_show1;

	@ViewById(R.id.hint_show2)
	RelativeLayout hint_show2;

	// 第二次滑动
	@ViewById(R.id.sliderBnt_show1)
	RelativeLayout sliderBnt_show1;

	@ViewById(R.id.sliderBnt)
	SliderBnt sliderBnt;

	@ViewById(R.id.sliderBnt1)
	SliderBnt sliderBnt1;

	@ViewById(R.id.progress)
	LinearLayout mProgressBar;

	@ViewById(R.id.refresh_root)
	RefreshableView mRefreshableView;

	private UtilAMap utilAMap = null;

	public boolean isSlider1;

	public boolean isSlider2;

	public static HashMap<String, String> data;

	private float alpha;

	public Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (1 == msg.what) {
				if (null != data) {
					if (null == practiseStartTime) {
						practiseStartTime = data.get("practiseStartTime");
					}
					initTime();
					// 向右滑动开始练车
					if ("RES_USER_START".equals(data.get("process"))) {
						sliderBnt_show.setVisibility(View.VISIBLE);
						hint_show.setVisibility(View.GONE);
						sliderBnt_show1.setVisibility(View.GONE);
						hint_show1.setVisibility(View.GONE);
						hint_show2.setVisibility(View.GONE);
						setData();
						// 向右滑动结束练车
					} else if ("RES_STUDY_FINISH".equals(data.get("process"))) {
						setData();
						sliderBnt_show.setVisibility(View.GONE);
						hint_show.setVisibility(View.GONE);
						sliderBnt_show1.setVisibility(View.VISIBLE);
						hint_show1.setVisibility(View.GONE);
						hint_show2.setVisibility(View.GONE);
						startTimer();
					} else if ("RES_NEW".equals(data.get("process"))) {
						setData();
						hint_show.setVisibility(View.GONE);
						sliderBnt_show1.setVisibility(View.VISIBLE);
						hint_show1.setVisibility(View.VISIBLE);
						hint_show2.setVisibility(View.GONE);
						hint_text.setText("等待学员开始练车");
					} else if (isSlider2) {
						sliderBnt_show.setVisibility(View.GONE);
						hint_show.setVisibility(View.GONE);
						sliderBnt_show1.setVisibility(View.GONE);
						hint_show1.setVisibility(View.VISIBLE);
						hint_show2.setVisibility(View.GONE);
						hint_text.setText("本次练车已结束，请准备下一场练习");
						stopTimer();
						Toast.makeText(StartPractice.this,
								data.get("message").toString(),
								Toast.LENGTH_SHORT).show();
						/**
						 * 正在练车中，这样判断原因： （1）在本页面滑动按钮后进行数据刷新 （2）退出本页面后也能够数据刷新
						 */
					} else if (isSlider1
							|| "RES_STUDY".equals(data.get("process"))) {
						if (isSlider1) {
							Toast.makeText(StartPractice.this,
									data.get("message").toString(),
									Toast.LENGTH_SHORT).show();
						} else {
							setData();
						}
						sliderBnt_show.setVisibility(View.GONE);
						hint_show.setVisibility(View.VISIBLE);
						sliderBnt_show1.setVisibility(View.GONE);
						hint_show1.setVisibility(View.GONE);
						hint_show2.setVisibility(View.GONE);
						startTimer();
					}
				} else {
					practice_data.setVisibility(View.GONE);
				}
			} else if (2 == msg.what) {
				mProgressBar.setVisibility(View.GONE);
				mRefreshableView.finishRefresh();
			} else {
				practice_data.setVisibility(View.GONE);
				hint_show2.setVisibility(View.VISIBLE);
			}
		};
	};

	private void startTimer() {
		Intent intent = new Intent(StartPractice.this, MyService.class);
		startService(intent);
	}

	private void stopTimer() {
		Intent intent = new Intent(StartPractice.this, MyService.class);
		stopService(intent);
	}

	@SuppressWarnings("deprecation")
	public void initTime() {
		Calendar ca = Calendar.getInstance();
		long currentTotalTime = ca.get(Calendar.SECOND)
				+ ca.get(Calendar.MINUTE) * 60 + ca.get(Calendar.HOUR_OF_DAY)
				* 3600;
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");

		try {
			if (UtilString.notNull(practiseStartTime)) {
				Date date = df.parse(practiseStartTime);
				long startTotalTime = date.getSeconds() + date.getMinutes()
						* 60 + date.getHours() * 3600;
				int totalTime = (int) (currentTotalTime - startTotalTime);
				if (totalTime >= 0) {
					Timer.time = totalTime;
				} else {
					Timer.time = 0;
				}
			} else {
				Timer.time = 0;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	// 设置sliderBnt是否可见
	public void setData() {
		practice_data.setVisibility(View.VISIBLE);

		leaner_name.setText(data.get("userName"));
		leaner_date.setText(data.get("dayDate"));
		leaner_time.setText(data.get("dayTime"));
		time_price_company.setText(data.get("price"));
		total_spent.setText(data.get("sysCost"));
	}

	@AfterViews
	public void initView() {

		ConfigurationF.CONTEXT_LIST.add(this);
		mRefreshableView.setRefreshListener(this);

		mProgressBar.setVisibility(View.VISIBLE);
		setSliderBnt();
		getData(new HashMap<String, String>(),
				"coach/reservation/now-study.json");

		utilAMap = new UtilAMap();
		utilAMap.init(this);
	}

	public void getData(HashMap<String, String> params, String action) {
		final Uri uri = new Uri(ConfigurationF.URL, params, action);

		new Thread(new Runnable() {

			@Override
			public void run() {
				new GetData(StartPractice.this, "", 7).getData(uri);
			}
		}).start();
	}

	public void setSliderBnt() {
		sliderBnt.setSlideListener(new SliderListener() {

			@SuppressLint("NewApi")
			@Override
			public void slideListener(float x, int parentWith, int viewWidth) {
				alpha = 1 - x / (parentWith - viewWidth);
				sliderbnt_hint.setAlpha(alpha);
			}

			@Override
			public void slideSuccess() {
				isSlider1 = true;

				HashMap<String, String> params = new HashMap<String, String>();
				params.put("reservationId", data.get("id"));
				params.put("trackLon", utilAMap.getMapInfo().getLatitude() + "");// 纬度
				params.put("trackLat", utilAMap.getMapInfo().getLongitude()
						+ "");// 经度
				params.put("trackAddress", utilAMap.getMapInfo().getAddress());
				getData(params, "coach/reservation/process-start.json");
			}

			@SuppressLint("NewApi")
			@Override
			public void slideUp() {
				AlphaAnimation animation = new AlphaAnimation(alpha, (float) 1);
				animation.setDuration(300);
				animation.setFillAfter(true);
				sliderbnt_hint.startAnimation(animation);
				sliderbnt_hint.setAlpha(1);
			}
		});
		sliderBnt1.setSlideListener(new SliderListener() {

			@SuppressLint("NewApi")
			@Override
			public void slideUp() {
				AlphaAnimation animation = new AlphaAnimation(alpha, (float) 1);
				animation.setDuration(300);
				animation.setFillAfter(true);
				sliderbnt_hint1.startAnimation(animation);
				sliderbnt_hint1.setAlpha(1);
			}

			@Override
			public void slideSuccess() {
				isSlider2 = true;
				HashMap<String, String> params = new HashMap<String, String>();
				params.put("reservationId", data.get("id"));
				params.put("trackLon", utilAMap.getMapInfo().getLatitude() + "");// 纬度
				params.put("trackLat", utilAMap.getMapInfo().getLongitude()
						+ "");// 经度
				params.put("trackAddress", utilAMap.getMapInfo().getAddress());
				getData(params, "coach/reservation/process-finish.json");
			}

			@SuppressLint("NewApi")
			@Override
			public void slideListener(float x, int parentWith, int viewWidth) {
				alpha = 1 - x / (parentWith - viewWidth);
				sliderbnt_hint1.setAlpha(alpha);
			}
		});
	}

	@Click({ R.id.start_back })
	public void click(View v) {
		switch (v.getId()) {
		case R.id.start_back:
			isSlider1 = false;
			isSlider2 = false;
			finish();
			break;

		default:
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			isSlider1 = false;
			isSlider2 = false;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i("StartPractice", "onDestroy");
		stopTimer();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onRefresh(RefreshableView view) {
		isSlider1 = false;
		isSlider2 = false;
		getData(new HashMap<String, String>(),
				"coach/reservation/now-study.json");
	}
}
