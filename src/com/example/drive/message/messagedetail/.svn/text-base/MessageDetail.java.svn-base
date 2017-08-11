package com.example.drive.message.messagedetail;

import java.util.HashMap;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.configuration.ConfigurationF;
import com.cqut.entity.Uri;
import com.cqut.http.GetData;
import com.example.drive.R;
import com.example.drive.message.Message;

@SuppressLint("HandlerLeak")
@EActivity(R.layout.message_detail)
@WindowFeature(Window.FEATURE_NO_TITLE)
public class MessageDetail extends Activity {
	@ViewById(R.id.message_title)
	TextView message_title;
	@ViewById(R.id.message_time)
	TextView message_time;
	@ViewById(R.id.message_content)
	TextView message_content;

	@ViewById(R.id.progress)
	LinearLayout mProgressBar;

	@Extra(Message.ID)
	String id;

	public static HashMap<String, String> data;

	public Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (1 == msg.what) {
				if (null != data) {
					message_title.setText(data.get("title").toString());
					message_time.setText(data.get("createTime").toString());

					message_content.setText(Html.fromHtml(data.get("content")));
				}
			} else {
				mProgressBar.setVisibility(View.GONE);
			}
		};
	};

	@Click(R.id.message_detail_back)
	public void click() {
		finish();
	}

	@AfterViews
	public void initView() {
		mProgressBar.setVisibility(View.VISIBLE);
		getData();
	}

	public void getData() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("messageid", id);
		final Uri uri = new Uri(ConfigurationF.URL, params,
				"school/message/detail.json");
		new Thread(new Runnable() {

			@Override
			public void run() {
				new GetData(MessageDetail.this, "", 9).getData(uri);
			}
		}).start();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
}
