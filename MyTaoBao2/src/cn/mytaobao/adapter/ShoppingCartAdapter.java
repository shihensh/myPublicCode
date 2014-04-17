package cn.mytaobao.adapter;

import cn.mytaobao.ui.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShoppingCartAdapter extends BaseAdapter {
	private Context mContext;
	private LayoutInflater mInflater;
	private boolean isModify = false;
	private int count;

	public ShoppingCartAdapter(Context context) {
		this.mContext = context;
		this.mInflater = LayoutInflater.from(context);

	}

	public void setModifyFlag(boolean isModify) {
		this.isModify = isModify;
		this.notifyDataSetChanged();

	}

	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = mInflater.inflate(R.layout.shopping_list_view, null);
		LinearLayout modify = (LinearLayout) convertView
				.findViewById(R.id.modify);
		final TextView txtTotalMoney = (TextView) convertView
				.findViewById(R.id.txtTotalMoney);
		TextView txtSingleMoney = (TextView) convertView
				.findViewById(R.id.txtSingleMoney);
		final TextView txtCount = (TextView) convertView
				.findViewById(R.id.txtCount);
		final EditText edtCount = (EditText) convertView
				.findViewById(R.id.edtCount);
		final ImageView reduce = (ImageView) convertView.findViewById(R.id.reduce);
		ImageView add = (ImageView) convertView.findViewById(R.id.add);

		final Double singleMoney = Double.valueOf(txtSingleMoney.getText()
				.toString());

		reduce.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				count = Integer.valueOf(edtCount.getText().toString());
				count--;
				edtCount.setText(count + "");
				txtTotalMoney.setText(singleMoney * count + "");
				txtCount.setText(count + "");

				if (count < 2) {
					reduce.setBackgroundDrawable(mContext.getResources()
							.getDrawable(R.drawable.cart_reduce_nm));
					reduce.setClickable(false);

				}

			}
		});
		add.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				count = Integer.valueOf(edtCount.getText().toString());
				count++;
				edtCount.setText(count + "");
				txtTotalMoney.setText(singleMoney * count + "");
				txtCount.setText(count + "");
				
				if (count >1) {
					reduce.setBackgroundDrawable(mContext.getResources()
							.getDrawable(R.drawable.cart_reduce_dw));
					reduce.setClickable(true);

				}

			}
		});

		if (isModify) {
			modify.setVisibility(View.VISIBLE);
			int mCount = Integer.valueOf(txtCount.getText().toString());
			edtCount.setText(txtCount.getText().toString());
			if (mCount == 1) {
				reduce.setClickable(false);
				reduce.setBackgroundDrawable(mContext.getResources()
						.getDrawable(R.drawable.cart_reduce_nm));

			}

		} else {
			modify.setVisibility(View.GONE);
			txtCount.setText(edtCount.getText().toString());

		}
		return convertView;
	}

}
