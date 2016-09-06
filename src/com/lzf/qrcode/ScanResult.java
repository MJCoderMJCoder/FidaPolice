package com.lzf.qrcode;

import com.lzf.fida.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ScanResult extends Activity {

	private TextView textview;
	private String result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scan_result);

		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		result = bundle.getString("result");
		textview = (TextView) findViewById(R.id.scanResultText);
		if (result.contains("http")) {
			textview.setTextColor(Color.BLUE);
			textview.setText(Html.fromHtml("<u>" + result + "</u>"));
			textview.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Uri uri = Uri.parse(result);
					Intent intent = new Intent(Intent.ACTION_VIEW, uri);
					startActivity(intent);
				}
			});
		} else {
			textview.setTextColor(Color.BLACK);
			textview.setText(result);
		}
	}

}
