package com.cqut.http.startpractice;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

public class StartPracticeE {
	public static HashMap<String, String> starPracticeData(String result) {
		HashMap<String, String> map = new HashMap<String, String>();

		try {
			JSONObject jooo = new JSONObject(result);
			try {
				String code = jooo.getString("code");
				map.put("code", code);
			} catch (Exception e) {
				map.put("code", "");
			}
			try {
				String message = jooo.getString("message");
				map.put("message", message);
			} catch (Exception e) {
				map.put("message", "");
			}
			try {
				JSONObject jooo1 = new JSONObject(jooo.getString("data"));
				try {
					String id = jooo1.getString("id");
					map.put("id", id);
				} catch (Exception e) {
					map.put("id", "");
				}
				try {
					String userName = jooo1.getString("userName");
					map.put("userName", userName);
				} catch (Exception e) {
					map.put("userName", "");
				}
				try {
					String dayDate = jooo1.getString("dayDate");
					map.put("dayDate", dayDate);
				} catch (Exception e) {
					map.put("dayDate", "");
				}
				try {
					String dayTime = jooo1.getString("dayTime");
					map.put("dayTime", dayTime);
				} catch (Exception e) {
					map.put("dayTime", "");
				}
				try {
					String price = jooo1.getString("price");
					map.put("price", Integer.parseInt(price) / 100 + "");
				} catch (Exception e) {
					map.put("price", "");
				}
				try {
					String sysCost = jooo1.getString("sysCost");
					map.put("sysCost", Integer.parseInt(sysCost) / 100 + "");
				} catch (Exception e) {
					map.put("sysCost", "");
				}
				try {
					String realCost = jooo1.getString("realCost");
					map.put("realCost", Integer.parseInt(realCost) / 100 + "");
				} catch (Exception e) {
					map.put("realCost", "");
				}
				try {
					String process = jooo1.getString("process");
					map.put("process", process);
				} catch (Exception e) {
					map.put("process", "");
				}
				try {
					String practiseStartTime = jooo1
							.getString("practiseStartTime");
					map.put("practiseStartTime", practiseStartTime);
				} catch (Exception e) {
					map.put("practiseStartTime", "");
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
