package cn.mytaobao.adapter;

import java.util.ArrayList;
import java.util.List;

import cn.mytaobao.model.Product;
import cn.mytaobao.model.Product2;
import cn.mytaobao.ui.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainShowAdapter extends BaseAdapter {
	private List<Product2> products;
	private Context mContext;
	private LayoutInflater mInflater;

	public MainShowAdapter(Context context) {
		this.mContext = context;
		this.mInflater = LayoutInflater.from(context);
		products = new ArrayList<Product2>();
		products.add(new Product2(1, R.drawable.maoyi, "毛领毛呢大衣", "￥199"));
		products.add(new Product2(1, R.drawable.shouji, "三星i9100", "￥3200"));
		products.add(new Product2(1, R.drawable.maoyi, "毛领毛呢大衣", "￥199"));
		products.add(new Product2(1, R.drawable.maoyi, "毛领毛呢大衣", "￥199"));
		products.add(new Product2(1, R.drawable.maoyi, "毛领毛呢大衣", "￥199"));
		products.add(new Product2(1, R.drawable.maoyi, "毛领毛呢大衣", "￥199"));
		products.add(new Product2(1, R.drawable.maoyi, "毛领毛呢大衣", "￥199"));
		products.add(new Product2(1, R.drawable.maoyi, "毛领毛呢大衣", "￥199"));
		products.add(new Product2(1, R.drawable.maoyi, "毛领毛呢大衣", "￥199"));

	}

	public int getCount() {
		return products.size();
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
		convertView = mInflater.inflate(R.layout.main_grid_view, null);
		Product2 product = products.get(position);
		ImageView img = (ImageView) convertView.findViewById(R.id.img);
		TextView txtName = (TextView) convertView.findViewById(R.id.txtName);
		TextView txtMoney = (TextView) convertView.findViewById(R.id.txtMoney);
		img.setImageDrawable(mContext.getResources().getDrawable(
				product.getImage()));
		txtName.setText(product.getName());
		txtMoney.setText(product.getPrice());
		return convertView;
	}

}
