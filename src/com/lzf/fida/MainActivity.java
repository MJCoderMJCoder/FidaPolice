
package com.lzf.fida;

import java.util.HashMap;
import java.util.Map;

import com.lzf.http.PostData;
import com.lzf.util.Encrypt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author MJCoder
 */
public class MainActivity extends FragmentActivity {

	private String username = "";
	private String password = "";
	private String message = "";
	private final int LOGIN = 6003;
	private final int ISLOGIN = 6004;
	private Context context = MainActivity.this;

	// 主要接受子线程发送的数据， 并用此数据配合主线程更新UI。
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case LOGIN:
				if (message.equals("OK")) {
					saveSP(username, password);
					MainActivity.this.finish();
					Intent intent = new Intent(MainActivity.this, RoutineActivity.class);
					startActivity(intent);
				} else {
					Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
				}
				break;
			default:
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Map<String, String> map = readSP();
		if (map.get("username") != null && map.get("password").length() == 64 && !(map.get("username").equals(""))) {
			MainActivity.this.finish();
			Intent intent = new Intent(MainActivity.this, RoutineActivity.class);
			startActivity(intent);
		} else {
			setContentView(R.layout.activity_main);
		}
	}

	// 登录按钮相应事件
	public void login(View view) {
		username = ((EditText) findViewById(R.id.userName)).getText().toString();
		String raw = ((EditText) findViewById(R.id.password)).getText().toString();
		password = Encrypt.encrypt(raw);
		new Thread() {
			public void run() {
				message = PostData.submit("http://192.168.2.36:8080/police/user", username, password);
				handler.sendEmptyMessage(LOGIN);
			};
		}.start();
	}

	// 往SharedPreferences保存用户偏好参数的方法
	private void saveSP(String username, String password) {
		SharedPreferences sp = context.getSharedPreferences("token", context.MODE_PRIVATE);
		Editor edit = sp.edit();
		edit.putString("username", username);
		edit.putString("token", password);
		edit.commit();
	}

	// 定义一个读取SP文件的方法
	public Map<String, String> readSP() {
		Map<String, String> data = new HashMap<String, String>();
		SharedPreferences sp = context.getSharedPreferences("token", context.MODE_PRIVATE);
		data.put("username", sp.getString("username", "").trim());
		data.put("password", sp.getString("token", "").trim());
		return data;
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// getMenuInflater().inflate(R.menu.main, menu);
	// return super.onCreateOptionsMenu(menu);
	// }
	//
	// @Override
	// public boolean onOptionsItemSelected(MenuItem item) {
	// int id = item.getItemId();
	// switch (id) {
	// case R.id.settings:
	// Builder dialogBuilder = new Builder(MainActivity.this);
	// View view =
	// LayoutInflater.from(MainActivity.this).inflate(R.layout.settings_ip_dialog,
	// null, false);
	// dialogBuilder.setView(view);
	// dialogBuilder.setCancelable(false);
	// final AlertDialog alertDialog = dialogBuilder.create();
	// alertDialog.show();
	// view.findViewById(R.id.cancel).setOnClickListener(new OnClickListener() {
	// @Override
	// public void onClick(View arg0) {
	// alertDialog.cancel();
	// }
	// });
	// view.findViewById(R.id.confirm).setOnClickListener(new OnClickListener()
	// {
	// @Override
	// public void onClick(View arg0) {
	// alertDialog.dismiss();
	// }
	// });
	// break;
	//
	// case R.id.upgrade:
	// ProgressDialog.show(MainActivity.this, "提示", "加载中，请稍后......", true,
	// true);
	// break;
	// }
	// return super.onOptionsItemSelected(item);
	// }
}
