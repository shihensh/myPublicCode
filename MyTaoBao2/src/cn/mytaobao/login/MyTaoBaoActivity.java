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
		// �����ޱ���
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// ����ȫ��
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
		//lable.getLocationInWindow(location); //��ȡ�ڵ�ǰ�����ڵľ�������
		//lable.getLocationOnScreen(location);//��ȡ��������Ļ�ڵľ�������
		

		
		//location [0]--->x����,location [1]--->y����


		/*btnRegist = (Button) this.findViewById(R.id.userRegist);
		btnRegist.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, RegisterActivity.class);
				startActivity(intent);
			}
		});*/
		
		//ʵ��onTouchEvent���� 
		

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
		//����ǰ��²��� 
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

		//��ȡ�����꣬�����ж� 
		private void showXY(float x, float y) { 
		
		if (x > 0 && y >90 && x < 165 && y < 155) {
			lable.setBackgroundResource(R.drawable.mytaobao_order);
			order_manager.setVisibility(View.VISIBLE);
			
			
		//tv.setText("������"); 
		} else if(x > 165 && y >90 && x < 326 && y < 155){ 
			lable.setBackgroundResource(R.drawable.mytaobao_baobei);
			order_manager.setVisibility(View.GONE);
		//tv.setText("x���꣺" + x + " y���꣺" + y); 
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
				product_name.setText("2011�ﶬװ��Ʒ�¿�Ůװ");
				product_price.setText("113.00Ԫ");
				product_count.setText("(��"+3+"��)");
				Date d=new Date(System.currentTimeMillis());
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); // ��ʽ������ ������ ʱ����
				product_date.setText(dateFormat.format(d));
				
				return convertView;
			}
			
		}
}