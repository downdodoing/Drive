package com.example.drive.my;

import java.util.HashMap;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.configuration.ConfigurationF;
import com.cqut.entity.Uri;
import com.cqut.http.GetData;
import com.example.drive.R;

@EActivity(R.layout.change_psd)
@WindowFeature(Window.FEATURE_NO_TITLE)
public class ChangePsd extends Activity {
	@ViewById(R.id.set_back)
	ImageView back;

	@ViewById(R.id.change_psd_count)
	EditText count;
	@ViewById(R.id.initial_psd)
	EditText initial_psd;
	@ViewById(R.id.new_psd)
	EditText new_psd;
	@ViewById(R.id.confirm)
	EditText confirm;

	@ViewById(R.id.set_Ok_bnt)
	RelativeLayout set_Ok_bnt;
	// 获取上一页面传递的参数
	@Extra(My.COUNT)
	String mCount;

	// 存储输入的数据
	String count_str;
	String init_psd_str;
	String new_psd_str;
	String confirm_str;

	@AfterViews
	public void initView() {
		ConfigurationF.CONTEXT_LIST.add(this);
		count.setText(mCount);
	}

	@Click({ R.id.set_Ok_bnt, R.id.set_back })
	public void click(View v) {
		switch (v.getId()) {
		case R.id.set_Ok_bnt:
			count_str = count.getText().toString();
			init_psd_str = initial_psd.getText().toString();
			new_psd_str = new_psd.getText().toString();
			confirm_str = confirm.getText().toString();

			if ("".equals(count_str)) {
				Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
			} else if ("".equals(init_psd_str) || init_psd_str.length() < 6) {
				Toast.makeText(this, "原密码最短需要6个字符", Toast.LENGTH_SHORT).show();
			} else if ("".equals(new_psd_str)) {
				Toast.makeText(this, "请输入新密码", Toast.LENGTH_SHORT).show();
			} else if ("".equals(confirm_str)) {
				Toast.makeText(this, "请再次输入新密码", Toast.LENGTH_SHORT).show();
			} else if (!new_psd_str.equals(confirm_str)) {
				Toast.makeText(this, "两次输入的密码不一样", Toast.LENGTH_SHORT).show();
			} else {
				showDialog();
			}
			break;

		case R.id.set_back:
			finish();
			break;
		default:
			break;
		}

	}

	public void showDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("提示");
		builder.setMessage("是否确认修改，修改后将会重新登录");
		builder.setPositiveButton("确认", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				updatePsd();
				dialog.dismiss();
			}
		});
		builder.setNegativeButton("取消", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	// 将数据发送到后台进行数据修改
	public void updatePsd() {
		HashMap<String, String> params1 = new HashMap<String, String>();
		params1.put("newPsw", new_psd_str);
		params1.put("oldPsw", init_psd_str);
		params1.put("account", count_str);
		String url = ConfigurationF.URL;
		final Uri uri = new Uri(url, params1, "coach-modifyPsw.json");
		// 请求网络数据需要子线程
		new Thread(new Runnable() {

			@SuppressLint("DefaultLocale")
			@Override
			public void run() {
				new GetData(ChangePsd.this, "", 2).getData(uri);
			}
		}).start();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
}
