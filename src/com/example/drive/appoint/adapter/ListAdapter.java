package com.example.drive.appoint.adapter;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.configuration.ConfigurationF;
import com.cqut.entity.Uri;
import com.cqut.http.GetData;
import com.example.drive.R;
import com.example.drive.appoint.Appoint;

public class ListAdapter extends BaseAdapter {

	private List<HashMap<String, String>> mData;
	private LayoutInflater inflater;
	private Activity context;

	public ListAdapter(List<HashMap<String, String>> mData, Activity context) {
		this.mData = mData;
		inflater = LayoutInflater.from(context);
		this.context = context;
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder = null;
		HashMap<String, String> map = mData.get(position);
		if (null == convertView) {
			holder = new Holder();
			// 根据不同的状态获取不同的converView
			if (map.get("status").equals("DEFAULT")) {
				convertView = inflater.inflate(
						R.layout.appoint_list_item_appoint_new, null);

				holder.which_subjec_agree = (TextView) convertView
						.findViewById(R.id.which_subjec_agree);
				holder.which_subjec_reject = (TextView) convertView
						.findViewById(R.id.which_subjec_reject);

				setOnclick(holder.which_subjec_agree,
						holder.which_subjec_reject, position);

			} else if (map.get("status").equals("EFFECTIVE")) {
				String process = map.get("process");
				if (process.equals("RES_NEW")) {
					convertView = inflater.inflate(
							R.layout.appoint_list_item_has_ok, null);
				} else if (process.equals("RES_FINISH")) {
					convertView = inflater.inflate(
							R.layout.appoint_list_item_has_end, null);
				} else {
					convertView = inflater.inflate(
							R.layout.appoint_list_item_wait_end, null);
				}
			} else if (map.get("status").equals("INVALID")) {
				convertView = inflater.inflate(
						R.layout.appoint_list_item_has_reject, null);
			}
			holder.leaner = (TextView) convertView.findViewById(R.id.leaner);
			holder.appoint_list_item_price_company = (TextView) convertView
					.findViewById(R.id.appoint_list_item_price_company);
			holder.appoint_list_item_practice_price = (TextView) convertView
					.findViewById(R.id.appoint_list_item_practice_price);
			holder.appoint_list_item_practice_time = (TextView) convertView
					.findViewById(R.id.appoint_list_item_practice_time);

			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}

		holder.leaner.setText(map.get("userName"));
		holder.appoint_list_item_price_company.setText("￥" + map.get("price"));
		holder.appoint_list_item_practice_price.setText("￥"
				+ map.get("sysCost"));
		holder.appoint_list_item_practice_time.setText(map.get("dayDate") + " "
				+ map.get("dayTime"));

		return convertView;
	}

	public void setOnclick(TextView agree, TextView reject, final int position) {
		agree.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog("同意预约", position, "EFFECTIVE");
			}
		});

		reject.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog("不同意预约", position, "INVALID");
			}
		});
	}

	public void updateData(String status, int position) {

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("reservationId", mData.get(position).get("id"));
		params.put("status", status);

		final Uri uri = new Uri(ConfigurationF.URL, params,
				"coach/reservation/audit.json");
		new Thread(new Runnable() {

			@Override
			public void run() {
				new GetData(context, "", 6).getData(uri);
			}
		}).start();
	}

	public void showDialog(String message, final int position, String status) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("提交后不能修改");
		builder.setMessage(message);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				updateData("EFFECTIVE", position);
				((Appoint) context).isClick = true;
				dialog.dismiss();
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();

	}

	static class Holder {
		// 存储学员姓名
		TextView leaner;
		// 存储公共部分数据
		TextView appoint_list_item_price_company,// 科目单价
				appoint_list_item_practice_price,// 练车费用
				appoint_list_item_practice_time;// 练车时间
		// 存储新申请数据
		TextView which_subjec_agree, which_subjec_reject;// 按钮

	}

}
