package com.example.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.os.AsyncTask;
import android.os.Handler;

public class AsyncGetNetWorkData extends AsyncTask<Void, Void, List<Map<String,Object>>> {
	private String uri;
	private Handler handler;
	private GetNetData getNetData;
	private JsonParse jsonParse;
	private int msg_what;
	public AsyncGetNetWorkData(String uri, Handler handler, int msg_what) {
		super();
		this.uri = uri;
		this.handler = handler;
		this.msg_what=msg_what;
		getNetData=new GetNetData();
		jsonParse=new JsonParse();
	}

	@Override
	protected List<Map<String,Object>> doInBackground(Void... arg0) {
		// TODO Auto-generated method stub
		String netData=getNetData.getNetWorkData(uri);
		return jsonParse.ParseJsonData(netData);
	}

	@Override
	protected void onPostExecute(List<Map<String,Object>> result) {
		// TODO Auto-generated method stub
		handler.sendEmptyMessage(msg_what);
		super.onPostExecute(result);
	}

}
