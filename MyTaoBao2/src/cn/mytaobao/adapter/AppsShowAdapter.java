package cn.mytaobao.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.mytaobao.ui.R;






import android.R.integer;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AppsShowAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private Context mContext;
	private List<Drawable> imgList;
	private List<String> nameList;

	public AppsShowAdapter(Context context) {
		this.mContext = context;
		this.mInflater = LayoutInflater.from(context);
		imgList = new ArrayList<Drawable>();
		nameList = new ArrayList<String>();
		imgList.add(context.getResources().getDrawable(
				R.drawable.icon_logistics));
		nameList.add("查物流");

		imgList.add(context.getResources()
				.getDrawable(R.drawable.icon_mytaobao));
		nameList.add("我的淘宝");
		imgList.add(context.getResources().getDrawable(
				R.drawable.icon_shopping_cart));
		nameList.add("购物车");
		imgList.add(context.getResources()
				.getDrawable(R.drawable.icon_wangwang));
		nameList.add("阿里旺旺");
		imgList.add(context.getResources()
				.getDrawable(R.drawable.icon_category));
		nameList.add("商品分类");
		imgList.add(context.getResources()
				.getDrawable(R.drawable.icon_favorite));
		nameList.add("我的收藏");
		imgList.add(context.getResources().getDrawable(R.drawable.icon_history));
		nameList.add("浏览历史");
		imgList.add(context.getResources().getDrawable(
				R.drawable.icon_android_zone));
		nameList.add("安卓专区");
		imgList.add(context.getResources().getDrawable(
				R.drawable.icon_promotion));
		nameList.add("每日精选");
		imgList.add(context.getResources().getDrawable(R.drawable.icon_ju));
		nameList.add("聚划算");
		imgList.add(context.getResources().getDrawable(
				R.drawable.icon_convenience));
		nameList.add("充值中心");
		imgList.add(context.getResources().getDrawable(R.drawable.icon_lottery));
		nameList.add("彩票");
		imgList.add(context.getResources().getDrawable(R.drawable.icon_tmall));
		nameList.add("淘宝商城");
		imgList.add(context.getResources().getDrawable(R.drawable.icon_koubei));
		nameList.add("找优惠");
		imgList.add(context.getResources().getDrawable(R.drawable.icon_tuitui));
		nameList.add("推推");
		imgList.add(context.getResources().getDrawable(
				R.drawable.icon_bar_search));
		nameList.add("二维码");

	}

	public int getCount() {

		return nameList.size();
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
		convertView = mInflater.inflate(R.layout.apps_grid_view, null);
		ImageView img = (ImageView) convertView.findViewById(R.id.img);
		TextView txtName = (TextView) convertView.findViewById(R.id.txtName);
		img.setImageDrawable(imgList.get(position));
		txtName.setText(nameList.get(position));
		return convertView;
	}
}
