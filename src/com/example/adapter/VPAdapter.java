package com.example.adapter;

import java.util.List;

import com.example.hztravelapp.MainActivity;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class VPAdapter extends PagerAdapter {
	
	List<ImageView> list;
	
	public VPAdapter(List<ImageView> imagesList) {
		// TODO Auto-generated constructor stub
		this.list=imagesList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0==arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		container.removeView(list.get(position));
	}

	/**
	 * 初始化
	 */
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		container.addView(list.get(position));	
		return list.get(position);
	}



}
