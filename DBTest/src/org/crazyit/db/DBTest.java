package org.crazyit.db;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class DBTest extends Activity {
	SQLiteDatabase db;

	Button bn = null;
	ListView listView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		initTitle();
		initData();
		initView();

	}

	public void initTitle() {

	}

	public void initData() {

		db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString() + "/my.db3", null);

		try {
			db.execSQL("create table news_inf(_id integer" + " primary key autoincrement," + " news_title varchar(50),"
					+ " news_content varchar(255))");
		} catch (Exception e) {

			Log.i("TAG", "e---" + e);
		}
	}

	public void initView() {
		listView = (ListView) findViewById(R.id.show);
		bn = (Button) findViewById(R.id.ok);

		queryData();

		bn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View source) {
				String title = ((EditText) findViewById(R.id.title)).getText().toString();
				String content = ((EditText) findViewById(R.id.content)).getText().toString();

				try {
					// 插入数据
					insertData(db, title, content);

					queryData();
				} catch (SQLiteException se) {

				}
			}
		});

	}

	// 插入数据
	private void insertData(SQLiteDatabase db, String title, String content) {
		// 执行插入语句
		db.execSQL("insert into news_inf values(null , ? , ?)", new String[] { title, content });
	}

	// 查询数据
	private void queryData() {
		Cursor cursor = db.rawQuery("select * from news_inf", null);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(DBTest.this, R.layout.line, cursor, new String[] {
				"news_title", "news_content" }, new int[] { R.id.my_title, R.id.my_content },
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		listView.setAdapter(adapter);

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		// 退出程序时关闭SQLiteDatabase
		if (db != null && db.isOpen()) {
			db.close();
		}
	}

}
