package com.example.mypm;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String api = "0";
		String quality = "";
		// start to get pm data value
		GetPmValue getPmValue = new GetPmValue();
		
		JSONObject json = getPmValue.getPmValue("chongqing");
		
		setContentView(R.layout.activity_main);
		
		TextView pmvalue = (TextView)this.findViewById(R.id.pmValue);
		
		if(json != null){
			try {
				api = json.getString("aqi");
				quality = json.getString("quality");
			} catch (JSONException e) {
				e.printStackTrace();
				api = "0";
				quality = "";
			} 
		} else {
			api = "0";
			quality = "";
		}
		pmvalue.setText(api +" "+quality);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
