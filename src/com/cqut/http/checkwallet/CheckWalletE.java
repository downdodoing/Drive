package com.cqut.http.checkwallet;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

public class CheckWalletE {
	public static HashMap<String, String> checkWallet(String result) {
		HashMap<String, String> map = new HashMap<String, String>();

		try {
			JSONObject jooo = new JSONObject(result);
			try {
				String code = jooo.getString("code");
				map.put("code", code);
			} catch (Exception e) {
				map.put("code", "");
				e.printStackTrace();
			}
			try {
				String message = jooo.getString("message");
				map.put("message", message);
			} catch (Exception e) {
				map.put("message", "");
				e.printStackTrace();
			}
			try {
				JSONObject jooo1 = new JSONObject(jooo.getString("data"));
				getData(map, jooo1);
			} catch (Exception e) {
				map.put("totalMoney", "");
				map.put("freezeMoney", "");
				e.printStackTrace();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return map;
	}

	private static void getData(HashMap<String, String> map, JSONObject jooo) {
		try {
			String totalMoney = jooo.getString("totalMoney");
			map.put("totalMoney", totalMoney);
		} catch (Exception e) {
			map.put("totalMoney", "");
			e.printStackTrace();
		}

		try {
			String freezeMoney = jooo.getString("freezeMoney");
			map.put("freezeMoney", freezeMoney);
		} catch (Exception e) {
			map.put("freezeMoney", "");
			e.printStackTrace();
		}
	}
}
