package com.proficiency.excercise.util;

import android.app.Activity;

public class CommonUtil {

	public static String getString(Activity activity, int id) {
		return activity.getResources().getString(id);
	}
	
}
