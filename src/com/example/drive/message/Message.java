package com.example.drive.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.configuration.ConfigurationF;
import com.cqut.entity.Uri;
import com.cqut.http.GetData;
import com.example.drive.R;
import com.example.drive.message.messageAdapter.MessageAdapter;
import com.example.drive.message.messagedetail.MessageDetail_;
import com.example.util.upgetmoredata.MyListView;
import com.example.util.upgetmoredata.myInterface.MyGetMoreListener;

@SuppressLint("HandlerLeak")
@EActivity(R.layout.message)
@WindowFeature(Window.FEATURE_NO_TITLE)
public class Message extends Activity {

	public static final String ID = "id";

	@ViewById(R.id.message_list)
	MyListView list;
	@ViewById(R.id.get_more)
	TextView get_more;

	@ViewById(R.id.progress)
	LinearLayout mProgressBar;
	// 页数
	private int pageNo = 1;
	// 一页的消息数
	private int pageSize = 10;
	private static final String PUBLISHTYPE = "publishType";
	public static List<HashMap<String, String>> data;
	private List<HashMap<String, String>> mData;

	private MessageAdapter adapter;

	public Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (1 == msg.what) {
				if (null == data) {
					data = new ArrayList<HashMap<String, String>>();
				}
				if (pageNo >= 2) {
					mData.addAll(data);
					adapter.notifyDataSetChanged();
				} else {
					// 用于保存原数据的地址，便于更新
					mData = data;
					adapter = new MessageAdapter(data, Message.this);
					list.setAdapter(adapter);
				}
			} else if (2 == msg.what) {
				mProgressBar.setVisibility(View.GONE);
				list.complementGetMore();
			} else {
				mProgressBar.setVisibility(View.GONE);
				list.complementGetMore();
				get_more.setText("没有更多数据");
			}
		};
	};

	@AfterViews
	public void initView() {
		ConfigurationF.CONTEXT_LIST.add(this);
		setList();

		mProgressBar.setVisibility(View.VISIBLE);
		getData();
	}

	// 获取后台数据
	public void getData() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("pageNo", pageNo + "");
		params.put("pageSize", pageSize + "");
		params.put(PUBLISHTYPE, "PUB_SCHOOL");

		final Uri uri = new Uri(ConfigurationF.URL, params,
				"school/message/list.json");

		new Thread(new Runnable() {

			@Override
			public void run() {
				new GetData(Message.this, "", 8).getData(uri);
			}
		}).start();
	}

	// 为list添加监听事件
	public void setList() {
		list.setGetMoreListener(new MyGetMoreListener() {

			@Override
			public void getMore() {
				new AsyncTask<Void, Void, String>() {

					@Override
					protected String doInBackground(Void... arg0) {
						return "";
					}

					@Override
					protected void onPostExecute(String result) {
						pageNo++;
						getData();
					}
				}.execute(null, null, null);
			}
		});
		// 判断是否滑动到了底部
		list.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
					// 判断是否滚动到底部
					if (view.getLastVisiblePosition() == view.getCount() - 1) {
						list.buttom();
						get_more.setText("正在加载....");
					}
				}

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
			}
		});

		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(Message.this, MessageDetail_.class);
				TextView v = (TextView) view.findViewById(R.id.item_msg_id);

				intent.putExtra(ID, v.getText().toString());
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
}
