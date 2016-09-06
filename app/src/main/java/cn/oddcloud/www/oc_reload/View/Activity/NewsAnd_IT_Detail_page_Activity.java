package cn.oddcloud.www.oc_reload.View.Activity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.v3.exception.BmobException;
import cn.oddcloud.www.oc_reload.Crt.DatabaseHelper;
import cn.oddcloud.www.oc_reload.Model.Collect_Page;
import cn.oddcloud.www.oc_reload.Model.Liulan_Page;
import cn.oddcloud.www.oc_reload.Model.News_List_item_page;
import cn.oddcloud.www.oc_reload.R;
import cn.oddcloud.www.oc_reload.Utils.Database_Utils;
import cn.oddcloud.www.oc_reload.Utils.MsharedPreferences;
import rx.Subscriber;


public class NewsAnd_IT_Detail_page_Activity extends Activity {


    private TextView content;
    private TextView time_tv;
    private TextView site_tv;
    private int position;
    private ProgressBar progressBar;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;
    private Database_Utils database_utils;
    /**
     * test 保存数据库
     * @param savedInstanceState
     */
    public static final String TAG = "DaoExample";
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item__detail);
        database_utils = new Database_Utils();
        database_utils.opendb(NewsAnd_IT_Detail_page_Activity.this);

        progressBar = (ProgressBar) findViewById(R.id.progrssbar);

        getListItemdetail();
        initContent();




        Toolbar toolbar = (Toolbar) findViewById(R.id.maintoolbar);

        toolbar.inflateMenu(R.menu.top_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.favorite_top_menu:
                        database_utils = new Database_Utils();
                        database_utils.opendb(NewsAnd_IT_Detail_page_Activity.this);
                        Bundle bundle=getIntent().getBundleExtra("mylistitem");
                        News_List_item_page newsListItems = (News_List_item_page) bundle.getSerializable("pagecontent");
                        ContentValues contentValues=new ContentValues();
                        contentValues.put("title", newsListItems.getTITLE());
                        contentValues.put("time",  newsListItems.getTIME());
                        contentValues.put("site",  newsListItems.getSite());
                        contentValues.put("content", (String) content.getText());
                        database_utils.insert("Data_collect",contentValues);
                        database_utils.closedb(NewsAnd_IT_Detail_page_Activity.this);

                        Collect_Page collectPage=new Collect_Page();
                        collectPage.setContent((String) content.getText());
                        collectPage.setTitle( newsListItems.getTITLE());
                        collectPage.setTime(newsListItems.getTIME());
                        collectPage.setSite( newsListItems.getSite());
                        collectPage.setPhone(MsharedPreferences.Getphone(NewsAnd_IT_Detail_page_Activity.this));
                        collectPage.saveObservable().subscribe(new Subscriber<String>() {
                            @Override
                            public void onCompleted() {
                                Log.d("KEEP_content","----onCompleted----");
                            }

                            @Override
                            public void onError(Throwable throwable) {
                                Log.d("KEEP_content","------"+new BmobException(throwable));
                            }

                            @Override
                            public void onNext(String s) {
                                Log.d("KEEP_content","---onNext---"+s);
                            }
                        });


                        Toast.makeText(NewsAnd_IT_Detail_page_Activity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });





    }


    public void getListItemdetail() {
        CoordinatorLayout coordinatorLayout= (CoordinatorLayout) findViewById(R.id.coordinator);
        AppBarLayout appBarLayout= (AppBarLayout)coordinatorLayout. findViewById(R.id.mainappbar);
        CollapsingToolbarLayout collapsingToolbarLayout= (CollapsingToolbarLayout) appBarLayout.findViewById(R.id.maincollapsing);
        CardView cardView= (CardView) findViewById(R.id.card_view);
        time_tv= (TextView) cardView.findViewById(R.id.detail_time);
        site_tv= (TextView) cardView.findViewById(R.id.detail_site);
        content= (TextView) findViewById(R.id.content);
        /**
         * 获取传递的数据
         */
        Bundle bundle=getIntent().getBundleExtra("mylistitem");
        News_List_item_page newsListItems = (News_List_item_page) bundle.getSerializable("pagecontent");
        collapsingToolbarLayout.setTitle(newsListItems.getTITLE());
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBarPlus1);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBarPlus1);
        time_tv.setText(newsListItems.getTIME());
        site_tv.setText(newsListItems.getSite());
        position= newsListItems.getPosition();
    }

    private void initContent() {

        Parameters para = new Parameters();
        para.put("channelId", "5572a109b3cdc86cf39001e3");
        para.put("channelName", "最新");
        para.put("count", "10");
        para.put("page", "1");
        ApiStoreSDK.execute("http://apis.baidu.com/showapi_open_bus/channel_news/search_news",
                ApiStoreSDK.GET,
                para,
                new ApiCallBack() {
                    @Override
                    public void onSuccess(int status, String responseString) {
                        try {

                            JSONObject jsonObject = new JSONObject(responseString);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("showapi_res_body");
                            JSONObject jsonObject2 = jsonObject1.getJSONObject("pagebean");
                            JSONArray jsonArray = jsonObject2.getJSONArray("contentlist");


                                JSONObject resultJsonObject = jsonArray.getJSONObject(position);
                                JSONArray title = resultJsonObject.getJSONArray("allList");
                                Log.d("TITLE", title.toString());
                                int length=title.length();

                                StringBuffer context=new StringBuffer();
                                for (int i=0;i<length;i++)
                                {
                                    String jsonObject3=title.getString(i);

                                   if (jsonObject3.contains("height"))
                                   {
                                       continue;
                                   }
                                        String contentitem=  jsonObject3+"\n";
                                     contentitem.replaceAll(","," %s%s%s");
                                    context.append(contentitem);
                                }
                            progressBar.setVisibility(View.INVISIBLE);
                            content.setText(context.toString());
                            if (!content.getText().toString().isEmpty()) {
                                Bundle bundle = getIntent().getBundleExtra("mylistitem");
                                News_List_item_page newsListItems = (News_List_item_page) bundle.getSerializable("pagecontent");
                                ContentValues contentValues = new ContentValues();
                                contentValues.put("title", newsListItems.getTITLE());
                                contentValues.put("time", newsListItems.getTIME());
                                contentValues.put("site", newsListItems.getSite());
                                Log.d("Detail_page","i"+context.toString());
                                contentValues.put("content", context.toString());
                                database_utils.insert("Data_HISTORY", contentValues);
                                database_utils.closedb(NewsAnd_IT_Detail_page_Activity.this);


                                Liulan_Page liulan_page=new Liulan_Page();
                                liulan_page.setContent(context.toString());
                                liulan_page.setTitle(newsListItems.getTITLE());
                                liulan_page.setTime(newsListItems.getTIME());
                                liulan_page.setSite(newsListItems.getSite());
                                liulan_page.setPhone(MsharedPreferences.Getphone(NewsAnd_IT_Detail_page_Activity.this));
                                liulan_page.saveObservable().subscribe(new Subscriber<String>() {
                                    @Override
                                    public void onCompleted() {
                                        Log.d("KEEP_content","----onCompleted----");
                                    }

                                    @Override
                                    public void onError(Throwable throwable) {
                                        Log.d("KEEP_content","------"+new BmobException(throwable));
                                    }

                                    @Override
                                    public void onNext(String s) {
                                        Log.d("KEEP_content","---onNext---"+s);
                                    }
                                });

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onComplete() {
                        Log.i("sdkdemo", "onComplete");
                    }

                    @Override
                    public void onError(int status, String responseString, Exception e) {
                        Log.i("sdkdemo", "onError, status: " + status);
                        Log.i("sdkdemo", "errMsg: " + (e == null ? "" : e.getMessage()));

                    }

                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
