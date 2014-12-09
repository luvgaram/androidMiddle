package com.example.w3_hw;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
		
		String img_path = context.getFilesDir().getPath() + "/" + articleData.get(position).getImgName();
		File img_load_path = new File(img_path);
		
		if (img_load_path.exists()) {
			// 이미지가 경로에 있으면 이미지를 비트맵으로 바꾸어서 이미지 뷰에 표시
			Bitmap bitmap = BitmapFactory.decodeFile(img_path);
			imageView.setImageBitmap(bitmap);
		}
		
		return row;
	}
	
	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		
	}
}
