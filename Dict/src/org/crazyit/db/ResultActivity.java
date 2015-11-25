/**
 *
 */
package org.crazyit.db;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ResultActivity extends Activity {
	
	List<Map<String, String>> list;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popup);
		
		initTitle();
		initData();
		initView();
		
	}
	
	public void initTitle() {

	}

	public void initData() { 
		list = (List<Map<String, String>>) getIntent().getExtras().getSerializable("data");
		
	}

	public void initView() {
		ListView listView = (ListView) findViewById(R.id.show);
		SimpleAdapter adapter = new SimpleAdapter(ResultActivity.this, list, R.layout.line, new String[] { "word",
		"detail" }, new int[] { R.id.word, R.id.detail });
		listView.setAdapter(adapter);
	}
	
	
}
