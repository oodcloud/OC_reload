package cn.oddcloud.www.oc_reload.View.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;

import java.util.ArrayList;

import cn.oddcloud.www.oc_reload.Model.SQLLoad;
import cn.oddcloud.www.oc_reload.R;
import cn.oddcloud.www.oc_reload.View.Adapter.SiteList_Item_Adapter;


/**
 * Created by florentchampigny on 24/04/15.
 */
public class SiteList_Item_Fragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    private static final int ITEM_COUNT = 100;


    private static   int site_positions;
    String site = null;
    String sitelinks=null;
    //站点名称
    ArrayList<String> list=new ArrayList();
    // //站点链接
    ArrayList<String> sitelink=new ArrayList();
    static  int i=0;


    public static SiteList_Item_Fragment newInstance() {
        return new SiteList_Item_Fragment();
    }





    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_sitelist, container, false);
        i++;
        if (i==1) {


            SQLLoad sqlLoad = new SQLLoad();
//            SQLiteDatabase db = sqlLoad.openDatabase(getContext());
            Log.d("Site", site_positions + "");


//            Cursor cursor = db.rawQuery("select * from feed where cid=?", new String[]{"" + site_positions});
//取出数据库中的URL到数据中然后写一个点击事件，没点击一次intent跳转到rss页面中
//            for (int i = 0; i < cursor.getCount(); i++) {
//                cursor.moveToNext();
//                site = cursor.getString(cursor.getColumnIndex("fname"));
//                sitelinks = cursor.getString(cursor.getColumnIndex("url"));
//                list.add(site);
//                sitelink.add(sitelinks);
//            }
//            //这是一个TextView，把得到的数据库中的name显示出来.
//            Log.d("SQL", list.toString());
//            Log.d("SQL", sitelink.toString());
//            cursor.close();
//            db.close();
        }

        mRecyclerView = (RecyclerView) view.findViewById(R.id.sitelist_1);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(false);
        mAdapter = new RecyclerViewMaterialAdapter(new SiteList_Item_Adapter(getContext(),list,sitelink));
        mRecyclerView.setAdapter(mAdapter);


        MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        i=0;
    }

    public static void setSite_position(int site_position) {
       site_positions = site_position+1;
    }
}
