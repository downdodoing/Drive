package com.cqut.http.changepsd;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

public class ChangePsdE {
	public static HashMap<String, String> changePsd(String result) {
		HashMap<String, String> map = new HashMap<String, String>();
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

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return map;

	}
}
