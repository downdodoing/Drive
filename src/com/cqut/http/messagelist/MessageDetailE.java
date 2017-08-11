package com.cqut.http.messagelist;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

public class MessageDetailE {
	public static HashMap<String, String> getMessageDetail(String result) {
		HashMap<String, String> map = new HashMap<String, String>();

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
				try {
					String createTime = jooo1.getString("createTime");
					map.put("createTime", createTime);
				} catch (Exception e) {
					map.put("createTime", "");
				}
				try {
					String title = jooo1.getString("title");
					map.put("title", title);
				} catch (Exception e) {
					map.put("title", "");
				}

				try {
					String content = jooo1.getString("content");
					map.put("content", content);
				} catch (Exception e) {
					map.put("content", "");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return map;
	}

}
