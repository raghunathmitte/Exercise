package com.proficiency.excercise.viewholders;

import com.proficiency.excercise.R;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class RowAdapterViewHolder {
	
	public TextView title;
	public TextView description;
	public ImageView imageView;
	
	public RowAdapterViewHolder(View view) {
		this.title = (TextView)view.findViewById(R.id.row_title);
		this.description = (TextView)view.findViewById(R.id.row_description);
		this.imageView = (ImageView)view.findViewById(R.id.row_image);
	}
	
}
