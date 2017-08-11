package com.example.util.upgetmoredata;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.drive.R;
import com.example.util.upgetmoredata.myInterface.MyGetMoreListener;

public class MyListView extends ListView {

	private ProgressBar list_buttom_pgb;

	private MyGetMoreListener getMoreListener;

	public MyListView(Context context) {
		this(context, null);
	}

	public MyListView(Context context, AttributeSet attrs) {
		this(context, attrs, 1);
	}

	public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.setFocusable(false);
		init(context);
	}

	public void setGetMoreListener(MyGetMoreListener getMoreListener) {
		this.getMoreListener = getMoreListener;
	}

	public void init(Context context) {
		View v = LayoutInflater.from(context).inflate(
				R.layout.list_buttom_more, null);

		list_buttom_pgb = (ProgressBar) v.findViewById(R.id.list_buttom_pgb);

		addFooterView(v);
		v.setMinimumHeight(36);
	}

	public void buttom() {
		list_buttom_pgb.setVisibility(View.VISIBLE);
		getMoreData();
	}

	public void getMoreData() {
		if (null != getMoreListener) {
			getMoreListener.getMore();
		}
	}

	public void complementGetMore() {
		list_buttom_pgb.setVisibility(View.GONE);
	}

}
