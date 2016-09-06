package cn.oddcloud.www.oc_reload.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import cn.oddcloud.www.oc_reload.R;
import cn.oddcloud.www.oc_reload.View.Activity.Sitelist_Website_context;


/**
 * Created by Administrator on 2016/6/8.
 */
public class WebSimpleAdapter extends SimpleAdapter {
    Context context;
    List<? extends Map<String, ?>> data;
    Map<String, ?> map;
    LayoutInflater layoutInflater;
    int resource;
    String[] from = null;
    int[] to = null;
    private TreeSet mSeparatorsSet = new TreeSet();

    public WebSimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        layoutInflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
        this.resource = resource;
        this.from = from;
        this.to = to;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {



        return position;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final ViewHolder holder;
        final List<ViewHolder> list=new ArrayList<>();
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(resource, null);
            holder.tv_website_title = (TextView) convertView.findViewById(R.id.tv_website_title);
            holder.tv_website_category = (TextView) convertView.findViewById(R.id.tv_website_category);
            holder.tv_website_pubDate = (TextView) convertView.findViewById(R.id.tv_website_pubDate);
            holder.tv_website_author = (TextView) convertView.findViewById(R.id.tv_website_author);

            list.add(holder);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_website_title.setTag(position);
        map = data.get(position);
        holder.tv_website_title.setText(map.get("title").toString());
        if (map.get("category") != null) {
            holder.tv_website_category.setText(map.get("category").toString());
        }
        holder.tv_website_pubDate.setText(map.get("pubDate").toString());
        if (map.get("author") != null)
            holder.tv_website_author.setText(map.get("author").toString());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 传递position和url进而做二次解析获取内容
                 */
                map = data.get((Integer) holder.tv_website_title.getTag());
                Intent intent = new Intent(context, Sitelist_Website_context.class);
                Bundle bundle = new Bundle();
                bundle.putString("title", map.get("title").toString());
                bundle.putString("time", map.get("pubDate").toString());
                if (map.get("category") != null) {
                    bundle.putString("site", map.get("category").toString());
                }
                bundle.putString("url", map.get("url").toString());
                bundle.putInt("postion", (Integer) holder.tv_website_title.getTag());
                intent.putExtra("websitelistitem", bundle);
                context.startActivity(intent);
            }
        });
        return convertView;

    }


    private static class ViewHolder {
        View view;
        TextView tv_website_title;
        TextView tv_website_category;
        TextView tv_website_pubDate;
        TextView tv_website_author;

    }
}
