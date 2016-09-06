package cn.oddcloud.www.oc_reload.View.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.baoyz.widget.PullRefreshLayout;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.oddcloud.www.oc_reload.APP;
import cn.oddcloud.www.oc_reload.R;
import cn.oddcloud.www.oc_reload.Utils.StringUtil;
import cn.oddcloud.www.oc_reload.Utils.VolleyUtil;
import cn.oddcloud.www.oc_reload.Utils.XmlRequest;
import cn.oddcloud.www.oc_reload.View.Adapter.WebSimpleAdapter;


public class Site_Website_Page_list extends Activity {
    /**
     * 获取数据后
     */
//    private RecyclerView mRecyclerView;
    String link;
   String title;
   private ListView listView;
    private ProgressBar progressBar;
private PullRefreshLayout layout;

  private WebSimpleAdapter simpleAdapter;
    private List<HashMap<String,String>> mContentItems;
    HashMap<String,String> map;
    private static final int[] ids = { R.id.tv_website_title, R.id.tv_website_category, R.id.tv_website_pubDate,
            R.id.tv_website_author };
    private static final String[] keys = { "title", "category", "pubDate", "author" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.site_website_page_list);
       link= getIntent().getStringExtra("link");
        title=getIntent().getStringExtra("title");
        listView= (ListView) findViewById(R.id.website_listview);
        mContentItems=new ArrayList<>();
        simpleAdapter=new WebSimpleAdapter(this,mContentItems,R.layout.fr_xml_request_list_item,keys, ids);
        Toolbar toolbar= (Toolbar) findViewById(R.id.site_website_toolbar);
        toolbar.setNavigationIcon(R.drawable.nav);
        toolbar.setTitle(title);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        progressBar= (ProgressBar) findViewById(R.id.page_list_progressbar);


        /**
         * 下拉刷新
         */

        layout = (PullRefreshLayout) this.findViewById(R.id.website_swipeRefreshLayout);
        layout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                layout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        layout.setRefreshing(false);
                    }
                }, 1500);
                GetRecontent();
            }
        });




        /**
         * test rss列表显示
         */
        APP.getRequestQueue();
        XmlRequest request = new XmlRequest(StringUtil.preUrl(link),
                new Response.Listener<XmlPullParser>() {

                    @Override
                    public void onResponse(XmlPullParser parser) {
                        try {
                            Log.d("WIN","nodeName");
                            mContentItems.clear();
                            boolean FLAG=false;
                            int eventType = parser.getEventType();
                            while (eventType != XmlPullParser.END_DOCUMENT) {
                                String nodeName = parser.getName();

                                switch (eventType) {

                                    case XmlPullParser.START_TAG:

                                        if ("item".equals(nodeName)) {
                                            map=new   HashMap<>();
                                            FLAG=true;
                                        }
                                        if(FLAG&"title".equals(nodeName)){
                                            progressBar.setVisibility(View.INVISIBLE);
                                            String titles=parser.nextText();
                                            map.put("title",titles);

                                        }
                                        else if("category".equals(nodeName)){
                                            String title=parser.nextText();
                                            map.put("category", title);

                                        }
                                        else if(FLAG&"pubDate".equals(nodeName)){
                                            String title=parser.nextText();
                                            map.put("pubDate", title);
                                            Log.d("WIN",title);
                                        }else if("author".equals(nodeName)){

                                            map.put("author", parser.nextText());
                                        }
                                        break;

                                    case XmlPullParser.END_TAG:
                                        if("item".equals(nodeName)){
                                            map.put("url",link);
                                            mContentItems.add(map);
                                            Log.d("WIN", "nodeName"+mContentItems.size());
                                            map=null;
                                        }
                                        break;
                                }
                                eventType = parser.next();
                            }
                            simpleAdapter.notifyDataSetChanged();
                        } catch (XmlPullParserException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                Log.e(""+getClass(),"请求失败"+arg0);
            }
        });

        // 请求加上Tag,用于取消请求
        request.setTag(this);
        APP.getRequestQueue().add(request);





        listView.setAdapter(simpleAdapter);

    }

    private void GetRecontent() {
        APP.getRequestQueue();
        XmlRequest request = new XmlRequest(StringUtil.preUrl(link),
                new Response.Listener<XmlPullParser>() {

                    @Override
                    public void onResponse(XmlPullParser parser) {
                        try {
                            Log.d("WIN","nodeName");
                            mContentItems.clear();
                            boolean FLAG=false;
                            int eventType = parser.getEventType();
                            while (eventType != XmlPullParser.END_DOCUMENT) {
                                String nodeName = parser.getName();

                                switch (eventType) {

                                    case XmlPullParser.START_TAG:

                                        if ("item".equals(nodeName)) {
                                            map=new   HashMap<>();
                                            FLAG=true;
                                        }
                                        if(FLAG&"title".equals(nodeName)){
                                            String titles=parser.nextText();
                                            map.put("title",titles);

                                        }
                                        else if("category".equals(nodeName)){
                                            String title=parser.nextText();
                                            map.put("category", title);

                                        }
                                        else if(FLAG&"pubDate".equals(nodeName)){
                                            String title=parser.nextText();
                                            map.put("pubDate", title);
                                            Log.d("WIN",title);
                                        }else if("author".equals(nodeName)){

                                            map.put("author", parser.nextText());
                                        }
                                        break;

                                    case XmlPullParser.END_TAG:
                                        if("item".equals(nodeName)){
                                            map.put("url",link);
                                            mContentItems.add(map);
                                            Log.d("WIN", "nodeName"+mContentItems.size());
                                            map=null;
                                        }
                                        break;
                                }
                                eventType = parser.next();
                            }
                            simpleAdapter.notifyDataSetChanged();
                        } catch (XmlPullParserException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                Log.e(""+getClass(),"请求失败"+arg0);
            }
        });

        // 请求加上Tag,用于取消请求
        request.setTag(this);
        APP.getRequestQueue().add(request);
    }


    @Override
    protected void onDestroy() {
        VolleyUtil.getQueue(this).cancelAll(this);
        super.onDestroy();

    }
}
