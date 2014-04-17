package cn.mytaobao.ui;

import cn.mytaobao.adapter.ProductListAdapter;
import cn.mytaobao.ui.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

public class CategoryProductActivity extends Activity {
	private ImageView populate;
	private ImageView credit;
	private ImageView price;
	private ImageView seller;
	private Context context;
	private boolean priceDwon = true;
	private String currentDown="populate";
	//private String currentSortType="desc";
	private ListView listView;
	private ProductListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category_produtc_view);
		context = this;
		getId();

		adapter = new ProductListAdapter(context);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(context, ProductInfoActivity.class);
				startActivity(intent);

			}
		});

		// 人气
		populate.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				//priceDwon = true;
				currentDown="populate";
				populate.setBackgroundDrawable(getDrawable(R.drawable.populate_on));
				credit.setBackgroundDrawable(getDrawable(R.drawable.credit_nm));
				price.setBackgroundDrawable(getDrawable(R.drawable.price_nm));
				seller.setBackgroundDrawable(getDrawable(R.drawable.seller_nm));
				
				if (v.getTag()==null){
					v.setTag("asc");
				}else{
					String type = v.getTag().toString().equals("asc")?"desc":"asc";
					v.setTag(type);
					//if (v.getTag().toString().equals(object))
				}

			}
		});
		// 信用
		credit.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				//priceDwon = true;
				currentDown="credit";
				populate.setBackgroundDrawable(getDrawable(R.drawable.populate_nm));
				credit.setBackgroundDrawable(getDrawable(R.drawable.credit_on));
				price.setBackgroundDrawable(getDrawable(R.drawable.price_nm));
				seller.setBackgroundDrawable(getDrawable(R.drawable.seller_nm));

			}
		});
		// 价格
		price.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				currentDown="price";
				if (priceDwon) {
					price.setBackgroundDrawable(getDrawable(R.drawable.price_on_down));
					priceDwon = false;

				} else {
					price.setBackgroundDrawable(getDrawable(R.drawable.price_on_up));
					priceDwon = true;

				}

				populate.setBackgroundDrawable(getDrawable(R.drawable.populate_nm));
				credit.setBackgroundDrawable(getDrawable(R.drawable.credit_nm));

				seller.setBackgroundDrawable(getDrawable(R.drawable.seller_nm));

			}
		});
		// 销量
		seller.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				currentDown="seller";
				priceDwon = true;
				populate.setBackgroundDrawable(getDrawable(R.drawable.populate_nm));
				credit.setBackgroundDrawable(getDrawable(R.drawable.credit_nm));
				price.setBackgroundDrawable(getDrawable(R.drawable.price_nm));
				seller.setBackgroundDrawable(getDrawable(R.drawable.seller_on));

			}
		});
	}

	private Drawable getDrawable(int id) {
		return context.getResources().getDrawable(id);

	}

	private void getId() {
		listView = (ListView) findViewById(R.id.list);
		populate = (ImageView) findViewById(R.id.populate);
		credit = (ImageView) findViewById(R.id.credit);
		price = (ImageView) findViewById(R.id.price);
		seller = (ImageView) findViewById(R.id.seller);

	}

}
