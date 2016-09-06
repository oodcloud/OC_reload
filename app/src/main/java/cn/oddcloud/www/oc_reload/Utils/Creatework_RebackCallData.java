package cn.oddcloud.www.oc_reload.Utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.oddcloud.www.oc_reload.Model.News_List_item;

/**
 * Created by wangyujie on 2016/8/30.
 */
public class Creatework_RebackCallData {
    public  static List<News_List_item> arrayList=new ArrayList();
    public static void  GetReCallcontent(final Handler handler){
        final Parameters para = new Parameters();
        para.put("channelId", "5572a109b3cdc86cf39001de");
//        para.put("channelName", "最新");
        para.put("count", "10");
        para.put("page", "1");
        Log.d("URL", "IS PASS START");


        new Thread(new Runnable() {
            @Override
            public void run() {
                ApiStoreSDK.execute("http://apis.baidu.com/showapi_open_bus/channel_news/search_news",
                        ApiStoreSDK.GET,
                        para,
                        new ApiCallBack() {
                            @Override
                            public void onSuccess(int status, String responseString) {

                                try {
                                    Log.d("URL", "IS PASS PASS"+responseString);
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


                                        arrayList.add(newsListItem);

                                        Message message=new Message();
                                        message.what=1;
                                        Bundle bundle=new Bundle();

                                        bundle.putSerializable("data", (Serializable) arrayList);
                                        message.setData(bundle);
                                        handler.sendMessage(message);



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
        }).start();

    }

}
