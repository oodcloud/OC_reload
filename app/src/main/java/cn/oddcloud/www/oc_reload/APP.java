package cn.oddcloud.www.oc_reload;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.baidu.apistore.sdk.ApiStoreSDK;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;

/**
 * Created by Administrator on 2016/6/1.
 */
public class APP extends Application {
    //把请求队列定义称为全局的
    public static RequestQueue queue;
    private static final String DB_NAME = "feed";
//    private static DaoSession sDaoSession;
    private static APP sInstance;
    private static SharedPreferences sSharePreferences;
    private static final String ACCOUNT_NAME_DEFAULT = "Local";
    private static final String SP_CURRENT_ACCOUNT = "current_account";
    public static APP getInstance() {
        return sInstance;
    }
    @Override
    public void onCreate() {
        ApiStoreSDK.init(this, "507ad4f249b9b275ab3f835a1732debb");
        super.onCreate();

        queue = Volley.newRequestQueue(getApplicationContext());

        //第一：默认初始化
        Bmob.initialize(this, "2965dd71443b025eebe6960a533da21e");

        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        BmobConfig config =new BmobConfig.Builder(this)
        ////设置appkey
        .setApplicationId("2965dd71443b025eebe6960a533da21e")
        ////请求超时时间（单位为秒）：默认15s
        .setConnectTimeout(30)
        ////文件分片上传时每片的大小（单位字节），默认512*1024
        .setUploadBlockSize(1024*1024)
        ////文件的过期时间(单位为秒)：默认1800s
        .setFileExpiration(2500)
        .build();
        Bmob.initialize(config);


    }
    public static RequestQueue getRequestQueue(){
        return queue;
    }

//    public static DaoSession getDaoSession() {
//        if (sDaoSession == null) {
//            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(getInstance(), DB_NAME, null);
//            sDaoSession = new DaoMaster(helper.getWritableDatabase()).newSession();
//        }
//        return sDaoSession;
//    }



    public static SharedPreferences getSharePreferences() {
        // TODO: 5/28/16 not bad to hold SharePreference ?
        if (sSharePreferences == null) {
            sSharePreferences = PreferenceManager.getDefaultSharedPreferences(getInstance());
        }
        return sSharePreferences;
    }
}