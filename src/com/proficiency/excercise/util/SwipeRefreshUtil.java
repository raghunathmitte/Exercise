package com.proficiency.excercise.util;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;

public class SwipeRefreshUtil extends SwipeRefreshLayout {
	private View scrollView;
	
	public SwipeRefreshUtil(Context context) {
		super(context);
	}

	public SwipeRefreshUtil(Context context, AttributeSet attrs) {
	    super(context, attrs);
	}
	
	public void setScrollableView(View view){
		scrollView = view;
	}
	
	@Override
	public boolean canChildScrollUp() {
		return ViewCompat.canScrollVertically(scrollView, -1);
	}
	
}
