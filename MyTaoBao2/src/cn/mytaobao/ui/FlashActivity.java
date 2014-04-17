package cn.mytaobao.ui;

import cn.mytaobao.ui.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class FlashActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Thread thread = new Thread(new Runnable() {

			public void run() {
				try {
					Thread.sleep(2000);
					Intent intent = new Intent(FlashActivity.this,
							ActivityGroup.class);
					startActivity(intent);
					finish();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		thread.start();

	}
}