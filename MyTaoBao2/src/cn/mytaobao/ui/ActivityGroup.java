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
		// ������ͼ
		setContentView(R.layout.activity_group_view);
		context = this;
		getId();

		container = (FrameLayout) findViewById(R.id.containerBody);
		// Ĭ��������
		//Intent.FLAG_ACTIVITY_CLEAR_TOP ������ڵ�ǰTask�У���Ҫ������Activity��
		//��ô�Ѹ�Acitivity֮ǰ������Activity���ص���
		//���Ѵ�Activity��ǰ�Ա��ⴴ��Activity��ʵ��
		container.addView(getLocalActivityManager().startActivity(
				"MainShowActivity",
				new Intent(context, MainShowActivity.class)
						.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
				.getDecorView());
		// ������
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

		// Apps����
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
		// �޻���
		juhuasuan.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				/*Toast.makeText(context, "��δ���ţ������ڴ�����", Toast.LENGTH_LONG)
						.show();*/
				container.removeAllViews();
				container.addView(getLocalActivityManager().startActivity(
						"testActivity",
						new Intent(context, testActivity.class)
								.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
						.getDecorView());

			}
		});
		// �̳�
		Tmall.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Toast.makeText(context, "��δ���ţ������ڴ�����", Toast.LENGTH_LONG)
						.show();

			}
		});
		// ����
		wuliu.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Toast.makeText(context, "����", Toast.LENGTH_LONG).show();

			}
		});
		// �ҵ��Ա�
		myTaobao.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(context, LoginActivity.class);
				startActivity(intent);
				Toast.makeText(context, "�ҵ��Ա�", Toast.LENGTH_LONG).show();

			}
		});

		// ���ﳵ
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
