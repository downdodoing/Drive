package com.example.drive.appoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.configuration.ConfigurationF;
import com.cqut.entity.Uri;
import com.cqut.http.GetData;
import com.example.drive.R;
import com.example.drive.appoint.adapter.ListAdapter;
import com.example.drive.firstpage.FirstPage;
import com.example.util.upgetmoredata.MyListView;
import com.example.util.upgetmoredata.myInterface.MyGetMoreListener;

@SuppressLint("HandlerLeak")
@EActivity(R.layout.appoint)
@WindowFeature(Window.FEATURE_NO_TITLE)
public class Appoint extends Activity {

	@ViewById(R.id.get_more)
	TextView get_more;

	@ViewById(R.id.list)
	MyListView list;

	public static List<HashMap<String, String>> mData;
	private static ListAdapter adapter;

	private int page1 = 1;

	public boolean isClick;

	private int whichList = 1;

	private List<HashMap<String, String>> mData1;

	// 头部image
	@ViewById(R.id.appoint_all_img)
	ImageView appoint_all_img;
	@ViewById(R.id.appoint_new_img)
	ImageView appoint_new_img;
	@ViewById(R.id.has_ok_img)
	ImageView has_ok_img;
	@ViewById(R.id.has_end_img)
	ImageView has_end_img;
	@ViewById(R.id.has_reject_img)
	ImageView has_reject_img;

	@ViewById(R.id.appoint_back)
	ImageView back;

	@ViewById(R.id.progress)
	LinearLayout mProgressBar;
	@Extra(FirstPage.DATA)
	String data;

	public Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (1 == msg.what) {
				if (null == mData) {
					mData = new ArrayList<HashMap<String, String>>();
				}

				// 当点击同意或者拒绝按钮后能够刷新listview的数据
				if (isClick) {
					mData1.clear();
					mData1.addAll(mData);
					adapter.notifyDataSetChanged();
				} else {
					if (page1 >= 2) {
						mData1.addAll(mData);
						adapter.notifyDataSetChanged();
					} else {
						// 用于保存原数据的地址，便于更新
						mData1 = mData;
						adapter = new ListAdapter(mData, Appoint.this);
						list.setAdapter(adapter);
					}
				}
			} else {
				get_more.setText("没有更多数据");
			}
			list.complementGetMore();
			mProgressBar.setVisibility(View.GONE);
		};
	};

	// 设置list以适应不同Activity进入
	public void setListMargin() {
		LinearLayout.LayoutParams layout = (android.widget.LinearLayout.LayoutParams) list
				.getLayoutParams();
		if (null != data && data.equals("FirstPage")) {
			layout.setMargins(0, 0, 0, 10);
			back.setVisibility(View.VISIBLE);
		} else {
			layout.setMargins(0, 0, 0, 56);
			back.setVisibility(View.GONE);
		}
		list.setLayoutParams(layout);
	}

	@AfterViews
	public void initView() {
		mProgressBar.setVisibility(View.VISIBLE);
		setListMargin();

		ConfigurationF.CONTEXT_LIST.add(this);
		// 为list设置滚动监听以便在滚动到底部能够加载更多
		setList();
		getData("", "", page1);

		// 设置获取更多数据监听
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
						page1++;
						isClick = false;
						switch (whichList) {
						case 1:
							getData("", "", page1);
							break;
						case 2:
							getData("DEFAULT ", "RES_NEW", page1);
							break;
						case 3:
							getData("EFFECTIVE ", "RES_NEW", page1);
							break;
						case 4:
							getData("EFFECTIVE ", "RES_FINISH", page1);

							break;
						case 5:
							getData("INVALID ", "", page1);
							break;

						default:
							break;
						}

					}
				}.execute(null, null, null);
			}
		});
	}

	public void getData(String status, String process, int page) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("status", status);
		params.put("process", process);
		params.put("pageNo", page + " ");

		final Uri uri = new Uri(ConfigurationF.URL, params,
				"coach/reservation/list.json");

		new Thread(new Runnable() {

			@Override
			public void run() {
				new GetData(Appoint.this, "", 6).getData(uri);
			}
		}).start();

	}

	@Click({ R.id.appoint_all, R.id.appoint_new, R.id.has_ok, R.id.has_end,
			R.id.has_reject, R.id.appoint_back })
	public void click(View v) {
		isClick = false;
		mProgressBar.setVisibility(View.VISIBLE);
		switch (v.getId()) {
		case R.id.appoint_all:
			appoint_all_img.setImageResource(R.drawable.down_1);
			appoint_new_img.setImageResource(R.drawable.down);
			has_ok_img.setImageResource(R.drawable.down);
			has_end_img.setImageResource(R.drawable.down);
			has_reject_img.setImageResource(R.drawable.down);
			// 初始化变量
			page1 = 1;
			whichList = 1;
			mData1 = null;

			getData("", "", page1);
			break;
		case R.id.appoint_new:
			appoint_all_img.setImageResource(R.drawable.down);
			appoint_new_img.setImageResource(R.drawable.down_1);
			has_ok_img.setImageResource(R.drawable.down);
			has_end_img.setImageResource(R.drawable.down);
			has_reject_img.setImageResource(R.drawable.down);

			whichList = 2;
			mData1 = null;
			page1 = 1;

			getData("DEFAULT ", "RES_NEW", page1);
			break;
		case R.id.has_ok:
			appoint_new_img.setImageResource(R.drawable.down);
			appoint_all_img.setImageResource(R.drawable.down);
			has_ok_img.setImageResource(R.drawable.down_1);
			has_end_img.setImageResource(R.drawable.down);
			has_reject_img.setImageResource(R.drawable.down);

			whichList = 3;
			mData1 = null;
			page1 = 1;

			getData("EFFECTIVE ", "RES_NEW", page1);
			break;
		case R.id.has_end:
			appoint_new_img.setImageResource(R.drawable.down);
			appoint_all_img.setImageResource(R.drawable.down);
			has_ok_img.setImageResource(R.drawable.down);
			has_end_img.setImageResource(R.drawable.down_1);
			has_reject_img.setImageResource(R.drawable.down);

			whichList = 4;
			mData1 = null;
			page1 = 1;

			getData("EFFECTIVE ", "RES_FINISH", page1);
			break;
		case R.id.has_reject:
			appoint_new_img.setImageResource(R.drawable.down);
			appoint_all_img.setImageResource(R.drawable.down);
			has_ok_img.setImageResource(R.drawable.down);
			has_end_img.setImageResource(R.drawable.down);
			has_reject_img.setImageResource(R.drawable.down_1);

			whichList = 5;
			mData1 = null;
			page1 = 1;

			getData("INVALID ", "", page1);
			break;
		case R.id.appoint_back:
			finish();
			break;
		default:
			break;
		}
	}

	// 为list添加底部，以及添加滑动事件
	public void setList() {
		list.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
					// 判断是否滚动到底部
					if (view.getLastVisiblePosition() == view.getCount() - 1) {
						get_more.setText("正在加载....");
						list.buttom();
					}
				}

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
			}
		});

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
}
