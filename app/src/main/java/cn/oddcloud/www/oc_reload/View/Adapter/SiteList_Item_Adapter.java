package cn.oddcloud.www.oc_reload.View.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import cn.oddcloud.www.oc_reload.R;


/**
 * Created by florentchampigny on 24/04/15.
 */
public class SiteList_Item_Adapter extends RecyclerView.Adapter<SiteList_Item_Adapter.ViewHolder> {

    ArrayList<String> contents;
    ArrayList<String> sitelink;

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;

   Context mcontext;
    public SiteList_Item_Adapter(Context context,ArrayList<String> list, ArrayList<String> sitelink) {
        mcontext=context;
       contents = list;
        this.sitelink=sitelink;

    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_HEADER;
            default:
                return TYPE_CELL;
        }
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }
//现在是目标是取到position进而得到url
    @Override
    public SiteList_Item_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = null;
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.sitelist, parent, false);
        //跳转到详情页面
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(mcontext, Site_Website_Page_list.class);
//                mcontext.startActivity(intent);
//
//            }
//        });



                return new SiteList_Item_Adapter.ViewHolder(view) {

                };



    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.site.setText(contents.get(position));


        final String link=sitelink.get(position);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(mcontext, Site_Website_Page_list.class);
//                intent.putExtra("link",link);
//                intent.putExtra("title",holder.site.getText());
//                mcontext.startActivity(intent);
            }
        });
    }





    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView site;

        private View view;
        public ViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            site= (TextView) view.findViewById(R.id.sitelist_title);

        }
    }
}