//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.0.1.
//


package com.example.drive.mainPage;

import java.io.Serializable;
import java.util.HashMap;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.drive.R.id;
import com.example.drive.R.layout;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class MainPage_
    extends MainPage
    implements HasViews, OnViewChangedListener
{

    private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
    public final static String DATA_EXTRA = "data";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        OnViewChangedNotifier previousNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        OnViewChangedNotifier.replaceNotifier(previousNotifier);
        setContentView(layout.main_page);
    }

    private void init_(Bundle savedInstanceState) {
        OnViewChangedNotifier.registerOnViewChangedListener(this);
        injectExtras_();
        requestWindowFeature(1);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
        super.setContentView(view, params);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    public static MainPage_.IntentBuilder_ intent(Context context) {
        return new MainPage_.IntentBuilder_(context);
    }

    public static MainPage_.IntentBuilder_ intent(android.app.Fragment fragment) {
        return new MainPage_.IntentBuilder_(fragment);
    }

    public static MainPage_.IntentBuilder_ intent(android.support.v4.app.Fragment supportFragment) {
        return new MainPage_.IntentBuilder_(supportFragment);
    }

    @Override
    public void onViewChanged(HasViews hasViews) {
        bar_index_btn = ((RelativeLayout) hasViews.findViewById(id.bar_index_btn));
        bar_message_btn = ((RelativeLayout) hasViews.findViewById(id.bar_message_btn));
        bar_reservation_btn = ((RelativeLayout) hasViews.findViewById(id.bar_reservation_btn));
        my_img = ((ImageView) hasViews.findViewById(id.my_img));
        im_home = ((ImageView) hasViews.findViewById(id.im_home));
        im_home_text = ((TextView) hasViews.findViewById(id.bar_index));
        im_phone = ((ImageView) hasViews.findViewById(id.im_phone));
        im_set_text = ((TextView) hasViews.findViewById(id.bar_reservation));
        bar_myself = ((TextView) hasViews.findViewById(id.bar_myself));
        bar_myself_btn = ((RelativeLayout) hasViews.findViewById(id.bar_myself_btn));
        im_set = ((ImageView) hasViews.findViewById(id.im_set));
        im_phone_text = ((TextView) hasViews.findViewById(id.bar_message));
        initView();
    }

    @SuppressWarnings("unchecked")
    private void injectExtras_() {
        Bundle extras_ = getIntent().getExtras();
        if (extras_!= null) {
            if (extras_.containsKey(DATA_EXTRA)) {
                data = ((HashMap<String, Object> ) extras_.getSerializable(DATA_EXTRA));
            }
        }
    }

    @Override
    public void setIntent(Intent newIntent) {
        super.setIntent(newIntent);
        injectExtras_();
    }

    public static class IntentBuilder_ {

        private Context context_;
        private final Intent intent_;
        private android.app.Fragment fragment_;
        private android.support.v4.app.Fragment fragmentSupport_;

        public IntentBuilder_(Context context) {
            context_ = context;
            intent_ = new Intent(context, MainPage_.class);
        }

        public IntentBuilder_(android.app.Fragment fragment) {
            fragment_ = fragment;
            context_ = fragment.getActivity();
            intent_ = new Intent(context_, MainPage_.class);
        }

        public IntentBuilder_(android.support.v4.app.Fragment fragment) {
            fragmentSupport_ = fragment;
            context_ = fragment.getActivity();
            intent_ = new Intent(context_, MainPage_.class);
        }

        public Intent get() {
            return intent_;
        }

        public MainPage_.IntentBuilder_ flags(int flags) {
            intent_.setFlags(flags);
            return this;
        }

        public void start() {
            context_.startActivity(intent_);
        }

        public void startForResult(int requestCode) {
            if (fragmentSupport_!= null) {
                fragmentSupport_.startActivityForResult(intent_, requestCode);
            } else {
                if (fragment_!= null) {
                    fragment_.startActivityForResult(intent_, requestCode);
                } else {
                    if (context_ instanceof Activity) {
                        ((Activity) context_).startActivityForResult(intent_, requestCode);
                    } else {
                        context_.startActivity(intent_);
                    }
                }
            }
        }

        public MainPage_.IntentBuilder_ data(HashMap<String, Object> data) {
            intent_.putExtra(DATA_EXTRA, ((Serializable) data));
            return this;
        }

    }

}
