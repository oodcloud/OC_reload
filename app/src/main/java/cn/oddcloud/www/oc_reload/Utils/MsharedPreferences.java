package cn.oddcloud.www.oc_reload.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by wangyujie on 2016/9/6.
 */
public class MsharedPreferences {
    public static SharedPreferences sharedPreferences;
    public static void Setphone(Context context,String phone){
        sharedPreferences=context.getSharedPreferences("User", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("phone",phone).commit();
        }
    public static String Getphone(Context context)
    {
        sharedPreferences=context.getSharedPreferences("User", Activity.MODE_PRIVATE);
       return sharedPreferences.getString("phone","");

    }
}
