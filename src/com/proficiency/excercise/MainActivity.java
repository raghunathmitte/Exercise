package com.proficiency.excercise;

import org.jdeferred.DoneCallback;
import org.jdeferred.FailCallback;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.widget.ListView;

import com.google.gson.Gson;
import com.proficiency.excercise.adapter.RowAdapter;
import com.proficiency.excercise.data.AppData;
import com.proficiency.excercise.modal.ListData;
import com.proficiency.excercise.network.BackgroundService;
import com.proficiency.excercise.util.DialogUtil;
import com.proficiency.excercise.util.LogUtil;
import com.proficiency.excercise.util.SwipeRefreshUtil;

public class MainActivity extends Activity {
	private final String TAG = "MainActivity";
	private final String URL = "https://dl.dropboxusercontent.com/u/746330/facts.json";
	private ListView listView;
	private SwipeRefreshUtil swipeRefreshLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		listView = (ListView) findViewById(R.id.pull_refresh_list);
		swipeRefreshLayout = (SwipeRefreshUtil) findViewById(R.id.swiperefresh_layout);
		swipeRefreshLayout.setScrollableView(listView);
		
		swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {
				LogUtil.i(TAG, "Refreshing");
				getDataFromServer(false);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void onResume() {
		super.onResume();
		if (AppData.getIntance().getListData() == null) {
			getDataFromServer(true);
		} else {
			loadView();
		}

	}

	private void loadView() {
		ListData listData = AppData.getIntance().getListData();
		if(listData != null) {
			String actionBarTitle = listData.getTitle();
			if(actionBarTitle != null) {
				getActionBar().setTitle(actionBarTitle);
			}
			RowAdapter adapter = new RowAdapter(MainActivity.this, listData.getRows());
			listView.setAdapter(adapter);
		}
	}

	private void getDataFromServer(boolean showProgress) {
		BackgroundService.getInstance().get(MainActivity.this, URL,showProgress).then(onSuccess, onFail);
	}

	private DoneCallback<JSONObject> onSuccess = new DoneCallback<JSONObject>() {

		@Override
		public void onDone(JSONObject jsonData) {
			onServerCallDone();
			if (jsonData != null) {
//				LogUtil.i(TAG, "onDone : "+jsonData.toString());
				parseData(jsonData);
			}
		}
	};

	private FailCallback<Object> onFail = new FailCallback<Object>() {

		@Override
		public void onFail(Object arg0) {
			onServerCallDone();
			if (arg0 != null){
				LogUtil.i(TAG, "onFail : "+arg0.toString());
				DialogUtil.getInstance().showAlertDialog(MainActivity.this, "Error", (String)arg0);
			}
		}
	};
	
	private void onServerCallDone() {
//		listView.onRefreshComplete();
		swipeRefreshLayout.setRefreshing(false);
	}
	
	private void parseData(JSONObject jsonObject){
		Gson gson = new Gson();
		ListData listData = gson.fromJson(jsonObject.toString(), ListData.class);
		AppData.getIntance().setListData(listData);
		loadView();
	}
}
