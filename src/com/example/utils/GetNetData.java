package com.example.utils;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class GetNetData {
	public String getNetWorkData(String uri){
		String result="";
		try {
			HttpGet get=new HttpGet(uri);
			HttpClient client=new DefaultHttpClient();
			HttpResponse response=client.execute(get);
			if (response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				result=EntityUtils.toString(response.getEntity());
				result=result.trim();
				Log.v("++++", result);
			}
			return result;
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return null;
	}
}
