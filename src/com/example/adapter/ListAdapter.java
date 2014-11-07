package com.example.adapter;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.example.hztravelapp.R;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {
	
	private Context context;
	private List<Map<String, Object>> list;

	public ListAdapter(Context context, List<Map<String, Object>> list) {
		super();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView==null){
			convertView=LayoutInflater.from(context).inflate(R.layout.travel_items_layout, null);
			holder=new ViewHolder();
			holder.travel_imageview=(ImageView) convertView.findViewById(R.id.travel_imageview);
			holder.travel_title=(TextView) convertView.findViewById(R.id.travel_title);
			holder.travel_distance=(TextView) convertView.findViewById(R.id.travel_distance);
			holder.travel_summary=(TextView) convertView.findViewById(R.id.travel_summary);
			holder.travel_price=(TextView) convertView.findViewById(R.id.travel_price);
			holder.travel_oldprice=(TextView) convertView.findViewById(R.id.travel_oldprice);
			holder.travel_grade=(TextView) convertView.findViewById(R.id.travel_grade);			
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		
		holder.travel_imageview.setImageDrawable(context.getResources().getDrawable(R.drawable.xihu1));
		holder.travel_title.setText((String)list.get(position).get("travel_title"));
		holder.travel_distance.setText((String)list.get(position).get("travel_distance"));
		holder.travel_summary.setText((String)list.get(position).get("travel_summary"));
		holder.travel_price.setText(list.get(position).get("travel_price")+"");
		holder.travel_oldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		holder.travel_oldprice.setText((String)list.get(position).get("travel_oldprice"));
		holder.travel_grade.setText((String)list.get(position).get("travel_grade"));
		return convertView;
	}

	public class ViewHolder{
		ImageView travel_imageview;
		TextView travel_title;
		TextView travel_distance;
		TextView travel_summary;
		TextView travel_price;
		TextView travel_oldprice;
		TextView travel_grade;
	}
}
