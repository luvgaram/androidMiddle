package com.example.w3_hw;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<Article> implements OnItemClickListener{
	private Context context;
	private int layoutResourceId;
	private ArrayList<Article> articleData;
	
	public CustomAdapter(Context context, int layoutResourceId, ArrayList<Article> articleData) {
		super(context, layoutResourceId, articleData);
		this.context = context;
		this.layoutResourceId = layoutResourceId;
		this.articleData = articleData;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		
		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
		}
		
		TextView tvTitle = (TextView) row.findViewById(R.id.custom_row_textView1);
		TextView tvContent = (TextView) row.findViewById(R.id.custom_row_textView2);
		
		tvTitle.setText(articleData.get(position).getTitle());
		tvContent.setText(articleData.get(position).getContent());
		
		ImageView imageView = (ImageView) row.findViewById(R.id.custom_row_imageView1);
		
		try {
			InputStream is = context.getAssets().open(articleData.get(position).getImgName());
			Drawable d = Drawable.createFromStream(is, null);
			imageView.setImageDrawable(d);
		} catch (IOException e){
			Log.e("ERROR", "ERROR:" + e);
		}
		
		return row;
	}
	
	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		
	}
}
