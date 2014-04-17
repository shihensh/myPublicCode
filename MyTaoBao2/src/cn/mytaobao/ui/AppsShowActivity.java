package cn.mytaobao.ui;

import cn.mytaobao.adapter.AppsShowAdapter;
import cn.mytaobao.ui.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class AppsShowActivity extends Activity {
	private ProgressBar bar;
	private GridView grid;
	private AppsShowAdapter adapter;
	private Context context;

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Log.i("handler", "handler");
			bar.setVisibility(View.GONE);
			grid.setVisibility(View.VISIBLE);
			grid.setNumColumns(4);
			adapter = new AppsShowAdapter(context);
			grid.setAdapter(adapter);

		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.apps_show);
		context = this;

		getId();
		// bar.setVisibility(View.GONE);
		// grid.setVisibility(View.VISIBLE);
		// grid.setNumColumns(4);
		// adapter = new AppsShowAdapter(context);
		// grid.setAdapter(adapter);

		Thread thread = new Thread(new Runnable() {

			public void run() {
				try {
					Thread.sleep(3000);
					handler.sendEmptyMessage(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		thread.start();
	}

	private void getId() {
		bar = (ProgressBar) findViewById(R.id.progress_bar);
		grid = (GridView) findViewById(R.id.appsGrid);

	}

}
