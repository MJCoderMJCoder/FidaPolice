/**
 * 
 */
package com.lzf.fida;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.lzf.fida.adapter.ReusableAdapter;
import com.lzf.fida.bean.Rountine;
import com.lzf.fragment.CommunityFragment;
import com.lzf.fragment.PopulationFragment;
import com.lzf.fragment.RentalFragment;
import com.lzf.fragment.SiteFragment;
import com.lzf.fragment.TasktypeFragment;
import com.lzf.qrcode.CaptureActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ViewDragHelper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author MJCoder
 * 
 *         日常工作
 *
 */
public class RoutineActivity extends FragmentActivity implements OnItemClickListener {

	private long exitTime = 0;
	private ListView routine;
	private DrawerLayout drawerLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_routine);

		drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
		setDrawerLeftEdgeSize(this, drawerLayout, 1.0f);

		List<Rountine> rountines = new ArrayList<Rountine>();
		rountines.add(new Rountine(R.drawable.ic_launcher, "日常签到"));
		rountines.add(new Rountine(R.drawable.ic_launcher, "入户采集"));
		rountines.add(new Rountine(R.drawable.ic_launcher, "场所核查"));
		rountines.add(new Rountine(R.drawable.ic_launcher, "业务查询"));
		rountines.add(new Rountine(R.drawable.ic_launcher, "任务通知"));
		rountines.add(new Rountine(R.drawable.ic_launcher, "工作日志"));
		rountines.add(new Rountine(R.drawable.ic_launcher, "我的任务"));
		ReusableAdapter<Rountine> adapter = new ReusableAdapter<Rountine>(rountines, R.layout.routine_list_item) {
			@Override
			public void bindView(ViewHolder holder, Rountine obj) {
				holder.setText(R.id.chinese, obj.getChinese());
				holder.setImageResource(R.id.image, obj.getImage());
			}
		};
		routine = (ListView) findViewById(R.id.routine);
		routine.setOnItemClickListener(this);
		routine.setAdapter(adapter);
		routine.performItemClick(routine, -1, R.id.routine);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.routine, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case R.id.getData:
			ProgressDialog.show(RoutineActivity.this, "提示", "加载中，请稍后......", true, true);
			break;
		case R.id.changePassword:
			ProgressDialog.show(RoutineActivity.this, "提示", "加载中，请稍后......", true, true);
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		FrameLayout mainboard = (FrameLayout) findViewById(R.id.mainboard);
		mainboard.removeAllViews();
		switch (position) {
		case 0:
			Toast.makeText(getApplicationContext(), "抱歉，当前设备不支持该功能！", Toast.LENGTH_SHORT).show();
			break;
		case 1:
			view = LayoutInflater.from(RoutineActivity.this).inflate(R.layout.household_collect, parent, false);
			final TextView tempStorage = (TextView) view.findViewById(R.id.tempStorage);
			final TextView community = (TextView) view.findViewById(R.id.community);
			final TextView noCommunity = (TextView) view.findViewById(R.id.noCommunity);
			tempStorage.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					tempStorage.setSelected(true);
					community.setSelected(false);
					noCommunity.setSelected(false);
					Toast.makeText(getApplicationContext(), "没有暂存数据", Toast.LENGTH_SHORT).show();
				}
			});
			community.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Toast.makeText(getApplicationContext(), "抱歉，当前设备不支持该功能！", Toast.LENGTH_SHORT).show();
				}
			});
			noCommunity.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Toast.makeText(getApplicationContext(), "抱歉，当前设备不支持该功能！", Toast.LENGTH_SHORT).show();
				}
			});
			mainboard.addView(view);
			tempStorage.performClick();
			break;
		case 2:
			view = LayoutInflater.from(RoutineActivity.this).inflate(R.layout.site_verification, parent, false);
			final TextView tempStor = (TextView) view.findViewById(R.id.tempStor);
			final TextView scan = (TextView) view.findViewById(R.id.scan);
			tempStor.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					tempStor.setSelected(true);
					scan.setSelected(false);
					Toast.makeText(getApplicationContext(), "没有暂存数据", Toast.LENGTH_SHORT).show();
				}
			});
			scan.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(RoutineActivity.this, CaptureActivity.class);
					startActivity(intent);
				}
			});
			mainboard.addView(view);
			tempStor.performClick();
			break;
		case 3:
			view = LayoutInflater.from(RoutineActivity.this).inflate(R.layout.business_query, parent, false);
			final TextView population = (TextView) view.findViewById(R.id.population);
			final TextView site = (TextView) view.findViewById(R.id.site);
			final TextView communityQuery = (TextView) view.findViewById(R.id.communityQuery);
			final TextView rental = (TextView) view.findViewById(R.id.rental);
			population.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					population.setSelected(true);
					site.setSelected(false);
					communityQuery.setSelected(false);
					rental.setSelected(false);
					PopulationFragment populationFragment = new PopulationFragment(RoutineActivity.this);
					getSupportFragmentManager().beginTransaction().replace(R.id.centerBody, populationFragment)
							.commit();
				}
			});
			site.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					site.setSelected(true);
					population.setSelected(false);
					communityQuery.setSelected(false);
					rental.setSelected(false);
					SiteFragment siteFragment = new SiteFragment(RoutineActivity.this);
					getSupportFragmentManager().beginTransaction().replace(R.id.centerBody, siteFragment).commit();
				}
			});
			communityQuery.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					communityQuery.setSelected(true);
					population.setSelected(false);
					site.setSelected(false);
					rental.setSelected(false);
					CommunityFragment communityFragment = new CommunityFragment(RoutineActivity.this);
					getSupportFragmentManager().beginTransaction().replace(R.id.centerBody, communityFragment).commit();
				}
			});
			rental.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					communityQuery.setSelected(false);
					population.setSelected(false);
					site.setSelected(false);
					rental.setSelected(true);
					RentalFragment rentalFragment = new RentalFragment(RoutineActivity.this);
					getSupportFragmentManager().beginTransaction().replace(R.id.centerBody, rentalFragment).commit();
				}
			});
			mainboard.addView(view);
			population.performClick();
			break;
		case 4:
			Toast.makeText(getApplicationContext(), "暂无任务通知！", Toast.LENGTH_SHORT).show();
			break;
		case 5:
			view = LayoutInflater.from(RoutineActivity.this).inflate(R.layout.work_log, parent, false);
			mainboard.addView(view);
			break;
		case 6:
			view = LayoutInflater.from(RoutineActivity.this).inflate(R.layout.my_tasks, parent, false);
			final TextView taskInfo = (TextView) view.findViewById(R.id.taskInfo);
			final TextView quarterTask = (TextView) view.findViewById(R.id.quarterTask);
			final TextView annualTask = (TextView) view.findViewById(R.id.annualTask);
			taskInfo.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					quarterTask.setSelected(false);
					annualTask.setSelected(false);
					taskInfo.setSelected(true);
					TasktypeFragment tasktypeFragment = new TasktypeFragment(RoutineActivity.this);
					getSupportFragmentManager().beginTransaction().replace(R.id.centerBody, tasktypeFragment).commit();
				}
			});
			quarterTask.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					quarterTask.setSelected(true);
					annualTask.setSelected(false);
					taskInfo.setSelected(false);
				}
			});
			annualTask.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					quarterTask.setSelected(false);
					annualTask.setSelected(true);
					taskInfo.setSelected(false);
				}
			});
			mainboard.addView(view);
			taskInfo.performClick();
			break;
		default:
			view = LayoutInflater.from(RoutineActivity.this).inflate(R.layout.slide_hint, parent, false);
			mainboard.addView(view);
			break;
		}
		drawerLayout.closeDrawer(routine);
	}

	// 重写回退按钮的事件，当用户点击回退按钮：
	// 提示连续点击两次退出App,否则弹出提示Toast
	@Override
	public void onBackPressed() {
		if ((System.currentTimeMillis() - exitTime) > 2000) {
			Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
			exitTime = System.currentTimeMillis();
		} else {
			finish();
		}
	}

	/**
	 * 抽屉滑动范围控制
	 * 
	 * @param activity
	 * @param drawerLayout
	 * @param displayWidthPercentage
	 *            占全屏的份额0~1
	 */
	private void setDrawerLeftEdgeSize(Activity activity, DrawerLayout drawerLayout, float displayWidthPercentage) {
		if (activity == null || drawerLayout == null)
			return;
		try {
			// find ViewDragHelper and set it accessible
			Field leftDraggerField = drawerLayout.getClass().getDeclaredField("mLeftDragger");
			leftDraggerField.setAccessible(true);
			ViewDragHelper leftDragger = (ViewDragHelper) leftDraggerField.get(drawerLayout);
			// find edgesize and set is accessible
			Field edgeSizeField = leftDragger.getClass().getDeclaredField("mEdgeSize");
			edgeSizeField.setAccessible(true);
			int edgeSize = edgeSizeField.getInt(leftDragger);
			// set new edgesize
			// Point displaySize = new Point();
			DisplayMetrics dm = new DisplayMetrics();
			activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
			edgeSizeField.setInt(leftDragger, Math.max(edgeSize, (int) (dm.widthPixels * displayWidthPercentage)));
		} catch (NoSuchFieldException e) {
			Log.e("NoSuchFieldException", e.getMessage().toString());
		} catch (IllegalArgumentException e) {
			Log.e("IllegalArgumentException", e.getMessage().toString());
		} catch (IllegalAccessException e) {
			Log.e("IllegalAccessException", e.getMessage().toString());
		}
	}
}
