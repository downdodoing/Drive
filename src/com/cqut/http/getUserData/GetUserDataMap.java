package com.cqut.http.getUserData;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.cqut.entity.Coach;

public class GetUserDataMap {

	public static HashMap<String, Object> toJSONArray(String result) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		JSONObject jooo;
		try {
			jooo = new JSONObject(result);
			try {
				String code = jooo.getString("code");
				map.put("code", code);
			} catch (JSONException e) {
				e.printStackTrace();
				map.put("code", "");
			}

			try {
				String message = jooo.getString("message");
				map.put("message", message);
			} catch (JSONException e) {
				e.printStackTrace();
				map.put("message", "");
			}
			JSONObject jooo1 = null;

			try {
				jooo1 = jooo.getJSONObject("data");
				Coach mCoach = getCoachData(jooo1);
				map.put("coach", mCoach);
			} catch (JSONException e) {
				map.put("coach", "");
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return map;

	}

	public static Coach getCoachData(JSONObject jooo) {
		String id = null;
		try {
			id = jooo.getString("id");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String name = null;
		try {
			name = jooo.getString("name");
		} catch (JSONException e) {
			e.printStackTrace();

		}
		String schoolId = null;
		try {
			schoolId = jooo.getString("schoolId");
		} catch (JSONException e) {
			e.printStackTrace();

		}
		String header = null;
		try {
			header = jooo.getString("header");
		} catch (JSONException e) {
			e.printStackTrace();

		}
		String sex = null;
		try {
			sex = jooo.getString("sex");
		} catch (JSONException e) {
			e.printStackTrace();

		}
		String loginAccount = null;
		try {
			loginAccount = jooo.getString("loginAccount");
		} catch (JSONException e) {
			e.printStackTrace();

		}
		String status = null;
		try {
			status = jooo.getString("status");
		} catch (JSONException e) {
			e.printStackTrace();

		}

		Coach mCoach = new Coach(id, name, schoolId, header, sex, loginAccount,
				status);

		return mCoach;

	}
}
