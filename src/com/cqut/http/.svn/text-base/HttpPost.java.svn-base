package com.cqut.http;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import android.util.Log;

import com.configuration.ConfigurationF;

public class HttpPost {
	public static FinalHttp finalHttp = new FinalHttp();
	private AjaxParams params;
	private String actionName;

	public HttpPost(String actionName, AjaxParams params) {

		this.actionName = actionName;
		this.params = params;

		if (finalHttp == null) {
			finalHttp = new FinalHttp();
		}
		finalHttp.configTimeout(20 * 1000);
	}

	public void get(AjaxCallBack<String> ajaxCallBack) {
		Log.i("TAG", ConfigurationF.URL + actionName);
		finalHttp.post(ConfigurationF.URL + "/" + actionName, params,
				ajaxCallBack);
	}

	/**
	 * 网络请求，使用afinal框架
	 * 
	 * @author
	 * 
	 * @param ajaxCallBack
	 *            网络请求回调的接口实现
	 * */
	public void web(AjaxCallBack<String> ajaxCallBack) {
		finalHttp.post(ConfigurationF.URL + "/" + actionName, params,
				ajaxCallBack);
		/* System.out.println(AppConfig.ServerUrl + "/" + actionName); */
	}

}
