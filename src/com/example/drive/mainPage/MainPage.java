package com.example.drive.mainPage;

import java.util.HashMap;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;

import android.annotation.SuppressLint;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.cqut.entity.Coach;
import com.example.drive.R;
import com.example.drive.appoint.Appoint_;
import com.example.drive.firstpage.FirstPage_;
import com.example.drive.login.Login;
import com.example.drive.message.Message_;
import com.example.drive.my.My_;

@SuppressWarnings("deprecation")
@EActivity(R.layout.main_page)
@WindowFeature(Window.FEATURE_NO_TITLE)
public class MainPage extends TabActivity implements OnClickListener {

	@ViewById(R.id.bar_index_btn)
	RelativeLayout bar_index_btn;
	@ViewById(R.id.bar_message_btn)
	RelativeLayout bar_message_btn;
	@ViewById(R.id.bar_reservation_btn)
	RelativeLayout bar_reservation_btn;
	@ViewById(R.id.bar_myself_btn)
	RelativeLayout bar_myself_btn;

	@ViewById(R.id.im_home)
	ImageView im_home;
	@ViewById(R.id.im_phone)
	ImageView im_phone;
	@ViewById(R.id.im_set)
	ImageView im_set;
	@ViewById(R.id.my_img)
	ImageView my_img;

	@ViewById(R.id.bar_index)
	TextView im_home_text;
	@ViewById(R.id.bar_message)
	TextView im_phone_text;
	@ViewById(R.id.bar_reservation)
	TextView im_set_text;
	@ViewById(R.id.bar_myself)
	TextView bar_myself;

	private TabHost tabHost;
	public static final String MCOACH = "mCoach";

	@Extra(Login.DATA)
	HashMap<String, Object> data;// 通过注解的方式获取上一页面传递的参数
	private Coach mCoach;

	@AfterViews
	public void initView() {
		bar_index_btn.setOnClickListener(this);
		bar_message_btn.setOnClickListener(this);
		bar_reservation_btn.setOnClickListener(this);
		bar_myself_btn.setOnClickListener(this);

		mCoach = (Coach) data.get("coach");

		tabHost = this.getTabHost();
		setTab();
	}

	public void setTab() {
		// 将登陆时从后台获取的数据传递到设置页面
		Intent intent_first = new Intent(this, FirstPage_.class);
		intent_first.putExtra(MCOACH, mCoach);
		TabSpec tabSpec1 = tabHost.newTabSpec("firstpage")
				.setIndicator("firstpage").setContent(intent_first);

		TabSpec tabSpec2 = tabHost.newTabSpec("message")
				.setIndicator("message")
				.setContent(new Intent(this, Message_.class));
		TabSpec tabSpec3 = tabHost.newTabSpec("appoint")
				.setIndicator("appoint")
				.setContent(new Intent(this, Appoint_.class));

		// 将登陆时从后台获取的数据传递到设置页面
		Intent intent_my = new Intent(this, My_.class);
		intent_my.putExtra(MCOACH, mCoach);
		TabSpec tabSpec4 = tabHost.newTabSpec("my").setIndicator("my")
				.setContent(intent_my);
		//设置点击底部导航按钮对应进入的页面
		tabHost.addTab(tabSpec1);
		tabHost.addTab(tabSpec2);
		tabHost.addTab(tabSpec3);
		tabHost.addTab(tabSpec4);
		// 将第一个页面设置为登陆成功后的显示的页面
		im_home.setImageResource(R.drawable.home_2);
		im_home_text.setTextColor(Color.parseColor("#FFC925"));
		tabHost.setCurrentTab(0);
	}

	@SuppressLint("ResourceAsColor")
	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.bar_index_btn:
			im_home.setImageResource(R.drawable.home_2);
			im_phone.setImageResource(R.drawable.phone_1);
			im_set.setImageResource(R.drawable.set_1);
			my_img.setImageResource(R.drawable.my_1);

			im_home_text.setTextColor(Color.parseColor("#FFC925"));
			im_phone_text.setTextColor(Color.parseColor("#ffffff"));
			im_set_text.setTextColor(Color.parseColor("#ffffff"));
			bar_myself.setTextColor(Color.parseColor("#ffffff"));

			tabHost.setCurrentTab(0);
			break;
		case R.id.bar_message_btn:
			im_home.setImageResource(R.drawable.home_1);
			im_phone.setImageResource(R.drawable.phone_2);
			im_set.setImageResource(R.drawable.set_1);
			my_img.setImageResource(R.drawable.my_1);

			im_home_text.setTextColor(Color.parseColor("#ffffff"));
			im_phone_text.setTextColor(Color.parseColor("#FFC925"));
			im_set_text.setTextColor(Color.parseColor("#ffffff"));
			im_set_text.setTextColor(Color.parseColor("#ffffff"));
			bar_myself.setTextColor(Color.parseColor("#ffffff"));

			tabHost.setCurrentTab(1);
			break;
		case R.id.bar_reservation_btn:
			im_home.setImageResource(R.drawable.home_1);
			im_phone.setImageResource(R.drawable.phone_1);
			im_set.setImageResource(R.drawable.set_2);
			my_img.setImageResource(R.drawable.my_1);

			im_home_text.setTextColor(Color.parseColor("#ffffff"));
			im_phone_text.setTextColor(Color.parseColor("#ffffff"));
			im_set_text.setTextColor(Color.parseColor("#FFC925"));
			bar_myself.setTextColor(Color.parseColor("#ffffff"));

			tabHost.setCurrentTab(2);
			break;
		case R.id.bar_myself_btn:
			im_home.setImageResource(R.drawable.home_1);
			im_phone.setImageResource(R.drawable.phone_1);
			im_set.setImageResource(R.drawable.set_1);
			my_img.setImageResource(R.drawable.my_2);

			im_home_text.setTextColor(Color.parseColor("#ffffff"));
			im_phone_text.setTextColor(Color.parseColor("#ffffff"));
			im_set_text.setTextColor(Color.parseColor("#ffffff"));
			bar_myself.setTextColor(Color.parseColor("#FFC925"));
			tabHost.setCurrentTab(3);
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
