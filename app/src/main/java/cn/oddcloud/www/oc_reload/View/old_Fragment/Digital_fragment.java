package cn.oddcloud.www.oc_reload.View.old_Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baoyz.widget.PullRefreshLayout;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.oddcloud.www.oc_reload.Model.News_List_item;
import cn.oddcloud.www.oc_reload.R;
import cn.oddcloud.www.oc_reload.Utils.Digtital_RebackCallData;
import cn.oddcloud.www.oc_reload.View.Adapter.NewsAnd_IT_Adapter;


public class Digital_fragment extends Fragment {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    private static final int ITEM_COUNT = 100;

    private List<News_List_item> mContentItems;
    private PullRefreshLayout layout;
    Handler handler;
    public static Digital_fragment newInstance() {
        return new Digital_fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mContentItems=new ArrayList<>();
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if ( msg.what==1) {
                    mContentItems= (List<News_List_item>) msg.getData().getSerializable("data");
                    mAdapter = new RecyclerViewMaterialAdapter(new NewsAnd_IT_Adapter(mContentItems));
                    mRecyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }
            }
        };

        /**
         * 下拉刷新
         */
        layout = (PullRefreshLayout) view.findViewById(R.id.recommendswipeRefreshLayout);
        layout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                layout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        layout.setRefreshing(false);
                    }
                }, 1500);
                Digtital_RebackCallData.GetReCallcontent(handler);
            }
        });
        //加载图片缓存
        new Thread(){
            @Override
            public void run() {
                super.run();
                Digtital_RebackCallData.GetReCallcontent(handler);
            }
        }.start();


        MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView);
    }

}