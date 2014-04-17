package cn.mytaobao.adapter;

import cn.mytaobao.ui.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductListAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private Context mContext;

	public ProductListAdapter(Context context) {
		this.mContext = context;
		mInflater = LayoutInflater.from(context);

	}

	public int getCount() {

		return 4;
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
		convertView = mInflater.inflate(R.layout.product_list_view, null);
		ImageView img = (ImageView) convertView.findViewById(R.id.img);
		TextView name = (TextView) convertView.findViewById(R.id.name);
		TextView price = (TextView) convertView.findViewById(R.id.price);
		img.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.shouji));
		name.setText("Samsung/ÈýÐÇ CSH-I509");
		price.setText("£¤ 3200 Ôª");
		return convertView;
	}

}
