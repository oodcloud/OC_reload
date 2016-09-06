package cn.oddcloud.www.oc_reload.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import cn.oddcloud.www.oc_reload.R;
import cn.oddcloud.www.oc_reload.View.Activity.List_Collection_content;


/**
 * Created by Administrator on 2016/6/12.
 */
public class MArrayAdapter extends ArrayAdapter {
    private Context context;
    private int list_item_card;
    private List<HashMap<String, String>> objects;
    private LayoutInflater layoutinflater;
   private  int flag;
    public MArrayAdapter(Context context, int list_item_card_small, List<HashMap<String, String>> objects,int flag) {
        super(context, list_item_card_small, objects);
        this.context = context;
        this.list_item_card = list_item_card_small;
        this.objects = objects;
        layoutinflater = LayoutInflater.from(context);
        this.flag=flag;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final HashMap<String, String> map = objects.get(position);
        View view = layoutinflater.inflate(list_item_card, null);
        TextView title = (TextView) view.findViewById(R.id.datalisteitem_title);
        TextView time = (TextView) view.findViewById(R.id.datalisteitem_time);
        TextView site = (TextView) view.findViewById(R.id.datalisteitem_site);
        title.setText(map.get("title"));
        time.setText(map.get("time"));
        site.setText(map.get("site"));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, List_Collection_content.class);
                Bundle bundle = new Bundle();
                bundle.putString("title", map.get("title").toString());
                bundle.putString("time", map.get("time").toString());

                bundle.putString("site", map.get("site").toString());

                bundle.putInt("flag", flag);//

                bundle.putInt("postion", position);
                intent.putExtra("collection", bundle);
                context.startActivity(intent);
            }
        });
        return view;
    }
}
