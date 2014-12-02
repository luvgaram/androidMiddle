package com.example.w3_hw;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends Activity implements OnClickListener, OnItemClickListener{
	
	private Button mButtonWrite;
	private Button mButtonRefresh;

	private ArrayList<Article> articleList;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        
        mButtonWrite = (Button)findViewById(R.id.main_button_write);
        mButtonRefresh = (Button)findViewById(R.id.main_button_refresh);
        
        mButtonWrite.setOnClickListener(this);
        mButtonRefresh.setOnClickListener(this);
        
		ListView listView = (ListView)findViewById(R.id.custom_list_listView);
		
		Dao dao = new Dao( getApplicationContext() );
		String testJsonData = dao.getJsonTestData();
		dao.insertJsonData(testJsonData);
	
		articleList = dao.getArticleList();
		
		CustomAdapter customAdapter = new CustomAdapter(this, R.layout.custom_list_row, articleList);
		listView.setAdapter(customAdapter);
		listView.setOnItemClickListener(this);
		
		Log.i("test", "리스트뷰 완료");    
      
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
    	Intent intent = new Intent(this, ViewActivity.class);
    	
    	intent.putExtra("ArticleNumber", articleList.get(position).getArticleNumber() + "");
    	
    	startActivity(intent);
    	
    }
    
    @Override
    public void onClick(View arg0) {
    	switch (arg0.getId()) {
    	case R.id.main_button_write:
    		Intent intentWrite = new Intent(this, WriteActivity.class);
    		startActivity(intentWrite);
    	case R.id.main_button_refresh:
    		break;
    	}
    }

}
