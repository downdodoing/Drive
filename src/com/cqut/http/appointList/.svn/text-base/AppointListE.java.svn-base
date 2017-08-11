package com.cqut.http.appointList;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AppointListE {
	public static HashMap<String, Object> getAppointListData(String result) {
		HashMap<String, Object> data = new HashMap<String, Object>();

		try {
			JSONObject jooo = new JSONObject(result);
			try {
				String code = jooo.getString("code");
				data.put("code", code);
			} catch (Exception e) {
				data.put("code", "");
			}
			try {
				String message = jooo.getString("message");
				data.put("message", message);
			} catch (Exception e) {
				data.put("message", "");
			}
			try {
				JSONObject jooo1 = new JSONObject(jooo.getString("data"));
				JSONArray jaaa = jooo1.getJSONArray("list");
				getData(data, jaaa);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return data;
	}

	// 获取返回数据中的JSONArray数据
	public static void getData(HashMap<String, Object> map, JSONArray jaaa) {

		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < jaaa.length(); i++) {
			HashMap<String, String> mHashPam = new HashMap<String, String>();
			try {
				JSONObject jooo = jaaa.getJSONObject(i);
				String id = "";
				try {
					id = jooo.getString("id");
					mHashPam.put("id", id);
				} catch (Exception e) {
					mHashPam.put("id", "");
				}
				String userName = "";
				try {
					userName = jooo.getString("userName");
					mHashPam.put("userName", userName);
				} catch (Exception e) {
					mHashPam.put("userName", "");
				}
				String subject = "";
				try {
					subject = jooo.getString("subject");
					mHashPam.put("subject", subject);
				} catch (Exception e) {
					mHashPam.put("subject", "");
				}
				String status = "";
				try {
					status = jooo.getString("status");
					mHashPam.put("status", status);
				} catch (Exception e) {
					mHashPam.put("status", "");
				}
				String dayDate = "";
				try {
					dayDate = jooo.getString("dayDate");
					mHashPam.put("dayDate", dayDate);
				} catch (Exception e) {
					mHashPam.put("dayDate", "");
				}
				String dayTime = "";
				try {
					dayTime = jooo.getString("dayTime");
					mHashPam.put("dayTime", dayTime);
				} catch (Exception e) {
					mHashPam.put("dayTime", "");
				}
				String price = "";
				try {
					price = jooo.getString("price");
					mHashPam.put("price", Integer.parseInt(price) / 100 + "");
				} catch (Exception e) {
					mHashPam.put("price", "");
				}
				String sysCost = "";
				try {
					sysCost = jooo.getString("sysCost");
					mHashPam.put("sysCost", Integer.parseInt(sysCost) / 100
							+ "");
				} catch (Exception e) {
					mHashPam.put("sysCost", "");
				}
				String realCost = "";
				try {
					realCost = jooo.getString("realCost");
					mHashPam.put("realCost", Integer.parseInt(realCost) / 100
							+ "");
				} catch (Exception e) {
					mHashPam.put("realCost", "");
				}
				String process = "";
				try {
					process = jooo.getString("process");
					mHashPam.put("process", process);
				} catch (Exception e) {
					mHashPam.put("process", "");
				}

				list.add(mHashPam);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		map.put("list", list);
	}
}
