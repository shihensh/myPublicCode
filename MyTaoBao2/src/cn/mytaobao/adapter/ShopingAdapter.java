package cn.mytaobao.adapter;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import cn.mytaobao.constant.IMyTaoBao;
import cn.mytaobao.model.Product;

import cn.mytaobao.ui.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ShopingAdapter extends BaseAdapter {
	private List<Product> products;
	private Context mContext=null;
	private LayoutInflater mInflater;
	

	public ShopingAdapter(Context context,List<Product> products) {
		this.mContext = context;
		this.mInflater = LayoutInflater.from(context);
		this.products = products;
	}

	public int getCount() {
		return products.size();
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = mInflater.inflate(R.layout.main_grid_view, null);
		Product product = products.get(position);
		ImageView img = (ImageView) convertView.findViewById(R.id.img);
		TextView txtName = (TextView) convertView.findViewById(R.id.txtName);
		TextView txtMoney = (TextView) convertView.findViewById(R.id.txtMoney);
		//img.setImageDrawable(mContext.getResources().getDrawable(
				//product.getImage()));
		txtName.setText(product.getpName());
		txtMoney.setText(String.valueOf(product.getpPrice()));
		
		Bitmap bm = getURLBitmap(IMyTaoBao.WEB_BASE_SERVER+product.getpImage());
		img.setImageBitmap(bm);
		return convertView;
	}
	
	public Bitmap getURLBitmap(String uriPic)
	  {
	    URL imageUrl = null;
	    Bitmap bitmap = null;
	    try
	    {
	      /* new URL对象将网址传入 */
	      imageUrl = new URL(uriPic);
	    } catch (MalformedURLException e)
	    {
	      e.printStackTrace();
	    }
	    try
	    {
	      /* 取得联机 */
	      HttpURLConnection conn = (HttpURLConnection) imageUrl
	          .openConnection();
	      conn.connect();
	      /* 取得回传的InputStream */
	      InputStream is = conn.getInputStream();
	      /* 将InputStream变成Bitmap */
	      bitmap = BitmapFactory.decodeStream(is);
	      /* 关闭InputStream */
	      is.close();
	      
	    } catch (IOException e)
	    {
	      e.printStackTrace();
	    }
	    return bitmap;
	  }

}
