package com.example.view;

import com.example.hztravelapp.R;
import com.nineoldandroids.view.ViewHelper;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class SlidingMenu extends HorizontalScrollView {
	//屏幕宽度
	private int screenW;
	//菜单的宽度
	private int menuW;
	//菜单宽度的一半
	private int halfmenuW;
	//菜单右边的距离
	private int rightpadding;
	//是否显示
	private boolean isdisplay;
	//是否第一次加载
	private boolean isfirst;
	private ViewGroup mMenu;
	private ViewGroup content;
	
	
	public SlidingMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	public SlidingMenu(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public SlidingMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		screenW=((Activity)context).getWindowManager().getDefaultDisplay().getWidth();
		TypedArray mArray=context.obtainStyledAttributes(attrs, R.styleable.SMattrs);
		rightpadding=mArray.getInt(R.styleable.SMattrs_rightpadding, 100);
	}
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
		if (changed){
			//隐藏菜单
			this.scrollTo(menuW, 0);
			isfirst=true;
		}
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		if(!isfirst){
			LinearLayout layout=(LinearLayout) getChildAt(0);
			mMenu = (ViewGroup) layout.getChildAt(0);
			content = (ViewGroup) layout.getChildAt(1);
			
			menuW=screenW-rightpadding;
			halfmenuW=menuW/2;
			mMenu.getLayoutParams().width=menuW;
			content.getLayoutParams().width=screenW;
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		switch (ev.getAction()) {
		case MotionEvent.ACTION_UP:
			// Up时，进行判断，如果显示区域大于菜单宽度一半则完全显示，否则隐藏
			int scrollX=getScrollX();
			if (scrollX>halfmenuW){
				this.smoothScrollTo(menuW, 0);
				isdisplay=false;
			}else {
				this.smoothScrollTo(0, 0);
				isdisplay=true;
				return true;
			}
			break;

		default:
			break;
		}
		return super.onTouchEvent(ev);
	}
	/**
	 * 打开菜单
	 */
	public void openMenu()
	{
		if (isdisplay)
			return;
		this.smoothScrollTo(0, 0);
		isdisplay = true;
	}

	/**
	 * 关闭菜单
	 */
	public void closeMenu()
	{
		if (isdisplay)
		{
			this.smoothScrollTo(menuW, 0);
			isdisplay = false;
		}
	}

	/**
	 * 切换菜单状态
	 */
	public void toggle()
	{
		if (isdisplay)
		{
			closeMenu();
		} else
		{
			openMenu();
		}
	}
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		// TODO Auto-generated method stub
		super.onScrollChanged(l, t, oldl, oldt);
		float scale = l * 1.0f / menuW;
		float leftScale = 1 - 0.3f * scale;
		float rightScale = 0.8f + scale * 0.2f;
		
		ViewHelper.setScaleX(mMenu, leftScale);
		ViewHelper.setScaleY(mMenu, leftScale);
		ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
		ViewHelper.setTranslationX(mMenu, menuW * scale * 0.7f);

		ViewHelper.setPivotX(content, 0);
		ViewHelper.setPivotY(content, content.getHeight() / 2);
		ViewHelper.setScaleX(content, rightScale);
		ViewHelper.setScaleY(content, rightScale);
	}

}
