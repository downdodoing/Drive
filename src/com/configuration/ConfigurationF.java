package com.configuration;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.HttpPost;

import android.app.Activity;
import android.content.SharedPreferences;

public class ConfigurationF {
	public static final String URL = "http://test.app.akaiche.com/";
	public static HttpPost httPost;
	// 用于存储所有的Activity便于在某一个时候全部结束
	public static final List<Activity> CONTEXT_LIST = new ArrayList<Activity>();
	public static final String LOGINSESSIONEXPIRED = "loginSessionExpired";

	public static SharedPreferences sp;

	public static void finishAc() {
		for (int i = 0; i < CONTEXT_LIST.size(); i++) {
			Activity av = CONTEXT_LIST.get(i);
			// 防止某些Activity被finish调而为空
			if (null != av) {
				av.finish();
			}
		}
	}

	public static void finishAcNotMainAc() {
		for (int i = 1; i < CONTEXT_LIST.size(); i++) {
			Activity av = CONTEXT_LIST.get(i);
			// 防止某些Activity被finish调而为空
			if (null != av) {
				av.finish();
			}
		}
	}
}
