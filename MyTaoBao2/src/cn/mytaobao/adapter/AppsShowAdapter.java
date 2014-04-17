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
		nameList.add("������");

		imgList.add(context.getResources()
				.getDrawable(R.drawable.icon_mytaobao));
		nameList.add("�ҵ��Ա�");
		imgList.add(context.getResources().getDrawable(
				R.drawable.icon_shopping_cart));
		nameList.add("���ﳵ");
		imgList.add(context.getResources()
				.getDrawable(R.drawable.icon_wangwang));
		nameList.add("��������");
		imgList.add(context.getResources()
				.getDrawable(R.drawable.icon_category));
		nameList.add("��Ʒ����");
		imgList.add(context.getResources()
				.getDrawable(R.drawable.icon_favorite));
		nameList.add("�ҵ��ղ�");
		imgList.add(context.getResources().getDrawable(R.drawable.icon_history));
		nameList.add("�����ʷ");
		imgList.add(context.getResources().getDrawable(
				R.drawable.icon_android_zone));
		nameList.add("��׿ר��");
		imgList.add(context.getResources().getDrawable(
				R.drawable.icon_promotion));
		nameList.add("ÿ�վ�ѡ");
		imgList.add(context.getResources().getDrawable(R.drawable.icon_ju));
		nameList.add("�ۻ���");
		imgList.add(context.getResources().getDrawable(
				R.drawable.icon_convenience));
		nameList.add("��ֵ����");
		imgList.add(context.getResources().getDrawable(R.drawable.icon_lottery));
		nameList.add("��Ʊ");
		imgList.add(context.getResources().getDrawable(R.drawable.icon_tmall));
		nameList.add("�Ա��̳�");
		imgList.add(context.getResources().getDrawable(R.drawable.icon_koubei));
		nameList.add("���Ż�");
		imgList.add(context.getResources().getDrawable(R.drawable.icon_tuitui));
		nameList.add("����");
		imgList.add(context.getResources().getDrawable(
				R.drawable.icon_bar_search));
		nameList.add("��ά��");

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
