package cn.mytaobao.adapter;

import java.util.ArrayList;
import java.util.List;

import cn.mytaobao.model.Comment;
import cn.mytaobao.ui.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CommentAdapter extends BaseAdapter {
	private List<Comment> comments;
	private Context mContext;
	private LayoutInflater mInflater;

	public CommentAdapter(Context context,List<Comment> comments) {
		this.mContext = context;
		this.mInflater = LayoutInflater.from(mContext);
		this.comments =comments;
		

	}

	public int getCount() {
		return comments.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	public void refresh(List<Comment> comments){
		this.comments = comments;
		this.notifyDataSetChanged();
		
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = mInflater.inflate(R.layout.comment_list_view, null);
		TextView note = (TextView) convertView.findViewById(R.id.txtComment);
		TextView name = (TextView) convertView.findViewById(R.id.txtName);
		TextView time = (TextView) convertView.findViewById(R.id.txtTime);
		note.setText(comments.get(position).getNote());
		name.setText(comments.get(position).getName());
		time.setText(comments.get(position).getTime());
		return convertView;
	}

}
