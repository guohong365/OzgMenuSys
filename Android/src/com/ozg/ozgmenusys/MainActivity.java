package com.ozg.ozgmenusys;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends BaseActivity { 
    	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		this.connect();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);				
		setContentView(R.layout.activity_main);
						
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		
	    if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
	        //当前为横屏， 在此处添加额外的处理代码
	    		    	
	    	//视图相关
			TextView labMsg = (TextView)this.findViewById(R.id.main_lab_msg);
			labMsg.setTextSize(24.0f);
			
			Button btn = (Button)this.findViewById(R.id.main_btn);
			btn.setGravity(View.GONE);
			btn.setTextSize(24.0f);
			btn.setOnClickListener(new OnClickListener() {
	
				@Override
				public void onClick(View v) {
										
					SharedPreferences sp = MainActivity.this.getSharedPreferences(AppConfig.APP_DATA, Context.MODE_PRIVATE);
					
					if(MainActivity.this.mConnection.isConnected() && sp.contains(AppConfig.CLIENT_DATA)) {
//						Log.d("ozgtest", "进入菜单界面");
						
						MainActivity.this.toMainActivity();		
					}
					else {
						MainActivity.this.connect();
					}
					
				}
			});
			
			//socket相关
			this.connect();
	    }
	    else if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
	        //当前为竖屏， 在此处添加额外的处理代码

	    }

	    //检测实体键盘的状态：推出或者合上
	    if(newConfig.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_NO) { 
	        //实体键盘处于推出状态，在此处添加额外的处理代码

	    }
	    else if (newConfig.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_YES) { 
	        //实体键盘处于合上状态，在此处添加额外的处理代码

	    }
	    
	}
	
}
