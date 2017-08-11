package com.example.drive.message.messageAdapter;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.drive.R;

public class MessageAdapter extends BaseAdapter {
	private List<HashMap<String, String>> data;
	private Context context;

	public MessageAdapter(List<HashMap<String, String>> data, Context context) {
		this.data = data;
		this.context = context;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder = null;
		if (null == convertView) {
			holder = new Holder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.message_list_item, null);
			holder.item_msg = (TextView) convertView
					.findViewById(R.id.item_msg);
			holder.item_msg_id = (TextView) convertView
					.findViewById(R.id.item_msg_id);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		holder.item_msg.setText(data.get(position).get("title"));
		holder.item_msg_id.setText(data.get(position).get("id"));
		return convertView;
	}

	static class Holder {
		TextView item_msg;
		TextView item_msg_id;
	}
}
