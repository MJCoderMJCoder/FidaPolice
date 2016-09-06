package com.lzf.fragment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lzf.fida.R;
import com.lzf.fida.RoutineActivity;
import com.lzf.fida.adapter.ReusableAdapter;
import com.lzf.fida.bean.Tasktype;
import com.lzf.http.GetData;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class TasktypeFragment extends Fragment {

	private Context context;
	private List<Tasktype> list;
	private String returnStr = "";
	private ListView tasktypeList;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (returnStr.equals("系统升级维护中，由此给您带来的不便敬请谅解！感谢您的支持与理解！")) {
				Toast.makeText(context, returnStr, Toast.LENGTH_SHORT).show();
			} else {
				List<Tasktype> list = new ArrayList<Tasktype>();
				JSONArray jArray;
				try {
					jArray = new JSONArray(returnStr);
					for (int i = 0; i < jArray.length(); i++) {
						JSONObject jObject = (JSONObject) jArray.get(i);
						Tasktype tasktype = new Tasktype(jObject.getInt("time"), jObject.getString("taskType"),
								jObject.getInt("inspect"), jObject.getInt("inspected"));
						list.add(tasktype);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				ReusableAdapter<Tasktype> adapter = new ReusableAdapter<Tasktype>(list, R.layout.tasktype_list_item) {
					@Override
					public void bindView(ViewHolder holder, Tasktype obj) {
						holder.setText(R.id.type, obj.getType());
						holder.setText(R.id.inspect, String.valueOf(obj.getInspect()));
						holder.setText(R.id.inspected, String.valueOf(obj.getInspected()));
					}
				};
				tasktypeList.setAdapter(adapter);
				tasktypeList.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

					}
				});
			}
		}
	};

	public TasktypeFragment(Context context) {
		super();
		this.context = context;
		new Thread() {
			public void run() {
				returnStr = GetData.getHtml("http://192.168.2.36:8080/police/tasksType");
				handler.sendEmptyMessage(6);
			}
		}.start();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_tasktype, container, false);
		tasktypeList = (ListView) view.findViewById(R.id.tasktypeList);
		return view;
	}

}
