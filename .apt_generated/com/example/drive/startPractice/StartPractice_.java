//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.0.1.
//


package com.example.drive.startPractice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.drive.R.id;
import com.example.drive.R.layout;
import com.example.util.refresh.RefreshableView;
import com.example.util.sliderBnt.SliderBnt;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class StartPractice_
    extends StartPractice
    implements HasViews, OnViewChangedListener
{

    private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        OnViewChangedNotifier previousNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        OnViewChangedNotifier.replaceNotifier(previousNotifier);
        setContentView(layout.start_practice);
    }

    private void init_(Bundle savedInstanceState) {
        OnViewChangedNotifier.registerOnViewChangedListener(this);
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

    public static StartPractice_.IntentBuilder_ intent(Context context) {
        return new StartPractice_.IntentBuilder_(context);
    }

    public static StartPractice_.IntentBuilder_ intent(android.app.Fragment fragment) {
        return new StartPractice_.IntentBuilder_(fragment);
    }

    public static StartPractice_.IntentBuilder_ intent(android.support.v4.app.Fragment supportFragment) {
        return new StartPractice_.IntentBuilder_(supportFragment);
    }

    @Override
    public void onViewChanged(HasViews hasViews) {
        leaner_time = ((TextView) hasViews.findViewById(id.leaner_time));
        sliderBnt1 = ((SliderBnt) hasViews.findViewById(id.sliderBnt1));
        total_spent = ((TextView) hasViews.findViewById(id.total_spent));
        hint_text = ((TextView) hasViews.findViewById(id.hint_text));
        time = ((TextView) hasViews.findViewById(id.time));
        leaner_date = ((TextView) hasViews.findViewById(id.leaner_date));
        sliderBnt_show = ((RelativeLayout) hasViews.findViewById(id.sliderBnt_show));
        sliderbnt_hint1 = ((TextView) hasViews.findViewById(id.sliderbnt_hint1));
        mProgressBar = ((LinearLayout) hasViews.findViewById(id.progress));
        sliderbnt_hint = ((TextView) hasViews.findViewById(id.sliderbnt_hint));
        mRefreshableView = ((RefreshableView) hasViews.findViewById(id.refresh_root));
        hint_show2 = ((RelativeLayout) hasViews.findViewById(id.hint_show2));
        hint_show = ((RelativeLayout) hasViews.findViewById(id.hint_show));
        time_price_company = ((TextView) hasViews.findViewById(id.time_price_company));
        sliderBnt = ((SliderBnt) hasViews.findViewById(id.sliderBnt));
        sliderBnt_show1 = ((RelativeLayout) hasViews.findViewById(id.sliderBnt_show1));
        hint_show1 = ((RelativeLayout) hasViews.findViewById(id.hint_show1));
        leaner_name = ((TextView) hasViews.findViewById(id.leaner_name));
        practice_data = ((LinearLayout) hasViews.findViewById(id.practice_data));
        {
            View view = hasViews.findViewById(id.start_back);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        StartPractice_.this.click(view);
                    }

                }
                );
            }
        }
        initView();
    }

    public static class IntentBuilder_ {

        private Context context_;
        private final Intent intent_;
        private android.app.Fragment fragment_;
        private android.support.v4.app.Fragment fragmentSupport_;

        public IntentBuilder_(Context context) {
            context_ = context;
            intent_ = new Intent(context, StartPractice_.class);
        }

        public IntentBuilder_(android.app.Fragment fragment) {
            fragment_ = fragment;
            context_ = fragment.getActivity();
            intent_ = new Intent(context_, StartPractice_.class);
        }

        public IntentBuilder_(android.support.v4.app.Fragment fragment) {
            fragmentSupport_ = fragment;
            context_ = fragment.getActivity();
            intent_ = new Intent(context_, StartPractice_.class);
        }

        public Intent get() {
            return intent_;
        }

        public StartPractice_.IntentBuilder_ flags(int flags) {
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

    }

}