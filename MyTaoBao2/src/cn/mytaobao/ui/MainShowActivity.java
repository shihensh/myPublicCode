package cn.mytaobao.ui;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cn.mytaobao.adapter.MainShowAdapter;
import cn.mytaobao.adapter.ShopingAdapter;
import cn.mytaobao.biz.ProductManager;
import cn.mytaobao.constant.IMyTaoBao;
import cn.mytaobao.model.Product;
import cn.mytaobao.tool.FileService;
import cn.mytaobao.tool.FileUtil;
import cn.mytaobao.tool.HttpUtil;
import cn.mytaobao.tool.ImageTools;
import cn.mytaobao.tool.StreamTool;
import cn.mytaobao.tool.Tool;
import cn.mytaobao.ui.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainShowActivity extends Activity implements OnTouchListener,
		OnGestureListener {

	private ViewFlipper viewFlipper;
	private GestureDetector mGestureDetector;
	private View phoneShowView;
	private Context context;
	/*
	 * private int[] image_ids = { R.drawable.ad_1, R.drawable.ad_2,
	 * R.drawable.ad_3, R.drawable.ad_4, R.drawable.ad_5 };
	 */
	//add by jhao
	private List<Bitmap> image_list = new ArrayList<Bitmap>();
	private List<String> image_url = new ArrayList<String>();
	
	private Map<Integer, String> menus;
	private ImageView img;
	private int position = 0;
	private Thread thread;
	private GridView gridView;
	private ShopingAdapter adapter;
	private TableRow row;
	private LinearLayout category;
	private LinearLayout recommend;
	private LinearLayout top10;
	private ProductManager productManager;
	// add by jhao
	private static final String AdvURL = IMyTaoBao.WEB_BASE_SERVER+"AdvServlet"; //"http://192.168.2.105:8080/TaoBaoWeb/AdvServlet";
	private static final String BASEURL = IMyTaoBao.WEB_BASE_SERVER;

	// add by jhao
	LayoutInflater mInflater = null;

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what == 1) {// 广告走马灯

				if (phoneShowView != null){
					viewFlipper.removeView(phoneShowView);
				}

				// position = position % image_ids.length;
				position = position % image_list.size();
				//Tool.ShowMessage(context, String.valueOf(image_list.size()));
				img.setImageDrawable(getDrawable(position));
				img.setTag(position);
				//img.setTag(position-1);
				
				position++;
				
				img.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						int index =Integer.parseInt(v.getTag().toString()) ;
						//Tool.ShowMessage(context, String.valueOf(index));
						Tool.ShowMessage(context,image_url.get(index));
					
	                    Intent fIntent = new Intent(Intent.ACTION_VIEW,Uri.parse(image_url.get(index)));
						fIntent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
	                    startActivity(fIntent);
				}
				});

				viewFlipper.setInAnimation(context.getApplicationContext(),
						R.anim.push_left_in);
				viewFlipper.setOutAnimation(context.getApplicationContext(),
						R.anim.push_left_out);
				viewFlipper.addView(phoneShowView);
				viewFlipper.showNext();
				

			} else if (msg.what==2){//加载商品列表

				    final List<Product> products =(ArrayList<Product>)msg.obj;
					adapter = new ShopingAdapter(MainShowActivity.this,products);
					gridView.setNumColumns(3);
					gridView.setAdapter(adapter);
					gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
						public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
								long arg3) {
							Product p = products.get(arg2);
							int pid = p.getpId();
							Intent intent = new Intent(context, ProductInfoActivity.class);
							intent.putExtra("pid", pid);
							//Tool.ShowMessage(context, String.valueOf(pid));
							startActivity(intent);
						}
					});

			}
		}

	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_show);

		menus = new HashMap<Integer, String>();
		// menus.put(R., value)

		context = this;
		productManager = new ProductManager();
		// edit by jhao
		mInflater = LayoutInflater.from(this);

		getId();

		AdvTask task = new AdvTask(this);

		task.execute(AdvURL);

		// 获取数据
		Thread thread2 = new Thread(new Runnable() {

			public void run() {
				int pageIndex = 0;
				int pageSize = 9;
				int parentId = 11;
				List<Product> products = new ArrayList<Product>();
				products = productManager.getProductByCategoryByPage(pageIndex,
						pageSize, parentId);
				
				Message msg = new Message();
				msg.what=2;
				msg.obj = products;
				handler.sendMessage(msg);
				//Log.i("products", products + "");
				

			}
		});
		thread2.start();
		// img.setImageDrawable(getDrawable(position));

		viewFlipper.setOnTouchListener(this);
		mGestureDetector = new GestureDetector(this);
		viewFlipper.addView(phoneShowView);

		/*adapter = new MainShowAdapter(context);
		gridView.setNumColumns(3);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(context, ProductInfoActivity.class);
				startActivity(intent);
			}
		});*/

		// 类别
		category.setOnClickListener(listener);
		// 每日推荐
		recommend.setOnClickListener(listener);

		// 金牌店铺
		top10.setOnClickListener(listener);

		// 广告走马灯
		thread = new Thread(new Runnable() {

			public void run() {
				while (true) {
					try {
						Thread.sleep(5000);
						handler.sendEmptyMessage(1);

					} catch (Exception e) {
						// TODO: handle exception
					}

				}
			}

		});

	}

	OnClickListener listener = new View.OnClickListener() {

		public void onClick(View v) {
			Intent intent;
			switch (v.getId()) {

			case R.id.recommend:// 每日推荐
				intent = new Intent(context, ProductInfoActivity.class);
				startActivity(intent);
				break;

			case R.id.category:// 类别
				intent = new Intent(context, CategoryActivity.class);
				startActivity(intent);
				break;
			case R.id.top10:// 金牌店铺
				Toast.makeText(context, "暂未开放，敬请期待。。", Toast.LENGTH_LONG)
						.show();
				break;
			}

		}
	};

	private void getId() {
		viewFlipper = (ViewFlipper) findViewById(R.id.vfPhone);
		gridView = (GridView) findViewById(R.id.mainList);
		category = (LinearLayout) findViewById(R.id.category);
		recommend = (LinearLayout) findViewById(R.id.recommend);
		top10 = (LinearLayout) findViewById(R.id.top10);

		// phoneShowView = mInflater.inflate(R.layout.ad_view, null);
		// img = (ImageView) phoneShowView.findViewById(R.id.img);
	}

	private Drawable getDrawable(int position) {
		// return getResources().getDrawable(image_ids[position]);
		return ImageTools.bitmapToDrawable(image_list.get(position));

	}

	public boolean onTouch(View v, MotionEvent event) {
		return mGestureDetector.onTouchEvent(event);
	}

	public boolean onDown(MotionEvent e) {
		return true;
	}

	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {

		if (e1.getX() > e2.getX()) {// 左移

			if (phoneShowView != null)
				viewFlipper.removeView(phoneShowView);
			position++;
			position = position % image_list.size(); // image_ids.length;
			img.setImageDrawable(getDrawable(position));

			viewFlipper.setInAnimation(context.getApplicationContext(),
					R.anim.push_left_in);
			viewFlipper.setOutAnimation(context.getApplicationContext(),
					R.anim.push_left_out);
			viewFlipper.addView(phoneShowView);
			viewFlipper.showNext();

		} else if (e1.getX() < e2.getX()) {// 右移
			position--;
			position = Math.abs(position) % image_list.size(); // image_ids.length;
			img.setImageDrawable(getDrawable(position));
			viewFlipper.setInAnimation(context.getApplicationContext(),
					R.anim.push_right_in);
			viewFlipper.setOutAnimation(context.getApplicationContext(),
					R.anim.push_right_out);
			viewFlipper.showPrevious();

		}

		return true;
	}

	public void onLongPress(MotionEvent e) {

	}

	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {

		return true;
	}

	public void onShowPress(MotionEvent e) {

	}

	public boolean onSingleTapUp(MotionEvent e) {

		return true;
	}

	// add by jhao
	class AdvTask extends AsyncTask<String, Integer, String> {
		private FileService fileService = null;

		private Context context = null;

		public AdvTask(Context context) {
			this.fileService = new FileService(context);
			this.context = context;
		}

		@Override
		protected void onPreExecute() {
			phoneShowView = mInflater.inflate(R.layout.loading, null);
		}

		@Override
		protected void onPostExecute(String result) {
			// super.onPostExecute(result);
			if (result.equals("")) { // 说明没有从服务端获取数据
				Tool.ShowMessage(context, "服务器广告数据并无更新!");
			} else {
				Tool.ShowMessage(context, "服务器获取数据成功!!");
			}
			// 从本地的Cache目录的/advImage/下获取图片数据
			image_list.clear();
			String ImgCacheDir = fileService.getCacheDirectory() + "/advImage/";
			File fileImgCacheDir = new File(ImgCacheDir);
			File[] files = fileImgCacheDir.listFiles();
			String fileLog ="";
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				if(file.getName().endsWith(".jpg"))
				image_list.add(ImageTools.getBitemapFromFile(file));
				if (file.getName().endsWith(".txt"))
					fileLog = fileService.getString2Cache(file.getAbsolutePath());
			}
			
			Tool.ShowMessage(context, fileLog);

			if (phoneShowView!=null){
				viewFlipper.removeView(phoneShowView);
				//viewFlipper.removeAllViews();
			}
			phoneShowView = mInflater.inflate(R.layout.ad_view, null);
			img = (ImageView) phoneShowView.findViewById(R.id.img);
			

			
			
			try {
				image_url.clear();
				//用于获取adv url
				if (!fileLog.equals("")){
					String[] urls = fileLog.split(",");
					for (String url : urls) {
						String advUrl = url.split("\\|")[1];
						image_url.add(advUrl);
					}
				}
			}catch(Exception ex){

			}
			thread.start();
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
		}

		@Override
		protected void onCancelled() {
			super.onCancelled();
		}

		@Override
		protected String doInBackground(String... params) {
			String subPath = "/advImage/";
			String result = HttpUtil.queryStringForGet(params[0]);
			
			String data = "";
			if (result.equals("")) // 说明没有最新数据
			{
				// 从Cache中读取数据

			} else {// 写Cache文件
				String[] files = result.split(",");
				
				try {
					image_url.clear();
					for (String fileStr : files) {
						String file = fileStr.split("\\|")[0];
						String advUrl = fileStr.split("\\|")[1];
						if (!file.equals("")){
							String picUrl = BASEURL + file;
							String fileName = new File(picUrl).getName();
							Bitmap bm = StreamTool.getURLBitmap(picUrl);// 通过网络获取图片
							fileService.save2CacheDir(subPath, fileName, bm);// 将图片保存到手机Catch
							if (!advUrl.equals("")) image_url.add(advUrl);
						}
					}
			
					//写日专，用于保存adv url路径
					fileService.save2CacheDir(subPath, "advlog.txt", result);
					
					data = "ok"; // 说明从服务器端获取数据成功
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			return data;
		}

	}

	/*
	 * add by tong
	 */
	public void parseProductFromJson(String jsonData) { 
		 
        Type typeList = new TypeToken<ArrayList<Product>>(){}.getType(); 
        Gson gson = new Gson(); 
        ArrayList<Product> products = gson.fromJson(jsonData, typeList); 
        for(Product product:products) { 
            System.out.println("name---------->" + product.getpName()); 
            System.out.println("price---------->" + product.getpPrice()); 
        } 
    } 
 

	
	// add by jhao
	class ProductTask extends AsyncTask<String, Integer, String> {
		

		private Context context = null;

		public ProductTask(Context context) {

			this.context = context;
		}

		@Override
		protected void onPreExecute() {
			phoneShowView = mInflater.inflate(R.layout.loading, null);
		}

		@Override
		protected void onPostExecute(String result) {
			
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
		}

		@Override
		protected void onCancelled() {
			super.onCancelled();
		}

		@Override
		protected String doInBackground(String... params) {

			return "";
		}

	}

}