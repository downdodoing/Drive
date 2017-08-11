package com.cqut.publicmethod;

import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.configuration.ConfigurationF;
import com.cqut.http.appointList.AppointListE;
import com.cqut.http.changepsd.ChangePsdE;
import com.cqut.http.checkwallet.CheckWalletE;
import com.cqut.http.getUserData.GetUserDataMap;
import com.cqut.http.messagelist.MessageDetailE;
import com.cqut.http.messagelist.MessageListE;
import com.cqut.http.practicemoney.PracticeMoneyE;
import com.cqut.http.startpractice.StartPracticeE;
import com.example.drive.appoint.Appoint;
import com.example.drive.firstpage.FirstPage;
import com.example.drive.login.Login;
import com.example.drive.login.Login_;
import com.example.drive.mainPage.MainPage_;
import com.example.drive.message.Message;
import com.example.drive.message.messagedetail.MessageDetail;
import com.example.drive.my.CheckWallet;
import com.example.drive.my.PricticeMoney;
import com.example.drive.startPractice.StartPractice;

public class GetDataAfterInter {
    @SuppressWarnings("unchecked")
    @SuppressLint("DefaultLocale")
    public static void getDataAfterInter(String result, Activity context, String DATA, int whichAC) {
        switch (whichAC) {
        case 1:// 登录
            HashMap<String, Object> data = GetUserDataMap.toJSONArray(result);
            if(0 != data.size()){
	            if (!"SUCCESS".equals(data.get("code").toString().toUpperCase())) {
	                Toast.makeText(context, data.get("message") + " ", Toast.LENGTH_SHORT).show();
	            } else {
	                Intent intent = new Intent(context, MainPage_.class);
	                intent.putExtra(DATA, data);
	                context.startActivity(intent);
	            }
            }
            ((Login) context).halder.sendEmptyMessage(1);
            break;
        case 2:// 修改密码
            HashMap<String, String> data1 = ChangePsdE.changePsd(result);
            if(0 != data1.size()){
	            if ("loginSessionExpired".equals(data1.get("code"))) {
	                loginSessionExpired();
	            } else if (!"SUCCESS".equals(data1.get("code").toString().toUpperCase())) {
	
	                Toast.makeText(context, data1.get("message") + " ", Toast.LENGTH_SHORT).show();
	            } else {
	                Toast.makeText(context, data1.get("message") + " ", Toast.LENGTH_SHORT).show();
	                // 结束之前的所有Activity
	                intentToLogin(context);
	            }
            }
            break;
        case 3:// 我的钱包
            HashMap<String, String> data0 = CheckWalletE.checkWallet(result);
            if(0 != data0.size()){
	            if ("loginSessionExpired".equals(data0.get("code"))) {
	                loginSessionExpired();
	            } else if (!"SUCCESS".equals(data0.get("code").toString().toUpperCase())) {
	                Toast.makeText(context, data0.get("message") + " ", Toast.LENGTH_SHORT).show();
	            } else {
	                CheckWallet.data = CheckWalletE.checkWallet(result);
	                ((CheckWallet) context).handler.sendEmptyMessage(1);
	            }
            }
            ((CheckWallet) context).handler.sendEmptyMessage(2);
            break;
        case 4:// 我的单价
            HashMap<String, Object> data2 = PracticeMoneyE.practiceMoney(result);
            if(0 != data2.size()){
	            if ("loginSessionExpired".equals(data2.get("code"))) {
	                loginSessionExpired();
	            } else if (!"SUCCESS".equals(data2.get("code").toString().toUpperCase())) {
	                Toast.makeText(context, data2.get("message") + " ", Toast.LENGTH_SHORT).show();
	            } else {
	                PricticeMoney.data = data2;
	                ((PricticeMoney) context).handler.sendEmptyMessage(1);
	            }
            }
            ((PricticeMoney) context).handler.sendEmptyMessage(2);
            break;
        case 5:// 提交科目单价
            HashMap<String, Object> data3 = PracticeMoneyE.practiceMoney(result);
            if(0 != data3.size()){
	            if ("loginSessionExpired".equals(data3.get("code"))) {
	                loginSessionExpired();
	            } else {
	                Toast.makeText(context, data3.get("message") + " ", Toast.LENGTH_SHORT).show();
	            }
            }
            break;
        case 6:// 预约列表
            HashMap<String, Object> data4 = AppointListE.getAppointListData(result);
            if(0 != data4.size()){
	            if ("loginSessionExpired".equals(data4.get("code"))) {
	                loginSessionExpired();
	            } else {
	                if (!"SUCCESS".equals(data4.get("code").toString().toUpperCase())) {
	                    if (null != data4.get("message") && data4.get("message").equals("没有获取到数据")) {
	                        ((Appoint) context).handler.sendEmptyMessage(2);
	                    } else {
	                        Toast.makeText(context, data4.get("message") + " ", Toast.LENGTH_SHORT).show();
	                    }
	                }
	                Appoint.mData = (List<HashMap<String, String>>) data4.get("list");
	                ((Appoint) context).handler.sendEmptyMessage(1);
	            }
            }
            break;
        case 7:// 学员练车
            HashMap<String, String> data7 = StartPracticeE.starPracticeData(result);
            if(0 != data7.size()){
	            if ("loginSessionExpired".equals(data7.get("code"))) {
	                loginSessionExpired();
	            } else if (!"SUCCESS".equals(data7.get("code").toString().toUpperCase())) {
	                Toast.makeText(context, data7.get("message") + " ", Toast.LENGTH_SHORT).show();
	                if ("没有预约".equals(data7.get("message"))) {
	                    ((StartPractice) context).handler.sendEmptyMessage(3);
	                }
	            } else {
	                // TODO ERROR 业务错误， 和EXCEPTION 系统异常 分开处理
	                StartPractice.data = data7;
	                ((StartPractice) context).handler.sendEmptyMessage(1);
	            }
            }
            ((StartPractice) context).handler.sendEmptyMessage(2);
            break;
        case 8:// 消息列表
            HashMap<String, Object> data5 = MessageListE.getMessageList(result);
            if(0 != data5.size()){
	            if ("loginSessionExpired".equals(data5.get("code"))) {
	                loginSessionExpired();
	            } else if (!"SUCCESS".equals(data5.get("code").toString().toUpperCase())) {
	
	                if (null != data5.get("message") && data5.get("message").equals("没有获取到数据")) {
	                    ((Message) context).handler.sendEmptyMessage(3);
	                } else {
	                    Toast.makeText(context, data5.get("message") + " ", Toast.LENGTH_SHORT).show();
	                }
	
	            } else {
	                Message.data = (List<HashMap<String, String>>) data5.get("list");
	                ((Message) context).handler.sendEmptyMessage(1);
	            }
            }
            ((Message) context).handler.sendEmptyMessage(2);
            break;
        case 9:// 消息详情内容
            HashMap<String, String> data6 = MessageDetailE.getMessageDetail(result);
            if(0 != data6.size()){
	            if ("loginSessionExpired".equals(data6.get("code"))) {
	                loginSessionExpired();
	            } else if (!"SUCCESS".equals(data6.get("code").toString().toUpperCase())) {
	                Toast.makeText(context, data6.get("message") + " ", Toast.LENGTH_SHORT).show();
	            } else {
	                MessageDetail.data = data6;
	                ((MessageDetail) context).handler.sendEmptyMessage(1);
	            }
            }
            ((MessageDetail) context).handler.sendEmptyMessage(2);
            break;
        case 10:// 首页
            HashMap<String, Object> data8 = MessageListE.getMessageList(result);
            if(0 != data8.size()){
	            if ("loginSessionExpired".equals(data8.get("code"))) {
	                loginSessionExpired();
	            } else if (!"SUCCESS".equals(data8.get("code").toString().toUpperCase())) {
	
	                if (null != data8.get("message") && data8.get("message").equals("没有获取到数据")) {
	                    ((FirstPage) context).handler.sendEmptyMessage(3);
	                } else {
	                    Toast.makeText(context, data8.get("message") + " ", Toast.LENGTH_SHORT).show();
	                }
	
	            } else {
	                FirstPage.data = (List<HashMap<String, String>>) data8.get("list");
	                ((FirstPage) context).handler.sendEmptyMessage(1);
	            }
            }
            ((FirstPage) context).handler.sendEmptyMessage(2);
            break;
        default:
            break;
        }

    }

    public static void loginSessionExpired() {
        ConfigurationF.finishAcNotMainAc();
    }

    public static void intentToLogin(Activity context) {
        ConfigurationF.sp.edit().putBoolean("AUTO_ISCHECK", false).commit();
        ConfigurationF.sp.edit().putString("PASSWAR", "").commit();

        Intent intent = new Intent(context, Login_.class);
        context.startActivity(intent);
        ConfigurationF.finishAc();
    }
}
