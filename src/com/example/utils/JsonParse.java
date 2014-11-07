package com.example.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

public class JsonParse {
	public List<Map<String,Object>> ParseJsonData(String data){
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		Map<String, Object> map;
		try {
			JSONArray array=new JSONObject(data).getJSONArray("data");
			for (int i=0;i<array.length();i++){
				JSONObject object=array.getJSONObject(i);
				Iterator<?> iterator=object.keys();
				map=new HashMap<String, Object>();
				while (iterator.hasNext()) {
					String name=iterator.next().toString();
					map.put(name, object.get(name));
				}
				list.add(map);
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
	
}
