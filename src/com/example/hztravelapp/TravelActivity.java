package com.example.hztravelapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.example.adapter.ListAdapter;
import com.example.utils.AsyncGetNetWorkData;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.text.format.DateUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class TravelActivity extends ActionBarActivity {
	
	private AsyncGetNetWorkData asyncGetNetWorkData;
	private String url="http://192.168.1.134:8080/HZTravelService/servlet/GetData";
	private Spinner mSpinner;
	private PullToRefreshListView mPullToRefreshListView;
	private List<Map<String, Object>> mList=new ArrayList<Map<String,Object>>();
	LinkedList<String> list=new LinkedList<String>();
	private String[] citys=new String[]{
			"全城",
			"热门商区",
			"惠城区",
			"惠阳区",
			"惠东区",
			"龙门县",
			"博罗县",
	};	
	private ArrayAdapter<String> adapter;
	private ListAdapter mListAdapter;
	private Map<String, Object> map;
	
	
	private Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 0x123:
				try {
					mList=asyncGetNetWorkData.get();
					Log.v("*****", mList.toString());
					mListAdapter=new ListAdapter(TravelActivity.this, mList);
					mPullToRefreshListView.setAdapter(mListAdapter);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			default:
				break;
			}
		}
		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.travel_layout);
		for (String city :citys){
			list.add(city);
		}
		
		mSpinner=(Spinner) findViewById(R.id.allcity);
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
		mSpinner.setAdapter(adapter);
		
		
		mPullToRefreshListView=(PullToRefreshListView) findViewById(R.id.pull_refresh_list);
//		mListAdapter=new ListAdapter(this, getData());
//		mPullToRefreshListView.setAdapter(mListAdapter);
		mPullToRefreshListView.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
						DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

				// Update the LastUpdatedLabel
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);

				// Do work to refresh the list here.
//				new GetDataTask().execute();
//				new Thread(new Runnable() {
//					
//					@Override
//					public void run() {
//						// TODO Auto-generated method stub
//						try {
//							Thread.sleep(3000);
//							Toast.makeText(TravelActivity.this, "No More Data!So 爱干啥啥！", 3000).show();
//							mPullToRefreshListView.onRefreshComplete();
//						} catch (Exception e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
//				}).start();
			}
		});
		asyncGetNetWorkData=new AsyncGetNetWorkData(url, handler,0x123);
		asyncGetNetWorkData.execute();
	}
	
	/*public List<Map<String, Object>> getData(){
		map = new HashMap<String, Object>();
		map.put("img", R.drawable.xihu1);
		map.put("title", "新银盏温泉团体门票");
		map.put("distance", "143km");
		map.put("summary", "[惠州等]新银盏温泉团体票1张，10人起订，80余中温泉泡池");
		map.put("price", "75");
		map.put("oldprice", "118元");
		map.put("grade", "4.9分");
		mList.add(map);
		
		map=new HashMap<String, Object>();
		map.put("img", R.drawable.xihu2);
		map.put("title", "龙门地派温泉票");
		map.put("distance", "95.5km");
		map.put("summary", "[龙门县]门票1张，节假日通用，可免费享用水果、饮料、点心等");
		map.put("price", "98");
		map.put("oldprice", "168元");
		map.put("grade", "4.5分");
		mList.add(map);
		
		map=new HashMap<String, Object>();
		map.put("img", R.drawable.xihu3);
		map.put("title", "永记生态园");
		map.put("distance", "29.0km");
		map.put("summary", "[平山]观光门票1张，置身田园风光，享受假日好心情");
		map.put("price", "40");
		map.put("oldprice", "60元");
		map.put("grade", "4.3分");
		mList.add(map);
						
		return mList;		
	}
	
	private class GetDataTask extends AsyncTask<Void, Void, Map<String, Object>> {

		@Override
		protected Map<String, Object> doInBackground(Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			}
			map=new HashMap<String, Object>();
			map.put("img", R.drawable.xihu4);
			map.put("title", "东江游");
			map.put("distance", "5.4km");
			map.put("summary", "[东平]换个角度看我们生活的城市！");
			map.put("price", "25");
			map.put("oldprice", "50元");
			map.put("grade", "4.5分");
			return map;
		}

		@Override
		protected void onPostExecute(Map<String, Object> result) {			
			mList.add(0, result);
			mListAdapter.notifyDataSetChanged();

			// Call onRefreshComplete when the list has been refreshed.
			mPullToRefreshListView.onRefreshComplete();

			super.onPostExecute(result);
		}
	}*/

}
