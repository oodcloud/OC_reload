package cn.oddcloud.www.oc_reload.View.Activity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
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

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.regex.Pattern;

import cn.bmob.v3.exception.BmobException;
import cn.oddcloud.www.oc_reload.APP;
import cn.oddcloud.www.oc_reload.Crt.DatabaseHelper;
import cn.oddcloud.www.oc_reload.Model.Collect_Page;
import cn.oddcloud.www.oc_reload.Model.Liulan_Page;
import cn.oddcloud.www.oc_reload.R;
import cn.oddcloud.www.oc_reload.Utils.Database_Utils;
import cn.oddcloud.www.oc_reload.Utils.MsharedPreferences;
import cn.oddcloud.www.oc_reload.Utils.StringUtil;
import cn.oddcloud.www.oc_reload.Utils.XmlRequest;
import rx.Subscriber;


public class Sitelist_Website_context extends Activity {
    private TextView content;
    private TextView time_tv;
    private TextView category_tv;

    private int position;
    private int Flag = -1;
    boolean FLAG = false;
    private String Link;
    private ProgressBar progressBar;
    private Database_Utils database_utils;
    /**
     * test 保存数据库
     *
     * @param savedInstanceState
     */

    private Cursor cursor;
    public static final String TAG = "DaoExample";
    private String title;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitelist__website_context);
        Toolbar toolbar = (Toolbar) findViewById(R.id.website_maintoolbar);
        toolbar.inflateMenu(R.menu.top_menu);
        getListItemdetail();
        initContent();



        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        progressBar = (ProgressBar) findViewById(R.id.website_progrssbar);

        database_utils = new Database_Utils();
        database_utils.opendb(Sitelist_Website_context.this);






        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.favorite_top_menu:
                        database_utils = new Database_Utils();
                        database_utils.opendb(Sitelist_Website_context.this);
                        Bundle bundle = getIntent().getBundleExtra("websitelistitem");
                        bundle.getString("title");
                        bundle.getString("time");
                        bundle.getString("category");
                        bundle.getString("url");
                        ContentValues contentValues=new ContentValues();
                        contentValues.put("title",  bundle.getString("title"));
                        contentValues.put("time",  bundle.getString("time"));
                        contentValues.put("site",  bundle.getString("site"));
                        contentValues.put("content", (String) content.getText());
                        database_utils.insert("Data_collect",contentValues);
                        database_utils.closedb(Sitelist_Website_context.this);


                        Collect_Page collectPage=new Collect_Page();
                        collectPage.setContent((String) content.getText());
                        collectPage.setTitle(bundle.getString("title"));
                        collectPage.setTime(bundle.getString("time"));
                        collectPage.setSite(bundle.getString("site"));
                        collectPage.setPhone(MsharedPreferences.Getphone(Sitelist_Website_context.this));
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

                        Toast.makeText(Sitelist_Website_context.this, "收藏成功", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });


    }


    /**
     * 下拉刷新
     */


    private void initContent() {
        /**
         * 做内容解析
         * 从websimpleAdapter中获取的position就用上了
         * 幸福每一天  V_Man
         */

        APP.getRequestQueue();
        XmlRequest request = new XmlRequest(StringUtil.preUrl(Link),
                new Response.Listener<XmlPullParser>() {

                    @Override
                    public void onResponse(XmlPullParser parser) {
                        try {
                            Log.d("WIN", "nodeName");


                            int eventType = parser.getEventType();
                            boolean FLAG = false;
                            while (eventType != XmlPullParser.END_DOCUMENT) {
                                String nodeName = parser.getName();

                                switch (eventType) {

                                    case XmlPullParser.START_TAG:

                                        if ("item".equals(nodeName)) {
                                            Flag++;
                                            if (Flag == position) {
                                                FLAG = true;
                                            }
                                        }
                                        Log.d("GOOD", FLAG + "");
                                        if (FLAG == true & "description".equals(nodeName)) {
                                            String description = parser.nextText();
                                            /**
                                             * c除去html中的标签字符
                                             */


                                            Pattern p_html;
                                            java.util.regex.Matcher m_html;
                                            Pattern p_special;
                                            java.util.regex.Matcher m_special;

                                            String alltext = null;
                                            // 定义HTML标签的正则表达式
                                            try {
                                                String regEx_special = "\\&[a-zA-Z]{1,10};";

                                                String regEx_html = "<[^>]+>";

                                                p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);

                                                m_html = p_html.matcher(description);
                                                description = m_html.replaceAll(""); // 过滤html标签
                                                p_special = Pattern.compile(regEx_special, Pattern.CASE_INSENSITIVE);
                                                m_special = p_special.matcher(description);
                                                description = m_special.replaceAll("");
                                                alltext = description;
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                            progressBar.setVisibility(View.INVISIBLE);
                                            content.setText(alltext);
                                            if(!alltext.isEmpty()) {
                                                Bundle bundle = getIntent().getBundleExtra("websitelistitem");
                                                bundle.getString("title");
                                                bundle.getString("time");
                                                bundle.getString("category");
                                                bundle.getString("url");
                                                ContentValues contentValues = new ContentValues();
                                                contentValues.put("title", bundle.getString("title"));
                                                contentValues.put("time", bundle.getString("time"));
                                                contentValues.put("site", bundle.getString("site"));
                                                contentValues.put("content", alltext);
                                                database_utils.insert("Data_HISTORY", contentValues);
                                                database_utils.closedb(Sitelist_Website_context.this);
                                                //上传服务器
                                                Liulan_Page liulan_page=new Liulan_Page();
                                                liulan_page.setContent(alltext);
                                                liulan_page.setTitle(bundle.getString("title"));
                                                liulan_page.setTime(bundle.getString("time"));
                                                liulan_page.setSite(bundle.getString("site"));
                                                liulan_page.setPhone(MsharedPreferences.Getphone(Sitelist_Website_context.this));
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




                                            FLAG = false;


                                        }
                                        break;

                                }
                                eventType = parser.next();
                            }

                        } catch (XmlPullParserException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                Log.e("" + getClass(), "请求失败" + arg0);
            }
        });

        // 请求加上Tag,用于取消请求
        request.setTag(this);
        APP.getRequestQueue().add(request);


    }

    public void getListItemdetail() {
        /**
         * CoordinatorLayout
         * AppBarLayout
         * CollapsingToolbarLayout
         * 这三个实现了联动效果
         */
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.website_coordinator);
        AppBarLayout appBarLayout = (AppBarLayout) coordinatorLayout.findViewById(R.id.website_bar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) appBarLayout.findViewById(R.id.website_maincollapsing);
        CardView cardView = (CardView) findViewById(R.id.website_card_view);
        time_tv = (TextView) cardView.findViewById(R.id.website_updata_time);
        category_tv = (TextView) cardView.findViewById(R.id.website_detail_site);
        content = (TextView) findViewById(R.id.website_content);
        /**
         * 获取传递的数据
         */
        Bundle bundle = getIntent().getBundleExtra("websitelistitem");
        title = bundle.getString("title");
        collapsingToolbarLayout.setTitle(title);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBarPlus1);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBarPlus1);
        time_tv.setText(bundle.getString("time"));
        Link = bundle.getString("url");
        if (bundle.get("category") != null)
            category_tv.setText(bundle.getString("category"));
        position = bundle.getInt("postion");
        Log.d("GOOD", position + "");


    }


}
