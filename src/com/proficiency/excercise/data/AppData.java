package com.proficiency.excercise.data;

import com.proficiency.excercise.modal.ListData;

public class AppData {

	private static AppData singleton;
	private ListData listData;
	
	private AppData() {}
	
	public static AppData getIntance() {
		if(singleton == null) {
			singleton = new AppData();
		}
		return singleton;
	}

	public ListData getListData() {
		return listData;
	}

	public void setListData(ListData listData) {
		this.listData = listData;
	}

}
