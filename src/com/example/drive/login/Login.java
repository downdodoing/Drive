package com.example.drive.login;

import java.util.HashMap;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.configuration.ConfigurationF;
import com.cqut.entity.Uri;
import com.cqut.http.GetData;
import com.example.drive.R;
import com.example.util.myService.MyService;

@SuppressLint({ "HandlerLeak", "WorldReadableFiles" })
@EActivity(R.layout.log_in)
@WindowFeature(Window.FEATURE_NO_TITLE)
public class Login extends Activity {
	@ViewById(R.id.count)
	EditText count;
	@ViewById(R.id.psd)
	EditText psd;

	@ViewById(R.id.textView1)
	TextView txt;

	@ViewById(R.id.check)
	CheckBox check;

	@ViewById(R.id.log_in_bnt)
	RelativeLayout log_in_bnt;

	@ViewById(R.id.forget_psd)
	TextView forget_psd;

	@ViewById(R.id.progress)
	LinearLayout mProgressBar;

	private String count_tx;
	private String psd_tx;

	public static final String DATA = "data";

	// 用于存储ToggleButton的开关状态
	private boolean isOn;
	// 用于判断是否已经点击登录按钮
	private boolean isClick;
	// 用于连续按两次退出系统
	private long exitTime;

	public Handler halder = new Handler() {
		public void handleMessage(android.os.Message msg) {
			mProgressBar.setVisibility(View.GONE);
			isClick = false;
		};
	};

	@SuppressWarnings("deprecation")
	@AfterViews
	public void initView() {
		txt.setVisibility(View.GONE);
		ConfigurationF.CONTEXT_LIST.add(this);

		ConfigurationF.sp = getSharedPreferences("user",
				Context.MODE_WORLD_READABLE);
		isOn = ConfigurationF.sp.getBoolean("AUTO_ISCHECK", false);

		count_tx = ConfigurationF.sp.getString("USER_NAME", "");
		psd_tx = ConfigurationF.sp.getString("PASSWAR", "");

		count.setText(count_tx);
		psd.setText(psd_tx);

		if (isOn) {
			check.setChecked(true);
			mProgressBar.setVisibility(View.VISIBLE);
			// 自动登录
			if (!"".equals(count_tx) && !"".equals(psd_tx)) {
				getInterINfo();
			} else {
				Toast.makeText(this, "登录信息丢失，请重新输入", Toast.LENGTH_SHORT).show();
			}
		}
		count.setText("15025355345 ");
		psd.setText("851117");
		setListener();
		log_in();
	}

	@SuppressLint("CommitPrefEdits")
	public void log_in() {

		log_in_bnt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (!isClick && getInputData()) {
					// 通过SharedPreferences保存登录信息以便自动登录
					Editor editor = ConfigurationF.sp.edit();
					if (check.isChecked()) {
						editor.putString("PASSWAR", psd_tx);
					}
					editor.putString("USER_NAME", count_tx);
					editor.commit();
					isClick = true;
					if (8 == mProgressBar.getVisibility()) {
						getInterINfo();
					}
					mProgressBar.setVisibility(View.VISIBLE);
				}
			}
		});
	}

	private void getInterINfo() {
		HashMap<String, String> params1 = new HashMap<String, String>();
		params1.put("password", psd_tx);
		params1.put("account", count_tx);

		String url = ConfigurationF.URL;
		final Uri uri = new Uri(url, params1, "coach-login.json");
		// 请求网络数据需要子线程
		new Thread(new Runnable() {

			@SuppressLint("DefaultLocale")
			@Override
			public void run() {
				new GetData(Login.this, DATA, 1).getData(uri);
			}
		}).start();
	}

	private boolean getInputData() {
		count_tx = count.getText().toString();
		psd_tx = psd.getText().toString();

		if ("".equals(count_tx)) {
			Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
			return false;
		}
		if ("".equals(psd_tx)) {
			Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	public void setListener() {

		check.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 设置选中状态
				if (check.isChecked()) {
					ConfigurationF.sp.edit().putBoolean("AUTO_ISCHECK", true)
							.commit();
				} else {
					ConfigurationF.sp.edit().putBoolean("AUTO_ISCHECK", false)
							.commit();
				}
			}
		});
	}

	// 连续按两次退出系统
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if (System.currentTimeMillis() - exitTime > 2000) {
				Toast.makeText(this, "再按一次退出登录", Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				finish();
				Intent intent = new Intent(Login.this, MyService.class);
				stopService(intent);
				System.exit(0);
			}
		}
		return true;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
}
