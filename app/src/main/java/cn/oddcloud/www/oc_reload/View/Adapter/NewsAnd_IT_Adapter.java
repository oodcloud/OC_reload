package cn.oddcloud.www.oc_reload.View.Adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.io.Serializable;
import java.util.List;

import cn.oddcloud.www.oc_reload.APP;
import cn.oddcloud.www.oc_reload.Model.News_List_item;
import cn.oddcloud.www.oc_reload.Model.News_List_item_page;
import cn.oddcloud.www.oc_reload.R;
import cn.oddcloud.www.oc_reload.Utils.LruImageCache;
import cn.oddcloud.www.oc_reload.View.Activity.NewsAnd_IT_Detail_page_Activity;


/**
 * Created by florentchampigny on 24/04/15.
 */
public class NewsAnd_IT_Adapter extends RecyclerView.Adapter<NewsAnd_IT_Adapter.ViewHolder> {
    /**
     * 当前的adapter不能删除，此关联推荐文章和IT资讯
     */
     private   List<News_List_item> contents;

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;
    ImageLoader imageLoader;
    public NewsAnd_IT_Adapter(List<News_List_item> contents) {
        this.contents = contents;
         imageLoader = new ImageLoader(APP.getRequestQueue(), new LruImageCache());
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

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = null;

        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_card_small, parent, false);

        return new ViewHolder(view);

    }




    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final News_List_item newsListItem =contents.get(position);


         holder.time.setText(newsListItem.getTIME());
        holder.title.setText(newsListItem.getTITLE());
        holder.site.setText(newsListItem.getSite());
        String url = newsListItem.getIMG();



        //加载图片
       if (url!=null) {

           ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(holder.imageView, R.drawable.ic_filter_drama_white_48dp, R.drawable.ic_filter_drama_white_48dp);
           imageLoader.get(url, imageListener,50,40);

       }

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                News_List_item newsListItems =contents.get(position);


                News_List_item_page news_list_item_page=new News_List_item_page();
                news_list_item_page.setTITLE(newsListItems.getTITLE());
                news_list_item_page.setSite(newsListItems.getSite());
                news_list_item_page.setIMG(newsListItems.getIMG());
                news_list_item_page.setTIME(newsListItems.getTIME());
                news_list_item_page.setPosition(position);

                Intent intent=new Intent(v.getContext(), NewsAnd_IT_Detail_page_Activity.class);
//                contents.clear();
                Bundle bundle=new Bundle();
                bundle.putSerializable("pagecontent", (Serializable) news_list_item_page);
                intent.putExtra("mylistitem", bundle);
                v.getContext().startActivity(intent);
            }
        });


    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private  TextView time;
        private TextView site;
        private ImageView imageView;
        private View view;
        public ViewHolder(View itemView) {
            super(itemView);
             view=itemView;
            title= (TextView) itemView.findViewById(R.id.datalisteitem_title);
            time= (TextView) itemView.findViewById(R.id.datalisteitem_time);
            site= (TextView) itemView.findViewById(R.id.datalisteitem_site);
            imageView= (ImageView) itemView.findViewById(R.id.datalist_item_img);

        }
    }
    }
