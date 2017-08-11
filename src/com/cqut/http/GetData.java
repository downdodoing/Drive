package com.cqut.http;

import java.util.Iterator;

import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Looper;
import android.util.Log;

import com.cqut.entity.Uri;
import com.cqut.publicmethod.GetDataAfterInter;

@SuppressLint("HandlerLeak")
public class GetData {
	private Activity context;
	private String DATA;
	private int whichAc;

	public GetData(Activity context, String DATA, int whichAc) {
		this.context = context;
		this.DATA = DATA;
		this.whichAc = whichAc;
	}

	public void getData(Uri uri) {
		AjaxParams params = new AjaxParams();

		// 获取参数
		Iterator<java.util.Map.Entry<String, String>> it = uri.getParams()
				.entrySet().iterator();
		while (it.hasNext()) {
			java.util.Map.Entry<String, String> entry = it.next();
			params.put(entry.getKey(), entry.getValue());
		}
		HttpPost hp = new HttpPost(uri.getAction(), params);
		Looper.prepare();
		hp.get(new AjaxCallBack<String>() {
			@Override
			public void onStart() {
				Log.i("TAG", "onstart");
				super.onStart();
			}

			@Override
			public void onSuccess(String result) {
				Log.i("TAG", "onSuccess");
				super.onSuccess(result);
				GetDataAfterInter.getDataAfterInter(result, context, DATA,
						whichAc);
			}

			@Override
			public void onFailure(Throwable t, int errorNo, String strMsg) {
				Log.i("TAG", "onFailure");
				//获取数据失败时去掉进度条
				GetDataAfterInter.getDataAfterInter("", context, DATA,
						whichAc);
				super.onFailure(t, errorNo, strMsg);
			}
		});
		Looper.loop();
	}
}
