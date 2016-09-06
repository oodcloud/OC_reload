package cn.oddcloud.www.oc_reload.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import cn.oddcloud.www.oc_reload.R;
import cn.oddcloud.www.oc_reload.Utils.BaseViewHolder;
import cn.oddcloud.www.oc_reload.View.Activity.TopicArticleList;


public class TopicArticleGridAdapter extends BaseAdapter {
	private Context mContext;

	public String[] img_text = { "移动互联", "科技业界", "产品设计", "系统架构", "编程语言", "移动开发",
			"云计算", "开源", "运维", };
	public int[] imgs = { R.drawable.theme_item1, R.drawable.theme_item2,
			R.drawable.theme_item3, R.drawable.theme_item4,
			R.drawable.theme_item5, R.drawable.theme_item6,
			R.drawable.theme_item7, R.drawable.theme_item8, R.drawable.theme_item9 };

	public TopicArticleGridAdapter(Context mContext) {
		super();
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return img_text.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.grid_item, null, false);
		}
		final TextView tv = BaseViewHolder.get(convertView, R.id.tv_item);
		ImageView iv = BaseViewHolder.get(convertView, R.id.iv_item);
		iv.setBackgroundResource(imgs[position]);

		tv.setText(img_text[position]);
		convertView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/**
				 * 当它被点击时会跳转到文章列表Theme_List
				 * 传递当前的位置position
				 */
				Intent intent=new Intent(mContext, TopicArticleList.class);
				intent.putExtra("title",tv.getText().toString());
				intent.putExtra("position",position);
				mContext.startActivity(intent);
			}
		});
		return convertView;
	}

}
