package com.cqut.http.messagelist;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MessageListE {
	public static HashMap<String, Object> getMessageList(String result) {
		HashMap<String, Object> map = new HashMap<String, Object>();

		try {
			JSONObject jooo = new JSONObject(result);

			try {
				String message = jooo.getString("message");
				map.put("message", message);
			} catch (Exception e) {
				map.put("message", "");
			}

			try {
				String code = jooo.getString("code");
				map.put("code", code);
			} catch (Exception e) {
				map.put("code", "");
			}

			try {
				JSONObject jooo1 = new JSONObject(jooo.getString("data"));
				JSONArray jaaa = jooo1.getJSONArray("list");
				getData(jaaa, map);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return map;
	}

	// 获取JSONArray里面的数据
	public static void getData(JSONArray jaaa, HashMap<String, Object> map) {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < jaaa.length(); i++) {
			HashMap<String, String> data = new HashMap<String, String>();
			try {
				JSONObject jooo = jaaa.getJSONObject(i);

				try {
					String id = jooo.getString("id");
					data.put("id", id);
				} catch (Exception e) {
					data.put("id", "");
				}
				try {
					String title = jooo.getString("title");
					data.put("title", title);
				} catch (Exception e) {
					data.put("title", "");
				}
				list.add(data);
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}

		map.put("list", list);
	}
}
