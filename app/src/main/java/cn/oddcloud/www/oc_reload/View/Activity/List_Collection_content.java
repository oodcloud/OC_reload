package cn.oddcloud.www.oc_reload.View.Activity;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.oddcloud.www.oc_reload.Model.Collect_Page;
import cn.oddcloud.www.oc_reload.Model.Liulan_Page;
import cn.oddcloud.www.oc_reload.R;
import cn.oddcloud.www.oc_reload.Utils.Database_Utils;
import cn.oddcloud.www.oc_reload.Utils.MsharedPreferences;


public class List_Collection_content extends Activity {
    private TextView content;
    private TextView time_tv;
    private TextView category_tv;
    private String title;
    private int position;
    private String contents;
    /**
     * 定义数据库
     *
     * @param savedInstanceState
     */
    private SQLiteDatabase db;
    public static final String TAG = "DaoExample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__collection_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.collection_maintoolbar);
        getListItemdetail();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Data();


    }


    private void Data() {

        Database_Utils database_utils=new Database_Utils();
        database_utils.opendb(this);
        JSONArray jsonArray= database_utils.DeptArray( getIntent().getBundleExtra("collection").getInt("flag"));
          if (jsonArray.length()!=0) {
              try {
                  JSONObject jsonObject = (JSONObject) jsonArray.get(position);
                  contents = jsonObject.getString("content");
                  content.setText(contents);
              } catch (JSONException e) {
                  e.printStackTrace();
              }
              database_utils.closedb(this);
          }
        else {
             int flags=getIntent().getBundleExtra("collection").getInt("flag");
              if (flags==1)//浏览文章
              {
                  BmobQuery<Liulan_Page> query = new BmobQuery<Liulan_Page>();
                  query.addWhereEqualTo("phone", MsharedPreferences.Getphone(this));
                  query.order("-updatedAt");
                  query.findObjects(new FindListener<Liulan_Page>() {
                      @Override
                      public void done(List<Liulan_Page> object, BmobException e) {
                          if(e==null){
                              Log.d("Check"," is do1p"+object.size());
                                  contents=object.get(position).getContent();
                              content.setText(contents);
                          }
                      }

                  });
              }
              else {
                  BmobQuery<Collect_Page> query = new BmobQuery<Collect_Page>();
                  query.addWhereEqualTo("phone", MsharedPreferences.Getphone(this));
                  query.order("-updatedAt");
                  query.findObjects(new FindListener<Collect_Page>() {
                      @Override
                      public void done(List<Collect_Page> object, BmobException e) {
                          if(e==null){
                              contents=object.get(position).getContent();
                              content.setText(contents);

                          }
                      }

                  });
              }


          }
    }

    private void getListItemdetail() {
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.collection_coordinator);
        AppBarLayout appBarLayout = (AppBarLayout) coordinatorLayout.findViewById(R.id.collection_bar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) appBarLayout.findViewById(R.id.collection_maincollapsing);
        CardView cardView = (CardView) findViewById(R.id.collection_card_view);
        time_tv = (TextView) cardView.findViewById(R.id.collection_updata_time);
        category_tv = (TextView) cardView.findViewById(R.id.collection_detail_site);
        content = (TextView) findViewById(R.id.collection_content);
        /**
         * 获取传递的数据
         */
        Bundle bundle = getIntent().getBundleExtra("collection");
        title = bundle.getString("title");
        collapsingToolbarLayout.setTitle(title);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBarPlus1);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBarPlus1);
        time_tv.setText(bundle.getString("time"));
        category_tv.setText(bundle.getString("site"));
        position = bundle.getInt("postion");
        Log.d("GOOD", position + "");
    }
}
