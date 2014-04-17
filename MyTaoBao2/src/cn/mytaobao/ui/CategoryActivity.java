package cn.mytaobao.ui;

import java.util.ArrayList;
import java.util.List;

import cn.mytaobao.adapter.SuperTreeViewAdapter;
import cn.mytaobao.ui.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;

public class CategoryActivity extends Activity {
	private Context context;
	private ExpandableListView expandableList;
	private SuperTreeViewAdapter superAdapter;

	public List<String> parent = new ArrayList<String>();
	public List<List<List<String>>> grandson = new ArrayList<List<List<String>>>();
	public List<String> mmGrandson = new ArrayList<String>();
	public List<List<String>> mGrandson = new ArrayList<List<String>>();
	public List<String> mChild = new ArrayList<String>();
	public List<List<String>> child = new ArrayList<List<String>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category_view);
		context = this;
		getId();

		// 模拟数据
		parent.add("虚拟");
		parent.add("服装");

		mChild.add("充值中心");
		mChild.add("游戏");
		mChild.add("彩票");
		mChild.add("网上营业厅");
		child.add(mChild);

		mChild = new ArrayList<String>();
		mChild.add("女装");
		mChild.add("男装");
		mChild.add("内衣");
		mChild.add("秋冬新款");
		child.add(mChild);

		mmGrandson.add("移动");
		mmGrandson.add("联通");
		mGrandson.add(mmGrandson);

		mmGrandson = new ArrayList<String>();
		mmGrandson.add("dnf");
		mmGrandson.add("梦幻");
		mGrandson.add(mmGrandson);

		mmGrandson = new ArrayList<String>();
		mmGrandson.add("足彩");
		mmGrandson.add("双色球");
		mGrandson.add(mmGrandson);

		mmGrandson = new ArrayList<String>();
		mmGrandson.add("3G上网");
		mmGrandson.add("小米");
		mGrandson.add(mmGrandson);
		grandson.add(mGrandson);
		
		
		mGrandson = new ArrayList<List<String>>();
		mmGrandson = new ArrayList<String>();
		mmGrandson.add("呢大衣");
		mmGrandson.add("打底");
		mGrandson.add(mmGrandson);
		
		mmGrandson = new ArrayList<String>();
		mmGrandson.add("羽絨衣");
		mmGrandson.add("棉衣");
		mGrandson.add(mmGrandson);
		
		mmGrandson = new ArrayList<String>();
		mmGrandson.add("保暖");
		mmGrandson.add("文胸");
		mGrandson.add(mmGrandson);
		
		mmGrandson = new ArrayList<String>();
		mmGrandson.add("毛衣");
		mmGrandson.add("皮外衣");
		mGrandson.add(mmGrandson);
		
		grandson.add(mGrandson);
		
		
		
		superAdapter = new SuperTreeViewAdapter(this, stvClickEvent);
		expandableList = (ExpandableListView) findViewById(R.id.ExpandableListView01);
		expandableList.setGroupIndicator(null);// 不要自带的了！！！

		List<SuperTreeViewAdapter.SuperTreeNode> superTreeNode = superAdapter
				.GetTreeNode();
		for (int i = 0; i < parent.size(); i++)// 第一层
		{
			SuperTreeViewAdapter.SuperTreeNode superNode = new SuperTreeViewAdapter.SuperTreeNode();
			superNode.parent = parent.get(i);

			// 第二层
			for (int ii = 0; ii < child.get(i).size(); ii++) {
				cn.mytaobao.adapter.TreeViewAdapter.TreeNode node = new cn.mytaobao.adapter.TreeViewAdapter.TreeNode();
				node.parent = child.get(i).get(ii);// 第二级菜单的标题

				for (int iii = 0; iii < grandson.get(i).get(ii).size(); iii++)// 第三级菜单
				{
					Log.i("grandson", grandson.get(i).get(ii).get(iii).toString());
					node.childs.add(grandson.get(i).get(ii).get(iii));
				}
				superNode.childs.add(node);
			}
			superTreeNode.add(superNode);

		}
		superAdapter.UpdateTreeNode(superTreeNode);
		expandableList.setAdapter(superAdapter);

	}

	/**
	 * 三级树形菜单的事件不再可用，本函数由三级树形菜单的子项（二级菜单）进行回调
	 */
	OnChildClickListener stvClickEvent = new OnChildClickListener() {

		public boolean onChildClick(ExpandableListView parent, View v,
				int groupPosition, int childPosition, long id) {
			String str = "parent id:" + String.valueOf(groupPosition)
					+ ",children id:" + String.valueOf(childPosition);
			Toast.makeText(context, str, 300).show();
			Intent intent = new Intent(context, CategoryProductActivity.class);
			startActivity(intent);

			return false;
		}

	};

	private void getId() {

	}

}
