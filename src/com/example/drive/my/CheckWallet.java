package com.example.drive.my;

import java.util.HashMap;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.configuration.ConfigurationF;
import com.cqut.entity.Uri;
import com.cqut.http.GetData;
import com.example.drive.R;

@SuppressLint("HandlerLeak")
@EActivity(R.layout.check_wallet)
@WindowFeature(Window.FEATURE_NO_TITLE)
public class CheckWallet extends Activity {

	@ViewById(R.id.progress)
	LinearLayout mProgressBar;

	public static TextView total_money;
	// 冻结资金
	public static TextView not_your_money;
	public static HashMap<String, String> data;

	public Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (1 == msg.what) {
				total_money.setText(data.get("totalMoney") + "元");
				not_your_money.setText(data.get("freezeMoney") + "元");
			} else {
				mProgressBar.setVisibility(View.GONE);
			}
		};
	};

	@AfterViews
	public void initView() {
		mProgressBar.setVisibility(View.VISIBLE);
		total_money = (TextView) findViewById(R.id.total_money);
		not_your_money = (TextView) findViewById(R.id.not_your_money);

		HashMap<String, String> params1 = new HashMap<String, String>();

		String url = ConfigurationF.URL;
		final Uri uri = new Uri(url, params1, "coach/coach-finance.json");
		// 请求网络数据需要子线程
		new Thread(new Runnable() {

			@Override
			public void run() {
				new GetData(CheckWallet.this, "", 3).getData(uri);
			}
		}).start();
	}

	@Click({ R.id.wallet_back })
	public void click() {
		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
}
