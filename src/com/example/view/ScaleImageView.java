package com.example.view;

import com.example.hztravelapp.R;
import com.example.interfaces.SIVOnClickListener;
import com.example.utils.BitmapUtils;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
/**
 * 自定义View--ScaleImageView
 * 点击具有缩放和指纹的效果的imageview
 * @author Joker_Ya
 *
 */
public class ScaleImageView extends ImageView {
	
	private SIVOnClickListener listener=null;
	
	//指纹
	private Bitmap fingerBitmap;
	//"NEW"图标
	private Bitmap label_new;
	//是否大图
	private boolean isBig;
	//自定义属性
	private int color;
	private float textsize;
	private boolean big;
	//private int home;
	private String text;
	//屏幕宽高
	private int screenW;
	private int screenH;
	//按下状态
	private int state;
	
	private int[] colors = { getResources().getColor(R.color.red),
			getResources().getColor(R.color.orange),
			getResources().getColor(R.color.blue),
			getResources().getColor(R.color.purple),
			getResources().getColor(R.color.air),
			getResources().getColor(R.color.texi),
			getResources().getColor(R.color.jingdian) };
	
	public ScaleImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public ScaleImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		//获得指纹图片的bitmap
		fingerBitmap = BitmapUtils.zoomImage(BitmapFactory.decodeResource(
				getResources(), R.drawable.fingerprint), 127, 127);
		label_new=BitmapFactory.decodeResource(getResources(), R.drawable.label_new);
		/*
		 * 获得自定义属性
		 */
		TypedArray mArray=context.obtainStyledAttributes(attrs, R.styleable.SIVattrs);
		color = mArray.getInt(R.styleable.SIVattrs_backcolor, 0);
		//字体大小，默认为24
		textsize = mArray.getDimension(R.styleable.SIVattrs_textSize, 24);
		//是否大图片，默认为真
		big = mArray.getBoolean(R.styleable.SIVattrs_big, true);
		//home = mArray.getInt(R.styleable.HomeButton_home, 0);
		text = mArray.getString(R.styleable.SIVattrs_text);
//		System.out.println("color:" + color + " textsize:" + textsize + " big:"
//				+ big + " home:" + home);
		screenW=((Activity)context).getWindow().getWindowManager().getDefaultDisplay().getWidth()/2-16;
		
		if (big){
			screenH=screenW;
		}else{
			screenH=screenW/2-4;
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		//super.onDraw(canvas);
		//得到图片资源
		BitmapDrawable bd=(BitmapDrawable) this.getDrawable();
		Bitmap resourceBitmap=bd.getBitmap();	
		
		canvas.drawColor(colors[color]);
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setTextSize(24);
		if (big){
			//矩阵
			Matrix matrix_big=new Matrix();
			matrix_big.postTranslate(this.getWidth()/2-resourceBitmap.getWidth()/2,
					this.getHeight()/2-resourceBitmap.getHeight()/2);
			//绘制上字体和图片
			//canvas.drawText(text, 10, 40, paint);
			//Log.v("+++++++", text);
			canvas.drawBitmap(resourceBitmap, matrix_big, paint);
		} else{
			//矩阵
			Matrix matrix_small=new Matrix();
			matrix_small.postTranslate(10, this.getHeight()/2-resourceBitmap.getHeight()/2);
			canvas.drawBitmap(resourceBitmap, matrix_small, paint);
		}
		
		if (state==1){
			/*
			 * 绘制上指纹
			 */
			Matrix matrix=new Matrix();
			matrix.postTranslate(this.getWidth()/2-fingerBitmap.getWidth()/2,
					this.getHeight()/2-fingerBitmap.getHeight()/2);
			canvas.drawText(text, 20, 40, paint);
			canvas.drawBitmap(fingerBitmap, matrix, paint);
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		// 重新设置屏幕大小
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		float start = 1.0f;
		float end = 0.95f;
		Animation scaleAnimation = new ScaleAnimation(start, end, start, end,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		Animation endAnimation = new ScaleAnimation(end, start, end, start,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		scaleAnimation.setDuration(200);
		scaleAnimation.setFillAfter(true);
		endAnimation.setDuration(200);
		endAnimation.setFillAfter(true);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			this.startAnimation(scaleAnimation);
			state = 1;
			//重绘
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			this.startAnimation(endAnimation);
			state = 0;
			invalidate();
			if(listener!=null){
				listener.onclick();
			}
			break;
		// 滑动出去不会调用action_up,调用action_cancel
		case MotionEvent.ACTION_CANCEL:
			this.startAnimation(endAnimation);
			state = 0;
			invalidate();
			break;

		default:
			break;
		}
		// 不返回true，Action_up就响应不了
		return true;
	}
	/**
	 * 加入响应事件
	 * @param listener
	 */
	public void setOnSIVClick(SIVOnClickListener listener){
		this.listener=listener;
	}
}
