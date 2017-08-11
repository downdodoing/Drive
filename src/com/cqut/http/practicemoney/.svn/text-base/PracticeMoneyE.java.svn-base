package com.cqut.http.practicemoney;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cqut.entity.Subject;

public class PracticeMoneyE {
	public static HashMap<String, Object> practiceMoney(String result) {
		HashMap<String, Object> map = new HashMap<String, Object>();
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
				JSONArray jaaa = jooo1.getJSONArray("list");
				getSubjectPrice(jaaa, map);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return map;
	}

	public static void getSubjectPrice(JSONArray jaaa,
			HashMap<String, Object> map) {
		for (int i = 0; i < jaaa.length(); i++) {
			ArrayList<Subject> list = new ArrayList<Subject>();

			try {
				JSONObject jooo = (JSONObject) jaaa.get(i);
				String subject = "";
				try {
					subject = jooo.getString("subject");
				} catch (Exception e) {

				}
				String price = "";
				try {
					price = jooo.getString("price");
					map.put("price", Integer.parseInt(price) / 100 + "");
				} catch (Exception e) {
					map.put("price", "");
				}
				Subject su = new Subject(subject, Integer.parseInt(price) / 100
						+ "");
				list.add(su);
				map.put("list", list);
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
	}
}
