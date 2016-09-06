package cn.oddcloud.www.oc_reload.View.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import cn.oddcloud.www.oc_reload.R;
import cn.oddcloud.www.oc_reload.View.fragment.SiteList_Item_Fragment;


/**
 * Created by Administrator on 2016/5/28.
 */
public class SiteListAdapter extends ArrayAdapter {
   private   Context context;
    FragmentManager fragmentManager;
    private LayoutInflater Inflater = null;
    public SiteListAdapter(Context context, int resource, Object[] objects, FragmentManager fragmentManager) {
        super(context, resource, objects);
        this.Inflater = LayoutInflater.from(context);
        this.context=context;
        this.fragmentManager=fragmentManager;

    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final String listItem = (String) getItem(position);
        ViewHolder holder;

        if (convertView == null) {
            holder=new ViewHolder();
            convertView = Inflater.inflate(R.layout.sitelist_tv, null);
            holder.siteitem= (TextView) convertView.findViewById(R.id.sitelist_tv);
            convertView.setTag(holder);
        }
        else {
            holder= (ViewHolder) convertView.getTag();
        }

        holder.siteitem.setText(listItem);
      //默认情况下的站点列表
        SiteList_Item_Fragment.setSite_position(0);
//        fragmentManager.beginTransaction().replace(R.id.siteitem_Container, SiteList_Item_Fragment.newInstance()).commit();
//        holder.siteitem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //根据点击的item判断分类列表对应的站点列表
//                SiteList_Item_Fragment.setSite_position(position);
//                fragmentManager.beginTransaction().replace(R.id.siteitem_Container, SiteList_Item_Fragment.newInstance()).commit();
//
//            }
//        });


        return convertView;
    }

    private class ViewHolder {
        TextView siteitem;
    }

}
