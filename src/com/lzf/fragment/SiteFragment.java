package com.lzf.fragment;

import com.lzf.fida.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

public class SiteFragment extends Fragment {
	private Context context;

	public SiteFragment(Context context) {
		super();
		this.context = context;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_site, container, false);
		view.findViewById(R.id.site).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				ProgressDialog.show(context, "提示", "加载中，请稍后......", true, true);
			}
		});
		return view;
	}
}
