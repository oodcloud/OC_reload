package cn.oddcloud.www.oc_reload.View.Adapter;

import android.util.Log;

import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.oddcloud.www.oc_reload.Model.News_List_item;


/**
 * Created by Administrator on 2016/6/4.
 */

/**
 * @V-man
 * 这个暂时不用
 * Test 类
 * 项目前期的废料，当初是想让他们根据当前点击的内容的名称输入到百度sdk中然后根据关键词检索，但是这样会产生一种问题就是
 * 无法返回这个getContents.getContent()，或许是我想的问题吧，逻辑有点乱，就这个fragment暂时不要
 */
public class GetContent {


    private static final String IT = "5572a108b3cdc86cf39001d1";
    private static String Channel=IT ;
    private static String FLAG ;
    private  List<News_List_item> mContentItems;

    public List<News_List_item> getContent() {

        Parameters para = new Parameters();
        para.put("channelId", getChannel());
        para.put("channelName", getChiose());
        Log.d("CHO", getChiose());
        para.put("count", "10");
        para.put("page", "1");

        mContentItems = new ArrayList<>();
        ApiStoreSDK.execute("http://apis.baidu.com/showapi_open_bus/channel_news/search_news",
                ApiStoreSDK.GET,
                para,
                new ApiCallBack() {
                    @Override
                    public void onSuccess(int status, String responseString) {
                        Log.i("sdkdemo", "onSuccessfully");
                        try {
                            Log.d("GSON", responseString);
                            JSONObject jsonObject = new JSONObject(responseString);

                            JSONObject jsonObject1 = jsonObject.getJSONObject("showapi_res_body");
                            JSONObject jsonObject2 = jsonObject1.getJSONObject("pagebean");
                            JSONArray jsonArray = jsonObject2.getJSONArray("contentlist");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject resultJsonObject = jsonArray.getJSONObject(i);
                                String title = resultJsonObject.getString("title");
                                String time = resultJsonObject.getString("pubDate");

                                String site = resultJsonObject.getString("source");
                                News_List_item newsListItem = new News_List_item();


                                newsListItem.setSite(site);
                                newsListItem.setTIME(time);
                                newsListItem.setTITLE(title);

                                //获取图片url'
                                if (!resultJsonObject.isNull("imageurls")) {
                                    JSONArray imgurl = resultJsonObject.getJSONArray("imageurls");

                                    if (!imgurl.isNull(0)) {
                                        JSONObject jsonObject3 = imgurl.getJSONObject(0);
                                        String url = jsonObject3.getString("url");
                                        Log.d("URL", url);
                                        newsListItem.setIMG(url);
                                    }
                                }


                                mContentItems.add(newsListItem);
                                Log.d("NULLS", mContentItems.size() + "....40..0..文章调试");


                            }
                            Log.d("NULLS", mContentItems.size() + "....30000..文章调试");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d("NULLS", mContentItems.size() + "..2test..文章调试");

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

                }

        );
        Log.d("NULLS", mContentItems.size() + "..1....文章调试");
        return mContentItems;
    }

    public String getChiose() {

        return FLAG;
    }


    public String getChannel() {

        return Channel;
    }

    public static void setChiose(int i) {
        switch (i) {

            case 0:
                Channel=IT;
                FLAG = "移动互联";
                Log.d("FLAG",FLAG+Channel);
                break;
            case 1:
                Channel=IT;
                FLAG = "科技";
                Log.d("FLAG",FLAG+Channel);
                break;
            case 2:
                Channel=IT;
                FLAG = "设计";
                Log.d("FLAG",FLAG+Channel);
                break;
            case 3:
                Channel=IT;
                FLAG = "系统架构";
                break;
            case 4:

                FLAG = "编程";
                break;
            case 5:

                FLAG = "移动开发";
                break;
            case 6:

                FLAG = "云计算";
                break;
            case 7:

                FLAG = "开源";
                break;
            case 8:

                FLAG = "运维";
                break;

        }

    }

}
