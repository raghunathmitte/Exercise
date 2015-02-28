package com.proficiency.excercise.adapter;

import java.util.ArrayList;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.BitmapAjaxCallback;
import com.proficiency.excercise.R;
import com.proficiency.excercise.modal.ListData;
import com.proficiency.excercise.modal.Row;
import com.proficiency.excercise.viewholders.RowAdapterViewHolder;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class RowAdapter extends BaseAdapter {
	private ArrayList<Row> listData;
	private Activity activity;
	private AQuery aquery;
	
	public RowAdapter(Activity activity, ArrayList<Row> listData) {
		this.listData = listData;
		this.activity = activity;
		
		for(int i = 0; i < listData.size(); i++) {
			Row rowItem = listData.get(i);
			String title = rowItem.getTitle();
			String desc = rowItem.getDescription();
			String imagePath = rowItem.getImageHref();
			//remove the items if all three are null
			if(title == null && desc == null && imagePath == null) {
				listData.remove(i);
				i--;
			}
		}
	}
	
	@Override
	public int getCount() {
		if(listData != null) {
			return listData.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if(listData != null) {
			return listData.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public int getViewTypeCount() {
		return getCount();
	}
	
	@Override
	public int getItemViewType(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null) {
			convertView = activity.getLayoutInflater().inflate(R.layout.list_view_row, null);
			convertView.setTag(new RowAdapterViewHolder(convertView));
		}
		Row rowItem = listData.get(position);
				
		RowAdapterViewHolder viewHolder = (RowAdapterViewHolder)convertView.getTag();
		viewHolder.title.setText("");
		viewHolder.description.setText("");
		viewHolder.imageView.setImageResource(0);
		if(rowItem != null) {
			String title = rowItem.getTitle();
			if(title != null) {
				viewHolder.title.setText(title);
			} 
			String description = rowItem.getDescription();
			if(description != null) {
				viewHolder.description.setText(description);
			}
			String imagePath = rowItem.getImageHref();
			if(imagePath != null) {
				if(aquery == null) {
					aquery = new AQuery(activity);
				}
//				aquery.id(viewHolder.imageView).image(imagePath, false, true, 0, R.drawable.ic_launcher, new BitmapAjaxCallback(){
//	                @Override
//	                public void callback(String url, ImageView iv, Bitmap bm, AjaxStatus status){
//	                        iv.setImageBitmap(bm);
////	                        progressBar.setVisibility(View.INVISIBLE);
//	                }
//	        	});
				aquery.id(viewHolder.imageView).image(imagePath, true, true, 0, R.drawable.ic_launcher);
			}
		}
		return convertView;
	}

}
