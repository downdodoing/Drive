//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.0.1.
//


package com.example.drive.appoint;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.drive.R.id;
import com.example.drive.R.layout;
import com.example.util.upgetmoredata.MyListView;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class Appoint_
    extends Appoint
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
        setContentView(layout.appoint);
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

    public static Appoint_.IntentBuilder_ intent(Context context) {
        return new Appoint_.IntentBuilder_(context);
    }

    public static Appoint_.IntentBuilder_ intent(android.app.Fragment fragment) {
        return new Appoint_.IntentBuilder_(fragment);
    }

    public static Appoint_.IntentBuilder_ intent(android.support.v4.app.Fragment supportFragment) {
        return new Appoint_.IntentBuilder_(supportFragment);
    }

    @Override
    public void onViewChanged(HasViews hasViews) {
        mProgressBar = ((LinearLayout) hasViews.findViewById(id.progress));
        list = ((MyListView) hasViews.findViewById(id.list));
        has_end_img = ((ImageView) hasViews.findViewById(id.has_end_img));
        has_ok_img = ((ImageView) hasViews.findViewById(id.has_ok_img));
        has_reject_img = ((ImageView) hasViews.findViewById(id.has_reject_img));
        appoint_new_img = ((ImageView) hasViews.findViewById(id.appoint_new_img));
        get_more = ((TextView) hasViews.findViewById(id.get_more));
        appoint_all_img = ((ImageView) hasViews.findViewById(id.appoint_all_img));
        back = ((ImageView) hasViews.findViewById(id.appoint_back));
        {
            View view = hasViews.findViewById(id.appoint_all);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        Appoint_.this.click(view);
                    }

                }
                );
            }
        }
        {
            View view = hasViews.findViewById(id.appoint_new);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        Appoint_.this.click(view);
                    }

                }
                );
            }
        }
        {
            View view = hasViews.findViewById(id.has_ok);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        Appoint_.this.click(view);
                    }

                }
                );
            }
        }
        {
            View view = hasViews.findViewById(id.has_end);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        Appoint_.this.click(view);
                    }

                }
                );
            }
        }
        {
            View view = hasViews.findViewById(id.has_reject);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        Appoint_.this.click(view);
                    }

                }
                );
            }
        }
        {
            View view = hasViews.findViewById(id.appoint_back);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        Appoint_.this.click(view);
                    }

                }
                );
            }
        }
        initView();
    }

    private void injectExtras_() {
        Bundle extras_ = getIntent().getExtras();
        if (extras_!= null) {
            if (extras_.containsKey(DATA_EXTRA)) {
                data = extras_.getString(DATA_EXTRA);
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
            intent_ = new Intent(context, Appoint_.class);
        }

        public IntentBuilder_(android.app.Fragment fragment) {
            fragment_ = fragment;
            context_ = fragment.getActivity();
            intent_ = new Intent(context_, Appoint_.class);
        }

        public IntentBuilder_(android.support.v4.app.Fragment fragment) {
            fragmentSupport_ = fragment;
            context_ = fragment.getActivity();
            intent_ = new Intent(context_, Appoint_.class);
        }

        public Intent get() {
            return intent_;
        }

        public Appoint_.IntentBuilder_ flags(int flags) {
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

        public Appoint_.IntentBuilder_ data(String data) {
            intent_.putExtra(DATA_EXTRA, data);
            return this;
        }

    }

}