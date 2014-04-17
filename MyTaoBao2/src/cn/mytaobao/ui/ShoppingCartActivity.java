package cn.mytaobao.ui;

import cn.mytaobao.adapter.ShoppingCartAdapter;
import cn.mytaobao.ui.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class ShoppingCartActivity extends Activity {

	private ShoppingCartAdapter adapter;
	private ListView list;
	private Context context;
	private TextView shoppingModify;
	private boolean inMotify = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shopping_cart);
		context = this;
		getId();
		adapter = new ShoppingCartAdapter(context);
		list.setAdapter(adapter);

		shoppingModify.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				String state = shoppingModify.getText().toString();
				if ("�޸�".equals(state)) {
					shoppingModify.setText("���");
					inMotify = true;
					adapter.setModifyFlag(true);
				} else {
					shoppingModify.setText("�޸�");
					inMotify = false;
					adapter.setModifyFlag(false);

				}

			}
		});
	}

	private void getId() {
		list = (ListView) findViewById(R.id.shoppingList);
		shoppingModify = (TextView) findViewById(R.id.shoppingModify);

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == event.KEYCODE_BACK) {
			if (inMotify) {
				inMotify = false;
				shoppingModify.setText("�޸�");
				adapter.setModifyFlag(false);
				return true;
			}

		}
		return super.onKeyDown(keyCode, event);
	}

}
