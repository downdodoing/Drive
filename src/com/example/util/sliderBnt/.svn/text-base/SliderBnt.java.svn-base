package com.example.util.sliderBnt;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.util.sliderBnt.MyInterface.SliderListener;

public class SliderBnt extends Button {

	// 存储手按下按钮后的X坐标
	private int downX;

	// 初始marginleft
	private int leftX;

	private SliderListener slideListener;

	@SuppressLint("NewApi")
	public SliderBnt(Context context) {
		this(context, null);
		leftX = (int) getX();
	}

	@SuppressLint("NewApi")
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	public SliderBnt(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setSlideListener(SliderListener slideListener) {
		this.slideListener = slideListener;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downX = (int) event.getX();
			break;
		case MotionEvent.ACTION_MOVE:
			handMoveEvent(event);
			break;
		case MotionEvent.ACTION_UP:
			handleUpEvent(event);
			break;
		case MotionEvent.ACTION_CANCEL:
			slideToRight();
			break;
		default:
			break;
		}
		return super.onTouchEvent(event);
	}

	@SuppressLint("NewApi")
	public void handMoveEvent(MotionEvent event) {

		int eventX = (int) event.getX();
		int mParentWdith = ((ViewGroup) getParent()).getWidth();
		int marginLeft = getLeft() + (eventX - downX);

		if (marginLeft < leftX) {
			marginLeft = leftX;
		} else if (marginLeft > mParentWdith - getWidth()) {
			marginLeft = mParentWdith - getWidth();
		}

		RelativeLayout.LayoutParams lp = (android.widget.RelativeLayout.LayoutParams) getLayoutParams();
		lp.leftMargin = marginLeft;
		setLayoutParams(lp);

		if (null != slideListener) {
			slideListener.slideListener(getX(), mParentWdith, getWidth());
		}

	}

	@SuppressLint("NewApi")
	public void handleUpEvent(MotionEvent event) {
		slideToRight();
	}
	
	public void slideToRight(){
		int mParentWdith = ((ViewGroup) getParent()).getWidth();
		Log.i("滑动", this.getLeft() + " " + mParentWdith + " " +getWidth());
		if (this.getLeft() >= mParentWdith - getWidth() - 10) {
			if (null != slideListener) {
				slideListener.slideSuccess();
			}
		} else {
			RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) getLayoutParams();
			lp.leftMargin = leftX;
			setLayoutParams(lp);
			TranslateAnimation animation = new TranslateAnimation(
					Animation.ABSOLUTE, getX(), Animation.ABSOLUTE, leftX,
					Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 0);
			animation.setDuration(300);
			animation.setFillAfter(true);
			startAnimation(animation);

			if (null != slideListener) {
				slideListener.slideUp();
			}
		}
	}

}
