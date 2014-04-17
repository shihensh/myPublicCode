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

		// ģ������
		parent.add("����");
		parent.add("��װ");

		mChild.add("��ֵ����");
		mChild.add("��Ϸ");
		mChild.add("��Ʊ");
		mChild.add("����Ӫҵ��");
		child.add(mChild);

		mChild = new ArrayList<String>();
		mChild.add("Ůװ");
		mChild.add("��װ");
		mChild.add("����");
		mChild.add("�ﶬ�¿�");
		child.add(mChild);

		mmGrandson.add("�ƶ�");
		mmGrandson.add("��ͨ");
		mGrandson.add(mmGrandson);

		mmGrandson = new ArrayList<String>();
		mmGrandson.add("dnf");
		mmGrandson.add("�λ�");
		mGrandson.add(mmGrandson);

		mmGrandson = new ArrayList<String>();
		mmGrandson.add("���");
		mmGrandson.add("˫ɫ��");
		mGrandson.add(mmGrandson);

		mmGrandson = new ArrayList<String>();
		mmGrandson.add("3G����");
		mmGrandson.add("С��");
		mGrandson.add(mmGrandson);
		grandson.add(mGrandson);
		
		
		mGrandson = new ArrayList<List<String>>();
		mmGrandson = new ArrayList<String>();
		mmGrandson.add("�ش���");
		mmGrandson.add("���");
		mGrandson.add(mmGrandson);
		
		mmGrandson = new ArrayList<String>();
		mmGrandson.add("��q��");
		mmGrandson.add("����");
		mGrandson.add(mmGrandson);
		
		mmGrandson = new ArrayList<String>();
		mmGrandson.add("��ů");
		mmGrandson.add("����");
		mGrandson.add(mmGrandson);
		
		mmGrandson = new ArrayList<String>();
		mmGrandson.add("ë��");
		mmGrandson.add("Ƥ����");
		mGrandson.add(mmGrandson);
		
		grandson.add(mGrandson);
		
		
		
		superAdapter = new SuperTreeViewAdapter(this, stvClickEvent);
		expandableList = (ExpandableListView) findViewById(R.id.ExpandableListView01);
		expandableList.setGroupIndicator(null);// ��Ҫ�Դ����ˣ�����

		List<SuperTreeViewAdapter.SuperTreeNode> superTreeNode = superAdapter
				.GetTreeNode();
		for (int i = 0; i < parent.size(); i++)// ��һ��
		{
			SuperTreeViewAdapter.SuperTreeNode superNode = new SuperTreeViewAdapter.SuperTreeNode();
			superNode.parent = parent.get(i);

			// �ڶ���
			for (int ii = 0; ii < child.get(i).size(); ii++) {
				cn.mytaobao.adapter.TreeViewAdapter.TreeNode node = new cn.mytaobao.adapter.TreeViewAdapter.TreeNode();
				node.parent = child.get(i).get(ii);// �ڶ����˵��ı���

				for (int iii = 0; iii < grandson.get(i).get(ii).size(); iii++)// �������˵�
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
	 * �������β˵����¼����ٿ��ã����������������β˵�����������˵������лص�
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
