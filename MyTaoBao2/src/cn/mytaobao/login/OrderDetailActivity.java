package cn.mytaobao.login;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.mytaobao.login.MyTaoBaoActivity.MyTaoBaoAdapter;
import cn.mytaobao.ui.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class OrderDetailActivity extends Activity{
	private Context context;
	private LayoutInflater inflater=null;
	private ListView lv=null;
	private MyOrderDetailAdapter adapter=null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 设置全屏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.order_detail);
		init();
		View header=inflater.inflate(R.layout.listviewheader, null);
		//在listview上加个控件一起滚动
		lv.addHeaderView(header, null, false);
		lv.setAdapter(adapter);
		
		
	}
	private void init() {
		context = this;
		inflater=LayoutInflater.from(context);
		lv=(ListView) this.findViewById(R.id.order_detail_lv);
		adapter=new MyOrderDetailAdapter();
		
	}
	class MyOrderDetailAdapter extends BaseAdapter{
		ImageView product_photo=null;
		TextView product_name=null;
		TextView product_price=null;
		TextView product_count=null;
		TextView product_date=null;

		
		public int getCount() {
			// TODO Auto-generated method stub
			return 3;
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
			if(convertView==null)
			convertView=inflater.inflate(R.layout.order_detail_item, null);
			product_photo=(ImageView) convertView.findViewById(R.id.product_photo);
			product_name=(TextView) convertView.findViewById(R.id.product_name);
			product_price=(TextView) convertView.findViewById(R.id.product_price);
			product_count=(TextView) convertView.findViewById(R.id.product_count);
			product_date=(TextView) convertView.findViewById(R.id.product_date);
			product_photo.setBackgroundResource(R.drawable.test);
			product_name.setText("2011秋冬装新品新款女装");
			product_price.setText("113.00元");
			product_count.setText("(共"+3+"件)");
			Date d=new Date(System.currentTimeMillis());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); // 格式化日期 年月日 时分秒
			product_date.setText(dateFormat.format(d));
			
			return convertView;
		}
		
	}

}
