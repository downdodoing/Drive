package com.example.drive.my;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.configuration.ConfigurationF;
import com.cqut.entity.Coach;
import com.example.drive.R;
import com.example.drive.mainPage.MainPage;

@EActivity(R.layout.my)
@WindowFeature(Window.FEATURE_NO_TITLE)
public class My extends Activity {
	@Extra(MainPage.MCOACH)
	Coach mCoach;

	@ViewById(R.id.settting_name)
	TextView name;
	@ViewById(R.id.settting_count)
	TextView count;

	@ViewById(R.id.change_psd)
	RelativeLayout change_psd;

	public static final String COUNT = "count";

	@AfterViews
	public void initView() {
		ConfigurationF.CONTEXT_LIST.add(this);
		name.setText(mCoach.getName());
		count.setText(mCoach.getLoginAccount());
	}

	@Click({ R.id.change_psd, R.id.my_wallet, R.id.practice_price })
	public void click(View v) {
		switch (v.getId()) {
		case R.id.change_psd:
			Intent intent = new Intent(this, ChangePsd_.class);
			intent.putExtra(COUNT, mCoach.getLoginAccount());
			startActivity(intent);
			break;

		case R.id.my_wallet:
			Intent intent1 = new Intent(this, CheckWallet_.class);
			startActivity(intent1);
			break;
		case R.id.practice_price:
			Intent intent2 = new Intent(this, PricticeMoney_.class);
			startActivity(intent2);
			break;
		default:
			break;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}
}
