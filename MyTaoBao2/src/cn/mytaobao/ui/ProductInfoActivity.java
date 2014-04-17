package cn.mytaobao.ui;

import java.util.ArrayList;
import java.util.List;

import cn.mytaobao.tool.Tool;
import cn.mytaobao.ui.R;
import cn.mytaobao.adapter.CommentAdapter;
import cn.mytaobao.model.Comment;
import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class ProductInfoActivity extends Activity implements OnGestureListener,
		OnTouchListener {
	private Context context;
	private ImageView tab;
	private ViewFlipper detailViewFlipper;
	private GestureDetector mGestureDetector;
	private View detailPhotoView;
	private ImageView img;
	private int position = 0;
	private LinearLayout jianjie;
	private LinearLayout xiangqing;
	private LinearLayout pinglun;
	private LinearLayout fenxiang;
	private LayoutInflater mInflater;
	private Button btnMore;
	private int mCount = 0;
	private ImageView shoppingCart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.product_info);
		context = this;
		mInflater = LayoutInflater.from(context);
		getId();
		
		int pid = getIntent().getIntExtra("pid", 0);
		//Tool.ShowMessage(context, String.valueOf(pid));

		detailViewFlipper.setOnTouchListener(this);
		mGestureDetector = new GestureDetector(this);
		detailPhotoView.setBackgroundDrawable(getDrawable(R.drawable.shouji));
		detailViewFlipper.addView(detailPhotoView);

		tab.setOnTouchListener(new View.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				Log.i("XY", "11111");
				// 鼠标点下的坐标,getX()相对于容器;getRawX()相对于屏幕
				int x = (int) event.getX();
				int y = (int) event.getY();

				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					if (y > 10 && y < 56) {
						if (x > 10 && x < 143) {// 简介
							tab.setBackgroundDrawable(context.getResources()
									.getDrawable(R.drawable.detail_tab1));
							jianjie.setVisibility(View.VISIBLE);
							xiangqing.setVisibility(View.GONE);
							pinglun.setVisibility(View.GONE);
							fenxiang.setVisibility(View.GONE);
						} else if (x > 148 && x < 255) {// 详情
							tab.setBackgroundDrawable(context.getResources()
									.getDrawable(R.drawable.detail_tab2));

							xiangqing.setVisibility(View.VISIBLE);
							jianjie.setVisibility(View.GONE);
							pinglun.setVisibility(View.GONE);
							fenxiang.setVisibility(View.GONE);

						} else if (x > 262 && x < 363) {// 评论
							tab.setBackgroundDrawable(context.getResources()
									.getDrawable(R.drawable.detail_tab3));
							final List<Comment> comments = new ArrayList<Comment>();
							final Comment c1 = new Comment("还挺好的", "lcmz1616",
									"[01.04]");
							final Comment c2 = new Comment(
									"屏幕分辨率比较低，标配没有SD卡，其余很好.", "香香宝妈", "[01.04]");
							final Comment c3 = new Comment(
									"手机还可以，就是没有耳机，收音机听不了，电板也是只有一个.",
									"shanghaifydz", "[01.03]");

							comments.add(c1);
							comments.add(c2);
							comments.add(c3);
							comments.add(c1);
							comments.add(c1);

							final ListView pinglunList = (ListView) findViewById(R.id.pinglunList);
							final CommentAdapter adapter = new CommentAdapter(
									context, comments);
							pinglunList.setAdapter(adapter);

							setListViewHeightBasedOnChildren(pinglunList);
							int count = pinglunList.getAdapter().getCount();
							if (count < 5) {
								btnMore.setVisibility(View.GONE);

							} else {
								btnMore.setVisibility(View.VISIBLE);
							}

							btnMore.setOnClickListener(new View.OnClickListener() {

								public void onClick(View v) {
									mCount++;
									Comment c4 = new Comment(mCount + "还挺好的",
											"lcmz1616", "[01.04]");
									comments.add(c1);
									comments.add(c2);
									comments.add(c3);
									comments.add(c1);
									comments.add(c4);
									pinglunList.setStackFromBottom(true);
									adapter.refresh(comments);
									setListViewHeightBasedOnChildren(pinglunList);

								}
							});
							pinglun.setVisibility(View.VISIBLE);
							jianjie.setVisibility(View.GONE);
							xiangqing.setVisibility(View.GONE);
							fenxiang.setVisibility(View.GONE);

						} else if (x > 371 && x < 473) {// 分享
							tab.setBackgroundDrawable(context.getResources()
									.getDrawable(R.drawable.detail_tab4));

							fenxiang.setVisibility(View.VISIBLE);
							jianjie.setVisibility(View.GONE);
							xiangqing.setVisibility(View.GONE);
							pinglun.setVisibility(View.GONE);

						}

						break;
					}
				}

				return false;
			}
		});

		shoppingCart.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(context, ShoppingCartActivity.class);
				startActivity(intent);

			}
		});

	}

	public void setListViewHeightBasedOnChildren(ListView listView) {

		ListAdapter listAdapter = listView.getAdapter();

		if (listAdapter == null) {

			return;

		}

		int totalHeight = 0;

		for (int i = 0; i < listAdapter.getCount(); i++) {

			View listItem = listAdapter.getView(i, null, listView);

			listItem.measure(0, 0);

			totalHeight += listItem.getMeasuredHeight();

		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();

		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount())) + 50;

		listView.setLayoutParams(params);

	}

	private void getId() {
		tab = (ImageView) findViewById(R.id.tab);
		detailViewFlipper = (ViewFlipper) findViewById(R.id.detailViewFlipper);
		detailPhotoView = LayoutInflater.from(context).inflate(
				R.layout.detail_photo_view, null);
		img = (ImageView) detailPhotoView.findViewById(R.id.img);
		jianjie = (LinearLayout) findViewById(R.id.jianjie);
		xiangqing = (LinearLayout) findViewById(R.id.xiangqing);
		pinglun = (LinearLayout) findViewById(R.id.pinglun);
		btnMore = (Button) findViewById(R.id.btnMore);
		fenxiang = (LinearLayout) findViewById(R.id.fenxiang);
		shoppingCart = (ImageView) findViewById(R.id.shoppingCart);

	}

	public boolean onTouch(View v, MotionEvent event) {
		return mGestureDetector.onTouchEvent(event);
	}

	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {

		if (e1.getX() > e2.getX() && position < 2) {// 左移
			position++;

			if (detailPhotoView != null)
				detailViewFlipper.removeView(detailPhotoView);

			img.setImageDrawable(getDrawable(R.drawable.ic_launcher));

			detailViewFlipper.setInAnimation(context.getApplicationContext(),
					R.anim.push_left_in);
			detailViewFlipper.setOutAnimation(context.getApplicationContext(),
					R.anim.push_left_out);
			detailViewFlipper.addView(detailPhotoView);
			detailViewFlipper.showNext();

		} else if (e1.getX() < e2.getX() && position > 0) {// 右移
			position--;
			img.setImageDrawable(getDrawable(R.drawable.shouji));
			detailViewFlipper.setInAnimation(context.getApplicationContext(),
					R.anim.push_right_in);
			detailViewFlipper.setOutAnimation(context.getApplicationContext(),
					R.anim.push_right_out);
			detailViewFlipper.showPrevious();
		}
		return true;

	}

	private Drawable getDrawable(int id) {
		return getResources().getDrawable(id);
	}

	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return true;
	}

	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

}
