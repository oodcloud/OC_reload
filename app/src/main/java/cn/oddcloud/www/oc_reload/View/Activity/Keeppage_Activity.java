package cn.oddcloud.www.oc_reload.View.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.oddcloud.www.oc_reload.Crt.DatabaseHelper;
import cn.oddcloud.www.oc_reload.Model.Collect_Page;
import cn.oddcloud.www.oc_reload.R;
import cn.oddcloud.www.oc_reload.Utils.Database_Utils;
import cn.oddcloud.www.oc_reload.Utils.MsharedPreferences;
import cn.oddcloud.www.oc_reload.View.Adapter.MArrayAdapter;


public class Keeppage_Activity extends Activity implements Toolbar.OnMenuItemClickListener {
    private ListView listView;
//    private MArrayAdapter adapter;
    private List<HashMap<String, String>> list;

    /**
     * 定义数据库
     *
     * @param savedInstanceState
     */
    private SQLiteDatabase db;
    private DatabaseHelper sqLiteOpenHelper;
    private Database_Utils database_utils;
    private Cursor cursor;
    public static final String TAG = "DaoExample";
    public MArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keeppage_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.feedback_toolbar);
        toolbar.setTitle("收藏文章");
        list = new ArrayList();
        listView = (ListView) findViewById(R.id.list_keep);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        database_utils = new Database_Utils();
        database_utils.opendb(Keeppage_Activity.this);
         JSONArray jsonArray= database_utils.DeptArray(0);


        Log.d("jsonArray"," "+jsonArray.toString());
         if (jsonArray.length()!=0)
         {
             HashMap<String, String> map;
             for (int i=0;i<jsonArray.length();i++)
             {
                 map = new HashMap<>();
                 try {

                     JSONObject jsonObject= (JSONObject) jsonArray.get(i);
                     map.put("title", jsonObject.getString("title"));
                     map.put("time", jsonObject.getString("time"));
                     map.put("site", jsonObject.getString("site"));
                     list.add(map);
                 } catch (JSONException e) {
                     e.printStackTrace();
                 }

             }
             database_utils.closedb(this);
             adapter = new MArrayAdapter(this, R.layout.list_item_card_small, list,0);
             listView.setAdapter(adapter);
         }
        else {
             final ProgressDialog  dialog = ProgressDialog.show(this, "加载中...", "正在加载，请稍后！");
             dialog.setCanceledOnTouchOutside(false);
             dialog.setCancelable(true);
             BmobQuery<Collect_Page> query = new BmobQuery<Collect_Page>();
             query.addWhereEqualTo("phone", MsharedPreferences.Getphone(this));
             query.order("-updatedAt");
             query.findObjects(new FindListener<Collect_Page>() {
                 @Override
                 public void done(List<Collect_Page> object, BmobException e) {
                     if(e==null){
                         HashMap<String, String> map;
                         for (int i=0;i<object.size();i++)
                         {
                             dialog.dismiss();
                             map = new HashMap<>();
                                 map.put("title", object.get(i).getTitle());
                                 map.put("time", object.get(i).getTime());
                                 map.put("site",object.get(i).getSite());
                                 list.add(map);
                         }
                         adapter = new MArrayAdapter(Keeppage_Activity.this, R.layout.list_item_card_small, list,0);
                         listView.setAdapter(adapter);
                     }else{
                         dialog.dismiss();
                         Toast.makeText(Keeppage_Activity.this,"当前无收藏文章",Toast.LENGTH_SHORT).show();
                     }
                 }

             });


         }


    }







    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        return super.onMenuItemSelected(featureId, item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        list.clear();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}
