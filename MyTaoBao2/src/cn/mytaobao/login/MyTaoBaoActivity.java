package cn.mytaobao.login;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.mytaobao.ui.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MyTaoBaoActivity extends Activity {
	//private Button btnRegist = null;
	private Context context;
	private ImageView lable;
	private int left,right,bottom,top;
	private int[] location ;
	private LayoutInflater inflater=null;
	private LinearLayout order_manager=null;
	private ListView lv=null;
	private MyTaoBaoAdapter adapter=null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 设置全屏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.mytaobao);
		init();
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent=new Intent(context,OrderDetailActivity.class);
				startActivity(intent);
			}
		});
		
		//location = new  int[2] ;
		//lable.getLocationInWindow(location); //获取在当前窗口内的绝对坐标
		//lable.getLocationOnScreen(location);//获取在整个屏幕内的绝对坐标
		

		
		//location [0]--->x坐标,location [1]--->y坐标


		/*btnRegist = (Button) this.findViewById(R.id.userRegist);
		btnRegist.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, RegisterActivity.class);
				startActivity(intent);
			}
		});*/
		
		//实现onTouchEvent方法 
		

	}
	private void init() {
		context = this;
		lable=(ImageView) this.findViewById(R.id.lable);
		inflater=LayoutInflater.from(context);
		lv=(ListView) this.findViewById(R.id.order_lv);
		adapter=new MyTaoBaoAdapter();
		order_manager=(LinearLayout) this.findViewById(R.id.order_manager);
		
	}
	public boolean onTouchEvent(MotionEvent event) { 
		//如果是按下操作 
		if(bottom==MotionEvent.ACTION_DOWN){
			left=lable.getLeft();
			 right=lable.getRight();
			 bottom=lable.getBottom();
			 top=lable.getTop();
			 
			 Toast.makeText(context, ""+top+"-----"+bottom, 3000).show();
		}
		if (event.getAction() == MotionEvent.ACTION_DOWN) { 
			Toast.makeText(context,event.getX()+":" +event.getY()+"", 3000).show();
		showXY(event.getX(), event.getY()); 
		} 
		return super.onTouchEvent(event); 
		} 

		//获取到坐标，进行判断 
		private void showXY(float x, float y) { 
		
		if (x > 0 && y >90 && x < 165 && y < 155) {
			lable.setBackgroundResource(R.drawable.mytaobao_order);
			order_manager.setVisibility(View.VISIBLE);
			
			
		//tv.setText("点中了"); 
		} else if(x > 165 && y >90 && x < 326 && y < 155){ 
			lable.setBackgroundResource(R.drawable.mytaobao_baobei);
			order_manager.setVisibility(View.GONE);
		//tv.setText("x坐标：" + x + " y坐标：" + y); 
		} 
		else if(x > 326 && y >90  && y < 155){
		lable.setBackgroundResource(R.drawable.mytaobao_shop);
		order_manager.setVisibility(View.GONE);
		}
		} 
		class MyTaoBaoAdapter extends BaseAdapter{
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
				convertView=inflater.inflate(R.layout.order_manager_item, null);
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