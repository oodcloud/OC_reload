package cn.oddcloud.www.oc_reload.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;

import cn.oddcloud.www.oc_reload.R;
import cn.oddcloud.www.oc_reload.View.fragment.RecommendArticlesFragment;
import cn.oddcloud.www.oc_reload.View.old_Fragment.IT_InformationFragment;


public class TopicArticleList extends FragmentActivity {

    private Object fragments;
    private int position;
   private Fragment fragment1;
    private Fragment fragment2;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_theme__list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_theme);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        position = intent.getIntExtra("position", 1);
        /**
         * 得到position后调用  setchanges(position);
         *
         * GetContent.setChiose(position);得目的是获取
         * 得到类别列表中的position和列表名称
         *
         * 当调用Theme_List_Fragment时会根据得到类别列表中的position和列表名称
         * 选择相应的关键词和频道进行请求数据
         */


        toolbar.setTitle(title);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
/**
 * 这个是根据关键词获取的内容的方法
 * 其中涉及到当前的activity和Theme_list_fragment
 * 和GetCONTEnT.java
 */

        /**
         * 随机获取
         */

        if (position % 2 == 0) {
            fragment1 = RecommendArticlesFragment.newInstance();
            fragment = fragment1;
        } else {
            fragment2 = IT_InformationFragment.newInstance();
            fragment = fragment2;
        }


        getSupportFragmentManager().beginTransaction().replace(R.id.theme_container, fragment).commit();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!this.getSupportFragmentManager().beginTransaction().isEmpty())
        {
            if (fragment1!=null)
            getSupportFragmentManager().beginTransaction().remove(fragment1);
            if (fragment2!=null)
                getSupportFragmentManager().beginTransaction().remove(fragment2);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
