package com.example.drive.my;

import java.util.ArrayList;
import java.util.HashMap;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.configuration.ConfigurationF;
import com.cqut.entity.Subject;
import com.cqut.entity.Uri;
import com.cqut.http.GetData;
import com.example.drive.R;

@SuppressLint("HandlerLeak")
@EActivity(R.layout.practicemoney)
@WindowFeature(Window.FEATURE_NO_TITLE)
public class PricticeMoney extends Activity {

	private static EditText practice_2;
	private static EditText practice_3;

	@ViewById(R.id.progress)
	LinearLayout mProgressBar;

	public static HashMap<String, Object> data;
	// 存储后台数据库中的初始金额
	public static String p2_price;
	public static String p3_price;

	// 存储输入的金额
	private String input_p2_price;
	private String input_p3_price;

	public Handler handler = new Handler() {
		@SuppressWarnings("unchecked")
		public void handleMessage(android.os.Message msg) {
			if (1 == msg.what) {
				ArrayList<Subject> list = (ArrayList<Subject>) data.get("list");
				if (null != list) {
					for (int i = 0; i < list.size(); i++) {
						Subject su = list.get(i);
						if ("SUB_THREE".equals(su.getSubject())) {
							practice_3.setText(su.getPrice());
							p3_price = su.getPrice();
						} else if ("SUB_TWO".equals(su.getSubject())) {
							p2_price = su.getPrice();
							practice_2.setText(su.getPrice());
						}
					}
				}
			} else {
				mProgressBar.setVisibility(View.GONE);
			}
		};
	};

	@AfterViews
	public void initView() {
		mProgressBar.setVisibility(View.VISIBLE);
		practice_2 = (EditText) findViewById(R.id.practice_2);
		practice_3 = (EditText) findViewById(R.id.practice_3);

		final Uri uri = new Uri(ConfigurationF.URL,
				new HashMap<String, String>(), "coach/price/list.json");
		new Thread(new Runnable() {

			@Override
			public void run() {
				new GetData(PricticeMoney.this, "", 4).getData(uri);
			}
		}).start();
	}

	@Click({ R.id.practice_Ok_bnt, R.id.money_back })
	public void click(View v) {
		switch (v.getId()) {
		case R.id.practice_Ok_bnt:
			if (isInput()) {
				showDialog();
			}
			break;
		case R.id.money_back:
			finish();
			break;

		default:
			break;
		}
	}

	public void showDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("提示");
		builder.setMessage("是否提交修改？");
		builder.setPositiveButton("确认", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				updatePrice();
				dialog.dismiss();
			}
		});
		builder.setNegativeButton("取消", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	// 判断是否输入
	public boolean isInput() {
		input_p2_price = practice_2.getText().toString();
		input_p3_price = practice_3.getText().toString();

		if (input_p2_price.equals("")) {
			Toast.makeText(this, "请输入科目二单价", Toast.LENGTH_SHORT).show();
			return false;
		} else if (input_p3_price.equals("")) {
			Toast.makeText(this, "请输入科目三单价", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	// 提交更新后的金额
	public void updatePrice() {
		HashMap<String, String> params = new HashMap<String, String>();

		if (input_p2_price.equals(p2_price)) {
			params.put("SUB_TWO", "0");
		} else if (input_p3_price.equals(p3_price)) {
			params.put("SUB_TWO", "0");
			params.put("SUB_THREE", "0");
		} else {
			params.put("SUB_TWO", input_p2_price);
			params.put("SUB_THREE", input_p3_price);
		}

		final Uri uri = new Uri(ConfigurationF.URL, params,
				"coach/price/save.json");

		new Thread(new Runnable() {

			@Override
			public void run() {
				new GetData(PricticeMoney.this, "", 5).getData(uri);
			}
		}).start();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
}
