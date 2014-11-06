package com.example.hztravelapp;

import java.util.ArrayList;
import java.util.List;

import com.example.adapter.VPAdapter;
import com.example.interfaces.SIVOnClickListener;
import com.example.view.ScaleImageView;
import com.example.view.SlidingMenu;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	
	private long firstTime;
	private ScaleImageView scaleImageView;
	private SlidingMenu slidingMenu;
	private ViewPager mViewPager;
	private int[] images=new int[]{
			R.drawable.home1,
			R.drawable.home2,
			R.drawable.home3,
			R.drawable.home4,
	};
	List<ImageView> imagesList=new ArrayList<ImageView>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		scaleImageView=(ScaleImageView) findViewById(R.id.siv1);
		mViewPager=(ViewPager) findViewById(R.id.viewpager);
		for (int i :images){
			ImageView imageView=new ImageView(this);
			imageView.setImageResource(i);
			imagesList.add(imageView);
		}
		VPAdapter adapter=new VPAdapter(imagesList);
		mViewPager.setAdapter(adapter);
		mViewPager.setOnPageChangeListener(null);
		scaleImageView.setOnSIVClick(new SIVOnClickListener() {
			
			@Override
			public void onclick() {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity.this, TravelActivity.class);
				startActivity(intent);
			}
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	/**
	 * 双击退出
	 */
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (System.currentTimeMillis()-firstTime<3000){
			finish();
		}else{
			firstTime=System.currentTimeMillis();
			Toast.makeText(this, "再按一次退出程序", 1000).show();
		}
	}
	
}
