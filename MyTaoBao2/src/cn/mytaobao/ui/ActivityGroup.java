package cn.mytaobao.ui;


import cn.mytaobao.login.LoginActivity;
import cn.mytaobao.ui.R;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

public class ActivityGroup extends android.app.ActivityGroup {
	private FrameLayout container = null;

	private ImageView btnMain;
	private RelativeLayout home;
	private RelativeLayout juhuasuan;
	private RelativeLayout Tmall;
	private RelativeLayout wuliu;
	private RelativeLayout myTaobao;
	private RelativeLayout shoppingCart;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 设置视图
		setContentView(R.layout.activity_group_view);
		context = this;
		getId();

		container = (FrameLayout) findViewById(R.id.containerBody);
		// 默认主界面
		//Intent.FLAG_ACTIVITY_CLEAR_TOP ：如果在当前Task中，有要启动的Activity，
		//那么把该Acitivity之前的所有Activity都关掉，
		//并把此Activity置前以避免创建Activity的实例
		container.addView(getLocalActivityManager().startActivity(
				"MainShowActivity",
				new Intent(context, MainShowActivity.class)
						.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
				.getDecorView());
		// 主界面
		home.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				container.removeAllViews();
				container.addView(getLocalActivityManager().startActivity(
						"MainShowActivity",
						new Intent(context, MainShowActivity.class)
								.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
						.getDecorView());

			}
		});

		// Apps界面
		btnMain.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				container.removeAllViews();
				container.addView(getLocalActivityManager().startActivity(
						"AppsShowActivity",
						new Intent(context, AppsShowActivity.class)
								.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
						.getDecorView());

			}
		});
		// 巨划算
		juhuasuan.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				/*Toast.makeText(context, "暂未开放，敬请期待。。", Toast.LENGTH_LONG)
						.show();*/
				container.removeAllViews();
				container.addView(getLocalActivityManager().startActivity(
						"testActivity",
						new Intent(context, testActivity.class)
								.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
						.getDecorView());

			}
		});
		// 商城
		Tmall.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Toast.makeText(context, "暂未开放，敬请期待。。", Toast.LENGTH_LONG)
						.show();

			}
		});
		// 物流
		wuliu.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Toast.makeText(context, "物流", Toast.LENGTH_LONG).show();

			}
		});
		// 我的淘宝
		myTaobao.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(context, LoginActivity.class);
				startActivity(intent);
				Toast.makeText(context, "我的淘宝", Toast.LENGTH_LONG).show();

			}
		});

		// 购物车
		shoppingCart.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				container.removeAllViews();
				container.addView(getLocalActivityManager().startActivity(
						"ShoppingCartActivity",
						new Intent(context, ShoppingCartActivity.class)
								.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
						.getDecorView());

			}
		});

	}

	private void getId() {
		btnMain = (ImageView) findViewById(R.id.btnMain);
		home = (RelativeLayout) findViewById(R.id.home);
		juhuasuan = (RelativeLayout) findViewById(R.id.juhuasuan);
		Tmall = (RelativeLayout) findViewById(R.id.Tmall);
		wuliu = (RelativeLayout) findViewById(R.id.wuliu);
		myTaobao = (RelativeLayout) findViewById(R.id.myTaobao);
		shoppingCart = (RelativeLayout) findViewById(R.id.shoppingCart);

	}

}
